/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.assembler;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.agnext.unification.common.CommonUtil;
import com.agnext.unification.common.Constants;
import com.agnext.unification.config.AnalyticsVariations;
import com.agnext.unification.entity.nafed.CityEntity;
import com.agnext.unification.entity.nafed.ClientsAnalyticsRange;
import com.agnext.unification.entity.nafed.CommodityVarietyEntity;
import com.agnext.unification.entity.nafed.CountryEntity;
import com.agnext.unification.entity.nafed.CustomerAddressEntity;
import com.agnext.unification.entity.nafed.CustomerBankDetailEntity;
import com.agnext.unification.entity.nafed.CustomerBillingDetailEntity;
import com.agnext.unification.entity.nafed.CustomerEntity;
import com.agnext.unification.entity.nafed.DcmCommodity;
import com.agnext.unification.entity.nafed.DcmCommodityCategory;
import com.agnext.unification.entity.nafed.DcmDevice;
import com.agnext.unification.entity.nafed.DcmDeviceGroup;
import com.agnext.unification.entity.nafed.DcmDeviceSubType;
import com.agnext.unification.entity.nafed.DcmDeviceType;
import com.agnext.unification.entity.nafed.DcmSensorProfile;
import com.agnext.unification.entity.nafed.DcmUserDevice;
import com.agnext.unification.entity.nafed.Packages;
import com.agnext.unification.entity.nafed.PermissionEntity;
import com.agnext.unification.entity.nafed.RoleEntity;
import com.agnext.unification.entity.nafed.RolePermissionEntity;
import com.agnext.unification.entity.nafed.ScanEntity;
import com.agnext.unification.entity.nafed.ScanLocation;
import com.agnext.unification.entity.nafed.ScanResultEntity;
import com.agnext.unification.entity.nafed.StateEntity;
import com.agnext.unification.entity.nafed.StateManagerOperatorsEntity;
import com.agnext.unification.entity.nafed.UserAddressEntity;
import com.agnext.unification.entity.nafed.UserEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.Analytics;
import com.agnext.unification.model.AnalyticsRangeModel;
import com.agnext.unification.model.AuthenticationModel;
import com.agnext.unification.model.CityModel;
import com.agnext.unification.model.CommodityCategoryModel;
import com.agnext.unification.model.CommodityModel;
import com.agnext.unification.model.CommodityVarietyModel;
import com.agnext.unification.model.CountryModel;
import com.agnext.unification.model.CustomerAddressModel;
import com.agnext.unification.model.CustomerBankDetailModel;
import com.agnext.unification.model.CustomerBillingDetailModel;
import com.agnext.unification.model.CustomerModel;
import com.agnext.unification.model.DeviceCommodityModel;
import com.agnext.unification.model.DeviceGroupModel;
import com.agnext.unification.model.DeviceModel;
import com.agnext.unification.model.DeviceSensorProfileModel;
import com.agnext.unification.model.DeviceSubTypeModel;
import com.agnext.unification.model.DeviceTypeModel;
import com.agnext.unification.model.LocationModel;
import com.agnext.unification.model.LoginResponseModel;
import com.agnext.unification.model.MetaDataModel;
import com.agnext.unification.model.PackagesModel;
import com.agnext.unification.model.PermissionModel;
import com.agnext.unification.model.RangeModel;
import com.agnext.unification.model.RoleModel;
import com.agnext.unification.model.ScanModel;
import com.agnext.unification.model.StateModel;
import com.agnext.unification.model.StatusModel;
import com.agnext.unification.model.UserAddressModel;
import com.agnext.unification.model.UserDeviceModel;
import com.agnext.unification.model.UserModel;
import com.agnext.unification.model.VarietyModel;
import com.agnext.unification.model.WeightConverterModel;
import com.agnext.unification.repository.nafed.CustomerRepository;
import com.agnext.unification.utility.Utility;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class EntityToVOAssembler.
 */
public final class EntityToVOAssembler {

    private static Logger logger = LoggerFactory.getLogger(EntityToVOAssembler.class);

    public EntityToVOAssembler() {
    }

    /**
     * Sets the new permission.
     *
     * @param entity
     *            the entity
     * @return the permission model
     */

    public static PermissionModel convert(PermissionEntity entity) {
	PermissionModel model = new PermissionModel();
	model.setPermissionCode(entity.getPermissionCode());
	model.setPermissionDesc(entity.getPermissionDesc());
	return model;
    }

    public static List<DeviceTypeModel> convertDeviceTypeDetail(List<DcmDeviceType> deviceTypeLst) {
	List<DeviceTypeModel> deviceTypeModels = new ArrayList<>();
	if (deviceTypeLst != null) {
	    for (DcmDeviceType dcmDeviceType : deviceTypeLst) {
		DeviceTypeModel deviceTypeModel = new DeviceTypeModel();
		deviceTypeModel.setDeviceTypeId(dcmDeviceType.getId());
		deviceTypeModel.setDeviceTypeDesc(dcmDeviceType.getDeviceTypeDesc());
		deviceTypeModels.add(deviceTypeModel);
	    }
	}

	return deviceTypeModels;
    }

    /**
     * convert.
     *
     * @param entity
     *            the entity
     * @return the role model
     */
    public static RoleModel convert(RoleEntity entity) {
	RoleModel model = new RoleModel();
	model.setRoleCode(entity.getRoleCode());
	model.setRoleDesc(entity.getRoleDesc());
	return model;
    }

    public static RoleModel convert(List<RolePermissionEntity> rolePermissionEntities) {

	if (CommonUtil.isEmpty(rolePermissionEntities))
	    return null;

	RoleModel response = convert(rolePermissionEntities.get(0).getRole());

	List<PermissionModel> permissions = new ArrayList<PermissionModel>();
	for (RolePermissionEntity rolePermissionEntity : rolePermissionEntities) {
	    permissions.add(convert(rolePermissionEntity.getPermission()));
	}
	response.setPermissions(permissions);

	return response;
    }

    public static List<RoleModel> convertRoleEntityToRoleModel(List<RoleEntity> roleEntities) {
	List<RoleModel> roleModelLst = new ArrayList<>();
	List<PermissionModel> permissionModelLst = new ArrayList<>();
	RoleModel roleModel = new RoleModel();
	roleModel.setRoleCode(roleEntities.get(0).getRoleCode());
	roleModel.setRoleDesc(roleEntities.get(0).getRoleDesc());
	for (PermissionEntity permissionEntity : roleEntities.get(0).getPermissions()) {
	    PermissionModel permissionModel = new PermissionModel();
	    permissionModel.setPermissionCode(permissionEntity.getPermissionCode());
	    permissionModel.setPermissionDesc(permissionEntity.getPermissionDesc());
	    permissionModelLst.add(permissionModel);
	}
	roleModel.setPermissions(permissionModelLst);
	roleModelLst.add(roleModel);

	return roleModelLst;
    }

    public static UserModel convertUserEntity(UserEntity entity, Long count) {
	List<UserAddressModel> lstUserAddressModel = new ArrayList<>();
	List<RoleModel> roleModelLst = new ArrayList<>();
	UserModel userModel = new UserModel();

	if (entity.getCustomer() != null)
	    userModel.setCustomerId(entity.getCustomer().getCustomerId());
	userModel.setCreatedOn(entity.getCreatedOn());
	//		userModel.setUserAlternateContactNumber(entity.getUserAlternateContactNumber());
	userModel.setUserContactNumber(entity.getUserContactNumber());
	userModel.setUserEmail(entity.getUserEmail());
	userModel.setUserFirstName(entity.getUserFirstName());
	userModel.setUserLastName(entity.getUserLastName());
	userModel.setUserId(entity.getUserId());
	userModel.setCreatedBy(entity.getCreatedBy());
	userModel.setCreatedOn(entity.getCreatedOn());
	userModel.setStatusId(entity.getStatus().getStatusId());
	userModel.setStatusName(entity.getStatus().getStatusDesc());
	userModel.setUserHierarchy(entity.getUserHierarchy());
	if (entity.getSupervisor() != null) {
	    userModel.setSupervisorId(entity.getSupervisor().getUserId());
	    userModel.setSupervisorName(
		    entity.getSupervisor().getUserFirstName() + " " + entity.getSupervisor().getUserLastName());
	}

	for (UserAddressEntity userAddressEntity : entity.getUserAddresses()) {
	    UserAddressModel addressModel = new UserAddressModel();
	    addressModel.setAddressId(userAddressEntity.getAddressId());
	    addressModel.setAddressLine1(userAddressEntity.getAddressLine1());
	    addressModel.setAddressLine2(userAddressEntity.getAddressLine2());
	    addressModel.setState(userAddressEntity.getState());
	    addressModel.setCity(userAddressEntity.getCity());
	    addressModel.setCountry(userAddressEntity.getCountry());
	    addressModel.setCreatedBy(userAddressEntity.getCreatedBy());
	    addressModel.setCreatedOn(userAddressEntity.getCreatedOn());
	    addressModel.setPinCode(userAddressEntity.getZipCode());
	    if (userAddressEntity.getAddressType() != null)
		addressModel.setAddressTypeId(userAddressEntity.getAddressType().getAddressTypeId());
	    lstUserAddressModel.add(addressModel);
	}

	for (RoleEntity roleEntity : entity.getRoles()) {
	    RoleModel roleModel = new RoleModel();
	    //			roleModel.setRoleId(roleEntity.getRoleId());
	    roleModel.setRoleCode(roleEntity.getRoleCode());
	    roleModel.setRoleDesc(roleEntity.getRoleDesc());

	    List<PermissionModel> permissionModelLst = new ArrayList<>();

	    for (PermissionEntity permissionEntity : roleEntity.getPermissions()) {
		PermissionModel permissionModel = new PermissionModel();
		//				permissionModel.setPermissionId(permissionEntity.getPermissionId());
		permissionModel.setPermissionCode(permissionEntity.getPermissionCode());
		permissionModel.setPermissionDesc(permissionEntity.getPermissionDesc());
		//				permissionModel.setPermissionId(permissionEntity.getPermissionId());
		//				permissionModel.setStatus(permissionEntity.getStatus().getStatusId());
		permissionModelLst.add(permissionModel);
	    }
	    roleModel.setPermissions(permissionModelLst);
	    roleModelLst.add(roleModel);
	}
	userModel.setTotalCount(count);
	userModel.setRoleList(roleModelLst);
	userModel.setUserAddresses(lstUserAddressModel);
	return userModel;
    }

