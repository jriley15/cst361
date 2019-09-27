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

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Data Access Class for writing and reading login and registration data from the database.
 * @author Trevor
 *
 */
@Stateless
@Local(IAuthenticationDAO.class)
@LocalBean
public class AuthenticationDAO implements IAuthenticationDAO
{
	// Default constructor.
	public AuthenticationDAO()
	{
	}
	
	/**
	 * Method for registering a user in the database.
	 */
	@Override
	public void registerUser(Registration user) throws Exception 
	{
		// Defining SQL statement, connecting string, username, and password to database.
		Connection conn = null;
		String url = "jdbc:derby:C:\\Users\\Trevor\\ReportingApp";
		String username = "username";
		String password = "password";
		String sql = "INSERT INTO IOTapp.Authentication(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD) VALUES('" + user.getFirstName() + "', '" + user.getLastName() +"', '" + user.getUsername() + "', '" + user.getPassword() +"')";
		
		try 
		{
			// Connect to the database.
			conn = DriverManager.getConnection(url, username, password);

			// Execute SQL Query.
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sql);
			
		}
		// Catching exceptions and throw it.
		catch (Exception e) 
		{
			throw e;
		}
		// Closing connection.
		finally
		{
			if (conn != null)
			{
				try 
				{
					// Close the connection
					conn.close();
				}
				// Catching exceptions and print the stack trace.
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Method for checking if a user exists in the authentication table (logging in).
	 */
	@Override
	public boolean loginCheck(User user) throws Exception 
	{
		// Set result as bool default to false.
		boolean result = false;
		// Defining SQL statement, connecting string, username, and password to database.
		Connection conn = null;
		String url = "jdbc:derby:C:\\Users\\Trevor\\ReportingApp";
		String username = "username";
		String password = "password";
		String sql = "SELECT * FROM IOTapp.Authentication WHERE USERNAME = '" + user.getUsername() + "' AND PASSWORD = '" + user.getPassword() + "'";
		
		try 
		{
			// Connect to the database.
			conn = DriverManager.getConnection(url, username, password);
			
			// Execute SQL Query.
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			
			// Set result to true if there was a row returned.
			if (rs.next())
			{
				result = true;				
			}
			
			// Close the result set.
			rs.close();
		}
		// Catch any Exceptions and throw it.
		catch (Exception e) 
		{
			throw e;
		}
		// Closing connection.
		finally
		{
			if (conn != null)
			{
				try 
				{
					// Close the connection.
					conn.close();
				}
				// Catch any Exceptions and print the stack trace.
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		// return the bool result.
		return result;
	}
}
