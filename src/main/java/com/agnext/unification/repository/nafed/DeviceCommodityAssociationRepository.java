package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.nafed.DeviceCommodityAssociation;

public interface DeviceCommodityAssociationRepository  extends JpaRepository<DeviceCommodityAssociation, Long>{

	@Query("select dca.commodities.id from DeviceCommodityAssociation dca where dca.device.id IN(:dIds) order by dca.commodities.id desc")
	List<Long> getCropsIdsByDeviceIds(@Param("dIds") List<Long> dIds);
}
