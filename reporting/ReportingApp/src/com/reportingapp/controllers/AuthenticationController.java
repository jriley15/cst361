package com.reportingapp.controllers;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import com.reportingapp.beans.Registration;
import com.reportingapp.beans.User;
import com.reportingapp.services.business.IAuthenticationService;

@ManagedBean
@ViewScoped
public class AuthenticationController 
{
	@Inject
	IAuthenticationService authenticationService;
	
	public String onLogin()
	{
		try 
		{
			// Getting the User managed bean
			FacesContext contxt = FacesContext.getCurrentInstance();
			User user = contxt.getApplication().evaluateExpressionGet(contxt, "#{user}", User.class);
			
			// If user's Username and Password are in the database, return LoginResponse, else LoginFailed.
			if(authenticationService.loginCheck(user) == true)
			{
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
				
				return "LoginResponse.xhtml";
			}
			else
			{
				return "LoginFailed.xhtml";			
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "Error.xhtml";
	}
	
	public String onRegister()
	{
		try 
		{
			// Getting the register managed bean
			FacesContext contxt = FacesContext.getCurrentInstance();
			Registration registration = contxt.getApplication().evaluateExpressionGet(contxt, "#{registration}", Registration.class);
			
			// Calling method to register user into Authentication table in database by passing the Register bean.
			authenticationService.registerUser(registration);
			
			// Forward to LoginFrom view along with the Register managed bean
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", registration);
			return "LoginForm.xhtml";
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "Error.xhtml";
	}
	
	public void check() throws IOException 
	{
		try 
		{
			// If user key isn't in the session, redirect to LoginForm view.
			if(!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("user"))
			{
				FacesContext.getCurrentInstance().getExternalContext().redirect("LoginForm.xhtml");			
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void removeFromSession()
	{
		try 
		{
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
