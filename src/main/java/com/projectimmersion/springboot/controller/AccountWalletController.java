package com.projectimmersion.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.projectimmersion.springboot.model.AccountWallet;
import com.projectimmersion.springboot.service.AccountWalletService;
import com.projectimmersion.springboot.view.View;

@RestController
@RequestMapping(value = "/api/accountwallet")
public class AccountWalletController {
	

	@Autowired
	private AccountWalletService accountWalletService;
	
	public AccountWalletController(AccountWalletService accountWalletService) {
		super();
		this.accountWalletService = accountWalletService;
		}
	
	@PostMapping()
	@JsonView(View.Base.class)
	public ResponseEntity<AccountWallet> saveUserBalance(@RequestBody AccountWallet accountWallet){	
	
		return new ResponseEntity<AccountWallet>(accountWalletService.saveUserBalance(accountWallet), HttpStatus.CREATED);
	}
	
	@PutMapping("update/{id}")
	@JsonView(View.Base.class)
	public ResponseEntity<AccountWallet> updateUserAddress(@PathVariable("id") long id
												  ,@RequestBody AccountWallet accountWallet){
		return new ResponseEntity<AccountWallet>(accountWalletService.updateUserBalance(accountWallet, id), HttpStatus.ACCEPTED);
	}

}
