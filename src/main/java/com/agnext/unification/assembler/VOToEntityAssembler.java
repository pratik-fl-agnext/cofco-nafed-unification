/*
 * @author Vishal B.
 * @version 1.0
 */

package com.agnext.unification.assembler;

import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.agnext.unification.common.CommonUtil;
import com.agnext.unification.common.Constants;
import com.agnext.unification.config.RequestContext;
import com.agnext.unification.entity.nafed.AddressTypeEntity;
import com.agnext.unification.entity.nafed.CommodityVarietyEntity;
import com.agnext.unification.entity.nafed.CustomerAddressEntity;
import com.agnext.unification.entity.nafed.CustomerBankDetailEntity;
import com.agnext.unification.entity.nafed.CustomerBillingDetailEntity;
import com.agnext.unification.entity.nafed.CustomerEntity;
import com.agnext.unification.entity.nafed.CustomerTypeEntity;
import com.agnext.unification.entity.nafed.DcmDevice;
import com.agnext.unification.entity.nafed.MoistureMeterResult;
import com.agnext.unification.entity.nafed.Packages;
import com.agnext.unification.entity.nafed.PermissionEntity;
import com.agnext.unification.entity.nafed.RoleEntity;
import com.agnext.unification.entity.nafed.ScanLocation;
import com.agnext.unification.entity.nafed.StateEntity;
import com.agnext.unification.entity.nafed.StatusEntity;
import com.agnext.unification.entity.nafed.Tragnext;
import com.agnext.unification.entity.nafed.UserAddressEntity;
import com.agnext.unification.entity.nafed.UserEntity;
import com.agnext.unification.model.AddressModel;
import com.agnext.unification.model.CustomSubscriptionModel;
import com.agnext.unification.model.CustomerAddressModel;
import com.agnext.unification.model.CustomerBankDetailModel;
import com.agnext.unification.model.CustomerBillingDetailModel;
import com.agnext.unification.model.CustomerModel;
import com.agnext.unification.model.LocationModel;
import com.agnext.unification.model.MoistureMeterResultModel;
import com.agnext.unification.model.PermissionModel;
import com.agnext.unification.model.RoleModel;
import com.agnext.unification.model.TragnextModel;
import com.agnext.unification.model.UserAddressModel;
import com.agnext.unification.model.UserCSVModel;
import com.agnext.unification.model.UserModel;

/**
 * The Class VOToEntityAssembler.
 */
public final class VOToEntityAssembler {
	
	   private static Logger logger = LoggerFactory.getLogger(VOToEntityAssembler.class);

    /**
     * Instantiates a new VO to entity assembler.
     */
    private VOToEntityAssembler() {

    }

    /**
     * Creates the role.
     *
     * @param request
     *            the request
     * @param statusEntity
     *            the status entity
     * @return the role entity
     */
    public static RoleEntity createRole(RoleModel request, StatusEntity statusEntity) {

	RoleEntity entity = new RoleEntity();
	entity.setRoleCode(request.getRoleCode());
	entity.setRoleDesc(request.getRoleDesc());
	entity.setStatus(statusEntity);
	return entity;
    }

    /**
     * Creates the permission.
     *
     * @param request
     *            the request
     * @param statusEntity
     *            the status entity
     * @return the permission entity
     */
    public static PermissionEntity createPermission(PermissionModel request, StatusEntity statusEntity) {
	PermissionEntity entity = new PermissionEntity();
	entity.setPermissionCode(request.getPermissionCode());
	entity.setPermissionDesc(request.getPermissionDesc());
	entity.setStatus(statusEntity);
	return entity;
    }

