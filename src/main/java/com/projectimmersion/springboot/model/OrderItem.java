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
@Table(name="order_item")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long order_id;
	
	@Column(name = "product_name")
	@JsonView(View.Base.class)
	private String productName;
	
	
	@Column(name = "category_name")
	@JsonView(View.Base.class)
	private String categoryName;
	
	@Column(name = "product_price")
	@JsonView(View.Base.class)
	private double productPrice;
	
	@Column(name ="product_quantity")
	@JsonView(View.Base.class)
	private int productQuantity;
	
	@Column(name ="status")
	@JsonView(View.Base.class)
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
	private User user;

	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
