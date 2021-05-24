/*
 * @author Vishal B.
 * @version 1.0
 */

package com.agnext.unification.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agnext.unification.common.CommonUtil;
import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.AddressTypeEntity;
import com.agnext.unification.entity.nafed.CustomerEntity;
import com.agnext.unification.entity.nafed.UserAddressEntity;
import com.agnext.unification.entity.nafed.UserEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.PasswordModel;
import com.agnext.unification.model.UserAddressModel;
import com.agnext.unification.model.UserCSVModel;
import com.agnext.unification.model.UserModel;
import com.agnext.unification.repository.nafed.AddressTypeRepository;
import com.agnext.unification.repository.nafed.CustomerRepository;
import com.agnext.unification.repository.nafed.UserAddressRepository;
import com.agnext.unification.repository.nafed.UserRepository;

@Component
public class UserValidator extends Validator {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	AddressTypeRepository addressTypeRepository;

	@Autowired
	UserRepository repository;

	@Autowired
	UserAddressRepository userAddressRepository;

	/**
	 * Validate customer valid.
	 *
	 * @param request the request
	 * @throws IMException the IM exception
	 */
	public CustomerEntity validateCustomer(UserModel request) throws IMException {
		
		Long customerId=null;
		if(request.getCustomerId() !=null) {
			customerId=request.getCustomerId();
		}else {
			customerId=applicationContext.getRequestContext().getCustomerId();
		}

		CustomerEntity customerEntity = customerRepository.findByCustomerIdAndStatusStatusId(
				customerId, Constants.STATUS.ACTIVE.getId());
		if (customerEntity == null)
			throw new IMException(Constants.ErrorCode.CUSTOMER_ID_NOT_EXIST,
					Constants.ErrorMessage.CUSTOMER_ID_NOT_EXIST);
		return customerEntity;

	}

	private AddressTypeEntity ADDRESS_TYPE_USER;

	private AddressTypeEntity getDefaultCustomerType() {
		if (ADDRESS_TYPE_USER == null)
			ADDRESS_TYPE_USER = addressTypeRepository.findByAddressType(Constants.AddressType.OFFICE);
		return ADDRESS_TYPE_USER;
	}

	public List<AddressTypeEntity> validateAddressType(UserModel request) throws IMException {
		List<AddressTypeEntity> entities = new ArrayList<AddressTypeEntity>();
		for (UserAddressModel userAddressModel : request.getUserAddresses()) {
			AddressTypeEntity addressTypeEntity = null;
			if (userAddressModel.getAddressTypeId() == null) {
				addressTypeEntity = getDefaultCustomerType();
			} else {
				addressTypeEntity = addressTypeRepository.findByAddressTypeId(userAddressModel.getAddressTypeId());
				if (addressTypeEntity == null)
					throw new IMException(Constants.ErrorCode.ADDRESS_ID_NOT_EXIST,
							Constants.ErrorMessage.ADDRESS_ID_NOT_EXIST);
			}

			entities.add(addressTypeEntity);

		}
		return entities;

	}

	public UserEntity userValidate(Long userId) throws IMException {
		UserEntity entity = repository.getOne(userId);
		if (entity == null)
			throw new IMException(Constants.ErrorCode.USER_ID_NOT_EXIST, Constants.ErrorMessage.USER_ID_NOT_EXIST);
		return entity;
	}

	public UserAddressEntity validateUserAddress(UserEntity userEntity) throws IMException {

		for (UserAddressEntity userAddressEntity : userEntity.getUserAddresses()) {
			UserAddressEntity entity = userAddressRepository.findByAddressId(userAddressEntity.getAddressId());
			if (entity == null)
				throw new IMException(Constants.ErrorCode.ADDRESS_ID_NOT_EXIST,
						Constants.ErrorMessage.ADDRESS_ID_NOT_EXIST);
			return entity;
		}
		return null;
	}

	public UserEntity validateUpdateUser(UserModel userModel) throws IMException {

		if (userModel != null) {
			UserEntity entity = repository.findByUserIdAndStatusStatusId(userModel.getUserId(),
					Constants.STATUS.ACTIVE.getId());
			if (entity == null)
				throw new IMException(Constants.ErrorCode.USER_ID_NOT_EXIST, Constants.ErrorMessage.USER_ID_NOT_EXIST);
			return entity;
		}

		return null;
	}

