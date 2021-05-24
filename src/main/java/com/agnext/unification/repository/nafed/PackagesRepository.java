package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.nafed.Packages;

public interface PackagesRepository extends JpaRepository<Packages, Long> {

    @Query("select p.packageName from Packages p where p.id=:id")
    String getName(@Param("id") Long id);

    @Query("select p.durationUnit from Packages p where p.id=:id")
    String getDurationUnit(@Param("id") Long id);

    @Query("select p.totalScans from Packages p where p.id=:id")
    Long getTotalScans(@Param("id") Long id);

    @Query("select p.durationPeriod from Packages p where p.id=:id")
    String getDurationPeriod(@Param("id") Long id);

    List<Packages> findByDeviceTypeId(Long id);

    List<Packages> findByDeviceTypeIdAndIsDefaultPackage(Long id, Boolean isDefault);

    List<Packages> findByClientIdAndIsDefaultPackage(Long clientId, Boolean isDefault);
}
