package com.projectimmersion.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectimmersion.springboot.model.ProductListing;
import com.projectimmersion.springboot.service.ProductListingService;

@RestController
@RequestMapping(value = "/api/productlist")
public class ProductListingController {
	
	@Autowired
	private ProductListingService productListingService;
	
	public ProductListingController(ProductListingService productListingService) {
		super();
		this.productListingService = productListingService;
		}

	//get all the users
	@GetMapping
	public List<ProductListing> getAllProduct(){
			return productListingService.getAllProduct();
		}
	@GetMapping("/category")
	public Page <ProductListing> getProds 
		(@PageableDefault(sort="categoryName", direction = Direction.ASC,size = 9) Pageable page){           
				return productListingService.getCategory(page);        
		}
}
