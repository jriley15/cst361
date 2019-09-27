using IoTDevice.Data;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Net.Http;
using System.Threading.Tasks;

namespace IoTDevice.Services
{
    public interface ICurrencyService
    {
        Task<IEnumerable<Currency>> GetCurrencyConversions();
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
        public async Task<IEnumerable<Currency>> GetCurrencyConversions()
        {
            var currencies = new List<Currency>();
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
                currencies.Add(await task);
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
    }
}