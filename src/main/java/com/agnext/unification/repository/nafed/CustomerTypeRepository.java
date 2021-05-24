package com.agnext.unification.repository.nafed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.CustomerTypeEntity;

public interface CustomerTypeRepository extends JpaRepository<CustomerTypeEntity, Long> {

	CustomerTypeEntity findByCustomerTypeId(Long customerTypeId);
	CustomerTypeEntity findByCustomerType(String customerType);



}
