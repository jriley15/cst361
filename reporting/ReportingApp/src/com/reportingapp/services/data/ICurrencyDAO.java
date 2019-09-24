package com.reportingapp.services.data;

import java.util.List;
import javax.ejb.Local;
import com.reportingapp.beans.Currency;

@Local
public interface ICurrencyDAO
{
	public List<Currency> getAllCurrencies() throws Exception;
}
