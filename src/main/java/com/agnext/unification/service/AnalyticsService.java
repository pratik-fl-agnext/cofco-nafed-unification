package com.agnext.unification.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.CustomerEntity;
import com.agnext.unification.entity.nafed.ScanEntity;
import com.agnext.unification.entity.nafed.ScanLocation;
import com.agnext.unification.entity.nafed.UserAnalyticLink;
import com.agnext.unification.entity.nafed.UserEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.AnalyticsAvgMinMaxModel;
import com.agnext.unification.model.AnalyticsCollectionModel;
import com.agnext.unification.model.ClientAvgMinMaxModel;
import com.agnext.unification.model.ClientDetailModel;
import com.agnext.unification.model.CollectionDetailsModel;
import com.agnext.unification.model.CollectionDetailsUniqueModel;
import com.agnext.unification.model.CollectionModel;
import com.agnext.unification.model.CommodityAnalyticModel;
import com.agnext.unification.model.CommodityCollectionModel;
import com.agnext.unification.model.CommodityDeviceDropDownModel;
import com.agnext.unification.model.CommodityNewModel;
import com.agnext.unification.model.DateComparator;
import com.agnext.unification.model.DeviceSerialNumber;
import com.agnext.unification.model.DeviceTypeDataModel;
import com.agnext.unification.model.FarmerDetailsModel;
import com.agnext.unification.model.GraphDataModel;
import com.agnext.unification.model.InstallationCenterDetails;
import com.agnext.unification.model.ScanCountModel;
import com.agnext.unification.model.ScanModel;
import com.agnext.unification.model.TotalCollectionModel;
import com.agnext.unification.model.TotalCollectionsModel;
import com.agnext.unification.model.UserLinkModel;
import com.agnext.unification.model.WarehouseData;
import com.agnext.unification.model.WarehouseDetailsModel;
import com.agnext.unification.repository.nafed.CustomerRepository;
import com.agnext.unification.repository.nafed.DcmCommodityRepository;
import com.agnext.unification.repository.nafed.DeviceRepository;
import com.agnext.unification.repository.nafed.FilterRepository;
import com.agnext.unification.repository.nafed.ScanLocationRepository;
import com.agnext.unification.repository.nafed.ScanResultEntityRepository;
import com.agnext.unification.repository.nafed.ScmScanRepository;
import com.agnext.unification.repository.nafed.UserAnalyticLinkRepository;
import com.agnext.unification.repository.nafed.UserRepository;
import com.agnext.unification.utility.Utility;

@Service
public class AnalyticsService extends GenericService {

    private static Logger logger = LoggerFactory.getLogger(AnalyticsService.class);

    @Autowired
    FilterRepository filterRepo;

    @Autowired
    ScanLocationRepository scanLocationRepo;

    @Autowired
    DeviceRepository deviceRepo;
    @Autowired
    ScanResultEntityRepository analyticResultRepo;

    @Autowired
    CustomerRepository customerRepo;
    //	@Autowired
    //    ScanLocationRepository locationRepo;

    @Autowired
    ScmScanRepository scanRepo;

    @Autowired
    DcmCommodityRepository commodityRepository;
    
    @Autowired
    UserRepository userRepo;

    public Long setOperatorId() {
	Long operatorId = null;
	logger.info(" Inside setUserId method ");
	System.out.println(" Request Context : " + applicationContext.getRequestContext());

	Set<String> roles = applicationContext.getRequestContext().getRoles();
	if (roles.iterator().next().equalsIgnoreCase(Constants.CustomerType.OPERATOR)) {
	    operatorId = applicationContext.getRequestContext().getUserId();
	}
	return operatorId;
    }

    public AnalyticsAvgMinMaxModel getCollection(Integer pageNumber, Integer limit, Long customerId2, Long commodityId,
	    Long ccId, Long dateFrom, Long dateTo, String deviceType, Long deviceTypeId, String deviceSerialNo,
	    Long stateId, Long categoryId, String districtName) throws IMException {

	BigDecimal avgQuantity = new BigDecimal(0.0);
	BigDecimal maxQuantity = new BigDecimal(0.0);
	BigDecimal minQuantity = new BigDecimal(0.0);
	BigDecimal totalQuantity = new BigDecimal(0.0);
	String unit = "";

	Long customerId = setCustomerId(customerId2);
	Long operatorId = setOperatorId();
	Long stateAdmin = applicationContext.getRequestContext().getStateAdmin();
	AnalyticsAvgMinMaxModel quantity = new AnalyticsAvgMinMaxModel();
	List<Object[]> resultObj = null;
	resultObj = filterRepo.getAggrigateQuantity(commodityId, ccId, dateFrom, dateTo, customerId, deviceType,
		deviceTypeId, deviceSerialNo, operatorId, stateAdmin, stateId,categoryId, districtName);
	for (Object[] obj : resultObj) {

	    avgQuantity = Utility.getBigDecimalValue(obj[0]);
	    minQuantity = Utility.getBigDecimalValue(obj[1]);
	    maxQuantity = Utility.getBigDecimalValue(obj[2]);
	    totalQuantity = Utility.getBigDecimalValue(obj[3]);
	    unit = Constants.WEIGHT_UNIT;
	    if (avgQuantity == null && minQuantity == null && maxQuantity == null && totalQuantity == null) {
		return null;
	    }

	}
	quantity.setMaxCollection(Utility.convertQuintalsToTons(maxQuantity));
	quantity.setAverageCollection(Utility.convertQuintalsToTons(avgQuantity));
	quantity.setMinCollection(Utility.convertQuintalsToTons(minQuantity));
	quantity.setTotalCollection(Utility.convertQuintalsToTons(totalQuantity));
	quantity.setUnit(unit);
	return quantity;

    }

