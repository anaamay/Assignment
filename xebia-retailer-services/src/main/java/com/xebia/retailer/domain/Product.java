package com.xebia.retailer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Product {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String sku;
	private Double price;
	private Long discount;
	private ProductType productType;

}
