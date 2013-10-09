package com.app.spring.service.model;

public class UserData {

	private String userName;
	private String email;
	private String firstName;
	private String lastName;
	private String hashedPassword;
	
	public UserData(String pUserName, String pEmail, String pFirstName, String pLastName) {
		this.userName = pUserName;
		this.firstName = pFirstName;
		this.lastName = pLastName;
		this.email = pEmail;
   }

	public void setHashedPassword(String pHashedPassword) {
		this.hashedPassword = pHashedPassword;
	}
	
	public String getHashedPassword() {
		return this.hashedPassword;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
