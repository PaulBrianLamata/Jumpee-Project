package com.projectimmersion.springboot.service.impl;



import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projectimmersion.springboot.model.User;
import com.projectimmersion.springboot.model.UserInfo;
import com.projectimmersion.springboot.repository.UserRepository;
import com.projectimmersion.springboot.service.UserService;



@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	
	public UserServiceImpl(UserRepository userRepository) {
		super(); 
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public User saveUser(User user) {
		UserInfo.setUserId(user.getUser_id());// id user
		System.out.print(user.getUser_id());
	
		//Password Confirmation
		String pattern= "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,16}$";
		String matcher= user.getPassword();
		boolean matchFound = matcher.matches(pattern);
		
		
	if(!matchFound) {
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Must Contain Alphanumeric");
	}
	else {
			if (user.getPassword().equals(user.getConfirmPassword())) {
			//For Encrypting password
			String encodedPassword = this.passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			String encodedConfirmPassword = this.passwordEncoder.encode(user.getConfirmPassword());
			user.setConfirmPassword(encodedConfirmPassword);
			// End For Encrypting password
			}
			else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords don't match");
				}
	}
		//save data from restApi
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		// find all the data
		String userEmail = UserInfo.getEmail();
		if(userEmail == null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Login your account to access this request");
		}
		else {
			return userRepository.findAll();
		}
	}
	
	@Override
	public void deleteUser(long user_id) {

		String userEmail = UserInfo.getEmail();
		if(userEmail == null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Login your account to access this request");
		}
		else {
			// check whether a user exist in a DB or not
			userRepository.findById(user_id).orElseThrow();
			userRepository.deleteById(user_id);
		}
	}

	@Override
	public User getUserById(long user_id) {
	// find all the data
			String userEmail = UserInfo.getEmail();
				if(userEmail == null){
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Login your account to access this request");
				}
				else {
					return userRepository.findById(user_id).orElseThrow();
				}
		
	}
	
	@Override
	public User updateUser(User user, long user_id) {
		
		String userEmail = UserInfo.getEmail();
		if(userEmail == null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Login your account to access this request");
		}
		else {
			// we need to check whether user with given id is exist in DB or not
			User existingUser = userRepository.findById(user_id).orElseThrow(); 
			existingUser.setEmail(user.getEmail());
			existingUser.setContactNumber(user.getContactNumber());
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setStatus(user.getStatus());
			String pattern= "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,16}$";
			String matcher= user.getPassword();
			boolean matchFound = matcher.matches(pattern);
			if(!matchFound) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Must Contain Alphanumeric");
			}else {
				String encodedPassword = this.passwordEncoder.encode(user.getPassword());
				existingUser.setPassword(encodedPassword);
			}
			// save existing USer to DB
			userRepository.save(existingUser);
			return existingUser;
		}
	}

	@Override
	public String userEmailPassword(String email, String password) {
		
		String passInDb = userRepository.getUserByPassword(email);
		boolean decodedPass = passwordEncoder.matches(password,passInDb);
		String mesg = null;
		
		if(passInDb != null) {
			if(decodedPass == true) {
				mesg = "Success";
			}else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your Password is Empty or Incorrect");
			}
		}
		else{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your Email is Empty or Incorrect");
		}
		return mesg;
	}

	@Override
	public String getUserEmail(String email) {
		// TODO Auto-generated method stub
		User getUserByEmail = userRepository.findByEmail(email);
		String userEmail = getUserByEmail.getEmail();
	
		long getid = getUserByEmail.getUser_id();
		System.out.println("Id: " + getid);
		
		Random rnd = new Random();
		int code = rnd.nextInt(999999);
		String codeConverted = String.valueOf(code);
		
		if(userEmail == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your Email is Empty or Incorrect");
		}
		else {
			 System.out.println("Email: " + userEmail);
			 System.out.print("code : " + codeConverted);
		}
		return codeConverted;
	}
	
	
	@Override
	public User updateUserResetToken(User user, long user_id) {
		// we need to check whether user with given id is exist in DB or not
		User existingUser = userRepository.findById(user_id).orElseThrow();  
		existingUser.setResetToken(user.getResetToken());
		// save existing User to DB
		userRepository.save(existingUser);
		return existingUser;
	}

	@Override
	public User updateUserResetPassword(User user, String email, String token) {	
		User u = userRepository.getUserByEmailObj(email);
		String userTokenInDB = this.userToken(email);
		String userToker = token;
		if(!userToker.equals(userTokenInDB)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Code");
		}
		else {
			String pattern= "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,16}$";
			String matcher= user.getPassword();
			boolean matchFound = matcher.matches(pattern);
			
			if(!matchFound) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password Must Contain Alphanumeric");
			}
			else {
				
				if(user.getPassword().equals(user.getConfirmPassword())) {
					String encodedPassword = this.passwordEncoder.encode(user.getPassword());
					u.setPassword(encodedPassword);
					String encodedConfirmPassword = this.passwordEncoder.encode(user.getConfirmPassword());
					u.setConfirmPassword(encodedConfirmPassword);
					System.out.println("Password User::" + user.getPassword());
					System.out.println("Confirm Password User::" + user.getConfirmPassword());
				}
				else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords don't match");
					}

			}
			  return userRepository.save(u);
		}
	}
	
	public String userToken(String email) {
		return userRepository.getResetToken(email);
	}
}
