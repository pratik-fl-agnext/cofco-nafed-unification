/*
 * @author Vishal Bansal
 * @version 1.0
 */
package com.agnext.unification.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

import com.agnext.unification.assembler.EntityToVOAssembler;
import com.agnext.unification.assembler.VOToEntityAssembler;
import com.agnext.unification.common.Constants;
import com.agnext.unification.communication.RestTemplateCall;
import com.agnext.unification.entity.nafed.CustomerEntity;
import com.agnext.unification.entity.nafed.CustomerTypeEntity;
import com.agnext.unification.entity.nafed.StatusEntity;
import com.agnext.unification.entity.nafed.UserEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.ClientDetailsModel;
import com.agnext.unification.model.CustomerCommodityAssignmentModel;
import com.agnext.unification.model.CustomerCountModel;
import com.agnext.unification.model.CustomerModel;
import com.agnext.unification.model.StatusModel;
import com.agnext.unification.repository.nafed.CustomerAddressRepository;
import com.agnext.unification.repository.nafed.CustomerBankDetailRepository;
import com.agnext.unification.repository.nafed.CustomerBillingDetailRepository;
import com.agnext.unification.repository.nafed.CustomerRepository;
import com.agnext.unification.repository.nafed.CustomerTypeRepository;
import com.agnext.unification.repository.nafed.FilterNativeRepository;
import com.agnext.unification.repository.nafed.RoleRepository;
import com.agnext.unification.repository.nafed.UserAddressRepository;
import com.agnext.unification.repository.nafed.UserRepository;
import com.agnext.unification.utility.EmailUtil;
import com.agnext.unification.validator.CustomerValidator;
import com.agnext.unification.validator.RoleValidator;
import com.agnext.unification.validator.UserValidator;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CustomerService extends GenericService {

    private static Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerValidator validator;

    @Autowired
    CustomerRepository repository;

    @Autowired
    RoleValidator roleValidator;

    @Autowired
    CustomerTypeRepository customerTypeRepository;

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserValidator userValidator;

    @Autowired
    CustomerAddressRepository customerAddressRepo;

    @Autowired
    CustomerBankDetailRepository bankDetailRepo;

    @Autowired
    CustomerBillingDetailRepository billingDetailRepo;

    @Autowired
    UserAddressRepository userAddressRepo;

    @Autowired
    EmailUtil emailUtilService;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    FilterNativeRepository filterNativeRepo;

    @Autowired
    RestTemplateCall restCall;

    @Value("${email-config.send-email}")
    private Boolean sendEmailCheck;

    /**
     * Creates the customer.
     *
     * @param request
     *            the request
     * @return the user model
     * @throws IMException
     *             the IM exception
     */

    private CustomerTypeEntity CUSTOMER_TYPE_PARTNER = null;

    private void initiateCustomerType() {
	if (CUSTOMER_TYPE_PARTNER == null)
	    CUSTOMER_TYPE_PARTNER = customerTypeRepository.findByCustomerType(Constants.CustomerType.PARTNER);
    }

    public CustomerModel createCustomer(CustomerModel request) throws IMException {
	initiateCustomerType();
	List<UserEntity> lstUser = new ArrayList<UserEntity>(1);
	CustomerTypeEntity customerTypeEntity = null;
	StatusEntity statusEntity = null;
	CustomerEntity savedCustomerEntity = new CustomerEntity();

	if (request.isPartner()) {
	    statusEntity = getStatusEntity(Constants.STATUS.ACTIVE.getId());
	    customerTypeEntity = customerTypeRepository.findByCustomerType(Constants.CustomerType.PARTNER);
	} else {
	    if (request.getPartnerId() != null)
		statusEntity = getStatusEntity(Constants.STATUS.INITIATED.getId());
	    else
		statusEntity = getStatusEntity(Constants.STATUS.ACTIVE.getId());
	    customerTypeEntity = validator.createCustomerTypeValidate(request.getCustomerTypeId());
	}

	CustomerEntity customerEntity = VOToEntityAssembler.createCustomer(request, customerTypeEntity, statusEntity,
		applicationContext.getRequestContext());

	logger.info("CustomerService.createCustomer customer saved successfully");

	CustomerEntity csEntity = repository.findByCustomerEmail(request.getEmail());
	if (csEntity != null) {
	    throw new IMException(Constants.ErrorCode.EMAIL_ALREADY_EXIST, Constants.ErrorMessage.EMAIL_ALREADY_EXIST);
	}

	validator.validateCreateCustomer(request);

	customerEntity.setCustomerAddresses(VOToEntityAssembler.createCustomerAddress(customerEntity, request,
		validator.validateAddressType(request), applicationContext.getRequestContext(),
		getStatusEntity(Constants.STATUS.ACTIVE.getId())));

	if (!request.getCustomerBankDetails().get(0).getBankName().isEmpty()
		&& !request.getCustomerBankDetails().get(0).getBankBranch().isEmpty()
		&& !request.getCustomerBankDetails().get(0).getBankAccountNumber().isEmpty()
		&& !request.getCustomerBankDetails().get(0).getBankIfsc().isEmpty()) {
	    validator.validateCustomerBankDetail(request);

	    customerEntity.setCustomerBankDetails(VOToEntityAssembler.createCustomerBankDetail(customerEntity, request,
		    applicationContext.getRequestContext()));
	}

	customerEntity.setCustomerBillingDetails(VOToEntityAssembler.createCustomerBillingDetail(customerEntity,
		request, applicationContext.getRequestContext()));

	UserEntity userEntity = VOToEntityAssembler.convertUser(request.getUserModel(), customerEntity,
		applicationContext.getRequestContext(), statusEntity);

	// Set Default Admin Role for user

	UserEntity entity = userRepo.findByUserEmail(request.getUserModel().getUserEmail());
	if (entity != null) {
	    throw new IMException(Constants.ErrorCode.EMAIL_ALREADY_EXIST, Constants.ErrorMessage.EMAIL_ALREADY_EXIST);
	}

	userValidator.validateCreateUser(request.getUserModel());
	if (request.isPartner())
	    userEntity.setRoles(Arrays.asList(roleRepo.getOne(2L)));
	else
	    userEntity.setRoles(Arrays.asList(roleRepo.getOne(11L)));
	String password = setRandomPassword(userEntity);

	userEntity.setUserAddresses(VOToEntityAssembler.createUserAddress(userEntity, request.getUserModel(),
		userValidator.validateAddressType(request.getUserModel()), applicationContext.getRequestContext(),
		getStatusEntity(Constants.STATUS.ACTIVE.getId())));

	lstUser.add(userEntity);

	customerEntity.setUsers(lstUser);

	savedCustomerEntity = repository.save(customerEntity);
	if (savedCustomerEntity != null) {
	    convert(request, savedCustomerEntity);
	}

	if (sendEmailCheck == Boolean.TRUE) {
	    emailUtilService.sendCustomerCreationMail(null, new String[] { customerEntity.getCustomerEmail() },
		    customerEntity.getCustomerName(), customerEntity.getCustomerEmail(), password);
	}
	logger.info("CustomerService.createUser user and user  address saved successfully");

	CustomerModel response = EntityToVOAssembler.convertCustomerEntity(customerEntity, true);
	logger.info("CustomerService.createCustomer response is :" + response);

	return response;
    }

    private void convert(CustomerModel request, CustomerEntity savedCustomerEntity) {
	String token = applicationContext.getRequestContext().getAccessToken();
	CustomerCommodityAssignmentModel cusCommModel = new CustomerCommodityAssignmentModel();
	if (request.getCommodityCategoryId() != null) {
	    Optional.ofNullable(request.getCommodityCategoryId()).ifPresent(cusCommModel::setCommodityCategoryId);
	}
	Optional.ofNullable(savedCustomerEntity.getCustomerId()).ifPresent(cusCommModel::setCustomerId);
	Optional.ofNullable(request.getStartOfSubscription()).ifPresent(cusCommModel::setStartOfSubscription);
	Optional.ofNullable(request.getEndOfSubscription()).ifPresent(cusCommModel::setEndOfSubscription);
	Optional.ofNullable(request.getProductId()).ifPresent(cusCommModel::setProductId);
	restCall.customerCommAssignment(cusCommModel, token);

    }

    private String setRandomPassword(UserEntity userEntity) {
	String password = RandomStringUtils.randomAlphanumeric(12);
	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	userEntity.setPassword(encoder.encode(password));
	return password;
    }

    public List<CustomerModel> geCustomers(Integer pageNumber, Integer limit, String keySearch, String customerType)
	    throws Exception {
	logger.info("get list of customers ");

	if (!validator.isValidUser(Constants.CustomerType.SERVICE_PROVIDER, false))
	    validator.isValidUser(Constants.CustomerType.PARTNER, true);
	List<CustomerEntity> customerEntities = null;
	Long count = new Long(0L);
	if (Constants.CustomerType.PARTNER.equals(applicationContext.getRequestContext().getCustomerType())) {
	    customerEntities = repository
		    .findByPartnerIdOrderByStatusStatusId(applicationContext.getRequestContext().getCustomerId());
	    if (customerEntities != null)
		count = new Long(customerEntities.size());

	} else {
	    customerEntities = filterNativeRepo.getCustomerDetailByFilters(keySearch, pageNumber, limit, customerType);

	    count = filterNativeRepo.getCustomerDetailCount(keySearch, customerType);
	}

	return EntityToVOAssembler.converListOfCustomers(customerEntities, count);
    }

    public CustomerModel getCustomerById(Long customerId) throws Exception {
	logger.info("get Customer by customer uuid ");
	String token = applicationContext.getRequestContext().getAccessToken();
	Long[] commodityCategoryIds = null;
	if (validator.isValidUser(Constants.CustomerType.SERVICE_PROVIDER, false)
		|| applicationContext.getRequestContext().getCustomerId() == customerId) {
	    commodityCategoryIds = restCall.getCommodityCategoriesByCustomerId(customerId, token);
	    return EntityToVOAssembler.convertCustomerEntity(repository.getOne(customerId), false,
		    commodityCategoryIds);
	} else
	    throw new IMException(Constants.ErrorCode.USER_NOT_AUTHORIZED, Constants.ErrorMessage.USER_NOT_AUTHORIZED);

    }

    public CustomerModel updateCustomerById(CustomerModel request) throws IMException {

	logger.info("Update customer detail by customer id ");
	validator.isValidUser(Constants.CustomerType.SERVICE_PROVIDER, true);

	CustomerEntity customerEntity = repository.findByCustomerIdAndStatusStatusIdNotIn(request.getCustomerId(),
		Constants.STATUS.DELETED.getId());
	if (customerEntity == null)
	    throw new IMException(Constants.ErrorCode.CUSTOMER_ID_NOT_EXIST,
		    Constants.ErrorMessage.CUSTOMER_ID_NOT_EXIST);

	//CustomerTypeEntity customerTypeEntity = validator.createCustomerTypeValidate(request.getCustomerTypeId());

	CustomerEntity entity1 = repository.findByCustomerEmailAndCustomerIdNotIn(request.getEmail(),
		request.getCustomerId());
	if (entity1 != null) {
	    throw new IMException(Constants.ErrorCode.EMAIL_ALREADY_EXIST, Constants.ErrorMessage.EMAIL_ALREADY_EXIST);
	}

	validator.validateUpdateCustomer(request);

	if (!request.getCustomerBankDetails().get(0).getBankName().isEmpty()
		&& !request.getCustomerBankDetails().get(0).getBankBranch().isEmpty()
		&& !request.getCustomerBankDetails().get(0).getBankAccountNumber().isEmpty()
		&& !request.getCustomerBankDetails().get(0).getBankIfsc().isEmpty()) {
	    validator.validateCustomerBankDetail(request);
	}
	deletePreviousData(request, customerEntity);

	customerEntity = VOToEntityAssembler.updateCustomer(request, null, applicationContext.getRequestContext(),
		customerEntity, validator.validateAddressType(request));

	return EntityToVOAssembler.convertCustomerEntity(repository.save(customerEntity), false);

    }

    private void deletePreviousData(CustomerModel request, CustomerEntity customerEntity) {
	if (customerEntity != null) {
	    convert(request, customerEntity);
	    customerAddressRepo.deleteAll(customerEntity.getCustomerAddresses());
	    if (customerEntity.getCustomerBillingDetails() != null
		    && customerEntity.getCustomerBillingDetails().size() > 0) {
		billingDetailRepo.deleteAll(customerEntity.getCustomerBillingDetails());
	    }
	    if (customerEntity.getCustomerBankDetails() != null && customerEntity.getCustomerBankDetails().size() > 0) {
		bankDetailRepo.deleteAll(customerEntity.getCustomerBankDetails());
	    }
	}

    }

    public CustomerModel deleteCustomerById(Long customerId) throws IMException {
	logger.info("delete customer by customer id");

	CustomerEntity customerEntity = repository.findByCustomerIdAndStatusStatusIdNotIn(customerId,
		Constants.STATUS.DELETED.getId());
	if (customerEntity == null)
	    throw new IMException(Constants.ErrorCode.CUSTOMER_ID_NOT_EXIST,
		    Constants.ErrorMessage.CUSTOMER_ID_NOT_EXIST);

	long count = userRepo.countByCustomerCustomerIdAndStatusStatusIdNotIn(customerEntity.getCustomerId(),
		Constants.STATUS.DELETED.getId());

	if (count > 0) {
	    throw new IMException("1222", "Customer can't be deleted, users found associated with it.");
	}

	customerEntity.setStatus(getStatusEntity(Constants.STATUS.DELETED.getId()));
	repository.save(customerEntity);
	return null;

    }

    public StatusModel updateCustomerStatus(Long customerId, StatusModel request) throws IMException {
	StatusModel model = new StatusModel();
	CustomerEntity customerEntity = repository.findByCustomerIdAndStatusStatusIdNotIn(customerId,
		Constants.STATUS.DELETED.getId());
	if (customerEntity == null)
	    throw new IMException(Constants.ErrorCode.CUSTOMER_ID_NOT_EXIST,
		    Constants.ErrorMessage.CUSTOMER_ID_NOT_EXIST);
	validator.isValidUser(Constants.CustomerType.SERVICE_PROVIDER, true);
	StatusEntity entity = validator.isValidStatus(request.getStatusId());
	customerEntity.setStatus(entity);
	repository.save(customerEntity);
	return EntityToVOAssembler.convertStatus(customerEntity, model);

    }

    public CustomerModel updateCustomerByIdAndUserStatusUpdate(CustomerModel request) throws IMException {
	request.setPartnerId(applicationContext.getRequestContext().getCustomerId());
	CustomerEntity customerEntity = validator.validateCustomerId(request);
	UserEntity userEntity = validator.validateUserForCustomer(customerEntity);
	customerEntity.setStatus(getStatusEntity(Constants.STATUS.ACTIVE.getId()));
	customerEntity.setRemarks(request.getRemarks());
	repository.save(customerEntity);
	userEntity.setStatus(getStatusEntity(Constants.STATUS.ACTIVE.getId()));
	userRepo.save(userEntity);
	return EntityToVOAssembler.convertCustomerEntity(customerEntity, false);
    }

    public CustomerCountModel getCustomersCount(Integer pageNumber, Integer limit) {
	logger.info("get count of all customers for superadmin");
	CustomerCountModel customerCountModel = new CustomerCountModel();
	Long totalCustomers = new Long(0L);
	Long totalPartners = new Long(0L);
	Long customersUnderPartners = new Long(0L);

	//		CustomerWidgetModel customerWidgetModel= new CustomerWidgetModel();		
	//		List<CustomerModel> totalCustomerModel= new ArrayList<CustomerModel>();
	//		List<CustomerModel>partnersModel= new ArrayList<CustomerModel>();
	//		List<CustomerModel>customerUnderPartnersModel= new ArrayList<CustomerModel>();
	//		
	//		List<CustomerEntity> totalCustomerEntities = null;
	//		Long totalCount = new Long(0L);
	//		
	//		List<CustomerEntity> partnerEntities = null;
	//		Long partnerCount = new Long(0L);
	//		
	//		List<CustomerEntity> customersUnderPartners = null;
	//		Long customersUnderPartnersCount = new Long(0L);
	//		
	//		totalCustomerEntities=filterNativeRepo.getCustomerDetailByFilters(null,pageNumber,limit,null);
	//		totalCount=repository.countByStatusStatusIdNotIn(Constants.STATUS.DELETED.getId());
	//		totalCustomerModel= EntityToVOAssembler.converListOfCustomers(totalCustomerEntities, totalCount);
	//		partnerEntities=totalCustomerEntities=filterNativeRepo.getCustomerDetailByFilters(null,pageNumber,limit,Constants.CustomerType.PARTNER);
	//		partnerCount=repository.countByCustomerTypeCustomerTypeIdAndStatusStatusIdNotIn(4L, Constants.STATUS.DELETED.getId());
	//		partnersModel= EntityToVOAssembler.converListOfCustomers(partnerEntities, partnerCount);
	//		
	//		customerWidgetModel.setCustomerDetails(totalCustomerModel);
	//		customerWidgetModel.setPartnersDetails(partnersModel);
	totalCustomers = repository.countByStatusStatusIdNotIn(Constants.STATUS.DELETED.getId());
	totalPartners = repository.countByCustomerTypeCustomerTypeIdAndStatusStatusIdNotIn(4L,
		Constants.STATUS.DELETED.getId());
	List<Long> partnerIds = repository.findByCustomerTypeCustomerTypeIdAndStatusStatusIdNotIn(4L,
		Constants.STATUS.DELETED.getId());
	customersUnderPartners = repository.getCustomersUnderPartnersCount(partnerIds,
		Constants.STATUS.DELETED.getId());
	customerCountModel.setTotalCustomers(totalCustomers);
	customerCountModel.setTotalPartners(totalPartners);
	customerCountModel.setCustomersUnderPartners(customersUnderPartners);
	return customerCountModel;
    }

    public String getCustomerNameById(Long customerId) {
	String name = "";
	name = repository.findCustomerNameById(customerId);

	return name;
    }

    public List<ClientDetailsModel> getClientDetails() {
	List<ClientDetailsModel> clientDetails = new ArrayList<ClientDetailsModel>();

	List<Long> clients = repository.findByCustomerTypeCustomerTypeIdAndStatusStatusIdNotIn(5L,
		Constants.STATUS.DELETED.getId());
	List<UserEntity> clientsUser = userRepo.getClientsUser(clients, Constants.STATUS.DELETED.getId());

	for (UserEntity userEntity : clientsUser) {
	    if (!userEntity.getUserEmail().equals("ai@agnext.in")) {
		ClientDetailsModel client = new ClientDetailsModel();
		client.setClientId(userEntity.getUserId());
		client.setClientName(userEntity.getUserFirstName());
		client.setPhoneNumber(userEntity.getUserContactNumber());
		client.setUsername(userEntity.getUserEmail());
		client.setCustomerId(userEntity.getCustomer().getCustomerId());
		client.setCustomerName(userEntity.getCustomer().getCustomerName());

		clientDetails.add(client);
	    }

	}

	return clientDetails;
    }

    public List<ClientDetailsModel> getOperators() {

	return null;
    }
}
