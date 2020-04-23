package com.xebia.retailer.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xebia.retailer.domain.Product;
import com.xebia.retailer.repository.ProductRepository;
import com.xebia.retailer.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product, Long productId) {
		Product product2 = null;
		Optional<Product> optional = productRepository.findById(productId);
		if (optional.isPresent()) {
			product2 = optional.get();
			product.setId(productId);
			product2 = productRepository.save(product);
		}
		return product2;
	}

	@Override
	public Product getProduct(Long productId) {
		Product product = null;
		Optional<Product> optional = productRepository.findById(productId);
		if (optional.isPresent()) {
			product = optional.get();
		}
		return product;
	}

	@Override
	public void delete(Long productId) {
		Optional<Product> optional = productRepository.findById(productId);
		if (optional.isPresent()) {
			productRepository.deleteById(productId);
		}
	}

}
