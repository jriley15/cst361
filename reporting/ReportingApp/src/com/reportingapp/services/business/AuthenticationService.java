package com.reportingapp.services.business;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import com.reportingapp.beans.Registration;
import com.reportingapp.beans.User;
import com.reportingapp.services.data.AuthenticationDAO;

@Stateless
@Local(IAuthenticationService.class)
@Alternative
public class AuthenticationService implements IAuthenticationService
{
	@EJB
	AuthenticationDAO service;
	
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
			System.out.println("------------------> IN LOGIN SERVICE BITCH!!!");
			service.registerUser(user);
		} 
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
			return service.loginCheck(user);
		} 
		catch (Exception e) 
		{
			throw e;
		}
	}
}
