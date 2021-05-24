/*
 * @author Vishal Bansal
 * @version 1.0
 */
package com.agnext.unification.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.agnext.unification.assembler.EntityToVOAssembler;
import com.agnext.unification.assembler.VOToEntityAssembler;
import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.CityEntity;
import com.agnext.unification.entity.nafed.CountryEntity;
import com.agnext.unification.entity.nafed.CustomerEntity;
import com.agnext.unification.entity.nafed.CustomerTypeEntity;
import com.agnext.unification.entity.nafed.DcmCommodity;
import com.agnext.unification.entity.nafed.DcmDevice;
import com.agnext.unification.entity.nafed.DcmDeviceType;
import com.agnext.unification.entity.nafed.DeviceCommodityEntity;
import com.agnext.unification.entity.nafed.RoleEntity;
import com.agnext.unification.entity.nafed.ScanLocation;
import com.agnext.unification.entity.nafed.StateEntity;
import com.agnext.unification.entity.nafed.StateManagerOperatorsEntity;
import com.agnext.unification.entity.nafed.StatusEntity;
import com.agnext.unification.entity.nafed.UserAddressEntity;
import com.agnext.unification.entity.nafed.UserEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.AddressModel;
import com.agnext.unification.model.CustomerAddressModel;
import com.agnext.unification.model.CustomerModel;
import com.agnext.unification.model.PasswordModel;
import com.agnext.unification.model.StatusModel;
import com.agnext.unification.model.UserCSVModel;
import com.agnext.unification.model.UserCountModel;
import com.agnext.unification.model.UserModel;
import com.agnext.unification.repository.nafed.CityRepository;
import com.agnext.unification.repository.nafed.CountryRepository;
import com.agnext.unification.repository.nafed.CustomerRepository;
import com.agnext.unification.repository.nafed.DcmCommodityRepository;
import com.agnext.unification.repository.nafed.DcmDeviceTypeRepository;
import com.agnext.unification.repository.nafed.DeviceCommodityRepository;
import com.agnext.unification.repository.nafed.DeviceRepository;
import com.agnext.unification.repository.nafed.FilterNativeRepository;
import com.agnext.unification.repository.nafed.ScanLocationRepository;
import com.agnext.unification.repository.nafed.StateManagerOperatorRepository;
import com.agnext.unification.repository.nafed.StateRepository;
import com.agnext.unification.repository.nafed.UserAddressRepository;
import com.agnext.unification.repository.nafed.UserRepository;
import com.agnext.unification.utility.EmailUtil;
import com.agnext.unification.utility.Utility;
import com.agnext.unification.validator.BaseValidator;
import com.agnext.unification.validator.CustomerValidator;
import com.agnext.unification.validator.RoleValidator;
import com.agnext.unification.validator.UserValidator;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserService extends GenericService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserValidator validator;

    @Autowired
    UserRepository repository;

    @Autowired
    UserAddressRepository userAddressRepository;

    @Autowired
    RoleValidator roleValidator;

    @Autowired
    EmailUtil emailUtilService;

    @Autowired
    CustomerValidator customerValidator;

    @Autowired
    FilterNativeRepository filterNativeRepo;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    StateManagerOperatorRepository smoRepo;

    @Autowired
    StateRepository stateRepo;

    @Autowired
    ScanLocationRepository locationRepo;

    @Autowired
    DeviceRepository deviceRepo;

    @Autowired
    DcmDeviceTypeRepository deviceTypeRepo;

    @Autowired
    BaseValidator deviceValidator;

    @Autowired
    DeviceCommodityRepository dceRepo;

    @Autowired
    DcmCommodityRepository commodityRepo;

    @Value("${email-config.send-email}")
    private Boolean sendEmailCheck;
    
	@Autowired
	CityRepository cityRepo;
	
	@Autowired
	CountryRepository countryRepo;

    /**
     * Creates the user.
     *
     * @param request
     *            the request
     * @return the user model
     * @throws IMException
     *             the IM exception
     */
    public UserModel createUser(UserModel request) throws IMException {

	logger.debug("UserService.createUser creating user in system");

	if (request.getRoles().contains("sub_client")) {
	    UserModel userResponsne = createSubClient(request);
	    return userResponsne;
	}

	CustomerEntity customerEntity = validator.validateCustomer(request);

	UserEntity userEntity = VOToEntityAssembler.createUser(request, customerEntity,
		getStatusEntity(Constants.STATUS.ACTIVE.getId()), applicationContext.getRequestContext());

	logger.info("UserService.createUser user saved successfully");

	List<RoleEntity> roleEntities = roleValidator.validateIfRolesExist(request.getRoles());

	UserEntity supervisorEntity = repository.findByUserIdAndStatusStatusId(request.getSupervisorId(),
		Constants.STATUS.ACTIVE.getId());
	if (supervisorEntity != null) {
	    userEntity.setSupervisor(supervisorEntity);
	}

	userEntity.setUserAddresses(
		VOToEntityAssembler.createUserAddress(userEntity, request, validator.validateAddressType(request),
			applicationContext.getRequestContext(), getStatusEntity(Constants.STATUS.ACTIVE.getId())));

	userEntity.setRoles(roleEntities);

	UserEntity entity = repository.findByUserEmail(request.getUserEmail());
	if (entity != null) {
	    throw new IMException(Constants.ErrorCode.EMAIL_ALREADY_EXIST, Constants.ErrorMessage.EMAIL_ALREADY_EXIST);
	}

	validator.validateCreateUser(request);

//	String password = setRandomPassword(userEntity);

	UserEntity user = repository.save(userEntity);

	// State Admin changes
	if (request.getRoles().contains("operator")) {
	    StateManagerOperatorsEntity stateAdmin = new StateManagerOperatorsEntity();

	    if (request.getStateAdminId() != null) {
		Boolean isStateAdminExist = repository.isUserEntityExist(request.getStateAdminId());
		if (isStateAdminExist.booleanValue() == false) {
		    throw new IMException(Constants.ErrorCode.STATE_ADMIN_NOT_PRESENT,
			    " Provided State Admin is invalid ");

		} else if (isStateAdminExist) {
		    logger.info("State Admin with id : " + request.getStateAdminId() + " exists");
		}
	    } else if (request.getStateAdminId() == null) {
		throw new IMException(Constants.ErrorCode.STATE_ADMIN_NOT_PRESENT, "State Admin cannot be empty ");
	    }

	    stateAdmin.setStateManagerId(request.getStateAdminId());
	    stateAdmin.setOperatorId(user.getUserId());
	    stateAdmin.setStatus(Constants.STATUS.ACTIVE.getId().intValue());
	    smoRepo.save(stateAdmin);

	}

	if (sendEmailCheck == Boolean.TRUE) {
	    emailUtilService.sendUserCreationMail(new String[] { userEntity.getUserEmail() }, null,
		    userEntity.getUserFirstName(), userEntity.getUserEmail(), request.getPassword());
	}

	logger.info("UserService.createUser user address saved successfully");

	UserModel response = EntityToVOAssembler.convertUserEntity(userEntity, 0L);

	logger.info("UserService.createUser response is :" + response);

	return response;
    }

    public UserModel createSubClient(UserModel request) throws IMException {
	logger.debug("UserService.createUser creating user in system");

	// save sub-client to customer table first
	CustomerModel customerModel = new CustomerModel();
	customerModel.setName(request.getUserFirstName() + request.getUserLastName());
	customerModel.setCustomerTypeId(5L);
	customerModel.setStatus(Constants.STATUS.ACTIVE.getId());
	customerModel.setEmail(request.getUserEmail());
	customerModel.setContactNumber(request.getUserContactNumber());

	List<CustomerAddressModel> addressModelList = new ArrayList<>();
	CustomerAddressModel addressModel = new CustomerAddressModel();
	addressModel.setAddressLine1(request.getUserAddresses().get(0).getAddressLine1());
	addressModel.setAddressLine2(request.getUserAddresses().get(0).getAddressLine2());
	addressModel.setAddressTypeId(request.getUserAddresses().get(0).getAddressTypeId());
	addressModel.setCity(request.getUserAddresses().get(0).getCity());
	addressModel.setCountry(request.getUserAddresses().get(0).getCountry());
	addressModel.setCreatedBy(applicationContext.getRequestContext().getUserId());
	addressModel.setCreatedOn(Instant.now().getEpochSecond());
	addressModel.setPinCode(request.getUserAddresses().get(0).getPinCode());
	addressModel.setState(request.getUserAddresses().get(0).getState());
	addressModelList.add(addressModel);

	customerModel.setCustomerAddresses(addressModelList);

	CustomerTypeEntity customerTypeEntity = new CustomerTypeEntity();
	customerTypeEntity.setCustomerTypeId(5L);

	CustomerEntity custEntity = VOToEntityAssembler.createCustomer(customerModel, customerTypeEntity,
		getStatusEntity(Constants.STATUS.ACTIVE.getId()), applicationContext.getRequestContext());

	customerValidator.validateSubClient(customerModel);

	custEntity.setCustomerAddresses(VOToEntityAssembler.createCustomerAddress(custEntity, customerModel,
		validator.validateAddressType(request), applicationContext.getRequestContext(),
		getStatusEntity(Constants.STATUS.ACTIVE.getId())));

	CustomerEntity savedCustomerEntity = customerRepo.save(custEntity);

	// save sub-client to user table

	UserEntity userEntity = VOToEntityAssembler.createUser(request, savedCustomerEntity,
		getStatusEntity(Constants.STATUS.ACTIVE.getId()), applicationContext.getRequestContext());

	logger.info("UserService.createUser user saved successfully");

	List<RoleEntity> roleEntities = roleValidator.validateIfRolesExist(request.getRoles());

	UserEntity supervisorEntity = repository.findByUserIdAndStatusStatusId(request.getSupervisorId(),
		Constants.STATUS.ACTIVE.getId());
	if (supervisorEntity != null) {
	    userEntity.setSupervisor(supervisorEntity);
	}

	userEntity.setUserAddresses(
		VOToEntityAssembler.createUserAddress(userEntity, request, validator.validateAddressType(request),
			applicationContext.getRequestContext(), getStatusEntity(Constants.STATUS.ACTIVE.getId())));

	userEntity.setRoles(roleEntities);

	UserEntity entity = repository.findByUserEmail(request.getUserEmail());
	if (entity != null) {
	    throw new IMException(Constants.ErrorCode.EMAIL_ALREADY_EXIST, Constants.ErrorMessage.EMAIL_ALREADY_EXIST);
	}

	validator.validateCreateUser(request);

	String password = setRandomPassword(userEntity);

	repository.save(userEntity);

	if (sendEmailCheck == Boolean.TRUE) {
	    emailUtilService.sendUserCreationMail(new String[] { userEntity.getUserEmail() }, null,
		    userEntity.getUserFirstName(), userEntity.getUserEmail(), password);
	}

	logger.info("UserService.createUser user address saved successfully");

	UserModel response = EntityToVOAssembler.convertUserEntity(userEntity, 0L);

	logger.info("UserService.createUser response is :" + response);

	return response;
    }

    /**
     * set password.
     *
     * @param userEntity
     *            the request
     * @return String
     */

    private String setRandomPassword(UserEntity userEntity) {
	String password = RandomStringUtils.randomAlphanumeric(12);
	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	userEntity.setPassword(encoder.encode(password));
	return password;
    }

    /**
     * get User permissions.
     *
     * @param userId
     *            the request
     * @return UserModel
     */

    public UserModel getUserPermissions(Long userId) {
	return EntityToVOAssembler.convertUserEntity(repository.getOne(userId), 0L);
    }

    /**
     * Get the user.
     *
     * @param request
     *            the request
     * @return the user model
     * @throws IMException
     *             the IM exception
     */
    public UserModel getUser(Long userId) throws IMException {
	UserEntity userEntity = validator.userValidate(userId);

	StateManagerOperatorsEntity smoEntity = smoRepo.findByOperatorId(userId);
	return EntityToVOAssembler.getUserDetail(userEntity, smoEntity);
    }

    /**
     * Get the user list.
     *
     * @param pageNumber
     *            the request
     * @param limit
     *            the request
     * @param userType
     * @param isStateAdmin
     * @param customer
     *            the request
     * @return the user model
     * @throws IMException
     *             the IM exception
     */
    public List<UserModel> getUsers(Integer pageNumber, Integer limit, Long customerId, String keySearch,
	    String userType, Boolean isStateAdmin) throws IMException {
	List<UserModel> response = new ArrayList<UserModel>();
	if (isStateAdmin != null && isStateAdmin) {
	    List<UserEntity> users = filterNativeRepo.getStateAdminDetailByFilters(setCustomerId(customerId), keySearch,
		    pageNumber, limit, applicationContext.getRequestContext().getUserId());

	    Long count = filterNativeRepo.getStateAdminDetailCount(setCustomerId(customerId), keySearch, userType,
		    applicationContext.getRequestContext().getUserId());

	    for (UserEntity user : users) {
		if (!user.getUserEmail().equals("ai@agnext.in"))
		    response.add(EntityToVOAssembler.convertUserEntity(user, count));
	    }

	    return response;
	}
	List<UserEntity> users = filterNativeRepo.getUserDetailByFilters(setCustomerId(customerId), keySearch,
		pageNumber, limit, applicationContext.getRequestContext().getUserId(), userType);

	Long count = filterNativeRepo.getUserDetailCount(setCustomerId(customerId), keySearch, userType);

	for (UserEntity user : users) {
	    if (!user.getUserEmail().equals("ai@agnext.in"))
		response.add(EntityToVOAssembler.convertUserEntity(user, count));
	}
	return response;
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

	}
	return customerId;
    }

    /**
     * Get the applicable customer.
     *
     * @param customer
     *            the request
     * @return Long
     * @throws IMException
     *             the IM exception
     */
    private Long fetchApplicableCustomerId(Long customerId) throws IMException {
	//		if (customerId == null
	//				|| (applicationContext.getRequestContext().getCustomerId() == customerId))
	//			return applicationContext.getRequestContext().getCustomerId();
	//		if (applicationContext.getRequestContext().getCustomerType().equals(Constants.CustomerType.SERVICE_PROVIDER))
	//			return customerId;

	if (applicationContext.getRequestContext().getCustomerType().equals(Constants.CustomerType.SERVICE_PROVIDER)
		&& customerId == null) {
	    return null;
	} else if (applicationContext.getRequestContext().getCustomerType()
		.equals(Constants.CustomerType.SERVICE_PROVIDER) && customerId != null) {
	    return customerId;
	} else if (applicationContext.getRequestContext().getCustomerType().equals(Constants.CustomerType.CUSTOMER)) {
	    return applicationContext.getRequestContext().getCustomerId();
	} else {
	    throw new IMException("PR001", "You are not authorized.");
	}
    }

    /**
     * Update the user.
     *
     * @param request
     *            the request
     * @param userId
     * @return the user model
     * @throws IMException
     *             the IM exception
     */
    public UserModel updateUser(UserModel request, Long userId) throws IMException {

	logger.debug("UserService.updateUser update user in system");

	UserEntity userEntity = repository.findByUserIdAndStatusStatusIdNotIn(userId, Constants.STATUS.DELETED.getId());
	if (userEntity == null)
	    throw new IMException(Constants.ErrorCode.USER_ID_NOT_EXIST, Constants.ErrorMessage.USER_ID_NOT_EXIST);

	validator.validateCreateUser(request);

	deletePreviousData(userEntity);

	userEntity.getRoles().clear();

	UserEntity entity1 = repository.findByUserEmailAndUserIdNotIn(request.getUserEmail(), userId);
	if (entity1 != null) {
	    throw new IMException(Constants.ErrorCode.EMAIL_ALREADY_EXIST, Constants.ErrorMessage.EMAIL_ALREADY_EXIST);
	}

	userEntity = VOToEntityAssembler.updateUser(request, applicationContext.getRequestContext(), userEntity);

	logger.info("UserService.updateUser user saved successfully");

	List<RoleEntity> roleEntities = roleValidator.validateIfRolesExist(request.getRoles());

	userEntity.setUserAddresses(VOToEntityAssembler.updateUserAddress(userEntity, request,
		validator.validateAddressType(request), applicationContext.getRequestContext()));

	userEntity.setRoles(roleEntities);

	repository.save(userEntity);

	logger.info("UserService.updateUser user address saved successfully");

	return EntityToVOAssembler.convertUserEntity(userEntity, 0L);

    }

    /**
     * delete the previous address.
     *
     * @param userEntity
     *            the request
     * @return void
     */
    private void deletePreviousData(UserEntity userEntity) {
	if (userEntity != null) {
	    userAddressRepository.deleteAll(userEntity.getUserAddresses());
	}

    }

    /**
     * get email.
     *
     * @param name
     *            the request
     * @return userEntity
     */

    public UserEntity loadUserByUsername(String name) {
	return repository.findByUserEmail(name);
    }

    /**
     * delete the user
     *
     * @param userId
     *            the request
     * @return UserModel
     */

    public UserModel deleteUser(Long userId) throws IMException {
	UserEntity userEntity = repository.findByUserIdAndStatusStatusIdNotIn(userId, Constants.STATUS.DELETED.getId());
	if (userEntity == null)
	    throw new IMException(Constants.ErrorCode.USER_ID_NOT_EXIST, Constants.ErrorMessage.USER_ID_NOT_EXIST);
	userEntity.getRoles().clear();
	userEntity.setUserEmail(userEntity.getUserEmail() + userEntity.getUserId());
	userEntity.setUserContactNumber(userEntity.getUserContactNumber() + userEntity.getUserId());
	userEntity.setStatus(getStatusEntity(Constants.STATUS.DELETED.getId()));
	repository.save(userEntity);
	return null;
    }

    /**
     * update the user status
     *
     * @param userId
     *            the request
     * @return UserModel
     * @throws IMException
     */
    public StatusModel updateUserStatus(Long userId, StatusModel request) throws IMException {
	StatusModel model = new StatusModel();
	UserEntity userEntity = repository.findByUserIdAndStatusStatusIdNotIn(userId, Constants.STATUS.DELETED.getId());
	if (userEntity == null)
	    throw new IMException(Constants.ErrorCode.USER_ID_NOT_EXIST, Constants.ErrorMessage.USER_ID_NOT_EXIST);

	if (validator.isValidUser(Constants.CustomerType.SERVICE_PROVIDER, false)
		|| applicationContext.getRequestContext().getCustomerId().longValue() == userEntity.getCustomer()
			.getCustomerId().longValue()) {

	    StatusEntity entity = customerValidator.isValidStatus(request.getStatusId());
	    userEntity.setStatus(entity);
	    repository.save(userEntity);
	    return EntityToVOAssembler.convertStatus(userEntity, model);
	}

	return model;
    }

    public void changePassword(PasswordModel request) throws IMException {
	Long userId = applicationContext.getRequestContext().getUserId();
	logger.info("UserService.changePassword, changing password for user with id : "
		+ applicationContext.getRequestContext().getUserId());
	UserEntity userEntity = repository.findByUserIdAndStatusStatusIdNotIn(userId, Constants.STATUS.DELETED.getId());
	if (userEntity == null)
	    throw new IMException(Constants.ErrorCode.USER_ID_NOT_EXIST, Constants.ErrorMessage.USER_ID_NOT_EXIST);
	validator.validateChangePassword(request, userEntity.getPassword());
	userEntity.setPassword(
		PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(request.getNewPassword()));
	repository.save(userEntity);
	logger.info("UserService.changePassword, password changed successfully");

    }

    public UserCountModel getUsersCount() {
	logger.info("UserService.getUsersCount,Get user count for superadmin ");

	UserCountModel userCountModel = new UserCountModel();

	Long userCount = new Long(0L);
	Long serviceProvUserCount = new Long(0L);
	Long customerUsersCount = new Long(0L);
	Long partnerCustomerUsersCount = new Long(0L);

	List<Long> serviceProviderCustomerIds = new ArrayList<Long>();
	List<Long> customerIds = new ArrayList<Long>();
	List<Long> partnerIds = new ArrayList<Long>();

	userCount = repository.countByStatusStatusIdNotIn(Constants.STATUS.DELETED.getId());
	serviceProviderCustomerIds = customerRepo.findByCustomerTypeCustomerTypeIdAndStatusStatusIdNotIn(1L,
		Constants.STATUS.DELETED.getId());
	serviceProvUserCount = repository.getServiceProviderUser(serviceProviderCustomerIds,
		Constants.STATUS.DELETED.getId());

	customerIds = customerRepo.findByCustomerTypeCustomerTypeIdAndStatusStatusIdNotIn(2L,
		Constants.STATUS.DELETED.getId());
	customerUsersCount = repository.getServiceProviderUser(customerIds, Constants.STATUS.DELETED.getId());

	partnerIds = customerRepo.findByCustomerTypeCustomerTypeIdAndStatusStatusIdNotIn(4L,
		Constants.STATUS.DELETED.getId());
	partnerCustomerUsersCount = repository.getServiceProviderUser(partnerIds, Constants.STATUS.DELETED.getId());

	userCountModel.setTotalUsers(userCount);
	userCountModel.setServiceProviderUsers(serviceProvUserCount);
	userCountModel.setTotalCustomerUsers(customerUsersCount);
	userCountModel.setTotalPartnersUsers(partnerCustomerUsersCount);

	return userCountModel;
    }


    //Bulk Upload

    public UserCSVModel createUsersFromCSV(MultipartFile mpf) throws IOException, IMException {

	try {
	    List<UserCSVModel> operators = csvToDatabase(mpf.getInputStream());
	    int count = 0;
	    for (UserCSVModel userCSVModel : operators) {
		if (userCSVModel != null) {
		    logger.info(" UserCSVValues in loop " + count + "  :  " + userCSVModel);
		    UserEntity entity = repository.findByUserEmail(userCSVModel.getEmail());
		    if (entity != null) {
			throw new IMException(Constants.ErrorCode.EMAIL_ALREADY_EXIST,
				Constants.ErrorMessage.EMAIL_ALREADY_EXIST);
		    }
		    CustomerEntity customerEntity = validator.validateCustomerForBulkUpload(userCSVModel, 183L);

		    if (userCSVModel.getRole().contains("sub_client")) {
			createSubClientForBulkUpload(userCSVModel);
		    }

		    // Tables in which we are suppose to populate data
		    UserEntity user = new UserEntity();
		    DcmDevice device = new DcmDevice();
		    ScanLocation location = new ScanLocation();
		    DeviceCommodityEntity dce = new DeviceCommodityEntity();
		    List<DeviceCommodityEntity> dceList = new ArrayList<DeviceCommodityEntity>();
		    StateManagerOperatorsEntity smo = new StateManagerOperatorsEntity();

		    // User/Operator population
		    user = saveOperator(userCSVModel, customerEntity);
		    logger.info(" Saving user: "+user.getUserEmail());
		    user = repository.save(user);

		    // Location Population
		    location = saveLocation(userCSVModel, location);
		    logger.info(" Saving location: "+location.getWarehouseName()+" where code is : "+location.getCode());
		    location = locationRepo.save(location);

		    //Device Population

		    device = saveDevice(userCSVModel, user, location, device, customerEntity);
		    logger.info(" Saving device with serial number : "+device.getSerialNumber());
		    device = deviceRepo.save(device);

		    // DeviceCommodity 
		    dceList = saveDeviceCommodity(userCSVModel, device, dce);
		    dceList = dceRepo.saveAll(dceList);

		    //State Manager device mapping
		    smo = saveStateManager(smo, userCSVModel, user);
		    logger.info(" Saving sate manager with serial number : "+smo.getStateManagerId()+ " and operator id : "+smo.getOperatorId());
		    smo = smoRepo.save(smo);

		    count++;
		}
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new IOException(e);
	}
	return null;
    }

    private List<UserCSVModel> csvToDatabase(InputStream is) {
	//		BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	//		CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT...);

	try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		CSVParser csvParser = new CSVParser(fileReader,
			CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	    List<UserCSVModel> userCSVModelList = new ArrayList<UserCSVModel>();

	    Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	    for (CSVRecord csvRecord : csvRecords) {
		UserCSVModel userCsvModel = new UserCSVModel(

			csvRecord.get("first_name"), csvRecord.get("last_name"), csvRecord.get("email"),
			csvRecord.get("password"), csvRecord.get("contact_number"), csvRecord.get("role"),
			csvRecord.get("address"), csvRecord.get("country"), csvRecord.get("state"),
			csvRecord.get("city"), csvRecord.get("pincode"), csvRecord.get("state_admin_email"),
			csvRecord.get("location_name"), csvRecord.get("warehouse_name"),
			csvRecord.get("device_serial_no"), csvRecord.get("device_type"), csvRecord.get("category_name"),
			csvRecord.get("commodity_name"), csvRecord.get("location_code"),
			csvRecord.get("customer_email"));

		userCSVModelList.add(userCsvModel);
	    }

	    return userCSVModelList;
	} catch (IOException e) {
	    throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	}

    }

    public void createSubClientForBulkUpload(UserCSVModel request) throws IMException {
	logger.debug("UserService.createUser creating user in system");

	// save sub-client to customer table first
	CustomerModel customerModel = new CustomerModel();
	customerModel.setName(request.getFirstname() + request.getLastname());
	customerModel.setCustomerTypeId(5L);
	customerModel.setStatus(Constants.STATUS.ACTIVE.getId());
	customerModel.setEmail(request.getEmail());
	customerModel.setContactNumber(request.getContactnumber());

	List<CustomerAddressModel> addressModelList = new ArrayList<>();
	CustomerAddressModel addressModel = new CustomerAddressModel();
	addressModel.setAddressLine1(request.getAddress());
	//	    	addressModel.setAddressLine2(request.getUserAddresses().get(0).getAddressLine2());
	//	   	addressModel.setAddressTypeId(request.getUserAddresses().get(0).getAddressTypeId());
	addressModel.setCity(request.getCity());
	addressModel.setCountry(request.getCountry());
	addressModel.setCreatedBy(applicationContext.getRequestContext().getUserId());
	addressModel.setCreatedOn(Instant.now().getEpochSecond());
	addressModel.setPinCode(Integer.valueOf(request.getPincode()));
	addressModel.setState(request.getState());
	addressModelList.add(addressModel);

	customerModel.setCustomerAddresses(addressModelList);

	CustomerTypeEntity customerTypeEntity = new CustomerTypeEntity();
	customerTypeEntity.setCustomerTypeId(5L);

	CustomerEntity custEntity = VOToEntityAssembler.createCustomer(customerModel, customerTypeEntity,
		getStatusEntity(Constants.STATUS.ACTIVE.getId()), applicationContext.getRequestContext());

	customerValidator.validateSubClient(customerModel);

	custEntity.setCustomerAddresses(VOToEntityAssembler.createCustomerAddress(custEntity, customerModel,
		validator.validateAddressTypeForBulkUpload(request), applicationContext.getRequestContext(),
		getStatusEntity(Constants.STATUS.ACTIVE.getId())));

	CustomerEntity savedCustomerEntity = customerRepo.save(custEntity);

	// save sub-client to user table

	UserEntity userEntity = VOToEntityAssembler.createUserForBulkUpload(request, savedCustomerEntity,
		getStatusEntity(Constants.STATUS.ACTIVE.getId()), applicationContext.getRequestContext());

	logger.info("UserService.createUser user saved successfully");
	List<String> roles = new ArrayList<String>();
	roles.add(request.getRole());
	List<RoleEntity> roleEntities = roleValidator.validateIfRolesExist(roles);

	UserEntity supervisorEntity = repository.findByUserIdAndStatusStatusId(1L, Constants.STATUS.ACTIVE.getId());
	if (supervisorEntity != null) {
	    userEntity.setSupervisor(supervisorEntity);
	}
	AddressModel address=configureAddresForOperator(request);
	userEntity.setUserAddresses(VOToEntityAssembler.createUserAddressForBulkUpload(userEntity, request,
		validator.validateAddressTypeForBulkUpload(request), applicationContext.getRequestContext(),
		getStatusEntity(Constants.STATUS.ACTIVE.getId()),address));

	userEntity.setRoles(roleEntities);

	UserEntity entity = repository.findByUserEmail(request.getEmail());
	if (entity != null) {
	    throw new IMException(Constants.ErrorCode.EMAIL_ALREADY_EXIST, Constants.ErrorMessage.EMAIL_ALREADY_EXIST);
	}

	//	    	validator.validateCreateUser(request);

	String password = setRandomPassword(userEntity);

	repository.save(userEntity);

	//	    	emailUtilService.sendUserCreationMail(new String[] { userEntity.getUserEmail() }, null,
	//	    		userEntity.getUserFirstName(), userEntity.getUserEmail(), password);

	logger.info("UserService.createUser user address saved successfully");

	// UserModel response = EntityToVOAssembler.convertUserEntity(userEntity, 0L);

	//	    	logger.info("UserService.createUser response is :" + response);
	//
	//	    	return response;
    }

    private UserEntity saveOperator(UserCSVModel userCSVModel, CustomerEntity customerEntity) throws IMException {
	UserEntity user = new UserEntity();
	user = VOToEntityAssembler.createUserForBulkUpload(userCSVModel, customerEntity,
		getStatusEntity(Constants.STATUS.ACTIVE.getId()), applicationContext.getRequestContext());

	List<String> roles = new ArrayList<String>();
	roles.add(userCSVModel.getRole());
	List<RoleEntity> roleEntities = roleValidator.validateIfRolesExist(roles);
	user.setRoles(roleEntities);
	AddressModel address=configureAddresForOperator(userCSVModel);
	List<UserAddressEntity> userAddresses = VOToEntityAssembler.createUserAddressForBulkUpload(user, userCSVModel,
		validator.validateAddressTypeForBulkUpload(userCSVModel), applicationContext.getRequestContext(),
		getStatusEntity(Constants.STATUS.ACTIVE.getId()),address);
	user.setUserAddresses(userAddresses);
	String password = Utility.setRandomPassword(userCSVModel.getPassword());
	if (user.getPassword() == null || user.getPassword().isEmpty()) {
	    user.setPassword(password);
	}
	return user;
    }
	private AddressModel configureAddresForOperator(UserCSVModel userCSVModel) {
		AddressModel address= new AddressModel();
		if(userCSVModel.getCountry()!=null) {
			CountryEntity country=countryRepo.findByName(userCSVModel.getCountry());
			if(country !=null && country.getId() !=null) {
			address.setCountryId(country.getId());
			}else {
				CountryEntity customCountry=countryRepo.findByName("Custom Country");
				address.setCustomCountryId(customCountry.getId());
			}
			}
		
		if(userCSVModel.getState() !=null) {
		StateEntity state=stateRepo.findByNameAndCountryId(userCSVModel.getState(),address.getCountryId());
		if(state !=null && state.getId() !=null) {
		address.setStateId(state.getId());
		}else {
			StateEntity customCountry=state=stateRepo.findByName("Custom State");
			address.setCustomStateId(customCountry.getId());
		}
		}
		
		if(userCSVModel.getCity() !=null) {
			if(userCSVModel.getEmail().equalsIgnoreCase("nafed_rj17a.op@agnext.in")) {
				System.out.println();
				System.out.println();
				System.out.println("Error Prone");
				System.out.println(userCSVModel.getCity());
				System.out.println();
				System.out.println();
			}
		CityEntity city=cityRepo.findByNameAndStateId(userCSVModel.getCity(),address.getStateId());
		if(city !=null && city.getId() !=null) {
		address.setCityId(city.getId());
		}else {
			CityEntity customCity=cityRepo.findByName("Custom City");
			address.setCustomCityId(customCity.getId());
		}
		}
	
				
		return address;
	}

    private ScanLocation saveLocation(UserCSVModel userCSVModel, ScanLocation location) {
	if (userCSVModel != null && userCSVModel.getLocationcode() != null)
	    location.setCreatedOn(Instant.now().toEpochMilli());
	location.setCode(userCSVModel.getLocationcode());
	location.setLocationName(userCSVModel.getWarehouseName());
	location.setWarehouseName(userCSVModel.getLocationname());
	StatusEntity status = statusRepository.getOne(Constants.STATUS.ACTIVE.getId());
	StateEntity state = stateRepo.findByName(userCSVModel.getState());
	if (state != null) {
	    location.setState(state);
	}
	if (status != null) {
	    location.setStatus(status);
	}
	return location;
    }

    private DcmDevice saveDevice(UserCSVModel userCSVModel, UserEntity user, ScanLocation location, DcmDevice device,
	    CustomerEntity customerEntity) throws IMException {
	long now = Instant.now().toEpochMilli();
	device.setCreatedOn(now);

	DcmDevice deviceSerialNumberCheck = deviceRepo.findBySerialNumber(userCSVModel.getDeviceserialno());
	if (deviceSerialNumberCheck == null) {
	    device.setSerialNumber(userCSVModel.getDeviceserialno());
	} else {
	    throw new IMException(Constants.ErrorCode.SERIAL_NUMBER_EXIST, Constants.ErrorMessage.SERIAL_NUMBER_EXIST);
	}
	DcmDeviceType deviceTypeId = deviceTypeRepo.findByDeviceTypeDesc(userCSVModel.getDevicetype());
	DcmDeviceType type= new DcmDeviceType();
	if(deviceTypeId !=null) {
	 type = deviceValidator.validateDcmDeviceType(deviceTypeId.getId());
	}else {
		 type = deviceValidator.validateDcmDeviceType(1L);
	}
	device.setDcmDeviceType(type);
	device.setCustomerId(customerEntity.getCustomerId());
	device.setDcmStatus(statusRepository.findByStatusId(Constants.STATUS.ACTIVE.getId()));
	device.setScanLocation(location);
	device.setUserId(user.getUserId());
	device.setStartOfLife(now);
	device.setStartOfService(now);
	Calendar c = Calendar.getInstance();
	device.setStartOfLife(now);
	c.add(Calendar.YEAR, 2);
	device.setEndOfLife(c.getTimeInMillis());
	device.setEndOfService(c.getTimeInMillis());
	device.setHwRevision("1");
	device.setFwRevision("1");
	return device;
    }


    private List<DeviceCommodityEntity> saveDeviceCommodity(UserCSVModel userCSVModel, DcmDevice device,
	    DeviceCommodityEntity dce) {
	String[] commoditiesName = userCSVModel.getCommodityname().split(",");
	List<DeviceCommodityEntity> dceList = new ArrayList<DeviceCommodityEntity>();
	List<String> commodityNameList=Arrays.asList(commoditiesName);
	List<DcmCommodity> commodities = commodityRepo.findByCommodityNameIn(commodityNameList);
	logger.debug(" Commodity string array  " + commoditiesName);
	/* Different approach to solve the single commodity return issue of above query*/
	for (String commodityName : commoditiesName) {
		DcmCommodity commodity= commodityRepo.getCommodityName(commodityName.trim());
		if(commodity !=null && commodity.getId() !=null) {
			 DeviceCommodityEntity dceNew = new DeviceCommodityEntity();
			    logger.debug(" Commodities " + commodities);
			    logger.debug(" Commodity  " + commodity);
			    dceNew.setCommodity(commodity);
			    dceNew.setDevice(device);
			    dceNew.setStatusId(8);
			    //			dceRepo.save(dce);
			    dceList.add(dceNew);
		}
	}

//	logger.debug("  userCSVModel.getCommodityname()  " + userCSVModel.getCommodityname());
//	for (DcmCommodity commodity : commodities) {
//	    DeviceCommodityEntity dceNew = new DeviceCommodityEntity();
//	    logger.debug(" Commodities " + commodities);
//	    logger.debug(" Commodity  " + commodity);
//	    dceNew.setCommodity(commodity);
//	    dceNew.setDevice(device);
//	    dceNew.setStatusId(8);
//	    //			dceRepo.save(dce);
//	    dceList.add(dceNew);
//	}

	return dceList;
    }

    private StateManagerOperatorsEntity saveStateManager(StateManagerOperatorsEntity smo, UserCSVModel userCSVModel,
	    UserEntity user) {
	smo.setOperatorId(user.getUserId());
	UserEntity stateAdmin = repository.findByUserEmail(userCSVModel.getStateadminemail());
	logger.info("State Admin : " + stateAdmin.getUserEmail() + " State Admin Id : " + stateAdmin.getUserId());
	smo.setStateManagerId(stateAdmin.getUserId());
	smo.setStatus(8);
	return smo;
    }


}