    public static CustomerModel convertCustomerEntity(CustomerEntity entity, boolean isUserDataRequired) {
	if (entity == null)
	    return null;
	List<CustomerAddressModel> lstCustomerAddressModel = new ArrayList<>();
	List<RoleModel> roleModelLst = new ArrayList<>();
	List<CustomerBankDetailModel> lstCustomerBankDetailModel = new ArrayList<>();
	List<CustomerBillingDetailModel> lstCustomerBillingDetailModel = new ArrayList<>();
	CustomerModel customerModel = null;

	if (entity != null) {
	    customerModel = new CustomerModel();
	    customerModel.setCreatedOn(entity.getCreatedOn());
	    customerModel.setName(entity.getCustomerName());
	    customerModel.setCreatedOn(entity.getCreatedOn());
	    if (entity.getCustomerType() != null)
		customerModel.setCustomerTypeId(entity.getCustomerType().getCustomerTypeId());
	    customerModel.setContactNumber(entity.getCustomerContactNumber());
	    customerModel.setEmail(entity.getCustomerEmail());
	    customerModel.setName(entity.getCustomerName());
	    customerModel.setGst(entity.getCustomerGst());
	    customerModel.setPan(entity.getCustomerPan());
	    customerModel.setCustomerTypeId(entity.getCustomerType().getCustomerTypeId());
	    customerModel.setCustomerType(entity.getCustomerType().getCustomerType());
	    customerModel.setCustomerUuid(entity.getCustomerUuid());
	    customerModel.setCustomerId(entity.getCustomerId());
	    if (entity.getPartnerId() != null)
		customerModel.setPartnerId(entity.getPartnerId());

	    for (CustomerAddressEntity customerAddressEntity : entity.getCustomerAddresses()) {
		CustomerAddressModel addressModel = new CustomerAddressModel();
		addressModel.setAddressId(customerAddressEntity.getAddressId());
		addressModel.setAddressLine1(customerAddressEntity.getAddressLine1());
		addressModel.setAddressLine2(customerAddressEntity.getAddressLine2());
		addressModel.setState(customerAddressEntity.getState());
		addressModel.setCity(customerAddressEntity.getCity());
		addressModel.setCountry(customerAddressEntity.getCountry());
		addressModel.setCreatedBy(customerAddressEntity.getCreatedBy());
		addressModel.setCreatedOn(customerAddressEntity.getCreatedOn());
		addressModel.setPinCode(customerAddressEntity.getZipCode());
		if (customerAddressEntity.getAddressType() != null)
		    addressModel.setAddressTypeId(customerAddressEntity.getAddressType().getAddressTypeId());
		lstCustomerAddressModel.add(addressModel);
	    }

	    //			if (entity.getRoles() != null) {
	    //				for (RoleEntity roleEntity : entity.getRoles()) {
	    //					RoleModel roleModel = new RoleModel();
	    //					roleModel.setRoleCode(roleEntity.getRoleCode());
	    //					roleModel.setRoleDesc(roleEntity.getRoleDesc());
	    //
	    //					List<PermissionModel> permissionModelLst = new ArrayList<>();
	    //
	    //					for (PermissionEntity permissionEntity : roleEntity.getPermissions()) {
	    //						PermissionModel permissionModel = new PermissionModel();
	    //						permissionModel.setPermissionCode(permissionEntity.getPermissionCode());
	    //						permissionModel.setPermissionDesc(permissionEntity.getPermissionDesc());
	    //						permissionModelLst.add(permissionModel);
	    //					}
	    //					roleModel.setPermissions(permissionModelLst);
	    //					roleModelLst.add(roleModel);
	    //				}
	    //			}

	    if (entity.getCustomerBankDetails() != null) {
		for (CustomerBankDetailEntity customerBankDetailEntity : entity.getCustomerBankDetails()) {
		    CustomerBankDetailModel customerBankDetailModel = new CustomerBankDetailModel();
		    customerBankDetailModel.setBankAccountNumber(customerBankDetailEntity.getBankAccountNumber());
		    customerBankDetailModel.setBankAddress(customerBankDetailEntity.getBankAddress());
		    customerBankDetailModel.setBankBranch(customerBankDetailEntity.getBankBranch());
		    customerBankDetailModel.setBankId(customerBankDetailEntity.getBankId());
		    customerBankDetailModel.setBankIfsc(customerBankDetailEntity.getBankIfsc());
		    customerBankDetailModel.setBankName(customerBankDetailEntity.getBankName());
		    customerBankDetailModel.setBankUuid(customerBankDetailEntity.getBankUuid());
		    customerBankDetailModel.setCreatedBy(customerBankDetailEntity.getCreatedBy());
		    customerBankDetailModel.setCreatedOn(customerBankDetailEntity.getCreatedOn());
		    customerBankDetailModel.setCustomer(customerBankDetailEntity.getCustomer().getCustomerId());
		    lstCustomerBankDetailModel.add(customerBankDetailModel);
		}
	    }

	    for (CustomerBillingDetailEntity customerBillingDetailEntity : entity.getCustomerBillingDetails()) {
		CustomerBillingDetailModel customerBillingDetailModel = new CustomerBillingDetailModel();
		customerBillingDetailModel
			.setBillingAccountNumber(customerBillingDetailEntity.getBillingAccountNumber());
		customerBillingDetailModel.setBillingAddress(customerBillingDetailEntity.getBillingAddress());
		customerBillingDetailModel.setBillingId(customerBillingDetailEntity.getBillingId());
		customerBillingDetailModel.setBillingUuid(customerBillingDetailEntity.getBillingUuid());
		customerBillingDetailModel.setCreatedBy(customerBillingDetailEntity.getCreatedBy());
		customerBillingDetailModel.setCreatedOn(customerBillingDetailEntity.getCreatedOn());
		customerBillingDetailModel.setCustomerId(customerBillingDetailEntity.getCustomer().getCustomerId());
		lstCustomerBillingDetailModel.add(customerBillingDetailModel);
	    }

	    if (isUserDataRequired) {

		for (UserEntity userEntity : entity.getUsers()) {
		    customerModel.setUserModel(convertUserEntity(userEntity, 0L));
		}
	    }

	    customerModel.setRoles(roleModelLst);
	    customerModel.setCustomerAddresses(lstCustomerAddressModel);
	    customerModel.setCustomerBankDetails(lstCustomerBankDetailModel);
	    customerModel.setCustomerBillingDetails(lstCustomerBillingDetailModel);
	}

	return customerModel;
    }

    public static UserModel getUserDetail(UserEntity userEntity, StateManagerOperatorsEntity smoEntity) {
	List<RoleModel> roleModels = new ArrayList<>();

	List<UserAddressModel> userAddressModels = new ArrayList<>();
	UserModel userModel = new UserModel();
	userModel.setUserId(userEntity.getUserId());
	userModel.setCreatedBy(userEntity.getCreatedBy());
	userModel.setCreatedOn(userEntity.getCreatedOn());
	if (userEntity.getCustomer() != null) {
	    userModel.setCustomerId(userEntity.getCustomer().getCustomerId());
	    userModel.setCustomerName(userEntity.getCustomer().getCustomerName());
	    userModel.setCustomerType(userEntity.getCustomer().getCustomerType().getCustomerType());
	}
	// userModel.setUser2faEnabled(userEntity.getUser2faEnabled());
	// userModel.setUser2faRequired(userEntity.getUser2faRequired());
	userModel.setUserAccountNumber(userEntity.getUserAccountNumber());
	userModel.setUserAlternateContactNumber(userEntity.getUserAlternateContactNumber());
	userModel.setUserContactNumber(userEntity.getUserContactNumber());
	userModel.setUserEmail(userEntity.getUserEmail());
	userModel.setUserFirstName(userEntity.getUserFirstName());
	userModel.setUserLastName(userEntity.getUserLastName());
	userModel.setUserHierarchy(userEntity.getUserHierarchy());
	if (userEntity.getSupervisor() != null) {
	    userModel.setSupervisorId(userEntity.getSupervisor().getUserId());
	    userModel.setSupervisorName(
		    userEntity.getSupervisor().getUserFirstName() + " " + userEntity.getSupervisor().getUserLastName());
	}
	if (smoEntity != null) {
	    userModel.setStateAdminId(smoEntity.getStateManagerId());
	}

	for (UserAddressEntity userAddressEntity : userEntity.getUserAddresses()) {
	    UserAddressModel userAddressModel = new UserAddressModel();
	    userAddressModel.setAddressId(userAddressEntity.getAddressId());
	    userAddressModel.setAddressLine1(userAddressEntity.getAddressLine1());
	    userAddressModel.setAddressLine2(userAddressEntity.getAddressLine2());
	    if (userAddressEntity.getAddressType() != null)
		userAddressModel.setAddressTypeId(userAddressEntity.getAddressType().getAddressTypeId());
	    userAddressModel.setCity(userAddressEntity.getCity());
	    userAddressModel.setCountry(userAddressEntity.getCountry());
	    userAddressModel.setCreatedBy(userAddressEntity.getCreatedBy());
	    userAddressModel.setCreatedOn(userAddressEntity.getCreatedOn());
	    userAddressModel.setState(userAddressEntity.getState());
	    userAddressModel.setPinCode(userAddressEntity.getZipCode());
	    userAddressModels.add(userAddressModel);
	}

	for (RoleEntity roleEntity : userEntity.getRoles()) {
	    RoleModel roleModel = new RoleModel();
	    roleModel.setRoleCode(roleEntity.getRoleCode());
	    roleModel.setRoleDesc(roleEntity.getRoleDesc());
	    List<PermissionModel> permissionModels = new ArrayList<>();
	    for (PermissionEntity permissionEntity : roleEntity.getPermissions()) {
		PermissionModel permissionModel = new PermissionModel();
		permissionModel.setPermissionCode(permissionEntity.getPermissionCode());
		permissionModel.setPermissionDesc(permissionEntity.getPermissionDesc());
		permissionModels.add(permissionModel);
		roleModel.setPermissions(permissionModels);
	    }
	    roleModels.add(roleModel);
	}
	userModel.setUserAddresses(userAddressModels);
	userModel.setRoleList(roleModels);
	return userModel;
    }

