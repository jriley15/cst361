package com.reportingapp.services.util;

import java.io.Serializable;

import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import com.reportingapp.services.util.LoggingService.LogLevel;

// Trevor Moore
// CST 361
// 11/20/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * LoggingInterceptor class for logging method entry and exits, as well as bean initialization.
 * @author Trevor
 *
 */
@Singleton
@DependsOn("LoggingService")
public class LoggingInterceptor implements Serializable {
	private static final long serialVersionUID = -5045797829393487242L;
	@Inject
	private LoggingService logger;
	/**
	 * Method for logging method entries and exits using @AroundInvoke.
	 * @param invocationContext type InvocationContext
	 * @return type Object
	 * @throws Exception 
	 */
	@AroundInvoke
	private Object methodLog(InvocationContext invocationContext) throws Exception {
		// Initialize object to null:
		Object object = null;
		// Try/Catch/Finally to ensure we always log entrace and exit of methods:
		try {
			// Log method enter:
			logger.log(invocationContext.getTarget().getClass().toString(), invocationContext.getMethod().getName(), LogLevel.INFO, "Enter method.");
			// Set object to the result of proceeding the context:
			object = invocationContext.proceed();
		}
		catch (Exception exception) {
			// Log exception and print the stack trace:
			logger.log(invocationContext.getTarget().getClass().toString(), invocationContext.getMethod().getName(), LogLevel.SEVERE, exception.getMessage());
			exception.printStackTrace();
			// Throw exception:
			throw exception;
		}
		finally {
			// Log method exit:
			logger.log(invocationContext.getTarget().getClass().toString(), invocationContext.getMethod().getName(), LogLevel.INFO, "Exit method.");
		}
		// Return the object:
		return object;
	}
}