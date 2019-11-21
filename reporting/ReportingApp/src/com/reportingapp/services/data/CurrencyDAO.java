package com.reportingapp.services.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import com.reportingapp.beans.Currency;
import com.reportingapp.services.util.LoggingInterceptor;
import com.reportingapp.services.util.LoggingService;
import com.reportingapp.services.util.LoggingService.LogLevel;

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
@Local(IDAO.class)
@LocalBean
@Interceptors(LoggingInterceptor.class)
public class CurrencyDAO implements IDAO<Currency> {
	// Inject our Logging Service.
	@Inject
	private LoggingService logger;
	// Default constructor
	public CurrencyDAO() {
	}
	/**
	 * Method for getting all currency data from the database.
	 */
	@Override
	public List<Currency> getAll() throws Exception {
		// Defining SQL statement, connecting string, username, and password to database.
		Connection conn = null;
		String url = "jdbc:mysql://nj5rh9gto1v5n05t.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/ghyhewjoohjciki4";
		String username = "t74flbsvr87xpkgq";
		String password = "ljkukrmmfw0pwtue";
		
		// Initialize empty array list of currency.
		List<Currency> currencies = new ArrayList<Currency>();
		
		// try catch the following:
		try {
			// Connect to the database.
			conn = DriverManager.getConnection(url, username, password);
			
			// Defining SQL statement
			String sql = "SELECT * FROM Currency";
			
			// Execute SQL Query and loop over result set.
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			
			// While there is another row add the currency data to the list:
			while(rs.next())
				currencies.add(new Currency(rs.getString("CURRENCY_ISO_CODE"), rs.getString("CURRENCY_NAME"), rs.getString("CURRENCY_COUNTRY"), rs.getString("CURRENCY_SYMBOL"), rs.getDouble("CURRENCY_USD_EXCHANGE_RATE"), rs.getString("DATE_RECORDED")));
			
			// Close the result set and statement.
			rs.close();
			stmnt.close();
		}
		// Catch any exceptions and print the stack trace. Log the message:
		catch(SQLException e) {
			logger.log("CurrencyDAO", "getAll", LogLevel.SEVERE, e.getMessage());
			e.printStackTrace();
		}
		// Closing connection.
		finally {
			if(conn != null) {
				try {
					// Close the connection.
					conn.close();
				}
				// Catch any exceptions and print the stack trace. Log the message
				catch(SQLException e) {
					logger.log("CurrencyDAO", "getAll", LogLevel.SEVERE, e.getMessage());
					e.printStackTrace();
				}
			}
		}
		// Return the list of currency.
		return currencies;
	}
	/**
	 * Method for getting one object from the database using an id.
	 */
	@Override
	public Currency get(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Method for adding a currency to the database.
	 */
	@Override
	public boolean add(Currency currency) throws Exception {
		// Defining SQL statement, connecting string, username, and password to database.
		Connection conn = null;
		String url = "jdbc:mysql://nj5rh9gto1v5n05t.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/ghyhewjoohjciki4";
		String username = "t74flbsvr87xpkgq";
		String password = "ljkukrmmfw0pwtue";
		
		// try catch the following:
		try {				
			// Connect to the Database
			conn = DriverManager.getConnection(url, username, password);

			// Insert a Currency
			String sql = String.format("INSERT INTO Currency(CURRENCY_ISO_CODE, CURRENCY_NAME, CURRENCY_COUNTRY, CURRENCY_SYMBOL, CURRENCY_USD_EXCHANGE_RATE, DATE_RECORDED) VALUES('%s', '%s', '%s', '%s', %.4f, '%s')", 
					currency.getCurrencyISOCode(), currency.getCurrencyName(), currency.getCurrencyCountry(), currency.getCurrencySymbol(), currency.getCurrencyUSDExchangeRate(), OffsetDateTime.now(ZoneOffset.UTC).toString());
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		}
		// Catch any exceptions and print the stack trace. Log the message:
		catch(SQLException e) {
			logger.log("CurrencyDAO", "add", LogLevel.SEVERE, e.getMessage());
			e.printStackTrace();
			// Return false if not successful:
			return false;
		}
		// Closing connection.
		finally {
			if(conn != null) {
				try {
					// Close the connection.
					conn.close();
				}
				// Catch any exceptions and print the stack trace. Log the message:
				catch(SQLException e) {
					logger.log("CurrencyDAO", "add", LogLevel.SEVERE, e.getMessage());
					e.printStackTrace();
					// Return false if not successful:
					return false;
				}
			}
		}
		// Return true if successful:
		return true;
	}
	/**
	 * Method for updating a currency in the database.
	 */
	@Override
	public boolean update(Currency currency) throws Exception {
		// Defining SQL statement, connecting string, username, and password to database:
		Connection conn = null;
		String url = "jdbc:mysql://nj5rh9gto1v5n05t.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/ghyhewjoohjciki4";
		String username = "t74flbsvr87xpkgq";
		String password = "ljkukrmmfw0pwtue";
				
		try {
			// Connect to the database:
			conn = DriverManager.getConnection(url, username, password);
			
			// Defining SQL statement
			String sql = "UPDATE Currency SET CURRENCY_USD_EXCHANGE_RATE = " + currency.getCurrencyUSDExchangeRate() + ", DATE_RECORDED = '" + OffsetDateTime.now(ZoneOffset.UTC).toString() + "' WHERE CURRENCY_ISO_CODE = '" + currency.getCurrencyISOCode() + "'";
			// Execute SQL update and close statement:
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sql);
			stmnt.close();
		}
		// Catching exceptions and print fail. Log the message:
		catch(SQLException e) {
			logger.log("CurrencyDAO", "update", LogLevel.SEVERE, e.getMessage());
			e.printStackTrace();
			// Return false if not successful:
			return false;
		}
		// Closing connection.
		finally {
			if(conn != null) {
				try {
					// Closing connection:
					conn.close();
				}
				// Catching exceptions and print fail. Log the message:
				catch(SQLException e) {
					logger.log("CurrencyDAO", "update", LogLevel.SEVERE, e.getMessage());
					e.printStackTrace();
					// Return false if not successful:
					return false;
				}
			}
		}
		// Return true if successful:
		return true;
	}
	/**
	 * Method for deleting an object from the database.
	 */
	@Override
	public boolean delete(Currency object) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
