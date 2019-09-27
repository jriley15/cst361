package com.reportingapp.services.business;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import com.reportingapp.beans.Currency;
import com.reportingapp.services.data.CurrencyDAO;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Class for handling the business logic of currency requests.
 * @author Trevor
 *
 */
@Stateless
@Local(ICurrencyService.class)
@Alternative
public class CurrencyService implements ICurrencyService
{
	// EJB Property of our CurrencyDAO.
	@EJB
	private CurrencyDAO service;
	
	// Default constructor.
	public CurrencyService()
	{
	}
	
	/**
	 * Method for returning all currencies in the database which will call on our DAO.
	 * @return List<Currency>
	 * @throws Exception
	 */
	@Override
	public List<Currency> getAllCurrencies() throws Exception 
	{
		try
		{
			// Return all currencies calling on our DAO.
			return service.getAllCurrencies();			
		}
		// Catch any exceptions and throw it.
		catch(Exception e)
		{
			throw e;
		}
	}
}
