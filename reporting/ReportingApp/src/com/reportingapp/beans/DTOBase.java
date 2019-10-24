package com.reportingapp.beans;

public class DTOBase {
	private int status;
	private String message;
	public DTOBase(int status, String message)
	{
		this.status = status;
		this.message = message;
	}
	public DTOBase()
	{
		this.status = 200;
		this.message = "OK";
	}
	public int getStatus()
	{
		return status;
	}
	public void setStatus(int status)
	{
		this.status = status;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
}