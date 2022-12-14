package com.projectimmersion.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectimmersion.springboot.model.Address;


@Repository("addressRepository")
public interface AddressRepository extends JpaRepository<Address, Long> {

	
}
