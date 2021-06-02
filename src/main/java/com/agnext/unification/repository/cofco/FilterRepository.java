package com.agnext.unification.repository.cofco;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.cofco.ClientsAnalyticsRange;
import com.agnext.unification.entity.cofco.DcmDeviceOrder;
import com.agnext.unification.entity.cofco.ScanEntity;
import com.agnext.unification.entity.cofco.ScanLocation;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.utility.Utility;

@Repository
@Transactional
public class FilterRepository {

    @PersistenceContext
    EntityManager em;

    public BigDecimal getAvgQuality(Long regionId, Long commodityId, Long ccId, String analysisName, Long from, Long to,
	    String deviceType, Long customerId, String deviceSerialNumber, Long operatorId) {

	StringBuilder sb = new StringBuilder();
	sb.append(
		"select avg(ar.result) from ScanEntity as cs inner join ScanResultEntity as ar on cs.id=ar.scanEntity.id where cs.commodityId =:commodityId  AND ar.analysisName =:analysisName");
	if (from != null && to != null) {
	    sb.append("  AND cs.createdOn between :from AND :to");
	}
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (ccId != null) {
	    sb.append(" and cs.installatonCenterId=:ccId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (deviceType != null) {
	    sb.append(" and cs.deviceType=:deviceType");
	}
	if (deviceSerialNumber != null && deviceSerialNumber.trim() != "") {
	    sb.append(" and cs.deviceSerialNo=:deviceSerialNumber");
	}
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceSerialNumber != null && deviceSerialNumber.trim() != "") {
	    query.setParameter("deviceSerialNumber", deviceSerialNumber);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (ccId != null) {
	    query.setParameter("ccId", ccId);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (analysisName != null) {
	    query.setParameter("analysisName", analysisName);
	}

	if (from != null) {
	    query.setParameter("from", from);
	}

	if (to != null) {
	    query.setParameter("to", to);
	}

	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	return Utility.getBigDecimalValue(query.getSingleResult());
    }

    public BigDecimal getMinQuality(Long regionId, Long commodityId, Long ccId, String analysisName, Long from, Long to,
	    String deviceType, Long customerId, String deviceSerialNumber, Long operatorId) {

	StringBuilder sb = new StringBuilder();
	sb.append(
		"select min(ar.result) from ScanEntity as cs inner join ScanResultEntity as ar on cs.id=ar.scanEntity.id where cs.commodityId =:commodityId AND ar.analysisName =:analysisName");
	if (from != null && to != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (ccId != null) {
	    sb.append(" and cs.installatonCenterId=:ccId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (deviceType != null) {
	    sb.append(" and cs.deviceType=:deviceType");
	}
	if (deviceSerialNumber != null && deviceSerialNumber.trim() != "") {
	    sb.append(" and cs.deviceSerialNo=:deviceSerialNumber");
	}
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (ccId != null) {
	    query.setParameter("ccId", ccId);
	}
	if (deviceSerialNumber != null && deviceSerialNumber.trim() != "") {
	    query.setParameter("deviceSerialNumber", deviceSerialNumber);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (analysisName != null) {
	    query.setParameter("analysisName", analysisName);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}

	if (from != null) {
	    query.setParameter("from", from);
	}

	if (to != null) {
	    query.setParameter("to", to);
	}

	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	return Utility.getBigDecimalValue(query.getSingleResult());
    }

    public BigDecimal getMaxQuality(Long regionId, Long commodityId, Long ccId, String analysisName, Long from, Long to,
	    String deviceType, Long customerId, String deviceSerialNumber, Long operatorId) {

	StringBuilder sb = new StringBuilder();
	sb.append(
		"select max(ar.result) from ScanEntity as cs inner join ScanResultEntity as ar on cs.id=ar.scanEntity.id where cs.commodityId =:commodityId AND ar.analysisName =:analysisName");
	if (from != null && to != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (ccId != null) {
	    sb.append(" and cs.installatonCenterId=:ccId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (deviceType != null) {
	    sb.append(" and cs.deviceType=:deviceType");
	}
	if (deviceSerialNumber != null && deviceSerialNumber.trim() != "") {
	    sb.append(" and cs.deviceSerialNo=:deviceSerialNumber");
	}
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceSerialNumber != null && deviceSerialNumber.trim() != "") {
	    query.setParameter("deviceSerialNumber", deviceSerialNumber);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (ccId != null) {
	    query.setParameter("ccId", ccId);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (analysisName != null) {
	    query.setParameter("analysisName", analysisName);
	}

	if (from != null) {
	    query.setParameter("from", from);
	}

	if (to != null) {
	    query.setParameter("to", to);
	}

	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	return Utility.getBigDecimalValue(query.getSingleResult());
    }

    public List<Double> getQualityByFilter(String regionId, Long commodityId, String ccId, String analysisName,
	    Long dateFrom, Long dateTo, Long operatorId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select ar.result from ScanEntity as cs inner join ScanResultEntity as ar on cs.id=ar.scanEntity.id where cs.commodityId =:commodityId AND ar.analysisName =:analysisName");
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (ccId != null && ccId != "") {
	    sb.append(" and cs.installatonCenterId=:ccId");
	}
	if (regionId != null && regionId.isEmpty() && regionId != "") {
	    sb.append(" and cs.regionId=:regionId");
	}
	Query query = em.createQuery(sb.toString());
	if (regionId != null && regionId.isEmpty() && regionId != "") {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (ccId != null) {
	    query.setParameter("ccId", ccId);
	}
	if (analysisName != null) {
	    query.setParameter("analysisName", analysisName);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	List<Double> list = query.getResultList();
	return list;
    }

    public List<Long> getScanDates(Long regionId, Long commodityId, Long ccId, String analysisName, Long dateFrom,
	    Long dateTo) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select cs.createdOn from ScanEntity as cs inner join ScanResultEntity as ar on cs.id=ar.scanEntity.id where cs.commodityId =:commodityId AND ar.analysisName =:analysisName");

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (ccId != null) {
	    sb.append(" and cs.installatonCenterId=:ccId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (ccId != null) {
	    query.setParameter("ccId", ccId);
	}
	if (analysisName != null) {
	    query.setParameter("analysisName", analysisName);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	List<Long> list = query.getResultList();

	return list;
    }

    public List<Long> getScanDatesForAcceptance(Long regionId, Long commodityId, Long installationCenterId,
	    Object object, Long dateFrom, Long dateTo, Long customerId, Long categoryId, String deviceType,
	    Long deviceTypeId, String deviceSerialNo, Long operatorId, Long stateAdmin, Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select cs.createdOn from ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId where 1=1");
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (installationCenterId != null) {
	    sb.append(" AND cs.installatonCenterId=:installationCenterId");
	}
	if (regionId != null) {
	    sb.append(" AND cs.regionId=:regionId");
	}
	if (categoryId != null) {
	    sb.append(" AND cs.commodityCategoryId=:categoryId");
	}

	if (commodityId != null) {
	    sb.append(" AND cs.commodityId=:commodityId");
	}

	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}

	if (deviceType != null) {
	    sb.append(" AND cs.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND cs.deviceTypeId=:deviceTypeId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (installationCenterId != null) {
	    query.setParameter("installationCenterId", installationCenterId);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (categoryId != null) {
	    query.setParameter("categoryId", categoryId);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}

	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}

	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}

	List<Long> list = query.getResultList();
	return list;
    }

    public List<Object[]> getCommodityIds(Long regionId, Long commodityId, Long installationCenterId, Long dateFrom,
	    Long dateTo, Long customerId, Long categoryId, String deviceType, Long deviceTypeId, String deviceSerialNo,
	    Long operatorId, Long stateAdmin, Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select count(cs.commodityId), c.commodityName from ScanEntity as cs inner join DcmCommodity c on c.id=cs.commodityId inner join ScanLocation sl on sl.id=cs.installatonCenterId where 1=1");
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (installationCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:installationCenterId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (deviceType != null) {
	    sb.append(" AND cs.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND cs.deviceTypeId=:deviceTypeId");
	}
	if (commodityId != null) {
	    sb.append(" AND cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}
	if (categoryId != null) {
	    sb.append(" AND cs.commodityCategoryId=:categoryId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}
	sb.append(" GROUP BY cs.commodityId");
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (installationCenterId != null) {
	    query.setParameter("installationCenterId", installationCenterId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (categoryId != null) {
	    query.setParameter("categoryId", categoryId);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	List<Object[]> list = query.getResultList();
	return list;
    }

    public List<Object[]> getAvgQualityPerDay(Long regionId, Long commodityId, Long ccId, String analysisName,
	    Long dateFrom, Long dateTo, Long customerId, String deviceSerialNumber, Long operatorId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select count(cs.id),avg(ar.result),cs.createdOnDate,cs.createdOn from ScanEntity as cs inner join ScanResultEntity as ar on cs.id=ar.scanEntity.id where cs.commodityId =:commodityId AND ar.analysisName =:analysisName");
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (ccId != null) {
	    sb.append(" and cs.installatonCenterId=:ccId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (deviceSerialNumber != null && deviceSerialNumber.trim() != "") {
	    sb.append(" and cs.deviceSerialNo=:deviceSerialNumber");
	}
	sb.append(" GROUP BY cs.createdOnDate ORDER BY cs.createdOnDate ASC");
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}

	if (ccId != null) {
	    query.setParameter("ccId", ccId);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (analysisName != null) {
	    query.setParameter("analysisName", analysisName);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (deviceSerialNumber != null && deviceSerialNumber.trim() != "") {
	    query.setParameter("deviceSerialNumber", deviceSerialNumber);
	}
	return query.getResultList();
    }

    public Long getScanCountPerDay(Long regionId, Long commodityId, Long installationCenterId, Object object,
	    Long dateFrom, Long dateTo, Long customerId, Long categoryId, String deviceType, Long deviceTypeId,
	    String deviceSerialNo, Long operatorId, Long stateAdmin, Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select count(cs.id) from ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId where 1=1");
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (installationCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:installationCenterId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (categoryId != null) {
	    sb.append(" and cs.commodityCategoryId=:categoryId");
	}
	if (deviceType != null) {
	    sb.append(" AND cs.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND cs.deviceTypeId=:deviceTypeId");
	}
	if (commodityId != null) {
	    sb.append(" AND cs.commodityId=:commodityId");
	}

	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}

	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (installationCenterId != null) {
	    query.setParameter("installationCenterId", installationCenterId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (categoryId != null) {
	    query.setParameter("categoryId", categoryId);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	return (Long) query.getSingleResult();
    }

    public Long getScanCountPerDay(Long regionId, Long commodityId, Long ccId, String analysisName, Long dateFrom,
	    Long dateTo, Object object) {
	StringBuilder sb = new StringBuilder();
	sb.append("select count(cs.id) from ScanEntity as cs  where cs.commodityId =:commodityId");

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (ccId != null) {
	    sb.append(" and cs.installatonCenterId=:ccId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (ccId != null) {
	    query.setParameter("ccId", ccId);
	}

	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	return (Long) query.getSingleResult();
    }

    public List<Object[]> getAggrigateQuantity(Long commodityId, Long ccId, Long dateFrom, Long dateTo, Long customerId,
	    String deviceType, Long deviceTypeId, String deviceSerialNo, Long operatorId, Long stateAdmin,
	    Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"SELECT avg(cs.weight),min(cs.weight),max(cs.weight),sum(cs.weight) FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId where 1=1 ");

	if (customerId != null) {
	    sb.append(" and cs.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (deviceTypeId != null) {
	    sb.append(" and cs.deviceTypeId=:deviceTypeId");
	}

	if (deviceType != null) {
	    sb.append(" and cs.deviceType=:deviceType");
	}

	if (ccId != null) {
	    sb.append(" and cs.installatonCenterId=:ccId");
	}
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	}

	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}
	if (deviceSerialNo != null) {
	    sb.append(" and cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}

	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}

	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}

	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}

	if (ccId != null) {
	    query.setParameter("ccId", ccId);
	}
	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (deviceSerialNo != null) {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	List<Object[]> list = query.getResultList();

	return list;
    }

    public List<Object[]> getCollectionsQuantityPerLocation(Long regionId, Long commodityId, Object object,
	    Long dateFrom, Long dateTo, Long customerId, String deviceType, Long deviceTypeId, String deviceSerialNo,
	    Long operatorId, Long stateAdmin, Long stateId) {

	StringBuilder sb = new StringBuilder();
	sb.append(
		"SELECT cs.installatonCenterId,sum(cs.weight) FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	if (customerId != null) {
	    sb.append(" and cs.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (deviceTypeId != null) {
	    sb.append(" and cs.deviceTypeId=:deviceTypeId");
	}

	if (deviceType != null) {
	    sb.append(" and cs.deviceType=:deviceType");
	}

	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	}

	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}
	if (deviceSerialNo != null) {
	    sb.append(" and cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}

	sb.append(" group by cs.installatonCenterId ");
	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}

	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}

	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (deviceSerialNo != null) {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	List<Object[]> list = query.getResultList();

	return list;
    }

    public Object[] getCollectionsQuantityByLocation(Long commodityId, Long locationId, Long dateFrom, Long dateTo,
	    Long customerId, String deviceType, Long deviceTypeId, String deviceSerialNo, Long operatorId,
	    Long stateAdmin, Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"SELECT cs.installatonCenterId,sum(cs.weight) FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId where  1=1 ");

	if (customerId != null) {
	    sb.append(" and cs.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (deviceTypeId != null) {
	    sb.append(" and cs.deviceTypeId=:deviceTypeId");
	}

	if (deviceType != null) {
	    sb.append(" and cs.deviceType=:deviceType");
	}

	if (locationId != null) {
	    sb.append(" and cs.installatonCenterId=:locationId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	}

	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}

	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}

	if (deviceSerialNo != null) {
	    sb.append(" and cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}

	// sb.append(" group by cs.installatonCenterId ");
	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (locationId != null) {
	    query.setParameter("locationId", locationId);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}

	if (deviceSerialNo != null) {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}

	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	Object[] list = (Object[]) query.getSingleResult();

	return list;
    }

    public List<Object[]> getCollectionDetailsOverTime(Integer pageNumber, Integer limit, Long customerId,
	    Long commodityId, Long instCenterId, Long dateFrom, Long dateTo, String deviceType, Long deviceTypeId,
	    String deviceSerialNo, Long operatorId, Long stateAdmin, Long stateId) {

	StringBuilder sb = new StringBuilder();
	sb.append(
		"SELECT sum(cs.weight),cs.createdOnDate,cs.createdOn FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	if (customerId != null) {
	    sb.append(" and cs.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (deviceTypeId != null) {
	    sb.append(" and cs.deviceTypeId=:deviceTypeId");
	}

	if (deviceType != null) {
	    sb.append(" and cs.deviceType=:deviceType");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	}

	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}

	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}

	if (instCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:instCenterId");
	}

	if (deviceSerialNo != null) {
	    sb.append(" and cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}
	sb.append(" group by cs.createdOnDate ");
	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}

	if (deviceSerialNo != null) {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}

	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	List<Object[]> list = query.getResultList();

	return list;
    }

    public List<Long> getClientsIds(Long commodityId, Long dateFrom, Long dateTo, Long instCenterId, Long customerId,
	    String deviceType, Long deviceTypeId, Long userId, Long operatorId) {
	StringBuilder sb = new StringBuilder();
	sb.append("SELECT s.userId FROM ScanEntity s where 1=1");

	if (customerId != null) {
	    sb.append(" and s.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and s.operatorId =:operatorId");
	}

	if (userId != null) {
	    sb.append(" and s.userId=:userId");
	}
	if (instCenterId != null) {
	    sb.append(" and s.installatonCenterId=:instCenterId");
	}
	if (commodityId != null) {
	    sb.append(" and s.commodityId=:commodityId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND s.createdOn between :dateFrom AND :dateTo");
	}
	if (deviceType != null) {
	    sb.append(" AND s.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND s.deviceTypeId=:deviceTypeId");
	}

	Query query = em.createQuery(sb.toString());

	if (userId != null) {
	    query.setParameter("userId", userId);
	}

	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}

	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	List<Long> fList = query.getResultList();
	return fList;

    }

    public BigDecimal maxClientCollection(Long commodityId, Long dateFrom, Long dateTo, Long customerId, Long userId,
	    Long instCenterId, Long operatorId) {
	StringBuilder sb = new StringBuilder();
	sb.append("SELECT sum(s.weight) FROM ScanEntity s where 1=1 ");

	if (customerId != null) {
	    sb.append(" and s.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and s.operatorId =:operatorId");
	}

	if (instCenterId != null) {
	    sb.append(" and s.installatonCenterId =:instCenterId");
	}
	if (commodityId != null) {
	    sb.append(" and s.commodityId =: commodityId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND s.createdOn between :dateFrom AND :dateTo");
	}
	if (userId != null) {
	    sb.append(" AND s.userId =:userId");
	}

	Query query = em.createQuery(sb.toString());
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}

	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (userId != null) {
	    query.setParameter("userId", userId);
	}
	return Utility.getBigDecimalValue(query.getSingleResult());
    }

    public List<Long> getScanDatesForCollectionOverTime(Long commodityId, Long instCenterId, Long dateFrom, Long dateTo,
	    Long customerId, Long operatorId) {

	StringBuilder sb = new StringBuilder();
	sb.append("SELECT s.createdOn FROM ScanEntity s where 1=1 ");

	if (customerId != null) {
	    sb.append(" and s.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and s.operatorId =:operatorId");
	}

	if (instCenterId != null) {
	    sb.append(" and s.installatonCenterId =:instCenterId");
	}
	if (commodityId != null) {
	    sb.append(" and s.commodityId =: commodityId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND s.createdOn between :dateFrom AND :dateTo");
	}

	Query query = em.createQuery(sb.toString());
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}

	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}

	return query.getResultList();
    }

    public BigDecimal getMinClientGraphDataPerDay(Long commodityId, Long instCenterId, Long dateFrom, Long dateTo,
	    Long clientIdWithMinAMT, Long customerId, Long operatorId) {

	StringBuilder sb = new StringBuilder();
	sb.append("SELECT sum(weight) FROM ScanEntity s where 1=1 ");

	if (customerId != null) {
	    sb.append(" and s.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and s.operatorId =:operatorId");
	}

	if (instCenterId != null) {
	    sb.append(" and s.installatonCenterId =:instCenterId");
	}
	if (commodityId != null) {
	    sb.append(" and s.commodityId =: commodityId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND s.createdOn between :dateFrom AND :dateTo");
	}
	if (clientIdWithMinAMT != null) {
	    sb.append(" AND s.userId =:userId");
	}
	Query query = em.createQuery(sb.toString());
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}

	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (clientIdWithMinAMT != null) {
	    query.setParameter("userId", clientIdWithMinAMT);
	}
	return Utility.getBigDecimalValue(query.getSingleResult());
    }

    public BigDecimal getMaxClientGraphDataPerDay(Long commodityId, Long instCenterId, Long dateFrom, Long dateTo,
	    Long userId, Long customerId, Long operatorId) {
	StringBuilder sb = new StringBuilder();
	sb.append("SELECT sum(weight) FROM ScanEntity s where 1=1 ");

	if (customerId != null) {
	    sb.append(" and s.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and s.operatorId =:operatorId");
	}

	if (instCenterId != null) {
	    sb.append(" and s.installatonCenterId =:instCenterId");
	}
	if (commodityId != null) {
	    sb.append(" and s.commodityId =: commodityId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND s.createdOn between :dateFrom AND :dateTo");
	}
	if (userId != null) {
	    sb.append(" AND s.userId =:userId");
	}
	Query query = em.createQuery(sb.toString());
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}

	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (userId != null) {
	    query.setParameter("userId", userId);
	}
	return Utility.getBigDecimalValue(query.getSingleResult());
    }

    public List<Long> getClientIds(Long commodityId, Long dateFrom, Long dateTo, Long instCenterId, Long customerId,
	    String deviceType, Long deviceTypeId, Long userId, Long operatorId) {
	StringBuilder sb = new StringBuilder();
	sb.append("SELECT s.customerId FROM ScanEntity s where 1=1");

	if (customerId != null) {
	    sb.append(" and s.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and s.operatorId =:operatorId");
	}
	if (userId != null) {
	    sb.append(" and s.userId=:userId");
	}
	if (instCenterId != null) {
	    sb.append(" and s.installatonCenterId=:instCenterId");
	}
	if (commodityId != null) {
	    sb.append(" and s.commodityId=:commodityId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND s.createdOn between :dateFrom AND :dateTo");
	}
	if (deviceType != null) {
	    sb.append(" AND s.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND s.deviceTypeId=:deviceTypeId");
	}

	Query query = em.createQuery(sb.toString());
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (userId != null) {
	    query.setParameter("userId", userId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	List<Long> fList = query.getResultList();
	return fList;
    }

    public Object[] getClientsParameters(Long commodityId, Long instCenterId, String analysisName, Long dateFrom,
	    Long dateTo, String deviceType, Long deviceTypeId, Long userId, Long customerId, Long operatorId) {

	StringBuilder sb = new StringBuilder();
	Object[] obj = null;
	sb.append(
		"select avg(cs.weight),sum(cs.areaCovered),cs.installatonCenterId from ScanEntity as cs  where  1=1 ");
	if (customerId != null) {
	    sb.append(" and cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (instCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:instCenterId");
	}
	if (userId != null) {
	    sb.append(" and cs.userId=:userId");
	}
	if (commodityId != null) {
	    sb.append(" AND cs.commodityId =:commodityId");
	}

	if (deviceType != null) {
	    sb.append(" AND cs.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND cs.deviceTypeId=:deviceTypeId");
	}
	//		if (analysisName != null) {
	//			sb.append(" AND ar.analysisName =:analysisName");
	//		}

	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}

	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}
	//		if (analysisName != null) {
	//			query.setParameter("analysisName", analysisName);
	//		}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (userId != null) {
	    query.setParameter("userId", userId);
	}
	obj = (Object[]) query.getSingleResult();
	return obj;
    }

    public BigDecimal getDateWiseQuantity(Long commodityId, Long instCenterId, Long dateFrom, Long dateTo,
	    Long customerId, String deviceType, Long deviceTypeId, String deviceSerialNo, Long userId, Long operatorId,
	    Long stateAdmin, Long stateId) {

	StringBuilder sb = new StringBuilder();
	BigDecimal sum = new BigDecimal(0.0);
	sb.append(
		"SELECT sum(cs.weight) FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	if (customerId != null) {
	    sb.append(" and cs.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (deviceTypeId != null) {
	    sb.append(" and cs.deviceTypeId=:deviceTypeId");
	}

	if (deviceType != null) {
	    sb.append(" and cs.deviceType=:deviceType");
	}

	if (userId != null && userId != 0) {
	    sb.append(" and cs.userId=:userId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	}

	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}

	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}

	if (instCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:instCenterId");
	}
	if (deviceSerialNo != null) {
	    sb.append(" and cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}
	System.out.println(" The query for increment-decrement API is :  " + sb);
	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (userId != null && userId != 0) {
	    query.setParameter("userId", userId);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}

	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}
	if (deviceSerialNo != null) {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	sum = Utility.getBigDecimalValue(query.getSingleResult());
	if (sum == null) {
	    return new BigDecimal(0.0);
	}
	return sum;
    }

    public List<Object[]> getCollectionsQuantityPerDay(Long commodityId, Long instCenterId, Long dateFrom, Long dateTo,
	    Long customerId, String deviceType, Long deviceTypeId, Long commodityCategoryId, String deviceSerialNo,
	    Long operatorId, Long stateAdmin, Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"SELECT sum(cs.weight),cs.createdOnDate,cs.createdOn,cs.id FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	if (customerId != null) {
	    sb.append(" and cs.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (deviceTypeId != null) {
	    sb.append(" and cs.deviceTypeId=:deviceTypeId");
	}

	if (deviceType != null) {
	    sb.append(" and cs.deviceType=:deviceType");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	}

	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}
	if (commodityCategoryId != null) {
	    sb.append(" and cs.commodityCategoryId=:commodityCategoryId");
	}
	if (instCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:instCenterId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}
	sb.append(" group by cs.createdOnDate ");
	System.out.println("Query : " + sb);
	Query query = em.createQuery(sb.toString());
	if (commodityCategoryId != null) {
	    query.setParameter("commodityCategoryId", commodityCategoryId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}

	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}
	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	List<Object[]> list = query.getResultList();

	return list;
    }

    public Long getTotalScanAcceptance(Long regionId, Long commodityId, Long installationCenterId, Long dateFrom,
	    Long dateTo, Long customerId, Long categoryId, String deviceType, Long deviceTypeId, String deviceSerialNo,
	    Long operatorId, Long stateAdmin, Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select count(cs.id) from ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId where cs.approval=1");
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (installationCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:installationCenterId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (categoryId != null) {
	    sb.append(" AND cs.commodityCategoryId=:categoryId");
	}
	if (deviceType != null) {
	    sb.append(" AND cs.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND cs.deviceTypeId=:deviceTypeId");
	}
	if (commodityId != null) {
	    sb.append(" AND cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}

	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (installationCenterId != null) {
	    query.setParameter("installationCenterId", installationCenterId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (categoryId != null) {
	    query.setParameter("categoryId", categoryId);
	}

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}

	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	return (Long) query.getSingleResult();

    }

    public List<Object[]> getAcceptedSumByCommodity(Long regionId, Long commodityId, Long installationCenterId,
	    Long dateFrom, Long dateTo, Long customerId, Long categoryId, String deviceType, Long deviceTypeId,
	    String deviceSerialNo, Long operatorId, Long stateAdmin, Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select count(cs.id), count(cs.accepted), c.commodityName from ScanEntity as cs inner join DcmCommodity c on c.id=cs.commodityId inner join ScanLocation sl on sl.id=cs.installatonCenterId  where cs.approval=1 ");
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (installationCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:installationCenterId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (deviceType != null) {
	    sb.append(" AND cs.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND cs.deviceTypeId=:deviceTypeId");
	}
	if (commodityId != null) {
	    sb.append(" AND cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}
	if (categoryId != null) {
	    sb.append(" AND cs.commodityCategoryId=:categoryId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}
	sb.append(" GROUP BY cs.commodityId");
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (installationCenterId != null) {
	    query.setParameter("installationCenterId", installationCenterId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (categoryId != null) {
	    query.setParameter("categoryId", categoryId);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	List<Object[]> list = query.getResultList();
	return list;
    }

    public List<Object[]> getScanAcceptanceRateAndCountPerDay(Long regionId, Long commodityId,
	    Long installationCenterId, Long startFrom, Long endTo, Long customerId, Long categoryId, String deviceType,
	    Long deviceTypeId, String deviceSerialNo, Long operatorId, Long stateAdmin, Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select count(cs.id), count(cs.accepted) from ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where cs.approval=1");
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (startFrom != null && endTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (installationCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:installationCenterId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (deviceType != null) {
	    sb.append(" AND cs.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND cs.deviceTypeId=:deviceTypeId");
	}
	if (commodityId != null) {
	    sb.append(" AND cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}
	if (categoryId != null) {
	    sb.append(" AND cs.commodityCategoryId=:categoryId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}
	sb.append(" GROUP BY cs.commodityId");
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (installationCenterId != null) {
	    query.setParameter("installationCenterId", installationCenterId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (startFrom != null) {
	    query.setParameter("from", startFrom);
	}
	if (endTo != null) {
	    query.setParameter("to", endTo);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (categoryId != null) {
	    query.setParameter("categoryId", categoryId);
	}
	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	List<Object[]> list = query.getResultList();
	return list;
    }

    public Long getTotalScanVariance(Long regionId, Long commodityId, Long installationCenterId, Long dateFrom,
	    Long dateTo, Long customerId, Long categoryId, String deviceType, Long deviceTypeId, String deviceSerialNo,
	    Long operatorId, Long stateId) {

	StringBuilder sb = new StringBuilder();
	sb.append(
		"select sum(cs.variance) from ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId where 1=1");
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (installationCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:installationCenterId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (categoryId != null) {
	    sb.append(" and cs.commodityCategoryId=:categoryId");
	}
	if (deviceType != null) {
	    sb.append(" AND cs.deviceType=:deviceType");
	}
	if (commodityId != null) {
	    sb.append(" AND cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND cs.deviceTypeId=:deviceTypeId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (installationCenterId != null) {
	    query.setParameter("installationCenterId", installationCenterId);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	// if (categoryId != null) {
	// query.setParameter("categoryId", categoryId);
	// }
	if (categoryId != null) {
	    query.setParameter("categoryId", categoryId);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	return (Long) query.getSingleResult();

    }

    public List<Object[]> getVarianceSumByCommodity(Long regionId, Long commodityId, Long installationCenterId,
	    Long dateFrom, Long dateTo, Long customerId, Long categoryId, String deviceType, Long deviceTypeId,
	    String deviceSerialNo, Long operatorId, Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select count(cs.id), sum(cs.variance), c.commodityName from ScanEntity as cs inner join DcmCommodity c on c.id=cs.commodityId inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1");
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (installationCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:installationCenterId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (deviceType != null) {
	    sb.append(" AND cs.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND cs.deviceTypeId=:deviceTypeId");
	}
	if (commodityId != null) {
	    sb.append(" AND cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}
	if (categoryId != null) {
	    sb.append(" AND cs.commodityCategoryId=:categoryId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	sb.append(" GROUP BY cs.commodityId");
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (installationCenterId != null) {
	    query.setParameter("installationCenterId", installationCenterId);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (categoryId != null) {
	    query.setParameter("categoryId", categoryId);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	List<Object[]> list = query.getResultList();
	return list;
    }

    public List<Object[]> getScanVarianceRateAndCountPerDay(Long regionId, Long commodityId, Long installationCenterId,
	    Long startFrom, Long endTo, Long customerId, Long categoryId, String deviceType, Long deviceTypeId,
	    String deviceSerialNo, Long operatorId, Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select count(cs.id), sum(cs.variance) from ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1");
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (startFrom != null && endTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (installationCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:installationCenterId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (deviceType != null) {
	    sb.append(" AND cs.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND cs.deviceTypeId=:deviceTypeId");
	}
	if (commodityId != null) {
	    sb.append(" AND cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}
	if (categoryId != null) {
	    sb.append(" AND cs.commodityCategoryId=:categoryId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	sb.append(" GROUP BY cs.commodityId");
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (installationCenterId != null) {
	    query.setParameter("installationCenterId", installationCenterId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (startFrom != null) {
	    query.setParameter("from", startFrom);
	}
	if (endTo != null) {
	    query.setParameter("to", endTo);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (categoryId != null) {
	    query.setParameter("categoryId", categoryId);
	}
	List<Object[]> list = query.getResultList();
	return list;
    }

    public List<Long> getScanDatesForAcceptanceByCategory(Long regionId, Long commodityId, Long installationCenterId,
	    Object object, Long dateFrom, Long dateTo, Long customerId, Long categoryId, String deviceType,
	    Long deviceTypeId, String deviceSerialNo, Long operatorId, Long stateAdmin, Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select cs.createdOn from ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId where 1=1");
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND (cs.createdOn between :from AND :to)");
	}
	if (installationCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:installationCenterId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (categoryId != null) {
	    sb.append(" and cs.commodityCategoryId=:categoryId");
	}
	if (deviceType != null) {
	    sb.append(" AND cs.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND cs.deviceTypeId=:deviceTypeId");
	}
	if (commodityId != null) {
	    sb.append(" AND cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (installationCenterId != null) {
	    query.setParameter("installationCenterId", installationCenterId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	// if (categoryId != null) {
	// query.setParameter("categoryId", categoryId);
	// }
	if (categoryId != null) {
	    query.setParameter("categoryId", categoryId);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}

	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	List<Long> list = query.getResultList();
	return list;

    }

    public BigDecimal getTotalQuantityForTheCustomerByCommodityCategory(Long customerId, Long commodityCategoryId,
	    Long dateFrom, Long dateTo, String deviceSerialNo, Long operatorId, Long stateAdmin, Long commodityId,
	    Long stateId) throws IMException {

	StringBuilder sb = new StringBuilder();
	try {
	    sb.append(
		    "SELECT sum(cs.weight) FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	    if (customerId != null) {
		sb.append(" and cs.customerId =:customerId");
	    }

	    if (operatorId != null) {
		sb.append(" and cs.operatorId =:operatorId");
	    }

	    if (commodityCategoryId != null) {
		sb.append(" and cs.commodityCategoryId=:commodityCategoryId");
	    }

	    if (stateId != null) {
		sb.append(" AND sl.state.id=:stateId");
	    }

	    if (commodityId != null) {
		sb.append(" and cs.commodityId=:commodityId");
	    }

	    if (dateFrom != null && dateTo != null) {
		sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	    }
	    if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
		sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	    }
	    if (stateAdmin != null) {
		sb.append(" and cs.stateAdmin =:stateAdmin");
	    }
	    Query query = em.createQuery(sb.toString());

	    if (customerId != null) {
		query.setParameter("customerId", customerId);
	    }
	    if (operatorId != null) {
		query.setParameter("operatorId", operatorId);
	    }
	    if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
		query.setParameter("deviceSerialNo", deviceSerialNo);
	    }

	    if (commodityCategoryId != null) {
		query.setParameter("commodityCategoryId", commodityCategoryId);
	    }

	    if (stateId != null) {
		query.setParameter("stateId", stateId);
	    }

	    if (commodityId != null) {
		query.setParameter("commodityId", commodityId);
	    }

	    if (dateFrom != null) {
		query.setParameter("dateFrom", dateFrom);
	    }

	    if (dateTo != null) {
		query.setParameter("dateTo", dateTo);
	    }

	    if (stateAdmin != null) {
		query.setParameter("stateAdmin", stateAdmin);
	    }

	    BigDecimal sum = Utility.getBigDecimalValue(query.getSingleResult());

	    return sum;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new IMException("SQL Error 12001", "Sql Exception At Landing Page  API");
	}
    }

    public BigDecimal getTotalQuantityForInstCenter(Long dateFrom, Long dateTo, Long id, Long customerId,
	    Long commodityCategoryId, Long operatorId, Long stateAdmin, Long commodityId, Long stateId)
	    throws IMException {

	StringBuilder sb = new StringBuilder();
	try {
	    sb.append(
		    "SELECT SUM(cs.weight) FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	    if (customerId != null) {
		sb.append(" and cs.customerId =:customerId");
	    }

	    if (operatorId != null) {
		sb.append(" and cs.operatorId =:operatorId");
	    }

	    if (commodityCategoryId != null) {
		sb.append(" and cs.commodityCategoryId=:commodityCategoryId");
	    }

	    if (stateId != null) {
		sb.append(" AND sl.state.id=:stateId");
	    }

	    if (commodityId != null) {
		sb.append(" and cs.commodityId=:commodityId");
	    }

	    if (dateFrom != null && dateTo != null) {
		sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	    }

	    if (id != null) {
		sb.append(" and cs.installatonCenterId=:id");
	    }
	    if (stateAdmin != null) {
		sb.append(" and cs.stateAdmin =:stateAdmin");
	    }
	    Query query = em.createQuery(sb.toString());

	    if (customerId != null) {
		query.setParameter("customerId", customerId);
	    }
	    if (operatorId != null) {
		query.setParameter("operatorId", operatorId);
	    }

	    if (commodityCategoryId != null) {
		query.setParameter("commodityCategoryId", commodityCategoryId);
	    }

	    if (stateId != null) {
		query.setParameter("stateId", stateId);
	    }

	    if (commodityId != null) {
		query.setParameter("commodityId", commodityId);
	    }
	    if (dateFrom != null) {
		query.setParameter("dateFrom", dateFrom);
	    }
	    if (dateTo != null) {
		query.setParameter("dateTo", dateTo);
	    }
	    if (id != null) {
		query.setParameter("id", id);
	    }
	    if (stateAdmin != null) {
		query.setParameter("stateAdmin", stateAdmin);
	    }

	    BigDecimal sum = Utility.getBigDecimalValue(query.getSingleResult());

	    return sum;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new IMException("SQL Error 12001", "Sql Exception At Landing Page  API");
	}
    }

    public BigDecimal getDateWiseQuantityModified(Integer pageNumber, Integer limit, Long customerId, Long commodityId,
	    Long instCenterId, Long dateFrom, Long dateTo, Long instCenterTypeId, Long commodityCategoryId,
	    String deviceType, Long deviceTypeId, Long operatorId, Long stateAdmin, Long stateId) throws IMException {
	StringBuilder sb = new StringBuilder();
	try {
	    BigDecimal sum = new BigDecimal(0.0);
	    sb.append(
		    "SELECT sum(cs.weight) FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	    if (customerId != null) {
		sb.append(" and cs.customerId =:customerId");
	    }

	    if (operatorId != null) {
		sb.append(" and cs.operatorId =:operatorId");
	    }

	    if (commodityCategoryId != null) {
		sb.append(" and cs.commodityCategoryId=:commodityCategoryId");
	    }

	    if (deviceTypeId != null) {
		sb.append(" and cs.deviceTypeId=:deviceTypeId");
	    }

	    if (deviceType != null) {
		sb.append(" and cs.deviceType=:deviceType");
	    }

	    if (instCenterId != null) {
		sb.append(" and cs.installatonCenterId=:instCenterId");
	    }

	    if (dateFrom != null && dateTo != null) {
		sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	    }

	    if (commodityId != null) {
		sb.append(" and cs.commodityId=:commodityId");
	    }

	    if (stateId != null) {
		sb.append(" AND sl.state.id=:stateId");
	    }
	    if (stateAdmin != null) {
		sb.append(" and cs.stateAdmin =:stateAdmin");
	    }

	    // sb.append(" group by cs.installatonCenterId ");
	    Query query = em.createQuery(sb.toString());

	    if (customerId != null) {
		query.setParameter("customerId", customerId);
	    }
	    if (operatorId != null) {
		query.setParameter("operatorId", operatorId);
	    }
	    if (commodityCategoryId != null) {
		query.setParameter("commodityCategoryId", commodityCategoryId);
	    }
	    if (deviceTypeId != null) {
		query.setParameter("deviceTypeId", deviceTypeId);
	    }
	    if (deviceType != null) {
		query.setParameter("deviceType", deviceType);
	    }
	    if (instCenterId != null) {
		query.setParameter("instCenterId", instCenterId);
	    }

	    if (dateFrom != null) {
		query.setParameter("dateFrom", dateFrom);
	    }
	    if (dateTo != null) {
		query.setParameter("dateTo", dateTo);
	    }
	    if (commodityId != null) {
		query.setParameter("commodityId", commodityId);
	    }

	    if (stateId != null) {
		query.setParameter("stateId", stateId);
	    }
	    if (stateAdmin != null) {
		query.setParameter("stateAdmin", stateAdmin);
	    }
	    sum = Utility.getBigDecimalValue(query.getSingleResult());
	    if (sum == null) {
		return new BigDecimal(0.0);
	    }
	    return sum;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new IMException("SQL Error 12001", "Sql Exception At Landing Page  API");

	}
    }

    public List<String> getDeviceTypeByFilter(Integer pageNumber, Integer limit, Long customerId, Long commodityId,
	    Long instCenterId, Long dateFrom, Long dateTo, Long instCenterTypeId, Long commodityCategoryId,
	    String deviceType, Long deviceTypeId, Long stateAdmin, Long stateId) throws IMException {
	StringBuilder sb = new StringBuilder();
	try {
	    List<String> deviceTypeList = null;
	    sb.append(
		    "SELECT cs.deviceType FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	    if (customerId != null) {
		sb.append(" and cs.customerId =:customerId");
	    }

	    if (commodityCategoryId != null) {
		sb.append(" and cs.commodityCategoryId=:commodityCategoryId");
	    }

	    if (deviceTypeId != null) {
		sb.append(" and cs.deviceTypeId=:deviceTypeId");
	    }

	    if (deviceType != null) {
		sb.append(" and cs.deviceType=:deviceType");
	    }

	    if (instCenterId != null) {
		sb.append(" and cs.installatonCenterId=:instCenterId");
	    }

	    if (dateFrom != null && dateTo != null) {
		sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	    }

	    if (commodityId != null) {
		sb.append(" and cs.commodityId=:commodityId");
	    }
	    if (stateId != null) {
		sb.append(" AND sl.state.id=:stateId");
	    }
	    if (stateAdmin != null) {
		sb.append(" and cs.stateAdmin =:stateAdmin");
	    }

	    System.out.println(sb.toString());
	    // sb.append(" group by cs.installatonCenterId ");
	    Query query = em.createQuery(sb.toString());

	    if (customerId != null) {
		query.setParameter("customerId", customerId);
	    }
	    if (commodityCategoryId != null) {
		query.setParameter("commodityCategoryId", commodityCategoryId);
	    }
	    if (deviceTypeId != null) {
		query.setParameter("deviceTypeId", deviceTypeId);
	    }
	    if (deviceType != null) {
		query.setParameter("deviceType", deviceType);
	    }
	    if (instCenterId != null) {
		query.setParameter("instCenterId", instCenterId);
	    }

	    if (dateFrom != null) {
		query.setParameter("dateFrom", dateFrom);
	    }
	    if (dateTo != null) {
		query.setParameter("dateTo", dateTo);
	    }
	    if (commodityId != null) {
		query.setParameter("commodityId", commodityId);
	    }

	    if (stateId != null) {
		query.setParameter("stateId", stateId);
	    }

	    if (stateAdmin != null) {
		query.setParameter("stateAdmin", stateAdmin);
	    }
	    deviceTypeList = query.getResultList();

	    return deviceTypeList;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new IMException("SQL Error 12001", "Sql Exception At Landing Page  API");

	}
    }

    public BigDecimal getTotalQuantityForDeviceSerialNumber(Integer pageNumber, Integer limit, Long customerId,
	    Long instCenterId, Long dateFrom, Long dateTo, Long commodityId, String deviceSerialNumber,
	    Long commodityCategoryId, Long operatorId, Long stateAdmin, Long stateId) throws IMException {
	StringBuilder sb = new StringBuilder();
	try {
	    BigDecimal sum = new BigDecimal(0.0);
	    sb.append(
		    "SELECT sum(cs.weight) FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	    if (customerId != null) {
		sb.append(" and cs.customerId =:customerId");
	    }

	    if (operatorId != null) {
		sb.append(" and cs.operatorId =:operatorId");
	    }

	    if (commodityCategoryId != null) {
		sb.append(" and cs.commodityCategoryId=:commodityCategoryId");
	    }

	    if (deviceSerialNumber != null) {
		sb.append(" and cs.deviceSerialNo=:deviceSerialNumber");
	    }

	    if (instCenterId != null) {
		sb.append(" and cs.installatonCenterId=:instCenterId");
	    }

	    if (dateFrom != null && dateTo != null) {
		sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	    }

	    if (commodityId != null) {
		sb.append(" and cs.commodityId=:commodityId");
	    }

	    if (stateId != null) {
		sb.append(" AND sl.state.id=:stateId");
	    }
	    if (stateAdmin != null) {
		sb.append(" and cs.stateAdmin =:stateAdmin");
	    }
	    // sb.append(" group by cs.installatonCenterId ");
	    Query query = em.createQuery(sb.toString());

	    if (customerId != null) {
		query.setParameter("customerId", customerId);
	    }
	    if (operatorId != null) {
		query.setParameter("operatorId", operatorId);
	    }
	    if (commodityCategoryId != null) {
		query.setParameter("commodityCategoryId", commodityCategoryId);
	    }

	    if (deviceSerialNumber != null) {
		query.setParameter("deviceSerialNumber", deviceSerialNumber);
	    }
	    if (instCenterId != null) {
		query.setParameter("instCenterId", instCenterId);
	    }

	    if (dateFrom != null) {
		query.setParameter("dateFrom", dateFrom);
	    }
	    if (dateTo != null) {
		query.setParameter("dateTo", dateTo);
	    }
	    if (commodityId != null) {
		query.setParameter("commodityId", commodityId);
	    }

	    if (stateId != null) {
		query.setParameter("stateId", stateId);
	    }

	    if (stateAdmin != null) {
		query.setParameter("stateAdmin", stateAdmin);
	    }
	    sum = Utility.getBigDecimalValue(query.getSingleResult());

	    return sum;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new IMException("SQL Error 12001", "Sql Exception At Landing Page  API");

	}
    }

    public BigDecimal getDateWiseDeviceTypeData(Integer pageNumber, Integer limit, Long customerId, Long commodityId,
	    Long instCenterId, Long dateFrom, Long dateTo, Long instCenterTypeId, String deviceType,
	    Long commodityCategoryId, String deviceSerialNo, Long operatorId, Long stateAdmin, Long stateId)
	    throws IMException {
	StringBuilder sb = new StringBuilder();
	try {
	    BigDecimal sum = new BigDecimal(0.0);
	    sb.append(
		    "SELECT sum(cs.weight) FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	    if (customerId != null) {
		sb.append(" and cs.customerId =:customerId");
	    }

	    if (operatorId != null) {
		sb.append(" and cs.operatorId =:operatorId");
	    }

	    if (commodityCategoryId != null) {
		sb.append(" and cs.commodityCategoryId=:commodityCategoryId");
	    }

	    if (deviceType != null) {
		sb.append(" and cs.deviceType=:deviceType");
	    }

	    if (instCenterId != null) {
		sb.append(" and cs.installatonCenterId=:instCenterId");
	    }

	    if (dateFrom != null && dateTo != null) {
		sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	    }

	    if (commodityId != null) {
		sb.append(" and cs.commodityId=:commodityId");
	    }

	    if (stateId != null) {
		sb.append(" AND sl.state.id=:stateId");
	    }

	    if (deviceSerialNo != null) {
		sb.append(" and cs.deviceSerialNo=:deviceSerialNo");
	    }
	    if (stateAdmin != null) {
		sb.append(" and cs.stateAdmin =:stateAdmin");
	    }
	    // sb.append(" group by cs.installatonCenterId ");
	    Query query = em.createQuery(sb.toString());

	    if (customerId != null) {
		query.setParameter("customerId", customerId);
	    }

	    if (operatorId != null) {
		query.setParameter("operatorId", operatorId);
	    }

	    if (commodityCategoryId != null) {
		query.setParameter("commodityCategoryId", commodityCategoryId);
	    }

	    if (deviceType != null) {
		query.setParameter("deviceType", deviceType);
	    }
	    if (instCenterId != null) {
		query.setParameter("instCenterId", instCenterId);
	    }

	    if (dateFrom != null) {
		query.setParameter("dateFrom", dateFrom);
	    }
	    if (dateTo != null) {
		query.setParameter("dateTo", dateTo);
	    }
	    if (commodityId != null) {
		query.setParameter("commodityId", commodityId);
	    }

	    if (stateId != null) {
		query.setParameter("stateId", stateId);
	    }
	    if (deviceSerialNo != null) {
		query.setParameter("deviceSerialNo", deviceSerialNo);
	    }
	    if (stateAdmin != null) {
		query.setParameter("stateAdmin", stateAdmin);
	    }

	    sum = Utility.getBigDecimalValue(query.getSingleResult());
	    if (sum == null) {
		return new BigDecimal(0.0);
	    }
	    return sum;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new IMException("SQL Error 12001", "Sql Exception At Landing Page  API");

	}
    }

    public List<Object[]> getCollectionsQuantityPerDayByDeviceType(Long commodityId, Long instCenterId, Long dateFrom,
	    Long dateTo, Long customerId, String deviceType, Long commodityCategoryId, String deviceSerialNumber,
	    Long operatorId, Long stateAdmin, Long stateId) throws IMException {
	StringBuilder sb = new StringBuilder();
	try {
	    List<Object[]> objList = null;
	    sb.append(
		    "SELECT sum(cs.weight),cs.createdOnDate FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where  1=1 ");

	    if (customerId != null) {
		sb.append(" and cs.customerId =:customerId");
	    }

	    if (operatorId != null) {
		sb.append(" and cs.operatorId =:operatorId");
	    }

	    if (commodityCategoryId != null) {
		sb.append(" and cs.commodityCategoryId=:commodityCategoryId");
	    }

	    if (deviceType != null) {
		sb.append(" and cs.deviceType=:deviceType");
	    }
	    if (deviceSerialNumber != null) {
		sb.append(" and cs.deviceSerialNo=:deviceSerialNumber");
	    }
	    if (instCenterId != null) {
		sb.append(" and cs.installatonCenterId=:instCenterId");
	    }

	    if (dateFrom != null && dateTo != null) {
		sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	    }

	    if (commodityId != null) {
		sb.append(" and cs.commodityId=:commodityId");
	    }

	    if (stateId != null) {
		sb.append(" AND sl.state.id=:stateId");
	    }
	    if (stateAdmin != null) {
		sb.append(" and cs.stateAdmin =:stateAdmin");
	    }

	    sb.append(" group by cs.createdOnDate ");
	    Query query = em.createQuery(sb.toString());

	    if (customerId != null) {
		query.setParameter("customerId", customerId);
	    }
	    if (operatorId != null) {
		query.setParameter("operatorId", operatorId);
	    }
	    if (commodityCategoryId != null) {
		query.setParameter("commodityCategoryId", commodityCategoryId);
	    }
	    if (deviceSerialNumber != null) {
		query.setParameter("deviceSerialNumber", deviceSerialNumber);
	    }
	    if (deviceType != null) {
		query.setParameter("deviceType", deviceType);
	    }
	    if (instCenterId != null) {
		query.setParameter("instCenterId", instCenterId);
	    }

	    if (dateFrom != null) {
		query.setParameter("dateFrom", dateFrom);
	    }
	    if (dateTo != null) {
		query.setParameter("dateTo", dateTo);
	    }
	    if (commodityId != null) {
		query.setParameter("commodityId", commodityId);
	    }
	    if (stateId != null) {
		query.setParameter("stateId", stateId);
	    }
	    if (stateAdmin != null) {
		query.setParameter("stateAdmin", stateAdmin);
	    }
	    objList = query.getResultList();

	    return objList;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new IMException("SQL Error 12001", "Sql Exception At Landing Page  API");

	}

    }

    public List<Object[]> getCommodityCollection(Long commodityId, Long installationCenterId, Long dateFrom,
	    Long dateTo, Long customerId, Long categoryId, String deviceType, Long deviceTypeId, String deviceSerialNo,
	    Long operatorId, Long stateAdmin, Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select sum(cs.weight),c.commodityName,cs.quantityUnit, cs.commodityId from ScanEntity as cs inner join DcmCommodity c on c.id=cs.commodityId inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId ");
	}

	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	if (installationCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:installationCenterId");
	}
	if (deviceType != null) {
	    sb.append(" AND cs.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND cs.deviceTypeId=:deviceTypeId");
	}
	if (commodityId != null) {
	    sb.append(" AND cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}
	if (categoryId != null) {
	    sb.append(" AND cs.commodityCategoryId=:categoryId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}
	sb.append(" GROUP BY cs.commodityId");
	Query query = em.createQuery(sb.toString());
	if (installationCenterId != null) {
	    query.setParameter("installationCenterId", installationCenterId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (categoryId != null) {
	    query.setParameter("categoryId", categoryId);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	List<Object[]> list = query.getResultList();
	return list;
    }

    public List<Object[]> getCollectionsQuantityPerDay(Long commodityId, Long instCenterId, Long dateFrom, Long dateTo,
	    Long customerId, String deviceType, Long deviceTypeId, String deviceSerialNo, Long userId, Long operatorId,
	    Long stateAdmin, Long stateId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"SELECT sum(cs.weight),cs.createdOnDate,cs.createdOn,cs.quantityUnit FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	if (customerId != null) {
	    sb.append(" and cs.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (deviceTypeId != null) {
	    sb.append(" and cs.deviceTypeId=:deviceTypeId");
	}

	if (deviceType != null) {
	    sb.append(" and cs.deviceType=:deviceType");
	}

	if (userId != null) {
	    sb.append(" and cs.userId=:userId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	}

	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}

	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}

	if (instCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:instCenterId");
	}

	if (deviceSerialNo != null) {
	    sb.append(" and cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}

	sb.append(" group by cs.createdOnDate ");
	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (userId != null) {
	    query.setParameter("userId", userId);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}
	if (deviceSerialNo != null) {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	List<Object[]> list = query.getResultList();

	return list;
    }

    public Object[] getTotalQuantityForInstCenterByCommodityCategory(Long customerId, Long commodityCategoryId,
	    Long dateFrom, Long dateTo, Long instCenterId, String deviceSerialNo, Long operatorId, Long commodityId,
	    Long stateId) {

	StringBuilder sb = new StringBuilder();
	Object[] obj = null;
	sb.append(
		"SELECT sum(cs.weight),cs.quantityUnit FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	if (customerId != null) {
	    sb.append(" and cs.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	}

	if (commodityCategoryId != null) {
	    sb.append(" and cs.commodityCategoryId=:commodityCategoryId");
	}
	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}

	if (instCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:instCenterId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	// sb.append(" group by cs.createdOnDate ");
	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}
	if (commodityCategoryId != null) {
	    query.setParameter("commodityCategoryId", commodityCategoryId);
	}
	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}
	obj = (Object[]) query.getSingleResult();

	return obj;

    }

    public List<Long> getCommodityIdsByInstCenter(Integer pageNumber, Integer limit, Long customerId, Long dateFrom,
	    Long dateTo, Long commodityCategoryId, Long instCenterId, String deviceType, Long deviceTypeId,
	    Long instCenterTypeId, String deviceSerialNo, Long operatorId, Long commodityId, Long stateId) {
	StringBuilder sb = new StringBuilder();
	List<Long> obj = null;
	sb.append(
		"SELECT cs.commodityId FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	if (customerId != null) {
	    sb.append(" and cs.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (deviceTypeId != null) {
	    sb.append(" and cs.deviceTypeId=:deviceTypeId");
	}

	if (deviceType != null) {
	    sb.append(" and cs.deviceType=:deviceType");
	}
	if (instCenterTypeId != null) {
	    sb.append(" and cs.instCenterTypeId=:instCenterTypeId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	}

	if (commodityCategoryId != null) {
	    sb.append(" and cs.commodityCategoryId=:commodityCategoryId");
	}

	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}

	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}

	if (instCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:instCenterId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	// sb.append(" group by cs.createdOnDate ");
	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (instCenterTypeId != null) {
	    query.setParameter("instCenterTypeId", instCenterTypeId);
	}

	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}
	if (commodityCategoryId != null) {
	    query.setParameter("commodityCategoryId", commodityCategoryId);
	}
	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}
	obj = query.getResultList();

	return obj;
    }

    public Object[] getCommodityDataByCommodityId(Long commodityId, Long instCenterId, Long dateFrom, Long dateTo,
	    Long customerId, Long commodityCategoryId, String deviceSerialNo, Long operatorId, Long stateId)
	    throws IMException {
	StringBuilder sb = new StringBuilder();
	try {
	    Object[] sum = null;
	    sb.append(
		    "SELECT sum(cs.weight),cs.commodityName,cs.quantityUnit FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	    if (customerId != null) {
		sb.append(" and cs.customerId =:customerId");
	    }

	    if (operatorId != null) {
		sb.append(" and cs.operatorId =:operatorId");
	    }

	    if (commodityCategoryId != null) {
		sb.append(" and cs.commodityCategoryId=:commodityCategoryId");
	    }
	    if (commodityId != null) {
		sb.append(" and cs.commodityId=:commodityId");
	    }

	    if (stateId != null) {
		sb.append(" AND sl.state.id=:stateId");
	    }

	    if (instCenterId != null) {
		sb.append(" and cs.installatonCenterId=:instCenterId");
	    }

	    if (dateFrom != null && dateTo != null) {
		sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	    }
	    if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
		sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	    }
	    // sb.append(" group by cs.createdOnDate ");
	    Query query = em.createQuery(sb.toString());

	    if (customerId != null) {
		query.setParameter("customerId", customerId);
	    }
	    if (operatorId != null) {
		query.setParameter("operatorId", operatorId);
	    }
	    if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
		query.setParameter("deviceSerialNo", deviceSerialNo);
	    }
	    if (commodityCategoryId != null) {
		query.setParameter("commodityCategoryId", commodityCategoryId);
	    }
	    if (commodityId != null) {
		query.setParameter("commodityId", commodityId);
	    }
	    if (stateId != null) {
		query.setParameter("stateId", stateId);
	    }
	    if (instCenterId != null) {
		query.setParameter("instCenterId", instCenterId);
	    }

	    if (dateFrom != null) {
		query.setParameter("dateFrom", dateFrom);
	    }
	    if (dateTo != null) {
		query.setParameter("dateTo", dateTo);
	    }
	    sum = (Object[]) query.getSingleResult();

	    return sum;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new IMException("SQL Error 12001", "Sql Exception At Landing Page  API");

	}
    }

    public BigDecimal getDateWiseQuantityNew(Long commodityId, Long instCenterId, Long dateFrom, Long dateTo,
	    Long farmerId, Long customerId, String deviceType, Long deviceTypeId, Long operatorId) throws IMException {
	try {
	    StringBuilder sb = new StringBuilder();
	    BigDecimal sum = new BigDecimal(0.0);
	    sb.append("SELECT sum(cs.weight) FROM ScanEntity as cs  where 1=1 ");

	    if (customerId != null) {
		sb.append(" and cs.customerId =:customerId");
	    }

	    if (operatorId != null) {
		sb.append(" and cs.operatorId =:operatorId");
	    }

	    if (deviceTypeId != null) {
		sb.append(" and cs.deviceTypeId=:deviceTypeId");
	    }

	    if (deviceType != null) {
		sb.append(" and cs.deviceType=:deviceType");
	    }

	    if (dateFrom != null && dateTo != null) {
		sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	    }

	    if (commodityId != null) {
		sb.append(" and cs.commodityId=:commodityId");
	    }

	    if (instCenterId != null) {
		sb.append(" and cs.installatonCenterId=:instCenterId");
	    }
	    if (farmerId != null) {
		sb.append(" and cs.farmerId=:farmerId");
	    }

	    Query query = em.createQuery(sb.toString());

	    if (customerId != null) {
		query.setParameter("customerId", customerId);
	    }

	    if (operatorId != null) {
		query.setParameter("operatorId", operatorId);
	    }

	    if (deviceTypeId != null) {
		query.setParameter("deviceTypeId", deviceTypeId);
	    }
	    if (deviceType != null) {
		query.setParameter("deviceType", deviceType);
	    }

	    if (dateFrom != null) {
		query.setParameter("dateFrom", dateFrom);
	    }
	    if (dateTo != null) {
		query.setParameter("dateTo", dateTo);
	    }
	    if (commodityId != null) {
		query.setParameter("commodityId", commodityId);
	    }
	    if (instCenterId != null) {
		query.setParameter("instCenterId", instCenterId);
	    }
	    if (farmerId != null) {
		query.setParameter("farmerId", farmerId);
	    }
	    sum = Utility.getBigDecimalValue(query.getSingleResult());
	    if (sum == null) {
		return new BigDecimal(0.0);
	    }

	    return sum;
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new IMException("SQL Error 12001", "Sql Exception At Scan Details By Id  API");
	}

    }

    public List<Long> getDatesForFarmer(Long commodityId, Long instCenterId, Long dateFrom, Long dateTo, Long farmerId,
	    Long customerId, String deviceType, Long deviceTypeId, Long operatorId) {

	StringBuilder sb = new StringBuilder();
	sb.append("SELECT createdOn FROM ScanEntity as cs  where 1=1 ");

	if (customerId != null) {
	    sb.append(" and cs.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (deviceTypeId != null) {
	    sb.append(" and cs.deviceTypeId=:deviceTypeId");
	}

	if (deviceType != null) {
	    sb.append(" and cs.deviceType=:deviceType");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	}

	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}

	if (instCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:instCenterId");
	}
	sb.append(" group by cs.createdOnDate ");
	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}

	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}

	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}

	List<Long> list = query.getResultList();

	return list;
    }

    public BigDecimal getCollectionsQuantityPerDayByFarmerId(Long commodityId, Long instCenterId, Long dateFrom,
	    Long dateTo, Long farmerId, Long customerId, String deviceType, Long deviceTypeId, Long operatorId) {
	StringBuilder sb = new StringBuilder();
	BigDecimal sum = new BigDecimal(0.0);
	sb.append("SELECT sum(cs.weight) FROM ScanEntity as cs  where 1=1 ");

	if (customerId != null) {
	    sb.append(" and cs.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (deviceTypeId != null) {
	    sb.append(" and cs.deviceTypeId=:deviceTypeId");
	}

	if (deviceType != null) {
	    sb.append(" and cs.deviceType=:deviceType");
	}
	if (farmerId != null) {
	    sb.append(" and cs.farmerId=:farmerId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	}

	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}

	if (instCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:instCenterId");
	}
	// sb.append(" group by cs.createdOnDate ");
	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (farmerId != null) {
	    query.setParameter("farmerId", farmerId);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (instCenterId != null) {
	    query.setParameter("instCenterId", instCenterId);
	}
	sum = Utility.getBigDecimalValue(query.getSingleResult());

	return sum;
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> deviceKeywordFilter(String keyword, Integer pageNumber, Integer limit, Long customerId,
	    String operationType) throws Exception {
	//and d.dcmStatus.id NOT IN('5')
	try {
	    StringBuilder sb = new StringBuilder();

	    if (operationType != null && operationType.equalsIgnoreCase(Constants.INVENTORY)) {
		sb.append(
			"SELECT  distinct d.id, d.serialNumber,d.customerId,d.dcmDeviceType.id,d.dcmDeviceType.deviceTypeDesc,"
				+ "d.startOfLife,d.endOfLife,d.startOfService,d.endOfService,d.fwRevision,d.hwRevision,"
				+ "d.dcmStatus.id,d.dcmStatus.statusDesc,d.createdOn FROM DcmDevice d  WHERE 1=1 ");
	    } else {

		sb.append(
			"SELECT  distinct d.id, d.serialNumber,d.customerId,d.dcmDeviceType.id,d.dcmDeviceType.deviceTypeDesc,"

				+ "d.startOfLife,d.endOfLife,d.startOfService,d.endOfService,d.fwRevision,d.hwRevision,"
				+ "d.dcmStatus.id,d.dcmStatus.statusDesc,d.createdOn FROM DcmDevice d  WHERE 1=1 ");
	    }
	    if (operationType != null && operationType.equalsIgnoreCase(Constants.INVENTORY)) {
		sb.append(" and d.dcmStatus.id NOT IN(" + Constants.STATUS.DELETED.getId() + ")");
	    } else {
		sb.append(" and d.dcmStatus.id IN(" + Constants.STATUS.ACTIVE.getId() + ")");
	    }

	    if (customerId != null) {
		sb.append(" AND d.customerId IN (:customerId) ");
	    }
	    if (keyword != null && !keyword.isEmpty()) {
		// sb.append(" AND (LOWER(d.serialNumber) like LOWER(:keyword) OR
		// LOWER(d.dcmDeviceType.deviceTypeDesc) like LOWER(:keyword) OR
		// LOWER(d.hwRevision) like LOWER(:keyword) OR LOWER(d.fwRevision) like
		// LOWER(:keyword) OR LOWER(d.deviceSubType.deviceSubTypeDesc) like
		// LOWER(:keyword) OR LOWER(d.deviceSubType.deviceSubTypeCode) like
		// LOWER(:keyword))");
		sb.append(
			" AND (LOWER(d.serialNumber) like LOWER(:keyword) OR LOWER(d.dcmDeviceType.deviceTypeDesc) like LOWER(:keyword) OR LOWER(d.hwRevision) like LOWER(:keyword) OR LOWER(d.fwRevision) like LOWER(:keyword))");
	    }

	    sb.append(" order by d.id desc");

	    System.out.println(sb.toString());

	    Query query = em.createQuery(sb.toString());
	    if (customerId != null) {
		query.setParameter("customerId", customerId);
	    }

	    if (keyword != null && !keyword.isEmpty()) {
		query.setParameter("keyword", "%" + keyword.trim() + "%");
	    }
	    if (pageNumber != null && limit != null) {
		query.setFirstResult(pageNumber * limit);
		query.setMaxResults(limit);
	    }

	    return query.getResultList();
	} catch (Exception e) {
	    System.out.print("Exception : " + e);
	    throw new Exception(e);
	}
    }

    @SuppressWarnings("unchecked")
    public Long deviceKeywordFilterCount(String keyword, Long customerId, String operationType) throws Exception {
	//and d.dcmStatus.id NOT IN('5')
	try {
	    Long count = null;
	    StringBuilder sb = new StringBuilder();

	    if (operationType != null && operationType.equalsIgnoreCase(Constants.INVENTORY)) {
		sb.append("SELECT  Count(d.id) FROM DcmDevice d  WHERE 1=1 ");
	    } else {

		sb.append("SELECT  Count( d.id) FROM DcmDevice d  WHERE 1=1 ");
	    }
	    if (operationType != null && operationType.equalsIgnoreCase(Constants.INVENTORY)) {
		sb.append(" and d.dcmStatus.id  NOT IN(" + Constants.STATUS.DELETED.getId() + ")");
	    } else {
		sb.append(" and d.dcmStatus.id IN(" + Constants.STATUS.ACTIVE.getId() + ")");
	    }

	    if (customerId != null) {
		sb.append(" AND d.customerId IN (:customerId) ");
	    }
	    if (keyword != null && !keyword.isEmpty()) {
		sb.append(
			" AND (LOWER(d.serialNumber) like LOWER(:keyword) OR LOWER(d.dcmDeviceType.deviceTypeDesc) like LOWER(:keyword) OR LOWER(d.hwRevision) like LOWER(:keyword) OR LOWER(d.fwRevision) like LOWER(:keyword) OR LOWER(d.deviceSubType.deviceSubTypeDesc) like LOWER(:keyword) OR LOWER(d.deviceSubType.deviceSubTypeCode) like LOWER(:keyword))");
	    }

	    System.out.println(sb.toString());

	    Query query = em.createQuery(sb.toString());
	    if (customerId != null) {
		query.setParameter("customerId", customerId);
	    }

	    if (keyword != null && !keyword.isEmpty()) {
		query.setParameter("keyword", "%" + keyword.trim() + "%");
	    }

	    return (Long) query.getSingleResult();
	} catch (Exception e) {
	    System.out.print("Exception : " + e);
	    throw new Exception(e);
	}
    }

    public Long scanCountByQualityGrade(Long commodityId, Double max, Double min, String analysisCode, Long customerId,
	    Long operatorId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select count(cs.id)  from ScanEntity as cs inner join ScanResultEntity as ar on cs.id=ar.scanEntity.id where cs.commodityId=:commodityId and ar.result BETWEEN :max AND :min");
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}

	if (analysisCode != null) {
	    sb.append(" and ar.analysisName=:analysisCode");
	}
	Query query = em.createQuery(sb.toString());
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (analysisCode != null) {
	    query.setParameter("analysisCode", analysisCode);
	}

	if (max != null) {
	    query.setParameter("max", max);
	}
	if (min != null) {
	    query.setParameter("min", min);
	}

	return (Long) query.getSingleResult();
    }

    public List<Long> getAnalytics(Long commodityId, Long customerId, String deviceType, Long deviceTypeId,
	    Long operatorId) {
	StringBuilder sb = new StringBuilder();
	sb.append("select s.id from ScanEntity s where 1=1 ");
	if (customerId != null) {
	    sb.append(" AND s.customerId =:customerId");
	}

	if (operatorId != null) {
	    sb.append(" and s.operatorId =:operatorId");
	}

	if (deviceTypeId != null) {
	    sb.append(" and s.deviceTypeId=:deviceTypeId");
	}

	if (deviceType != null) {
	    sb.append(" and s.deviceType=:deviceType");
	}

	if (commodityId != null) {
	    sb.append(" and s.commodityId=:commodityId");
	}

	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}

	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	List<Long> list = query.getResultList();

	return list;
    }

    public List<DcmDeviceOrder> findCustomerOrders(Long customerId, Integer pageNumber, Integer limit,
	    String searchKeyword) {

	StringBuilder sb = new StringBuilder();
	sb.append("Select do from DcmDeviceOrder do where 1=1 ");

	if (customerId != null) {
	    sb.append(" AND do.customerId IN (:customerId) ");
	}
	if (searchKeyword != null && !searchKeyword.isEmpty()) {
	    sb.append(
		    " AND do.mode like :searchKeyword OR do.dcmDeviceType.deviceTypeDesc like :searchKeyword OR do.remarks like :searchKeyword OR do.dcmStatus.statusDesc like :searchKeyword ");
	}

	sb.append(" order by do.id desc ");

	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (searchKeyword != null && !searchKeyword.isEmpty()) {
	    query.setParameter("searchKeyword", "%" + searchKeyword + "%");
	}

	if (pageNumber == null && limit == null) {
	    pageNumber = 0;
	    limit = 10;
	    query.setFirstResult(pageNumber * limit);
	    query.setMaxResults(limit);
	} else {
	    query.setFirstResult(pageNumber * limit);
	    query.setMaxResults(limit);
	}

	return query.getResultList();
    }

    public Long findCustomerOrdersCount(Long customerId, String searchKeyword) {

	StringBuilder sb = new StringBuilder();
	sb.append("Select count(*) from DcmDeviceOrder do where 1=1 ");

	if (customerId != null) {
	    sb.append(" AND do.customerId IN (:customerId) ");
	}
	if (searchKeyword != null && !searchKeyword.isEmpty()) {
	    sb.append(
		    " AND do.mode like :searchKeyword OR do.dcmDeviceType.deviceTypeDesc like :searchKeyword OR do.remarks like :searchKeyword OR do.dcmStatus.statusDesc like :searchKeyword ");
	}

	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (searchKeyword != null && !searchKeyword.isEmpty()) {
	    query.setParameter("searchKeyword", "%" + searchKeyword + "%");
	}

	return (Long) query.getSingleResult();
    }

    public List<ScanLocation> getAllCommercialLocationByFilter(String keySearch, Long customerId, Integer pageNumber,
	    Integer limit, Long regionId, Long installationCenterTypeId, List<Long> instCenterIds) {
	StringBuilder sb = new StringBuilder();
	sb.append("select cl from ScanLocation cl  WHERE cl.status.id=" + Constants.STATUS.ACTIVE.getId());

	//		if (customerId != null) {
	//		    sb.append(" AND cl.customerId IN (:customerId) ");
	//		}
	//		if (regionId != null) {
	//		    sb.append(" AND cl.dcmRegion.id IN (:regionId) ");
	//		}

	//		if (installationCenterTypeId != null) {
	//		    sb.append(" AND cl.dcmCommercialLocationType.id IN (:installationCenterTypeId) ");
	//		}
	if (instCenterIds != null && instCenterIds.size() != 0) {
	    sb.append(" AND cl.id IN (:instCenterIds) ");
	}

	if (keySearch != null && !keySearch.isEmpty()) {
	    sb.append(" AND  LOWER(cl.locationName) like LOWER(:keySearch) ");

	}

	sb.append(" order by cl.id desc");

	System.out.println(" Query :  " + sb.toString());

	Query query = em.createQuery(sb.toString());

	//		if (customerId != null) {
	//		    query.setParameter("customerId", customerId);
	//		}

	//		if (installationCenterTypeId != null) {
	//		    query.setParameter("installationCenterTypeId", installationCenterTypeId);
	//		}

	if (keySearch != null && !keySearch.isEmpty()) {
	    query.setParameter("keySearch", "%" + keySearch + "%");
	}

	if (instCenterIds != null && instCenterIds.size() != 0) {
	    query.setParameter("instCenterIds", instCenterIds);
	}

	if (pageNumber == null && limit == null) {
	    pageNumber = 0;
	    limit = 10;
	    query.setFirstResult(pageNumber * limit);
	    query.setMaxResults(limit);
	} else {
	    query.setFirstResult(pageNumber * limit);
	    query.setMaxResults(limit);
	}

	return query.getResultList();
    }

    public Long getAllCommercialLocationByFilterCount(String keySearch, Long customerId, Long regionId) {
	StringBuilder sb = new StringBuilder();
	sb.append("select count(cl) from ScanLocation cl  WHERE  cl.status.id=" + Constants.STATUS.ACTIVE.getId());

	//		if (customerId != null) {
	//		    sb.append(" AND cl.customerId IN (:customerId) ");
	//		}
	//		if (regionId != null) {
	//		    sb.append(" AND cl.dcmRegion.id IN (:regionId) ");
	//		}

	if (keySearch != null && !keySearch.isEmpty()) {
	    sb.append(" AND  (LOWER(cl.instCenterName) like LOWER(:keySearch)  ");

	}

	sb.append(" order by cl.id desc");

	System.out.println("Total Count : " + sb.toString());

	Query query = em.createQuery(sb.toString());
	//
	//		if (customerId != null) {
	//		    query.setParameter("customerId", customerId);
	//		}
	//		if (regionId != null) {
	//		    query.setParameter("regionId", regionId);
	//		}

	if (keySearch != null && !keySearch.isEmpty()) {
	    query.setParameter("keySearch", "%" + keySearch + "%");
	}

	return (Long) query.getSingleResult();
    }

    public List<Long> getLocationIds(Long customerId, Long categoryId, Long dateFrom, Long dateTo, Long operatorId,
	    Long stateAdmin, Long commodityId, Long stateId) {

	StringBuilder sb = new StringBuilder();
	sb.append(
		"SELECT distinct cs.installatonCenterId FROM ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId  where 1=1 ");

	if (customerId != null) {
	    sb.append(" and cs.customerId =:customerId");
	}

	if (categoryId != null) {
	    sb.append(" and cs.commodityCategoryId=:categoryId");
	}

	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :dateFrom AND :dateTo");
	}

	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}

	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}

	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}

	Query query = em.createQuery(sb.toString());

	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}

	if (categoryId != null) {
	    query.setParameter("categoryId", categoryId);
	}

	if (dateFrom != null) {
	    query.setParameter("dateFrom", dateFrom);
	}

	if (dateTo != null) {
	    query.setParameter("dateTo", dateTo);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}

	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}

	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	List<Long> instCenterIds = query.getResultList();

	return instCenterIds;
    }

    public List<Object[]> getPlotInstAndAvgByPlot(Long commodityId, String analysisName, Long dateFrom, Long dateTo,
	    Long customerId, Long operatorId) {
	StringBuilder sb = new StringBuilder();
	sb.append(
		"select avg(ar.result), cs.plotId, i.locationName from ScanEntity as cs inner join ScanResultEntity as ar on cs.id=ar.scanEntity.id left join ScanLocation i on i.id=cs.installatonCenterId where cs.commodityId =:commodityId AND cs.customerId=:customerId AND ar.analysisName =:analysisName");
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND cs.createdOn between :from AND :to");
	}
	sb.append(" GROUP BY cs.plotId");
	Query query = em.createQuery(sb.toString());

	if (analysisName != null) {
	    query.setParameter("analysisName", analysisName);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	List<Object[]> list = query.getResultList();
	return list;
    }

    public List<ClientsAnalyticsRange> getFilteredRange(Long clientId, String warehosueName, Long commodityId) {

	StringBuilder sb = new StringBuilder();
	sb.append("select car from ClientsAnalyticsRange car where 1=1 ");
	if (clientId != null) {
	    sb.append(" And car.client.customerId =: clientId");
	}
	if (warehosueName != null && !warehosueName.isEmpty()) {
	    sb.append(" AND car.warehouseName =: warehosueName");
	}
	if (commodityId != null) {
	    sb.append(" AND car.commodityId.id =: commodityId");
	}

	Query query = em.createQuery(sb.toString());

	if (clientId != null) {
	    query.setParameter("clientId", clientId);
	}
	if (warehosueName != null) {
	    query.setParameter("warehosueName", warehosueName);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	List<ClientsAnalyticsRange> list = query.getResultList();
	return list;
    }

    public Long getFilteredRangeCount(Long clientId, String warehosueName, Long commodityId) {
	StringBuilder sb = new StringBuilder();
	sb.append("select count(car.id) from ClientsAnalyticsRange car where 1=1 ");
	if (clientId != null) {
	    sb.append(" And car.client.customerId =: clientId");
	}
	if (warehosueName != null && !warehosueName.isEmpty()) {
	    sb.append(" AND car.warehouseName =: warehosueName");
	}
	if (commodityId != null) {
	    sb.append(" AND car.commodityId.id =: commodityId");
	}

	Query query = em.createQuery(sb.toString());

	if (clientId != null) {
	    query.setParameter("clientId", clientId);
	}
	if (warehosueName != null) {
	    query.setParameter("warehosueName", warehosueName);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	Long count = (Long) query.getSingleResult();
	return count;
    }

    public List<Object[]> getClientsByCommodity(Long commodityId) {

	StringBuilder sb = new StringBuilder();
	sb.append(
		"select distinct ar.customerId,ar.customerName from ScanEntity as cs inner join CustomerEntity as ar on cs.customerId=ar.customerId where 1=1");
	if (commodityId != null) {
	    sb.append(" and cs.commodityId =:commodityId");
	}

	Query query = em.createQuery(sb.toString());

	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	List<Object[]> list = query.getResultList();
	return list;
    }

    //	public Long getFilteredDataByCommodityCount(Long commodityId) {
    //		StringBuilder sb = new StringBuilder();
    //		sb.append(
    //				"select count(cs.id) from ScanEntity as cs inner join CustomerEntity as ar on cs.id=ar.customerId left join ScanLocation i on i.id=cs.installatonCenterId where 1=1");
    //		if(commodityId != null){
    //		    sb.append(" and cs.commodityId =:commodityId");
    //		}
    //		
    //		Query query = em.createQuery(sb.toString());
    //
    //		if (commodityId != null) {
    //			query.setParameter("commodityId", commodityId);
    //		}
    //		Long count = (Long) query.getSingleResult();
    //		return count;
    //	}

    public List<Object[]> getLocations(Long clientId) {

	StringBuilder sb = new StringBuilder();
	sb.append(
		"select distinct ar.id,ar.locationName,ar.warehouseName from ScanEntity as cs inner join ScanLocation as ar on cs.installatonCenterId=ar.id where 1=1");
	if (clientId != null) {
	    sb.append(" and cs.customerId =:clientId");
	}

	Query query = em.createQuery(sb.toString());

	if (clientId != null) {
	    query.setParameter("clientId", clientId);
	}
	List<Object[]> list = query.getResultList();
	return list;
    }

    public List<ScanEntity> findAllByFilters(String deviceSerialNumber, Long commodityId, Integer pageNumber,
	    Integer limit) {

	StringBuilder sb = new StringBuilder();
	sb.append(
		"SELECT Distinct cs FROM ScanEntity as cs inner join ScanResultEntity rs on cs.id=rs.scanEntity.id where rs.labResult is not null  ");

	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}

	if (deviceSerialNumber != null) {
	    sb.append(" and cs.deviceSerialNo=:deviceSerialNumber");
	}

	Query query = em.createQuery(sb.toString());

	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	if (deviceSerialNumber != null) {
	    query.setParameter("deviceSerialNumber", deviceSerialNumber);
	}
	if (pageNumber != null && limit != null) {
	    query.setFirstResult(pageNumber * limit);
	    query.setMaxResults(limit);
	}
	List<ScanEntity> scanEntity = query.getResultList();

	return scanEntity;
    }

    public Long findLabCount(String deviceSerialNumber, Long commodityId) {

	StringBuilder sb = new StringBuilder();
	sb.append(
		"SELECT count(cs.id) FROM ScanEntity as cs inner join ScanResultEntity rs on cs.id=rs.scanEntity.id where rs.labResult is not null ");

	if (commodityId != null) {
	    sb.append(" and cs.commodityId=:commodityId");
	}

	if (deviceSerialNumber != null) {
	    sb.append(" and cs.deviceSerialNo=:deviceSerialNumber");
	}

	Query query = em.createQuery(sb.toString());

	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}

	if (deviceSerialNumber != null) {
	    query.setParameter("deviceSerialNumber", deviceSerialNumber);
	}

	Long count = (Long) query.getSingleResult();

	return count;
    }

    public Long getAllScanCount(Long regionId, Long commodityId, Long installationCenterId, Object object,
	    Long dateFrom, Long dateTo, Long customerId, Long categoryId, String deviceType, Long deviceTypeId,
	    String deviceSerialNo, Long operatorId, Long stateAdmin, Long stateId) {

	StringBuilder sb = new StringBuilder();
	sb.append(
		"select count(cs.id) from ScanEntity as cs inner join ScanLocation sl on sl.id=cs.installatonCenterId where 1=1");
	if (customerId != null) {
	    sb.append(" AND cs.customerId =:customerId");
	}
	if (operatorId != null) {
	    sb.append(" and cs.operatorId =:operatorId");
	}
	if (dateFrom != null && dateTo != null) {
	    sb.append(" AND (cs.createdOn between :from AND :to)");
	}
	if (installationCenterId != null) {
	    sb.append(" and cs.installatonCenterId=:installationCenterId");
	}
	if (regionId != null) {
	    sb.append(" and cs.regionId=:regionId");
	}
	if (categoryId != null) {
	    sb.append(" and cs.commodityCategoryId=:categoryId");
	}
	if (deviceType != null) {
	    sb.append(" AND cs.deviceType=:deviceType");
	}
	if (deviceTypeId != null) {
	    sb.append(" AND cs.deviceTypeId=:deviceTypeId");
	}
	if (commodityId != null) {
	    sb.append(" AND cs.commodityId=:commodityId");
	}
	if (stateId != null) {
	    sb.append(" AND sl.state.id=:stateId");
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    sb.append(" AND cs.deviceSerialNo=:deviceSerialNo");
	}
	if (stateAdmin != null) {
	    sb.append(" and cs.stateAdmin =:stateAdmin");
	}
	Query query = em.createQuery(sb.toString());
	if (regionId != null) {
	    query.setParameter("regionId", regionId);
	}
	if (operatorId != null) {
	    query.setParameter("operatorId", operatorId);
	}
	if (installationCenterId != null) {
	    query.setParameter("installationCenterId", installationCenterId);
	}
	if (deviceType != null) {
	    query.setParameter("deviceType", deviceType);
	}
	if (deviceTypeId != null) {
	    query.setParameter("deviceTypeId", deviceTypeId);
	}
	if (dateFrom != null) {
	    query.setParameter("from", dateFrom);
	}
	if (dateTo != null) {
	    query.setParameter("to", dateTo);
	}
	// if (categoryId != null) {
	// query.setParameter("categoryId", categoryId);
	// }
	if (categoryId != null) {
	    query.setParameter("categoryId", categoryId);
	}
	if (customerId != null) {
	    query.setParameter("customerId", customerId);
	}
	if (commodityId != null) {
	    query.setParameter("commodityId", commodityId);
	}
	if (stateId != null) {
	    query.setParameter("stateId", stateId);
	}
	if (deviceSerialNo != null && deviceSerialNo.trim() != "") {
	    query.setParameter("deviceSerialNo", deviceSerialNo);
	}
	if (stateAdmin != null) {
	    query.setParameter("stateAdmin", stateAdmin);
	}
	Long count = (Long) query.getSingleResult();
	return count;
    }
}
