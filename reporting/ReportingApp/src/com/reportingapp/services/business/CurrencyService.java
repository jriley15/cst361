package com.reportingapp.services.business;

import java.util.ArrayList;
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
public class CurrencyService implements ICurrencyService {
	// EJB Property of our CurrencyDAO.
	@EJB
	private CurrencyDAO service;
	// Default constructor.
	public CurrencyService() {
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
			return service.getAll();			
		}
		// Catch any exceptions and throw it.
		catch(Exception e)
		{
			throw e;
		}
	}
	/**
	 * Method for returning adding or updating a currency in the database.
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean addOrUpdateCurrencies(List<Currency> currencies) throws Exception
	{
		try
		{
			// Get all currency names in the database
			List<String> currentISOCodes = new ArrayList<String>();
			for (Currency currency : service.getAll()) {
				currentISOCodes.add(currency.getCurrencyISOCode());
			}
			// Check if currencies being added or updated exist
			for (Currency currency : currencies) {
				if (currentISOCodes.contains(currency.getCurrencyISOCode())) {
					if (!service.update(currency)) {
						return false;
					}
				}		
				else {
					if (!service.add(currency)) {
						return false;
					}
				}
			}
			return true;
		}
		// Catch any exceptions and throw it.
		catch(Exception e)
		{
			throw e;
		}
	}
}
