package com.reportingapp.services.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Singleton;

// Trevor Moore
// CST 361
// 11/21/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
* Logging Service as Singleton for printing log data to the console.
* To be used in our Logging Interceptor and everywhere else where needed.
* @author Trevor
*
*/
@Singleton
public class LoggingService {
	// Declare our dateFormat object for logging the current date:
	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
	// Declare our enum log levels:
    public enum LogLevel {INFO, WARNING, SEVERE};
    /**
     * Method for logging data to the console.
     * @param className type String
     * @param methodName type String
     * @param level type enum LogLevel
     * @param message type String
     */
	public void log(String className, String methodName, LogLevel level, String message) {
		// Declare string builder for building log string to be printed:
		StringBuilder log = new StringBuilder();
		// Append data, class name, method name, and message:
		log.append(dateFormat.format(new Date())).append(" - ");
		log.append("[").append(className).append(".");
		log.append(methodName).append("()] - ");
		log.append("[").append(level.toString()).append("] - ");
        log.append(message);
        // Print message to console.
        System.out.println(log.toString());
	}
}