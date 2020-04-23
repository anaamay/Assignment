package com.xebia.retailer.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xebia.retailer.domain.Customer;
import com.xebia.retailer.repository.CustomerRepository;
import com.xebia.retailer.service.CustomertService;

@Service
public class CustomertServiceImpl implements CustomertService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(Long customerId) {
		Customer customer = null;
		Optional<Customer> optional = customerRepository.findById(customerId);
		if (optional.isPresent()) {
			customer = optional.get();
		}
		return customer;
	}

	@Override
	public Customer update(Customer customer, Long customerId) {
		Customer customer2 = null;
		Optional<Customer> optional = customerRepository.findById(customerId);
		if (optional.isPresent()) {
			customer.setId(customerId);
			customer2 = customerRepository.save(customer);
		}
		return customer2;
	}

	@Override
	public void delete(Long customerId) {
		Optional<Customer> optional = customerRepository.findById(customerId);
		if (optional.isPresent()) {
			customerRepository.deleteById(customerId);
		}
	}

}
