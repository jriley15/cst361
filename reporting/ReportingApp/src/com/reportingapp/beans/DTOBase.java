package com.reportingapp.beans;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
* DTO Base class to be derived for all DTO types.
* @author Trevor
*
*/
public class DTOBase {
	private int status;
	private String message;
	public DTOBase(int status, String message) {
		this.status = status;
		this.message = message;
	}
	public DTOBase() {
		this.status = 200;
		this.message = "OK";
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}