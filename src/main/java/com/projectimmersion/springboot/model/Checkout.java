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
@Table(name="checkout")
public class Checkout {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long checkout_id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private ProductListing productListing;
	
	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "order_id")
	private OrderItem orderItem;
	
	@Column(name = "category_name")
	@JsonView(View.Base.class)
	private String categoryName;
	
	@Column(name = "product_name")
	@JsonView(View.Base.class)
	private String productName;
	
	@Column(name = "product_quantity")
	@JsonView(View.Base.class)
	private int productQuantity;
	
	
	@Column(name = "total_price")
	@JsonView(View.Base.class)
	private double totalPrice;
	
	@Column(name = "status")
	@JsonView(View.Base.class)
	private String status;
	
	
	public long getCheckout_id() {
		return checkout_id;
	}
	public void setCheckout_id(long checkout_id) {
		this.checkout_id = checkout_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ProductListing getProductListing() {
		return productListing;
	}
	public void setProductListing(ProductListing productListing) {
		this.productListing = productListing;
	}
	public OrderItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	

}
