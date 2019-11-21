package com.reportingapp.services.util;

import com.reportingapp.beans.Currency;
import com.reportingapp.beans.DTO;
import com.reportingapp.beans.DTOBase;
import com.reportingapp.beans.Registration;
import com.reportingapp.beans.User;

// Trevor Moore
// CST 361
// 11/21/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
* Factory for creating all POJO object types.
* @author Trevor
*
*/
public class Factory {
	// Declare our enums for DTOs, Currencies, and Inputs:
	public static enum DTOType {DTOBASE, DTO};
	public static enum CurrencyType {CURRENCY};
	public static enum InputType {USER, REGISTRATION};
	/**
	 * Method for getting DTO objects.
	 * @param <T> type Generic
	 * @param type enum DTOType
	 * @return type DTOBase
	 */
	public static <T> DTOBase getDTO(DTOType type, int errorCode, String message, T data) {
		// Switch on the type:
		switch (type) {
			// Return DTO on type DTO
			case DTOBASE:
				return new DTOBase(errorCode, message);
			// Return DTO on type DTO
			case DTO:
				return new DTO<T>(errorCode, message, data);
			// Default return DTOBase
			default:
				return new DTOBase(errorCode, message);
		}
	}
	/**
	 * Method for getting Currency objects.
	 * @param type enum CurrencyType
	 * @return type Currency
	 */
	public static Currency getCurrency(CurrencyType type) {
		// Switch on the type:
		switch (type) {
			// Return Currency on type Currency
			case CURRENCY:
				return new Currency();
			// Default return Currency
			default:
				return new Currency();
		}
	}
	/**
	 * Method for getting input types (for forms, etc.).
	 * @param type enum InputType
	 * @return type User
	 */
	public static User getInput(InputType type) {
		// Switch on the type:
		switch (type) {
			// Return User on type User
			case USER:
				return new User();
			// Return Registration on type Registration
			case REGISTRATION:
				return new Registration();
			// Default return User
			default:
				return new User();
		}
	}
}