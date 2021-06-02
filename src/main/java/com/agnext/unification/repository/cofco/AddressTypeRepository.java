package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.AddressTypeEntity;

@Repository("cofcoAddressTypeRepository")
public interface AddressTypeRepository extends JpaRepository<com.agnext.unification.entity.cofco.AddressTypeEntity, Long> {

	AddressTypeEntity findByAddressTypeId(Long addressTypeId);

	AddressTypeEntity findByAddressType(String user);


}
