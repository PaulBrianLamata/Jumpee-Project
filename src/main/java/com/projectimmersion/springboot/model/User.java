package com.projectimmersion.springboot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;
import com.projectimmersion.springboot.view.View;


@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;
	
	@Column(unique = true)
	// email should be a valid email format
	// email should not be null or empty
	@NotEmpty
	@Email(message = "Email must be unique and formatted")
	@JsonView(View.Base.class)
	private String email;
	
	@Column(name = "contact_number")
	//contact number should not be null or empty
	@NotEmpty
	@Pattern(regexp = "^(09|\\+639)\\d{9}$", message = "mobile number invalid")
	@JsonView(View.Base.class)
	private String contactNumber;
	
	
	@JsonView(View.Base.class)
	@Column(name = "first_name")
	// first name should not be null or empty
	@NotEmpty
	private String firstName;
	
	
	@JsonView(View.Base.class)
	@Column(name = "last_name")
	// last name should not be null or empty
	@NotEmpty
	private String lastName;
	
	@Column(name = "password")
	// password should not be null of empty
	// password should have at least 8 characters
	@NotEmpty
	@Size(min = 8, message = "password should have at least 8 characters")
	@JsonView(View.Base.class)
	private String password;
	 
	@NotEmpty
	@Column(name = "confirm_password")
	@JsonView(View.Base.class)
	private String confirmPassword;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "reset_token", nullable = true)
	private String resetToken;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
	private List<Address> address;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
	private List<AccountWallet> accountWallet;
	
	

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	// Start of Getters and Setter
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<AccountWallet> getAccountWallet() {
		return accountWallet;
	}

	public void setAccountWallet(List<AccountWallet> accountWallet) {
		this.accountWallet = accountWallet;
	}

	// End of Getters and Setter

}