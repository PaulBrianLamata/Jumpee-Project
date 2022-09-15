package com.projectimmersion.springboot.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projectimmersion.springboot.model.AccountWallet;
import com.projectimmersion.springboot.model.User;
import com.projectimmersion.springboot.model.UserInfo;
import com.projectimmersion.springboot.repository.AccountWalletRepository;
import com.projectimmersion.springboot.repository.UserRepository;
import com.projectimmersion.springboot.service.AccountWalletService;
@Service
public class AccountWalletImpl implements AccountWalletService {
	

	private AccountWalletRepository accountWalletRepository;
	private UserRepository userRepository;
	
	

	
	public AccountWalletImpl(AccountWalletRepository accountWalletRepository, UserRepository userRepository) {
		super(); 
		this.accountWalletRepository = accountWalletRepository;
		this.userRepository = userRepository;
	}




	@Override
	public AccountWallet saveUserBalance(AccountWallet accountWallet) {
		String userEmail = UserInfo.getEmail();
		
		if(userEmail == null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Login your account to access this request");
		}
		else {
			System.out.println(userEmail);
			AccountWallet newAccountWallet = new AccountWallet();
			User existEmail = userRepository.findByEmail(userEmail);
			newAccountWallet.setUser(existEmail);
			newAccountWallet.setBalance(accountWallet.getBalance());
			return accountWalletRepository.save(newAccountWallet);
		}
		
	}
	

	@Override
	public AccountWallet updateUserBalance(AccountWallet accountWallet, long id) {
		String userEmail = UserInfo.getEmail();
		if(userEmail == null){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Login your account to access this request");
		}
		else {
		AccountWallet AccountWalletExist = accountWalletRepository.findById(id).orElseThrow();
		AccountWalletExist.setBalance(accountWallet.getBalance());
		return accountWalletRepository.save(AccountWalletExist);
		}
	}

}
