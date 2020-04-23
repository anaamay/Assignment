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

import com.xebia.retailer.domain.Customer;
import com.xebia.retailer.service.CustomertService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomertService customertService;

	@PostMapping("/save")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
		Customer response = customertService.save(customer);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/update/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable Long customerId) {
		Customer response = customertService.update(customer, customerId);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/getcustomer/{customerId}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
		Customer response = customertService.getCustomer(customerId);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long customerId) {
		customertService.delete(customerId);
		return ResponseEntity.noContent().build();
	}
}
