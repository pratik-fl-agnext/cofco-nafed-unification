package com.agnext.unification.common;

import java.util.Collection;
import java.util.Map;

public class CommonUtil {

	public static boolean verifyMaxLength(String value, int maxLength) {
		if(!isEmpty(value)) 
			return value.length()<=maxLength;
		
		return false;
	}
	
	public static boolean verifyMinLength(String value, int minLength) {
		if(!isEmpty(value)) 
			return value.length()>=minLength;
		
		return false;
	}
	public static boolean verifyMinMaxLength(String value, int minLength,int maxLength) {
		if(!isEmpty(value)) 
			return (verifyMaxLength(value, maxLength)&&verifyMinLength(value, minLength));
		
		return false;
	}

	public static boolean isEmpty(String value) {
		return value == null || value.trim().length() == 0 || value.equals("null") || value.equals("");
	}
	
	public static boolean isEmpty(Map<Object, Object> map) {
		return map == null || map.size() == 0;
	}

	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

}
