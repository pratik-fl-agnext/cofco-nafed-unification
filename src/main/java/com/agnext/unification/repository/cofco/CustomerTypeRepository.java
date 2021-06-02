package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.CustomerTypeEntity;

@Repository("cofcoCustomerTypeRepository")
public interface CustomerTypeRepository extends JpaRepository<CustomerTypeEntity, Long> {

	CustomerTypeEntity findByCustomerTypeId(Long customerTypeId);
	CustomerTypeEntity findByCustomerType(String customerType);



}
