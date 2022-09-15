package com.projectimmersion.springboot.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.projectimmersion.springboot.model.ProductListing;
import com.projectimmersion.springboot.model.UserInfo;
import com.projectimmersion.springboot.repository.ProductListingRepository;
import com.projectimmersion.springboot.service.ProductListingService;

@Service
public class ProductListingImpl implements ProductListingService {
	
	private ProductListingRepository productListingRepository;
	
	public ProductListingImpl(ProductListingRepository productListingRepository) {
		super(); 
		this.productListingRepository = productListingRepository;
	}
	@Override
	public List<ProductListing> getAllProduct() {
		
		// find all the data
		String userEmail = UserInfo.getEmail();
		if(userEmail == null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Login your account to access this request");
		}
		else {
			return productListingRepository.findAll();
		}
	}
	@Override
	public Page <ProductListing> getCategory (Pageable page) {
		String userEmail = UserInfo.getEmail();
		if(userEmail == null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Login your account to access this request");
		}
		else {
			return productListingRepository.findAll(page);
		}
        
    }
}
