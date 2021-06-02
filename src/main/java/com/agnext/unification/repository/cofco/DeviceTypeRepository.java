package com.agnext.unification.repository.cofco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.cofco.DcmDeviceType;

public interface DeviceTypeRepository extends JpaRepository<DcmDeviceType, Long> {
	@Query("select dt.deviceTypeDesc from DcmDeviceType dt where dt.id=:id")
	String getName(@Param("id") Long id);
	
	@Query("select dt.deviceTypeDesc from DcmDeviceType dt where dt.id=:deviceTypeId")
	String getNameById(@Param("deviceTypeId") Long deviceTypeId);
}
