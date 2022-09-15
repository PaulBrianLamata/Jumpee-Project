package com.projectimmersion.springboot.service;

import com.projectimmersion.springboot.model.Address;



public interface AddressService {
	
	void deleteUserAddress(long id);
	Address updateUserAddress(Address address, long id);
	Address saveUserAddress(Address address);

}
