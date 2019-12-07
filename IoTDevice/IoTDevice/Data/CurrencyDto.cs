namespace IoTDevice.Data
{
    /// <summary>
    /// Currency Dto class object
    /// </summary>
    public class CurrencyDto
    {
        public string CurrencyIsoCode { get; set; }

        public decimal CurrencyUsdExchangeRate { get; set; }
    }
}