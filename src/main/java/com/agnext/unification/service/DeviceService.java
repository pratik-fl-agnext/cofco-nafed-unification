package com.agnext.unification.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
/*
 * Device Service Class
 * 
 * @author piyush.r
 * @version 1.0
 * 
 */
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agnext.unification.assembler.EntityToVOAssembler;
import com.agnext.unification.common.Constants;
import com.agnext.unification.config.RequestContext;
import com.agnext.unification.config.ServerContext;
import com.agnext.unification.entity.nafed.CustomerEntity;
import com.agnext.unification.entity.nafed.DcmCommodity;
import com.agnext.unification.entity.nafed.DcmDevice;
import com.agnext.unification.entity.nafed.DcmDeviceOrder;
import com.agnext.unification.entity.nafed.DcmDeviceType;
import com.agnext.unification.entity.nafed.DeviceCommodityEntity;
import com.agnext.unification.entity.nafed.DeviceCommodityPurchased;
import com.agnext.unification.entity.nafed.ScanLocation;
import com.agnext.unification.entity.nafed.UserDetails;
import com.agnext.unification.entity.nafed.UserEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.CommodityModel;
import com.agnext.unification.model.CustomerDeviceSubscriptionModel;
import com.agnext.unification.model.DeviceGroupModel;
import com.agnext.unification.model.DeviceModel;
import com.agnext.unification.model.DeviceOrderModel;
import com.agnext.unification.model.DeviceSensorProfileModel;
import com.agnext.unification.model.DeviceSubTypeModel;
import com.agnext.unification.model.DeviceTypeModel;
import com.agnext.unification.model.UserDetailModel;
import com.agnext.unification.repository.nafed.CustomerRepository;
import com.agnext.unification.repository.nafed.DcmCommodityRepository;
import com.agnext.unification.repository.nafed.DcmDeviceGroupRepository;
import com.agnext.unification.repository.nafed.DcmDeviceSubTypeRepository;
import com.agnext.unification.repository.nafed.DcmDeviceTypeRepository;
import com.agnext.unification.repository.nafed.DcmSensorProfileRepository;
import com.agnext.unification.repository.nafed.DeviceCommodityPurchasedRepository;
import com.agnext.unification.repository.nafed.DeviceCommodityRepository;
import com.agnext.unification.repository.nafed.DeviceRepository;
import com.agnext.unification.repository.nafed.FilterRepository;
import com.agnext.unification.repository.nafed.StateManagerOperatorRepository;
import com.agnext.unification.repository.nafed.StatusRepository;
import com.agnext.unification.repository.nafed.UserDetailsRepository;
import com.agnext.unification.repository.nafed.UserRepository;
import com.agnext.unification.utility.Utility;
import com.agnext.unification.validator.BaseValidator;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class DeviceService extends GenericService {

    private static Logger logger = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    DeviceRepository repo;

    @Autowired
    FilterRepository filterRepo;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    DcmCommodityRepository commodityRepo;

    @Autowired
    DcmDeviceGroupRepository deviceGroupRepo;
    @Autowired
    DcmDeviceSubTypeRepository dcmDeviceSubTypeRepo;
    @Autowired
    DcmSensorProfileRepository sensorProfileRepo;

    @Autowired
    DcmDeviceGroupRepository dcmDeviceGroupRepo;
    @Autowired
    DcmDeviceTypeRepository deviceTypeRepo;

    @Autowired
    ServerContext serverContext;

    @Autowired
    BaseValidator validator;

    @Autowired
    StatusRepository statusRepo;

    @Autowired
    DeviceCommodityPurchasedRepository commodityPurchasedRepo;

    @Autowired
    DeviceCommodityRepository deviceCommodityRepo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StateManagerOperatorRepository smoRepo;

    @Autowired
    UserDetailsRepository userDetailsRepo;

    public List<DeviceModel> getDevicesByFilter(String keyword, Integer pageNumber, Integer limit, Long customerId2,
	    String operationType2) throws Exception {
	logger.info("get device list by applied filter");
	Long customerId = null;
	if (customerId2 != null) {
	    customerId = setCustomerId(customerId2);
	}
	List<DeviceModel> deviceModelList = new ArrayList<>();

	String operationType = null;
	if (applicationContext.getRequestContext().getCustomerType()
		.equalsIgnoreCase(Constants.CustomerType.SERVICE_PROVIDER)) {
	    operationType = "Inventory";
	}

	List<Object[]> testObjList = filterRepo.deviceKeywordFilter(keyword, pageNumber, limit, customerId,
		operationType);
	Long count = filterRepo.deviceKeywordFilterCount(keyword, customerId, operationType);
	if (testObjList != null && !testObjList.isEmpty()) {
	    deviceModelList = fillDeviceModel(testObjList, operationType, count);
	    return deviceModelList;
	}
	return null;
    }

    //	private Long setCustomerId(Long customerId2) throws IMException {
    //		logger.info(" Inside setCustomerId method, the customer id to set is : " + customerId2);
    //		System.out.println(" Request Context : " + applicationContext.getRequestContext());
    //		Long customerId = null;
    //		if (Constants.CustomerType.SERVICE_PROVIDER.equals(applicationContext.getRequestContext().getCustomerType())) {
    //			if (customerId2 == null)
    //				throw new IMException(Constants.ErrorCode.CUSTOMER_ID_NOT_PROVIDED,
    //						Constants.ErrorMessage.CUSTOMER_ID_NOT_PROVIDED);
    //
    //			customerId = customerId2;
    //
    //		} else if (Constants.CustomerType.CUSTOMER.equals(applicationContext.getRequestContext().getCustomerType())) {
    //			customerId = applicationContext.getRequestContext().getCustomerId();
    //
    //		}
    //		return customerId;
    //	}

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

	}else if(applicationContext.getRequestContext().getRoles().contains("state_admin")) {
		customerId=applicationContext.getRequestContext().getCustomerId();
	}
	return customerId;
    }

	public List<DeviceModel> fillDeviceModel(List<Object[]> objList, String operationType, Long count) {

		List<DeviceModel> deviceModelList = new ArrayList<DeviceModel>();

		if (operationType != null && !operationType.isEmpty()) {

			if (operationType.equalsIgnoreCase(Constants.INVENTORY)) {
				if (null != objList && !objList.isEmpty()) {

					objList.stream().forEach(obj -> {
						DeviceModel deviceModel = new DeviceModel();

						Optional.ofNullable(obj[0]).ifPresent(deviceId -> deviceModel.setDeviceId((Long) deviceId));

						Optional.ofNullable(obj[1])
								.ifPresent(serialNo -> deviceModel.setSerialNumber((String) serialNo));

						if (obj[2] != null) {
							Optional.ofNullable(obj[2])
									.ifPresent(customerUuid -> deviceModel.setCustomerId((Long) customerUuid));
							// String custmorName = restCalls.getCustomerName((Long) obj[2], token);
							System.out.println(" Customer Id : " + (Long) obj[2]);
							if ((Long) obj[2] != null) {
								CustomerEntity customer = customerRepo.getOne((Long) obj[2]);
								if (customer != null && customer.getCustomerName() != null
										&& !customer.getCustomerName().isEmpty())
									deviceModel.setCustomerName(customer.getCustomerName());
							}
						}

						// if (obj[3] != null) {
						// Optional.ofNullable(obj[3])
						// .ifPresent(deviceId -> deviceModel.setUserUuid((String) deviceId));
						// }

						if (obj[3] != null) {
							Optional.ofNullable(obj[3])
									.ifPresent(deviceId -> deviceModel.setDeviceTypeId((Long) deviceId));
						}

						if (obj[4] != null) {
							Optional.ofNullable(obj[4])
									.ifPresent(deviceId -> deviceModel.setDeviceType((String) deviceId));
						}

						// if (obj[6] != null) {
						// Optional.ofNullable(obj[6]).ifPresent(
						// deviceSubTypeId -> deviceModel.setDeviceSubTypeId((Long) deviceSubTypeId));
						// }

						// if(obj[7]!=null) {
						// Optional.ofNullable(obj[7])
						// .ifPresent(deviceSubTypeDesc -> deviceModel.setDeviceSubTypeDesc((String)
						// deviceSubTypeDesc));
						// }

						Optional.ofNullable((Long) obj[13]).ifPresent(deviceModel::setCreatedOn);
						Optional.ofNullable(obj[5])
								.ifPresent(startOfLife -> deviceModel.setStartOfLife((Long) startOfLife));
						Optional.ofNullable(obj[6]).ifPresent(endOfLife -> deviceModel.setEndOfLife((Long) endOfLife));

						Optional.ofNullable(obj[7])
								.ifPresent(startOfService -> deviceModel.setStartOfService((Long) startOfService));
						Optional.ofNullable(obj[8])
								.ifPresent(endOfService -> deviceModel.setEndOfService((Long) endOfService));

						Optional.ofNullable(obj[9])
								.ifPresent(fwRevision -> deviceModel.setFwRevision((String) fwRevision));
						Optional.ofNullable(obj[10])
								.ifPresent(swRevision -> deviceModel.setHwRevision((String) swRevision));

						Optional.ofNullable(obj[11]).ifPresent(statusId -> deviceModel.setStatusId((Long) statusId));
						Optional.ofNullable(obj[12])
								.ifPresent(statusDesc -> deviceModel.setStatusDesc((String) statusDesc));
						if (count != null)
							Optional.ofNullable(count).ifPresent(deviceModel::setTotalCount);

						deviceModelList.add(deviceModel);

					});

				}
			}
		}

		else {
			if (null != objList && !objList.isEmpty()) {

				objList.stream().forEach(obj -> {
					DeviceModel deviceModel = new DeviceModel();

					Optional.ofNullable(obj[0]).ifPresent(deviceId -> deviceModel.setDeviceId((Long) deviceId));

					Optional.ofNullable(obj[1]).ifPresent(serialNo -> deviceModel.setSerialNumber((String) serialNo));

					if (obj[2] != null) {
						Optional.ofNullable(obj[2])
								.ifPresent(customerUuid -> deviceModel.setCustomerId((Long) customerUuid));
						// String custmorName = restCalls.getCustomerName((Long) obj[2], token);
						System.out.println(" Customer Id : " + (Long) obj[2]);
						if ((Long) obj[2] != null) {
							CustomerEntity customer = customerRepo.getOne((Long) obj[2]);
							if (customer != null && customer.getCustomerName() != null
									&& !customer.getCustomerName().isEmpty())
								deviceModel.setCustomerName(customer.getCustomerName());
						}
					}

					// if (obj[3] != null) {
					// Optional.ofNullable(obj[3])
					// .ifPresent(deviceId -> deviceModel.setUserUuid((String) deviceId));
					// }

					if (obj[3] != null) {
						Optional.ofNullable(obj[3]).ifPresent(deviceId -> deviceModel.setDeviceTypeId((Long) deviceId));
					}

					if (obj[4] != null) {
						Optional.ofNullable(obj[4]).ifPresent(deviceId -> deviceModel.setDeviceType((String) deviceId));
					}

					// if (obj[6] != null) {
					// Optional.ofNullable(obj[6]).ifPresent(
					// deviceSubTypeId -> deviceModel.setDeviceSubTypeId((Long) deviceSubTypeId));
					// }

					// if(obj[7]!=null) {
					// Optional.ofNullable(obj[7])
					// .ifPresent(deviceSubTypeDesc -> deviceModel.setDeviceSubTypeDesc((String)
					// deviceSubTypeDesc));
					// }

					Optional.ofNullable((Long) obj[13]).ifPresent(deviceModel::setCreatedOn);
					Optional.ofNullable(obj[5])
							.ifPresent(startOfLife -> deviceModel.setStartOfLife((Long) startOfLife));
					Optional.ofNullable(obj[6]).ifPresent(endOfLife -> deviceModel.setEndOfLife((Long) endOfLife));

					Optional.ofNullable(obj[7])
							.ifPresent(startOfService -> deviceModel.setStartOfService((Long) startOfService));
					Optional.ofNullable(obj[8])
							.ifPresent(endOfService -> deviceModel.setEndOfService((Long) endOfService));

					Optional.ofNullable(obj[9]).ifPresent(fwRevision -> deviceModel.setFwRevision((String) fwRevision));
					Optional.ofNullable(obj[10])
							.ifPresent(swRevision -> deviceModel.setHwRevision((String) swRevision));

					Optional.ofNullable(obj[11]).ifPresent(statusId -> deviceModel.setStatusId((Long) statusId));
					Optional.ofNullable(obj[12])
							.ifPresent(statusDesc -> deviceModel.setStatusDesc((String) statusDesc));
					if (count != null)
						Optional.ofNullable(count).ifPresent(deviceModel::setTotalCount);

					deviceModelList.add(deviceModel);

				});

			}
		}

		return deviceModelList;
	}

	public List<DeviceOrderModel> getDeviceOrder(Integer pageNumber, Integer limit, String searchKeyword) {
		logger.info("get Device order list");
		Sort sort = Sort.by(Direction.DESC, "id");
		List<DeviceOrderModel> deviceOrderModels = new ArrayList<>();
		Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(Constants.ZERO),
				Optional.ofNullable(limit).orElse(Constants.RECORD_LIMIT), sort);

		List<DcmDeviceOrder> dcmDeviceOrders = new ArrayList<>();
		Long count = new Long(0L);

		if (Constants.CustomerType.CUSTOMER.equals(applicationContext.getRequestContext().getCustomerType())) {
			// dcmDeviceOrders =
			// deviceOrderRepo.findByCustomerId(serverContext.getRequestContext().getCustomerId(),
			// pageable);
			dcmDeviceOrders = filterRepo.findCustomerOrders(serverContext.getRequestContext().getCustomerId(),
					pageNumber, limit, searchKeyword);
			count = filterRepo.findCustomerOrdersCount(serverContext.getRequestContext().getCustomerId(),
					searchKeyword);
		} else if (Constants.CustomerType.SERVICE_PROVIDER
				.equals(applicationContext.getRequestContext().getCustomerType())) {
			dcmDeviceOrders = filterRepo.findCustomerOrders(null, pageNumber, limit, searchKeyword);
			count = filterRepo.findCustomerOrdersCount(null, searchKeyword);
		}
		// Long count =deviceOrderRepo.count();

		if (dcmDeviceOrders != null) {

			for (DcmDeviceOrder dcmDeviceOrder : dcmDeviceOrders) {
				deviceOrderModels.add(convertDeviceOrder(dcmDeviceOrder, count));
			}
		}
		return deviceOrderModels;

	}

	private DeviceOrderModel convertDeviceOrder(DcmDeviceOrder deviceOrder, Long count) {
		DeviceOrderModel deviceOrderModel = new DeviceOrderModel();
		if (deviceOrder != null) {
			deviceOrderModel.setDeviceOrderId(deviceOrder.getId());
			deviceOrderModel.setCustomerId(deviceOrder.getCustomerId());
			deviceOrderModel.setDeviceCount(deviceOrder.getDeviceCount());
			deviceOrderModel.setDeviceTypeId(deviceOrder.getDcmDeviceType().getId());
			deviceOrderModel.setDeviceTypeDesc(deviceOrder.getDcmDeviceType().getDeviceTypeDesc());
			deviceOrderModel.setMode(deviceOrder.getMode());
			deviceOrderModel.setRemarks(deviceOrder.getRemarks());
			deviceOrderModel.setDeviceOrderRandomId(deviceOrder.getDeviceOrderRandomId());
			deviceOrderModel.setStatusId(deviceOrder.getStatus().getStatusId());
			deviceOrderModel.setStatusName(deviceOrder.getStatus().getStatusDesc());
			if (count != null)
				deviceOrderModel.setCount(count);
			if (deviceOrder.getCommodities() != null) {
				List<Long> commoditiesIds = new ArrayList<>();
				commoditiesIds.add(deviceOrder.getCommodities());

				List<DcmCommodity> dcmCommodities = commodityRepo.findByIdInAndStatusStatusId(commoditiesIds,
						Constants.STATUS.ACTIVE.getId());

				List<CommodityModel> models = new ArrayList<>();
				if (dcmCommodities != null) {
					for (DcmCommodity dcmCommodity : dcmCommodities) {
						CommodityModel commodityModel = new CommodityModel();
						commodityModel.setCommodityId(dcmCommodity.getId());
						commodityModel.setCommodityName(dcmCommodity.getCommodityName());
						commodityModel.setCommodityCode(dcmCommodity.getCommodityCode());
						commodityModel.setCommodityCategoryId(dcmCommodity.getDcmCommodityCategory().getId());
						commodityModel.setCommodityCategoryName(
								dcmCommodity.getDcmCommodityCategory().getCommodityCategoryName());
						models.add(commodityModel);
					}
				}

				deviceOrderModel.setCommodityModel(models);

			}

			return deviceOrderModel;
		}
		return deviceOrderModel;
	}

	public DeviceModel getDeviceById(Long id) throws Exception {

	Boolean isSubscribed = false;
	UserEntity user=null;
	UserEntity stateManager=null;
    List<DcmCommodity> deviceCommodities=null;
	List<DeviceCommodityPurchased> customerSubscribedList = commodityPurchasedRepo.findIfSubscribed(id);
	for (DeviceCommodityPurchased deviceCommodityPurchased : customerSubscribedList) {
	    if (deviceCommodityPurchased.getId() != null) {
		System.out.println(" Id :  " + deviceCommodityPurchased.getId() + " Date Time :  "
			+ Utility.convertLocalDateTimeIntoEpochMilli(deviceCommodityPurchased.getExpiredOn()));
		if (Utility.convertLocalDateTimeIntoEpochMilli(deviceCommodityPurchased.getExpiredOn()) > Instant.now()
			.getEpochSecond())
		    isSubscribed = true;
	    }
	}
	DcmDevice device=repo.findByDcmStatusStatusIdNotAndId(Constants.STATUS.DELETED.getId(), id);
	if(device !=null && device.getDcmStatus()!=null &&  device.getDcmStatus().getStatusId().equals(Constants.STATUS.ACTIVE.getId())) {
		 user= userRepository.findByUserIdAndStatusStatusId(device.getUserId(), Constants.STATUS.ACTIVE.getId());


	List<Long> deviceCommodityIds=deviceCommodityRepo.findByDeviceIdAndStatusId(device.getId(), (Constants.STATUS.ACTIVE.getId()).intValue());
    deviceCommodities=commodityRepo.findByIdInAndStatusStatusId(deviceCommodityIds, Constants.STATUS.ACTIVE.getId());
    Long stateManagerId=smoRepo.findStateManagerIdByOperatorId(device.getUserId());
    stateManager= userRepository.findByUserIdAndStatusStatusId(stateManagerId, Constants.STATUS.ACTIVE.getId());
	}

	return EntityToVOAssembler.fillDeviceModel(
			device, isSubscribed,user,deviceCommodities,stateManager);
    }

	public List<DeviceTypeModel> getAllDeviceTypes(Integer pageNumber, Integer limit) {
		logger.info("get all device type detail list");
		Sort sort = Sort.by(Direction.DESC, "id");
		Pageable pageable = PageRequest.of(Optional.ofNullable(pageNumber).orElse(Constants.ZERO),
				Optional.ofNullable(limit).orElse(Constants.RECORD_LIMIT), sort);
		return EntityToVOAssembler.convertDeviceTypeDetail(deviceTypeRepo.findAll());
	}

	public List<DeviceGroupModel> getDeviceGroups() {
		return EntityToVOAssembler.fillDeviceGroupModel(deviceGroupRepo.findAll());
	}

	public List<DeviceSubTypeModel> getDeviceSubTypes() {
		return EntityToVOAssembler.fillDeviceSubTypeModel(dcmDeviceSubTypeRepo.findAll());
	}

	public List<DeviceSensorProfileModel> getSensorProfiles() {
		return EntityToVOAssembler.fillDeviceSensorProfileModel(sensorProfileRepo.findAll());

	}

	public void saveDevices(DeviceModel deviceModel) throws IMException {
		logger.info("create device inventory ");
		DcmDevice device = new DcmDevice();
		// PushNotificationJSONData jSONData = new PushNotificationJSONData();
		// String deviceToken = null;
		// DcmUserDevice userDevice = new DcmUserDevice();
		long now = Instant.now().toEpochMilli();
		device.setCreatedOn(now);
//		if (deviceModel.getStartOfLife() >= deviceModel.getEndOfLife())
//			throw new IMException(Constants.ErrorCode.DEVICE_SOS_GREATER_THAN_EQUALS_TO_EOS,
//					Constants.ErrorMessage.DEVICE_SOS_GREATER_THAN_EQUAL_TO_EOS_MESSAGE);
//
		// userDevice =
		// userDeviceRepo.findByUserId(serverContext.getRequestContext().getUserId());
		// deviceToken = userDevice.getDeviceToken();

		DcmDevice deviceSerialNumberCheck = repo.findBySerialNumber(deviceModel.getSerialNumber());
		if (deviceSerialNumberCheck == null) {
			device.setSerialNumber(deviceModel.getSerialNumber());
		} else {
			throw new IMException(Constants.ErrorCode.SERIAL_NUMBER_EXIST, Constants.ErrorMessage.SERIAL_NUMBER_EXIST);
		}
		DcmDeviceType type = validator.validateDcmDeviceType(deviceModel.getDeviceTypeId());
		device.setDcmDeviceType(type);
		// if (type.getDeviceTypeDesc().equalsIgnoreCase(Constants.DeviceType.SENSOR)) {
		// if (deviceModel.getSensorProfileId() != null &&
		// deviceModel.getSensorProfileId() != 0) {
		// DcmSensorProfile dgroup =
		// validator.validateSensorProfile(deviceModel.getDeviceGroupId());
		// device.setSensorProfile(dgroup);
		// device.setSensorProfile(dcmSensorProfileRepo.getOne(deviceModel.getSensorProfileId()));
		// }
		//
		// if (deviceModel.getDeviceSubTypeId() != null) {
		// DcmDeviceSubType subtype =
		// validator.validateDeviceSubType(deviceModel.getDeviceSubTypeId());
		// device.setDeviceSubType(subtype);
		// }
		// if (deviceModel.getDeviceGroupId() != null) {
		// DcmDeviceGroup dgroup =
		// validator.validateDcmDeviceGroup(deviceModel.getDeviceGroupId());
		// device.setDeviceGroup(dgroup);
		// }
		// }
		device.setHwRevision("1");
		device.setStartOfService(Instant.now().toEpochMilli());
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 2);
		device.setEndOfLife(c.getTimeInMillis());
		device.setStartOfLife(Instant.now().toEpochMilli());
		device.setFwRevision(deviceModel.getFwRevision());
		device.setEndOfService(c.getTimeInMillis());
		// device.setVendorName(deviceModel.getVendorName());
		device.setDcmStatus(statusRepo.findByStatusDesc(Constants.DeviceStatus.INITIATED));
		repo.save(device);
		// if (deviceToken != null && !deviceToken.isEmpty()) {
		// jSONData.setBody("Device Added Successfull !!");
		// jSONData.setTitle("Save Device");
		// jSONData.setId(1L);
		// jSONData.setType("Mobile Notification");
		// try {
		// PushNotificaionService.sendPushNotification(1L, jSONData,
		// "AAAAHv8RqmQ:APA91bEPMXD3RltAAtZK0EIEa-lorMwa3AHW26qcBTnZP9TRQV69Rmfv2NLCSzzFlWJEYXzZ44QojKeuM8uB8Di40qRSO92u35uJZlJ38mAyNzxtYEkZhhoTC9Vkiw6HIJaut8pyN1Dt",
		// deviceToken, Constants.NOTIFICATION_SERVER_HOST_URL);
		// } catch (JsonProcessingException e) {
		// e.printStackTrace();
		// }
		// }
		//
	}

	public void updateDevices(DeviceModel deviceModel) throws IMException {
		logger.info("update device inventory ");
		DcmDevice device = repo.getOne(deviceModel.getDeviceId());
		if (deviceModel.getSerialNumber() != null && !deviceModel.getSerialNumber().isEmpty()) {
			device.setSerialNumber(deviceModel.getSerialNumber());
		}
		DcmDeviceType type = validator.validateDcmDeviceType(deviceModel.getDeviceTypeId());

	device.setDcmDeviceType(type);
	//		if (type.getDeviceTypeDesc().equalsIgnoreCase(Constants.DeviceType.SENSOR)) {
	//			if (deviceModel.getSensorProfileId() != null && deviceModel.getSensorProfileId() != 0) {
	//				DcmSensorProfile dgroup = validator.validateSensorProfile(deviceModel.getDeviceGroupId());
	//				device.setSensorProfile(dgroup);
	//			}
	//
	//			if (deviceModel.getDeviceSubTypeId() != null) {
	//
	//				DcmDeviceSubType subtype = validator.validateDeviceSubType(deviceModel.getDeviceSubTypeId());
	//
	//				device.setDeviceSubType(subtype);
	//			}
	//			if (deviceModel.getDeviceGroupId() != null) {
	//				DcmDeviceGroup dgroup = validator.validateDcmDeviceGroup(deviceModel.getDeviceGroupId());
	//
	//				device.setDeviceGroup(dgroup);
	//			}
	//
	//		}

	/*
	 * Code below is being commented bcoz we are controlling the device access and duration during licensing
	 */
//	device.setHwRevision(deviceModel.getHwRevision());
//	device.setStartOfService(deviceModel.getStartOfService());
//	device.setEndOfLife(deviceModel.getEndOfLife());
//	device.setStartOfLife(deviceModel.getStartOfLife());
//	device.setFwRevision(deviceModel.getFwRevision());
//	device.setEndOfService(deviceModel.getEndOfService());
	//		device.setVendorName(deviceModel.getVendorName());
	device.setDcmStatus(statusRepo.findByStatusDesc(Constants.DeviceStatus.INITIATED));
	// device.setDcmStatus(Constants.STATUS.ACTIVE.getId());

		repo.save(device);
	}

	public void provision(DeviceModel deviceModel) throws IMException {
		logger.info("device provisioning ");

	/*
	 * Code below is being commented bcoz we are controlling the device access and duration during licensing
	 */
//	if (deviceModel.getStartOfService() >= deviceModel.getEndOfService())
//	    throw new IMException(Constants.ErrorCode.DEVICE_SOS_GREATER_THAN_EQUALS_TO_EOS,
//		    Constants.ErrorMessage.DEVICE_SOS_GREATER_THAN_EQUAL_TO_EOS_MESSAGE);
	//		else if(deviceModel.getStartOfService() == deviceModel.getEndOfService())
	//			throw new DCMException(Constants.ErrorCode.DEVICE_SOS_GREATER_THAN_EOS,
	//					Constants.ErrorMessage.DEVICE_SOS_GREATER_THAN_EOS_MESSAGE);

	DcmDevice device = repo.getOne(deviceModel.getDeviceId());
	if (deviceModel.getCustomerId() != null) {
	    device.setCustomerId(deviceModel.getCustomerId());
	}
	if (deviceModel.getUserUuid() != null && !deviceModel.getUserUuid().isEmpty()) {
	    device.setUserUuid(deviceModel.getUserUuid());
	}

	device.setUserId(deviceModel.getUserId());

	if (deviceModel.getCommercialLocationId() != null) {
	    ScanLocation location = validator.validateDcmCommercialLocation(deviceModel.getCommercialLocationId());
	    device.setScanLocation(location);
	}
//	device.setHwRevision(deviceModel.getHwRevision());
//	device.setStartOfService(deviceModel.getStartOfService());
//	device.setFwRevision(deviceModel.getFwRevision());
//	device.setEndOfService(deviceModel.getEndOfService());
	//		device.setVendorName(deviceModel.getVendorName());

	//		if (deviceModel.getColdStoreId() != null) {
	//			DcmColdStore dcmColdStore = validator.validateColdStoreId(deviceModel.getColdStoreId());
	//			device.setDcmColdStore(dcmColdStore);
	//		}

		device.setDcmStatus(statusRepo.findByStatusDesc(Constants.DeviceStatus.ACTIVE));
		setDeviceCommodityData(device, deviceModel);
		repo.save(device);
	}

	public CustomerDeviceSubscriptionModel getCustomerSubscribedDeviceDetails(Integer pageNumber, Integer limit)
			throws IMException {
		Long userId = null;
		Long customerId = null;
		List<Object[]> dcmDevice = new ArrayList<Object[]>();
		List<DeviceTypeModel> deviceTypeModelList = new ArrayList<>();
		CustomerDeviceSubscriptionModel cDSM = new CustomerDeviceSubscriptionModel();

		RequestContext requestContext = serverContext.getRequestContext();
		if (Constants.CustomerType.CUSTOMER.equals(requestContext.getCustomerType())) {
			userId = requestContext.getUserId();
			customerId = requestContext.getCustomerId();

			if (userId == null)
				throw new IMException(Constants.ErrorCode.USER_ID_IS_NOT_PRESENT,
						Constants.ErrorMessage.USER_ID_IS_NOT_PRESENT_MESSAGE);

			if (customerId == null)
				throw new IMException(Constants.ErrorCode.CUSTOMER_ID_IS_NOT_PRESENT,
						Constants.ErrorMessage.CUSTOMER_ID_IS_NOT_PRESENT_MESSAGE);

		} else if (Constants.CustomerType.SERVICE_PROVIDER.equals(requestContext.getCustomerType()))
			throw new IMException(Constants.ErrorCode.NOT_ACCESSABLE_LOGIN,
					Constants.ErrorMessage.NOT_ACCESSABLE_LOGIN_MESSAGE);

		dcmDevice = repo.findByCustomerIdAndUserIdAndDcmStatusId(customerId, userId, Constants.STATUS.ACTIVE.getId());
		for (Object obj[] : dcmDevice) {

			DeviceTypeModel deviceTypeModel = new DeviceTypeModel();
			deviceTypeModel.setDeviceTypeId((Long) obj[0]);
			deviceTypeModel.setDeviceTypeDesc((String) obj[1]);

			deviceTypeModelList.add(deviceTypeModel);

		}
		cDSM.setDeviceTypeModel(deviceTypeModelList);

		return cDSM;
	}

	private void setDeviceCommodityData(DcmDevice device, DeviceModel deviceModel) {

		Long deviceId = device.getId();
		List<Long> cIds = new ArrayList<Long>();
		List<DeviceCommodityEntity> dcList = new ArrayList<DeviceCommodityEntity>();

		if (deviceModel.getCommodityIds() != null) {
			cIds = Arrays.asList(deviceModel.getCommodityIds());
		}
		List<DcmCommodity> commodities = commodityRepo.findByIdIn(cIds);
		for (DcmCommodity commodity : commodities) {
			DeviceCommodityEntity dc = new DeviceCommodityEntity();
			if (commodity != null && device != null && commodity.getId() != null && device.getId() != null) {
				dc = deviceCommodityRepo.findByDeviceIdAndCommodityId(device.getId(), commodity.getId());
			}
			if (dc == null) {
				dc = new DeviceCommodityEntity();
			}
			dc.setDevice(device);
			dc.setCommodity(commodity);
			dc.setStatusId(Constants.STATUS.ACTIVE.getId().intValue());
			dcList.add(dc);

		}

		deviceCommodityRepo.saveAll(dcList);

	}

	public CustomerDeviceSubscriptionModel getOperatorDeviceDetails(Integer pageNumber, Integer limit)
			throws IMException {
		Long userId = null;
		Long customerId = null;
		List<Object[]> dcmDevice = new ArrayList<Object[]>();
		List<DeviceTypeModel> deviceTypeModelList = new ArrayList<>();
		CustomerDeviceSubscriptionModel cDSM = new CustomerDeviceSubscriptionModel();

		RequestContext requestContext = serverContext.getRequestContext();
		if (Constants.CustomerType.CUSTOMER.equals(requestContext.getCustomerType())) {
			userId = requestContext.getUserId();
			customerId = requestContext.getCustomerId();

		} else if (Constants.CustomerType.SERVICE_PROVIDER.equals(requestContext.getCustomerType()))
			throw new IMException(Constants.ErrorCode.NOT_ACCESSABLE_LOGIN,
					Constants.ErrorMessage.NOT_ACCESSABLE_LOGIN_MESSAGE);

		dcmDevice = repo.findDeviceByCustomerIdAndUserIdAndDcmStatusId(customerId, userId,
				Constants.STATUS.ACTIVE.getId());
		for (Object obj[] : dcmDevice) {

			cDSM.setDeviceId((Long) obj[0]);
			cDSM.setDeviceSerialNumber((String) obj[1]);

		}

		return cDSM;
	}

	public void saveUserDetails(UserDetailModel userDetailModel) throws Exception {

		UserDetails userDetail = new UserDetails();

		try {
			Optional.ofNullable(userDetailModel.getUserId()).ifPresent(userDetail::setUserId);
			Optional.ofNullable(userDetailModel.getUserEmail()).ifPresent(userDetail::setUserEmail);
			Optional.ofNullable(userDetailModel.getSelectedCommodity()).ifPresent(userDetail::setSelectedCommodity);
			Optional.ofNullable(userDetailModel.getPhoneVersion()).ifPresent(userDetail::setPhoneVersion);
			Optional.ofNullable(userDetailModel.getPhoneSerialNumber()).ifPresent(userDetail::setPhoneSerialNumber);
			Optional.ofNullable(userDetailModel.getPhoneBrand()).ifPresent(userDetail::setPhoneBrand);
			Optional.ofNullable(userDetailModel.getDeviceCommodity()).ifPresent(userDetail::setDeviceCommodity);

			userDetailsRepo.save(userDetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e);
		}

	}
}
