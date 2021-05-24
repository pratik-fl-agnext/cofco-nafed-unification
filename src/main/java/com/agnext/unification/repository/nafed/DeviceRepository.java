package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.nafed.DcmDevice;

public interface DeviceRepository extends JpaRepository<DcmDevice, Long> {

    DcmDevice findBySerialNumber(String serialNo);

    @Query("select d.id, d.dcmDeviceType.id,d.serialNumber from DcmDevice d where d.dcmDeviceType.deviceTypeDesc =:deviceTypeName "
	    + "and (COALESCE(:keyword, null) is null or lower(d.serialNumber) Like lower(concat('%', :keyword,'%')) or lower(d.dcmDeviceType.deviceTypeDesc) Like lower(concat('%', :keyword,'%')) "
	    + "or lower(d.scanLocation.locationName) Like lower(concat('%', :keyword,'%')) "
	    + "or lower(d.scanLocation.warehouseName) Like lower(concat('%', :keyword,'%'))) ")
    List<Object[]> getDetailWithSearch(@Param("deviceTypeName") String deviceTypeName,
	    @Param("keyword") String keyword);

    @Query("select d.id, d.dcmDeviceType.id,d.serialNumber from DcmDevice d where d.dcmDeviceType.deviceTypeDesc =:deviceTypeName ")
    List<Object[]> getDetailWithoutSearch(@Param("deviceTypeName") String deviceTypeName);

    @Query("select d.serialNumber from DcmDevice d where d.serialNumber =:deviceSerialNo and d.scanLocation.id=:instaId")
    String checkDeviceInInstalationCenter(@Param("deviceSerialNo") String deviceSerialNo,
	    @Param("instaId") Long instaId);

    DcmDevice findByDcmStatusStatusIdNotAndId(Long statusId, Long deviceId);

    DcmDevice findBySerialNumberAndUserId(String deviceSerialNum, Long userId);

    @Query("Select DISTINCT d.dcmDeviceType.id,d.dcmDeviceType.deviceTypeDesc from DcmDevice as d where  d.customerId=:customerId AND d.userId=:userId AND d.dcmStatus.id=:statusId ")
    List<Object[]> findByCustomerIdAndUserIdAndDcmStatusId(@Param("customerId") Long customerId,
	    @Param("userId") Long userId, @Param("statusId") Long statusId);

    @Query("select d.id from DcmDevice d where d.dcmDeviceType.id=:deviceTypeId")
    List<Long> getByDeviceTypeId(@Param("deviceTypeId") Long deviceTypeId);

    DcmDevice findBySerialNumberIgnoreCase(String deviceId);

    DcmDevice findByUserId(Long userId);
    
    List<DcmDevice> findByUserIdInAndDcmStatusStatusIdNotIn(List<Long> userIdList, Long status);

    DcmDevice findByUserIdAndDcmStatusStatusId(Long userId, Long statusId);

    @Query("Select d from DcmDevice as d where  d.customerId=:customerId AND d.userId=:userId AND d.dcmStatus.id=:statusId ")
    DcmDevice findDetailsByCustomerIdAndUserIdAndDcmStatusId(@Param("customerId") Long customerId,
	    @Param("userId") Long userId, @Param("statusId") Long statusId);

    @Query("select d.scanLocation.id from DcmDevice d where d.userId=:operatorId ")
    List<Long> getInstCenterId(@Param("operatorId") Long operatorId);

    @Query("Select d.id,d.serialNumber from DcmDevice as d where  d.customerId=:customerId AND d.userId=:userId AND d.dcmStatus.id=:statusId ")
    List<Object[]> findDeviceByCustomerIdAndUserIdAndDcmStatusId(@Param("customerId") Long customerId,
	    @Param("userId") Long userId, @Param("statusId") Long statusId);
}
