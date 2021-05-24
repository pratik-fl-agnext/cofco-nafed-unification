package com.agnext.unification.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.commons.text.RandomStringGenerator.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.agnext.unification.common.Constants;
import com.agnext.unification.exception.IMException;
import com.agnext.unification.model.WeightConverterModel;

public class Utility {

    private static Logger logger = LoggerFactory.getLogger(Utility.class);
    private static Marker marker = MarkerFactory.getMarker("Utility");

    public static String getContentTypeByFileName(String fileName) {
	if (fileName.contains(Constants.JPEG_FORMAT)) {
	    return "image/jpeg";
	} else if (fileName.toUpperCase().contains(Constants.JPG_FORMAT)) {
	    return "image/jpg";
	}
	return "";
    }

    /* public static Double formatDecimal(Double value) { if (value != null) {
     * String sValue = String.valueOf(value); String[] spliter =
     * sValue.split("\\."); String val = spliter[1]; if (val.length() > 2) { val
     * = val.substring(0, 2); } Double finalVal = Double.valueOf(spliter[0] +
     * "." + val); return finalVal; } return 0.0; } */

    public static BigDecimal formatDecimal(BigDecimal value) {
	if (value != null) {
	    if (isValueLessThenOne(value)) {
		BigDecimal absValue = value.abs();
		int precision = 0;
		while (absValue.compareTo(BigDecimal.ONE) < 0) {
		    absValue = absValue.multiply(new BigDecimal(10));
		    precision++;
		}
		return value.setScale(precision, RoundingMode.HALF_EVEN);
	    } else {
		return value.setScale(2, RoundingMode.DOWN);
	    }
	} else {
	    return new BigDecimal(0.0);
	}
    }

    public static boolean isValueLessThenOne(BigDecimal value) {
	return value != null && value.compareTo(BigDecimal.ZERO) > 0 && value.compareTo(BigDecimal.ONE) < 0;
    }

    public static BigDecimal getBigDecimalValue(Object value) {
	BigDecimal myValue = new BigDecimal(0.0);
	if (value != null && value instanceof Double) {
	    myValue = new BigDecimal((Double) value);
	} else if (value != null && value instanceof Integer) {
	    myValue = new BigDecimal((Integer) value);
	} else {
	    myValue = (BigDecimal) value;
	}
	return myValue;
    }

    /**
     * Get Current date
     * 
     * @return java.sql.Date current date
     */
    public static Date getCurrentDate() {

	LocalDate ldate = LocalDate.now();
	return convertToSqlDate(
		java.util.Date.from(ldate.atStartOfDay().atZone(ZoneId.of(Constants.DEFAULT_ZONE)).toInstant()));
    }

    /**
     * Converts java.util.Date to java.sql.Date
     * 
     * @param utilDate
     *            java.util.Date to be converted
     * @return converted java.sql.Date
     */
    public static Date convertToSqlDate(java.util.Date utilDate) {

	return new Date(utilDate.getTime());
    }

