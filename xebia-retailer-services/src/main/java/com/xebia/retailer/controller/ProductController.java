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

import com.xebia.retailer.domain.Product;
import com.xebia.retailer.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Product product2 = productService.save(product);
		return new ResponseEntity<>(product2, HttpStatus.CREATED);
	}

	@PutMapping("/update/{productId}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long productId) {
		Product product2 = productService.update(product, productId);
		return ResponseEntity.ok(product2);
	}

	@GetMapping("/getproduct/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
		Product product = productService.getProduct(productId);
		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
		productService.delete(productId);
		return ResponseEntity.noContent().build();
	}

}
