package com.xebia.retailer.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.xebia.retailer.domain.BillingDetails;
import com.xebia.retailer.domain.Customer;
import com.xebia.retailer.domain.CustomerType;
import com.xebia.retailer.domain.Product;
import com.xebia.retailer.domain.ProductType;
import com.xebia.retailer.repository.CustomerRepository;
import com.xebia.retailer.service.BillingService;

@Transactional
@Service
public class BillingServiceImpl implements BillingService {

	@Autowired
	private CustomerRepository customerRepository;

	@Value("${employee.discount:30}")
	private int employeeDiscount;

	@Value("${affiliate.discount:10}")
	private int affiliateDiscount;

	@Value("${customer.over.2_years:5}")
	private int overTwoYearDiscount;

	@Value("${customer.eligible.discount:2}")
	private int eligibleForDiscount;

	@Override
	public BillingDetails calculateBill(Set<Product> products, String contactNo) {
		BillingDetails billingDetails = new BillingDetails();
		Double billAmount = 0.0;
		Double discountAmount = 0.0;
		if (!CollectionUtils.isEmpty(products)) {
			billAmount = products.stream().map(product -> product.getPrice())
					.collect(Collectors.summingDouble(Double::doubleValue));
		} else {
			throw new RuntimeException("Cart is empty.");
		}

		Optional<Customer> optional = customerRepository.findByContactNo(contactNo);
		if (optional.isPresent()) {
			Double discountedProductSum = products.stream().map(product -> {
				if (product.getProductType().equals(ProductType.OTHER))
					return product.getPrice();
				return null;
			}).collect(Collectors.summingDouble(Double::doubleValue));

			discountAmount = calculateDiscount(discountedProductSum, optional.get());
			billAmount = billAmount - discountAmount;
		} else {
			Customer customer = new Customer();
			customer.setContactNo(contactNo);
			customer.setCustomerFrom(LocalDate.now());
			customerRepository.save(customer);
		}
		billingDetails.setTotal(billAmount);
		billingDetails.setProducts(products);
		billingDetails.setDiscount(discountAmount);
		return billingDetails;
	}

	private Double calculateDiscount(Double discountedProductSum, Customer customer) {
		Double discount = 0.0;
		if (customer.getCustomerType().equals(CustomerType.EMPLOYEE)) {
			discount = ((100 - employeeDiscount) * discountedProductSum) / 100;
		} else if (customer.getCustomerType().equals(CustomerType.AFFILIATE)) {
			discount = ((100 - affiliateDiscount) * discountedProductSum) / 100;
		} else {
			Period period = Period.between(LocalDate.now(), customer.getCustomerFrom());
			Integer customerDuration = period.getYears();
			if (customerDuration >= eligibleForDiscount) {
				discount = ((100 - overTwoYearDiscount) * discountedProductSum) / 100;
			}
		}
		return discount;
	}

}
