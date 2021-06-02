package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.cofco.PackageCommoditiesPrice;

public interface PackageCommoditiesPriceRepository extends JpaRepository<PackageCommoditiesPrice, Long> {

    @Query("from PackageCommoditiesPrice pcp where pcp.packages.id=:id "
	    + "and (COALESCE(:cIds, null) is null or pcp.commodities.id IN :cIds)")
    List<PackageCommoditiesPrice> findByPackages(@Param("id") Long id, @Param("cIds") List<Long> cIds);
}
