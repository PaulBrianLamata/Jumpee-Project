package com.projectimmersion.springboot.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projectimmersion.springboot.model.AccountWallet;
import com.projectimmersion.springboot.model.Checkout;
import com.projectimmersion.springboot.model.OrderItem;
import com.projectimmersion.springboot.model.ProductListing;
import com.projectimmersion.springboot.model.User;
import com.projectimmersion.springboot.model.UserInfo;
import com.projectimmersion.springboot.repository.AccountWalletRepository;
import com.projectimmersion.springboot.repository.CheckoutRepository;
import com.projectimmersion.springboot.repository.OrderItemRepository;
import com.projectimmersion.springboot.repository.ProductListingRepository;
import com.projectimmersion.springboot.repository.UserRepository;
import com.projectimmersion.springboot.service.CheckoutService;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	
	private CheckoutRepository checkoutRepository;
	private OrderItemRepository orderItemRepository;
	private UserRepository userRepository;
	private ProductListingRepository productListingRepository;
	private AccountWalletRepository accountWalletRepository;

	
	public CheckoutServiceImpl(CheckoutRepository checkoutRepository, OrderItemRepository orderItemRepository,
			UserRepository userRepository, ProductListingRepository productListingRepository,
			AccountWalletRepository accountWalletRepository) {
		super();
		this.checkoutRepository = checkoutRepository;
		this.orderItemRepository = orderItemRepository;
		this.userRepository = userRepository;
		this.productListingRepository = productListingRepository;
		this.accountWalletRepository = accountWalletRepository;
	}

	@Override
	public Checkout addCheckout(Checkout checkout) {
		String userEmail = UserInfo.getEmail();
		if (userEmail ==  null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Login your account to access this request");
		}
		else {
			Checkout newCheckout = new Checkout();
			User existEmail = userRepository.findByEmail(userEmail);
			long getid = existEmail.getUser_id();
			AccountWallet existAccountWallet = accountWalletRepository.getById(getid);
			ProductListing existProductInfo = productListingRepository.findByProductName(checkout.getProductName());
			OrderItem existOrderItemInfo = orderItemRepository.findByProductName(checkout.getProductName());
			Double newBalance = newBalance(existAccountWallet.getBalance(), existOrderItemInfo.getTotalPrice());
			existAccountWallet.setBalance(newBalance);
			newCheckout.setUser(existEmail);
			newCheckout.setOrderItem(existOrderItemInfo);
			newCheckout.setProductListing(existProductInfo);
			newCheckout.setCategoryName(existOrderItemInfo.getCategoryName());
			newCheckout.setProductName(checkout.getProductName());
			newCheckout.setProductQuantity(existOrderItemInfo.getProductQuantity());
			newCheckout.setTotalPrice(existOrderItemInfo.getTotalPrice());
			newCheckout.setStatus("PAID");
			existOrderItemInfo.setStatus("PAID");
			return checkoutRepository.save(newCheckout);
		}
		
	}
	
	public double newBalance(double balance,double totalPrice) {
		return balance - totalPrice;
		
	}
}
