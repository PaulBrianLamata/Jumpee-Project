package com.projectimmersion.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.projectimmersion.springboot.view.View;

@Entity
@Table(name="address")
public class Address {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 long id;
	 
	 @JsonView(View.Base.class)
	 @Column(name = "user_address", table = "address")
	 String userAddress;
	 
	 @JsonView(View.Base.class)
	 @Column(name = "contact_person", table = "address")
	 String contactPerson;
	 
	 @JsonView(View.Base.class)
	 @Column(name = "contact_number", table = "address")
	 String contactNumber;
	 
	 @ManyToOne
	 @JoinColumn(name = "user_id", referencedColumnName = "user_id")
	 private User user;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	 
	 


}
