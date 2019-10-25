package com.reportingapp.controllers;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import com.reportingapp.beans.Registration;
import com.reportingapp.beans.User;
import com.reportingapp.services.business.IAuthenticationService;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Controller for handling Login and Registration.
 * @author Trevor
 *
 */
@ManagedBean
@ViewScoped
public class AuthenticationController {
	// Inject our authentication service.
	@Inject
	IAuthenticationService authenticationService;
	/**
	 * Method for handling logins.
	 * @return String (view)
	 */
	public String onLogin() {
		try {
			// Getting the User managed bean.
			FacesContext contxt = FacesContext.getCurrentInstance();
			User user = contxt.getApplication().evaluateExpressionGet(contxt, "#{user}", User.class);
			
			// If user's Username and Password are in the database, return LoginResponse, else LoginFailed.
			if(authenticationService.loginCheck(user)) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
				return "LoginResponse.xhtml";
			}
			else {
				return "LoginFailed.xhtml";			
			}
		}
		// Catch any exceptions and print the stack trace.
		catch (Exception e) {
			e.printStackTrace();
		}
		// Return the generic error page if the login didn't fail and it also wasn't successful.
		return "Error.xhtml";
	}
	/**
	 * Method for handling registration.
	 * @return String (view)
	 */
	public String onRegister() {
		try {
			// Getting the register managed bean.
			FacesContext contxt = FacesContext.getCurrentInstance();
			Registration registration = contxt.getApplication().evaluateExpressionGet(contxt, "#{registration}", Registration.class);
			
			// Calling method to register user into Authentication table in database by passing the Register bean.
			if(authenticationService.registerUser(registration)) {
				// Forward to LoginFrom view along with the Register managed bean if registration is successful.
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", registration);
				return "LoginForm.xhtml";
			}
			else {
				return "RegisterFailed.xhtml";
			}
		}
		// Catch any exceptions and print the stack trace.
		catch (Exception e) {
			e.printStackTrace();
		}
		// Return the generic error page if the registration wasn't successful.
		return "Error.xhtml";
	}
	/**
	 * Method for checking if a user is logged in.
	 * @throws IOException
	 */
	public void check() throws IOException {
		try {
			// If user key isn't in the session, redirect to LoginForm view.
			if(!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("user"))
				FacesContext.getCurrentInstance().getExternalContext().redirect("LoginForm.xhtml");			
		}
		// Catch any exceptions and print the stack trace.
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method for removing the user from the session.
	 */
	public void removeFromSession() {
		try {
			// Remove the user from the session map.
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("user");
		}
		// Catch any exceptions and print the stack trace.
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
