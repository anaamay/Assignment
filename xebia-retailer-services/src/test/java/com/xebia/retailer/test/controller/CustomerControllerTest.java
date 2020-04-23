package com.xebia.retailer.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.xebia.retailer.controller.CustomerController;
import com.xebia.retailer.domain.Customer;
import com.xebia.retailer.domain.CustomerType;
import com.xebia.retailer.repository.CustomerRepository;
import com.xebia.retailer.service.CustomertService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CustomerControllerTest {

	@InjectMocks
	CustomerController customerController;

	@SpyBean
	CustomertService customertService;

	@MockBean
	CustomerRepository customerRepository;

	@Test
	final void testSaveCustomer() throws Exception {
		Customer customerCaseObj = prepareCustomer();
		when(customerRepository.save(customerCaseObj)).thenReturn(customerCaseObj);
		ResponseEntity<Customer> responseEntity = customerController.saveCustomer(customerCaseObj);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	final void testUpdateCustomer() throws Exception {
		Long customerId = 1L;
		Customer customerCaseObj = prepareCustomer();
		when(customerRepository.save(customerCaseObj)).thenReturn(customerCaseObj);
		ResponseEntity<Customer> responseEntity = customerController.updateCustomer(customerCaseObj, customerId);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	final void testGetCustomer() throws Exception {
		Long customerId = 1L;
		Customer customerCaseObj = prepareCustomer();
		Optional<Customer> optional = Optional.ofNullable(customerCaseObj);
		when(customerRepository.findById(customerId)).thenReturn(optional);
		ResponseEntity<Customer> responseEntity = customerController.getCustomer(customerId);
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	final void testDeleteProduct() throws Exception {
		Long customerId = 1L;
		doNothing().when(customerRepository).deleteById(customerId);
		ResponseEntity<Void> responseEntity = customerController.deleteProduct(customerId);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}

	private Customer prepareCustomer() {
		Customer customer = new Customer();
		customer.setContactNo("123445611");
		customer.setCustomerFrom(LocalDate.now());
		customer.setCustomerType(CustomerType.EMPLOYEE);
		customer.setFirstName("Admin");
		customer.setId(1L);
		customer.setLastName("Admin Last");
		return customer;
	}
}
