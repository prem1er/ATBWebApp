package com.app.spring.service.model;


public class UserData {

	private String userName;
	private int userId;
	private String email;
	private String firstName;
	private String lastName;
	private String hashedPassword;
	private String hashedActivationId;
	private boolean isUserEnabled;
	
	public UserData(String pUserName, String pEmail, String pFirstName, String pLastName) {
		this.userName = pUserName;
		this.firstName = pFirstName;
		this.lastName = pLastName;
		this.email = pEmail;
	}
	
	public UserData(String pActivationId, int pUserId, boolean pIsUserEnabled) {
		this.hashedActivationId = pActivationId;
		this.userId = pUserId;
		this.isUserEnabled = pIsUserEnabled;
	}
	
	public boolean isUserEnabled() {
		return isUserEnabled;
	}

	public void setUserEnabled(boolean pIsUserEnabled) {
		this.isUserEnabled = pIsUserEnabled;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int pUserId) {
		this.userId = pUserId;
	}
	
	public String getHashedActivationId() {
		return hashedActivationId;
	}

	public void setHashedActivationId(String pHashedActivationId) {
		this.hashedActivationId = pHashedActivationId;
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