    public static CustomerModel convertCustomerList(CustomerEntity entity, Long count) {

	List<CustomerAddressModel> lstCustomerAddressModel = new ArrayList<>();
	List<RoleModel> roleModelLst = new ArrayList<>();
	List<CustomerBankDetailModel> lstCustomerBankDetailModel = new ArrayList<>();
	List<CustomerBillingDetailModel> lstCustomerBillingDetailModel = new ArrayList<>();
	CustomerModel customerModel = new CustomerModel();
	customerModel.setCreatedOn(entity.getCreatedOn());
	customerModel.setName(entity.getCustomerName());
	customerModel.setCreatedOn(entity.getCreatedOn());
	if (entity.getCustomerType() != null)
	    customerModel.setCustomerTypeId(entity.getCustomerType().getCustomerTypeId());
	customerModel.setContactNumber(entity.getCustomerContactNumber());
	customerModel.setEmail(entity.getCustomerEmail());
	customerModel.setName(entity.getCustomerName());
	customerModel.setGst(entity.getCustomerGst());
	customerModel.setPan(entity.getCustomerPan());
	customerModel.setCustomerTypeId(entity.getCustomerType().getCustomerTypeId());
	customerModel.setCustomerType(entity.getCustomerType().getCustomerType());
	customerModel.setCustomerUuid(entity.getCustomerUuid());
	customerModel.setCustomerId(entity.getCustomerId());
	customerModel.setStatus(entity.getStatus().getStatusId());
	customerModel.setStatusName(entity.getStatus().getStatusDesc());
	customerModel.setPartnerId(entity.getPartnerId());
	if (Constants.CustomerType.PARTNER.equals(entity.getCustomerType().getCustomerType()))
	    customerModel.setPartner(true);

	for (CustomerAddressEntity customerAddressEntity : entity.getCustomerAddresses()) {
	    CustomerAddressModel addressModel = new CustomerAddressModel();
	    addressModel.setAddressId(customerAddressEntity.getAddressId());
	    addressModel.setAddressLine1(customerAddressEntity.getAddressLine1());
	    addressModel.setAddressLine2(customerAddressEntity.getAddressLine2());
	    addressModel.setState(customerAddressEntity.getState());
	    addressModel.setCity(customerAddressEntity.getCity());
	    addressModel.setCountry(customerAddressEntity.getCountry());
	    addressModel.setCreatedBy(customerAddressEntity.getCreatedBy());
	    addressModel.setCreatedOn(customerAddressEntity.getCreatedOn());
	    addressModel.setPinCode(customerAddressEntity.getZipCode());
	    if (customerAddressEntity.getAddressType() != null)
		addressModel.setAddressTypeId(customerAddressEntity.getAddressType().getAddressTypeId());
	    lstCustomerAddressModel.add(addressModel);
	}

	//		if (entity.getRoles() != null) {
	//			for (RoleEntity roleEntity : entity.getRoles()) {
	//				RoleModel roleModel = new RoleModel();
	//				roleModel.setRoleCode(roleEntity.getRoleCode());
	//				roleModel.setRoleDesc(roleEntity.getRoleDesc());
	//
	//				List<PermissionModel> permissionModelLst = new ArrayList<>();
	//
	//				for (PermissionEntity permissionEntity : roleEntity.getPermissions()) {
	//					PermissionModel permissionModel = new PermissionModel();
	//					permissionModel.setPermissionCode(permissionEntity.getPermissionCode());
	//					permissionModel.setPermissionDesc(permissionEntity.getPermissionDesc());
	//					permissionModelLst.add(permissionModel);
	//				}
	//				roleModel.setPermissions(permissionModelLst);
	//				roleModelLst.add(roleModel);
	//			}
	//		}

	for (CustomerBankDetailEntity customerBankDetailEntity : entity.getCustomerBankDetails()) {
	    CustomerBankDetailModel customerBankDetailModel = new CustomerBankDetailModel();
	    customerBankDetailModel.setBankAccountNumber(customerBankDetailEntity.getBankAccountNumber());
	    customerBankDetailModel.setBankAddress(customerBankDetailEntity.getBankAddress());
	    customerBankDetailModel.setBankBranch(customerBankDetailEntity.getBankBranch());
	    customerBankDetailModel.setBankId(customerBankDetailEntity.getBankId());
	    customerBankDetailModel.setBankIfsc(customerBankDetailEntity.getBankIfsc());
	    customerBankDetailModel.setBankName(customerBankDetailEntity.getBankName());
	    customerBankDetailModel.setBankUuid(customerBankDetailEntity.getBankUuid());
	    customerBankDetailModel.setCreatedBy(customerBankDetailEntity.getCreatedBy());
	    customerBankDetailModel.setCreatedOn(customerBankDetailEntity.getCreatedOn());
	    customerBankDetailModel.setCustomer(customerBankDetailEntity.getCustomer().getCustomerId());
	    lstCustomerBankDetailModel.add(customerBankDetailModel);
	}

	for (CustomerBillingDetailEntity customerBillingDetailEntity : entity.getCustomerBillingDetails()) {
	    CustomerBillingDetailModel customerBillingDetailModel = new CustomerBillingDetailModel();
	    customerBillingDetailModel.setBillingAccountNumber(customerBillingDetailEntity.getBillingAccountNumber());
	    customerBillingDetailModel.setBillingAddress(customerBillingDetailEntity.getBillingAddress());
	    customerBillingDetailModel.setBillingId(customerBillingDetailEntity.getBillingId());
	    customerBillingDetailModel.setBillingUuid(customerBillingDetailEntity.getBillingUuid());
	    customerBillingDetailModel.setCreatedBy(customerBillingDetailEntity.getCreatedBy());
	    customerBillingDetailModel.setCreatedOn(customerBillingDetailEntity.getCreatedOn());
	    customerBillingDetailModel.setCustomerId(customerBillingDetailEntity.getCustomer().getCustomerId());
	    lstCustomerBillingDetailModel.add(customerBillingDetailModel);
	}
	customerModel.setTotalCount(count);
	customerModel.setRoles(roleModelLst);
	customerModel.setCustomerAddresses(lstCustomerAddressModel);
	customerModel.setCustomerBankDetails(lstCustomerBankDetailModel);
	customerModel.setCustomerBillingDetails(lstCustomerBillingDetailModel);

	return customerModel;
    }

    public static List<CustomerModel> converListOfCustomers(List<CustomerEntity> customerEntities, Long count) {

	List<CustomerModel> customerModels = new ArrayList<>();
	if (customerEntities != null) {
	    for (CustomerEntity customerEntity : customerEntities) {
		if (!Constants.CustomerType.SERVICE_PROVIDER.equals(customerEntity.getCustomerType().getCustomerType()))
		    customerModels.add(convertCustomerList(customerEntity, count));
	    }
	}
	return customerModels;
    }

    public static List<CountryModel> convertCountries(List<CountryEntity> countryEntities) {
	List<CountryModel> countryModels = new ArrayList<>();
	if (countryEntities != null) {
	    for (CountryEntity countryEntity : countryEntities) {
		CountryModel model = new CountryModel();
		model.setCountryId(countryEntity.getId());
		model.setCountryName(countryEntity.getName());
		countryModels.add(model);

	    }
	}
	return countryModels;
    }

    public static List<StateModel> convertStates(List<StateEntity> stateEntities) {
	List<StateModel> stateModels = new ArrayList<>();
	if (stateEntities != null) {
	    for (StateEntity stateEntity : stateEntities) {
		StateModel stateModel = new StateModel();
		stateModel.setStateId(stateEntity.getId());
		stateModel.setStateName(stateEntity.getName());
		stateModels.add(stateModel);
	    }
	}
	return stateModels;
    }

    public static List<CityModel> convertCities(List<CityEntity> cityEntities) {
	List<CityModel> cityModels = new ArrayList<>();
	if (cityEntities != null) {
	    for (CityEntity cityEntity : cityEntities) {
		CityModel cityModel = new CityModel();
		cityModel.setCityId(cityEntity.getId());
		cityModel.setCityName(cityEntity.getName());
		cityModels.add(cityModel);
	    }
	}
	return cityModels;
    }

    public static StatusModel convertStatus(CustomerEntity customerEntity, StatusModel model) {
	model.setStatusId(customerEntity.getStatus().getStatusId());
	model.setCustomerId(customerEntity.getCustomerId());
	return model;
    }

