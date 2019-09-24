package com.reportingapp.services.data;

import javax.ejb.Local;
import com.reportingapp.beans.Registration;
import com.reportingapp.beans.User;

@Local
public interface IAuthenticationDAO
{
	public boolean loginCheck(User user) throws Exception;
	public void registerUser(Registration user) throws Exception;
}
