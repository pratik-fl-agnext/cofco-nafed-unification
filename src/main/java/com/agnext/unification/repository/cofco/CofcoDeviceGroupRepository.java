package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.DcmDeviceGroup;

@Repository("cofcoDeviceGroupRepository")
public interface CofcoDeviceGroupRepository extends JpaRepository<DcmDeviceGroup, Long>{

}
