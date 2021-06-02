package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.DeviceCost;

@Repository("cofcoDeviceCostRepository")
public interface DeviceCostRepository extends JpaRepository<DeviceCost, Long> {
	@Query("select dc.price from DeviceCost dc inner join DcmDevice d on d.id=dc.deviceId where d.dcmDeviceType.id=:deviceType")
	List<Double> getCostByDeviceType(@Param("deviceType") Long deviceType);

}
