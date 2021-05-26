package com.agnext.unification.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Class to contain all constants to be used in application
 *
 * @author VISHAL B.
 */
public class Constants {

	public static final String SEPARATOR = "/";
	public static final int QRCODE_SIZE = 300;
	public static final String ZERO_STRING = "0";
	public static final String ONE_STRING = "1";
	public static final String TWO_STRING = "2";
	public static final String THREE_STRING = "3";
	public static final String AUTHENTICATION_FAILED = "Authentication Failed";
	public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String JPG_FORMAT = "JPG";
	public static final String JPEG_FORMAT = "JPEG";
	public static final String PERIOD = ".";
	public static final String DEFAULT_ZONE = "Asia/Calcutta";
	public static final String PROFILE_IMAGE_NAME = "profile.jpg";
	public static final String QRCODE_IMAGE_NAME = "qrcode.png";
	public static final Byte ENABLE = 1;
	public static final Byte DISABLED = 2;
	public static final String S3_QR_ENDPOINT = "https://agnext-jasmine.s3.us-east-2.amazonaws.com/";
	public static final int ZERO = 0;
	public static final Integer RECORD_LIMIT = 10;
	public static final String SUCCESS_JSON = "{\"status\":\"success\"}";
	public static final String DATE_FORMAT = "MM/dd/yyyy";
	public static final String IAM_ACCESS_URL = "http://13.71.36.247:9982/oauth/token?grant_type=authorization_code&code=";
	public static final String CLIENT_ID_WEB = "clientId";
	public static final String PASSWORD_WEB = "secret";
	public static final String CLIENT_ID_MOBILE = "client-mobile";
	public static final String PASSWORD_MOBILE = "secret";
	public static final String AUTHORIZATION = "Authorization";
	public static final String BASIC = "Basic";
	public static final String BEARER = "Bearer";
	public static final String IAM_USER_ACCESS_URL = "http://13.71.36.247:9982/api/user/";
	public static final Integer RECORD_LIMIT_100 = 100;
	public static final String INVENTORY = "Inventory";
	public static final String SCAN_ID_NOT_EXIST_CODE = "12055";
	public static final String SCAN_ID_NOT_EXIST = "Scan id does not exist ";
	public static final String EXECUTING = "executing {} method";
	public static final String MONTHDATEYEAR_FORMAT = "MM-dd-yyyy HH:mm";
	public static final String DATEMONTHYEAR_FORMAT = "dd-MM-yyyy hh:mm a";
	public static final String NOTIFICATION_SERVER_HOST_URL = "http://23.98.216.140:9456/notification";
	public static final String ARYA_SERVER_KEY = "scan_qx_1.0";
	public static final Long SERVICE_PROVIDER_CUSTOMER_TYPE_ID = 1L;
	public static final String DATETIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
	public static final String DATE_FORMAT_NEW = "MM-dd-yyyy";
	public static final String WEIGHT_UNIT = "Tons";
	public static final String COFCO = "COFCO";
	public static final String NAFED = "NAFED";

	public enum STATUS {
		// USER AND FAQ(Active and InActive)
		INITIATED(1L, "initiated"), DISPATCHED(2L, "dispacted"), RECEIVED(3L, "received"), SHIPPED(4L, "SHIPPED"),
		DELETED(5L, "deleted"), COMPLETED(6L, "completed"), FUTUREUSE(7L, "futureuse"), ACTIVE(8L, "active"),
		INACTIVE(9L, "inactive"), SUSPENDED(10L, "suspended"), BLOCKED(11L, "blocked"), BARRED(12L, "barred"),
		PUBLISHED(13L, "published"), INPROGRESS(14L, "inprogress");

		public static String getAbbr(Long id) {
			for (STATUS e : STATUS.values()) {
				if (e.id == id) {
					return e.abbr;
				}
			}
			return "";// not found
		}

		private String abbr;

		private Long id;

		private STATUS(Long id, String abbr) {
			this.id = id;
			this.abbr = abbr;
		}

		public String getAbbr() {
			return this.abbr;
		}

		public Long getId() {
			return this.id;
		}

		@Override
		public String toString() {
			return this.abbr;
		}
	}
	
	public enum URL {

	    COFCO("http://cofco.qualix.ai", "COFCO"), NAFED("http://visio.qualixag.club", "NAFED");

