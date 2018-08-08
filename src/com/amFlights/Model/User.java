package com.amFlights.Model;

public class User {

	String username;
	String email;
	UserType userType;
	
	
	public User(String username, String email, int userType) {
		super();
		this.username = username;
		this.email = email;
		this.userType = UserType.values()[userType];
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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