	public void validateCreateUser(UserModel request) throws IMException {

		if (request.getUserFirstName() == null) {
			throw new IMException(Constants.ErrorCode.USER_NAME_IS_MANDATORY,
					Constants.ErrorMessage.USER_NAME_IS_MANDATORY);
		}

		if (request.getUserLastName() == null) {
			throw new IMException(Constants.ErrorCode.USER_LAST_NAME_IS_MANDATORY,
					Constants.ErrorMessage.USER_LAST_NAME_IS_MANDATORY);
		}

		if (request.getUserContactNumber() == null) {
			throw new IMException(Constants.ErrorCode.USER_CONTACT_NUMBER_IS_MANDATORY,
					Constants.ErrorMessage.USER_CONTACT_NUMBER_IS_MANDATORY);
		}

		if (request.getUserEmail() == null) {
			throw new IMException(Constants.ErrorCode.USER_EMAIL_IS_MANDATORY,
					Constants.ErrorMessage.USER_EMAIL_IS_MANDATORY);
		}

		if (request.getRoles() == null) {
			throw new IMException(Constants.ErrorCode.USER_ROLE_IS_MANDATORY,
					Constants.ErrorMessage.USER_ROLE_IS_MANDATORY);
		}

		if (request.getUserAddresses() == null) {
			throw new IMException(Constants.ErrorCode.USER_ADDRESS_IS_MANDATORY,
					Constants.ErrorMessage.USER_ADDRESS_IS_MANDATORY);
		}
		

		validateMinMaxLength(request.getUserFirstName(), Constants.FieldLength.FIRST_NAME_MIN_LENGTH,
				Constants.FieldLength.FIRST_NAME_MAX_LENGTH, Constants.ErrorCode.FIRST_NAME_INVALID_MAX_LENGTH,
				Constants.ErrorMessage.FIRST_NAME_INVALID_MAX_LENGTH);

		validateMinMaxLength(request.getUserLastName(), Constants.FieldLength.LAST_NAME_MIN_LENGTH,
				Constants.FieldLength.LAST_NAME_MAX_LENGTH, Constants.ErrorCode.LAST_NAME_INVALID_MAX_LENGTH,
				Constants.ErrorMessage.LAST_NAME_INVALID_MAX_LENGTH);

		validatePhoneNumber(request.getUserContactNumber(), Constants.ErrorCode.CONTACT_NUMBER_INVALID,
				Constants.ErrorMessage.CONTACT_NUMBER_INVALID);

		validateEmail(request.getUserEmail(), Constants.ErrorCode.EMAIL_INVALID, Constants.ErrorMessage.EMAIL_INVALID);

		for (UserAddressModel userAddressModel : request.getUserAddresses()) {
			validateAddress(userAddressModel.getAddressLine1(), Constants.ErrorCode.ADDRESS_LINE_INVALID,
					Constants.ErrorMessage.ADDRESS_LINE_INVALID);
			
			validateZipCode(userAddressModel.getPinCode() + "", Constants.ErrorCode.INVALID_ZIP_CODE,
					Constants.ErrorMessage.INVALID_ZIP_CODE);
			
			if (userAddressModel.getPinCode() == null) {
				throw new IMException(Constants.ErrorCode.ZIP_CODE_MANDATORY,
						Constants.ErrorMessage.ZIP_CODE_MANDATORY);
			}
			
			if (userAddressModel.getAddressLine1() == null) {
				throw new IMException(Constants.ErrorCode.ADDRESS_ID_NOT_EXIST,
						Constants.ErrorMessage.ADDRESS_ID_NOT_EXIST);
			}
		}
	}
	