    /**
     * Creates the user.
     *
     * @param request
     *            the request
     * @param customerEntity
     * @param statusEntity
     *            the status entity
     * @param requestContext
     * @return the user entity
     */
    public static UserEntity createUser(UserModel request, CustomerEntity customerEntity, StatusEntity statusEntity,
	    RequestContext requestContext) {
	UserEntity userEntity = new UserEntity();
	userEntity.setUserFirstName(request.getUserFirstName());
	userEntity.setUserLastName(request.getUserLastName());
	userEntity.setUserContactNumber(request.getUserContactNumber());
	userEntity.setUserAlternateContactNumber(request.getUserAlternateContactNumber());
	userEntity.setUserEmail(request.getUserEmail());
	userEntity.setUser2faRequired(request.getUser2faRequired());
	userEntity.setUserUuid(UUID.randomUUID().toString());
	userEntity.setCustomer(customerEntity);
	userEntity.setStatus(statusEntity);
	userEntity.setUserHierarchy(request.getUserHierarchy());
	userEntity.setCreatedOn(Instant.now().getEpochSecond());
	userEntity.setCreatedBy(requestContext.getUserId());
	userEntity.setUserAccountNumber(RandomStringUtils.randomAlphanumeric(20).toUpperCase());
	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		if (request.getPassword() != null && !request.getPassword().equalsIgnoreCase("")) {
			logger.info(" Saving user's provided password");
			userEntity.setPassword(encoder.encode(request.getPassword()));
		} else {
			userEntity.setPassword(encoder.encode(RandomStringUtils.randomAlphanumeric(12)));
		}
	userEntity.setUser2faEnabled(false);
	userEntity.setUser2faRequired(false);
	return userEntity;
    }

    public static List<UserAddressEntity> createUserAddress(UserEntity entity, UserModel request,
	    List<AddressTypeEntity> addressTypeEntities, RequestContext requestContext, StatusEntity statusEntity) {
	List<UserAddressEntity> userAddressEntities = new ArrayList<UserAddressEntity>();
	if (!CommonUtil.isEmpty(request.getUserAddresses())) {
	    for (int i = 0; i < request.getUserAddresses().size(); i++) {
		UserAddressModel userAddressModel = request.getUserAddresses().get(i);
		UserAddressEntity userAddressEntity = new UserAddressEntity();
		AddressTypeEntity addressTypeEntity = addressTypeEntities.get(i);
		userAddressEntity.setAddressType(addressTypeEntity);
		userAddressEntity.setUser(entity);
		userAddressEntity.setAddressLine1(userAddressModel.getAddressLine1());
		userAddressEntity.setAddressLine2(userAddressModel.getAddressLine2());
		userAddressEntity.setCity(userAddressModel.getCity());
		userAddressEntity.setState(userAddressModel.getState());
		userAddressEntity.setCountry(userAddressModel.getCountry());
		userAddressEntity.setZipCode(userAddressModel.getPinCode());
		userAddressEntity.setCreatedOn(Instant.now().getEpochSecond());
		userAddressEntity.setCreatedBy(requestContext.getUserId());
		userAddressEntity.setStatus(statusEntity);
		userAddressEntities.add(userAddressEntity);
	    }
	}
	return userAddressEntities;
    }

    public static CustomerEntity createCustomer(CustomerModel request, CustomerTypeEntity customerTypeEntity,
	    StatusEntity statusEntity, RequestContext requestContext) {

	CustomerEntity customerEntity = new CustomerEntity();
	customerEntity.setCreatedBy(requestContext.getUserId());
	customerEntity.setCreatedOn(Instant.now().getEpochSecond());
	customerEntity.setCustomerAdminUserId(1);
	customerEntity.setCustomerContactNumber(request.getContactNumber());
	customerEntity.setCustomerEmail(request.getEmail());
	customerEntity.setCustomerName(request.getName());
	customerEntity.setCustomerType(customerTypeEntity);
	if (request.getPartnerId() != null) {
	    customerEntity.setPartnerId(request.getPartnerId());
	}
	customerEntity.setCustomerUuid(UUID.randomUUID().toString());
	customerEntity.setCustomerPan(request.getPan());
	customerEntity.setCustomerGst(request.getGst());
	if (!CommonUtil.isEmpty(request.getCustomerBankDetails()))
	    customerEntity.setCustomerBankDetails(
		    createCustomerBankDetails(customerEntity, request.getCustomerBankDetails()));
	if (!CommonUtil.isEmpty(request.getCustomerAddresses()))
	    customerEntity.setCustomerBankDetails(
		    createCustomerAddressDetails(customerEntity, request.getCustomerAddresses()));
	customerEntity.setStatus(statusEntity);
	return customerEntity;
    }

    private static List<CustomerBankDetailEntity> createCustomerAddressDetails(CustomerEntity customerEntity,
	    List<CustomerAddressModel> customerAddresses) {
	return null;
    }

