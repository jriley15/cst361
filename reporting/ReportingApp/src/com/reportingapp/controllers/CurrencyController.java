package com.reportingapp.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import com.reportingapp.beans.Currency;
import com.reportingapp.services.business.ICurrencyService;

@ManagedBean
@ViewScoped
public class CurrencyController 
{
	@Inject
	ICurrencyService currencyService;
	
	public List<Currency> getAllCurrencies() 
	{
		try 
		{
			return currencyService.getAllCurrencies();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return new ArrayList<Currency>();
	}
}