	public void validateChangePassword(PasswordModel request, String dbPassword) throws IMException {

		if (CommonUtil.isEmpty(request.getOldPassword())) 
			throw new IMException(Constants.ErrorCode.OLD_PASSWORD_REQUIRED, Constants.ErrorMessage.OLD_PASSWORD_REQUIRED);
		
		/*
		 * if(!dbPassword.equals(PasswordEncoderFactories.
		 * createDelegatingPasswordEncoder().encode(request.getOldPassword()))) throw
		 * new IMException(Constants.ErrorCode.INVALID_CURRENT_PASSWORD_ERROR,
		 * Constants.ErrorMessage.INVALID_CURRENT_PASSWORD_ERROR);
		 */
		
		if (CommonUtil.isEmpty(request.getNewPassword())) 
			throw new IMException(Constants.ErrorCode.NEW_PASSWORD_REQUIRED, Constants.ErrorMessage.NEW_PASSWORD_REQUIRED);
		
		validateMinMaxLength(request.getNewPassword(), Constants.FieldLength.PASSWORD_MIN_LENGTH,
				Constants.FieldLength.PASSWORD_MAX_LENGTH, Constants.ErrorCode.INVALID_PASSWORD_ERROR,
				Constants.ErrorMessage.INVALID_PASSWORD_ERROR);
		
		if (request.getOldPassword().equals(request.getNewPassword())) 
			throw new IMException(Constants.ErrorCode.OLD_NEW_PASSWORD_SAME, Constants.ErrorMessage.OLD_NEW_PASSWORD_SAME);
		
		if(!validatePattern(request.getNewPassword(), Constants.Patterns.PASSWORD))
			throw new IMException(Constants.ErrorCode.INVALID_PASSWORD_ERROR, Constants.ErrorMessage.INVALID_PASSWORD_ERROR);
	}

	public List<AddressTypeEntity> validateAddressTypeBulk(UserModel request) throws IMException {
		List<AddressTypeEntity> entities = new ArrayList<AddressTypeEntity>();
		for (UserAddressModel userAddressModel : request.getUserAddresses()) {
			AddressTypeEntity addressTypeEntity = null;
			if (userAddressModel.getAddressTypeId() == null) {
				addressTypeEntity = getDefaultCustomerType();
			} else {
				addressTypeEntity = addressTypeRepository.findByAddressTypeId(userAddressModel.getAddressTypeId());
				if (addressTypeEntity == null)
					throw new IMException(Constants.ErrorCode.ADDRESS_ID_NOT_EXIST,
							Constants.ErrorMessage.ADDRESS_ID_NOT_EXIST);
			}

			entities.add(addressTypeEntity);

		}
		return entities;

	}
	
	/**
	 * Validate customer valid.
	 *
	 * @param request the request
	 * @throws IMException the IM exception
	 */
	public CustomerEntity validateCustomerForBulkUpload(UserCSVModel request,Long cutomerId2) throws IMException {
		
		Long customerId=null;
		if(cutomerId2 !=null) {
			customerId=cutomerId2;
		}else {
			customerId=applicationContext.getRequestContext().getCustomerId();
		}

		CustomerEntity customerEntity = customerRepository.findByCustomerIdAndStatusStatusId(
				customerId, Constants.STATUS.ACTIVE.getId());
		if (customerEntity == null)
			throw new IMException(Constants.ErrorCode.CUSTOMER_ID_NOT_EXIST,
					Constants.ErrorMessage.CUSTOMER_ID_NOT_EXIST);
		return customerEntity;

	}
	
	public List<AddressTypeEntity> validateAddressTypeForBulkUpload(UserCSVModel request) throws IMException {
		List<AddressTypeEntity> entities = new ArrayList<AddressTypeEntity>();
//		for (UserAddressModel userAddressModel : request.getUserAddresses()) {
			AddressTypeEntity addressTypeEntity = null;
			if (request !=null) {
				addressTypeEntity = getDefaultCustomerType();
			} else {
				addressTypeEntity = addressTypeRepository.findByAddressTypeId(4L);
				if (addressTypeEntity == null)
					throw new IMException(Constants.ErrorCode.ADDRESS_ID_NOT_EXIST,
							Constants.ErrorMessage.ADDRESS_ID_NOT_EXIST);
//			}
			}
			entities.add(addressTypeEntity);

		return entities;

	}
}