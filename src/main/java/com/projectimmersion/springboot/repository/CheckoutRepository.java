package com.projectimmersion.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectimmersion.springboot.model.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

}
