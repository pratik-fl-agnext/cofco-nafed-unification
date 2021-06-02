package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.cofco.AddressTypeEntity;

public interface AddressTypeRepository extends JpaRepository<com.agnext.unification.entity.cofco.AddressTypeEntity, Long> {

	AddressTypeEntity findByAddressTypeId(Long addressTypeId);

	AddressTypeEntity findByAddressType(String user);


}
