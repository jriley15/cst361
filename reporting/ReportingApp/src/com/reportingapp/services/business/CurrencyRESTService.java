package com.reportingapp.services.business;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.reportingapp.beans.Currency;
import com.reportingapp.beans.DTO;

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
public class CurrencyRESTService {
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
	public DTO getCurrenciesAsJson() {
		try {
			// Call getAllCurrencies on our Currency Service.
			return new DTO(200, "Ok.", service.getAllCurrencies());
		}
		// Catch any exceptions and print the stack trace.
		catch (Exception e) {
			e.printStackTrace();
			// Return an empty array list of currency.
			return new DTO(500, "An error occured on the server.", new ArrayList<Currency>());
		}
	}
	/**
	 * Method for adding and updating currencies in the database - to be consumed by our IoT Device.
	 * @return
	 */
	@POST
	@Path("/addorupdatecurrencies")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DTO addOrUpdateCurrenciesAsJson(List<Currency> currencies) {
		try {
			// Call add or update on our Currency Service.
			if (service.addOrUpdateCurrencies(currencies)) {
				return new DTO(200, "Ok.", currencies);
			}
			else {
				return new DTO(500, "An error occured on the server.", null);
			}
		}
		// Catch any exceptions and print the stack trace.
		catch (Exception e) {
			e.printStackTrace();
			// Return appropriate message
			return new DTO(500, "An error occured on the server.", null);
		}
	}
}
