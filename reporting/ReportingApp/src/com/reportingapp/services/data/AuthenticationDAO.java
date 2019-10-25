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
import com.reportingapp.beans.Registration;

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
public class AuthenticationDAO implements IDAO<Registration> {
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
		String url = "jdbc:mysql://tkck4yllxdrw0bhi.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/cjr9k7s8xz19nnga";
		String username = "vqof07ohtf203y72";
		String password = "z7ds5u9r4qk4db8m";
		
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
		// Catch any exceptions and print the stack trace.
		catch(SQLException e) {
			e.printStackTrace();
		}
		// Closing connection.
		finally {
			if(conn != null) {
				try {
					// Close the connection.
					conn.close();
				}
				// Catch any exceptions and print the stack trace.
				catch(SQLException e) {
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
		String url = "jdbc:mysql://tkck4yllxdrw0bhi.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/cjr9k7s8xz19nnga";
		String username = "vqof07ohtf203y72";
		String password = "z7ds5u9r4qk4db8m";
		String sql = "INSERT INTO Authentication(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD) VALUES('" + user.getFirstName() + "', '" + user.getLastName() +"', '" + user.getUsername() + "', '" + user.getPassword() +"')";
		
		try {
			// Connect to the database.
			conn = DriverManager.getConnection(url, username, password);

			// Execute SQL Query.
			Statement stmnt = conn.createStatement();
			stmnt.executeUpdate(sql);
			
		}
		// Catching exceptions and throw it.
		catch (Exception e) {
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
				// Catching exceptions and print the stack trace.
				catch (SQLException e) {
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
