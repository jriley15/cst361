package com.reportingapp.services.business;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.reportingapp.beans.Currency;
import com.reportingapp.beans.DTOBase;
import com.reportingapp.services.util.Factory;
import com.reportingapp.services.util.Factory.DTOType;
import com.reportingapp.services.util.LoggingInterceptor;
import com.reportingapp.services.util.LoggingService;
import com.reportingapp.services.util.LoggingService.LogLevel;

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
@Interceptors(LoggingInterceptor.class)
public class CurrencyRESTService {
	// Inject our Currency Service.
	@Inject
	private ICurrencyService service;
	// Inject our Logging Service.
	@Inject
	private LoggingService logger;
	/**
	 * Method for returning all currency data in the database.
	 * @return
	 */
	@GET
	@Path("/getcurrencies")
	@Produces(MediaType.APPLICATION_JSON)
	public DTOBase getCurrenciesAsJson() {
		try {
			// Call getAllCurrencies on our Currency Service and return DTO from Factory:
			return Factory.getDTO(DTOType.DTO, 200, "Ok.", service.getAllCurrencies());
		}
		// Catch any exceptions and print the stack trace. Log the message.
		catch (Exception e) {
			logger.log("CurrencyRESTService", "getCurrenciesAsJson", LogLevel.SEVERE, e.getMessage());
			e.printStackTrace();
			// Return an empty DTO with error message using Factory.
			return Factory.getDTO(DTOType.DTO, 500, "An error occured on the server.", null);
		}
	}
	/**
	 * Method for adding a currency to the database
	 * @return
	 */
	@POST
	@Path("/addorupdatecurrencies")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DTOBase addOrUpdateCurrenciesAsJson(List<Currency> currencies) {
		try {
			// Call addOrUpdateCurrencies on our Currency Service and return DTO on success:
			if (service.addOrUpdateCurrencies(currencies)) {
				return Factory.getDTO(DTOType.DTO, 200, "Ok.", currencies);
			}
			// Else return empty DTO with error message:
			else {
				return Factory.getDTO(DTOType.DTO, 500, "An error occured on the server.", null);
			}
		}
		// Catch any exceptions and print the stack trace. Log the message.
		catch (Exception e) {
			logger.log("CurrencyRESTService", "addCurrenciesAsJson", LogLevel.SEVERE, e.getMessage());
			e.printStackTrace();
			// Return an empty DTO with error message.
			return Factory.getDTO(DTOType.DTO, 500, "An error occured on the server.", null);
		}
	}
}