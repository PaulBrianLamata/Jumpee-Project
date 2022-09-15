package com.projectimmersion.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projectimmersion.springboot.model.ProductListing;

public interface ProductListingService {
	
	List<ProductListing> getAllProduct();
	Page<ProductListing> getCategory(Pageable page);
	
	
}
