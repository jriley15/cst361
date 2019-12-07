using System;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using IoTDevice.Services;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;

namespace IoTDevice
{
    /// <summary>
    /// Main Worker service class that implements a background service
    /// </summary>
    public class Worker : BackgroundService
    {
        private readonly ILogger<Worker> _logger;
        private readonly ICurrencyService _currencyService;

        /// <summary>
        /// constructor with injected services
        /// </summary>
        /// <param name="logger"></param>
        /// <param name="currencyService"></param>
        public Worker(ILogger<Worker> logger, ICurrencyService currencyService)
        {
            _logger = logger;
            _currencyService = currencyService;
        }

        /// <summary>
        /// main application loop
        /// </summary>
        /// <param name="stoppingToken"></param>
        /// <returns></returns>
        protected override async Task ExecuteAsync(CancellationToken stoppingToken)
        {
            while (!stoppingToken.IsCancellationRequested)
            {
                //log the current time
                _logger.LogInformation("Worker running at: {time}", DateTimeOffset.Now);

                //fetch currencies
                var currencies = (await _currencyService.GetCurrencyConversions()).ToList();

                //sends updated currency values to the JavaEE REST api
                var syncSuccessful = await _currencyService.SyncCurrencyValues(currencies);

                //log currencies to console
                foreach (var currency in currencies)
                {
                    _logger.LogDebug("Currency: " + currency.CurrencyISOCode + " = " + currency.CurrencyUSDExchangeRate + " USD");
                }

                //sleep for 10 seconds
                await Task.Delay(10000, stoppingToken);
            }
        }
    }
}
