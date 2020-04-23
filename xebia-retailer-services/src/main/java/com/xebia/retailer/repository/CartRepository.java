package com.xebia.retailer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xebia.retailer.domain.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