    private static List<CustomerBankDetailEntity> createCustomerBankDetails(CustomerEntity customerEntity,
	    List<CustomerBankDetailModel> bankDetailList) {
	List<CustomerBankDetailEntity> response = new ArrayList<CustomerBankDetailEntity>(bankDetailList.size());
	for (CustomerBankDetailModel bankDetailRequest : bankDetailList) {
	    CustomerBankDetailEntity bankDetail = new CustomerBankDetailEntity();
	    bankDetail.setBankAccountNumber(bankDetailRequest.getBankAccountNumber());
	    bankDetail.setBankAddress(bankDetailRequest.getBankAddress());
	    bankDetail.setBankBranch(bankDetailRequest.getBankBranch());
	    bankDetail.setBankIfsc(bankDetailRequest.getBankIfsc());
	    bankDetail.setBankName(bankDetailRequest.getBankName());
	    bankDetail.setBankUuid(UUID.randomUUID().toString());
	    bankDetail.setCustomer(customerEntity);
	    bankDetail.setCreatedBy(customerEntity.getCreatedBy());
	    bankDetail.setCreatedOn(customerEntity.getCreatedOn());
	    response.add(bankDetail);
	}

	return response;
    }

    public static List<CustomerAddressEntity> createCustomerAddress(CustomerEntity customerEntity,
	    CustomerModel request, List<AddressTypeEntity> addressTypeEntities, RequestContext requestContext,
	    StatusEntity statusEntity) {

	List<CustomerAddressEntity> customerAddressEntities = new ArrayList<CustomerAddressEntity>();
	if (!CommonUtil.isEmpty(request.getCustomerAddresses())) {
	    for (int i = 0; i < request.getCustomerAddresses().size(); i++) {
		CustomerAddressModel customerAddressModel = request.getCustomerAddresses().get(i);
		CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();
		AddressTypeEntity addressTypeEntity = addressTypeEntities.get(i);
		customerAddressEntity.setAddressType(addressTypeEntity);
		customerAddressEntity.setCustomer(customerEntity);
		customerAddressEntity.setAddressLine1(customerAddressModel.getAddressLine1());
		customerAddressEntity.setAddressLine2(customerAddressModel.getAddressLine2());
		customerAddressEntity.setCity(customerAddressModel.getCity());
		customerAddressEntity.setState(customerAddressModel.getState());
		customerAddressEntity.setCountry(customerAddressModel.getCountry());
		customerAddressEntity.setZipCode(customerAddressModel.getPinCode());
		customerAddressEntity.setCreatedOn(Instant.now().getEpochSecond());
		customerAddressEntity.setCreatedBy(requestContext.getUserId());
		customerAddressEntity.setStatus(statusEntity);
		customerAddressEntities.add(customerAddressEntity);
	    }
	}
	return customerAddressEntities;
    }

    public static List<CustomerBankDetailEntity> createCustomerBankDetail(CustomerEntity customerEntity,
	    CustomerModel request, RequestContext requestContext) {

	List<CustomerBankDetailEntity> customerBankDetailEntities = new ArrayList<>();
	if (!CommonUtil.isEmpty(request.getCustomerBankDetails())) {
	    for (CustomerBankDetailModel customerBankDetailModel : request.getCustomerBankDetails()) {
		CustomerBankDetailEntity customerBankDetailEntity = new CustomerBankDetailEntity();
		customerBankDetailEntity.setBankAccountNumber(customerBankDetailModel.getBankAccountNumber());
		customerBankDetailEntity.setBankAddress(customerBankDetailModel.getBankAddress());
		customerBankDetailEntity.setBankBranch(customerBankDetailModel.getBankBranch());
		customerBankDetailEntity.setBankIfsc(customerBankDetailModel.getBankIfsc());
		customerBankDetailEntity.setBankName(customerBankDetailModel.getBankName());
		customerBankDetailEntity.setBankUuid(RandomStringUtils.randomAlphanumeric(20).toUpperCase());
		customerBankDetailEntity.setCustomer(customerEntity);
		customerBankDetailEntity.setCreatedBy(requestContext.getUserId());
		customerBankDetailEntity.setCreatedOn(Instant.now().getEpochSecond());
		customerBankDetailEntities.add(customerBankDetailEntity);
	    }
	}

	return customerBankDetailEntities;
    }

