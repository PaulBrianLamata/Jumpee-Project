package com.projectimmersion.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.projectimmersion.springboot.model.Checkout;
import com.projectimmersion.springboot.service.CheckoutService;
import com.projectimmersion.springboot.view.View;

@RestController
@RequestMapping(value = "/api/checkout")
public class CheckoutController {
	

	@Autowired
	private CheckoutService checkoutService;
	
	public CheckoutController(CheckoutService checkoutService) {
		super();
		this.checkoutService = checkoutService;
		}
	
	@PostMapping()
	@JsonView(View.Base.class)
	public ResponseEntity<Checkout> checkoutItem(@RequestBody Checkout checkout){	
		
		return new ResponseEntity<Checkout>(checkoutService.addCheckout(checkout), HttpStatus.CREATED);
	}
}
