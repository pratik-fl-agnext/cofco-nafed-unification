package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.nafed.ScanEntity;
import com.agnext.unification.repository.BaseRepository;

public interface NafedScmScanRepository extends BaseRepository<ScanEntity> {

    @Query("from ScanEntity se where se.id=:scanId")
    ScanEntity getById(@Param("scanId") Long scanId);

    @Query("select distinct s.installatonCenterId from ScanEntity s where s.customerId = :customerId")
    List<Long> getInstallationCenterByCustomerId(@Param("customerId") Long customerId);

    ScanEntity findBySampleId(String sampleId);

    @Query("SELECT s FROM ScanEntity s inner join ScanLocation sl on sl.id=s.installatonCenterId where (COALESCE(:customerId, null) is null or s.customerId=:customerId) "
	    + "and (COALESCE(:commodityId, null) is null or s.commodityId = :commodityId) "
	    + "and (COALESCE(:ccId, null) is null or s.installatonCenterId = :ccId) "
	    + "and ((COALESCE(:dateFrom, null) is null and COALESCE(:dateTo, null) is null) or (s.createdOn between :dateFrom AND :dateTo)) "
	    + "and (COALESCE(:farmerId, null) is null or s.farmerId = :farmerId) "
	    + "and (COALESCE(:deviceType, null) is null or s.deviceType = :deviceType) "
	    + "and (COALESCE(:deviceTypeId, null) is null or s.deviceTypeId = :deviceTypeId) "
	    + "and (COALESCE(:deviceSerialNumber, null) is null or s.deviceSerialNo = :deviceSerialNumber) "
	    + "and (COALESCE(:operatorId, null) is null or s.operatorId = :operatorId) "
	    + "and (COALESCE(:commCategoryId, null) is null or s.commodityCategoryId = :commCategoryId) "
	    + "and (COALESCE(:stateId, null) is null or sl.state.id = :stateId) "
	    + "and s.isValid = true ")
    List<ScanEntity> getScanHistory(@Param("customerId") Long customerId, @Param("commodityId") Long commodityId,
	    @Param("ccId") Long ccId, @Param("dateFrom") Long dateFrom, @Param("dateTo") Long dateTo,
	    @Param("farmerId") Long farmerId, @Param("deviceType") String deviceType,
	    @Param("deviceTypeId") Long deviceTypeId, @Param("deviceSerialNumber") String deviceSerialNumber,
	    Pageable pageable, @Param("operatorId") Long operatorId, @Param("commCategoryId") Long commCategoryId,
	    @Param("stateId") Long stateId);

    @Query("SELECT s FROM ScanEntity s inner join ScanLocation sl on sl.id=s.installatonCenterId where (COALESCE(:customerId, null) is null or s.customerId=:customerId) "
	    + "and (COALESCE(:commodityId, null) is null or s.commodityId = :commodityId) "
	    + "and (COALESCE(:ccId, null) is null or s.installatonCenterId = :ccId) "
	    + "and ((COALESCE(:dateFrom, null) is null and COALESCE(:dateTo, null) is null) or (s.createdOn between :dateFrom AND :dateTo)) "
	    + "and (COALESCE(:farmerId, null) is null or s.farmerId = :farmerId) "
	    + "and (COALESCE(:deviceType, null) is null or s.deviceType = :deviceType) "
	    + "and (COALESCE(:deviceTypeId, null) is null or s.deviceTypeId = :deviceTypeId) "
	    + "and (COALESCE(:deviceSerialNumber, null) is null or s.deviceSerialNo = :deviceSerialNumber) "
	    + "and (COALESCE(:commCategoryId, null) is null or s.commodityCategoryId = :commCategoryId) "
	    + "and (COALESCE(:operatorId, null) is null or s.operatorId = :operatorId) "
	    + "and (COALESCE(:stateAdminId, null) is null or s.stateAdmin = :stateAdminId) order by s.id desc")
    List<ScanEntity> fetchScanHistoryForMoisture(@Param("customerId") Long customerId,
	    @Param("commodityId") Long commodityId, @Param("ccId") Long ccId, @Param("dateFrom") Long dateFrom,
	    @Param("dateTo") Long dateTo, @Param("farmerId") Long farmerId, @Param("deviceType") String deviceType,
	    @Param("deviceTypeId") Long deviceTypeId, @Param("deviceSerialNumber") String deviceSerialNumber,
	    @Param("commCategoryId") Long commCategoryId, @Param("operatorId") Long operatorId,
	    @Param("stateAdminId") Long stateAdminId, Pageable pageable);