    public static List<CustomerBillingDetailEntity> createCustomerBillingDetail(CustomerEntity customerEntity,
	    CustomerModel request, RequestContext requestContext) {

	List<CustomerBillingDetailEntity> customerBillingDetailEntities = new ArrayList<>();
	if (!CommonUtil.isEmpty(request.getCustomerBillingDetails())) {
	    for (CustomerBillingDetailModel customerBillingDetailModel : request.getCustomerBillingDetails()) {
		CustomerBillingDetailEntity customerBillingDetailEntity = new CustomerBillingDetailEntity();
		customerBillingDetailEntity
			.setBillingAccountNumber(customerBillingDetailModel.getBillingAccountNumber());
		customerBillingDetailEntity.setBillingAddress(customerBillingDetailModel.getBillingAddress());
		customerBillingDetailEntity.setBillingUuid(RandomStringUtils.randomAlphanumeric(20).toUpperCase());
		customerBillingDetailEntity.setCreatedBy(requestContext.getUserId());
		customerBillingDetailEntity.setCreatedOn(Instant.now().getEpochSecond());
		customerBillingDetailEntity.setCustomer(customerEntity);
		customerBillingDetailEntities.add(customerBillingDetailEntity);
	    }
	}
	return customerBillingDetailEntities;
    }

    public static UserEntity updateUser(UserModel request, CustomerEntity customerEntity, StatusEntity statusEntity,
	    RequestContext requestContext, UserEntity userEntity) {
	userEntity.setCustomer(customerEntity);
	//		userEntity.setStatus(statusEntity);
	userEntity.setUserAlternateContactNumber(request.getUserAlternateContactNumber());
	userEntity.setUserContactNumber(request.getUserContactNumber());
	userEntity.setUserEmail(request.getUserEmail());
	userEntity.setUserFirstName(request.getUserFirstName());
	userEntity.setUserLastName(request.getUserLastName());
	userEntity.setUpdatedOn(Instant.now().getEpochSecond());
	userEntity.setUpdatedBy(requestContext.getUserId());
	if (request.getUserAccountNumber() != null)
	    userEntity.setUserAccountNumber(RandomStringUtils.randomAlphanumeric(20).toUpperCase());
	return userEntity;
    }

    public static List<UserAddressEntity> updateUserAddress(UserEntity userEntity, UserModel request,
	    List<AddressTypeEntity> addressTypeEntities, RequestContext requestContext) {

	List<UserAddressEntity> userAddressEntities = new ArrayList<UserAddressEntity>();

	if (!CommonUtil.isEmpty(request.getUserAddresses())) {
	    for (int i = 0; i < request.getUserAddresses().size(); i++) {
		UserAddressModel userAddressModel = request.getUserAddresses().get(i);
		AddressTypeEntity addressTypeEntity = addressTypeEntities.get(i);
		UserAddressEntity userAddressEntity = new UserAddressEntity();
		userAddressEntity.setAddressType(addressTypeEntity);
		userAddressEntity.setUser(userEntity);
		userAddressEntity.setAddressLine1(userAddressModel.getAddressLine1());
		userAddressEntity.setAddressLine2(userAddressModel.getAddressLine2());
		userAddressEntity.setCity(userAddressModel.getCity());
		userAddressEntity.setState(userAddressModel.getState());
		userAddressEntity.setCountry(userAddressModel.getCountry());
		userAddressEntity.setZipCode(userAddressModel.getPinCode());
		userAddressEntity.setCreatedOn(Instant.now().getEpochSecond());
		userAddressEntity.setCreatedBy(requestContext.getUserId());
		userAddressEntities.add(userAddressEntity);
	    }
	}
	return userAddressEntities;
    }

    public static UserEntity convertUser(UserModel userModel, CustomerEntity customerEntity,
	    RequestContext requestContext, StatusEntity statusEntity) {

	UserEntity userEntity = new UserEntity();
	userEntity.setUserFirstName(userModel.getUserFirstName());
	userEntity.setUserLastName(userModel.getUserLastName());
	userEntity.setUserEmail(userModel.getUserEmail());
	userEntity.setUserContactNumber(userModel.getUserContactNumber());
	userEntity.setUserHierarchy(userModel.getUserHierarchy());
	userEntity.setCustomer(customerEntity);
	userEntity.setCreatedBy(requestContext.getUserId());
	userEntity.setCreatedOn(Instant.now().getEpochSecond());
	userEntity.setStatus(statusEntity);
	if (userModel.getUser2faRequired() != null)
	    userEntity.setUser2faRequired(userModel.getUser2faRequired());
	userEntity.setUser2faEnabled(true);
	return userEntity;
    }

