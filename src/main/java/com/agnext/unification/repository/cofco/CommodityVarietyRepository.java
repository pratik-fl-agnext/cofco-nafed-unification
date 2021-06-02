package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.CommodityVarietyEntity;

@Repository("cofcoCommodityVarietyRepository")
public interface CommodityVarietyRepository extends JpaRepository<CommodityVarietyEntity, Long> {

    List<CommodityVarietyEntity> findByCommodityId(Long commodityId);

    @Query("from CommodityVarietyEntity e where e.id = :id ")
    CommodityVarietyEntity getById(@Param("id") Long id);

    @Query("from CommodityVarietyEntity e where e.varietyName = :varietyName")
    CommodityVarietyEntity getVariety(@Param("varietyName") String varietyName);

}