    public List<AnalyticsCollectionModel> getCollectionList(Integer pageNumber, Integer limit, Long customerId2,
	    Long commodityId, Long locationId, Long dateFrom, Long dateTo, Long regionId, String deviceType,
	    Long deviceTypeId, String deviceSerialNo, Long stateId, Long categoryId, String districtName) throws IMException {

	List<Object[]> scanResult = null;
	Object[] scanResultArr = null;

	Long customerId = setCustomerId(customerId2);
	Long operatorId = setOperatorId();
	Long stateAdmin = applicationContext.getRequestContext().getStateAdmin();
	List<AnalyticsCollectionModel> analyticsCollectionList = new ArrayList<AnalyticsCollectionModel>();
	List<ScanEntity> scanEntityList = new ArrayList<ScanEntity>();

	if (locationId == null) {
	    scanResult = filterRepo.getCollectionsQuantityPerLocation(regionId, commodityId, null, dateFrom, dateTo,
		    customerId, deviceType, deviceTypeId, deviceSerialNo, operatorId, stateAdmin, stateId,categoryId,districtName);

	    for (Object[] obj : scanResult) {
		if (obj != null) {

		    AnalyticsCollectionModel analyticsCollections = new AnalyticsCollectionModel();
		    if ((Long) obj[0] != null) {
			String installationCenterName = scanLocationRepo.getOne((Long) obj[0]).getLocationName();
			analyticsCollections.setInstCenterName(installationCenterName);
		    }
		    Optional.ofNullable((Long) obj[0]).ifPresent(analyticsCollections::setInstCenterId);
		    Optional.ofNullable(Utility.getBigDecimalValue(obj[1])).ifPresent(value -> {
				try {
					analyticsCollections
					    .setWeight(Utility.convertQuintalsToTons(Utility.getBigDecimalValue(value)));
				} catch (IMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

		    analyticsCollections.setQuantityUnit(Constants.WEIGHT_UNIT);
		    analyticsCollectionList.add(analyticsCollections);
		    
		}
	    }
	} else {
	    scanResultArr = filterRepo.getCollectionsQuantityByLocation(commodityId, locationId, dateFrom, dateTo,
		    customerId, deviceType, deviceTypeId, deviceSerialNo, operatorId, stateAdmin, stateId,categoryId, districtName);
	    AnalyticsCollectionModel analyticsCollections = new AnalyticsCollectionModel();
	    if ((Long) scanResultArr[0] != null) {
		String installationCenterName = scanLocationRepo.getOne((Long) scanResultArr[0]).getLocationName();
		analyticsCollections.setInstCenterName(installationCenterName);
	    }
	    Optional.ofNullable(Utility.getBigDecimalValue(scanResultArr[1])).ifPresent(weight -> {
			try {
				analyticsCollections
				    .setWeight(Utility.convertQuintalsToTons(Utility.getBigDecimalValue(weight)));
			} catch (IMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	    Optional.ofNullable((Long) scanResultArr[0]).ifPresent(analyticsCollections::setInstCenterId);
	    

	    analyticsCollections.setQuantityUnit(Constants.WEIGHT_UNIT);
	    analyticsCollectionList.add(analyticsCollections);

	}
	return analyticsCollectionList;

    }

    private Long setCustomerId(Long customerId2) throws IMException {
	logger.info(" Inside setCustomerId method, the customer id to set is : " + customerId2);
	System.out.println(" Request Context : " + applicationContext.getRequestContext());
	Long customerId = null;
	if (Constants.CustomerType.CLIENT.equals(applicationContext.getRequestContext().getCustomerType())) {
	    if (customerId2 == null) {
		customerId = applicationContext.getRequestContext().getCustomerId();
	    } else {
		customerId = customerId2;
	    }

	} else if (Constants.CustomerType.CUSTOMER.equals(applicationContext.getRequestContext().getCustomerType())) {
	    customerId = null;

	} else if (applicationContext.getRequestContext().getRoles().contains("state_admin")) {
	    customerId = applicationContext.getRequestContext().getCustomerId();
	}
	return customerId;
    }

    public List<ScanCountModel> getCollectionDetailsListOverTime(Integer pageNumber, Integer limit, Long customerId2,
	    Long commodityId, Long instCenterId, Long dateFrom, Long dateTo, String deviceType, Long deviceTypeId,
	    String deviceSerialNo, Long stateId, Long categoryId, String districtName) throws IMException {

	List<ScanCountModel> list = new ArrayList<ScanCountModel>();
	List<Object[]> scanResult = null;

	String unit = "";

	Long customerId = setCustomerId(customerId2);
	Long operatorId = setOperatorId();
	Long stateAdmin = applicationContext.getRequestContext().getStateAdmin();

	scanResult = filterRepo.getCollectionDetailsOverTime(pageNumber, limit, customerId, commodityId, instCenterId,
		dateFrom, dateTo, deviceType, deviceTypeId, deviceSerialNo, operatorId, stateAdmin, stateId,categoryId, districtName);

	for (Object[] obj : scanResult) {
	    //			unit = (String) obj[3];
	    ScanCountModel count = new ScanCountModel();
	    count.setTotalCollection(Utility.convertQuintalsToTons(Utility.getBigDecimalValue(obj[0])));
	    count.setScanDate(Utility.formatDateToString(new java.sql.Date((Long) obj[2]), Constants.DATE_FORMAT));
	    count.setDateDone((Long) obj[2]);
	    count.setUnit(Constants.WEIGHT_UNIT);

	    list.add(count);
	}

	return list;
    }

    //	public List<ScanCountModel> getCenterAndRegionCollections(Integer pageNumber, Integer limit, Long customerId2,
    //			Long commodityId, Long instCenterId, Long dateFrom, Long dateTo, String deviceType,
    //			Long deviceTypeId, String deviceSerialNo) throws IMException {
    //
    //		Long customerId = setCustomerId(customerId2);
    //		Double centerSum = 0.0;
    //		Double regionSum = 0.0;
    //		List<String> uniqueDateSet = null;
    //
    //
    //		List<ScanCountModel> list = null;
    //		try {
    //			List<Long> dates = filterRepo.getScanDatesForCollectionOverTime( commodityId, instCenterId,
    //					dateFrom, dateTo, deviceType, deviceTypeId, customerId, deviceSerialNo);
    //			uniqueDateSet = getUniqueDateSet(dates);
    //			List<String> scDateq = new ArrayList<>();
    //			Set<Long> scanDate = new HashSet<>(dates);
    //			// List<Long> dateList = new ArrayList<>(scanDate);
    //			Collections.sort(dates, new DateComparator());
    //			System.out.print(dates);
    //			for (Long sDate : dates) {
    //				Date currentDate = new Date(sDate);
    //				System.out.print(currentDate);
    //				System.out.print(currentDate.toInstant().getEpochSecond());
    //				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    //				System.out.print(df.format(currentDate));
    //				scDateq.add(df.format(currentDate));
    //
    //				// scDate.add(df.format(currentDate));
    //			}
    //			Set<String> scDate = new LinkedHashSet<String>(scDateq);
    //			list = new ArrayList<>();
    //			for (String date : uniqueDateSet) {
    //				Long startFrom = getEpochFromString(date);
    //
    //				Long endTo = startFrom + 86400000;
    //
    //				centerSum = filterRepo.getTotalCollectionByInstallationCenterAndRegionId(commodityId, instCenterId,
    //						startFrom, endTo, customerId,  deviceType, deviceTypeId, deviceSerialNo);
    //
    //				regionSum = filterRepo.getTotalCollectionByRegionId(commodityId, startFrom, endTo, customerId, regionId,
    //						deviceType, deviceTypeId, deviceSerialNo);
    //
    //				ScanCountModel count = new ScanCountModel();
    //				count.setScanDate(date);
    //				count.setDateDone(startFrom);
    //				count.setCenterCollection(Utility.formatDecimal(centerSum));
    //				count.setRegionCollection(Utility.formatDecimal(regionSum));
    //				list.add(count);
    //			}
    //		} catch (NumberFormatException e) {
    //			// TODO Auto-generated catch block
    //			e.printStackTrace();
    //		} catch (ParseException e) {
    //			// TODO Auto-generated catch block
    //			e.printStackTrace();
    //		}
    //		return list;
    //	}

    public ClientAvgMinMaxModel getClientsDetails(Integer pageNumber, Integer limit, Long customerId2, Long commodityId,
	    Long dateFrom, Long dateTo, Long instCenterId, String deviceType, Long deviceTypeId) throws IMException {

	DecimalFormat decimalFormat = new DecimalFormat("#.##");

	ClientAvgMinMaxModel clientAvgMinMaxModel = new ClientAvgMinMaxModel();

	List<GraphDataModel> incrementGraphData = null;
	List<GraphDataModel> decrementGraphData = null;
	BigDecimal minClientData = new BigDecimal(0.0);
	BigDecimal maxClientData = new BigDecimal(0.0);

	BigDecimal quantity = new BigDecimal(0.0);
	BigDecimal maxQuantity = new BigDecimal(0.0);
	BigDecimal initialQuantity = new BigDecimal(0.0);
	BigDecimal initialMinQuantity = new BigDecimal(9999999.0);
	BigDecimal minQuantity = new BigDecimal(0.0);

	BigDecimal maxQuantitySum = new BigDecimal(0.0);
	BigDecimal minQuantitySum = new BigDecimal(999999999.0);

	Long clientWithMaxAMT = Long.valueOf(0);
	Long clientIdWithMinAMT = Long.valueOf(0);

	BigDecimal previousScanDataInc = new BigDecimal(0.0);
	;
	BigDecimal previousScanDataDesc = new BigDecimal(0.0);
	;

	BigDecimal currentScanDataInc = new BigDecimal(0.0);
	;
	BigDecimal currentScanDataDesc = new BigDecimal(0.0);
	;

	BigDecimal increasePercentage = new BigDecimal(50.0);
	BigDecimal decreasePercentage = new BigDecimal(20.0);

	BigDecimal increaseDiff = new BigDecimal(0.0);
	BigDecimal decreaseDiff = new BigDecimal(0.0);

	BigDecimal preSum = new BigDecimal(0.0);
	;
	BigDecimal preSumForMinAMTF = new BigDecimal(0.0);
	;

	Long customerId = setCustomerId(customerId2);
	Long operatorId = setOperatorId();
	Long userId = null;

	long previousFromDateMilli = 0;
	if (dateFrom != null) {
	    Date dateFrom1 = new Date(dateFrom);
	    Date dateTo1 = new Date(dateTo);
	    long duration = dateTo1.getTime() - dateFrom1.getTime();
	    long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
	    LocalDateTime ldt = LocalDateTime.ofInstant(dateFrom1.toInstant(), ZoneId.systemDefault());
	    // Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	    LocalDateTime ldt2 = ldt.minusDays(diffInDays);
	    // String ldt3 = ldt.format(formatter);

	    ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
	    long previousFromDate = ldt2.atZone(zoneId).toEpochSecond();
	    previousFromDateMilli = TimeUnit.SECONDS.toMillis(previousFromDate);
	}
	List<Long> clientIds = filterRepo.getClientsIds(commodityId, dateFrom, dateTo, instCenterId, customerId,
		deviceType, deviceTypeId, userId, operatorId);
	if (clientIds == null || clientIds.isEmpty()) {
	    throw new IMException(Constants.ErrorCode.NO_RECORD_FORUND, Constants.ErrorMessage.NO_RECORD_FORUND);
	}
	Collections.sort(clientIds);
	Set<Long> clientUIds = new HashSet<>(clientIds);
	Map<Long, BigDecimal> amt = new HashMap<>();
	for (Long long1 : clientUIds) {
	    if (long1 != 0) {
		quantity = filterRepo.maxClientCollection(commodityId, dateFrom, dateTo, customerId, long1,
			instCenterId, operatorId);

		if (quantity.compareTo(initialQuantity) == 1) {
		    clientWithMaxAMT = long1;
		    maxQuantity = quantity;
		    initialQuantity = quantity;

		}

		if (quantity.compareTo(initialMinQuantity) == -1) {
		    clientIdWithMinAMT = long1;
		    minQuantity = quantity;
		    initialMinQuantity = quantity;
		}
	    }
	    // minQuantity = filtersNativeRepo.minFarmerCollection(regionId, commodityId,
	    // dateFrom, dateTo,customerId,long1);
	}
	if (dateFrom != null && clientWithMaxAMT != 0) {
	    preSum = filterRepo.maxClientCollection(commodityId, previousFromDateMilli, dateFrom, customerId,
		    clientWithMaxAMT, instCenterId, operatorId);
	    System.out.print("preSum" + preSum);
	}
	if (dateFrom != null && clientIdWithMinAMT != 0) {
	    preSumForMinAMTF = filterRepo.maxClientCollection(commodityId, previousFromDateMilli, dateFrom, customerId,
		    clientIdWithMinAMT, instCenterId, operatorId);

	    System.out.print("preSum" + preSumForMinAMTF);
	}

	if (preSum != null && preSumForMinAMTF != null && maxQuantity != null && minQuantity != null) {
	    increaseDiff = maxQuantity.subtract(preSum);
	    decreaseDiff = minQuantity.subtract(preSumForMinAMTF);
	    if (preSum.doubleValue() != 0.0 && preSumForMinAMTF.doubleValue() != 0.0) {
		increasePercentage = increaseDiff.multiply(BigDecimal.valueOf(100)).divide(preSum, 2,
			RoundingMode.DOWN);
		decreasePercentage = decreaseDiff.multiply(BigDecimal.valueOf(100)).divide(preSumForMinAMTF,
			RoundingMode.DOWN);
	    }

	    if (preSum.doubleValue() == 0.0 && maxQuantity.doubleValue() != 0.0) {
		clientAvgMinMaxModel.setIncrementPercentage(BigDecimal.valueOf(100.0));
	    } else if (preSum.doubleValue() == 0.0 && maxQuantity.doubleValue() == 0.0) {
		clientAvgMinMaxModel.setIncrementPercentage(BigDecimal.valueOf(0.0));
	    }

	    if (preSumForMinAMTF.doubleValue() == 0.0 && minQuantity.doubleValue() != 0.0) {
		clientAvgMinMaxModel.setDecrementPercentage(BigDecimal.valueOf(100.0));
	    } else if (preSumForMinAMTF.doubleValue() == 0.0 && minQuantity.doubleValue() == 0.0) {
		clientAvgMinMaxModel.setDecrementPercentage(BigDecimal.valueOf(0.0));
	    }

	}

	clientAvgMinMaxModel.setMaxCollection(Utility.formatDecimal(maxQuantity));
	clientAvgMinMaxModel.setMinCollection(Utility.formatDecimal(minQuantitySum));
	clientAvgMinMaxModel.setMaxClientId(clientWithMaxAMT);
	clientAvgMinMaxModel.setMaxClientName("Vishal");
	clientAvgMinMaxModel.setMinClientId(clientIdWithMinAMT);
	clientAvgMinMaxModel.setMinClientName("Piyush");
	if (instCenterId != null && instCenterId != 0) {
	    clientAvgMinMaxModel.setMaxInstCenterId(instCenterId);
	    clientAvgMinMaxModel.setMinInstCenterId(instCenterId);
	    clientAvgMinMaxModel.setMaxInstCenterName("Mohali");
	    clientAvgMinMaxModel.setMinInstCenterName("Mohali");
	} else {
	    clientAvgMinMaxModel.setMaxInstCenterName("Mohali");
	    clientAvgMinMaxModel.setMinInstCenterName("Mohali");
	    clientAvgMinMaxModel.setMaxInstCenterId(Long.valueOf(1));
	    clientAvgMinMaxModel.setMinInstCenterId(Long.valueOf(1));
	}

	clientAvgMinMaxModel.setIncrementPercentage(Utility.formatDecimal(increasePercentage));
	clientAvgMinMaxModel.setDecrementPercentage(Utility.formatDecimal(decreasePercentage));

	try {
	    List<Long> dates = filterRepo.getScanDatesForCollectionOverTime(commodityId, instCenterId, dateFrom, dateTo,
		    customerId, operatorId);
	    List<String> scDateq = new ArrayList<>();
	    Set<Long> scanDate = new HashSet<>(dates);
	    // List<Long> dateList = new ArrayList<>(scanDate);
	    Collections.sort(dates, new DateComparator());
	    System.out.print(dates);
	    for (Long sDate : dates) {
		Date currentDate = new Date(sDate);
		System.out.print(currentDate);
		System.out.print(currentDate.toInstant().getEpochSecond());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		System.out.print(df.format(currentDate));
		scDateq.add(df.format(currentDate));

		// scDate.add(df.format(currentDate));
	    }
	    Set<String> scDate = new LinkedHashSet<String>(scDateq);
	    incrementGraphData = new ArrayList<>();
	    decrementGraphData = new ArrayList<>();
	    for (String sDate : scDate) {
		String myDate1 = sDate;
		Date date11 = new SimpleDateFormat("MM/dd/yyyy").parse(myDate1);
		System.out.println(myDate1 + "\t" + date11);

		long millisFrom = date11.getTime();
		date11.toInstant().getEpochSecond();
		System.out.print(date11.toInstant().getEpochSecond());
		Long startFrom = Long.valueOf(date11.toInstant().getEpochSecond() + "000");

		Long endTo = startFrom + 86400000;

		minClientData = filterRepo.getMinClientGraphDataPerDay(commodityId, instCenterId, startFrom, endTo,
			clientIdWithMinAMT, customerId, operatorId);
		maxClientData = filterRepo.getMaxClientGraphDataPerDay(commodityId, instCenterId, startFrom, endTo,
			clientWithMaxAMT, customerId, operatorId);
		GraphDataModel incrementCount = new GraphDataModel();
		GraphDataModel decrementCount = new GraphDataModel();
		incrementCount.setScanDate(sDate);
		incrementCount.setIncrementGraphDate(startFrom);
		decrementCount.setDecrementGraphDate(startFrom);
		incrementCount.setIncrementGraphTotalWeight(Utility.formatDecimal(maxClientData));
		decrementCount.setDecrementGraphTotalWeight(Utility.formatDecimal(minClientData));
		incrementGraphData.add(incrementCount);
		decrementGraphData.add(decrementCount);
		decrementCount.setScanDate(sDate);
		clientAvgMinMaxModel.setIncrementGraphData(incrementGraphData);
		clientAvgMinMaxModel.setDecrementGraphData(decrementGraphData);

	    }
	} catch (NumberFormatException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return clientAvgMinMaxModel;
    }
    //
    // public ClientAvgMinMaxModel getFarmerDetails(Integer pageNumber, Integer
    // limit, Long customerId, Long commodityId,
    // Long dateFrom, Long dateTo, Long regionId, Long instCenterId, String
    // deviceType, Long deviceTypeId) {
    // // TODO Auto-generated method stub
    // return null;
    //
    // }

    //	public ClientAvgMinMaxModel getFarmerQualityDetails(Integer pageNumber, Integer limit, Long customerId2,
    //			Long commodityId, Long dateFrom, Long dateTo, Long instCenterId, String analysisCode,
    //			String deviceType, Long deviceTypeId) throws IMException {
    //		DecimalFormat decimalFormat = new DecimalFormat("#.##");
    //
    //		ClientAvgMinMaxModel farmerAvgMinMaxModel = new ClientAvgMinMaxModel();
    //
    //		List<GraphDataModel> incrementGraphData = null;
    //		List<GraphDataModel> decrementGraphData = null;
    //		Double minFarmerData = 0.0;
    //		Double maxFarmerData = 0.0;
    //
    //		Double quality = 0.0;
    //		Double maxQuality = 0.0;
    //		Double initialQuality = 0.0;
    //		Double initialMinQuality = 9999999.0;
    //		Double minQuality = 0.0;
    //
    //		Double maxQualitySum = 0.0;
    //		Double minQualitySum = 999999999.0;
    //
    //		Long farmerIdWithMaxAMT = Long.valueOf(0);
    //		Long farmerIdWithMinAMT = Long.valueOf(0);
    //
    //		Double previousScanDataInc = 0.0;
    //		Double previousScanDataDesc = 0.0;
    //
    //		Double currentScanDataInc = 0.0;
    //		Double currentScanDataDesc = 0.0;
    //
    //		Double increasePercentage = 50.0;
    //		Double decreasePercentage = 20.0;
    //
    //		Double increaseDiff = 0.0;
    //		Double decreaseDiff = 0.0;
    //
    //		Double preSum = 0.0;
    //		Double preSumForMinAMTF = 0.0;
    //
    //		Long customerId = null;
    //
    //		if (Constants.CUSTOMER_TYPE_PROVIDER.equals(serverContext.getRequestContext().getCustomerType())) {
    //			customerId = customerId2;
    //
    //		} else if (Constants.CUSTOMER_TYPE_CUSTOMER.equals(serverContext.getRequestContext().getCustomerType())) {
    //			customerId = serverContext.getRequestContext().getCustomerId();
    //
    //		}
    //		long previousFromDateMilli = 0;
    //		if (dateFrom != null) {
    //			Date dateFrom1 = new Date(dateFrom);
    //			Date dateTo1 = new Date(dateTo);
    //			long duration = dateTo1.getTime() - dateFrom1.getTime();
    //			long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
    //			LocalDateTime ldt = LocalDateTime.ofInstant(dateFrom1.toInstant(), ZoneId.systemDefault());
    //			// Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    //			LocalDateTime ldt2 = ldt.minusDays(diffInDays);
    //			// String ldt3 = ldt.format(formatter);
    //
    //			ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
    //			long previousFromDate = ldt2.atZone(zoneId).toEpochSecond();
    //			previousFromDateMilli = TimeUnit.SECONDS.toMillis(previousFromDate);
    //		}
    //		List<Long> farmerIds = filterRepo.getFarmerIdsForQuality( commodityId, dateFrom, dateTo, instCenterId,
    //				customerId, deviceType, deviceTypeId, analysisCode);
    //		if (farmerIds == null || farmerIds.isEmpty()) {
    //			throw new IMException(Constants.ErrorCode.NO_RECORD_FORUND, Constants.ErrorMessage.NO_RECORD_FORUND);
    //		}
    //		Collections.sort(farmerIds);
    //		Set<Long> farmerUIds = new HashSet<>(farmerIds);
    //		Map<Long, Double> amt = new HashMap<>();
    //		for (Long long1 : farmerUIds) {
    //			if (long1 != 0) {
    //				quality = filterRepo.maxFarmerQuality( commodityId, dateFrom, dateTo, customerId, long1,
    //						instCenterId, analysisCode);
    //
    //				if (quality > initialQuality) {
    //					farmerIdWithMaxAMT = long1;
    //					maxQuality = quality;
    //					initialQuality = quality;
    //
    //				}
    //
    //				if (quality < initialMinQuality) {
    //					farmerIdWithMinAMT = long1;
    //					minQuality = quality;
    //					initialMinQuality = quality;
    //				}
    //			}
    //			// minQuantity = filtersNativeRepo.minFarmerCollection(regionId, commodityId,
    //			// dateFrom, dateTo,customerId,long1);
    //		}
    //		if (dateFrom != null && farmerIdWithMaxAMT != 0) {
    //			preSum = filterRepo.maxFarmerCollectionQuality(regionId, commodityId, previousFromDateMilli, dateFrom,
    //					customerId, farmerIdWithMaxAMT, analysisCode, instCenterId);
    //			System.out.print("preSum" + preSum);
    //		}
    //		if (dateFrom != null && farmerIdWithMinAMT != 0) {
    //			preSumForMinAMTF = filterRepo.maxFarmerCollectionQuality(regionId, commodityId, previousFromDateMilli,
    //					dateFrom, customerId, farmerIdWithMinAMT, analysisCode, instCenterId);
    //
    //			System.out.print("preSum" + preSumForMinAMTF);
    //		}
    //
    //		if (preSum != null && preSumForMinAMTF != null && maxQuality != null && minQuality != null) {
    //			increaseDiff = maxQuality - preSum;
    //			decreaseDiff = minQuality - preSumForMinAMTF;
    //			if (preSum != 0.0 && preSumForMinAMTF != 0.0) {
    //				increasePercentage = increaseDiff * 100 / preSum;
    //				decreasePercentage = decreaseDiff * 100 / preSumForMinAMTF;
    //			}
    //			if (preSum == 0.0 && maxQuality != 0.0) {
    //				farmerAvgMinMaxModel.setIncrementPercentage(100.0);
    //			} else if (preSum == 0.0 && maxQuality == 0.0) {
    //				farmerAvgMinMaxModel.setIncrementPercentage(0.0);
    //			}
    //			if (preSumForMinAMTF == 0.0 && minQuality != 0.0) {
    //				farmerAvgMinMaxModel.setDecrementPercentage(100.0);
    //			} else if (preSumForMinAMTF == 0.0 && minQuality == 0.0) {
    //				farmerAvgMinMaxModel.setDecrementPercentage(0.0);
    //			}
    //
    //		}
    //
    //		farmerAvgMinMaxModel.setMaxCollection(Utility.formatDecimal(maxQuality));
    //		farmerAvgMinMaxModel.setMinCollection(Utility.formatDecimal(minQualitySum));
    //		farmerAvgMinMaxModel.setMaxFarmerId(farmerIdWithMaxAMT);
    //		farmerAvgMinMaxModel.setMinFarmerId(farmerIdWithMinAMT);
    //		if (instCenterId != null && instCenterId != 0) {
    //			farmerAvgMinMaxModel.setMaxInstCenterId(instCenterId);
    //			farmerAvgMinMaxModel.setMinInstCenterId(instCenterId);
    //		} else {
    //			farmerAvgMinMaxModel.setMaxInstCenterId(Long.valueOf(1));
    //			farmerAvgMinMaxModel.setMinInstCenterId(Long.valueOf(1));
    //		}
    //
    //		farmerAvgMinMaxModel.setIncrementPercentage(Utility.formatDecimal(increasePercentage));
    //		farmerAvgMinMaxModel.setDecrementPercentage(Utility.formatDecimal(decreasePercentage));
    //
    //		try {
    //			List<Long> dates = filterRepo.getScanDatesForCollectionOverTime(regionId, commodityId, instCenterId,
    //					dateFrom, dateTo, customerId);
    //			List<String> scDateq = new ArrayList<>();
    //			Set<Long> scanDate = new HashSet<>(dates);
    //			// List<Long> dateList = new ArrayList<>(scanDate);
    //			Collections.sort(dates, new DateComparator());
    //			System.out.print(dates);
    //			for (Long sDate : dates) {
    //				Date currentDate = new Date(sDate);
    //				System.out.print(currentDate);
    //				System.out.print(currentDate.toInstant().getEpochSecond());
    //				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    //				System.out.print(df.format(currentDate));
    //				scDateq.add(df.format(currentDate));
    //
    //				// scDate.add(df.format(currentDate));
    //			}
    //			Set<String> scDate = new LinkedHashSet<String>(scDateq);
    //			incrementGraphData = new ArrayList<>();
    //			decrementGraphData = new ArrayList<>();
    //			for (String sDate : scDate) {
    //				String myDate1 = sDate;
    //				Date date11 = new SimpleDateFormat("MM/dd/yyyy").parse(myDate1);
    //				System.out.println(myDate1 + "\t" + date11);
    //
    //				long millisFrom = date11.getTime();
    //				date11.toInstant().getEpochSecond();
    //				System.out.print(date11.toInstant().getEpochSecond());
    //				Long startFrom = Long.valueOf(date11.toInstant().getEpochSecond() + "000");
    //
    //				Long endTo = startFrom + 86400000;
    //
    //				minFarmerData = filterRepo.getMinFarmerGraphDataPerDayForQuality(regionId, commodityId, instCenterId,
    //						startFrom, endTo, farmerIdWithMinAMT, analysisCode, customerId);
    //				maxFarmerData = filterRepo.getMaxFarmerGraphDataPerDayForQuality(regionId, commodityId, instCenterId,
    //						startFrom, endTo, farmerIdWithMaxAMT, analysisCode, customerId);
    //				GraphDataModel incrementCount = new GraphDataModel();
    //				GraphDataModel decrementCount = new GraphDataModel();
    //				incrementCount.setScanDate(sDate);
    //				incrementCount.setIncrementGraphDate(startFrom);
    //				decrementCount.setDecrementGraphDate(startFrom);
    //				incrementCount.setIncrementGraphTotalWeight(Utility.formatDecimal(maxFarmerData));
    //				decrementCount.setDecrementGraphTotalWeight(Utility.formatDecimal(minFarmerData));
    //				incrementGraphData.add(incrementCount);
    //				decrementGraphData.add(decrementCount);
    //				decrementCount.setScanDate(sDate);
    //				farmerAvgMinMaxModel.setIncrementGraphData(incrementGraphData);
    //				farmerAvgMinMaxModel.setDecrementGraphData(decrementGraphData);
    //				// Double sum = filtersNativeRepo.getCollectionsQuantityPerDay(regionId,
    //				// commodityId, instCenterId,
    //				// startFrom, endTo);
    //				// GraphDataModel count = new GraphDataModel();
    //				// count.setScanDate(sDate);
    //				// count.setDateDone(startFrom);
    //				// count.setTotalCollection(sum);
    //				// list.add(count);
    //				// scanCountModel.setGraphData(list);
    //			}
    //		} catch (NumberFormatException e) {
    //			// TODO Auto-generated catch block
    //			e.printStackTrace();
    //		} catch (ParseException e) {
    //			// TODO Auto-generated catch block
    //			e.printStackTrace();
    //		}
    //
    //		return farmerAvgMinMaxModel;
    //
    //	}

    public List<ClientDetailModel> getClientList(Integer pageNumber, Integer limit, Long customerId2, Long commodityId,
	    Long dateFrom, Long dateTo, Long instCenterId, String deviceType, Long deviceTypeId) throws IMException {
	// TODO Auto-generated method stub
	// return null;
	// Long dateFrom, Long dateTo, Long regionId, Long instCenterId) throws
	// IMException {
	DecimalFormat decimalFormat = new DecimalFormat("#.##");

	BigDecimal avgQuantityObj = new BigDecimal(0.0);
	BigDecimal areaCoveredObj = new BigDecimal(0.0);
	Long instCenterIdObj = null;

	List<Long> farmerIdList = new ArrayList<Long>();

	Object[] obj = null;
	Long customerId = setCustomerId(customerId2);
	Long operatorId = setOperatorId();
	Long userId = null;

	List<ClientDetailModel> detailModels = new ArrayList<>();
	List<Long> farmerIds = filterRepo.getClientIds(commodityId, dateFrom, dateTo, instCenterId, customerId,
		deviceType, deviceTypeId, null, operatorId);
	if (farmerIds == null || farmerIds.isEmpty()) {
	    return null;
	    //			throw new IMException(Constants.ErrorCode.NO_RECORD_FORUND, Constants.ErrorMessage.NO_RECORD_FORUND);
	}
	Set<Long> farmerUIds = new HashSet<>(farmerIds);

	for (Long long1 : farmerUIds) {
	    if (long1 != null)
		farmerIdList.add(long1);

	}

	if (farmerIdList != null) {

	    for (Long farmerId : farmerIdList) {
		//				ClientDetailModel clientDetailModel = restTemplateCalls.getVendorDetailProfile(farmerId);
		ClientDetailModel farmerDetailModelRes = new ClientDetailModel();
		/******************** Optimization ****************/
		obj = filterRepo.getClientsParameters(commodityId, instCenterId, null, dateFrom, dateTo, deviceType,
			deviceTypeId, userId, farmerId, operatorId);
		{
		    avgQuantityObj = Utility.getBigDecimalValue(obj[0]);
		    areaCoveredObj = Utility.getBigDecimalValue(obj[1]);
		    instCenterIdObj = (Long) obj[2];
		    if (avgQuantityObj != null) {
			farmerDetailModelRes.setAvgCollection(Utility.convertQuintalsToTons(avgQuantityObj));
			farmerDetailModelRes.setUnit(Constants.WEIGHT_UNIT);
		    }
		    if (areaCoveredObj != null) {
			farmerDetailModelRes.setArea(areaCoveredObj);
		    }
		    if (instCenterIdObj != null) {
			ScanLocation location = scanLocationRepo.getOne(instCenterIdObj);
			farmerDetailModelRes.setInstCenterId(location.getId());
			farmerDetailModelRes.setInstCenterName(location.getLocationName());
			//						System.out.println(" Inst Center name : "+ clientDetailModel.getInstCenterName());
			//						System.out.println(" instCenter.getInstCenterName() : "+ clientDetailModel.getInstCenterName());

		    }
		}

		// Double avgQuantity = filterRepo.getFarmersQuantity(regionId, commodityId,
		// instCenterId, null, dateFrom,
		// dateTo, deviceType, deviceTypeId, farmerId);
		// Double areaCovered = filterRepo.getFarmersAreaCoverd(regionId, commodityId,
		// instCenterId, null,
		// dateFrom, dateTo, deviceType, deviceTypeId, farmerId);
		// if (avgQuantity != null) {
		// farmerDetailModelRes.setAvgCollection(avgQuantity);
		// }
		// if (areaCovered != null) {
		// farmerDetailModelRes.setArea(areaCovered);
		// }
		//
		// Long instId = filterRepo.getScanEntityByFarmerId(farmerId);
		farmerDetailModelRes.setFarmerId(farmerId);

		// farmerDetailModel.setInstCenterId(instId);

		CustomerEntity clientDetails = customerRepo.getOne(farmerId);

		if (clientDetails != null) {
		    farmerDetailModelRes.setPhoneNumber(clientDetails.getCustomerContactNumber());
		    farmerDetailModelRes.setFarmerName(clientDetails.getCustomerName());
		}
		detailModels.add(farmerDetailModelRes);
	    }
	}

	return detailModels;
    }

    //	public List<ClientDetailModel> getFarmerListQuality(Integer pageNumber, Integer limit, Long customerId2,
    //			Long commodityId, Long dateFrom, Long dateTo,  Long instCenterId, String analysisCode,
    //			String deviceType, Long deviceTypeId) throws IMException {
    //		
    //		DecimalFormat decimalFormat = new DecimalFormat("#.##");
    //
    //		List<Long> farmerIdList = new ArrayList<Long>();
    //        
    //		Long customerId=setCustomerId(customerId2);
    //		
    //
    //		List<ClientDetailModel> detailModels = new ArrayList<>();
    //		List<Long> farmerIds = filterRepo.getFarmerIds( commodityId, dateFrom, dateTo, instCenterId,
    //				customerId, deviceType, deviceTypeId);
    //		if (farmerIds == null || farmerIds.isEmpty()) {
    //			throw new IMException(Constants.ErrorCode.NO_RECORD_FORUND, Constants.ErrorMessage.NO_RECORD_FORUND);
    //		}
    //		Set<Long> farmerUIds = new HashSet<>(farmerIds);
    //
    //		for (Long long1 : farmerUIds) {
    //			if (long1 != null)
    //				farmerIdList.add(long1);
    //
    //		}
    //
    //		if (farmerIdList != null) {
    //			for (Long farmerId : farmerIdList) {
    //				ClientDetailModel farmerDetailModel = restTemplateCalls.getVendorDetailProfile(farmerId);
    //				ClientDetailModel farmerDetailModelRes = new ClientDetailModel();
    //
    //				Double avgQuality = filterRepo.getFarmersQuality( commodityId, instCenterId, analysisCode,
    //						dateFrom, dateTo, deviceType, deviceTypeId, farmerId);
    //				Double areaCovered = filterRepo.getFarmersAreaCoverd( commodityId, instCenterId, analysisCode,
    //						dateFrom, dateTo, deviceType, deviceTypeId, farmerId);
    //				if (avgQuality != null) {
    //					farmerDetailModelRes.setAvgQuality(avgQuality);
    //				}
    //				if (areaCovered != null) {
    //					farmerDetailModelRes.setArea(areaCovered);
    //				}
    //				// Long instId = filterRepo.getScanEntityByFarmerId(farmerId);
    //				farmerDetailModelRes.setFarmerId(farmerId);
    //
    //				// farmerDetailModelRes.setInstCenterId(instId);
    //
    //				if (farmerDetailModel != null) {
    //					farmerDetailModelRes.setPhoneNumber(farmerDetailModel.getPhoneNumber());
    //					farmerDetailModelRes.setFarmerName(farmerDetailModel.getFarmerName());
    //				}
    //
    //				detailModels.add(farmerDetailModelRes);
    //			}
    //		}
    //
    //		return detailModels;
    //	}

    public ScanCountModel getIncreamentDecrementInCollections(Integer pageNumber, Integer limit, Long customerId2,
	    Long commodityId, Long instCenterId, Long dateFrom, Long dateTo, String deviceType, Long deviceTypeId,
	    String deviceSerialNo, Long stateId, Long categoryId, String districtName) throws IMException {
	Long customerId = setCustomerId(customerId2);
	Long operatorId = setOperatorId();
	Long stateAdmin = applicationContext.getRequestContext().getStateAdmin();
	Long userId = null;
	// setUserId(clientId);

	if (dateFrom == null && dateTo == null)
	    throw new IMException(Constants.ErrorCode.DATE_NOT_FOUND, Constants.ErrorMessage.DATE_NOT_FOUND);

	ScanCountModel scanCountModel = new ScanCountModel();
	BigDecimal previousScanData = new BigDecimal(0.0);
	BigDecimal currentScanData = new BigDecimal(0.0);
	BigDecimal percentage = new BigDecimal(0.0);
	BigDecimal difference = new BigDecimal(0.0);
	List<GraphDataModel> list = new ArrayList<GraphDataModel>();

	Date dateFrom1 = new Date(dateFrom);
	Date dateTo1 = new Date(dateTo);
	long duration = dateTo1.getTime() - dateFrom1.getTime();
	long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
	LocalDateTime ldt = LocalDateTime.ofInstant(dateFrom1.toInstant(), ZoneId.systemDefault());
	LocalDateTime ldt2 = ldt.minusDays(diffInDays);

	ZoneId zoneId = ZoneId.systemDefault();
	long previousFromDate = ldt2.atZone(zoneId).toEpochSecond();
	long previousFromDateMilli = TimeUnit.SECONDS.toMillis(previousFromDate);
	Date dateFrom3 = new Date(previousFromDateMilli);
	previousScanData = filterRepo.getDateWiseQuantity(commodityId, instCenterId, previousFromDateMilli, dateFrom,
		customerId, deviceType, deviceTypeId, deviceSerialNo, userId, operatorId, stateAdmin, stateId,categoryId,districtName);
	currentScanData = filterRepo.getDateWiseQuantity(commodityId, instCenterId, dateFrom, dateTo, customerId,
		deviceType, deviceTypeId, deviceSerialNo, userId, operatorId, stateAdmin, stateId,categoryId,districtName);

	if (currentScanData != null && previousScanData != null) {
	    difference = currentScanData.subtract(previousScanData);
	    if (previousScanData.doubleValue() != 0.0) {
		percentage = difference.multiply(BigDecimal.valueOf(100)).divide(previousScanData, 2,
			RoundingMode.DOWN);
	    }
	    scanCountModel.setDifference(Utility.formatDecimal(difference));
	    scanCountModel.setDifferencePercentage(Utility.formatDecimal(percentage));
	    if (previousScanData.doubleValue() == 0.0 && currentScanData.doubleValue() != 0.0) {
		scanCountModel.setDifferencePercentage(BigDecimal.valueOf(100.0));
	    } else if (previousScanData.doubleValue() == 0.0 && currentScanData.doubleValue() == 0.0) {
		scanCountModel.setDifferencePercentage(BigDecimal.valueOf(0.0));
	    }
	}

	List<Object[]> ObjList = filterRepo.getCollectionsQuantityPerDay(commodityId, instCenterId, dateFrom, dateTo,
		customerId, deviceType, deviceTypeId, deviceSerialNo, userId, operatorId, stateAdmin, stateId,categoryId, districtName);
	for (Object[] obj : ObjList) {
	    GraphDataModel count = new GraphDataModel();

	    count.setTotalCollection(Utility.convertQuintalsToTons(Utility.getBigDecimalValue(obj[0])));
	    count.setScanDate(Utility.formatDateToString((Date) obj[1], Constants.DATE_FORMAT));
	    count.setDateDone((Long) obj[2]);
	    scanCountModel.setUnit(Constants.WEIGHT_UNIT);
	    list.add(count);
	}

	scanCountModel.setGraphData(list);

	return scanCountModel;
    }

    //	public List<ScanCountModel> getPaymentOverTime(Long customerId2, Long commodityId, Long instCenterId, Long dateFrom,
    //			Long dateTo,  String deviceType, Long deviceTypeId) throws IMException {
    //
    //		List<ScanCountModel> list = new ArrayList<ScanCountModel>();
    //		Long customerId = null;
    //		List<Object[]> scanObject = null;
    //		// RequestContext requestContext = serverContext.getRequestContext();
    //
    //		if (Constants.CUSTOMER_TYPE_PROVIDER.equals(serverContext.getRequestContext().getCustomerType())) {
    //			if (customerId2 == null)
    //				throw new IMException(Constants.ErrorCode.CUSTOMER_ID_NOT_PROVIDED_CODE,
    //						Constants.ErrorMessage.CUSTOMER_ID_NOT_PROVIDED);
    //			customerId = customerId2;
    //
    //		} else if (Constants.CUSTOMER_TYPE_CUSTOMER.equals(serverContext.getRequestContext().getCustomerType())) {
    //			customerId = serverContext.getRequestContext().getCustomerId();
    //
    //		}
    //
    //		scanObject = filterRepo.getPaymentDataPerDay( commodityId, instCenterId, dateFrom, dateTo, customerId,
    //				deviceType, deviceTypeId);
    //		for (Object[] obj : scanObject) {
    //			ScanCountModel count = new ScanCountModel();
    //			count.setTotalCollection(Utility.formatDecimal((Double) obj[0]));
    //			count.setScanDate(Utility.formatDateToString((Date) obj[1], Constants.DATE_FORMAT));
    //			count.setDateDone((Long) obj[2]);
    //			list.add(count);
    //
    //		}
    //
    //		return list;
    //	}
    //
    //	public List<ScanCountModel> getPaymentOverTime(Long customerId, Long commodityId, Long instCenterId, Long dateFrom,
    //			Long dateTo, Long regionId) {
    //
    //		return null;
    //	}
    //
    //	public List<ScanCountModel> getPaymentChartData(Long customerId2, Long commodityId, Long instCenterId,
    //			Long dateFrom, Long dateTo, Long regionId, String deviceType, Long deviceTypeId) throws IMException {
    //		Long customerId = null;
    //		Double paymentSum = 0.00;
    //
    //		List<ScanCountModel> list = new ArrayList<ScanCountModel>();
    //		List<Object[]> scanObject = null;
    //		if (Constants.CUSTOMER_TYPE_PROVIDER.equals(serverContext.getRequestContext().getCustomerType())) {
    //
    //			if (customerId2 == null)
    //				throw new IMException(Constants.ErrorCode.CUSTOMER_ID_NOT_PROVIDED_CODE,
    //						Constants.ErrorMessage.CUSTOMER_ID_NOT_PROVIDED);
    //			customerId = customerId2;
    //
    //		} else if (Constants.CUSTOMER_TYPE_CUSTOMER.equals(serverContext.getRequestContext().getCustomerType())) {
    //
    //			customerId = serverContext.getRequestContext().getCustomerId();
    //		}
    //
    //		scanObject = filterRepo.getPaymentChartData(customerId, commodityId, instCenterId, dateFrom, dateTo, regionId,
    //				deviceType, deviceTypeId);
    //
    //		for (Object[] obj : scanObject) {
    //			ScanCountModel scModel = new ScanCountModel();
    //			scModel.setTotalPaymemnt((Double) obj[0]);
    //			scModel.setInstCenterId((Long) obj[1]);
    //			list.add(scModel);
    //		}
    //
    //		return list;
    //	}
    //
    //	public List<PaymentListModel> getPaymentList(Integer pageNumber, Integer limit, Long customerId2, Long commodityId,
    //			Long instCenterId, Long dateFrom, Long dateTo, Long regionId, String deviceType, Long deviceTypeId)
    //			throws IMException {
    //
    //		List<ScanEntity> scanEntityList = new ArrayList<ScanEntity>();
    //
    //		Long customerId = null;
    //
    //		if (Constants.CUSTOMER_TYPE_PROVIDER.equals(serverContext.getRequestContext().getCustomerType())) {
    //			if (customerId2 == null)
    //				throw new IMException(Constants.ErrorCode.CUSTOMER_ID_NOT_PROVIDED_CODE,
    //						Constants.ErrorMessage.CUSTOMER_ID_NOT_PROVIDED);
    //			customerId = customerId2;
    //
    //		} else if (Constants.CUSTOMER_TYPE_CUSTOMER.equals(serverContext.getRequestContext().getCustomerType())) {
    //			customerId = serverContext.getRequestContext().getCustomerId();
    //
    //		}
    //
    //		List<PaymentListModel> paymentList = new ArrayList<PaymentListModel>();
    //
    //		scanEntityList = filterRepo.getPaymentCollectionList(pageNumber, limit, customerId, commodityId, instCenterId,
    //				dateFrom, dateTo, regionId, deviceType, deviceTypeId);
    //		for (ScanEntity scanEntity : scanEntityList) {
    //
    //			System.out.println(" scanEntity : " + scanEntity.toString());
    //			PaymentListModel payment = new PaymentListModel();
    //			payment.setFarmerName(" ");
    //			payment.setFarmerId(scanEntity.getFarmerId());
    //			payment.setCompanyName(" ");
    //			payment.setCompanyId(scanEntity.getCustomerId());
    //			payment.setCommodityName(" ");
    //			payment.setCommodityId(scanEntity.getCommodityId());
    //			payment.setPayment_date(scanEntity.getCreatedOn());
    //			payment.setPaymentAmount(Utility.formatDecimal(scanEntity.getAmount()));
    //			paymentList.add(payment);
    //
    //		}
    //		return paymentList;
    //
    //	}

    public CollectionDetailsModel getCollectionDetailsModified(Integer pageNumber, Integer limit, Long customerId2,
	    Long commodityId, Long instCenterId, Long dateFrom, Long dateTo, Long instCenterTypeId,
	    Long commodityCategoryId, String deviceType, Long deviceTypeId, String deviceSerialNo, String keyword,
	    Long stateId) throws IMException {

	BigDecimal previousScanDataForInst = new BigDecimal(0.0);
	BigDecimal currentScanDataForInst = new BigDecimal(0.0);
	List<String> deviceTypeList = null;
	List<String> uniqueDeviceTypeList = null;
	List<String> uniqueDate = null;
	Long startfromMillis = null;
	Long endToMillis = null;
	BigDecimal totalInstQuantity = new BigDecimal(0.0);
	List<String> uniqueDates = null;

	Long customerId = setCustomerId(customerId2);
	Long operatorId = setOperatorId();
	Long userId = null;
	Long stateAdmin = applicationContext.getRequestContext().getStateAdmin();

	BigDecimal percentage = new BigDecimal(0.0);
	BigDecimal difference = new BigDecimal(0.0);
	BigDecimal totalQuantity = new BigDecimal(0.0);
	BigDecimal commodityData = new BigDecimal(0.0);

	BigDecimal previousScanDataDT = new BigDecimal(0.0);
	BigDecimal currentScanDataDT = new BigDecimal(0.0);
	BigDecimal percentageDT = new BigDecimal(0.0);
	BigDecimal differenceDT = new BigDecimal(0.0);
	BigDecimal totalQuantityDT = new BigDecimal(0.0);
	BigDecimal totalPerDeviceTypePerDayDT = new BigDecimal(0.0);
	BigDecimal totalQuantityBasedOnSearch = new BigDecimal(0.0);

	List<Object[]> totalPerDeviceTypePerDay = null;

	List<Long> instCenterIdList = new ArrayList<>();

	List<Long> instCenterIdNewList = new ArrayList<Long>();

	List<String> uniqueDeviceType = new ArrayList<String>();

	List<Long> uniqueCIds = new ArrayList<Long>();

	List<Object[]> deviceWithSearch = new ArrayList<>();

	List<CommodityCollectionModel> cCModuleList = new ArrayList<CommodityCollectionModel>();
	List<ScanLocation> location = new ArrayList<ScanLocation>();
	CollectionDetailsModel collectionDetails = new CollectionDetailsModel();
	List<InstallationCenterDetails> installationCenterDetailList = new ArrayList<InstallationCenterDetails>();

	if (instCenterId != null) {
	} else if (instCenterId == null) {

	    List<Long> scanDataLocationIds = filterRepo.getLocationIds(customerId, commodityCategoryId, dateFrom,
		    dateTo, operatorId, stateAdmin, commodityId, stateId);
	    scanDataLocationIds = uniqueInstCenterIds(scanDataLocationIds);

	    if (scanDataLocationIds.isEmpty()) {
		scanDataLocationIds = null;
	    }

	    List<ScanLocation> locationList = new ArrayList<>();
	    List<ScanLocation> locationListWithSearch = scanLocationRepo.findLocationsWithSearch(scanDataLocationIds,
		    keyword);

	    if (locationListWithSearch.size() != 0) {
		locationList.addAll(locationListWithSearch);
	    } else {
		List<ScanLocation> locationListWithoutSearch = scanLocationRepo
			.findAllByIdAndStatusStatusId(scanDataLocationIds, Constants.STATUS.ACTIVE.getId());
		locationList.addAll(locationListWithoutSearch);
	    }

	    if (scanDataLocationIds == null || scanDataLocationIds.isEmpty()) {
		return null;
	    }
	    totalQuantity = filterRepo.getTotalQuantityForTheCustomerByCommodityCategory(customerId,
		    commodityCategoryId, dateFrom, dateTo, deviceSerialNo, operatorId, stateAdmin, commodityId,
		    stateId);
	    // location = scanLocationRepo.findByDcmByIds(instCenterIdList);
	    Long previousFromDateInMillis = getPreviousTime(dateFrom, dateTo);

	    for (ScanLocation scanLocation : locationList) {
		if (!scanLocation.getLocationName().equals("CDD3")) {
		    List<DeviceTypeDataModel> deviceTypeDataModelList = new ArrayList<DeviceTypeDataModel>();
		    InstallationCenterDetails installationCenterDetails = new InstallationCenterDetails();

		    totalInstQuantity = filterRepo.getTotalQuantityForInstCenter(dateFrom, dateTo, scanLocation.getId(),
			    customerId, commodityCategoryId, operatorId, stateAdmin, commodityId, stateId);
		    // if (totalInstQuantity != null && totalInstQuantity != 0.0) {
		    previousScanDataForInst = filterRepo.getDateWiseQuantityModified(pageNumber, limit, customerId,
			    commodityId, scanLocation.getId(), previousFromDateInMillis, dateFrom, null,
			    commodityCategoryId, null, null, operatorId, stateAdmin, stateId);
		    currentScanDataForInst = filterRepo.getDateWiseQuantityModified(pageNumber, limit, customerId,
			    commodityId, scanLocation.getId(), dateFrom, dateTo, null, commodityCategoryId, null, null,
			    operatorId, stateAdmin, stateId);
		    if (currentScanDataForInst != null && previousScanDataForInst != null) {
			difference = currentScanDataForInst.subtract(previousScanDataForInst);
			if (previousScanDataForInst.doubleValue() != 0.0) {
			    percentage = difference.multiply(
				    BigDecimal.valueOf(100).divide(previousScanDataForInst, 2, RoundingMode.DOWN));
			}
			installationCenterDetails.setDifference(Utility.formatDecimal(difference));
			installationCenterDetails.setDifferencePercentage(Utility.formatDecimal(percentage));
			if (previousScanDataForInst.doubleValue() == 0.0
				&& currentScanDataForInst.doubleValue() != 0.0) {
			    installationCenterDetails.setDifferencePercentage(new BigDecimal(100.0));
			} else if (previousScanDataForInst.doubleValue() == 0.0
				&& currentScanDataForInst.doubleValue() == 0.0) {
			    installationCenterDetails.setDifferencePercentage(new BigDecimal(0.0));
			}
		    }

		    deviceTypeList = filterRepo.getDeviceTypeByFilter(pageNumber, limit, customerId, commodityId,
			    scanLocation.getId(), dateFrom, dateTo, null, commodityCategoryId, null, null, stateAdmin,
			    stateId);

		    uniqueDeviceTypeList = getUniqueDeviceTypes(deviceTypeList);

		    for (String deviceTypeName : uniqueDeviceTypeList) {
			List<Object[]> dList = new ArrayList<>();
			deviceWithSearch = deviceRepo.getDetailWithSearch(deviceTypeName, keyword);

			//dList.addAll(deviceWithSearch);

			if (deviceWithSearch.size() == 0) {
			    dList.addAll(deviceRepo.getDetailWithoutSearch(deviceTypeName));
			} else {
			    dList.addAll(deviceWithSearch);
			}

			for (Object[] ob : dList) {

			    DeviceTypeDataModel deviceTypeDataModel = new DeviceTypeDataModel();
			    totalQuantityDT = filterRepo.getTotalQuantityForDeviceSerialNumber(pageNumber, limit,
				    customerId, scanLocation.getId(), dateFrom, dateTo, commodityId, (String) ob[2],
				    commodityCategoryId, operatorId, stateAdmin, stateId);
			    String check = deviceRepo.checkDeviceInInstalationCenter((String) ob[2],
				    scanLocation.getId());
			    if (check != null || totalQuantityDT != null) {
				System.out.println(" deviceTypeName : " + deviceTypeName + " " + " totalQuantityDT : "
					+ totalQuantityDT + " inst center id : " + scanLocation.getId());
				previousScanDataDT = filterRepo.getDateWiseDeviceTypeData(pageNumber, limit, customerId,
					commodityId, scanLocation.getId(), previousFromDateInMillis, dateFrom, null,
					deviceTypeName, commodityCategoryId, (String) ob[2], operatorId, stateAdmin,
					stateId);
				currentScanDataDT = filterRepo.getDateWiseDeviceTypeData(pageNumber, limit, customerId,
					commodityId, scanLocation.getId(), dateFrom, dateTo, null, deviceTypeName,
					commodityCategoryId, (String) ob[2], operatorId, stateAdmin, stateId);
				logger.debug("Current Data and Previous data for device : " + (String) ob[2] + " is : "
					+ currentScanDataDT + " and : " + previousScanDataDT + " respectively ");
				if (currentScanDataDT != null && previousScanDataDT != null) {
				    differenceDT = currentScanDataDT.subtract(previousScanDataDT);
				    if (previousScanDataDT.doubleValue() != 0.0) {
					percentageDT = differenceDT.multiply(BigDecimal.valueOf(100)
						.divide(previousScanDataDT, 2, RoundingMode.DOWN));
				    }

				    deviceTypeDataModel.setDifference(Utility.formatDecimal(differenceDT));
				    deviceTypeDataModel.setDifferencePercentage(Utility.formatDecimal(percentageDT));
				    if (previousScanDataDT.doubleValue() == 0.0
					    && currentScanDataDT.doubleValue() != 0.0) {
					deviceTypeDataModel.setDifferencePercentage(new BigDecimal(100.0));
				    } else if (previousScanDataDT.doubleValue() == 0.0
					    && currentScanDataDT.doubleValue() == 0.0) {
					deviceTypeDataModel.setDifferencePercentage(new BigDecimal(0.0));
				    }
				}

				deviceTypeDataModel.setDeviceTypeName(deviceTypeName);
				deviceTypeDataModel.setDeviceId((Long) ob[0]);
				deviceTypeDataModel.setDeviceSerialNo((String) ob[2]);
				deviceTypeDataModel.setDeviceTypeId((Long) ob[1]);
				if (totalQuantityDT != null) {
				    deviceTypeDataModel.setTotalQuantity(
					    Utility.convertQuintalsToTons(Utility.getBigDecimalValue(totalQuantityDT)));
				    totalQuantityBasedOnSearch = totalQuantityBasedOnSearch.add(totalQuantityDT);
				} else {
				    deviceTypeDataModel.setTotalQuantity(BigDecimal.valueOf(0));
				}

				deviceTypeDataModel.setQuantityUnit(Constants.WEIGHT_UNIT);

				totalPerDeviceTypePerDay = filterRepo.getCollectionsQuantityPerDayByDeviceType(
					commodityId, scanLocation.getId(), startfromMillis, endToMillis, customerId,
					deviceTypeName, commodityCategoryId, (String) ob[2], operatorId, stateAdmin,
					stateId);
				Map<String, BigDecimal> deviceTypeDailyData = new HashMap<>();
				for (Object[] obj : totalPerDeviceTypePerDay) {
				    if (obj[0] != null) {
					deviceTypeDailyData.put(
						(Utility.formatDateToString((Date) obj[1], Constants.DATE_FORMAT)),
						(Utility.formatDecimal(Utility.getBigDecimalValue(obj[0]))));
				    } else {
					deviceTypeDailyData.put(
						(Utility.formatDateToString((Date) obj[1], Constants.DATE_FORMAT)),
						new BigDecimal(0.0));
				    }

				}

				deviceTypeDataModel.setDailyData(deviceTypeDailyData);
				deviceTypeDataModelList.add(deviceTypeDataModel);
			    }
			}
		    }
		    installationCenterDetails.setDeviceTypeDataModel(deviceTypeDataModelList);
		    // }

		    if (totalInstQuantity != null) {
			installationCenterDetails
				.setTotalQuantity(Utility.convertQuintalsToTons(Utility.getBigDecimalValue(totalInstQuantity)));
		    } else {
			totalInstQuantity = BigDecimal.valueOf(0);
		    }

		    installationCenterDetails.setQuantityUnit(Constants.WEIGHT_UNIT);
		    installationCenterDetails.setDeviceTypes(uniqueDeviceType);
		    installationCenterDetails.setInstCenterName(scanLocation.getLocationName());
		    installationCenterDetails.setWarehouseName(scanLocation.getWarehouseName());
		    installationCenterDetails.setCode(scanLocation.getCode());

		    installationCenterDetails.setInstCenterId(scanLocation.getId());
		    //			installationCenterDetails
		    //					.setInstCenterTypeName(scanLocation.getDcmCommercialLocationType().getInstCenterTypeDesc());
		    //			installationCenterDetails.setInstCenterTypeId(scanLocation.getDcmCommercialLocationType().getId());

		    if (keyword != null && locationListWithSearch.isEmpty() && deviceTypeDataModelList.isEmpty()) {

		    } else if (keyword != null && locationListWithSearch.isEmpty() && deviceWithSearch.isEmpty()) {

		    } else if (keyword != null && !locationListWithSearch.isEmpty()
			    && deviceTypeDataModelList.isEmpty()) {
			installationCenterDetailList.add(installationCenterDetails);
		    } else if (keyword != null && locationListWithSearch.isEmpty()
			    && !deviceTypeDataModelList.isEmpty()) {
			installationCenterDetailList.add(installationCenterDetails);
		    } else {
			installationCenterDetailList.add(installationCenterDetails);
		    }
		} else if (scanLocation.getLocationName().equals("CDD3")
			&& applicationContext.getRequestContext().getUserEmail().equals("ai@agnext.in")) {
		    List<DeviceTypeDataModel> deviceTypeDataModelList = new ArrayList<DeviceTypeDataModel>();
		    InstallationCenterDetails installationCenterDetails = new InstallationCenterDetails();

		    totalInstQuantity = filterRepo.getTotalQuantityForInstCenter(dateFrom, dateTo, scanLocation.getId(),
			    192L, commodityCategoryId, operatorId, stateAdmin, commodityId, stateId);
		    // if (totalInstQuantity != null && totalInstQuantity != 0.0) {
		    previousScanDataForInst = filterRepo.getDateWiseQuantityModified(pageNumber, limit, 192L,
			    commodityId, scanLocation.getId(), previousFromDateInMillis, dateFrom, null,
			    commodityCategoryId, null, null, operatorId, stateAdmin, stateId);
		    currentScanDataForInst = filterRepo.getDateWiseQuantityModified(pageNumber, limit, 192L,
			    commodityId, scanLocation.getId(), dateFrom, dateTo, null, commodityCategoryId, null, null,
			    operatorId, stateAdmin, stateId);
		    if (currentScanDataForInst != null && previousScanDataForInst != null) {
			difference = currentScanDataForInst.subtract(previousScanDataForInst);
			if (previousScanDataForInst.doubleValue() != 0.0) {
			    percentage = difference.multiply(
				    BigDecimal.valueOf(100).divide(previousScanDataForInst, 2, RoundingMode.DOWN));
			}
			installationCenterDetails.setDifference(Utility.formatDecimal(difference));
			installationCenterDetails.setDifferencePercentage(Utility.formatDecimal(percentage));
			if (previousScanDataForInst.doubleValue() == 0.0
				&& currentScanDataForInst.doubleValue() != 0.0) {
			    installationCenterDetails.setDifferencePercentage(new BigDecimal(100.0));
			} else if (previousScanDataForInst.doubleValue() == 0.0
				&& currentScanDataForInst.doubleValue() == 0.0) {
			    installationCenterDetails.setDifferencePercentage(new BigDecimal(0.0));
			}
		    }

		    deviceTypeList = filterRepo.getDeviceTypeByFilter(pageNumber, limit, 192L, commodityId,
			    scanLocation.getId(), dateFrom, dateTo, null, commodityCategoryId, null, null, stateAdmin,
			    stateId);

		    uniqueDeviceTypeList = getUniqueDeviceTypes(deviceTypeList);

		    for (String deviceTypeName : uniqueDeviceTypeList) {
			List<Object[]> dList = new ArrayList<>();
			deviceWithSearch = deviceRepo.getDetailWithSearch(deviceTypeName, keyword);

			//dList.addAll(deviceWithSearch);

			if (deviceWithSearch.size() == 0) {
			    dList.addAll(deviceRepo.getDetailWithoutSearch(deviceTypeName));
			} else {
			    dList.addAll(deviceWithSearch);
			}

			for (Object[] ob : dList) {

			    DeviceTypeDataModel deviceTypeDataModel = new DeviceTypeDataModel();
			    totalQuantityDT = filterRepo.getTotalQuantityForDeviceSerialNumber(pageNumber, limit, 192L,
				    scanLocation.getId(), dateFrom, dateTo, commodityId, (String) ob[2],
				    commodityCategoryId, operatorId, stateAdmin, stateId);
			    String check = deviceRepo.checkDeviceInInstalationCenter((String) ob[2],
				    scanLocation.getId());
			    if (check != null || totalQuantityDT != null) {
				System.out.println(" deviceTypeName : " + deviceTypeName + " " + " totalQuantityDT : "
					+ totalQuantityDT + " inst center id : " + scanLocation.getId());
				previousScanDataDT = filterRepo.getDateWiseDeviceTypeData(pageNumber, limit, 192L,
					commodityId, scanLocation.getId(), previousFromDateInMillis, dateFrom, null,
					deviceTypeName, commodityCategoryId, (String) ob[2], operatorId, stateAdmin,
					stateId);
				currentScanDataDT = filterRepo.getDateWiseDeviceTypeData(pageNumber, limit, 192L,
					commodityId, scanLocation.getId(), dateFrom, dateTo, null, deviceTypeName,
					commodityCategoryId, (String) ob[2], operatorId, stateAdmin, stateId);
				logger.debug("Current Data and Previous data for device : " + (String) ob[2] + " is : "
					+ currentScanDataDT + " and : " + previousScanDataDT + " respectively ");
				if (currentScanDataDT != null && previousScanDataDT != null) {
				    differenceDT = currentScanDataDT.subtract(previousScanDataDT);
				    if (previousScanDataDT.doubleValue() != 0.0) {
					percentageDT = differenceDT.multiply(BigDecimal.valueOf(100)
						.divide(previousScanDataDT, 2, RoundingMode.DOWN));
				    }

				    deviceTypeDataModel.setDifference(Utility.formatDecimal(differenceDT));
				    deviceTypeDataModel.setDifferencePercentage(Utility.formatDecimal(percentageDT));
				    if (previousScanDataDT.doubleValue() == 0.0
					    && currentScanDataDT.doubleValue() != 0.0) {
					deviceTypeDataModel.setDifferencePercentage(new BigDecimal(100.0));
				    } else if (previousScanDataDT.doubleValue() == 0.0
					    && currentScanDataDT.doubleValue() == 0.0) {
					deviceTypeDataModel.setDifferencePercentage(new BigDecimal(0.0));
				    }
				}

				deviceTypeDataModel.setDeviceTypeName(deviceTypeName);
				deviceTypeDataModel.setDeviceId((Long) ob[0]);
				deviceTypeDataModel.setDeviceSerialNo((String) ob[2]);
				deviceTypeDataModel.setDeviceTypeId((Long) ob[1]);
				if (totalQuantityDT != null) {
				    deviceTypeDataModel.setTotalQuantity(
					    Utility.convertQuintalsToTons(Utility.getBigDecimalValue(totalQuantityDT)));
				    totalQuantityBasedOnSearch = totalQuantityBasedOnSearch.add(totalQuantityDT);
				} else {
				    deviceTypeDataModel.setTotalQuantity(BigDecimal.valueOf(0));
				}

				deviceTypeDataModel.setQuantityUnit(Constants.WEIGHT_UNIT);

				totalPerDeviceTypePerDay = filterRepo.getCollectionsQuantityPerDayByDeviceType(
					commodityId, scanLocation.getId(), startfromMillis, endToMillis, 192L,
					deviceTypeName, commodityCategoryId, (String) ob[2], operatorId, stateAdmin,
					stateId);
				Map<String, BigDecimal> deviceTypeDailyData = new HashMap<>();
				for (Object[] obj : totalPerDeviceTypePerDay) {
				    if (obj[0] != null) {
					deviceTypeDailyData.put(
						(Utility.formatDateToString((Date) obj[1], Constants.DATE_FORMAT)),
						(Utility.formatDecimal(Utility.getBigDecimalValue(obj[0]))));
				    } else {
					deviceTypeDailyData.put(
						(Utility.formatDateToString((Date) obj[1], Constants.DATE_FORMAT)),
						new BigDecimal(0.0));
				    }

				}

				deviceTypeDataModel.setDailyData(deviceTypeDailyData);
				deviceTypeDataModelList.add(deviceTypeDataModel);
			    }
			}
		    }
		    installationCenterDetails.setDeviceTypeDataModel(deviceTypeDataModelList);
		    // }

		    if (totalInstQuantity != null) {
			installationCenterDetails
				.setTotalQuantity(Utility.convertQuintalsToTons(Utility.getBigDecimalValue(totalInstQuantity)));
		    } else {
			totalInstQuantity = BigDecimal.valueOf(0);
		    }

		    installationCenterDetails.setQuantityUnit(Constants.WEIGHT_UNIT);
		    installationCenterDetails.setDeviceTypes(uniqueDeviceType);
		    installationCenterDetails.setInstCenterName(scanLocation.getLocationName());

		    installationCenterDetails.setInstCenterId(scanLocation.getId());
		    //			installationCenterDetails
		    //					.setInstCenterTypeName(scanLocation.getDcmCommercialLocationType().getInstCenterTypeDesc());
		    //			installationCenterDetails.setInstCenterTypeId(scanLocation.getDcmCommercialLocationType().getId());

		    if (keyword != null && locationListWithSearch.isEmpty() && deviceTypeDataModelList.isEmpty()) {

		    } else if (keyword != null && locationListWithSearch.isEmpty() && deviceWithSearch.isEmpty()) {

		    } else if (keyword != null && !locationListWithSearch.isEmpty()
			    && deviceTypeDataModelList.isEmpty()) {
			installationCenterDetailList.add(installationCenterDetails);
		    } else if (keyword != null && locationListWithSearch.isEmpty()
			    && !deviceTypeDataModelList.isEmpty()) {
			installationCenterDetailList.add(installationCenterDetails);
		    } else {
			installationCenterDetailList.add(installationCenterDetails);
		    }

		}
	    }

	    collectionDetails.setInstallationCenterDetails(installationCenterDetailList);
	    //if (totalQuantity != null) {
	    if (totalQuantityBasedOnSearch != null) {

		collectionDetails.setTotalQuantity(
			String.valueOf(Utility.convertQuintalsToTons(Utility.getBigDecimalValue(totalQuantity))));
		collectionDetails.setQuantityUnit(Constants.WEIGHT_UNIT);
	    } else {
		collectionDetails.setTotalQuantity(String.valueOf(0.0));
		collectionDetails.setQuantityUnit(Constants.WEIGHT_UNIT);

	    }
	}

	/************************ for collections *****************************/
	try {
	    //    List<Long> cIds = filterRepo.getCommodityIds(pageNumber, limit, customerId, null, dateFrom, dateTo,
	    //	    regionId, commodityCategoryId);
	    List<Object[]> cIds = filterRepo.getCommodityCollection(commodityId, instCenterId, dateFrom, dateTo,
		    customerId, commodityCategoryId, deviceType, deviceTypeId, deviceSerialNo, operatorId, stateAdmin,
		    stateId);
	    if (cIds != null) {
		// uniqueCIds = uniqueInstCenterIds(cIds);

		CollectionModel collection = new CollectionModel();
		LinkedHashMap<String, BigDecimal> commulativeData = new LinkedHashMap<>();
		/******************* for each commodity *****************/
		for (Object[] com : cIds) {

		    List<String> dailyData = new ArrayList<String>();
		    Map<String, BigDecimal> data = null;

		    CommodityCollectionModel commColModel = new CommodityCollectionModel();
		    // Object[] commData = filterRepo.getCommodityDataByCommodityId(regionId,
		    // comIds, instCenterId,
		    // dateFrom, dateTo, customerId, commodityCategoryId,deviceSerialNo);
		    // String commodityName= filterRepo.getScanDetailsByCommodityId(comIds);

		    commColModel.setCommodityId((Long) com[3]);
		    commColModel.setCommodityName((String) com[1]);

		    if ((com[0] != null) && (Utility.getBigDecimalValue(com[0])).doubleValue() != 0) {
			// {
			commColModel.setTotal(Utility.convertQuintalsToTons(Utility.getBigDecimalValue(com[0])));
			commColModel.setUnit(Constants.WEIGHT_UNIT);
			data = new HashMap<String, BigDecimal>();
			commColModel.setDailyData(data);
			cCModuleList.add(commColModel);
		    }
		}
		List<Object[]> commodityDataObj = filterRepo.getCollectionsQuantityPerDay(commodityId, instCenterId,
			dateFrom, dateTo, customerId, deviceType, deviceTypeId, commodityCategoryId, deviceSerialNo,
			operatorId, stateAdmin, stateId);
		for (Object[] obj : commodityDataObj) {
		    commulativeData.put((Utility.formatDateToString((Date) obj[1], Constants.DATE_FORMAT)),
			    Utility.formatDecimal(Utility.getBigDecimalValue(obj[0])));
		}

		collection.setCommodities(cCModuleList);
		collection.setDailyData(commulativeData);
		collectionDetails.setCollection(collection);

	    }
	} catch (NumberFormatException e) {
	    e.printStackTrace();

	}

	return collectionDetails;
    }

    private List<Long> uniqueInstCenterIds(List<Long> instCenterIdList) {

	List<Long> installationCenterIdList = new ArrayList<Long>();
	Set<Long> instCenterIds = new HashSet<>(instCenterIdList);
	if (instCenterIdList != null && !instCenterIdList.isEmpty())
	    for (Long long1 : instCenterIds) {
		if (long1 != null) {
		    installationCenterIdList.add(long1);
		}
	    }
	return installationCenterIdList;
    }

    private Long getPreviousTime(Long dateFrom, Long dateTo) {

	Date dateFrom1 = new Date(dateFrom);
	Date dateTo1 = new Date(dateTo);
	long duration = dateTo1.getTime() - dateFrom1.getTime();
	long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
	LocalDateTime ldt = LocalDateTime.ofInstant(dateFrom1.toInstant(), ZoneId.systemDefault());
	LocalDateTime ldt2 = ldt.minusDays(diffInDays);

	ZoneId zoneId = ZoneId.systemDefault();
	long previousFromDate = ldt2.atZone(zoneId).toEpochSecond();
	long previousFromDateMilli = TimeUnit.SECONDS.toMillis(previousFromDate);

	return previousFromDateMilli;
    }

    private List<String> getUniqueDeviceTypes(List<String> deviceTypeList) {

	List<String> uniqueDeviceType = new ArrayList<String>();
	Set<String> deviceTypeUnique = new HashSet<>(deviceTypeList);

	for (String string : deviceTypeUnique) {

	    if (string != null && !string.isEmpty())
		uniqueDeviceType.add(string);

	}
	return uniqueDeviceType;
    }

    private List<String> getUniqueDateSet(List<Long> dates) {
	List<String> scDateq = new ArrayList<>();
	List<String> uniqueDateList = new ArrayList<String>();
	Set<Long> scanDate = new HashSet<>(dates);
	Collections.sort(dates, new DateComparator());
	for (Long sDate : dates) {
	    Date currentDate = new Date(sDate);
	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	    scDateq.add(df.format(currentDate));
	}
	Set<String> scDate = new LinkedHashSet<String>(scDateq);

	for (String string : scDate) {

	    if (string != null && !string.isEmpty())
		uniqueDateList.add(string);

	}
	return uniqueDateList;
    }

    private Long getEpochFromString(String date) throws ParseException {
	String myDate1 = date;
	Date date11 = new SimpleDateFormat("MM/dd/yyyy").parse(myDate1);
	long millisFrom = date11.getTime();
	date11.toInstant().getEpochSecond();
	Long startFrom = Long.valueOf(date11.toInstant().getEpochSecond() + "000");
	return startFrom;
    }

    public CollectionDetailsUniqueModel getCollectionDetailsByIdModified(Integer pageNumber, Integer limit,
	    Long customerId2, Long commodityId, Long instCenterId, Long dateFrom, Long dateTo, Long instCenterTypeId,
	    Long commodityCategoryId, String deviceType, Long deviceTypeId, String deviceSerialNo, Long stateId)
	    throws IMException {
	CollectionDetailsUniqueModel collDUM = new CollectionDetailsUniqueModel();
	// Double previousScanDataForInst = 0.0;
	// Double currentScanDataForInst = 0.0;
	// List<String> deviceTypeList = null;
	// List<String> uniqueDeviceTypeList = null;
	// List<String> uniqueDate = null;
	// Long startfromMillis = null;
	// Long endToMillis = null;
	// Double totalInstQuantity = 0.0;
	// List<String> uniqueDates = null;

	Long customerId = setCustomerId(customerId2);
	Long operatorId = setOperatorId();
	Long stateAdmin = applicationContext.getRequestContext().getStateAdmin();

	// Double percentage = 0.0;
	// Double difference = 0.0;
	Object[] ObjArr = null;
	// Double commodityData = 0.0;

	// Double previousScanDataDT = 0.0;
	// Double currentScanDataDT = 0.0;
	// Double percentageDT = 0.0;
	// Double differenceDT = 0.0;
	// Double totalQuantityDT = 0.0;
	// Double totalPerDeviceTypePerDayDT = 0.0;

	// List<Object[]> totalPerDeviceTypePerDay = null;
	//
	// List<Long> instCenterIdList = null;
	//
	// List<Long> instCenterIdNewList = new ArrayList<Long>();
	//
	// List<String> uniqueDeviceType = new ArrayList<String>();

	List<Long> uniqueCIds = new ArrayList<Long>();

	List<CommodityCollectionModel> cCModuleList = new ArrayList<CommodityCollectionModel>();
	// List<DcmCommercialLocation> dcmCommercialLocation = new
	// ArrayList<DcmCommercialLocation>();
	// CollectionDetailsModel collectionDetails = new CollectionDetailsModel();
	// List<InstallationCenterDetails> installationCenterDetailList = new
	// ArrayList<InstallationCenterDetails>();

	ObjArr = filterRepo.getTotalQuantityForInstCenterByCommodityCategory(customerId, commodityCategoryId, dateFrom,
		dateTo, instCenterId, deviceSerialNo, operatorId, commodityId, stateId);

	if (ObjArr != null && ObjArr[0] != null) {
	    collDUM.setTotalQuantity(String.valueOf(Utility.convertQuintalsToTons(Utility.getBigDecimalValue(ObjArr[0]))));
	}
	collDUM.setQuantityUnit(Constants.WEIGHT_UNIT);

	/*************************************
	 * For Collection
	 ************************/
	try {
	    List<Long> cIds = filterRepo.getCommodityIdsByInstCenter(pageNumber, limit, customerId, dateFrom, dateTo,
		    commodityCategoryId, instCenterId, deviceType, deviceTypeId, instCenterTypeId, deviceSerialNo,
		    operatorId, commodityId, stateId);
	    if (cIds != null) {
		uniqueCIds = uniqueInstCenterIds(cIds);

		CollectionModel collection = new CollectionModel();
		LinkedHashMap<String, BigDecimal> commulativeData = new LinkedHashMap<>();
		/******************* for each commodity *****************/
		for (Long comIds : uniqueCIds) {

		    List<String> dailyData = new ArrayList<String>();
		    Map<String, BigDecimal> data = null;

		    CommodityCollectionModel commColModel = new CommodityCollectionModel();
		    Object[] commData = filterRepo.getCommodityDataByCommodityId(comIds, instCenterId, dateFrom, dateTo,
			    customerId, commodityCategoryId, deviceSerialNo, operatorId, stateId);
		    // String commodityName= filterRepo.getScanDetailsByCommodityId(comIds);
		    commColModel.setCommodityId(comIds);
		    commColModel.setCommodityName((String) commData[1]);
		    commColModel.setTotal(Utility.convertQuintalsToTons(Utility.getBigDecimalValue(commData[0])));
		    commColModel.setUnit(Constants.WEIGHT_UNIT);
		    data = new HashMap<String, BigDecimal>();

		    commColModel.setDailyData(data);
		    cCModuleList.add(commColModel);
		}
		List<Object[]> commodityDataObj = filterRepo.getCollectionsQuantityPerDay(commodityId, instCenterId,
			dateFrom, dateTo, customerId, deviceType, deviceTypeId, commodityCategoryId, deviceSerialNo,
			operatorId, stateAdmin, stateId);
		for (Object[] obj : commodityDataObj) {
		    logger.debug(" Id :  " + obj[3] + " Created on date :  " + (Date) obj[1] + " & Weight :  "
			    + Utility.getBigDecimalValue(obj[0]));
		    commulativeData.put((Utility.formatDateToString((Date) obj[1], Constants.DATE_FORMAT)),
			    Utility.formatDecimal(Utility.getBigDecimalValue(obj[0])));
		}

		collection.setCommodities(cCModuleList);
		collection.setDailyData(commulativeData);
		collDUM.setCollection(collection);

	    }
	} catch (NumberFormatException e) {
	    e.printStackTrace();

	}

	return collDUM;
    }

    public FarmerDetailsModel getClientDetailById(Integer pageNumber, Integer limit, Long customerId2, Long commodityId,
	    Long dateFrom, Long dateTo, Long instCenterId, Long farmerId, String analysisCode, String deviceType,
	    Long deviceTypeId) throws IMException {
	Long customerId = setCustomerId(customerId2);
	Long operatorId = setOperatorId();
	List<GraphDataModel> list = new ArrayList<GraphDataModel>();
	List<GraphDataModel> qualityList = new ArrayList<GraphDataModel>();
	FarmerDetailsModel farmerDetailModel = new FarmerDetailsModel();

	try {
	    if (dateFrom == null || dateTo == null) {
		throw new IMException(Constants.ErrorCode.DATE_NOT_FOUND, Constants.ErrorMessage.DATE_NOT_FOUND);
	    }

	    BigDecimal previousScanData = new BigDecimal(0.0);
	    BigDecimal currentScanData = new BigDecimal(0.0);
	    BigDecimal percentage = new BigDecimal(0.0);
	    BigDecimal difference = new BigDecimal(0.0);

	    BigDecimal previousQualityScanData = new BigDecimal(0.0);
	    BigDecimal currentQualityScanData = new BigDecimal(0.0);
	    BigDecimal qualityPercentage = new BigDecimal(0.0);
	    BigDecimal qualityDifference = new BigDecimal(0.0);
	    BigDecimal qualitySum = new BigDecimal(0.0);

	    Date dateFrom1 = new Date(dateFrom);
	    Date dateTo1 = new Date(dateTo);
	    long duration = dateTo1.getTime() - dateFrom1.getTime();
	    long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
	    LocalDateTime ldt = LocalDateTime.ofInstant(dateFrom1.toInstant(), ZoneId.systemDefault());
	    LocalDateTime ldt2 = ldt.minusDays(diffInDays);

	    ZoneId zoneId = ZoneId.systemDefault();
	    long previousFromDate = ldt2.atZone(zoneId).toEpochSecond();
	    long previousFromDateMilli = TimeUnit.SECONDS.toMillis(previousFromDate);

	    previousScanData = filterRepo.getDateWiseQuantityNew(commodityId, instCenterId, previousFromDateMilli,
		    dateFrom, null, farmerId, deviceType, deviceTypeId, operatorId);
	    currentScanData = filterRepo.getDateWiseQuantityNew(commodityId, instCenterId, dateFrom, dateTo, null,
		    farmerId, deviceType, deviceTypeId, operatorId);

	    //			previousQualityScanData = filterRepo.getDateWiseQualityNew(regionId, commodityId, instCenterId,
	    //					previousFromDateMilli, dateFrom, farmerId, analysisCode, customerId, deviceType, deviceTypeId);
	    //
	    //			currentQualityScanData = filterRepo.getDateWiseQualityNew(regionId, commodityId, instCenterId, dateFrom,
	    //					dateTo, farmerId, analysisCode, customerId, deviceType, deviceTypeId);

	    if (currentScanData != null && previousScanData != null) {
		difference = currentScanData.subtract(previousScanData);
		if (previousScanData.doubleValue() != 0.0) {
		    percentage = difference.multiply(BigDecimal.valueOf(100)).divide(previousScanData, 2,
			    RoundingMode.DOWN);
		}
		farmerDetailModel.setQuantityDifference(Utility.convertQuintalsToTons(difference));
		farmerDetailModel.setQuantityDifferencePercentage(Utility.formatDecimal(percentage));
		if (previousScanData.doubleValue() == 0.0 && currentScanData.doubleValue() != 0.0) {
		    farmerDetailModel.setQuantityDifferencePercentage(new BigDecimal(100.0));
		} else if (previousScanData.doubleValue() == 0.0 && currentScanData.doubleValue() == 0.0) {
		    farmerDetailModel.setQuantityDifferencePercentage(new BigDecimal(0.0));
		}
	    }
	    farmerDetailModel.setUnit(Constants.WEIGHT_UNIT);
	    /***************
	     * quality difference and %
	     ***************************************************/
	    //			if (currentQualityScanData != null && previousQualityScanData != null) {
	    //				qualityDifference = currentQualityScanData - previousQualityScanData;
	    //				if (previousQualityScanData != 0.0) {
	    //					qualityPercentage = qualityDifference * 100 / previousQualityScanData;
	    //				}
	    //				farmerDetailModel.setQualityDifference(Utility.formatDecimal(qualityDifference));
	    //				farmerDetailModel.setQualityDifferencePercentage(Utility.formatDecimal(qualityPercentage));
	    //				if (previousQualityScanData == 0.0 && currentQualityScanData != 0.0) {
	    //					farmerDetailModel.setQualityDifferencePercentage(100.0);
	    //				} else if (previousQualityScanData == 0.0 && currentQualityScanData == 0.0) {
	    //					farmerDetailModel.setQualityDifferencePercentage(0.0);
	    //				}
	    //			}
	    /**************************************************************************************************/

	    List<Long> dates = filterRepo.getDatesForFarmer(commodityId, instCenterId, dateFrom, dateTo, null, farmerId,
		    deviceType, deviceTypeId, operatorId);
	    List<String> scDateq = new ArrayList<>();
	    Set<Long> scanDate = new HashSet<>(dates);
	    // List<Long> dateList = new ArrayList<>(scanDate);
	    Collections.sort(dates, new DateComparator());
	    System.out.print(dates);
	    for (Long sDate : dates) {
		Date currentDate = new Date(sDate);
		System.out.print(currentDate);
		System.out.print(currentDate.toInstant().getEpochSecond());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		System.out.print(df.format(currentDate));
		scDateq.add(df.format(currentDate));

		// scDate.add(df.format(currentDate));
	    }
	    Set<String> scDate = new LinkedHashSet<String>(scDateq);
	    list = new ArrayList<>();
	    for (String sDate : scDate) {
		String myDate1 = sDate;
		Date date11 = new SimpleDateFormat("MM/dd/yyyy").parse(myDate1);
		System.out.println(myDate1 + "\t" + date11);

		long millisFrom = date11.getTime();
		date11.toInstant().getEpochSecond();
		System.out.print(date11.toInstant().getEpochSecond());
		Long startFrom = Long.valueOf(date11.toInstant().getEpochSecond() + "000");

		Long endTo = startFrom + 86400000;

		BigDecimal sum = filterRepo.getCollectionsQuantityPerDayByFarmerId(commodityId, instCenterId, startFrom,
			endTo, null, farmerId, deviceType, deviceTypeId, operatorId);
		//				qualitySum = filterRepo.getCollectionsQualityPerDayByFarmerId(regionId, commodityId, instCenterId,
		//						startFrom, endTo, farmerId, customerId, analysisCode, deviceType, deviceTypeId);
		GraphDataModel count = new GraphDataModel();
		GraphDataModel qualityData = new GraphDataModel();
		qualityData.setDateDone(startFrom);
		qualityData.setScanDate(sDate);
		qualityData.setTotalCollection(qualitySum);
		count.setScanDate(sDate);
		count.setDateDone(startFrom);
		count.setTotalCollection(Utility.formatDecimal(sum));
		list.add(count);
		qualityList.add(qualityData);

	    }
	    farmerDetailModel.setQuantityGraphData(list);
	    farmerDetailModel.setQualityGraphData(qualityList);
	    return farmerDetailModel;
	} catch (NumberFormatException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new IMException("SQL Error 12002", " Exception At Scan Details By Id  API");
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new IMException("SQL Error 12003", " Exception At Scan Details By Id  API");
	}

    }

    //	public List<CommodityAnalyticModel> getAnalytics(Long customerId, Long commodityId, String analyticCode,
    //			String deviceType, Long deviceTypeId) {
    //		List<CommodityAnalyticModel> response = new ArrayList<>();
    //		List<Long> scanIds = filterRepo.getAnalytics(commodityId, customerId, deviceType, deviceTypeId);
    //		if(scanIds!=null && !scanIds.isEmpty()) {
    //		List<String> analyticName = analyticResultRepo.getAnlyticName(scanIds);
    //	
    //		
    //		for (String anlytic : analyticName) {
    //			CommodityAnalyticModel c = new CommodityAnalyticModel();
    //			c.setAnalyticCode(anlytic);
    //			c.setAnalyticName(anlytic);
    //			response.add(c);
    //		}}
    //		return response;
    //	}
    //	
    private Long setUserId(Long userId2) throws IMException {
	logger.info(" Inside setUserId method, the User id to set is : " + userId2);
	Long userId = null;
	//		if (Constants.CustomerType.SERVICE_PROVIDER.equals(applicationContext.getRequestContext().getCustomerType())) {
	//			if (userId2 == null)
	//				throw new IMException(Constants.ErrorCode.CUSTOMER_ID_NOT_PROVIDED,
	//						Constants.ErrorMessage.CUSTOMER_ID_NOT_PROVIDED);
	//
	userId = userId;
	//
	//		} else if (Constants.CustomerType.CUSTOMER.equals(applicationContext.getRequestContext().getCustomerType())) {
	//			userId = applicationContext.getRequestContext().getUserId();
	//
	//		}
	return null;
    }

    public List<CommodityAnalyticModel> getAnalytics(Long customerId, Long commodityId, String analyticCode,
	    String deviceType, Long deviceTypeId) {
	Long operatorId = setOperatorId();
	List<CommodityAnalyticModel> response = new ArrayList<>();
	List<Long> scanIds = filterRepo.getAnalytics(commodityId, customerId, deviceType, deviceTypeId, operatorId);
	if (scanIds != null && !scanIds.isEmpty()) {
	    List<String> analyticName = analyticResultRepo.getAnlyticName(scanIds);

	    for (String anlytic : analyticName) {
		CommodityAnalyticModel c = new CommodityAnalyticModel();
		c.setAnalyticCode(anlytic);
		c.setAnalyticName(anlytic);
		response.add(c);
	    }
	}
	return response;
    }

    public CommodityDeviceDropDownModel getCommodityDeviceDropDown() {
	List<Long> commodityIds = scanRepo.getAllCommodityId();

	List<String> deviceSN = scanRepo.findAllDeviceSNByCommodityIds(commodityIds);

	CommodityDeviceDropDownModel cDDM = new CommodityDeviceDropDownModel();
	List<CommodityNewModel> commodities = new ArrayList<CommodityNewModel>();
	for (Long cId : commodityIds) {
	    CommodityNewModel commodity = new CommodityNewModel();
	    commodity.setCommodityId(cId);
	    commodity.setCommodityName(commodityRepository.getCommodityName(cId));

	    List<DeviceSerialNumber> dSNs = new ArrayList<>();
	    for (String string : deviceSN) {
		DeviceSerialNumber dSN = new DeviceSerialNumber();
		dSN.setDeviceSerialNumber(string);
		dSNs.add(dSN);
	    }
	    commodity.setDevices(dSNs);
	    commodities.add(commodity);

	}
	cDDM.setCommodities(commodities);

	return cDDM;
    }

	public void updateScan(ScanModel postData, 	Long scanId) throws IMException {
		ScanEntity scn = scanRepo.getOne(scanId);
		if (scn == null) {
		    throw new IMException(Constants.ErrorCode.NO_RECORD_FORUND, Constants.ErrorMessage.NO_RECORD_FORUND);
		}
		Optional.ofNullable(postData.getSocietyName()).ifPresent(scn::setSocietyName);
//		WeightConverterModel weightDetails = new WeightConverterModel();
//		weightDetails = Utility.postWeightConverter(postData.getWeight(), postData.getQuantityUnit());
//		logger.debug("********* Weight : " + weightDetails.getWeight() + " Weight unit : " + weightDetails.getUnit());
//		scn.setWeight(weightDetails.getWeight());
//		scn.setQuantityUnit(weightDetails.getUnit());
		Optional.ofNullable(postData.getTruckNumber()).ifPresent(scn::setTruckNumber);
		Optional.ofNullable(postData.getNumberOfBags()).ifPresent(scn::setBag);
		Optional.ofNullable(postData.getAcceptedBags()).ifPresent(scn::setAcceptedBags);
		Optional.ofNullable(postData.getRejectedBags()).ifPresent(scn::setRejectedBags);
		Optional.ofNullable(postData.getQuantity()).ifPresent(scn::setQuantity);
		scanRepo.save(scn);
		
	}

	@Autowired
	UserAnalyticLinkRepository userLinkRepo;
	public UserLinkModel getUserLinks() {
		
		Long userId=null;
		
		if(applicationContext.getRequestContext().getRoles().contains("state_admin")) {
			userId=applicationContext.getRequestContext().getStateAdmin();
		
		}else {
		userId= applicationContext.getRequestContext().getUserId();
		}
		
		if (userId ==null) {
			UserEntity user=userRepo.findByUserEmail(applicationContext.getRequestContext().getUserEmail());
			
			if(user !=null)
				logger.info(" User details extracted by user_email ");
			userId=user.getUserId();
		}
		
		UserLinkModel linkDetails= new UserLinkModel();
		UserAnalyticLink userLinks= new UserAnalyticLink();
		userLinks=userLinkRepo.findByUserId(userId);
		
		 if(userLinks !=null) {
			 List<String> linkList= new ArrayList<String>();
			 linkDetails.setUserId(userLinks.getUserId());
			 linkList.add(userLinks.getLink());
			 linkDetails.setLinks(linkList);
			 linkDetails.setId(userLinks.getId());
			 linkDetails.setStatus(Constants.STATUS.getAbbr(userLinks.getStatus()));
			 linkDetails.setRole(applicationContext.getRequestContext().getRoles().iterator().next());
			 
		 }
		
		
		
		return linkDetails;
	}

	public WarehouseDetailsModel getLocationAnalyticsDetails(Integer pageNumber, Integer limit, Long commodityId,
			Long dateFrom, Long dateTo, Long commodityCategoryId, String deviceType, Long deviceTypeId,
			String deviceSerialNo, String keyword, Long stateId, String locationName, String districtName) throws IMException {
		WarehouseDetailsModel warehouseDetailsModel = new WarehouseDetailsModel();
		List<WarehouseData> warehouseDataList = new ArrayList<WarehouseData>();
		Long customerId = setCustomerId();
		Long operatorId = setOperatorId();
		Long stateAdmin = applicationContext.getRequestContext().getStateAdmin();

		List<Object[]> dataObjList = filterRepo.getWarehouseData(pageNumber,limit,customerId, commodityCategoryId, dateFrom, dateTo,
				operatorId, stateAdmin, commodityId, stateId,keyword,locationName,districtName);

		for (Object[] obj : dataObjList) {
			WarehouseData warehouseData = new WarehouseData();
			warehouseData.setWarehouseId((Long) obj[0]);
			warehouseData.setWarehouseName((String) obj[1]);
			warehouseData.setLocationName((String) obj[2]);
			warehouseData.setWarehouseCode((String) obj[3]);
			warehouseData.setTotalQuantity(Utility.convertQuintalsToTons((BigDecimal) obj[4]));
			warehouseData.setQuantityUnit(Constants.WEIGHT_UNIT);
			warehouseData.setDeviceSerialNo((String) obj[5]);
			// set Daily Graph Data
			List<Object[]> warehouseDailyData = filterRepo.getWarehouseDailyData((Long) obj[0],null);
			LinkedHashMap<String, BigDecimal> warehouseDailyDataMap = new LinkedHashMap<>();
			for (Object[] objects : warehouseDailyData) {
				if (objects[0] != null) {
					warehouseDailyDataMap.put((Utility.formatDateToString((Date) objects[1], Constants.DATE_FORMAT)),
							(Utility.convertQuintalsToTons(Utility.getBigDecimalValue(objects[0]))));
				} else {
					warehouseDailyDataMap.put((Utility.formatDateToString((Date) objects[1], Constants.DATE_FORMAT)),
							new BigDecimal(0.0));
				}
			}
			warehouseData.setDailyData(warehouseDailyDataMap);
			warehouseDataList.add(warehouseData);

		}
		warehouseDetailsModel.setWarehouseData(warehouseDataList);

		return warehouseDetailsModel;
	}

	private Long setCustomerId() throws IMException {
		logger.info(" Inside setCustomerId method, Request Context : " + applicationContext.getRequestContext());
		Long customerId = null;
		customerId = applicationContext.getRequestContext().getCustomerId();
//	if (Constants.CustomerType.CLIENT.equals(applicationContext.getRequestContext().getCustomerType())) {
//		customerId = applicationContext.getRequestContext().getCustomerId();
//	    }
//
//	 else if (Constants.CustomerType.CUSTOMER.equals(applicationContext.getRequestContext().getCustomerType())) {
//	    customerId = null;
//
//	} else if (applicationContext.getRequestContext().getRoles().contains("state_admin")) {
//	    customerId = applicationContext.getRequestContext().getCustomerId();
//	}
		return customerId;
	}

	public TotalCollectionsModel getTotalCollections(Long commodityId, Long dateFrom, Long dateTo,
			Long commodityCategoryId, String deviceSerialNo, String keyword, Long stateId, String districtName) throws IMException {
		TotalCollectionsModel totalCollectionResponse = new TotalCollectionsModel();

		List<TotalCollectionModel> totalCollectionsList = new ArrayList<TotalCollectionModel>();
		TotalCollectionModel totalCollectionModel = new TotalCollectionModel();
		Long customerId = setCustomerId();
		Long operatorId = setOperatorId();
		Long stateAdmin = applicationContext.getRequestContext().getStateAdmin();

		BigDecimal totalCollection = filterRepo.getTotalCollections(customerId, commodityCategoryId, dateFrom, dateTo,
				operatorId, stateAdmin, commodityId, stateId, keyword, districtName);
		totalCollectionModel.setTotalCollection(Utility.convertQuintalsToTons(totalCollection));
		totalCollectionModel.setCollectionUnit(Constants.WEIGHT_UNIT);
		// set Commodity Wise Data
		List<Object[]> commodityWiseCollection = filterRepo.getCommodityWiseCollection(customerId, commodityCategoryId,
				dateFrom, dateTo, operatorId, stateAdmin, commodityId, stateId, keyword, districtName);
		LinkedHashMap<String, BigDecimal> commodityWiseTotalCollectionDailyDataMap = new LinkedHashMap<>();
		for (Object[] objects : commodityWiseCollection) {
			if (objects[0] != null) {
				commodityWiseTotalCollectionDailyDataMap.put((String) objects[1],
						(Utility.convertQuintalsToTons(Utility.getBigDecimalValue(objects[2]))));
			} else {
				commodityWiseTotalCollectionDailyDataMap.put((String) objects[1], new BigDecimal(0.0));
			}
		}
		totalCollectionModel.setCommodityWiseCollection(commodityWiseTotalCollectionDailyDataMap);
//		totalCollectionsList.add(totalCollectionModel);
		totalCollectionResponse.setTotalCollectionModel(totalCollectionsList);
		List<Object[]> collectionDailyData = filterRepo.getTotalCollectionsDailyData(customerId, commodityCategoryId,
				dateFrom, dateTo, operatorId, stateAdmin, commodityId, stateId, keyword, districtName);
		
//		for (Object[] obj : collectionDailyData) {
//			System.out.println();
//			System.out.println(" Date List are as follows : "+ (Date) obj[0]);
//		}
		LinkedHashMap<String, BigDecimal> totalCollectionDailyDataMap = new LinkedHashMap<>();
		for (Object[] obj : collectionDailyData) {
			if (obj[0] != null) {
				totalCollectionDailyDataMap.put((Utility.formatDateToString((Date) obj[0], Constants.DATE_FORMAT)),
						(Utility.convertQuintalsToTons(Utility.getBigDecimalValue(obj[1]))));
			} else {
				totalCollectionDailyDataMap.put((Utility.formatDateToString((Date) obj[0], Constants.DATE_FORMAT)),
						new BigDecimal(0.0));
			}
		}
		totalCollectionModel.setDailyData(totalCollectionDailyDataMap);
		totalCollectionsList.add(totalCollectionModel);

		return totalCollectionResponse;
	}
}
