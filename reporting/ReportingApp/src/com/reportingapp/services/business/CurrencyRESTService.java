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

@RequestScoped
@Path("/currency")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class CurrencyRESTService 
{
	@Inject
	ICurrencyService service;
	
	@GET
	@Path("/getcurrencies")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Currency> getCurrenciesAsJson()
	{
		try
		{
			return service.getAllCurrencies();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new ArrayList<Currency>();
		}
	}
}
