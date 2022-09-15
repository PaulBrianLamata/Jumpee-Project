package com.projectimmersion.springboot.service;

import com.projectimmersion.springboot.model.AccountWallet;

public interface AccountWalletService {


	AccountWallet saveUserBalance(AccountWallet accountWallet);
	AccountWallet updateUserBalance(AccountWallet accountWallet, long id);

}
