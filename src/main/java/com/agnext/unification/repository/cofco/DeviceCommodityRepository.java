package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.cofco.DeviceCommodityEntity;

public interface DeviceCommodityRepository extends JpaRepository<DeviceCommodityEntity, Long>{

	List<DeviceCommodityEntity> findByDeviceId(Long deviceId);

	DeviceCommodityEntity findByDeviceIdAndCommodityId(Long deviceId, Long commodityId);
	
	@Query("select s.commodity.id from DeviceCommodityEntity s where s.device.id=:deviceId and s.statusId=:statusId")
	List<Long> findByDeviceIdAndStatusId(@Param ("deviceId") Long deviceId,@Param("statusId") Integer statusId);
	
}
