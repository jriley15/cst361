using IoTDevice.Data;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace IoTDevice.Services
{
    public interface ICurrencyService
    {
        Task<IEnumerable<CurrencyDto>> GetCurrencyConversions();

        Task<bool> SyncCurrencyValues(List<CurrencyDto> currencyDtos);
    }

    public class CurrencyService : ICurrencyService
    {
        private readonly IHttpClientFactory _clientFactory;
        private readonly ILogger<Worker> _logger;

        public CurrencyService(ILogger<Worker> logger, IHttpClientFactory clientFactory)
        {
            _logger = logger;
            _clientFactory = clientFactory;
        }

        //kicks of http requests to get currency conversions from api
        public async Task<IEnumerable<CurrencyDto>> GetCurrencyConversions()
        {
            var currencies = new List<CurrencyDto>();
            var currencyTasks = new List<Task<Currency>>();

            // Start http requests for every currency type.
            foreach (var currencyType in (CurrencyType[])Enum.GetValues(typeof(CurrencyType)))
            {
                currencyTasks.Add(GetCurrencyTask(currencyType));
            }

            // Wait for them to all finish concurrently.
            await Task.WhenAll(currencyTasks);

            // Get the values from each task.
            foreach (Task<Currency> task in currencyTasks)
            {
                //await the async fetch
                var currency = await task;

                //dto mapping
                currencies.Add(new CurrencyDto() { currencyISOCode = currency.Type.ToString(), currencyUSDExchangeRate = currency.Value });
            }

            return currencies;
        }

        //async task for http request
        private async Task<Currency> GetCurrencyTask(CurrencyType currencyType)
        {
            var currency = new Currency() { Type = currencyType };
            //get http client from factory
            var client = _clientFactory.CreateClient("RapidApi");

            try
            {
                //parse value from api
                currency.Value = Decimal.Parse(await client.GetStringAsync("/exchange?q=1&from=" + currencyType.ToString() + "&to=USD"), NumberStyles.Float);
            }
            catch (Exception e)
            {
                // Http request failed. FYI - happens for CNH every time.
                _logger.LogError("GetCurrencyTask threw exception: " + e.Message);
            }
            return currency;
        }

        //sends updated currency values to the JavaEE REST API
        public async Task<bool> SyncCurrencyValues(List<CurrencyDto> currencyDtos)
        {
            //create a new http client
            var client = _clientFactory.CreateClient("JavaEERest");

            try
            {
                //post list of currency dto's to the rest endpoint
                await client.PostAsync("/ReportingApp/rest/currency/addorupdatecurrencies", new StringContent(JsonConvert.SerializeObject(currencyDtos), Encoding.UTF8, "application/json"));

                return true;
            }
            catch (Exception e)
            {
                // Http request failed.
                _logger.LogError("SyncCurrencyValues threw exception: " + e.Message);
            }

            return false;
        }
    }
}