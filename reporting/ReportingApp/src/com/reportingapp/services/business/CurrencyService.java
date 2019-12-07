package com.reportingapp.services.business;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import com.reportingapp.beans.Currency;
import com.reportingapp.services.data.CurrencyDAO;
import com.reportingapp.services.util.LoggingInterceptor;
import com.reportingapp.services.util.LoggingService;
import com.reportingapp.services.util.LoggingService.LogLevel;

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
@Interceptors(LoggingInterceptor.class)
public class CurrencyService implements ICurrencyService {
	// EJB Property of our CurrencyDAO.
	@EJB
	private CurrencyDAO service;
	// Inject our Logging Service.
	@Inject
	private LoggingService logger;
	// Default constructor.
	public CurrencyService() {
	}
	/**
	 * Method for returning all currencies in the database which will call on our DAO.
	 * @return List Currency list of Currency objects
	 * @throws Exception throws Exception
	 */
	@Override
	public List<Currency> getAllCurrencies() throws Exception {
		try {
			// Return all currencies calling on our DAO.
			return service.getAll();			
		}
		// Catch any exceptions and throw it. Log the message:
		catch(Exception e) {
			logger.log("CurrencyService", "getAllCurrencies", LogLevel.SEVERE, e.getMessage());
			throw e;
		}
	}
	/**
	 * Method for returning adding or updating a currency in the database.
	 * @return boolean
	 * @throws Exception throws Exception
	 */
	@Override
	public boolean addOrUpdateCurrencies(List<Currency> currencies) throws Exception {
		try {
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
		// Catch any exceptions and throw it. Log the message:
		catch(Exception e)
		{
			logger.log("CurrencyService", "addOrUpdateCurrencies", LogLevel.SEVERE, e.getMessage());
			throw e;
		}
	}
}
