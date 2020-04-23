package com.xebia.retailer.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Cart {

	@Id
	@GeneratedValue
	private Long id;
	private Integer noOfItems;
	@OneToMany
	@JoinColumn(name = "product_id")
	private Set<Product> products = new HashSet<>();
}