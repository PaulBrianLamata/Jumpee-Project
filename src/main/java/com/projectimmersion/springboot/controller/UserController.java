package com.projectimmersion.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.projectimmersion.springboot.model.Message;
import com.projectimmersion.springboot.model.User;
import com.projectimmersion.springboot.model.UserInfo;
import com.projectimmersion.springboot.service.UserService;
import com.projectimmersion.springboot.view.View;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;


	public UserController(UserService userService) {
		super();
		this.userService = userService;
		}
	
	
		//build create employee REST API
		@PostMapping()
		@JsonView(View.Base.class)
		public ResponseEntity<User> saveUser(@RequestBody User user){
			
		
			return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
		}
		
		//get all the users
		@GetMapping
		@JsonView(View.Base.class)
		public List<User> getAllUsers(){
			return userService.getAllUsers();
		}
		
		// build get User by id REST API
		// http://localhost:8080/api/users/1
		@GetMapping("{user_id}")
		@JsonView(View.Base.class)
		public ResponseEntity<User> getUserById(@PathVariable("user_id") long user_id){
			return new ResponseEntity<User>(userService.getUserById(user_id), HttpStatus.OK);
		}
		
		// build delete User REST API
		// http://localhost:8080/api/users/1
		@DeleteMapping("{user_id}")
		public ResponseEntity<String> deleteUser(@PathVariable("user_id") long user_id){
			
			// delete User from DB
			userService.deleteUser(user_id);
			
			return new ResponseEntity<String>("User deleted successfully!.", HttpStatus.OK);
		}
		
		// build update user REST API
		// http://localhost:8080/api/users/1
		@PutMapping("{user_id}")
		@JsonView(View.Base.class)
		public ResponseEntity<User> updateUser(@PathVariable("user_id") long user_id
													  ,@RequestBody User user){
			return new ResponseEntity<User>(userService.updateUser(user, user_id), HttpStatus.OK);
		}
		
		//build login user REST API
		// build get User by id REST API
				@PostMapping("/login")

				public ResponseEntity<Message> login(@RequestBody User user){
				String isSuccess = userService.userEmailPassword(user.getEmail(), user.getPassword());
				
				
				if(isSuccess.equals("Success")) {
					UserInfo.setEmail(user.getEmail());//email user
					
					return ResponseEntity.ok().body(new Message("Message: Welcome User"));
						}else {
					return ResponseEntity.badRequest().body(new Message("Error: Wrong Username or password"));
				}
					
			}
		
	}
		
