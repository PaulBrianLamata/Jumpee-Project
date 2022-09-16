package com.projectimmersion.springboot.model;

public class UserInfo {

	public static Long userId;
	public static String email;
	public static Long getUserId() {
		return userId;
	}
	public static void setUserId(Long userId) {
		UserInfo.userId = userId;
	}
	public static String getEmail() {
		return email;
	}
	public static void setEmail(String email) {
		UserInfo.email = email;
	}
	
	
}
