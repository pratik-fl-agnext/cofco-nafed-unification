package com.agnext.unification.repository.nafed;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agnext.unification.entity.nafed.AddressTypeEntity;

public interface AddressTypeRepository extends JpaRepository<AddressTypeEntity, Long> {

	AddressTypeEntity findByAddressTypeId(Long addressTypeId);

	AddressTypeEntity findByAddressType(String user);


}
