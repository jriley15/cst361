using System;
using System.Collections.Generic;
using System.Globalization;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using IoTDevice.Data;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;

namespace IoTDevice.Services
{
    /// <summary>
    ///     Currency service interface
    /// </summary>
    public interface ICurrencyService
    {
        Task<IEnumerable<CurrencyDto>> GetCurrencyConversions();

        Task<bool> SyncCurrencyValues(List<CurrencyDto> currencyDtos);
    }

    /// <summary>
    ///     Currency service class implementation
    /// </summary>
    public class CurrencyService : ICurrencyService
    {
        //class member http client factory that creates instances of http client
        private readonly IHttpClientFactory _clientFactory;

        //class member logger service
        private readonly ILogger<Worker> _logger;

        //constructor that receives injected dependencies from the framework
        public CurrencyService(ILogger<Worker> logger, IHttpClientFactory clientFactory)
        {
            _logger = logger;
            _clientFactory = clientFactory;
        }

        /// <summary>
        ///     kicks of http requests to get currency conversions from api
        /// </summary>
        /// <returns></returns>
        public async Task<IEnumerable<CurrencyDto>> GetCurrencyConversions()
        {
            //initialize CurrencyDto and task lists
            var currencies = new List<CurrencyDto>();
            var currencyTasks = new List<Task<Currency>>();

            // Start http requests for every currency type.
            foreach (var currencyType in (CurrencyType[]) Enum.GetValues(typeof(CurrencyType)))
                currencyTasks.Add(GetCurrencyTask(currencyType));

            // Wait for them to all finish concurrently.
            await Task.WhenAll(currencyTasks);

            // Get the values from each task.
            foreach (var task in currencyTasks)
            {
                //await the async fetch
                var currency = await task;

                //dto mapping
                currencies.Add(new CurrencyDto
                    {CurrencyIsoCode = currency.Type.ToString(), CurrencyUsdExchangeRate = currency.Value});
            }

            return currencies;
        }

        /// <summary>
        ///     sends updated currency values to the JavaEE REST API
        /// </summary>
        /// <param name="currencyDtos"></param>
        /// <returns></returns>
        public async Task<bool> SyncCurrencyValues(List<CurrencyDto> currencyDtos)
        {
            //create a new http client
            var client = _clientFactory.CreateClient("JavaEERest");

            try
            {
                //post list of currency dto's to the rest endpoint
                await client.PostAsync("/ReportingApp/rest/currency/addorupdatecurrencies",
                    new StringContent(JsonConvert.SerializeObject(currencyDtos), Encoding.UTF8, "application/json"));

                return true;
            }
            catch (Exception e)
            {
                // Http request failed.
                _logger.LogError("SyncCurrencyValues threw exception: " + e.Message);
            }

            return false;
        }

        /// <summary>
        ///     async task for http request
        /// </summary>
        /// <param name="currencyType"></param>
        /// <returns></returns>
        private async Task<Currency> GetCurrencyTask(CurrencyType currencyType)
        {
            var currency = new Currency {Type = currencyType};
            //get http client from factory
            var client = _clientFactory.CreateClient("RapidApi");

            try
            {
                //parse value from api
                currency.Value =
                    decimal.Parse(await client.GetStringAsync("/exchange?q=1&from=" + currencyType + "&to=USD"),
                        NumberStyles.Float);
            }
            catch (Exception e)
            {
                // Http request failed. FYI - happens for CNH every time.
                _logger.LogError("GetCurrencyTask threw exception: " + e.Message);
            }

            return currency;
        }
    }
}