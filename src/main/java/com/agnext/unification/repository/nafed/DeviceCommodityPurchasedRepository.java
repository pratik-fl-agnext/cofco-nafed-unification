package com.agnext.unification.repository.nafed;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agnext.unification.entity.nafed.DcmCommodity;
import com.agnext.unification.entity.nafed.DcmDevice;
import com.agnext.unification.entity.nafed.DeviceCommodityPurchased;

public interface DeviceCommodityPurchasedRepository extends JpaRepository<DeviceCommodityPurchased, Long> {

    @Query("from DeviceCommodityPurchased dcp where dcp.clientId=:clientId order by dcp.deviceTypeId asc")
    List<DeviceCommodityPurchased> getSubscriptionsByClient(@Param("clientId") Long clientId);

    @Query("select dcp.device from DeviceCommodityPurchased dcp where dcp.clientId=:clientId and dcp.deviceTypeId=:deviceTypeId order by dcp.device.id asc")
    List<DcmDevice> getSubscriptionsByClientAndDeviceType(@Param("clientId") Long clientId,
	    @Param("deviceTypeId") Long deviceTypeId);

    List<DeviceCommodityPurchased> findByClientIdAndCommodityAndDeviceAndDeviceTypeIdAndPackageId(Long clientId,
	    DcmCommodity commodity, DcmDevice device, Long deviceTypeId, Long packageId);

    @Query("from DeviceCommodityPurchased dcp where dcp.clientId=:clientId and dcp.device.id=:id")
    List<DeviceCommodityPurchased> getSubscriptionsByClientAndDeviceId(@Param("clientId") Long clientId,
	    @Param("id") Long id);

    @Query("from DeviceCommodityPurchased d where d.licenceNo  =:licenceNo")
    DeviceCommodityPurchased renewLicence(@Param("licenceNo") String licenceNo);

    @Query("select dcp from DeviceCommodityPurchased dcp where dcp.licenceNo =:licenseNo")
    DeviceCommodityPurchased fetchLicenseByLicenseNo(@Param("licenseNo") String licenseNo);

    @Query("from DeviceCommodityPurchased dcp where dcp.device.id=:deviceId")
    List<DeviceCommodityPurchased> findIfSubscribed(@Param("deviceId") Long deviceId);

    @Query("from DeviceCommodityPurchased dcp where dcp.device.serialNumber=:serialNo")
    List<DeviceCommodityPurchased> findByDeviceSerialNumberIn(@Param("serialNo") String serialNo);

    @Query("from DeviceCommodityPurchased d where d.clientId = :clientId and d.commodity.id In :commodity and d.device.serialNumber = :deviceSerialNumber and d.packageId = :packageId")
    List<DeviceCommodityPurchased> findByClientIdAndCommodityIdInAndDeviceSerialNumberAndPackageId(
	    @Param("clientId") Long clientId, @Param("commodity") List<Long> commodity,
	    @Param("deviceSerialNumber") String deviceSerialNumber, @Param("packageId") Long packageId);

}
