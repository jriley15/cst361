package com.reportingapp.services.data;

import javax.ejb.Local;
import com.reportingapp.beans.Registration;
import com.reportingapp.beans.User;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Interface (contract) for our Authentication DAO.
 * @author Trevor
 *
 */
@Local
public interface IAuthenticationDAO
{
	/**
	 * Method for registering a user in the database.
	 */
	public boolean loginCheck(User user) throws Exception;
	/**
	 * Method for checking if a user exists in the authentication table (logging in).
	 */
	public void registerUser(Registration user) throws Exception;
}
