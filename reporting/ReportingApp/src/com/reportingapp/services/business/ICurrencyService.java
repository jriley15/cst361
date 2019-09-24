package com.reportingapp.services.business;

import java.util.List;
import javax.ejb.Local;
import com.reportingapp.beans.Currency;

@Local
public interface ICurrencyService
{
	public List<Currency> getAllCurrencies() throws Exception;
}