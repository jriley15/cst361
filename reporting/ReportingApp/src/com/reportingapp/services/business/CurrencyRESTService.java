package com.reportingapp.services.business;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.reportingapp.beans.Currency;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Currency REST Service for returning JSON currency data.
 * To be used in any of the JS files we may have to write.
 * @author Trevor
 *
 */
@RequestScoped
@Path("/currency")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class CurrencyRESTService 
{
	// Inject our Currency Service.
	@Inject
	private ICurrencyService service;
	
	/**
	 * Method for returning all currency data in the database.
	 * @return
	 */
	@GET
	@Path("/getcurrencies")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Currency> getCurrenciesAsJson()
	{
		try
		{
			// Call getAllCurrencies on our Currency Service.
			return service.getAllCurrencies();
		}
		// Catch any exceptions and print the stack trace.
		catch (Exception e)
		{
			e.printStackTrace();
			// Return an empty array list of currency.
			return new ArrayList<Currency>();
		}
	}
}
