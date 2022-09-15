package com.projectimmersion.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectimmersion.springboot.service.AccountWalletService;
import com.projectimmersion.springboot.service.OrderItemService;

@RestController
@RequestMapping(value = "/api/neworder")
public class OrderItemController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	public OrderItemController(OrderItemService orderItemService) {
		super();
		this.orderItemService = orderItemService;
		//itutuloy abangan..
		}
	

}
