using System;
using System.Collections.Generic;
using System.Text;

namespace IoTDevice.Data
{
    public class CurrencyDto
    {
        public string currencyISOCode { get; set; }

        public decimal currencyUSDExchangeRate { get; set; }
    }
}
