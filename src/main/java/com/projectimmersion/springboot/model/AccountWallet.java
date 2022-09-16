package com.projectimmersion.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.projectimmersion.springboot.view.View;

@Entity
@Table(name="account_wallet")
public class AccountWallet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_account_wallet_id;
	
	@Column(name = "balance")
	@JsonView(View.Base.class)
	private double balance;

	
	 @ManyToOne
	 @JoinColumn(name = "user_id", referencedColumnName = "user_id")
	 private User user;
	 
	public long getUser_account_wallet_id() {
		return user_account_wallet_id;
	}

	public void setUser_account_wallet_id(long user_account_wallet_id) {
		this.user_account_wallet_id = user_account_wallet_id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	


}
