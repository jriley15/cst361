package com.reportingapp.services.business;

import javax.ejb.Local;
import com.reportingapp.beans.Registration;
import com.reportingapp.beans.User;

@Local
public interface IAuthenticationService 
{
	public void registerUser(Registration user) throws Exception;
	public boolean loginCheck(User user) throws Exception;
}
