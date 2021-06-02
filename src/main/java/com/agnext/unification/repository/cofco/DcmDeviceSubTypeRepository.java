package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.DcmDeviceSubType;

@Repository("cofcoDcmDeviceSubTypeRepository")
public interface DcmDeviceSubTypeRepository extends JpaRepository<DcmDeviceSubType, Long> {

}
