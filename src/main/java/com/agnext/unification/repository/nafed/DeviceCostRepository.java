package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.nafed.DeviceCost;

public interface DeviceCostRepository extends JpaRepository<DeviceCost, Long> {
	@Query("select dc.price from DeviceCost dc inner join DcmDevice d on d.id=dc.deviceId where d.dcmDeviceType.id=:deviceType")
	List<Double> getCostByDeviceType(@Param("deviceType") Long deviceType);

}
