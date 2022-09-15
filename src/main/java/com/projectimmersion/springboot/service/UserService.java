package com.projectimmersion.springboot.service;

import java.util.List;

import com.projectimmersion.springboot.model.User;

public interface UserService {
	
	 	User saveUser(User user);
		List<User> getAllUsers();
		User getUserById(long user_id);
		User updateUser(User user, long user_id);
		void deleteUser(long user_id);
		
		//Method for login user
		String userEmailPassword(String email, String password);
		
		//Method to get the code 
		String getUserEmail(String email);
	
		
		//Method to update the reset token in specific user.
		User updateUserResetToken(User user, long user_id);
		
		//Method to Reset Password of the user
		User updateUserResetPassword(User user, String email, String token);
		
		
		
		
		
		
	

}
 