using System;
using IoTDevice.Services;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;

namespace IoTDevice
{
    /// <summary>
    /// Main application entry point
    /// </summary>
    public class Program
    {
        /// <summary>
        /// Main void
        /// </summary>
        /// <param name="args"></param>
        public static void Main(string[] args)
        {
            CreateHostBuilder(args).Build().Run();
        }

        /// <summary>
        /// Host builder method that configures the container and kicks off the worker service
        /// </summary>
        /// <param name="args"></param>
        /// <returns></returns>
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

                    //JavaEE REST API client config
                    services.AddHttpClient("JavaEERest", client =>
                    {
                        client.BaseAddress = new Uri("http://localhost:8080");
                        client.Timeout = TimeSpan.FromSeconds(5);
                    });

                    //adds worker to container and begins executing it on its own thread
                    services.AddHostedService<Worker>();
                });
    }
}
