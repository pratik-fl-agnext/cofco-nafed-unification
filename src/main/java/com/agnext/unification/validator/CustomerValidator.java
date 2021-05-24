/*
 * @author Vishal B.
 * @version 1.0
 */

package com.agnext.unification.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.AddressTypeEntity;
import com.agnext.unification.entity.nafed.CustomerEntity;
import com.agnext.unification.entity.nafed.CustomerTypeEntity;
import com.agnext.unification.entity.nafed.StatusEntity;
import com.agnext.unification.entity.nafed.UserEntity;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.CustomerAddressModel;
import com.agnext.unification.model.CustomerBankDetailModel;
import com.agnext.unification.model.CustomerModel;
import com.agnext.unification.repository.nafed.AddressTypeRepository;
import com.agnext.unification.repository.nafed.CustomerAddressRepository;
import com.agnext.unification.repository.nafed.CustomerBankDetailRepository;
import com.agnext.unification.repository.nafed.CustomerBillingDetailRepository;
import com.agnext.unification.repository.nafed.CustomerRepository;
import com.agnext.unification.repository.nafed.CustomerTypeRepository;
import com.agnext.unification.repository.nafed.StatusRepository;
import com.agnext.unification.repository.nafed.UserRepository;

@Component
public class CustomerValidator extends Validator {

	@Autowired
	CustomerTypeRepository repository;

	@Autowired
	AddressTypeRepository addressTypeRepository;

	@Autowired
	CustomerBankDetailRepository customerBankRepo;

	@Autowired
	CustomerAddressRepository customerAddressRepo;

	@Autowired
	CustomerBillingDetailRepository billingRepo;

	@Autowired
	CustomerRepository customerRpo;

	@Autowired
	StatusRepository statusRepo;

	@Autowired
	UserRepository userRepository;

	private CustomerTypeEntity CUSTOMER_TYPE_CUSTOMER;

	private CustomerTypeEntity getDefaultCustomerType() {
		if (CUSTOMER_TYPE_CUSTOMER == null)
			CUSTOMER_TYPE_CUSTOMER = repository.findByCustomerType(Constants.CustomerType.CUSTOMER);
		return CUSTOMER_TYPE_CUSTOMER;
	}

	private AddressTypeEntity ADDRESS_TYPE_USER;

	private AddressTypeEntity getDefaultAddressType() {
		if (ADDRESS_TYPE_USER == null)
			ADDRESS_TYPE_USER = addressTypeRepository.findByAddressType(Constants.AddressType.OFFICE);
		return ADDRESS_TYPE_USER;
	}

	public CustomerTypeEntity createCustomerTypeValidate(Long customerTypeId) throws IMException {

		CustomerTypeEntity customerTypeEntity = null;
		if (customerTypeId != null)
			customerTypeEntity = repository.findByCustomerTypeId(customerTypeId);
		else
			customerTypeEntity = getDefaultCustomerType();

		if (customerTypeEntity == null)
			throw new IMException(Constants.ErrorCode.CUSTOMER_TYPE_ID_NOT_FOUND,
					Constants.ErrorMessage.CUSTOMER_TYPE_ID_NOT_FOUND);
		return customerTypeEntity;
	}

	public List<AddressTypeEntity> validateAddressType(CustomerModel request) throws IMException {
		List<AddressTypeEntity> entities = new ArrayList<AddressTypeEntity>();
		for (CustomerAddressModel customerAddressModel : request.getCustomerAddresses()) {
			AddressTypeEntity addressTypeEntity = null;
			if (customerAddressModel.getAddressTypeId() == null) {
				addressTypeEntity = getDefaultAddressType();
			} else {
				addressTypeEntity = addressTypeRepository.findByAddressTypeId(customerAddressModel.getAddressTypeId());
				if (addressTypeEntity == null)
					throw new IMException(Constants.ErrorCode.ADDRESS_ID_NOT_EXIST,
							Constants.ErrorMessage.ADDRESS_ID_NOT_EXIST);
			}
			if (customerAddressModel.getAddressLine1() == null) {
				throw new IMException(Constants.ErrorCode.ADDRESS_ID_NOT_EXIST,
						Constants.ErrorMessage.ADDRESS_ID_NOT_EXIST);
			}

			if (customerAddressModel.getCountry() == null) {
				throw new IMException(Constants.ErrorCode.COUNTRY_INVALID, Constants.ErrorMessage.COUNTRY_INVALID);
			}

			if (customerAddressModel.getState() == null) {
				throw new IMException(Constants.ErrorCode.STATE_INVALID, Constants.ErrorMessage.STATE_INVALID);
			}

			if (customerAddressModel.getCity() == null) {
				throw new IMException(Constants.ErrorCode.CITY_INVALID, Constants.ErrorMessage.CITY_INVALID);
			}

			if (customerAddressModel.getPinCode() == null) {
				throw new IMException(Constants.ErrorCode.ZIP_CODE_MANDATORY,
						Constants.ErrorMessage.ZIP_CODE_MANDATORY);
			}

			validateAddress(customerAddressModel.getAddressLine1(), Constants.ErrorCode.ADDRESS_LINE_INVALID,
					Constants.ErrorMessage.ADDRESS_LINE_INVALID);

			validateZipCode(customerAddressModel.getPinCode() + "", Constants.ErrorCode.ZIP_CODE_MANDATORY,
					Constants.ErrorMessage.ZIP_CODE_MANDATORY);

			entities.add(addressTypeEntity);

		}
		return entities;

	}