	    private final String url;
	    private final String id;

	    private static final Map<String, String> MAP = new HashMap<String, String>();
	    static {
	        for (URL s : URL.values()) {
	            MAP.put(s.url, s.id);
	        }
	    }

	    private URL(String url, String id) {
	        this.url = url;
	        this.id = id;
	    }                                                                                                                               
	                                                                                                                             	    
	    public String getUrl() {
	        return url;
	    }

	    public String getId() {
	        return id;
	    }

	    public static String getIdUsingUrl(String url){
		return MAP.get(url);
	    }
	}

	public interface ErrorMessage {

		String PERMISSION_CODE_ALREADY_EXIST = "Permission code already exist";
		String ROLE_CODE_ALREADY_EXIST = "Role code already exist";
		String INVALID_STATUS = "Invalid status";
		String ROLE_CODE_INVALID_MAX_LENGTH = "Max length for role code is 45";
		String ROLE_DESC_INVALID_MAX_LENGTH = "Max length for role desciption is 45";
		String PERMISSION_ID_NOT_EXIST = "Invalid permission";
		String ROLE_NOT_FOUND = "Role not found";
		String PERMISSIONS_ID_NOT_EXIST = "Permissions not found against ids : ";
		String CUSTOMER_ID_NOT_EXIST = "Customer not found against ids :";
		String MISSING_AUTHORIZATION = "Authorization missing";
		String ADDRESS_ID_NOT_EXIST = "Address type id not found against ids :";
		String ROLES_ID_NOT_EXIST = "Roles not found against ids : ";
		String CUSTOMER_TYPE_ID_NOT_FOUND = "Customer type not found against ids : ";
		String USER_ID_NOT_EXIST = "User not found against ids :";
		String CUSTOMER_NAME_INVALID_MAX_LENGTH = "Max length for customer name is 255";
		String CUSTOMER_NAME_INVALID_CHARACTERS = "Invalid name only alphabets, space and dot allowed";
		String CUSTOMER_NAME_IS_MANDATORY = "customer name is mandatory";
		String EMAIL_INVALID = "email invalid";
		String CONTACT_NUMBER_INVALID = "Phone Number must be in the form XXXXXXXXXX";
		String GST_NUMBER_INVALID = "Please specify a valid GSTTIN Number";
		String PAN_NUMBER_NUMBER_INVALID = "Pan number invalid";
		String ADDRESS_LINE_INVALID = "Address type invalid eg. #1, North Street, Chennai - 11 ";
		String ADDRESS_LINE_MANDATORY = "Address line is mandatory";
		String COUNTRY_INVALID = "Please select country name";
		String STATE_INVALID = "Please select state name";
		String CITY_INVALID = "Please select city name";
		String ZIP_CODE_MANDATORY = "Zip code is mandatory";
		String ZIP_CODE_INVALID = "12025";
		String BANK_NAME_INVALID_MAX_LENGTH = "Max length for bank name is 255";
		String BANK_BRANCH_CODE_INVALID = "Bank branch or ifsc code is invalid";
		String BANK_ACCOUNT_NUMBER_INVALID = "Bank account number invalid";
		String BANK_ID_NOT_EXIST = "Bank id doesn't exist";
		String BILLING_ID_NOT_EXIST = "Billing id doesn't exist";
		String EMAIL_ALREADY_EXIST = "Email already exist";
		String USER_NAME_IS_MANDATORY = "User first name is mandatory";
		String USER_LAST_NAME_IS_MANDATORY = "User last name is mandatory";
		String USER_CONTACT_NUMBER_IS_MANDATORY = "User contact number is mandatory";
		String USER_EMAIL_IS_MANDATORY = "User email is mandatory";
		String USER_ROLE_IS_MANDATORY = "User role is mandatory";
		String USER_ADDRESS_IS_MANDATORY = "User address is mandatory";
		String FIRST_NAME_INVALID_MAX_LENGTH = "Max length for first name is 255";
		String LAST_NAME_INVALID_MAX_LENGTH = "Max length for last name is 255";
		String USER_NOT_AUTHORIZED = "You are not authorized for this request";
		String STATUS_ID_NOT_EXIST = "Status id doesn't exist";
		String OLD_PASSWORD_REQUIRED = "Current password is required";
		String NEW_PASSWORD_REQUIRED = "New password is required";
		String OLD_NEW_PASSWORD_SAME = "New password cannot be same as previous password";
		String INVALID_PASSWORD_ERROR = "Invalid password, please use 8 to 20 characters with atleast one digit, one lower case character, one uppercase character and one special characters from @ . # $ !";
		String INVALID_CURRENT_PASSWORD_ERROR = "Current password is incorrect";
		String GST_ALREADY_EXIST = "Gst already exist";
		String PAN_ALREADY_EXIST = "Pan already exist";
		String CUSTOMER_ID_NOT_PROVIDED = "Customer Id has not been provided";
		String NO_RECORD_FORUND = "No record has been found";
		String DATE_NOT_FOUND = "Plz select a date range to view the result";
		String COMMODITY_CATEGORY_ID_NOT_EXIST = "Entered category does not exists ";
		String UNIT_MISMATCH_MSG = "Unit provided in the request does not match with available units, Plz provide one"
				+ " of ' g for Grams, kg for Kilograms , mg for Milligrams , tonne for Tons' or l for Litre";
		String TYPE_ID_NOT_EXIST = "Type id does not exists";
		String SERIAL_NUMBER_EXIST = "Serial number exists ";
		String DEVICE_SOS_GREATER_THAN_EQUAL_TO_EOS_MESSAGE = "Start of service is greater then End of Service";
		String COMMERCIAL_LOCATION_ID_NOT_EXIST = "Scan location does not exists";
		String DEVICE_TOKEN_IS_MISSING = "Device token is missing";
		String DEVICE_SERIAL_NUMBER_NOT_EXIST = "Device serial number does not exists";
		String USER_ID_IS_NOT_PRESENT_MESSAGE = " User id is not present ";
		String CUSTOMER_ID_IS_NOT_PRESENT_MESSAGE = "Customer Id is not present ";
		String NOT_ACCESSABLE_LOGIN_MESSAGE = " This module is not accessible to the user ";
		String SAMPLE_ID_EXISTS = "Sample ID already exists";
		String COMMODITY_NOT_MATCHED = "Commodity does not match with moisture meter result";
		String INVALID_ZIP_CODE = "Provided zipcode is not valid";
		String STATE_NOT_FOUND = "State is not registered for the state admin";
	}

