package com.reportingapp.services.business;

import java.util.ArrayList;
import java.util.List;

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
public class AuthenticationService implements IAuthenticationService {
	// EJB Property of our AuthenticationDAO.
	@EJB
	private AuthenticationDAO service;
	// Default constructor.
	public AuthenticationService() {
	}
	/**
	 * Method for inserting user into the database by using the DataConnection class's methods.
	 * @param user type Register
	 * @throws Exception 
	 */
	@Override
	public boolean registerUser(Registration registration) throws Exception {
		try {
			// Get list of all current users and usernames grabbed from the db.
			List<Registration> currentUsers = service.getAll();
			List<String> currentUsernames = new ArrayList<String>();
			// Fill the list of usernames using our list of users
			for (Registration user : currentUsers) {
				currentUsernames.add(user.getUsername());
			}
			// If there is already a username in use, return false, else register the user:
			if (currentUsernames.contains(registration.getUsername())) {
				return false;
			}
			else {
				service.add(registration);
				return true;
			}
		}
		// Catch any exceptions and throw it.
		catch (Exception e) {
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
	public boolean loginCheck(User user) throws Exception {
		try {
			// Get list of all users from the db.
			List<Registration> currentUsers = service.getAll();
			// If there are any in the list of current users with the same username and password (case sensitive), return true.
			for (Registration registration : currentUsers) {
				if (registration.getUsername().equals(user.getUsername()) && registration.getPassword().equals(user.getPassword())) {
					return true;
				}
			}
			// Else return false
			return false;
		}
		// Catch any exceptions and throw it.
		catch (Exception e) {
			throw e;
		}
	}
}