    @Query("SELECT s FROM ScanEntity s inner join ScanLocation sl on sl.id=s.installatonCenterId where (COALESCE(:customerId, null) is null or s.customerId=:customerId) "
	    + "and (COALESCE(:commodityId, null) is null or s.commodityId = :commodityId) "
	    + "and (COALESCE(:ccId, null) is null or s.installatonCenterId = :ccId) "
	    + "and ((COALESCE(:dateFrom, null) is null and COALESCE(:dateTo, null) is null) or s.createdOn between :dateFrom AND :dateTo) "
	    + "and (COALESCE(:farmerId, null) is null or s.farmerId = :farmerId) "
	    + "and (COALESCE(:deviceType, null) is null or s.deviceType = :deviceType) "
	    + "and (COALESCE(:deviceTypeId, null) is null or s.deviceTypeId = :deviceTypeId) "
	    + "and (COALESCE(:deviceSerialNumber, null) is null or s.deviceSerialNo = :deviceSerialNumber) "
	    + "and (COALESCE(:operatorId, null) is null or s.operatorId = :operatorId) "
	    + "and (COALESCE(:commCategoryId, null) is null or s.commodityCategoryId = :commCategoryId) "
	    + "and (COALESCE(:stateId, null) is null or sl.state.id = :stateId) " + "and s.isValid = true")

    List<ScanEntity> getScanHistoryCount(@Param("customerId") Long customerId, @Param("commodityId") Long commodityId,
	    @Param("ccId") Long ccId, @Param("dateFrom") Long dateFrom, @Param("dateTo") Long dateTo,
	    @Param("farmerId") Long farmerId, @Param("deviceType") String deviceType,
	    @Param("deviceTypeId") Long deviceTypeId, @Param("deviceSerialNumber") String deviceSerialNumber,
	    @Param("operatorId") Long operatorId, @Param("commCategoryId") Long commCategoryId,
	    @Param("stateId") Long stateId);

    @Query("Select Distinct s.commodityCategoryId from ScanEntity s where (COALESCE(:customerId, null) is null or s.customerId=:customerId) and (COALESCE(:operatorId, null) is null or s.operatorId=:operatorId) ")
    List<Long> getCategoryIdsByCustomer(@Param("customerId") Long customerId, @Param("operatorId") Long operatorId);
    
    @Query("Select Distinct s.commodityCategoryId from ScanEntity s where (COALESCE(:stateAdminId, null) is null or s.stateAdmin=:stateAdminId) ")
    List<Long> getCategoriesForStateAdmin(@Param("stateAdminId") Long stateAdminId);

    @Query("select Distinct s.commodityId from ScanEntity s where s.commodityId != null ")
    List<Long> getAllCommodityId();

    @Query("select Distinct s.deviceSerialNo from ScanEntity s where s.commodityId IN (:commodityIds)")
    List<String> findAllDeviceSNByCommodityIds(@Param("commodityIds") List<Long> commodityIds);

    @Query("SELECT s FROM ScanEntity s where (COALESCE(:customerId, null) is null or s.customerId=:customerId) "
	    + "and (COALESCE(:commodityId, null) is null or s.commodityId = :commodityId) "
	    + "and (COALESCE(:ccId, null) is null or s.installatonCenterId = :ccId) "
	    + "and ((COALESCE(:dateFrom, null) is null and COALESCE(:dateTo, null) is null) or s.createdOn between :dateFrom AND :dateTo) "
	    + "and (COALESCE(:farmerId, null) is null or s.farmerId = :farmerId) "
	    + "and (COALESCE(:deviceType, null) is null or s.deviceType = :deviceType) "
	    + "and (COALESCE(:deviceTypeId, null) is null or s.deviceTypeId = :deviceTypeId) "
	    + "and (COALESCE(:deviceSerialNumber, null) is null or s.deviceSerialNo = :deviceSerialNumber) "
	    + "and (COALESCE(:operatorId, null) is null or s.operatorId = :operatorId) " + " and s.customerId=192 "
	    + "and s.isValid = true order by s.id desc")
    List<ScanEntity> getScanHistoryCountForAI(@Param("customerId") Long customerId,
	    @Param("commodityId") Long commodityId, @Param("ccId") Long ccId, @Param("dateFrom") Long dateFrom,
	    @Param("dateTo") Long dateTo, @Param("farmerId") Long farmerId, @Param("deviceType") String deviceType,
	    @Param("deviceTypeId") Long deviceTypeId, @Param("deviceSerialNumber") String deviceSerialNumber,
	    @Param("operatorId") Long operatorId);

    @Query("select Distinct s.commodityId from ScanEntity s where (COALESCE(:categoryId, null) is null or s.commodityCategoryId=:categoryId) and (COALESCE(:customerId, null) is null or s.customerId=:customerId)")
    List<Long> getAllCommodityIdByCategory(@Param("categoryId") Long categoryId, @Param("customerId") Long customerId);
    
