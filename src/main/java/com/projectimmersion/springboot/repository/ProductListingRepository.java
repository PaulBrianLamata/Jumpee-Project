package com.projectimmersion.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectimmersion.springboot.model.ProductListing;

public interface ProductListingRepository extends JpaRepository<ProductListing, Long> {

}
