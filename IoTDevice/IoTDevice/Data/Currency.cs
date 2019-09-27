using System;

namespace IoTDevice.Data
{
    //currency data model
    public class Currency
    {
        public CurrencyType Type { get; set; }
        public Decimal Value { get; set; }
    }
}