	public void validateCreateCustomer(CustomerModel request) throws IMException {

		if (request.getName() == null) {
			throw new IMException(Constants.ErrorCode.CUSTOMER_NAME_IS_MANDATORY,
					Constants.ErrorMessage.CUSTOMER_NAME_IS_MANDATORY);
		}

		validateMinMaxLength(request.getName(), Constants.FieldLength.CUSTOMER_NAME_MIN_LENGTH,
				Constants.FieldLength.CUSTOMER_NAME_MAX_LENGTH, Constants.ErrorCode.CUSTOMER_NAME_INVALID_MAX_LENGTH,
				Constants.ErrorMessage.CUSTOMER_NAME_INVALID_MAX_LENGTH);

		validateCharacters(request.getName(), Constants.ErrorCode.CUSTOMER_NAME_INVALID_CHARACTERS,
				Constants.ErrorMessage.CUSTOMER_NAME_INVALID_CHARACTERS);

		validateEmail(request.getEmail(), Constants.ErrorCode.EMAIL_INVALID, Constants.ErrorMessage.EMAIL_INVALID);

		validatePhoneNumber(request.getContactNumber(), Constants.ErrorCode.CONTACT_NUMBER_INVALID,
				Constants.ErrorMessage.CONTACT_NUMBER_INVALID);

		CustomerEntity customerEntity = customerRpo.findByCustomerGst(request.getGst());

		if (customerEntity != null) {
			throw new IMException(Constants.ErrorCode.GST_ALREADY_EXIST, Constants.ErrorMessage.GST_ALREADY_EXIST);
		}

		CustomerEntity customerEntityPan = customerRpo.findByCustomerPan(request.getPan());

		if (customerEntityPan != null) {
			throw new IMException(Constants.ErrorCode.PAN_ALREADY_EXIST, Constants.ErrorMessage.PAN_ALREADY_EXIST);
		}

		validateGstIn(request.getGst(), Constants.ErrorCode.GST_NUMBER_INVALID,
				Constants.ErrorMessage.GST_NUMBER_INVALID);

		validatePanNumber(request.getPan(), Constants.ErrorCode.PAN_NUMBER_NUMBER_INVALID,
				Constants.ErrorMessage.PAN_NUMBER_NUMBER_INVALID);

	}
	
	public void validateSubClient(CustomerModel request) throws IMException {

		if (request.getName() == null) {
			throw new IMException(Constants.ErrorCode.CUSTOMER_NAME_IS_MANDATORY,
					Constants.ErrorMessage.CUSTOMER_NAME_IS_MANDATORY);
		}
		
		CustomerEntity csEntity = customerRpo.findByCustomerEmail(request.getEmail());
		if (csEntity != null) {
			throw new IMException(Constants.ErrorCode.EMAIL_ALREADY_EXIST, Constants.ErrorMessage.EMAIL_ALREADY_EXIST);
		}

		validateMinMaxLength(request.getName(), Constants.FieldLength.CUSTOMER_NAME_MIN_LENGTH,
				Constants.FieldLength.CUSTOMER_NAME_MAX_LENGTH, Constants.ErrorCode.CUSTOMER_NAME_INVALID_MAX_LENGTH,
				Constants.ErrorMessage.CUSTOMER_NAME_INVALID_MAX_LENGTH);

		validateCharacters(request.getName(), Constants.ErrorCode.CUSTOMER_NAME_INVALID_CHARACTERS,
				Constants.ErrorMessage.CUSTOMER_NAME_INVALID_CHARACTERS);

		validateEmail(request.getEmail(), Constants.ErrorCode.EMAIL_INVALID, Constants.ErrorMessage.EMAIL_INVALID);

		validatePhoneNumber(request.getContactNumber(), Constants.ErrorCode.CONTACT_NUMBER_INVALID,
				Constants.ErrorMessage.CONTACT_NUMBER_INVALID);
	}
	
