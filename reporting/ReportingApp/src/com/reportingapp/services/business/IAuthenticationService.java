package com.reportingapp.services.business;

import javax.ejb.Local;
import com.reportingapp.beans.Registration;
import com.reportingapp.beans.User;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Interface (contract) for our Authentication Service.
 * @author Trevor
 *
 */
@Local
public interface IAuthenticationService {
	/**
	 * Method for inserting user into the database by using the DataConnection class's methods.
	 * @param user type Register
	 * @return boolean
	 * @throws Exception throws Exception
	 */
	public boolean registerUser(Registration user) throws Exception;
	/**
	 * Method for checking if user's username and password is in database by using the DataConnection class's methods.
	 * @param user type Register
	 * @return boolean
	 * @throws Exception throws Exception
	 */
	public boolean loginCheck(User user) throws Exception;
}