    @Query("select Distinct s.commodityId from ScanEntity s where (COALESCE(:categoryId, null) is null or s.commodityCategoryId=:categoryId) and (COALESCE(:stateAdminId, null) is null or s.stateAdmin=:stateAdminId)")
    List<Long> getAllCommodityForStateAdmin(@Param("categoryId") Long categoryId, @Param("stateAdminId") Long stateAdminId);

    @Query("SELECT s FROM ScanEntity s inner join ScanLocation sl on sl.id=s.installatonCenterId where (COALESCE(:customerId, null) is null or s.customerId=:customerId) "
	    + "and (COALESCE(:commodityId, null) is null or s.commodityId = :commodityId) "
	    + "and (COALESCE(:ccId, null) is null or s.installatonCenterId = :ccId) "
	    + "and ((COALESCE(:dateFrom, null) is null and COALESCE(:dateTo, null) is null) or s.createdOn between :dateFrom AND :dateTo) "
	    + "and (COALESCE(:farmerId, null) is null or s.farmerId = :farmerId) "
	    + "and (COALESCE(:deviceType, null) is null or s.deviceType = :deviceType) "
	    + "and (COALESCE(:deviceTypeId, null) is null or s.deviceTypeId = :deviceTypeId) "
	    + "and (COALESCE(:deviceSerialNumber, null) is null or s.deviceSerialNo = :deviceSerialNumber) "
	    + "and (COALESCE(:operatorId, null) is null or s.operatorId = :operatorId) "
	    + "and (COALESCE(:commCategoryId, null) is null or s.commodityCategoryId = :commCategoryId) "
	    + "and (COALESCE(:stateId, null) is null or sl.state.id = :stateId) "
	    + "and s.isValid = true order by s.id desc")

	List<ScanEntity> getScanHistoryAIModel(@Param("customerId") Long customerId, @Param("commodityId") Long commodityId,
			@Param("ccId") Long ccId, @Param("dateFrom") Long dateFrom, @Param("dateTo") Long dateTo,
			@Param("farmerId") Long farmerId, @Param("deviceType") String deviceType,
			@Param("deviceTypeId") Long deviceTypeId, @Param("deviceSerialNumber") String deviceSerialNumber,
			Pageable pageable, @Param("operatorId") Long operatorId, @Param("commCategoryId") Long commCategoryId,
			@Param("stateId") Long stateId);

	@Query("SELECT count(s.id) FROM ScanEntity s inner join ScanLocation sl on sl.id=s.installatonCenterId where (COALESCE(:customerId, null) is null or s.customerId=:customerId) "
			+ "and (COALESCE(:commodityId, null) is null or s.commodityId = :commodityId) "
			+ "and (COALESCE(:ccId, null) is null or s.installatonCenterId = :ccId) "
			+ "and ((COALESCE(:dateFrom, null) is null and COALESCE(:dateTo, null) is null) or (s.createdOn between :dateFrom AND :dateTo)) "
			+ "and (COALESCE(:farmerId, null) is null or s.farmerId = :farmerId) "
			+ "and (COALESCE(:deviceType, null) is null or s.deviceType = :deviceType) "
			+ "and (COALESCE(:deviceTypeId, null) is null or s.deviceTypeId = :deviceTypeId) "
			+ "and (COALESCE(:deviceSerialNumber, null) is null or s.deviceSerialNo = :deviceSerialNumber) "
			+ "and (COALESCE(:commCategoryId, null) is null or s.commodityCategoryId = :commCategoryId) "
			+ "and (COALESCE(:operatorId, null) is null or s.operatorId = :operatorId) "
			+ "and (COALESCE(:stateAdminId, null) is null or s.stateAdmin = :stateAdminId) ")
	Long fetchScanHistoryCountForMoisture(@Param("customerId") Long customerId, @Param("commodityId") Long commodityId,
			@Param("ccId") Long ccId, @Param("dateFrom") Long dateFrom, @Param("dateTo") Long dateTo,
			@Param("farmerId") Long farmerId, @Param("deviceType") String deviceType,
			@Param("deviceTypeId") Long deviceTypeId, @Param("deviceSerialNumber") String deviceSerialNumber,
			@Param("commCategoryId") Long commCategoryId, @Param("operatorId") Long operatorId,
			@Param("stateAdminId") Long stateAdminId);
	
    ScanEntity findBySampleIdAndCommodityId(String sampleId , Long commodityId);

	ScanEntity findBySampleIdAndCommodityName(String sampleId, String commodityName);
	
	ScanEntity findBySampleIdAndCommodityIdAndIsValid(String sampleId , Long commodityId,Boolean isValid);
	
    List<ScanEntity> findBySampleIdAndImageUniqueIdAndIsValid(String sampleId , String imageUniqueId,Boolean isValid);

}
