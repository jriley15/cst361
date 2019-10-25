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
 * Bean for storing User (login) data.
 * @author Trevor
 *
 */
@ManagedBean
@ViewScoped
public class User {
	// Properties with validation
	@NotNull(message = "Username cannot be left blank. This is a required field.")
	@Size(min=8, max=25)
	private String username = "";
	@NotNull(message = "Password cannot be left blank. This is a required field.")
	@Size(min=8, max=25)
	private String password = "";
	public User() {

	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
