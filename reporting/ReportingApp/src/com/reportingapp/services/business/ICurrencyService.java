package com.reportingapp.services.business;

import java.util.List;
import javax.ejb.Local;
import com.reportingapp.beans.Currency;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Interface (contract) for our Currency Service.
 * @author Trevor
 *
 */
@Local
public interface ICurrencyService {
	/**
	 * Method for returning all currencies in the database which will call on our DAO.
	 * @return List Currency list of Currency objects
	 * @throws Exception throws Exception
	 */
	public List<Currency> getAllCurrencies() throws Exception;	
	/**
	 * Method for adding or updating a list of currencies in the database.
	 * @param currencies list of Currency objects
	 * @return boolean
	 * @throws Exception throws Exception
	 */
	public boolean addOrUpdateCurrencies(List<Currency> currencies) throws Exception;
}