    public static CustomerEntity updateCustomer(CustomerModel request, CustomerTypeEntity customerTypeEntity,
	    RequestContext requestContext, CustomerEntity customerEntity, List<AddressTypeEntity> addressTypeEntities) {

	customerEntity.setCreatedBy(requestContext.getUserId());
	customerEntity.setUpdatedBy(requestContext.getUserId());
	customerEntity.setUpdatedOn(Instant.now().getEpochSecond());
	customerEntity.setCreatedOn(Instant.now().getEpochSecond());
	customerEntity.setCustomerAdminUserId(1);
	customerEntity.setCustomerContactNumber(request.getContactNumber());
	customerEntity.setCustomerEmail(request.getEmail());
	customerEntity.setCustomerName(request.getName());

	if (customerTypeEntity != null) {
	    customerEntity.setCustomerType(customerTypeEntity);
	}
	customerEntity.setCustomerUuid(UUID.randomUUID().toString());
	customerEntity.setCustomerPan(request.getPan());
	customerEntity.setCustomerGst(request.getGst());

	if (!CommonUtil.isEmpty(request.getCustomerBankDetails()))
	    customerEntity.setCustomerBankDetails(updateCustomerBankDetails(customerEntity, request, requestContext));
	if (!CommonUtil.isEmpty(request.getCustomerAddresses()))
	    customerEntity.setCustomerAddresses(
		    updateCustomerAddressDetails(customerEntity, request, requestContext, addressTypeEntities));
	if (!CommonUtil.isEmpty(request.getCustomerBillingDetails()))
	    customerEntity
		    .setCustomerBillingDetails(updateCustomerBillingDetails(customerEntity, request, requestContext));

	return customerEntity;
    }

    private static List<CustomerBillingDetailEntity> updateCustomerBillingDetails(CustomerEntity customerEntity,
	    CustomerModel request, RequestContext requestContext) {
	List<CustomerBillingDetailEntity> customerBillingDetailEntities = new ArrayList<>();
	if (!CommonUtil.isEmpty(request.getCustomerBillingDetails())) {
	    for (CustomerBillingDetailModel customerBillingDetailModel : request.getCustomerBillingDetails()) {
		CustomerBillingDetailEntity customerBillingDetailEntity = new CustomerBillingDetailEntity();
		customerBillingDetailEntity
			.setBillingAccountNumber(customerBillingDetailModel.getBillingAccountNumber());
		customerBillingDetailEntity.setBillingAddress(customerBillingDetailModel.getBillingAddress());
		customerBillingDetailEntity.setBillingUuid(RandomStringUtils.randomAlphanumeric(20).toUpperCase());
		customerBillingDetailEntity.setCreatedBy(requestContext.getUserId());
		customerBillingDetailEntity.setCreatedOn(Instant.now().getEpochSecond());
		customerBillingDetailEntity.setCustomer(customerEntity);
		customerBillingDetailEntities.add(customerBillingDetailEntity);
	    }
	}
	return customerBillingDetailEntities;
    }

    private static List<CustomerAddressEntity> updateCustomerAddressDetails(CustomerEntity customerEntity,
	    CustomerModel request, RequestContext requestContext, List<AddressTypeEntity> addressTypeEntities) {
	List<CustomerAddressEntity> customerAddressEntities = new ArrayList<CustomerAddressEntity>();
	if (!CommonUtil.isEmpty(request.getCustomerAddresses())) {
	    for (int i = 0; i < request.getCustomerAddresses().size(); i++) {
		CustomerAddressModel customerAddressModel = request.getCustomerAddresses().get(i);
		AddressTypeEntity addressTypeEntity = addressTypeEntities.get(i);
		CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();
		customerAddressEntity.setAddressType(addressTypeEntity);
		customerAddressEntity.setCustomer(customerEntity);
		customerAddressEntity.setAddressLine1(customerAddressModel.getAddressLine1());
		customerAddressEntity.setAddressLine2(customerAddressModel.getAddressLine2());
		customerAddressEntity.setCity(customerAddressModel.getCity());
		customerAddressEntity.setState(customerAddressModel.getState());
		customerAddressEntity.setCountry(customerAddressModel.getCountry());
		customerAddressEntity.setZipCode(customerAddressModel.getPinCode());
		customerAddressEntity.setCreatedOn(Instant.now().getEpochSecond());
		customerAddressEntity.setCreatedBy(requestContext.getUserId());
		customerAddressEntities.add(customerAddressEntity);
	    }
	}
	return customerAddressEntities;
    }

