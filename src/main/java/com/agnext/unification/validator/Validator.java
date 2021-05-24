/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agnext.unification.common.CommonUtil;
import com.agnext.unification.common.Constants;
import com.agnext.unification.config.ApplicationContext;
import com.agnext.unification.exception.IMException;

/**
 * The Class Validator.
 */
@Component
public class Validator {
	
	@Autowired
	ApplicationContext applicationContext;
	
	
	public boolean isValidUser(String userType, boolean throwException)throws IMException {
		boolean response =  userType!=null && userType.equals(applicationContext.getRequestContext().getCustomerType());
		
		if(!throwException)
			return response;
		else if(!response)
			throw new IMException(Constants.ErrorCode.USER_NOT_AUTHORIZED,
					Constants.ErrorMessage.USER_NOT_AUTHORIZED);
		return true;
		
	}
	
	protected boolean isValidUserHierarchy(String userHierarchy) {
		
		return userHierarchy!=null && userHierarchy.equals(applicationContext.getRequestContext().getUserHierarchy());
	}

	protected void validateMaxLength(String value, int maxLength, String errorCode, String errorMessage)
			throws IMException {
		if (!CommonUtil.verifyMaxLength(value, maxLength))
			throw new IMException(errorCode, errorMessage);
	}

	protected void validateMinLength(String value, int minLength, String errorCode, String errorMessage)
			throws IMException {
		if (!CommonUtil.verifyMinLength(value, minLength))
			throw new IMException(errorCode, errorMessage);
	}

	protected void validateMinMaxLength(String value, int minLength, int maxLength, String errorCode,
			String errorMessage) throws IMException {
		if (!CommonUtil.verifyMinMaxLength(value, minLength, maxLength))
			throw new IMException(errorCode, errorMessage);
	}
	

	protected void validateCharacters(String value, String errorCode, String errorMessage) throws IMException {
//		String regex = "(?i)[A-Za-z]+([&.\\s]+[A-Za-z]*)*";
		String regexSpace ="^([A-Za-z]+ )+[A-Za-z]+$|^[A-Za-z]+$";
//		String regexDot="^([a-zA-Z]+\\\\.)+[a-zA-Z]$";
		if (!value.matches(regexSpace)) {
			throw new IMException(errorCode, errorMessage);
		}
	}

	protected void validateEmail(String value, String errorCode, String errorMessage) throws IMException {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		if (!pat.matcher(value).matches()) {
			throw new IMException(errorCode, errorMessage);
		}
	}

	protected void validatePhoneNumber(String value, String errorCode, String errorMessage) throws IMException {
		Pattern pattern = Pattern.compile("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$");
		Matcher matcher = pattern.matcher(value);
		if (!matcher.matches()) {
			throw new IMException(errorCode, errorMessage);
		}
	}

	protected void validateGstIn(String value, String errorCode, String errorMessage) throws IMException {
		String regex = "\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[A-Z\\d]{1}[Z]{1}[A-Z\\d]{1}";
		if (!value.matches(regex)) {
			throw new IMException(errorCode, errorMessage);
		}
	}

	protected void validatePanNumber(String value, String errorCode, String errorMessage) throws IMException {
		Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
		Matcher matcher = pattern.matcher(value);
		if (!matcher.matches()) {
			throw new IMException(errorCode, errorMessage);
		}
	}

	protected void validateAddress(String value, String errorCode, String errorMessage) throws IMException {
		Pattern pattern = Pattern.compile("^[#.0-9a-zA-Z\\s,-]+$");
		Matcher matcher = pattern.matcher(value);
		if (!matcher.matches()) {
			throw new IMException(errorCode, errorMessage);
		}
	}
	
	protected void validateZipCode(String value, String errorCode, String errorMessage) throws IMException {
		Pattern pattern = Pattern.compile("^[1-9][0-9]{5}$");
		Matcher matcher = pattern.matcher(value);
		if (!matcher.matches()) {
			throw new IMException(errorCode, errorMessage);
		}
	}
	
	protected void validateBankBranchCode(String value, String errorCode, String errorMessage) throws IMException {
		Pattern pattern = Pattern.compile("^[A-Z]{4}[0][A-Z0-9]{6}$");
		Matcher matcher = pattern.matcher(value);
		if (!matcher.matches()) {
			throw new IMException(errorCode, errorMessage);
		}
	}
	
	protected void validateBankAccountnumber(String value, String errorCode, String errorMessage) throws IMException {
//		Pattern pattern = Pattern.compile("[0-9]{9,11}");
		Pattern pattern = Pattern.compile("^[0-9]{5,25}$");
		Matcher matcher = pattern.matcher(value);
		if (!matcher.matches()) {
			throw new IMException(errorCode, errorMessage);
		}
	}
	protected boolean validatePattern(String value,Pattern pattern) {
		return pattern.matcher(value).matches();
	}
}
