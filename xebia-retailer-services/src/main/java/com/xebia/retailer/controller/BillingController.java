package com.xebia.retailer.controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.retailer.domain.BillingDetails;
import com.xebia.retailer.domain.Product;
import com.xebia.retailer.service.BillingService;

@RestController
@RequestMapping("/bills")
public class BillingController {
	
	//@Autowired
	private BillingService billingService;

	@GetMapping("/calculatebill/{contactNo}")
	public ResponseEntity<BillingDetails> calculateBill(@RequestBody Set<Product> products,
			@PathVariable String contactNo) {
		BillingDetails billingDetails = billingService.calculateBill(products, contactNo);
		return new ResponseEntity<>(billingDetails, HttpStatus.OK);
	}
}
