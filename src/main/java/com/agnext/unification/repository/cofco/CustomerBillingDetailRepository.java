/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.CustomerBillingDetailEntity;
import com.agnext.unification.entity.cofco.CustomerEntity;

/**
 * The Interface CustomerBillingDetailRepository.
 */
@Repository("cofcoCustomerBillingDetailRepository")
public interface CustomerBillingDetailRepository extends JpaRepository<CustomerBillingDetailEntity, Long> {

	List<CustomerBillingDetailEntity> findByCustomerIn(CustomerEntity customerEntity);

	

}
