package com.projectimmersion.springboot.service.impl;



import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projectimmersion.springboot.model.Address;
import com.projectimmersion.springboot.model.User;
import com.projectimmersion.springboot.model.UserInfo;
import com.projectimmersion.springboot.repository.AddressRepository;
import com.projectimmersion.springboot.repository.UserRepository;
import com.projectimmersion.springboot.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	private AddressRepository addressRepository;
	private UserRepository userRepository;
	
	
	public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
		super(); 
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
	}
	
	
	@Override
	public void deleteUserAddress(long id) {
		addressRepository.findById(id).orElseThrow();
		addressRepository.deleteById(id);
	}


	@Override
	public Address updateUserAddress(Address address, long id) {
		String userEmail = UserInfo.getEmail();
		if(userEmail == null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Login your account to access this request");
		}
		else {
			Address addressExist = addressRepository.findById(id).orElseThrow();
			addressExist.setUserAddress(address.getUserAddress());
			addressExist.setContactPerson(address.getContactPerson());
			addressExist.setContactNumber(address.getContactNumber());
			addressRepository.save(addressExist);
			return addressExist;
		}
	}
	@Override
	public Address saveUserAddress(Address address) {
		String userEmail = UserInfo.getEmail();
		if(userEmail == null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Login your account to access this request");
		}
		else
		{
			Address newAddr = new Address();
			User existEmail = userRepository.findByEmail(userEmail);
			newAddr.setUser(existEmail);
			newAddr.setUserAddress(address.getUserAddress());
			newAddr.setContactPerson(address.getContactPerson());
			newAddr.setContactNumber(address.getContactNumber());
			return addressRepository.save(newAddr);
		}
	}
}
