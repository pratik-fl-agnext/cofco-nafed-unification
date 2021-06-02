/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.CustomerBankDetailEntity;
import com.agnext.unification.entity.cofco.CustomerEntity;
/**
 * The Interface CustomerBankDetailRepository.
 */
@Repository("cofcoCustomerBankDetailRepository")
public interface CustomerBankDetailRepository extends JpaRepository<CustomerBankDetailEntity, Long> {

	List<CustomerBankDetailEntity> findByCustomerIn(CustomerEntity customerEntity);

	
	



}
	