    private static List<CustomerBankDetailEntity> updateCustomerBankDetails(CustomerEntity customerEntity,
	    CustomerModel request, RequestContext requestContext) {
	List<CustomerBankDetailEntity> customerBankDetailEntities = new ArrayList<>();
	if (!CommonUtil.isEmpty(request.getCustomerBankDetails())) {
	    for (CustomerBankDetailModel customerBankDetailModel : request.getCustomerBankDetails()) {
		CustomerBankDetailEntity customerBankDetailEntity = new CustomerBankDetailEntity();
		customerBankDetailEntity.setBankAccountNumber(customerBankDetailModel.getBankAccountNumber());
		customerBankDetailEntity.setBankAddress(customerBankDetailModel.getBankAddress());
		customerBankDetailEntity.setBankBranch(customerBankDetailModel.getBankBranch());
		customerBankDetailEntity.setBankIfsc(customerBankDetailModel.getBankIfsc());
		customerBankDetailEntity.setBankName(customerBankDetailModel.getBankName());
		customerBankDetailEntity.setBankUuid(RandomStringUtils.randomAlphanumeric(20).toUpperCase());
		customerBankDetailEntity.setCustomer(customerEntity);
		customerBankDetailEntity.setCreatedBy(requestContext.getUserId());
		customerBankDetailEntity.setUpdatedBy(requestContext.getUserId());
		customerBankDetailEntity.setCreatedOn(Instant.now().getEpochSecond());
		customerBankDetailEntity.setUpdatedOn(Instant.now().getEpochSecond());
		customerBankDetailEntities.add(customerBankDetailEntity);
	    }
	}

	return customerBankDetailEntities;
    }

    public static UserEntity updateUser(UserModel userModel, RequestContext requestContext, UserEntity userEntity) {
	userEntity.setUserFirstName(userModel.getUserFirstName());
	userEntity.setUserLastName(userModel.getUserLastName());
	userEntity.setUserEmail(userModel.getUserEmail());
	userEntity.setUserContactNumber(userModel.getUserContactNumber());
	userEntity.setUserHierarchy(userModel.getUserHierarchy());
	//		userEntity.setCustomer(customerEntity);
	userEntity.setCreatedBy(requestContext.getUserId());
	userEntity.setCreatedOn(Instant.now().getEpochSecond());
	userEntity.setUpdatedBy(requestContext.getUserId());
	userEntity.setUpdatedOn(Instant.now().getEpochSecond());
	userEntity.setUser2faRequired(userModel.getUser2faRequired());
	userEntity.setUser2faEnabled(true);
	// PasswordEncoder encoder =
	// PasswordEncoderFactories.createDelegatingPasswordEncoder();
	// userEntity.setPassword(encoder.encode(RandomStringUtils.randomAlphanumeric(12)));
	return userEntity;

    }