	public interface ErrorCode {

		String PERMISSION_CODE_ALREADY_EXIST = "12001";
		String ROLE_CODE_ALREADY_EXIST = "12002";
		String INVALID_STATUS = "12003";
		String ROLE_CODE_INVALID_MAX_LENGTH = "12004";
		String PERMISSION_ID_NOT_EXIST = "12005";
		String ROLE_NOT_FOUND = "12006";
		String PERMISSIONS_ID_NOT_EXIST = "12007";
		String CUSTOMER_ID_NOT_EXIST = "12008";
		String MISSING_AUTHORIZATION = "AUTH401";
		String ADDRESS_ID_NOT_EXIST = "12009";
		String ROLES_ID_NOT_EXIST = "12010";
		String CUSTOMER_TYPE_ID_NOT_FOUND = "12011";
		String USER_ID_NOT_EXIST = "12012";
		String CUSTOMER_NAME_INVALID_MAX_LENGTH = "12012";
		String CUSTOMER_NAME_INVALID_CHARACTERS = "12013";
		String CUSTOMER_NAME_IS_MANDATORY = "12014";
		String EMAIL_INVALID = "12015";
		String CONTACT_NUMBER_INVALID = "12016";
		String GST_NUMBER_INVALID = "12017";
		String PAN_NUMBER_NUMBER_INVALID = "12018";
		String ADDRESS_LINE_INVALID = "12019";
		String ADDRESS_LINE_MANDATORY = "12020";
		String COUNTRY_INVALID = "12021";
		String STATE_INVALID = "12022";
		String CITY_INVALID = "12023";
		String ZIP_CODE_MANDATORY = "12024";
		String ZIP_CODE_INVALID = "12025";
		String BANK_NAME_INVALID_MAX_LENGTH = "12026";
		String BANK_BRANCH_CODE_INVALID = "12027";
		String BANK_ACCOUNT_NUMBER_INVALID = "12028";
		String BANK_ID_NOT_EXIST = "12029";
		String BILLING_ID_NOT_EXIST = "12030";
		String EMAIL_ALREADY_EXIST = "12031";
		String USER_NAME_IS_MANDATORY = "12032";
		String USER_LAST_NAME_IS_MANDATORY = "12033";
		String USER_CONTACT_NUMBER_IS_MANDATORY = "12034";
		String USER_EMAIL_IS_MANDATORY = "12035";
		String USER_ROLE_IS_MANDATORY = "12036";
		String USER_ADDRESS_IS_MANDATORY = "12037";
		String FIRST_NAME_INVALID_MAX_LENGTH = "12038";
		String LAST_NAME_INVALID_MAX_LENGTH = "12039";
		String USER_NOT_AUTHORIZED = "12040";
		String STATUS_ID_NOT_EXIST = "12041";
		String OLD_PASSWORD_REQUIRED = "12042";
		String NEW_PASSWORD_REQUIRED = "12043";
		String OLD_NEW_PASSWORD_SAME = "12044";
		String INVALID_PASSWORD_ERROR = "12045";
		String INVALID_CURRENT_PASSWORD_ERROR = "12046";
		String GST_ALREADY_EXIST = "12047";
		String PAN_ALREADY_EXIST = "12048";
		String CUSTOMER_ID_NOT_PROVIDED = "12049";
		String NO_RECORD_FORUND = "12050";
		String DATE_NOT_FOUND = "12051";
		String COMMODITY_CATEGORY_ID_NOT_EXIST = "12052";
		String UNIT_MISMATCH_CODE = "12053";
		String TYPE_ID_NOT_EXIST = "12054";
		String DEVICE_SOS_GREATER_THAN_EQUALS_TO_EOS = "12055";
		String SERIAL_NUMBER_EXIST = "12056";
		String COMMERCIAL_LOCATION_ID_NOT_EXIST = "12057";
		String DEVICE_TOKEN_IS_MISSING = "12058";
		String DEVICE_SERIAL_NUMBER_NOT_EXIST = "12059";
		String USER_ID_IS_NOT_PRESENT = "12060";
		String CUSTOMER_ID_IS_NOT_PRESENT = "12061";
		String NOT_ACCESSABLE_LOGIN = "12062";
		String SAMPLE_ID_EXISTS = "12063";
		String COMMODITY_NOT_MATCHED = "12064";
		String STATE_ADMIN_NOT_PRESENT = "12065";
		String INVALID_ZIP_CODE = "12066";
		String STATE_NOT_FOUND = "12067";
	}

