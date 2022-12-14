package com.projectimmersion.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.projectimmersion.springboot.model.OrderItem;
import com.projectimmersion.springboot.service.OrderItemService;
import com.projectimmersion.springboot.view.View;

@RestController
@RequestMapping(value = "/api/neworder")
public class OrderItemController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	public OrderItemController(OrderItemService orderItemService) {
		super();
		this.orderItemService = orderItemService;
		}
	
	@PostMapping()
	@JsonView(View.Base.class)
	public ResponseEntity<OrderItem> addOrderItem(@RequestBody OrderItem orderItem){	
		
		return new ResponseEntity<OrderItem>(orderItemService.addOrderItem(orderItem), HttpStatus.CREATED);
	}
	
	

}