    public static ScanLocation convertCommercialLocation(LocationModel request, StatusEntity statusEntity,
	    boolean isUpdated, StateEntity state, Long userId, ScanLocation locationRequest) {

	if (statusEntity != null) {
	    locationRequest.setStatus(statusEntity);
	}
	locationRequest.setCreatedOn(Instant.now().getEpochSecond());
	// Location is being saved in warehouse and warehouse in location column as a requirement and not a bug
	locationRequest.setLocationName(request.getWarehouseName());
	locationRequest.setWarehouseName(request.getInstCenterName());
	locationRequest.setCode(request.getCode());
	if (state != null)
	    locationRequest.setState(state);
	//		if (isUpdated) {
	//			locationRequest.setModifiedBy(request.getCustomerId());
	//			locationRequest.setModifiedOn(Instant.now().getEpochSecond());
	//		}
	//		locationRequest.setCreatedBy(request.getCustomerId());
	//		locationRequest.setCustomerId(request.getCustomerId());
	//		locationRequest.setDcmCommercialLocationType(commercialLocationType);

	//		if (request.getNotes() != null)
	//			locationRequest.setNotes(request.getNotes());
	//		dcmCommercialLocation.setDcmSite(dcmSite);
	//		dcmCommercialLocation.setDcmRegion(dcmRegion);
	//		dcmCommercialLocation.setInstCenterName(request.getInstCenterName());
	//		if (request.getUserId() != null)
	//			dcmCommercialLocation.setUserId(request.getUserId());
	return locationRequest;

    }

    public static Packages convertCustomPackageDetails(CustomSubscriptionModel customSubscriptionModel,
	    DcmDevice deviceEntity) {
	Packages p = new Packages();
	p.setPackageName(customSubscriptionModel.getPackageName());
	p.setTotalScans(customSubscriptionModel.getNumberOfScan());
	p.setStatus(1);
	p.setDeviceType(deviceEntity.getDcmDeviceType());
	p.setIsDefaultPackage(Boolean.FALSE);
	p.setClientId(customSubscriptionModel.getCustomerId());

	long monthsBetween = ChronoUnit.MONTHS.between(
		YearMonth.from(LocalDate.parse(customSubscriptionModel.getFromDate())),
		YearMonth.from(LocalDate.parse(customSubscriptionModel.getToDate())));

	p.setDurationPeriod(String.valueOf(monthsBetween));
	p.setDurationUnit("Month");
	return p;
    }

    public static MoistureMeterResult convertMoistureMeterResult(MoistureMeterResultModel model, Long operatorId,
	    CommodityVarietyEntity varietyEntity) {
	MoistureMeterResult entity = new MoistureMeterResult();
	entity.setSampleId(model.getSampleId());
	entity.setClientId(operatorId);
	entity.setCommodityName(model.getCommodityName());
	entity.setMoisture(model.getMoisture());
	entity.setTemperature(model.getTemperature());
	entity.setTruckNumber(model.getTruckNumber());
	entity.setWeight(model.getWeight());
	if (model.getToken() != null) {
	    entity.setToken(model.getToken());
	} else {
	    Random rand = new Random();
	    entity.setToken(Integer.toString((rand.nextInt(10000))));
	}
	//entity.setVarietyId(model.getVarietyId());
	if (model.getCreatedOn() != null) {
	    entity.setCreatedOn(model.getCreatedOn());
	} else {
	    entity.setCreatedOn(String.valueOf(Instant.now().getEpochSecond()));
	}

	if (model.getVarietyId() != null && model.getVarietyId() == Constants.CustomVariety.CUSTOM_VARIETY_ID && model.getVarietyName() != null) {	    
	    entity.setVarietyId(model.getVarietyId());
	    entity.setVarietyName(model.getVarietyName());
	} else if(varietyEntity != null){
	    entity.setVarietyId(varietyEntity.getId());
	    entity.setVarietyName(varietyEntity.getVarietyName());
	}
	return entity;
    }
    public static UserEntity createUserForBulkUpload(UserCSVModel request, CustomerEntity customerEntity, StatusEntity statusEntity,
    	    RequestContext requestContext) {
    	UserEntity userEntity = new UserEntity();
    	userEntity.setUserFirstName(request.getFirstname());
    	userEntity.setUserLastName(request.getLastname());
    	userEntity.setUserContactNumber(request.getContactnumber());
    	userEntity.setUserAlternateContactNumber(request.getContactnumber());
    	userEntity.setUserEmail(request.getEmail());
    	userEntity.setUser2faRequired(true);
    	userEntity.setUserUuid(UUID.randomUUID().toString());
    	userEntity.setCustomer(customerEntity);
    	userEntity.setStatus(statusEntity);
    	if(request.getRole().equalsIgnoreCase("customer_admin"))
    	{
    		userEntity.setUserHierarchy("admin");
    	}
    	userEntity.setCreatedOn(Instant.now().getEpochSecond());
    	userEntity.setCreatedBy(requestContext.getUserId());
    	userEntity.setUserAccountNumber(RandomStringUtils.randomAlphanumeric(20).toUpperCase());
    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    	userEntity.setPassword(encoder.encode(request.getPassword()));
    	userEntity.setUser2faEnabled(false);
    	userEntity.setUser2faRequired(false);
    	return userEntity;
        }
    
