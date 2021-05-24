package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.nafed.CommodityVarietyEntity;

public interface CommodityVarietyRepository extends JpaRepository<CommodityVarietyEntity, Long> {

    List<CommodityVarietyEntity> findByCommodityId(Long commodityId);

    @Query("from CommodityVarietyEntity e where e.id = :id ")
    CommodityVarietyEntity getById(@Param("id") Long id);

}
