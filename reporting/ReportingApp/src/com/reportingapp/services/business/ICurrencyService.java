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
public interface ICurrencyService
{
	/**
	 * Method for returning all currencies in the database which will call on our DAO.
	 * @return List<Currency>
	 * @throws Exception
	 */
	public List<Currency> getAllCurrencies() throws Exception;
}