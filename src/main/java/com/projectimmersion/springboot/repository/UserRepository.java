package com.projectimmersion.springboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectimmersion.springboot.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	//Query to get the email
	@Query(value = "select email from users where email =?1", nativeQuery = true)
	String getUserByEmail(@Param("email")String email);
	
	//Query to get the password
	@Query(value = "select password from users where email =?1", nativeQuery = true)
	String getUserByPassword(@Param("email")String email);
	
	//Query to get the reset_token
	@Query(value = "select reset_token from users where email =?1", nativeQuery = true)
	String getResetToken(@Param("email")String email);
		
	
	@Query(value = "select * from users where email =?1", nativeQuery = true)
	User getUserByEmailObj(@Param("email")String email);
	
	 User findByEmail(String email);
	
	
			

}
