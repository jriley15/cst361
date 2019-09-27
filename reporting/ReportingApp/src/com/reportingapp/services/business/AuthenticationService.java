package com.reportingapp.services.business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import com.reportingapp.beans.Registration;
import com.reportingapp.beans.User;
import com.reportingapp.services.data.AuthenticationDAO;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Authentication Service for handling logic concerning logging in and registering.
 * @author Trevor
 *
 */
@Stateless
@Local(IAuthenticationService.class)
@Alternative
public class AuthenticationService implements IAuthenticationService
{
	// EJB Property of our AuthenticationDAO.
	@EJB
	private AuthenticationDAO service;
	
	// Default constructor.
	public AuthenticationService()
	{
	}
	
	/**
	 * Method for inserting user into the database by using the DataConnection class's methods.
	 * @param user type Register
	 * @throws Exception 
	 */
	@Override
	public void registerUser(Registration user) throws Exception 
	{
		try 
		{
			// Call the registerUser method on our DAO passing in the Registration object.
			service.registerUser(user);
		}
		// Catch any exceptions and throw it.
		catch (Exception e) 
		{
			throw e;
		}
	}
	
	/**
	 * Method for checking if user's username and password is in database by using the DataConnection class's methods.
	 * @param user type Register
	 * @return boolean
	 * @throws Exception 
	 */
	@Override
	public boolean loginCheck(User user) throws Exception 
	{
		try 
		{
			// Call loginCheck on our DAO passing in the User object.
			return service.loginCheck(user);
		}
		// Catch any exceptions and throw it.
		catch (Exception e) 
		{
			throw e;
		}
	}
}