    public static List<UserAddressEntity> createUserAddressForBulkUpload(UserEntity entity, UserCSVModel request,
    	    List<AddressTypeEntity> addressTypeEntities, RequestContext requestContext, StatusEntity statusEntity, AddressModel address) {
    	List<UserAddressEntity> userAddressEntities = new ArrayList<UserAddressEntity>();
    	
    	if (request.getAddress() !=null && !request.getAddress().isEmpty()) {
    	    for (int i = 0; i < addressTypeEntities.size(); i++) {
//    		UserAddressModel userAddressModel = request.getUserAddresses().get(i);
    		UserAddressEntity userAddressEntity = new UserAddressEntity();
    		AddressTypeEntity addressTypeEntity = addressTypeEntities.get(i);
    		userAddressEntity.setAddressType(addressTypeEntity);
    		userAddressEntity.setUser(entity);
    		userAddressEntity.setAddressLine1(request.getAddress());
//    		userAddressEntity.setAddressLine2(userAddressModel.getAddressLine2());
    		if (address != null && address.getCityId() != null) {
				userAddressEntity.setCity(address.getCityId().toString());
			}else if(address ==null || address.getCityId() ==null  ) {
				userAddressEntity.setCity(address.getCustomCityId().toString());
			}
			if (address != null && address.getStateId() != null) {
				userAddressEntity.setState(address.getStateId().toString());
			}else if(address ==null || address.getStateId() ==null ) {
				userAddressEntity.setCity(address.getCustomStateId().toString());
			}
			
			if (address != null && address.getCountryId() != null) {
				userAddressEntity.setCountry(address.getCountryId().toString());
			}else if(address ==null || address.getCountryId() ==null  ) {
				userAddressEntity.setCity(address.getCustomCountryId().toString());
			}
    		userAddressEntity.setZipCode(Integer.valueOf(request.getPincode()));
    		userAddressEntity.setCreatedOn(Instant.now().getEpochSecond());
    		userAddressEntity.setCreatedBy(requestContext.getUserId());
    		userAddressEntity.setStatus(statusEntity);
    		userAddressEntities.add(userAddressEntity);
    	    }
    	}
    	return userAddressEntities;
        }

    public static Tragnext convertTragnextModelToEntity(TragnextModel tragnextModel) {
    	Tragnext tragnext = new Tragnext();
    	tragnext.setName(tragnextModel.getName() !=null ? tragnextModel.getName():"");
    	tragnext.setEmail(tragnextModel.getEmail() !=null ? tragnextModel.getEmail():"");
		tragnext.setPhone(tragnextModel.getPhoneNumber() !=null ? tragnextModel.getPhoneNumber():"");
		if(tragnextModel.getParameters() != null){
			tragnext.setAverageWeight(tragnextModel.getParameters().getAvgWeightOfTruck() !=null ? tragnextModel.getParameters().getAvgWeightOfTruck():"");
			tragnext.setAvgPrice(tragnextModel.getParameters().getAvgPriceOfFlc() !=null ? tragnextModel.getParameters().getAvgPriceOfFlc():"");
			tragnext.setNoOfTruck(tragnextModel.getParameters().getNoOfTrucksPerDay() !=null ? tragnextModel.getParameters().getNoOfTrucksPerDay():"");
			tragnext.setNoOfBoughtLeaves(tragnextModel.getParameters().getPercentageOfBoughtLeaves() !=null ? tragnextModel.getParameters().getPercentageOfBoughtLeaves():"");
			tragnext.setDeltaFlcSlab(tragnextModel.getParameters().getDeltaBetweenFLCSlab() !=null ? tragnextModel.getParameters().getDeltaBetweenFLCSlab():"");
			tragnext.setAvgWaterPercentage(tragnextModel.getParameters().getAvgPercentageOfWater() !=null ? tragnextModel.getParameters().getAvgPercentageOfWater():"");
		}
    	return tragnext;
    }
}