	public interface FieldLength {

		int ROLE_CODE_MAX_LENGTH = 45;
		int ROLE_DESC_MAX_LENGTH = 45;
		int PERMISSION_CODE_MAX_LENGTH = 45;
		int PERMISSION_DESC_MAX_LENGTH = 45;
		int CUSTOMER_NAME_MAX_LENGTH = 255;
		int CUSTOMER_NAME_MIN_LENGTH = 3;
		int BANK_NAME_MAX_LENGTH = 255;
		int BANK_NAME_MIN_LENGTH = 3;
		int FIRST_NAME_MIN_LENGTH = 3;
		int FIRST_NAME_MAX_LENGTH = 255;
		int LAST_NAME_MIN_LENGTH = 3;
		int LAST_NAME_MAX_LENGTH = 255;
		int PASSWORD_MIN_LENGTH = 3;
		int PASSWORD_MAX_LENGTH = 20;

	}

	public interface RequestParams {

		String HEADER_AUTHORIZATION = "Authorization";
		String HEADER_AUTHORIZATION_BEARER = "Bearer";

	}

	public interface CustomerType {

		String CUSTOMER = "CUSTOMER";
		String SERVICE_PROVIDER = "SERVICE_PROVIDER";
		String LAB = "LAB";
		String VENDOR = "VENDOR";
		String USER = "USER";
		String PARTNER = "PARTNER";
		String CLIENT = "CLIENT";
		String OPERATOR = "OPERATOR";
		String STATE_ADMIN = "state_admin";
		String CUSTOMER_ADMIN = "customer_admin";

	}

	public interface AddressType {

		String OFFICE = "OFFICE";

	}

	public interface Patterns {

