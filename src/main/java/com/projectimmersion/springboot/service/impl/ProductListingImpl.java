package com.projectimmersion.springboot.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projectimmersion.springboot.model.ProductListing;
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
		return productListingRepository.findAll();
	}
	@Override
	public Page <ProductListing> getCategory (Pageable page) {
		return productListingRepository.findAll(page);
    }
}
