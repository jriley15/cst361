package com.reportingapp.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.

/**
 * Bean for storing registration data. Extends the User bean.
 * @author Trevor
 *
 */
@ManagedBean
@ViewScoped
public class Registration extends User
{
	// Properties with validation
	@NotNull(message = "First name cannot be left blank. This is a required field.")
	@Size(min=2, max=30)
	private String firstName = "";
	
	@NotNull(message = "Last name cannot be left blank. This is a required field.")
	@Size(min=2, max=30)
	private String lastName = "";
	
	public Registration() 
	{
		
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}