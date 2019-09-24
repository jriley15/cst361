package com.reportingapp.services.business;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import com.reportingapp.beans.Currency;
import com.reportingapp.services.data.CurrencyDAO;

@Stateless
@Local(ICurrencyService.class)
@Alternative
public class CurrencyService implements ICurrencyService
{
	@EJB
	CurrencyDAO service;
	
	public CurrencyService()
	{
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Currency> getAllCurrencies() throws Exception 
	{
		try
		{
			return service.getAllCurrencies();			
		}
		catch(Exception e)
		{
			throw e;
		}
	}
}
