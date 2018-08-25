package com.amFlights.model;

public class User {

	String username;
	String email;
	UserType userType;
	int userId;
	
	
	public User(String username, String email, int userType,int userId) {
		super();
		this.username = username;
		this.email = email;
		this.userType = UserType.values()[userType];
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = UserType.values()[userType];
	}
}