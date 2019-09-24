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

@Stateless
@Local(ICurrencyDAO.class)
@LocalBean
public class CurrencyDAO implements ICurrencyDAO
{
	public CurrencyDAO()
	{
	}

	@Override
	public List<Currency> getAllCurrencies() throws Exception
	{
		//defining sql statement and connecting string, as well as username and password to database
		Connection conn = null;
		String url = "jdbc:derby:C:\\Users\\Trevor\\ReportingApp";
		String username = "username";
		String password = "password";
		String sql = "SELECT * FROM IOTapp.Currency";
		List<Currency> currencies = new ArrayList<Currency>();
		
		try 
		{
			//connect to the database
			conn = DriverManager.getConnection(url, username, password);
			
			//Execute SQL Query and loop over result set
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			while(rs.next())
			{
				currencies.add(new Currency(rs.getString("CURRENCY_ISO_CODE"), rs.getString("CURRENCY_NAME"), rs.getString("CURRENCY_COUNTRY"), rs.getString("CURRENCY_SYMBOL"), rs.getDouble("CURRENCY_USD_EXCHANGE_RATE"), rs.getTimestamp("DATE_RECORDED")));
			}
			
			//cleanup result set
			rs.close();
			
		}
		//catching exceptions and printing failure
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
		//closing connection
		finally
		{
			if(conn != null)
			{
				try 
				{
					//closing connection
					conn.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return currencies;
	}
}
