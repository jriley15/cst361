package com.reportingapp.services.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.reportingapp.beans.Currency;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Data Access Class for writing and reading currency data from the database.
 * @author Trevor
 *
 */
@Stateless
@Local(ICurrencyDAO.class)
@LocalBean
public class CurrencyDAO implements ICurrencyDAO
{
	// Default constructor.
	public CurrencyDAO()
	{
	}

	/**
	 * Method for getting all currency data from the database.
	 */
	@Override
	public List<Currency> getAllCurrencies() throws Exception
	{
		// Defining SQL statement, connecting string, username, and password to database.
		Connection conn = null;
		String url = "jdbc:derby:C:\\Users\\Trevor\\ReportingApp";
		String username = "username";
		String password = "password";
		String sql = "SELECT * FROM IOTapp.Currency";
		
		// Initialize empty array list of currency.
		List<Currency> currencies = new ArrayList<Currency>();
		
		// try catch the following:
		try 
		{
			// Connect to the database.
			conn = DriverManager.getConnection(url, username, password);
			
			// Execute SQL Query and loop over result set.
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			
			// While there is another row:
			while(rs.next())
			{
				// Add the currency data to the list.
				currencies.add(new Currency(rs.getString("CURRENCY_ISO_CODE"), rs.getString("CURRENCY_NAME"), rs.getString("CURRENCY_COUNTRY"), rs.getString("CURRENCY_SYMBOL"), rs.getDouble("CURRENCY_USD_EXCHANGE_RATE"), rs.getTimestamp("DATE_RECORDED")));
			}
			
			// Close the result set.
			rs.close();
			
		}
		// Catch any exceptions and print the stack trace.
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		// Closing connection.
		finally
		{
			if(conn != null)
			{
				try 
				{
					// Close the connection.
					conn.close();
				}
				// Catch any exceptions and print the stack trace.
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		// Return the list of currency.
		return currencies;
	}
}