		Pattern PASSWORD = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,20})");
	}

	public interface DeviceStatus {

		String ACTIVE = "ACTIVE";
		String PROVISIONED = "PROVISIONED";
		String INITIATED = "INITIATED";
	}

	public interface DeviceType {

		String PRO = "Pro";
		String NANO = "Nano";
		String SENSOR = "Sensor";
	}

	public interface BearerType {

		String WEB = "web";
		String MOBILE = "mobile";
	}

	public interface Roles {

		String CUSTOMER_ADMIN = "customer_admin";
		String CLIENT = "sub_client";
		String OPERATOR = "operator";

	}

	public interface CustomVariety {

		Long CUSTOM_VARIETY_ID = 17L;

	}

	public enum ANALYTICS {
		MOISTURECONTENT(1L, "moisturecontent"), ADMIXTURE(2L, "admixture"), FOREIGNMATTER(3L, "foreignmatter"),
		DAMAGED(4L, "damaged"), WEEVILLED(5L, "weevilled"), SHRIVELLEDANDIMMATURE(6L, "shrivelled & immature"),
		SLIGHTLYDAMAGED(7L, "slightlydamaged"), SHELLING(8L, "shelling"), PODSOFOTHERVAR(9L, "podsofothervariety"),
		DAMAGEDANDWEEVILLED(10L, "damagedandweevilled"), IMMATURE(11L, "immature"),
		OTHERFOODGRAINS(12L, "otherfoodgrains"), SMALLATROPHIEDSEEDS(13L, "smallatrophiedseeds"), SPLITCRACKED(14L, "splitcracked");

		public static String getAbbr(Long id) {
			for (ANALYTICS e : ANALYTICS.values()) {
				if (e.id == id) {
					return e.abbr;
				}
			}
			return "";// not found
		}

		private String abbr;

		private Long id;

		private ANALYTICS(Long id, String abbr) {
			this.id = id;
			this.abbr = abbr;
		}

		public String getAbbr() {
			return this.abbr;
		}

		public Long getId() {
			return this.id;
		}

		@Override
		public String toString() {
			return this.abbr;
		}
	}

	public enum STANDARDIZEDANALYTICS {
		MOISTURECONTENT(1L, "moisturecontent"), ADMIXTURE(2L, "admixture"), FOREIGNMATTER(3L, "foreignmatter"),
		DAMAGED(4L, "damaged"), WEEVILLED(5L, "weevilled"), SHRIVELLEDANDIMMATURE(6L, "shrivelledandimmature"),
		SLIGHTLYDAMAGED(7L, "slightlydamaged"), SHELLING(8L, "shelling"), PODSOFOTHERVAR(9L, "podsofothervariety"),
		DAMAGEDANDWEEVILLED(10L, "damagedandweevilled"), IMMATURE(11L, "immature"),
		OTHERFOODGRAINS(12L, "otherfoodgrains"), SMALLATROPHIEDSEEDS(13L, "smallatrophiedseeds"), SPLITCRACKED(14L, "splitcracked");

		public static String getAnalyticsById(Long id) {
			for (STANDARDIZEDANALYTICS e : STANDARDIZEDANALYTICS.values()) {
				if (e.id == id) {
					return e.analytics;
				}
			}
			return "";// not found
		}

		private String analytics;

		private Long id;

		private STANDARDIZEDANALYTICS(Long id, String abbr) {
			this.id = id;
			this.analytics = abbr;
		}

		public String getAnalytics() {
			return this.analytics;
		}

		public Long getId() {
			return this.id;
		}

		@Override
		public String toString() {
			return this.analytics;
		}
	}

	public enum APPVERSIONSTATUS {
		// USER AND FAQ(Active and InActive)
		ACTIVE(0L, "Active"), INACTIVE(1L, "inactive"), SUSPENDED(2L, "suspended"), BLOCKED(3L, "blocked"),;

		public static String getAbbr(Long id) {
			for (APPVERSIONSTATUS e : APPVERSIONSTATUS.values()) {
				if (e.id == id) {
					return e.abbr;
				}
			}
			return "";// not found
		}

		private String abbr;

		private Long id;

		private APPVERSIONSTATUS(Long id, String abbr) {
			this.id = id;
			this.abbr = abbr;
		}

		public String getAbbr() {
			return this.abbr;
		}

		public Long getId() {
			return this.id;
		}

		@Override
		public String toString() {
			return this.abbr;
		}
	}
}
