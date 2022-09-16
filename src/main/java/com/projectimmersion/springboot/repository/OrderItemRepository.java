package com.projectimmersion.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectimmersion.springboot.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	OrderItem findByProductName(String productName);
	
}
