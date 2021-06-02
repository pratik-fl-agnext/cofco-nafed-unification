/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.CustomerAddressEntity;
import com.agnext.unification.entity.cofco.CustomerEntity;

/**
 * The Interface CustomerAddressRepository.
 */
@Repository("cofcoCustomerAddressRepository")
public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity, Long> {

	List<CustomerAddressEntity> findByCustomerIn(CustomerEntity customerEntity);

	CustomerAddressEntity findByCustomerCustomerId(Long customerId);




}