    public static StatusModel convertStatus(UserEntity userEntity, StatusModel model) {
	model.setStatusId(userEntity.getStatus().getStatusId());
	model.setCustomerId(userEntity.getUserId());
	return model;
    }

    public static List<RoleModel> convertRole(List<RoleEntity> roles) {
	List<RoleModel> roleModels = new ArrayList<>();
	if (roles != null) {
	    for (RoleEntity roleEntity : roles) {
		RoleModel roleModel = new RoleModel();
		roleModel.setRoleCode(roleEntity.getRoleCode());
		roleModel.setRoleDesc(roleEntity.getRoleDesc());
		roleModels.add(roleModel);
	    }
	}
	return roleModels;
    }

    public static List<RoleModel> convertRolePermission(List<RoleEntity> roleLst) {
	List<RoleModel> roleModelLst = new ArrayList<>();
	if (roleLst != null) {

	    for (RoleEntity roleEntity : roleLst) {
		List<PermissionModel> permissionModelLst = new ArrayList<>();
		RoleModel roleModel = new RoleModel();
		roleModel.setRoleCode(roleEntity.getRoleCode());
		roleModel.setRoleDesc(roleEntity.getRoleDesc());
		for (PermissionEntity permissionEntity : roleEntity.getPermissions()) {
		    PermissionModel permissionModel = new PermissionModel();
		    permissionModel.setPermissionCode(permissionEntity.getPermissionCode());
		    permissionModel.setPermissionDesc(permissionEntity.getPermissionDesc());
		    permissionModelLst.add(permissionModel);
		}
		roleModel.setPermissions(permissionModelLst);
		roleModelLst.add(roleModel);

	    }
	}
	return roleModelLst;
    }

    public static CustomerModel convertCustomerEntity(CustomerEntity entity, boolean isUserDataRequired,
	    Long[] commodityCategoryIds) {
	if (entity == null)
	    return null;
	List<CustomerAddressModel> lstCustomerAddressModel = new ArrayList<>();
	List<RoleModel> roleModelLst = new ArrayList<>();
	List<CustomerBankDetailModel> lstCustomerBankDetailModel = new ArrayList<>();
	List<CustomerBillingDetailModel> lstCustomerBillingDetailModel = new ArrayList<>();
	CustomerModel customerModel = null;
	;
	if (entity != null) {
	    customerModel = new CustomerModel();
	    customerModel.setCreatedOn(entity.getCreatedOn());
	    customerModel.setName(entity.getCustomerName());
	    customerModel.setCreatedOn(entity.getCreatedOn());
	    if (entity.getCustomerType() != null)
		customerModel.setCustomerTypeId(entity.getCustomerType().getCustomerTypeId());
	    customerModel.setContactNumber(entity.getCustomerContactNumber());
	    customerModel.setEmail(entity.getCustomerEmail());
	    customerModel.setName(entity.getCustomerName());
	    customerModel.setGst(entity.getCustomerGst());
	    customerModel.setPan(entity.getCustomerPan());
	    customerModel.setCustomerTypeId(entity.getCustomerType().getCustomerTypeId());
	    customerModel.setCustomerType(entity.getCustomerType().getCustomerType());
	    customerModel.setCustomerUuid(entity.getCustomerUuid());
	    customerModel.setCustomerId(entity.getCustomerId());
	    customerModel.setCommodityCategoryId(commodityCategoryIds);
	    if (entity.getPartnerId() != null)
		customerModel.setPartnerId(entity.getPartnerId());

	    for (CustomerAddressEntity customerAddressEntity : entity.getCustomerAddresses()) {
		CustomerAddressModel addressModel = new CustomerAddressModel();
		addressModel.setAddressId(customerAddressEntity.getAddressId());
		addressModel.setAddressLine1(customerAddressEntity.getAddressLine1());
		addressModel.setAddressLine2(customerAddressEntity.getAddressLine2());
		addressModel.setState(customerAddressEntity.getState());
		addressModel.setCity(customerAddressEntity.getCity());
		addressModel.setCountry(customerAddressEntity.getCountry());
		addressModel.setCreatedBy(customerAddressEntity.getCreatedBy());
		addressModel.setCreatedOn(customerAddressEntity.getCreatedOn());
		addressModel.setPinCode(customerAddressEntity.getZipCode());
		if (customerAddressEntity.getAddressType() != null)
		    addressModel.setAddressTypeId(customerAddressEntity.getAddressType().getAddressTypeId());
		lstCustomerAddressModel.add(addressModel);
	    }

	    //			if (entity.getRoles() != null) {
	    //				for (RoleEntity roleEntity : entity.getRoles()) {
	    //					RoleModel roleModel = new RoleModel();
	    //					roleModel.setRoleCode(roleEntity.getRoleCode());
	    //					roleModel.setRoleDesc(roleEntity.getRoleDesc());
	    //
	    //					List<PermissionModel> permissionModelLst = new ArrayList<>();
	    //
	    //					for (PermissionEntity permissionEntity : roleEntity.getPermissions()) {
	    //						PermissionModel permissionModel = new PermissionModel();
	    //						permissionModel.setPermissionCode(permissionEntity.getPermissionCode());
	    //						permissionModel.setPermissionDesc(permissionEntity.getPermissionDesc());
	    //						permissionModelLst.add(permissionModel);
	    //					}
	    //					roleModel.setPermissions(permissionModelLst);
	    //					roleModelLst.add(roleModel);
	    //				}
	    //			}

	    for (CustomerBankDetailEntity customerBankDetailEntity : entity.getCustomerBankDetails()) {
		CustomerBankDetailModel customerBankDetailModel = new CustomerBankDetailModel();
		customerBankDetailModel.setBankAccountNumber(customerBankDetailEntity.getBankAccountNumber());
		customerBankDetailModel.setBankAddress(customerBankDetailEntity.getBankAddress());
		customerBankDetailModel.setBankBranch(customerBankDetailEntity.getBankBranch());
		customerBankDetailModel.setBankId(customerBankDetailEntity.getBankId());
		customerBankDetailModel.setBankIfsc(customerBankDetailEntity.getBankIfsc());
		customerBankDetailModel.setBankName(customerBankDetailEntity.getBankName());
		customerBankDetailModel.setBankUuid(customerBankDetailEntity.getBankUuid());
		customerBankDetailModel.setCreatedBy(customerBankDetailEntity.getCreatedBy());
		customerBankDetailModel.setCreatedOn(customerBankDetailEntity.getCreatedOn());
		customerBankDetailModel.setCustomer(customerBankDetailEntity.getCustomer().getCustomerId());
		lstCustomerBankDetailModel.add(customerBankDetailModel);
	    }

	    for (CustomerBillingDetailEntity customerBillingDetailEntity : entity.getCustomerBillingDetails()) {
		CustomerBillingDetailModel customerBillingDetailModel = new CustomerBillingDetailModel();
		customerBillingDetailModel
			.setBillingAccountNumber(customerBillingDetailEntity.getBillingAccountNumber());
		customerBillingDetailModel.setBillingAddress(customerBillingDetailEntity.getBillingAddress());
		customerBillingDetailModel.setBillingId(customerBillingDetailEntity.getBillingId());
		customerBillingDetailModel.setBillingUuid(customerBillingDetailEntity.getBillingUuid());
		customerBillingDetailModel.setCreatedBy(customerBillingDetailEntity.getCreatedBy());
		customerBillingDetailModel.setCreatedOn(customerBillingDetailEntity.getCreatedOn());
		customerBillingDetailModel.setCustomerId(customerBillingDetailEntity.getCustomer().getCustomerId());
		lstCustomerBillingDetailModel.add(customerBillingDetailModel);
	    }

	    if (isUserDataRequired) {

		for (UserEntity userEntity : entity.getUsers()) {
		    customerModel.setUserModel(convertUserEntity(userEntity, 0L));
		}
	    }

	    customerModel.setRoles(roleModelLst);
	    customerModel.setCustomerAddresses(lstCustomerAddressModel);
	    customerModel.setCustomerBankDetails(lstCustomerBankDetailModel);
	    customerModel.setCustomerBillingDetails(lstCustomerBillingDetailModel);
	}

	return customerModel;
    }

    public static LoginResponseModel convertLoginResponse(AuthenticationModel response) {
	LoginResponseModel loginResponseModel = new LoginResponseModel();
	loginResponseModel.setAccessToken(response.getAccessToken());
	if (response.getPermissions() != null && response.getPermissions().size() > 0)
	    loginResponseModel.setPermissions(response.getPermissions().stream().collect(Collectors.toSet()));
	loginResponseModel.setUser(response.getUser());
	return loginResponseModel;
    }

    public static List<CommodityCategoryModel> convertCommodityCategory(List<DcmCommodityCategory> categoriesList) {

	List<CommodityCategoryModel> categoryModels = new ArrayList<CommodityCategoryModel>();
	if (categoriesList != null) {
	    for (DcmCommodityCategory dcmCommodityCategory : categoriesList) {
		CommodityCategoryModel commodityCategoryModel = new CommodityCategoryModel();
		commodityCategoryModel.setCommodityCategoryId(dcmCommodityCategory.getId());
		commodityCategoryModel.setCommodityCategoryName(dcmCommodityCategory.getCommodityCategoryName());
		categoryModels.add(commodityCategoryModel);
	    }
	}
	return categoryModels;
    }

    public static List<CommodityModel> convertCommodity(List<DcmCommodity> commodityList) {

	List<CommodityModel> commodityModels = new ArrayList<CommodityModel>();
	if (commodityList != null) {
	    for (DcmCommodity commodityEntity : commodityList) {
		CommodityModel commodityModel = setCommodityDetail(commodityEntity);
		commodityModels.add(commodityModel);
	    }
	}
	return commodityModels;
    }

