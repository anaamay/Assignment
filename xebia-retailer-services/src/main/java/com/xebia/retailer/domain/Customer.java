package com.xebia.retailer.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private String contactNo;
	private LocalDate customerFrom;
	private CustomerType customerType;
}
