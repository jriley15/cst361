using System;
using IoTDevice.Services;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

namespace IoTDevice
{
    public class Program
    {
        public static void Main(string[] args)
        {
            CreateHostBuilder(args).Build().Run();
        }

        public static IHostBuilder CreateHostBuilder(string[] args) =>

            Host.CreateDefaultBuilder(args)
                .ConfigureServices((hostContext, services) =>
                {
                    services.AddSingleton<ICurrencyService, CurrencyService>();

                    services.AddHttpClient("RapidApi", client =>
                    {
                        client.BaseAddress = new Uri(hostContext.Configuration["RapidAPIURL"]);
                        client.DefaultRequestHeaders.Add("x-rapidapi-host", hostContext.Configuration["RapidAPIHost"]);
                        client.DefaultRequestHeaders.Add("x-rapidapi-key", hostContext.Configuration["RapidAPIKey"]);
                        client.Timeout = TimeSpan.FromSeconds(3);
                    });
                    //.AddTransientHttpErrorPolicy(builder => builder.WaitAndRetryAsync(new[]
                    //{
                    //    TimeSpan.FromSeconds(1),
                    //    TimeSpan.FromSeconds(5),
                    //    TimeSpan.FromSeconds(10)
                    //}));

                    services.AddHostedService<Worker>();
                });
    }
}