    private static CommodityModel setCommodityDetail(DcmCommodity commodityEntity) {
	CommodityModel commodityModel = new CommodityModel();
	commodityModel.setCommodityId(commodityEntity.getId());
	commodityModel.setCommodityName(commodityEntity.getCommodityName());
	commodityModel.setCommodityCode(commodityEntity.getCommodityCode());
	Optional.ofNullable(commodityEntity.getCount()).ifPresent(commodityModel::setCount);
	commodityModel.setCommodityCategoryId(commodityEntity.getDcmCommodityCategory().getId());
	commodityModel.setCommodityCategoryName(commodityEntity.getDcmCommodityCategory().getCommodityCategoryName());
	return commodityModel;
    }

    public static List<CommodityModel> convertCommodityByCategoryId(List<DcmCommodity> commoditiesLst) {
	List<CommodityModel> commodityModels = new ArrayList<CommodityModel>();
	if (commoditiesLst != null) {
	    for (DcmCommodity dcmCommodity : commoditiesLst) {
		CommodityModel commodityModel = setCommodityDetail(dcmCommodity);
		commodityModels.add(commodityModel);
	    }
	}
	return commodityModels;
    }

    public static MetaDataModel convertModelForMetaData(ScanEntity scanEntity, CustomerRepository customerRepo)
	    throws IMException, JsonParseException, JsonMappingException, IOException {
	if (scanEntity != null) {
	    MetaDataModel scanModel = new MetaDataModel();
	    Optional.ofNullable(scanEntity.getBatchId()).ifPresent(scanModel::setBatchId);
	    Optional.ofNullable(scanEntity.getLotId()).ifPresent(scanModel::setLotId);
	    Optional.ofNullable(scanEntity.getSampleId()).ifPresent(scanModel::setSampleId);
	    Optional.ofNullable(scanEntity.getQuantity())
		    .ifPresent(quantity -> scanModel.setQuantity(String.valueOf(quantity)));
	    WeightConverterModel weightDetails = new WeightConverterModel();
	    weightDetails = Utility.fetchWeightConverter(scanEntity.getWeight(), scanEntity.getQuantityUnit());
	    System.out.println(
		    "********* Weight : " + weightDetails.getWeight() + " Weight unit : " + weightDetails.getUnit());
	    scanModel.setWeight(weightDetails.getWeight() + " " + weightDetails.getUnit());
	    //	    Optional.ofNullable(scanEntity.getCustomerId()).ifPresent(customerId -> {
	    //		final String customerName = customerRepo.findCustomerNameById(customerId);
	    //		scanModel.setCustomerName(customerName);
	    //	    });
	    Optional.ofNullable(scanEntity.getTruckNumber()).ifPresent(scanModel::setTruckNumber);
	    Optional.ofNullable(scanEntity.getSlipNo()).ifPresent(scanModel::setSlipNumber);
	    Optional.ofNullable(scanEntity.getGatePass()).ifPresent(scanModel::setGatePass);
	    Optional.ofNullable(scanEntity.getCadNo()).ifPresent(scanModel::setCadNumber);
	    Optional.ofNullable(scanEntity.getBag()).ifPresent(scanModel::setBag);
	    Optional.ofNullable(scanEntity.getTruckGrossWeight()).ifPresent(scanModel::setTruckGrossWeight);
	    Optional.ofNullable(scanEntity.getTruckTareWeight()).ifPresent(scanModel::setTruckTareWeight);
	    Optional.ofNullable(scanEntity.getStackNumber()).ifPresent(scanModel::setStackNumber);
	    Optional.ofNullable(scanEntity.getChamberNumber()).ifPresent(scanModel::setChamberNumber);
	    Optional.ofNullable(scanEntity.getAvgWeightPerBag()).ifPresent(scanModel::setAvgWeightPerBag);
	    Optional.ofNullable(scanEntity.getPackingSize()).ifPresent(scanModel::setPackingSize);
	    Optional.ofNullable(scanEntity.getWeighbridgeName()).ifPresent(scanModel::setWeightBridgeName);
	    Optional.ofNullable(scanEntity.getGRNNumber()).ifPresent(scanModel::setGrnNumber);
	    return scanModel;
	} else {
	    throw new IMException(Constants.SCAN_ID_NOT_EXIST_CODE, Constants.SCAN_ID_NOT_EXIST);
	}
    }

