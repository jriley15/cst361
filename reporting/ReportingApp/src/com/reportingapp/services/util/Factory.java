package com.reportingapp.services.util;

import com.reportingapp.beans.DTO;
import com.reportingapp.beans.DTOBase;

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
	 * @param errorCode type int
	 * @param message type String
	 * @param data T type Generic
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
}