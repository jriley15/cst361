package com.reportingapp.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import com.reportingapp.beans.Currency;
import com.reportingapp.services.business.ICurrencyService;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Controller for handling currency data requests.
 * @author Trevor
 *
 */
@ManagedBean
@ViewScoped
public class CurrencyController 
{
	// Inject our Currency Service.
	@Inject
	ICurrencyService currencyService;
	
	/**
	 * Method for returning all currency data in the database.
	 * @return List<Currency>
	 */
	public List<Currency> getAllCurrencies() 
	{
		try 
		{
			// Call getAllCurrencies on our currency service.
			return currencyService.getAllCurrencies();
		}
		// Catch any exceptions and print the stack trace.
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// Return an empty array list of currency if something went wrong.
		return new ArrayList<Currency>();
	}
}
