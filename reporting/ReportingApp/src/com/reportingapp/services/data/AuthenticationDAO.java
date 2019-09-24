package com.reportingapp.services.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.reportingapp.beans.Registration;
import com.reportingapp.beans.User;

@Stateless
@Local(IAuthenticationDAO.class)
@LocalBean
public class AuthenticationDAO implements IAuthenticationDAO
{
	public AuthenticationDAO()
	{
	}
	
	@Override
	public void registerUser(Registration user) throws Exception 
	{
		//defining sql statement and connecting string, as well as username and password to database
		Connection conn = null;
		String url = "jdbc:derby:C:\\Users\\Trevor\\ReportingApp";
		String username = "username";
		String password = "password";
		String sql = "INSERT INTO IOTapp.Authentication(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD) VALUES('" + user.getFirstName() + "', '" + user.getLastName() +"', '" + user.getUsername() + "', '" + user.getPassword() +"')";
		
		try 
		{
			//connect to the database
			conn = DriverManager.getConnection(url, username, password);

			//Execute SQL Query and loop over result set
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sql);
			
		}
		//catching exceptions and printing failure
		catch (Exception e) 
		{
			throw e;
		}
		//closing and catching exceptions
		finally
		{
			if (conn != null)
			{
				try 
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public boolean loginCheck(User user) throws Exception 
	{
		boolean result = false;
		Connection conn = null;
		String url = "jdbc:derby:C:\\Users\\Trevor\\ReportingApp";
		String username = "username";
		String password = "password";
		String sql = "SELECT * FROM IOTapp.Authentication WHERE USERNAME = '" + user.getUsername() + "' AND PASSWORD = '" + user.getPassword() + "'";
		
		try 
		{
			//connect to the database
			conn = DriverManager.getConnection(url, username, password);
			
			//Execute SQL Query and loop over result set
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			
			if (rs.next())
			{
				result = true;				
			}
			
			rs.close();
		}
		//printing exceptions and failure
		catch (Exception e) 
		{
			throw e;
		}
		//closing connection and printing exceptions
		finally
		{
			if (conn != null)
			{
				try 
				{
					//closing connection
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
}
