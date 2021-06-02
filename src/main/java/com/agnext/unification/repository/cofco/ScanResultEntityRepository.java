/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.ScanResultEntity;

/**
 * The Interface ScmScanRepository.
 */
@Repository("cofcoScanResultEntityRepository")
public interface ScanResultEntityRepository extends JpaRepository<ScanResultEntity, Long> {

    List<ScanResultEntity> findByScanEntityIdIn(List<Long> scanId);

    List<ScanResultEntity> findByScanEntityId(Long scanId);

    @Query("select distinct r.analysisName from ScanResultEntity r where r.scanEntity.id IN(:scanIds)")
    List<String> getAnlyticName(@Param("scanIds") List<Long> scanIds);

    @Query("select r from ScanResultEntity r where r.scanEntity.id =:scanIds")
    List<ScanResultEntity> getScanResults(@Param("scanIds") Long scanIds);
}
