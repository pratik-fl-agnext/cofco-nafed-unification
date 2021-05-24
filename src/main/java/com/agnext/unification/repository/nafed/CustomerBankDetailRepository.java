/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.CustomerBankDetailEntity;
import com.agnext.unification.entity.nafed.CustomerEntity;

/**
 * The Interface CustomerBankDetailRepository.
 */
public interface CustomerBankDetailRepository extends JpaRepository<CustomerBankDetailEntity, Long> {

	List<CustomerBankDetailEntity> findByCustomerIn(CustomerEntity customerEntity);

	
	



}
	