    public static LocalDateTime getCurrentDateTime() {
	logger.debug(marker, Constants.EXECUTING, "getCurrentDateTime()");
	return LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of(Constants.DEFAULT_ZONE))
		.toLocalDateTime();
    }

    /**
     * Convert millis to LocalDateTime
     * 
     * @param millis
     * @return
     */
    public static LocalDateTime millsToLocalDateTime(long millis) {
	Instant instant = Instant.ofEpochMilli(millis);
	LocalDateTime date = instant.atZone(ZoneId.systemDefault())
		.withZoneSameInstant(ZoneId.of(Constants.DEFAULT_ZONE)).toLocalDateTime();
	return date;
    }

    /**
     * Convert format of the date according to the 'format' passed
     * 
     * @param obj
     *            java.util.Date object to be converted
     * @param format
     *            converts in this format
     * @return string representation of converted date
     */
    public static String formatDateToString(java.util.Date obj, String format) {
	SimpleDateFormat sdf = new SimpleDateFormat(format);
	return sdf.format(obj);
    }

    /**
     *
     * @param localDateTime
     * @return
     */
    public static String formatDateToString(LocalDateTime localDateTime) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATEMONTHYEAR_FORMAT);
	return localDateTime.format(formatter);
    }

    /**
     * Post Weight Convertor
     *
     * @param weight
     * @param Unit
     * @return
     * @throws IMException
     */
    public static WeightConverterModel postWeightConverter(BigDecimal weight, String Unit) throws IMException {
	WeightConverterModel weightModel = new WeightConverterModel();

	if (Unit != null && !Unit.isEmpty()) {
	    if (Unit.equalsIgnoreCase("tonne") || Unit.equalsIgnoreCase("kg") || Unit.equalsIgnoreCase("g")
		    || Unit.equalsIgnoreCase("mg") || Unit.equalsIgnoreCase("l") || Unit.equalsIgnoreCase("tons")
		    || Unit.equalsIgnoreCase("Tonnes") || Unit.equalsIgnoreCase("quintal")) {

		BigDecimal processedWeight = new BigDecimal(0.0);

		if (Unit.equalsIgnoreCase("tonne") || Unit.equalsIgnoreCase("tons")
			|| Unit.equalsIgnoreCase("Tonnes")) {
		    processedWeight = weight;
		    weightModel.setUnit("tons");
		} else if (Unit.equalsIgnoreCase("kg")) {
		    processedWeight = weight.divide(BigDecimal.valueOf(1000));
		    weightModel.setUnit("Kg");
		} else if (Unit.equalsIgnoreCase("mg")) {
		    processedWeight = weight.divide(BigDecimal.valueOf(1000000000));
		    weightModel.setUnit("mg");
		} else if (Unit.equalsIgnoreCase("l")) {
		    processedWeight = weight.divide(BigDecimal.valueOf(1000));
		    weightModel.setUnit("Litres");
		} else if (Unit.equalsIgnoreCase("g")) {
		    processedWeight = weight.divide(BigDecimal.valueOf(1000000));
		    weightModel.setUnit("Grams");
		} else if (Unit.equalsIgnoreCase("quintal")) {
		    processedWeight = weight;
		    weightModel.setUnit("quintals");
		}
		weightModel.setWeight(processedWeight);
		System.out.println(
			"Weight :  " + weightModel.getWeight() + " & " + "  Weight Unit : " + weightModel.getUnit());
	    } else {
		throw new IMException(Constants.ErrorCode.UNIT_MISMATCH_CODE, Constants.ErrorMessage.UNIT_MISMATCH_MSG);
	    }
	    return weightModel;
	} else {
	    weightModel.setWeight(weight);
	    weightModel.setUnit(Unit);
	    return weightModel;
	}
    }

    public static WeightConverterModel fetchWeightConverter(BigDecimal weight, String Unit) throws IMException {
	WeightConverterModel weightModel = new WeightConverterModel();
	logger.debug(" Unit : " + Unit);

	if (Unit != null && !Unit.isEmpty()) {
	    if (Unit.equalsIgnoreCase("tons") || Unit.equalsIgnoreCase("kg") || Unit.equalsIgnoreCase("grams")
		    || Unit.equalsIgnoreCase("mg") || Unit.equalsIgnoreCase("litres")
		    || Unit.equalsIgnoreCase("quintal")) {

		BigDecimal processedWeight = new BigDecimal(0.0);
		if (Unit.equalsIgnoreCase("tons")) {
		    processedWeight = weight;
		    weightModel.setWeight(processedWeight);
		    weightModel.setUnit("Tons");
		} else if (Unit.equalsIgnoreCase("kg")) {

		    processedWeight = weight.multiply(BigDecimal.valueOf(1000));
		    weightModel.setWeight(processedWeight);
		    weightModel.setUnit("Kg");
		} else if (Unit.equalsIgnoreCase("mg")) {
		    processedWeight = weight.multiply(BigDecimal.valueOf(1000000000));
		    weightModel.setWeight(processedWeight);
		    weightModel.setUnit("Mg");
		} else if (Unit.equalsIgnoreCase("litres")) {
		    processedWeight = weight.multiply(BigDecimal.valueOf(1000));
		    weightModel.setUnit("Litres");
		    weightModel.setWeight(processedWeight);
		} else if (Unit.equalsIgnoreCase("grams")) {
		    processedWeight = weight.multiply(BigDecimal.valueOf(1000000));
		    weightModel.setWeight(processedWeight);
		    weightModel.setUnit("Grams");
		} else if (Unit.equalsIgnoreCase("quintal")) {
		    processedWeight = weight;
		    weightModel.setWeight(processedWeight.setScale(2, BigDecimal.ROUND_HALF_UP));
		    weightModel.setUnit("quintals");
		}
	    } else {
		logger.error("&&&&&&&&&&&& Unit : " + Unit);
		throw new IMException(Constants.ErrorCode.UNIT_MISMATCH_CODE, Constants.ErrorMessage.UNIT_MISMATCH_MSG);
	    }
	    return weightModel;
	} else {
	    weightModel.setWeight(weight);
	    weightModel.setUnit(Unit);
	    return weightModel;
	}
    }

    /**
     * Get Current date time in String format
     *
     * @return LocalDateTime in String
     */
    public static String getCurrentMonthDateTimeString() {
	logger.debug(marker, Constants.EXECUTING, "getCurrentDateTimeString()");
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.MONTHDATEYEAR_FORMAT);
	return LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of(Constants.DEFAULT_ZONE))
		.toLocalDateTime().format(formatter);
    }

    /**
     *
     * @param date
     * @return
     */
    public static String formatLocalMonthDateTimeToString(LocalDateTime date) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.MONTHDATEYEAR_FORMAT);
	return date.format(formatter);
    }

    public static String formatDateToString1(Date date) {
	return formatDateToString(date, Constants.DATE_FORMAT);
    }

    public static Double formatDecimal1(Double value) {
	if (value != null) {
	    String sValue = String.valueOf(value);
	    String[] spliter = sValue.split("\\.");
	    String val = spliter[1];
	    if (val.length() > 2) {
		val = val.substring(0, 1);
	    }

	    Double finalVal = Double.valueOf(spliter[0] + "." + 0);
	    return finalVal;
	}
	return 0.0;
    }

    /**
     *
     * @param length
     * @return
     */
    public static String randomStringGenerator(int length) {
	final RandomStringGenerator randomStringGenerator = new Builder().withinRange('0', 'z')
		.filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build();
	String refId = randomStringGenerator.generate(length);
	return refId;
    }

    /**
     * Convert format of the local date time to
     * {@link Constants#DATETIME_FORMAT}
     * 
     * @param date
     *            LocalDateTime object to be converted
     * @return string representation of converted date
     * 
     * @see #formatLocalDateToString(LocalDate)
     */
    public static String formatLocalDateTimeToString(LocalDateTime date) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATETIME_FORMAT);
	return date.format(formatter);
    }

    public static Double formatDecimal(Double value) {
	if (value != null) {
	    String sValue = String.valueOf(value);
	    String[] spliter = sValue.split("\\.");
	    String val = spliter[1];
	    if (val.length() > 2) {
		val = val.substring(0, 2);
	    }
	    System.out.println(" The value which we receive is : " + value + "  &  First value : " + spliter[0]
		    + " & Second value :  " + val + "  which should be :  " + spliter[0] + "." + val);
	    Double finalVal = Double.valueOf(spliter[0] + "." + val);
	    return finalVal;
	}
	return 0.0;
    }

    public static Long convertLocalDateTimeIntoEpochMilli(LocalDateTime ldt) throws ParseException {
	Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
	long timeInMillis = instant.toEpochMilli();
	return timeInMillis;
    }

	public static String setRandomPassword(String password) {
		String encodedPassword = "";
		if (password != null && !password.equals("") && !password.isEmpty()) {
			PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			encodedPassword = (encoder.encode(password));
		}
		return encodedPassword;
	}
	
	  /**
     * Get Current date
     * 
     * @return java.sql.Date current date
     */
    public static Date getCurrentDate(Long epochMillis) {
    	LocalDate ld = Instant.ofEpochMilli(epochMillis).atZone(ZoneId.systemDefault()).toLocalDate();
//	LocalDate ldate = LocalDate.now();
	return convertToSqlDate(
		java.util.Date.from(ld.atStartOfDay().atZone(ZoneId.of(Constants.DEFAULT_ZONE)).toInstant()));
    }
    
    public static BigDecimal convertQuintalsToTons(BigDecimal weight) throws IMException {
    	BigDecimal response= new BigDecimal(0.0);
    	if(weight !=null && !weight.equals(0)) {
    		response=formatDecimal(weight.divide(new BigDecimal(10)));
    	}
    	return response;
        }
}
