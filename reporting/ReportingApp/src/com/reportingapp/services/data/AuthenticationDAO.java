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
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import com.reportingapp.beans.Registration;
import com.reportingapp.services.util.LoggingInterceptor;
import com.reportingapp.services.util.LoggingService;
import com.reportingapp.services.util.LoggingService.LogLevel;

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
@Local(IDAO.class)
@LocalBean
@Interceptors(LoggingInterceptor.class)
public class AuthenticationDAO implements IDAO<Registration> {
	// Inject our Logging Service.
	@Inject
	private LoggingService logger;
	// Default constructor.
	public AuthenticationDAO() {
	}
	/**
	 * Method for getting all objects from the database.
	 */
	@Override
	public List<Registration> getAll() throws Exception {
		// Defining SQL statement, connecting string, username, and password to database.
		Connection conn = null;
		String url = "jdbc:mysql://nj5rh9gto1v5n05t.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/ghyhewjoohjciki4";
		String username = "t74flbsvr87xpkgq";
		String password = "ljkukrmmfw0pwtue";
		
		// Initialize empty array list of currency.
		List<Registration> users = new ArrayList<Registration>();
				
		try {
			// Connect to the database.
			conn = DriverManager.getConnection(url, username, password);
			
			// Defining SQL statement
			String sql = "SELECT * FROM Authentication";
			
			// Execute SQL Query and loop over result set.
			Statement stmnt = conn.createStatement();
			ResultSet rs = stmnt.executeQuery(sql);
			
			// While there is another row add the user data to the list.:
			while(rs.next())
				users.add(new Registration(rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("USERNAME"), rs.getString("PASSWORD")));
			
			// Close the result set and statement.
			rs.close();
			stmnt.close();
		}
		// Catch any exceptions and print the stack trace. Log the message:
		catch(SQLException e) {
			logger.log("AuthenticationDAO", "getAll", LogLevel.SEVERE, e.getMessage());
			e.printStackTrace();
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
					logger.log("AuthenticationDAO", "getAll", LogLevel.SEVERE, e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		// Return the list of currency.
		return users;
	}
	/**
	 * Method for getting one object from the database using an id.
	 */
	@Override
	public Registration get(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Method for adding an object to the database.
	 */
	@Override
	public boolean add(Registration user) throws Exception {
		// Defining SQL statement, connecting string, username, and password to database.
		Connection conn = null;
		String url = "jdbc:mysql://nj5rh9gto1v5n05t.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/ghyhewjoohjciki4";
		String username = "t74flbsvr87xpkgq";
		String password = "ljkukrmmfw0pwtue";
		String sql = "INSERT INTO Authentication(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD) VALUES('" + user.getFirstName() + "', '" + user.getLastName() +"', '" + user.getUsername() + "', '" + user.getPassword() +"')";
		
		try {
			// Connect to the database.
			conn = DriverManager.getConnection(url, username, password);

			// Execute SQL Query.
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sql);
			
		}
		// Catching exceptions and throw it. Log the message:
		catch (Exception e) {
			logger.log("AuthenticationDAO", "add", LogLevel.SEVERE, e.getMessage());
			e.printStackTrace();
			return false;
		}
		// Closing connection.
		finally {
			if (conn != null) {
				try {
					// Close the connection.
					conn.close();
				}
				// Catching exceptions and print the stack trace. Log the message:
				catch (SQLException e) {
					logger.log("AuthenticationDAO", "add", LogLevel.SEVERE, e.getMessage());
					e.printStackTrace();
					return false;
				}
			}
		}
		// Return true on success.
		return true;
	}
	/**
	 * Method for updating an object in the database.
	 */
	@Override
	public boolean update(Registration object) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Method for deleting an object from the database.
	 */
	@Override
	public boolean delete(Registration object) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
