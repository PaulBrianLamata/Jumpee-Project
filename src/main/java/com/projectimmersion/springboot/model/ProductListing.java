package com.projectimmersion.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.projectimmersion.springboot.view.View;

@Entity
@Table(name="product_listing")
public class ProductListing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long product_id;
	
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
	
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;  
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
}
