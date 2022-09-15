package com.projectimmersion.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.projectimmersion.springboot.model.Address;
import com.projectimmersion.springboot.service.AddressService;
import com.projectimmersion.springboot.view.View;

@RestController
@RequestMapping(value = "/api/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	public AddressController(AddressService addressService) {
		super();
		this.addressService = addressService;
		}
	
	@PostMapping()
	@JsonView(View.Base.class)
	public ResponseEntity<Address> saveUserAddress(@RequestBody Address address){	
		return new ResponseEntity<Address>(addressService.saveUserAddress(address), HttpStatus.CREATED);
	}
	
	// build delete User REST API
	// http://localhost:8080/api/users/1
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteUserAddress(@PathVariable("id") long id){
		// delete User from DB
		addressService.deleteUserAddress(id);
		return new ResponseEntity<String>("Address deleted successfully!.", HttpStatus.OK);
	}
	
	@PutMapping("update/{id}")
	@JsonView(View.Base.class)
	public ResponseEntity<Address> updateUserAddress(@PathVariable("id") long id
												  ,@RequestBody Address address){
		return new ResponseEntity<Address>(addressService.updateUserAddress(address, id), HttpStatus.OK);
	}
}
