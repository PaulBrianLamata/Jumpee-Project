package com.projectimmersion.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectimmersion.springboot.model.User;
import com.projectimmersion.springboot.service.UserService;

@RestController
@RequestMapping("/resetpassword")
public class PasswordResetController {

	
	@Autowired
	private UserService userService;
	
	
	public PasswordResetController(UserService userService) {
		super();
		this.userService = userService;
		}
	
	// build get User by id REST API
	// http://localhost:8080/api/employees/1
	@PostMapping()
	public ResponseEntity<String> save(@RequestBody User user){
	String findUser = userService.getUserEmail(user.getEmail());
	return new ResponseEntity<String>("Passcode : " + findUser, HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("reset/{user_id}")
	public ResponseEntity<User> updateResetToken(@PathVariable("user_id") long user_id
			  ,@RequestBody User user){
return new ResponseEntity<User>(userService.updateUserResetToken(user, user_id), HttpStatus.ACCEPTED);

	}
	

	@PutMapping("reset/{reset_token}/{email}")
	public ResponseEntity<User> updateResetToken(@PathVariable("reset_token") String reset_token,
												 @PathVariable("email") String email
												 ,@RequestBody User user){
//		userService.updateUserResetPassword(user,email,reset_token);
		return new ResponseEntity<User>(userService.updateUserResetPassword(user,email,reset_token), HttpStatus.ACCEPTED);
	}
	
}

