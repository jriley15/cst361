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
                    //add currency service singleton service to container
                    services.AddSingleton<ICurrencyService, CurrencyService>();

                    //adds http client to container
                    services.AddHttpClient("RapidApi", client =>
                    {
                        client.BaseAddress = new Uri(hostContext.Configuration["RapidAPIURL"]);
                        client.DefaultRequestHeaders.Add("x-rapidapi-host", hostContext.Configuration["RapidAPIHost"]);
                        client.DefaultRequestHeaders.Add("x-rapidapi-key", hostContext.Configuration["RapidAPIKey"]);
                        client.Timeout = TimeSpan.FromSeconds(3);
                    });

                    //JavaEE REST API client
                    services.AddHttpClient("JavaEERest", client =>
                    {
                        client.BaseAddress = new Uri("http://localhost:8080");
                        client.Timeout = TimeSpan.FromSeconds(5);
                    });

                    //.AddTransientHttpErrorPolicy(builder => builder.WaitAndRetryAsync(new[]
                    //{
                    //    TimeSpan.FromSeconds(1),
                    //    TimeSpan.FromSeconds(5),
                    //    TimeSpan.FromSeconds(10)
                    //}));

                    //adds worker to container and begins executing it
                    services.AddHostedService<Worker>();
                });
    }
}
