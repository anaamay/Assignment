package com.xebia.retailer.service;

import com.xebia.retailer.domain.Cart;
import com.xebia.retailer.domain.Product;

public interface CartService {

	Cart addProduct(Product product, Long cartId);

	Cart updateProduct(Product product, Long cartId);

	Cart getCart(Long cartId);

	void deleteCart(Long cartId);

}
