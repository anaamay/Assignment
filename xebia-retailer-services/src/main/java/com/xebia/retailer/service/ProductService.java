package com.xebia.retailer.service;

import com.xebia.retailer.domain.Product;

public interface ProductService {

	Product save(Product product);

	Product update(Product product, Long productId);

	Product getProduct(Long productId);

	void delete(Long productId);

}
