package com.projectimmersion.springboot.model;

public class UserInfo {

	public static int userId;
	public static String email;
	public static int getUserId() {
		return userId;
	}
	public static void setUserId(int userId) {
		UserInfo.userId = userId;
	}
	public static String getEmail() {
		return email;
	}
	public static void setEmail(String email) {
		UserInfo.email = email;
	}
	
	
}
