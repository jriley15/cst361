package com.reportingapp.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import com.reportingapp.beans.Currency;
import com.reportingapp.services.business.ICurrencyService;
import com.reportingapp.services.util.LoggingInterceptor;
import com.reportingapp.services.util.LoggingService;
import com.reportingapp.services.util.LoggingService.LogLevel;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Controller for handling currency data requests.
 * @author Trevor
 *
 */
@Named
@ViewScoped
@Interceptors(LoggingInterceptor.class)
public class CurrencyController implements Serializable {
	private static final long serialVersionUID = 7473923518828646579L;
	// Inject our Currency Service.
	@Inject
	ICurrencyService currencyService;
	// Inject our Logging Service.
	@Inject
	private LoggingService logger;
	/**
	 * Method for returning all currency data in the database.
	 * @return List Currency returns a list of Currency Objects
	 */
	public List<Currency> getAllCurrencies() {
		try {
			// Call getAllCurrencies on our currency service.
			return currencyService.getAllCurrencies();
		}
		// Catch any exceptions and print the stack trace.
		catch (Exception e) {
			logger.log("CurrencyController", "getAllCurrencies", LogLevel.SEVERE, e.getMessage());
			e.printStackTrace();
		}
		// Return an empty array list of currency if something went wrong.
		return new ArrayList<Currency>();
	}
}