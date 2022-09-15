package com.projectimmersion.springboot.service.impl;

import org.springframework.stereotype.Service;

import com.projectimmersion.springboot.model.OrderItem;
import com.projectimmersion.springboot.model.ProductListing;
import com.projectimmersion.springboot.model.User;
import com.projectimmersion.springboot.model.UserInfo;
import com.projectimmersion.springboot.repository.OrderItemRepository;
import com.projectimmersion.springboot.repository.ProductListingRepository;
import com.projectimmersion.springboot.repository.UserRepository;
import com.projectimmersion.springboot.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	
	private OrderItemRepository orderItemRepository;
	private UserRepository userRepository;
	private ProductListingRepository productListingRepository;
	
	public OrderItemServiceImpl(OrderItemRepository orderItemRepository, UserRepository userRepository,
			ProductListingRepository productListingRepository) {
		super(); 
		this.orderItemRepository = orderItemRepository;
		this.userRepository = userRepository;
		this.productListingRepository = productListingRepository;
	}
	

	@Override
	public OrderItem addOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		String userEmail = UserInfo.getEmail();
		
		OrderItem newOrderItem = new OrderItem();
		User existEmail = userRepository.findByEmail(userEmail);
		ProductListing existProductInfo = productListingRepository.findByProductName(orderItem.getProductName());
		newOrderItem.setUser(existEmail);
		newOrderItem.setCategoryName(existProductInfo.getCategoryName());
		newOrderItem.setProductPrice(existProductInfo.getProductPrice());
		newOrderItem.setProductQuantity(orderItem.getProductQuantity());
		newOrderItem.setProductName(orderItem.getProductName());
		newOrderItem.setStatus("PENDING");
		
		return orderItemRepository.save(newOrderItem);
	}
	
	
	
	
	
	
}
