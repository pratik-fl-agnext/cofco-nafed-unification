/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.CustomerBillingDetailEntity;
import com.agnext.unification.entity.nafed.CustomerEntity;

/**
 * The Interface CustomerBillingDetailRepository.
 */
public interface CustomerBillingDetailRepository extends JpaRepository<CustomerBillingDetailEntity, Long> {

	List<CustomerBillingDetailEntity> findByCustomerIn(CustomerEntity customerEntity);

	

}
