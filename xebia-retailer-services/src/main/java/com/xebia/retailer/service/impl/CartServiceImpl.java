package com.xebia.retailer.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.xebia.retailer.domain.Cart;
import com.xebia.retailer.domain.Product;
import com.xebia.retailer.repository.CartRepository;
import com.xebia.retailer.service.CartService;

@Transactional
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Override
	public Cart addProduct(Product product, Long cartId) {
		Set<Product> products = new HashSet<>();
		Cart cart = null;
		if (cartId != null) {
			Optional<Cart> optional = cartRepository.findById(cartId);
			if (optional.isPresent()) {
				cart = optional.get();
				if (!CollectionUtils.isEmpty(cart.getProducts())) {
					products = cart.getProducts();
				}
			} else {
				cart = new Cart();
			}
		} else {
			cart = new Cart();
		}
		cart.setProducts(products);
		cart.setNoOfItems(products.size());
		return cartRepository.save(cart);
	}

	@Override
	public Cart updateProduct(Product product, Long cartId) {
		Cart cart = null;
		Optional<Cart> optional = cartRepository.findById(cartId);
		if (optional.isPresent()) {
			cart = optional.get();
		}
		return cart;
	}

	@Override
	public Cart getCart(Long cartId) {
		Cart cart = null;
		Optional<Cart> optional = cartRepository.findById(cartId);
		if (optional.isPresent()) {
			cart = optional.get();
		}
		return cart;
	}

	@Override
	public void deleteCart(Long cartId) {
		Optional<Cart> optional = cartRepository.findById(cartId);
		if (optional.isPresent()) {
			cartRepository.deleteById(cartId);
		}

	}
}
