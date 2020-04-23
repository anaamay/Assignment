package com.xebia.retailer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xebia.retailer.domain.BillingDetails;

@Repository
public interface BillingRepository extends JpaRepository<BillingDetails, Long> {

}
