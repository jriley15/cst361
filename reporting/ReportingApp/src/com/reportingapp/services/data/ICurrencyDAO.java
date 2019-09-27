package com.reportingapp.services.data;

import java.util.List;
import javax.ejb.Local;
import com.reportingapp.beans.Currency;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Interface (contract) for our Currency DAO.
 * @author Trevor
 *
 */
@Local
public interface ICurrencyDAO
{
	/**
	 * Method for getting all currency data from the database.
	 */
	public List<Currency> getAllCurrencies() throws Exception;
}
