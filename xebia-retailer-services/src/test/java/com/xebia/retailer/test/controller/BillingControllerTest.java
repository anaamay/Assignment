package com.xebia.retailer.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.xebia.retailer.controller.BillingController;
import com.xebia.retailer.domain.BillingDetails;
import com.xebia.retailer.domain.Product;
import com.xebia.retailer.domain.ProductType;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BillingControllerTest {

	@InjectMocks
	BillingController billingController;

	@Test
	final void testCalculateBill() throws Exception {
		Product productObj = prepareProduct();
		Set<Product> products = new HashSet<>();
		products.add(productObj);
		String conatctNo = "1234567890";
		ResponseEntity<BillingDetails> responseEntity = billingController.calculateBill(products, conatctNo);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	private Product prepareProduct() {
		Product product = new Product();
		product.setDiscount(10L);
		product.setId(1L);
		product.setName("Dettol");
		product.setPrice(150D);
		product.setProductType(ProductType.GROCERIES);
		product.setSku("SKJ65567G");
		return product;
	}
}
