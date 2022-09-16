package com.projectimmersion.springboot.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
		if (userEmail ==  null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Login your account to access this request");
		}
		else {
			OrderItem newOrderItem = new OrderItem();
			User existEmail = userRepository.findByEmail(userEmail);
			ProductListing existProductInfo = productListingRepository.findByProductName(orderItem.getProductName());
			Double getPriceDouble = existProductInfo.getProductPrice();
			int getProductQuantity = orderItem.getProductQuantity();
			int existProductQuantity = existProductInfo.getProductQuantity();
			
			
			newOrderItem.setUser(existEmail);
			
			newOrderItem.setCategoryName(existProductInfo.getCategoryName());
			newOrderItem.setProductQuantity(orderItem.getProductQuantity());
			newOrderItem.setProductName(orderItem.getProductName());
			newOrderItem.setStatus("PENDING");
			
			
			//Total Price and Update the Quantity in Product Listing
			
			//Call Method to Update Quantity in Product list table
			int newQuantity = newQuantity(existProductQuantity, getProductQuantity);
			existProductInfo.setProductQuantity(newQuantity);
			
			//Call Method for Total Price
			Double newPrice = totalPrice(getPriceDouble, getProductQuantity);
			newOrderItem.setTotalPrice(newPrice);
			// End of Total Price and Update the Quantity in Product Listing
			return orderItemRepository.save(newOrderItem);
		}
		
		
	}
	
	
			public double totalPrice(double price,int quantity) {
				return price * quantity;
				
			}
			
			public int newQuantity(int quantity, int newQuantity) {
				return quantity - newQuantity;
				
			}
	
	
	
	
}
