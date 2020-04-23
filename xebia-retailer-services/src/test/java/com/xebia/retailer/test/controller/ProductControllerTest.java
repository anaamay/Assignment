package com.xebia.retailer.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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

import com.xebia.retailer.controller.ProductController;
import com.xebia.retailer.domain.Product;
import com.xebia.retailer.domain.ProductType;
import com.xebia.retailer.repository.ProductRepository;
import com.xebia.retailer.service.ProductService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ProductControllerTest {

	@InjectMocks
	ProductController productController;

	@SpyBean
	ProductService productService;

	@MockBean
	ProductRepository productRepository;

	@Test
	final void testSaveProduct() throws Exception {
		Product productObj = prepareProduct();
		when(productRepository.save(productObj)).thenReturn(productObj);
		ResponseEntity<Product> responseEntity = productController.saveProduct(productObj);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	final void testUpdateProduct() throws Exception {
		Long productId = 1L;
		Product productObj = prepareProduct();
		when(productRepository.save(productObj)).thenReturn(productObj);
		ResponseEntity<Product> responseEntity = productController.updateProduct(productObj, productId);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	final void testGetproduct() throws Exception {
		Long productId = 1L;
		Product productObj = prepareProduct();
		Optional<Product> optional = Optional.ofNullable(productObj);
		when(productRepository.findById(productId)).thenReturn(optional);
		ResponseEntity<Product> responseEntity = productController.getProduct(productId);
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	final void testDeleteProduct() throws Exception {
		Long productId = 1L;
		doNothing().when(productRepository).deleteById(productId);
		ResponseEntity<Void> responseEntity = productController.deleteProduct(productId);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
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
