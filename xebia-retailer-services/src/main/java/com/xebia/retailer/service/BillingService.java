package com.xebia.retailer.service;

import java.util.Set;

import com.xebia.retailer.domain.BillingDetails;
import com.xebia.retailer.domain.Product;

public interface BillingService {

	BillingDetails calculateBill(Set<Product> products, String contactNo);

}
