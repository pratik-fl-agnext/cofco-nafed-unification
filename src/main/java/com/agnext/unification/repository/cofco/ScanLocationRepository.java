package com.agnext.unification.repository.cofco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agnext.unification.entity.cofco.ScanLocation;
import com.agnext.unification.entity.cofco.StateEntity;

@Repository("cofcoScanLocationRepository")
public interface ScanLocationRepository extends JpaRepository<ScanLocation, Long> {

    ScanLocation findByLocationName(String name);

    ScanLocation findByIdAndStatusStatusId(Long id, Long id2);

    //	@Query("SELECT cl FROM ScanLocation cl where cl.customerId = :customerId and cl.dcmStatus.id = 8 "
    //			+ " and (COALESCE(:keyword, null) is null or LOWER(cl.instCenterName) Like LOWER(concat('%', :keyword,'%')) or LOWER(cl.dcmCommercialLocationType.instCenterTypeDesc) Like LOWER(concat('%', :keyword,'%'))) ")
    //	List<ScanLocation> getDetailsWithSearch(@Param("customerId") Long customerId,
    //			@Param("keyword") String keyword);

    List<Long> findIdByStatusStatusId(Long statusId);

    @Query("Select sl from ScanLocation sl where sl.id IN (:ids) and sl.status.statusId =:statusId")
    List<ScanLocation> findAllByIdAndStatusStatusId(@Param("ids") List<Long> ids, @Param("statusId") Long statusId);

    @Query("Select distinct sl.state from ScanLocation sl where sl.id IN (:ids) and sl.status.statusId =:statusId")
    List<StateEntity> getStateByIdAndStatusStatusId(@Param("ids") List<Long> ids, @Param("statusId") Long statusId);

    @Query("Select sl from ScanLocation sl where (COALESCE(:ids, null) is null or sl.id IN :ids) "
	    + "and (COALESCE(:keyword, null) is null or LOWER(sl.locationName) Like LOWER(concat('%', :keyword,'%'))) "
	    + "and (COALESCE(:keyword, null) is null or LOWER(sl.warehouseName) Like LOWER(concat('%', :keyword,'%')))")
    List<ScanLocation> findLocationsWithSearch(@Param("ids") List<Long> ids, @Param("keyword") String keyword);

    @Query("Select sl from ScanLocation sl where (COALESCE(:ids, null) is null or sl.id IN :ids) "
	    + "and (COALESCE(:keyword, null) is null or LOWER(sl.locationName) Like LOWER(concat('%', :keyword,'%')))")
    List<ScanLocation> findLocationsWithSearchForAIModel(@Param("ids") List<Long> ids,
	    @Param("keyword") String keyword);

    ScanLocation findByWarehouseName(String warehouseName);

}
