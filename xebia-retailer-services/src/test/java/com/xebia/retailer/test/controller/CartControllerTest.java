package com.xebia.retailer.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.xebia.retailer.controller.CartController;
import com.xebia.retailer.domain.Cart;
import com.xebia.retailer.domain.Product;
import com.xebia.retailer.domain.ProductType;
import com.xebia.retailer.repository.CartRepository;
import com.xebia.retailer.service.CartService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CartControllerTest {

	@InjectMocks
	CartController cartController;

	@SpyBean
	CartService cartService;

	@MockBean
	CartRepository cartRepository;

	@Test
	final void testAddProduct() throws Exception {
		Long cartId = 1L;
		Cart cartObject = prepareCart();
		Product product = prepareProduct();
		when(cartRepository.save(cartObject)).thenReturn(cartObject);
		ResponseEntity<Cart> responseEntity = cartController.addProduct(product, cartId);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	final void testUpdateProduct() throws Exception {
		Long cartId = 1L;
		Cart cart = prepareCart();
		Product product = prepareProduct();
		when(cartRepository.save(cart)).thenReturn(cart);
		ResponseEntity<Cart> responseEntity = cartController.updateProduct(product, cartId);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	final void testGetCart() throws Exception {
		Long cartId = 1L;
		Cart cart = prepareCart();
		Optional<Cart> optional = Optional.ofNullable(cart);
		when(cartRepository.findById(cartId)).thenReturn(optional);
		ResponseEntity<Cart> responseEntity = cartController.getCart(cartId);
		assertNotNull(responseEntity);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	final void testDeleteCart() throws Exception {
		Long cartId = 1L;
		doNothing().when(cartRepository).deleteById(cartId);
		ResponseEntity<Void> responseEntity = cartController.deleteCart(cartId);
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

	private Cart prepareCart() {
		Cart cart = new Cart();
		cart.setId(1L);
		cart.setNoOfItems(10);
		Set<Product> products = new HashSet<>();
		cart.setProducts(products);

		return cart;
	}
}