    public static ScanModel convertScmScan(ScanEntity scanEntity, Map<Long, List<String>> commoditiesAnalyticsMap, AnalyticsVariations analyticsVariations)
	    throws IMException, JsonParseException, JsonMappingException, IOException {
	if (scanEntity != null) {

	    ObjectMapper mapper = new ObjectMapper();
	    Map<String, BigDecimal> map = null;
	    if (scanEntity.getDensity() != null) {
		map = mapper.readValue(scanEntity.getDensity(), Map.class);
	    }

	    ScanModel scanModel = new ScanModel();
	    Optional.ofNullable(scanEntity.getDeviceSerialNo()).ifPresent(scanModel::setDeviceSerialNo);
	    Optional.ofNullable(scanEntity.getLotId()).ifPresent(scanModel::setLotId);
	    Optional.ofNullable(scanEntity.getCommodityId()).ifPresent(scanModel::setCommodityId);
	    Optional.ofNullable(scanEntity.getScanByUserCode()).ifPresent(scanModel::setScanByUserCode);
	    Optional.ofNullable(scanEntity.getLocation()).ifPresent(scanModel::setLocation);
	    Optional.ofNullable(scanEntity.getVendorCode()).ifPresent(scanModel::setVendorCode);
	    Optional.ofNullable(scanEntity.getQuantity()).ifPresent(scanModel::setQuantity);
	    Optional.ofNullable(scanEntity.getFarmerCode()).ifPresent(scanModel::setFarmerCode);
	    Optional.ofNullable(scanEntity.getBatchId()).ifPresent(scanModel::setBatchId);
	    Optional.ofNullable(scanEntity.getSampleId()).ifPresent(scanModel::setSampleId);
	    Optional.ofNullable(scanEntity.getVarietyId()).ifPresent(scanModel::setVarietyId);
	    Optional.ofNullable(scanEntity.getId()).ifPresent(scanModel::setId);
	    Optional.ofNullable(scanEntity.getDeviceType()).ifPresent(scanModel::setDeviceType);
	    scanModel.setMessage(setScanMessage(scanEntity));
	    scanModel.setApproval(scanEntity.getApproval());
	    if (scanEntity.getApproval() == 0) {
		scanModel.setApprovalDesc("Pending");
	    } else if (scanEntity.getApproval() == 1) {
		scanModel.setApprovalDesc("Approved");
	    }

	    else if (scanEntity.getApproval() == 2) {
		scanModel.setApprovalDesc("Rejected");
	    } else {
		scanModel.setApprovalDesc("Undefined");
	    }
	    if (scanEntity.getStatusChangedOn() != null)
		Optional.ofNullable(Utility.formatLocalMonthDateTimeToString(scanEntity.getStatusChangedOn()))
			.ifPresent(scanModel::setMsgTimeStamp);
	    //
	    Optional.ofNullable(scanEntity.getDeviceSerialNo()).ifPresent(scanModel::setDeviceSerialNo);
	    Optional.ofNullable(scanEntity.getInstallatonCenterId()).ifPresent(scanModel::setInstCenterTypeId);
	    Optional.ofNullable(scanEntity.getUserId()).ifPresent(scanModel::setUserId);
	    Optional.ofNullable(scanEntity.getCommodityName()).ifPresent(scanModel::setCommodityName);
	    WeightConverterModel weightDetails = new WeightConverterModel();
	    weightDetails = Utility.fetchWeightConverter(scanEntity.getWeight(), scanEntity.getQuantityUnit());
	    System.out.println(
		    "********* Weight : " + weightDetails.getWeight() + " Weight unit : " + weightDetails.getUnit());
	    scanModel.setWeight(weightDetails.getWeight());
	    scanModel.setQuantityUnit(weightDetails.getUnit());

	    //            Optional.ofNullable(scanEntity.getQuantityUnit()).ifPresent(scanModel::setQuantityUnit);
	    //            Optional.ofNullable(scanEntity.getWeight()).ifPresent(scanModel::setWeight);

	    Optional.ofNullable(scanEntity.getAreaCovered()).ifPresent(scanModel::setAreaCovered);
	    
	    List<Analytics> quality = new ArrayList();
	    Analytics grain_count = new Analytics();
//	    if (scanEntity.getCommodityId() == 4L) {
//		for (ScanResultEntity result : scanEntity.getResults()) {
//		    Analytics ana = new Analytics();
//		    ana.setAmountUnit("");
//		    ana.setAnalysisName(result.getAnalysisName());
//		    ana.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//		    ana.setAnalysisId(String.valueOf(result.getId()));
//		    ana.setTotalAmount(
//			    String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//		    quality.add(ana);
//		}
//	    } else {
		Boolean moisturePresent = false;
		BigDecimal moisture = null;
		
		List<String> standardList = commoditiesAnalyticsMap.get(scanEntity.getCommodityId());
		
		for (ScanResultEntity result : scanEntity.getResults()) {
			
			/* Standardization of Analytics Parameters */
		
				if (result.getAnalysisName() != null) {
					Analytics analyticsNew= new Analytics();
					
					if (
							analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.ADMIXTURE.getAnalytics()).contains(result.getAnalysisName())) {
						System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
						analyticsNew.setAmountUnit("%");
						analyticsNew.setAnalysisName(Constants.ANALYTICS.ADMIXTURE.getAbbr());
						analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
						analyticsNew.setAnalysisId(String.valueOf(result.getId()));
						analyticsNew.setTotalAmount(
							String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

						if (map != null && map.containsKey(result.getAnalysisName())) {
							analyticsNew.setByDensityResult(Utility
							    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
						}

					} else if (
							analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.DAMAGED.getAnalytics()).contains(result.getAnalysisName())) {
						
						System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
						analyticsNew.setAmountUnit("%");
						analyticsNew.setAnalysisName(Constants.ANALYTICS.DAMAGED.getAbbr());
						analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
						analyticsNew.setAnalysisId(String.valueOf(result.getId()));
						analyticsNew.setTotalAmount(
							String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

						if (map != null && map.containsKey(result.getAnalysisName())) {
							analyticsNew.setByDensityResult(Utility
							    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
						}

					}

					else if (
							analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.FOREIGNMATTER.getAnalytics()).contains(result.getAnalysisName())) {
						
						System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
						analyticsNew.setAmountUnit("%");
						analyticsNew.setAnalysisName(Constants.ANALYTICS.FOREIGNMATTER.getAbbr());
						analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
						analyticsNew.setAnalysisId(String.valueOf(result.getId()));
						analyticsNew.setTotalAmount(
							String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

						if (map != null && map.containsKey(result.getAnalysisName())) {
							analyticsNew.setByDensityResult(Utility
							    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
						}

					} else if (analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.IMMATURE.getAnalytics()).contains(result.getAnalysisName())) {
						
						
						System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
						analyticsNew.setAmountUnit("%");
						analyticsNew.setAnalysisName(Constants.ANALYTICS.IMMATURE.getAbbr());
						analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
						analyticsNew.setAnalysisId(String.valueOf(result.getId()));
						analyticsNew.setTotalAmount(
							String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

						if (map != null && map.containsKey(result.getAnalysisName())) {
							analyticsNew.setByDensityResult(Utility
							    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
						}

					} else if (!moisturePresent &&
							analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.MOISTURECONTENT.getAnalytics()).contains(result.getAnalysisName())) {
						
						 moisture = result.getResult();
						    moisturePresent = true;
						
							if (moisture != null) {
								analyticsNew.setAmountUnit("%");
								analyticsNew.setAnalysisName(Constants.ANALYTICS.MOISTURECONTENT.getAbbr());
								analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(moisture)));
								analyticsNew.setTotalAmount(String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(moisture))));
							}
						    
//						System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
//						analyticsNew.setAmountUnit("%");
//						analyticsNew.setAnalysisName(customAnalyticsModel.getMoisturecontent());
//						analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//						analyticsNew.setAnalysisId(String.valueOf(result.getId()));
//						analyticsNew.setTotalAmount(
//							String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));


					} else if (
							analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.PODSOFOTHERVAR.getAnalytics()).contains(result.getAnalysisName())) {
						
						System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
						analyticsNew.setAmountUnit("%");
						analyticsNew.setAnalysisName(Constants.ANALYTICS.PODSOFOTHERVAR.getAbbr());
						analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
						analyticsNew.setAnalysisId(String.valueOf(result.getId()));
						analyticsNew.setTotalAmount(
							String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

						if (map != null && map.containsKey(result.getAnalysisName())) {
							analyticsNew.setByDensityResult(Utility
							    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
						}

					} else if (
							analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SHELLING.getAnalytics()).contains(result.getAnalysisName())) {
						
						System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
						analyticsNew.setAmountUnit("%");
						analyticsNew.setAnalysisName(Constants.ANALYTICS.SHELLING.getAbbr());
						analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
						analyticsNew.setAnalysisId(String.valueOf(result.getId()));
						analyticsNew.setTotalAmount(
							String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

						if (map != null && map.containsKey(result.getAnalysisName())) {
							analyticsNew.setByDensityResult(Utility
							    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
						}

					} else if (
							analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SHRIVELLEDANDIMMATURE.getAnalytics()).contains(result.getAnalysisName())) {
						
						System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
						analyticsNew.setAmountUnit("%");
						analyticsNew.setAnalysisName(Constants.ANALYTICS.SHRIVELLEDANDIMMATURE.getAbbr());
						analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
						analyticsNew.setAnalysisId(String.valueOf(result.getId()));
						analyticsNew.setTotalAmount(
							String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

						if (map != null && map.containsKey(result.getAnalysisName())) {
							analyticsNew.setByDensityResult(Utility
							    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
						}

					} else if (
							analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SLIGHTLYDAMAGED.getAnalytics()).contains(result.getAnalysisName())) {
						
						System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
						analyticsNew.setAmountUnit("%");
						analyticsNew.setAnalysisName(Constants.ANALYTICS.SLIGHTLYDAMAGED.getAbbr());
						analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
						analyticsNew.setAnalysisId(String.valueOf(result.getId()));
						analyticsNew.setTotalAmount(
							String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

						if (map != null && map.containsKey(result.getAnalysisName())) {
							analyticsNew.setByDensityResult(Utility
							    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
						}

					} else if (
							analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.WEEVILLED.getAnalytics()).contains(result.getAnalysisName())) {
						
						System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
						analyticsNew.setAmountUnit("%");
						analyticsNew.setAnalysisName(Constants.ANALYTICS.WEEVILLED.getAbbr());
						analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
						analyticsNew.setAnalysisId(String.valueOf(result.getId()));
						analyticsNew.setTotalAmount(
							String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

						if (map != null && map.containsKey(result.getAnalysisName())) {
							analyticsNew.setByDensityResult(Utility
							    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
						}

					}
					else if (
							analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.DAMAGEDANDWEEVILLED.getAnalytics()).contains(result.getAnalysisName())) {
						
						System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
						analyticsNew.setAmountUnit("%");
						analyticsNew.setAnalysisName(Constants.ANALYTICS.DAMAGEDANDWEEVILLED.getAbbr());
						analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
						analyticsNew.setAnalysisId(String.valueOf(result.getId()));
						analyticsNew.setTotalAmount(
							String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

						if (map != null && map.containsKey(result.getAnalysisName())) {
							analyticsNew.setByDensityResult(Utility
							    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
						}

					}
					else if (
							analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.OTHERFOODGRAINS.getAnalytics()).contains(result.getAnalysisName())) {
						
						System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
						analyticsNew.setAmountUnit("%");
						analyticsNew.setAnalysisName(Constants.ANALYTICS.OTHERFOODGRAINS.getAbbr());
						analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
						analyticsNew.setAnalysisId(String.valueOf(result.getId()));
						analyticsNew.setTotalAmount(
							String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

						if (map != null && map.containsKey(result.getAnalysisName())) {
							analyticsNew.setByDensityResult(Utility
							    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
						}

					}
					else if (
							analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SMALLATROPHIEDSEEDS.getAnalytics()).contains(result.getAnalysisName())) {
						
						System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
						analyticsNew.setAmountUnit("%");
						analyticsNew.setAnalysisName(Constants.ANALYTICS.SMALLATROPHIEDSEEDS.getAbbr());
						analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
						analyticsNew.setAnalysisId(String.valueOf(result.getId()));
						analyticsNew.setTotalAmount(
							String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

						if (map != null && map.containsKey(result.getAnalysisName())) {
							analyticsNew.setByDensityResult(Utility
							    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
						}

					}
					else if (
						analyticsVariations.getAnalytics().get(Constants.STANDARDIZEDANALYTICS.SPLITCRACKED.getAnalytics()).contains(result.getAnalysisName())) {
					
					System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
					analyticsNew.setAmountUnit("%");
					analyticsNew.setAnalysisName(Constants.ANALYTICS.SPLITCRACKED.getAbbr());
					analyticsNew.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
					analyticsNew.setAnalysisId(String.valueOf(result.getId()));
					analyticsNew.setTotalAmount(
						String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));

					if (map != null && map.containsKey(result.getAnalysisName())) {
						analyticsNew.setByDensityResult(Utility
						    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
					}

				}
					
					if(analyticsNew !=null && analyticsNew.getAmountUnit() !=null && !analyticsNew.getAmountUnit().equalsIgnoreCase(""))
                   quality.add(analyticsNew);
				}
			    logger.info(" Analytics : " + quality);
			    Collections.reverse(quality);
			    logger.info(" Analytics reverse list  :  " + quality);
			    scanModel.setQuality(removeDuplicates(quality));
			
			/* End ! */

//		    if (result.getAnalysisName().equals("grain_count")) {
//			Analytics analytics = new Analytics();
//			analytics.setAmountUnit("");
//			analytics.setAnalysisName(result.getAnalysisName());
//			analytics.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//			analytics.setAnalysisId(String.valueOf(result.getId()));
//			analytics.setTotalAmount(
//				String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//			grain_count = analytics;
//			//			quality.add(0, analytics);
//		    } else if (!result.getAnalysisName().equals("grain_count")
//			    && !result.getAnalysisName().equals("moisturecontent")
//			    && !result.getAnalysisName().equals("moisture")) {
//			Analytics ana = new Analytics();
//			System.out.println(" result.getAnalysisName() " + result.getAnalysisName());
//			ana.setAmountUnit("%");
//			ana.setAnalysisName(result.getAnalysisName());
//			ana.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult())));
//			ana.setAnalysisId(String.valueOf(result.getId()));
//			ana.setTotalAmount(
//				String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(result.getResult()))));
//
//			if (map != null && map.containsKey(result.getAnalysisName())) {
//			    ana.setByDensityResult(Utility
//				    .formatDecimal(Utility.getBigDecimalValue(map.get(result.getAnalysisName()))));
//			}
//			quality.add(ana);
//		    } 
//		    else if (result.getAnalysisName().equals("moisture")
//			    || result.getAnalysisName().equals("moisturecontent")) {
//			if (result.getAnalysisName().equals("moisture")) {
//			    moisture = result.getResult();
//			    moisturePresent = true;
//			} else if (result.getAnalysisName().equals("moisturecontent")
//				&& moisturePresent.booleanValue() == false) {
//			    moisture = result.getResult();
//			}
//
//		    }

		}
//		quality.add(setMoisture(moisture));
//	    }
	
//	    if (grain_count != null && grain_count.getAnalysisName() != null && !grain_count.getAnalysisName().isEmpty()
//		    && grain_count.getAnalysisName().equalsIgnoreCase("grain_count")) {
//		quality.add(0, grain_count);
//	    }

	    //	    scanModel.setQuality(quality.stream().distinct().collect(Collectors.toList()));
	    //	    scanModel.setQuality(quality);

	    // scanModel.setFarmer(farmerDetailModel);
	    return scanModel;
	} else {
	    throw new IMException(Constants.SCAN_ID_NOT_EXIST_CODE, Constants.SCAN_ID_NOT_EXIST);
	}

    }

    /**
     * Set the Scan Message
     * 
     * @param scanEntity
     *
     */
    private static String setScanMessage(ScanEntity scanEntity) {
	String message = "Status last";
	LocalDateTime statusChangedOn = scanEntity.getStatusChangedOn();
	if (statusChangedOn == null) {
	    message = message + " created on:";
	    final Long creationTimeStamp = scanEntity.getCreatedOn();
	    statusChangedOn = Utility.millsToLocalDateTime(creationTimeStamp);
	} else {
	    message = message + " updated on:";
	}
	final String statusUpdateTime = Utility.formatDateToString(statusChangedOn);
	if (!StringUtils.isEmpty(scanEntity.getMessage())) {
	    message = message + statusUpdateTime + " with message :" + scanEntity.getMessage();
	} else {
	    message = message + statusUpdateTime;
	}
	return message;
    }

    public static LocationModel convertLocationTypeDetial(ScanLocation loc, Long count) {

	LocationModel locationModel = new LocationModel();

	//		locationModel.setCreatedBy(loc.getCreatedBy());
	locationModel.setCreatedOn(loc.getCreatedOn());
	//		locationModel.setCustomerId(loc.getCustomerId());
	locationModel.setInstallationCenterId(loc.getId());
	locationModel.setInstCenterName(loc.getLocationName());
	//		locationModel.setModifiedBy(loc.getModifiedBy());
	//		locationModel.setModifiedOn(loc.getModifiedOn());
	//		locationModel.setUserId(loc.getUserId());
	locationModel.setTotalCount(count);
	locationModel.setCode(loc.getCode());

	if (loc.getState() != null && loc.getState().getId() != null) {
	    locationModel.setStateId(loc.getState().getId());
	    locationModel.setStateName(loc.getState().getName());
	}
	locationModel.setWarehouseName(loc.getWarehouseName());
	//		if (loc.getDcmSite() != null) {
	//			locationModel.setSiteId(loc.getDcmSite().getId());
	//			locationModel.setSiteName(loc.getDcmSite().getName());
	//		}
	//
	//		if (loc.getDcmRegion() != null) {
	//			locationModel.setRegionId(loc.getDcmRegion().getId());
	//			locationModel.setRegionName(loc.getDcmRegion().getName());
	//		}

	//		if (loc.getNotes() != null)
	//			locationModel.setNotes(loc.getNotes());

	return locationModel;
    }

    public static DeviceModel fillDeviceModel(DcmDevice dcmDevice, Boolean isSubscribed, UserEntity user,
	    List<DcmCommodity> deviceCommodities, UserEntity stateManager) {

	DeviceModel deviceModel = new DeviceModel();
	if (dcmDevice != null) {
	    Optional.ofNullable(dcmDevice.getId()).ifPresent(deviceModel::setDeviceId);
	    Optional.ofNullable(dcmDevice.getSerialNumber()).ifPresent(deviceModel::setSerialNumber);

	    if (dcmDevice.getDcmDeviceType() != null && dcmDevice.getDcmDeviceType().getId() != null) {
		Optional.ofNullable(dcmDevice.getDcmDeviceType().getId()).ifPresent(deviceModel::setDeviceTypeId);
		Optional.ofNullable(dcmDevice.getDcmDeviceType().getDeviceTypeDesc())
			.ifPresent(deviceModel::setDeviceType);
	    }
	    if (dcmDevice.getDcmStatus() != null && dcmDevice.getDcmStatus().getStatusId() != null) {
		Optional.ofNullable(dcmDevice.getDcmStatus().getStatusId()).ifPresent(deviceModel::setStateId);
		Optional.ofNullable(dcmDevice.getDcmStatus().getStatusDesc()).ifPresent(deviceModel::setStatusDesc);
	    }
	    if (dcmDevice.getScanLocation() != null && dcmDevice.getScanLocation().getId() != null) {
		Optional.ofNullable(dcmDevice.getScanLocation().getId())
			.ifPresent(deviceModel::setCommercialLocationId);
	    }
	    if (user != null && user.getCustomer() != null && user.getCustomer().getCustomerId() != null) {
		Optional.ofNullable(user.getCustomer().getCustomerId()).ifPresent(deviceModel::setCustomerId);
		Optional.ofNullable(user.getCustomer().getCustomerName()).ifPresent(deviceModel::setCustomerName);
		Optional.ofNullable(user.getCustomer().getCustomerEmail()).ifPresent(deviceModel::setCustomerEmail);
	    }
	    if (user != null && user.getUserId() != null) {
		Optional.ofNullable(user.getUserId()).ifPresent(deviceModel::setUserId);
		Optional.ofNullable(user.getUserFirstName()).ifPresent(deviceModel::setUserName);
		Optional.ofNullable(user.getUserEmail()).ifPresent(deviceModel::setUserEmail);
	    }
	    if (stateManager != null && stateManager.getUserId() != null) {
		Optional.ofNullable(stateManager.getUserId()).ifPresent(deviceModel::setStateId);
		Optional.ofNullable(stateManager.getUserFirstName()).ifPresent(deviceModel::setStateManagerName);
		Optional.ofNullable(stateManager.getUserEmail()).ifPresent(deviceModel::setStateManagerEmail);
	    }

	    if (deviceCommodities != null && !deviceCommodities.isEmpty() && deviceCommodities.size() > 0) {
		Map<DcmCommodityCategory, List<CommodityModel>> map = new HashMap<DcmCommodityCategory, List<CommodityModel>>();

		for (DcmCommodity dcmCommodity : deviceCommodities) {
		    if (map.containsKey(dcmCommodity.getDcmCommodityCategory())) {
			map.get(dcmCommodity.getDcmCommodityCategory())
				.add(new CommodityModel(dcmCommodity.getId(), dcmCommodity.getCommodityName()));
		    } else {
			List<CommodityModel> commList = new ArrayList<>();
			commList.add(new CommodityModel(dcmCommodity.getId(), dcmCommodity.getCommodityName()));
			map.put(dcmCommodity.getDcmCommodityCategory(), commList);
		    }
		}

		List<DeviceCommodityModel> dcmList = new ArrayList<DeviceCommodityModel>();
		for (Map.Entry<DcmCommodityCategory, List<CommodityModel>> entry : map.entrySet()) {
		    DeviceCommodityModel dcm = new DeviceCommodityModel();
		    dcm.setCategoryId(entry.getKey().getId());
		    dcm.setCategoryName(entry.getKey().getCommodityCategoryName());
		    dcm.setCommodities(entry.getValue());
		    dcmList.add(dcm);
		}
		deviceModel.setDeviceCommodities(dcmList);
	    }

	    //			if (dcmDevice.getDeviceGroup() != null && dcmDevice.getDeviceGroup().getId() != null) {
	    //				Optional.ofNullable(dcmDevice.getDeviceGroup().getId()).ifPresent(deviceModel::setDeviceGroupId);
	    //				Optional.ofNullable(dcmDevice.getDeviceGroup().getDeviceGroupDesc())
	    //						.ifPresent(deviceModel::setDeviceGroupDesc);
	    //			}
	    //			if (dcmDevice.getDeviceSubType() != null && dcmDevice.getDeviceSubType().getId() != null) {
	    //				Optional.ofNullable(dcmDevice.getDeviceSubType().getId()).ifPresent(deviceModel::setDeviceSubTypeId);
	    //				Optional.ofNullable(dcmDevice.getDeviceSubType().getDeviceSubTypeDesc())
	    //						.ifPresent(deviceModel::setDeviceSubTypeDesc);
	    //			}
	    //			Optional.ofNullable(dcmDevice.getDeviceSubType().getDeviceSubTypeDesc()).ifPresent(deviceModel::setDeviceSubTypeDesc);
	    Optional.ofNullable(dcmDevice.getHwRevision()).ifPresent(deviceModel::setHwRevision);
	    Optional.ofNullable(dcmDevice.getFwRevision()).ifPresent(deviceModel::setFwRevision);
	    Optional.ofNullable(dcmDevice.getStartOfLife()).ifPresent(deviceModel::setStartOfLife);
	    Optional.ofNullable(dcmDevice.getEndOfLife()).ifPresent(deviceModel::setEndOfLife);
	    Optional.ofNullable(dcmDevice.getStartOfService()).ifPresent(deviceModel::setStartOfService);
	    Optional.ofNullable(dcmDevice.getEndOfService()).ifPresent(deviceModel::setEndOfService);
	    //			if (dcmDevice.getSensorProfile() != null && dcmDevice.getSensorProfile().getId() != null) {
	    //				Optional.ofNullable(dcmDevice.getSensorProfile().getId()).ifPresent(deviceModel::setSensorProfileId);
	    //				Optional.ofNullable(dcmDevice.getSensorProfile().getSensorProfileDesc())
	    //						.ifPresent(deviceModel::setSensorProfileDesc);
	    //			}
	    //			Optional.ofNullable(dcmDevice.getVendorName()).ifPresent(deviceModel::setVendorName);
	    if (dcmDevice.getCustomerId() != null) {
		Optional.ofNullable(dcmDevice.getCustomerId()).ifPresent(deviceModel::setCustomerId);
	    }
	    Optional.ofNullable(dcmDevice.getUserId()).ifPresent(deviceModel::setUserId);
	    deviceModel.setIsSubscribed(isSubscribed);
	}
	return deviceModel;

    }

    public static LocationModel convertCommercialLocationTypeDetial(ScanLocation location, Long l) {

	LocationModel locationModel = new LocationModel();
	locationModel.setInstallationCenterId(location.getId());
	locationModel.setInstCenterName(location.getLocationName());
	locationModel.setWarehouseName(location.getWarehouseName());
	locationModel.setCode(location.getCode());
	if (location.getState() != null && location.getState().getId() != null) {
	    locationModel.setStateId(location.getState().getId());
	    locationModel.setStateName(location.getState().getName());
	}
	if (location.getStatus() != null && location.getStatus().getStatusId() != null) {
	    locationModel.setStatusId(location.getStatus().getStatusId());
	    locationModel.setStatusName(location.getStatus().getStatusDesc());
	}
	return locationModel;
    }

    public static List<DeviceGroupModel> fillDeviceGroupModel(List<DcmDeviceGroup> deviceGroupList) {
	List<DeviceGroupModel> deviceGroupModelList = new ArrayList<>();

	if (deviceGroupList != null) {
	    for (DcmDeviceGroup dcmDeviceGroup : deviceGroupList) {
		DeviceGroupModel deviceGroupModel = new DeviceGroupModel();
		Optional.ofNullable(dcmDeviceGroup.getId()).ifPresent(deviceGroupModel::setDeviceGroupId);
		Optional.ofNullable(dcmDeviceGroup.getDeviceGroupCode())
			.ifPresent(deviceGroupModel::setDeviceGroupCode);
		Optional.ofNullable(dcmDeviceGroup.getDeviceGroupDesc())
			.ifPresent(deviceGroupModel::setDeviceGroupDesc);
		deviceGroupModelList.add(deviceGroupModel);
	    }
	}
	return deviceGroupModelList;
    }

    public static List<DeviceSubTypeModel> fillDeviceSubTypeModel(List<DcmDeviceSubType> dcmDeviceSubTypeList) {
	List<DeviceSubTypeModel> deviceSubTypeModelList = new ArrayList<>();

	if (dcmDeviceSubTypeList != null) {
	    for (DcmDeviceSubType dcmDeviceSubType : dcmDeviceSubTypeList) {
		DeviceSubTypeModel deviceSubTypeModel = new DeviceSubTypeModel();
		Optional.ofNullable(dcmDeviceSubType.getId()).ifPresent(deviceSubTypeModel::setDeviceSubTypeId);
		Optional.ofNullable(dcmDeviceSubType.getDeviceSubTypeCode())
			.ifPresent(deviceSubTypeModel::setDeviceSubTypepCode);
		Optional.ofNullable(dcmDeviceSubType.getDeviceSubTypeDesc())
			.ifPresent(deviceSubTypeModel::setDeviceSubTypeDesc);
		deviceSubTypeModelList.add(deviceSubTypeModel);
	    }
	}
	return deviceSubTypeModelList;
    }

    public static List<DeviceSensorProfileModel> fillDeviceSensorProfileModel(
	    List<DcmSensorProfile> dcmSensorProfileList) {
	List<DeviceSensorProfileModel> deviceSensorProfileModelList = new ArrayList<>();

	if (dcmSensorProfileList != null) {
	    for (DcmSensorProfile dcmSensorProfile : dcmSensorProfileList) {
		DeviceSensorProfileModel deviceSensorProfileModel = new DeviceSensorProfileModel();
		Optional.ofNullable(dcmSensorProfile.getId())
			.ifPresent(deviceSensorProfileModel::setDeviceSensorProfileId);
		Optional.ofNullable(dcmSensorProfile.getSensorProfileCode())
			.ifPresent(deviceSensorProfileModel::setDeviceSensorProfileCode);
		Optional.ofNullable(dcmSensorProfile.getSensorProfileDesc())
			.ifPresent(deviceSensorProfileModel::setDeviceSensorProfileDesc);
		deviceSensorProfileModelList.add(deviceSensorProfileModel);
	    }
	}
	return deviceSensorProfileModelList;
    }

    public static UserDeviceModel convertUserDevice(DcmUserDevice userDevice) {
	UserDeviceModel deviceModel = new UserDeviceModel();
	deviceModel.setUserDeviceTokenId(userDevice.getId());
	deviceModel.setCreated_on(userDevice.getCreatedOn());
	deviceModel.setDeviceToken(userDevice.getDeviceToken());
	deviceModel.setModifiedOn(userDevice.getModifiedOn());
	deviceModel.setUserId(userDevice.getUserId());
	return deviceModel;
    }

    public static AnalyticsRangeModel convertRange(AnalyticsRangeModel rangeResponseModel,
	    List<ClientsAnalyticsRange> rangeEntity) {

	List<RangeModel> ranges = new ArrayList<>();

	for (ClientsAnalyticsRange range : rangeEntity) {
	    RangeModel rangeModel = new RangeModel();
	    rangeModel.setId(range.getId());
	    rangeModel.setAnalyticId(range.getAnalytics().getId());
	    rangeModel.setAnalyticName(range.getAnalytics().getAnalyticName().trim());

	    if (range.getLocation() != null && range.getLocation().getWarehouseName() != null
		    && !range.getLocation().getWarehouseName().isEmpty())
		rangeModel.setWarehouseName(range.getLocation().getWarehouseName().trim());

	    if (range.getCommodityId() != null && range.getCommodityId().getCommodityName() != null) {
		rangeModel.setCommodityName(range.getCommodityId().getCommodityName().trim());
		rangeModel.setCommodityId(range.getAnalytics().getCommodityId().getId());
	    }

	    if (range.getClient() != null && range.getLocation().getWarehouseName() != null) {
		rangeModel.setClientId(range.getClient().getCustomerId());
		rangeModel.setClientName(range.getClient().getCustomerName().trim());
	    }

	    //rangeModel.setMinRange(range.getMinRange());
	    rangeModel.setMaxRange(range.getMaxRange());

	    if (range.getLocation() != null && range.getLocation().getId() != null) {
		rangeModel.setLocationId(range.getLocation().getId());
		rangeModel.setLocationName(range.getLocation().getLocationName().trim());
	    }
	    ranges.add(rangeModel);
	}
	rangeResponseModel.setRangeList(ranges);

	return rangeResponseModel;
    }

    public static AnalyticsRangeModel convertRangeFillModel(AnalyticsRangeModel range, List<Object[]> objArray) {
	// TODO Auto-generated method stub
	return null;
    }

    public static PackagesModel convertPackage(Packages p) {
	PackagesModel pvo = new PackagesModel();

	pvo.setId(p.getId());
	//		if(p.getSubscriptionType()!=null) {
	//			pvo.setSubscriptionName(p.getSubscriptionType().getSubscriptionType());
	//	
	//		}
	pvo.setPackageName(p.getPackageName());
	pvo.setTotalScans(p.getTotalScans());
	pvo.setDurationUnit(p.getDurationUnit());
	if (p.getDurationPeriod() != null) {
	    pvo.setDurationPeriod(Integer.parseInt(p.getDurationPeriod()));
	}
	//pvo.setDeviceTypeName(p.getDeviceType().);
	return pvo;
    }

    public static List<PackagesModel> convertPackageList(List<Packages> pList) {

	List<PackagesModel> packageLst = new ArrayList<>();
	if (!pList.isEmpty()) {
	    pList.forEach(pack -> packageLst.add(convertPackage(pack)));
	}

	return packageLst;
    }

    public static CommodityVarietyModel convertVarieties(List<CommodityVarietyEntity> cvList,
	    CommodityVarietyModel cvModel) {
	List<VarietyModel> varieties = new ArrayList<VarietyModel>();
	for (CommodityVarietyEntity cv : cvList) {
	    VarietyModel vm = new VarietyModel();
	    if (cv != null && cv.getCommodity() != null && cv.getCommodity().getId() != null) {
		cvModel.setCommodityId(cv.getCommodity().getId());
		cvModel.setCommodityName(cv.getCommodity().getCommodityName());
	    }
	    vm.setVarietyId(cv.getId());
	    vm.setVarietyName(cv.getVarietyName());
	    varieties.add(vm);
	}
	cvModel.setVarieties(varieties);

	return cvModel;
    }

    public static <T> List<T> removeDuplicates(List<T> list) {
	Set<T> set = new LinkedHashSet<>();
	set.addAll(list);
	list.clear();
	list.addAll(set);
	return list;
    }

    private static Analytics setMoisture(BigDecimal moisture) {
	Analytics ana = new Analytics();
	if (moisture != null) {
	    ana.setAmountUnit("%");
	    ana.setAnalysisName("moisturecontent");
	    ana.setResult(Utility.formatDecimal(Utility.getBigDecimalValue(moisture)));
	    ana.setTotalAmount(String.valueOf(Utility.formatDecimal(Utility.getBigDecimalValue(moisture))));
	}
	return ana;
    }

}
