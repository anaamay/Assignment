package com.xebia.retailer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.retailer.domain.Cart;
import com.xebia.retailer.domain.Product;
import com.xebia.retailer.service.CartService;

@RestController
@RequestMapping("/carts")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/add/{cartId}")
	public ResponseEntity<Cart> addProduct(@RequestBody Product product, @PathVariable Long cartId){
		Cart cart = cartService.addProduct(product, cartId);
		return new ResponseEntity<>(cart, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{cartId}")
	public ResponseEntity<Cart> updateProduct(@RequestBody Product product, @PathVariable Long cartId){
		Cart cart = cartService.updateProduct(product, cartId);
		return ResponseEntity.ok(cart);
	}
	
	@GetMapping("/getcart/{cartId}")
	public ResponseEntity<Cart> getCart(@PathVariable Long cartId){
		Cart cart = cartService.getCart(cartId);
		return ResponseEntity.ok(cart);
	}
	
	@DeleteMapping("/delete/{cartId}")
	public ResponseEntity<Void> deleteCart(@PathVariable Long cartId){
		cartService.deleteCart(cartId);
		return ResponseEntity.noContent().build();
	}
	

}