	public void validateUpdateCustomer(CustomerModel request) throws IMException {

		if (request.getName() == null) {
			throw new IMException(Constants.ErrorCode.CUSTOMER_NAME_IS_MANDATORY,
					Constants.ErrorMessage.CUSTOMER_NAME_IS_MANDATORY);
		}

		validateMinMaxLength(request.getName(), Constants.FieldLength.CUSTOMER_NAME_MIN_LENGTH,
				Constants.FieldLength.CUSTOMER_NAME_MAX_LENGTH, Constants.ErrorCode.CUSTOMER_NAME_INVALID_MAX_LENGTH,
				Constants.ErrorMessage.CUSTOMER_NAME_INVALID_MAX_LENGTH);

		validateCharacters(request.getName(), Constants.ErrorCode.CUSTOMER_NAME_INVALID_CHARACTERS,
				Constants.ErrorMessage.CUSTOMER_NAME_INVALID_CHARACTERS);

		validateEmail(request.getEmail(), Constants.ErrorCode.EMAIL_INVALID, Constants.ErrorMessage.EMAIL_INVALID);

		validatePhoneNumber(request.getContactNumber(), Constants.ErrorCode.CONTACT_NUMBER_INVALID,
				Constants.ErrorMessage.CONTACT_NUMBER_INVALID);

		validateGstIn(request.getGst(), Constants.ErrorCode.GST_NUMBER_INVALID,
				Constants.ErrorMessage.GST_NUMBER_INVALID);

		validatePanNumber(request.getPan(), Constants.ErrorCode.PAN_NUMBER_NUMBER_INVALID,
				Constants.ErrorMessage.PAN_NUMBER_NUMBER_INVALID);

	}

	public void validateCustomerBankDetail(CustomerModel request) throws IMException {

		for (CustomerBankDetailModel bankDetailModel : request.getCustomerBankDetails()) {

			validateMinMaxLength(bankDetailModel.getBankName(), Constants.FieldLength.BANK_NAME_MIN_LENGTH,
					Constants.FieldLength.BANK_NAME_MAX_LENGTH, Constants.ErrorCode.BANK_NAME_INVALID_MAX_LENGTH,
					Constants.ErrorMessage.BANK_NAME_INVALID_MAX_LENGTH);

			validateMinMaxLength(bankDetailModel.getBankBranch(), Constants.FieldLength.BANK_NAME_MIN_LENGTH,
					Constants.FieldLength.BANK_NAME_MAX_LENGTH, Constants.ErrorCode.BANK_NAME_INVALID_MAX_LENGTH,
					Constants.ErrorMessage.BANK_NAME_INVALID_MAX_LENGTH);

			validateBankAccountnumber(bankDetailModel.getBankAccountNumber(),
					Constants.ErrorCode.BANK_ACCOUNT_NUMBER_INVALID,
					Constants.ErrorMessage.BANK_ACCOUNT_NUMBER_INVALID);

			validateBankBranchCode(bankDetailModel.getBankIfsc(), Constants.ErrorCode.BANK_BRANCH_CODE_INVALID,
					Constants.ErrorMessage.BANK_BRANCH_CODE_INVALID);
		}
	    
	}

	public StatusEntity isValidStatus(Long statusId) throws IMException {
		StatusEntity entity = statusRepo.findByStatusId(statusId);
		if (entity == null)
			throw new IMException(Constants.ErrorCode.STATUS_ID_NOT_EXIST, Constants.ErrorMessage.STATUS_ID_NOT_EXIST);
		return entity;

	}

	public CustomerEntity validateCustomerId(CustomerModel request) throws IMException {
		CustomerEntity customerEntity = customerRpo.findByCustomerIdAndPartnerIdAndStatusStatusId(
				request.getCustomerId(), request.getPartnerId(), Constants.STATUS.INITIATED.getId());
		if (customerEntity == null) {
			throw new IMException(Constants.ErrorCode.CUSTOMER_ID_NOT_EXIST,
					Constants.ErrorMessage.CUSTOMER_ID_NOT_EXIST);
		}
		return customerEntity;
	}

	public UserEntity validateUserForCustomer(CustomerEntity customerEntity) throws IMException {
		UserEntity userEntity = userRepository.findByStatusStatusIdAndCustomerCustomerId(
				Constants.STATUS.INITIATED.getId(), customerEntity.getCustomerId());
		if (userEntity == null) {
			throw new IMException(Constants.ErrorCode.USER_ID_NOT_EXIST, Constants.ErrorCode.USER_ID_NOT_EXIST);
		}
		return userEntity;
	}

}
