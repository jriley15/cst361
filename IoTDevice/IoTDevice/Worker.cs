using System;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using IoTDevice.Services;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;

namespace IoTDevice
{
    public class Worker : BackgroundService
    {
        private readonly ILogger<Worker> _logger;
        private readonly ICurrencyService _currencyService;

        public Worker(ILogger<Worker> logger, ICurrencyService currencyService)
        {
            _logger = logger;
            _currencyService = currencyService;
        }

        //main application loop
        protected override async Task ExecuteAsync(CancellationToken stoppingToken)
        {
            while (!stoppingToken.IsCancellationRequested)
            {
                _logger.LogInformation("Worker running at: {time}", DateTimeOffset.Now);

                //fetch currencies
                var currencies = (await _currencyService.GetCurrencyConversions()).ToList();

                var syncSuccessful = await _currencyService.SyncCurrencyValues(currencies);

                //log currencies to console
                foreach (var currency in currencies)
                {
                    _logger.LogDebug("Currency: " + currency.currencyISOCode + " = " + currency.currencyUSDExchangeRate + " USD");
                }

                //sleep for 20 seconds
                await Task.Delay(20000, stoppingToken);
            }
        }
    }
}
