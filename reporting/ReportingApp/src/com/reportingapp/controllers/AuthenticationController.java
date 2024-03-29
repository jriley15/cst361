package com.reportingapp.controllers;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import com.reportingapp.beans.Registration;
import com.reportingapp.beans.User;
import com.reportingapp.services.business.IAuthenticationService;
import com.reportingapp.services.util.LoggingInterceptor;
import com.reportingapp.services.util.LoggingService;
import com.reportingapp.services.util.LoggingService.LogLevel;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Controller for handling Login and Registration.
 * @author Trevor
 *
 */
@Named
@ViewScoped
@Interceptors(LoggingInterceptor.class)
public class AuthenticationController implements Serializable {
	private static final long serialVersionUID = -7444770772927830438L;
	// Inject our authentication service.
	@Inject
	IAuthenticationService authenticationService;
	// Inject our Logging Service.
	@Inject
	private LoggingService logger;
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
			if (authenticationService.loginCheck(user)) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
				FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
				return "LoginResponse.xhtml";
			}
			else {
				return "LoginFailed.xhtml";			
			}
		}
		// Catch any exceptions and print the stack trace. Log the message.
		catch (Exception e) {
			logger.log("AuthenticationController", "onLogin", LogLevel.SEVERE, e.getMessage());
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
		// Catch any exceptions and print the stack trace. Log the message.
		catch (Exception e) {
			logger.log("AuthenticationController", "onRegister", LogLevel.SEVERE, e.getMessage());
			e.printStackTrace();
		}
		// Return the generic error page if the registration wasn't successful.
		return "Error.xhtml";
	}
	/**
	 * Method for checking if a user is logged in.
	 * @throws IOException throws IOException
	 */
	public void check() throws IOException {
		try {
			// If user key isn't in the session, redirect to LoginForm view.
			if(!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("user"))
				FacesContext.getCurrentInstance().getExternalContext().redirect("LoginForm.xhtml");			
		}
		// Catch any exceptions and print the stack trace. Log the message.
		catch (Exception e) {
			logger.log("AuthenticationController", "check", LogLevel.SEVERE, e.getMessage());
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
		// Catch any exceptions and print the stack trace. Log the message.
		catch (Exception e) {
			logger.log("AuthenticationController", "removeFromSession", LogLevel.SEVERE, e.getMessage());
			e.printStackTrace();
		}
	}
}
