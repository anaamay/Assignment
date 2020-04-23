package com.xebia.retailer.service;

import com.xebia.retailer.domain.Customer;

public interface CustomertService {

	void delete(Long customerId);

	Customer getCustomer(Long customerId);

	Customer update(Customer customer, Long customerId);

	Customer save(Customer customer);

}
