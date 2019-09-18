using System;
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

        protected override async Task ExecuteAsync(CancellationToken stoppingToken)
        {
            while (!stoppingToken.IsCancellationRequested)
            {
                _logger.LogInformation("Worker running at: {time}", DateTimeOffset.Now);

                var currencies = await _currencyService.GetCurrencyConversions();

                foreach (var currency in currencies)
                {
                    _logger.LogDebug("Currency: " + currency.Type.ToString() + " = " + currency.Value + " USD");
                }

                await Task.Delay(10000, stoppingToken);
            }
        }
    }
}
