package com.agnext.unification.common;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Class to send response back to client
 * 
 * @author PIYUSH RANJAN.
 *
 */
@SuppressWarnings("rawtypes")
@JsonInclude(Include.NON_NULL)
public class Response {

    public static Response of(final String success, final String message) {
        return new Response(success, message, null, null, null);
    }

    public static Response of(int httpCode, final String success, final String message) {
        return new Response(httpCode, success, message, null, null, null);
    }

    public static Response of(final String success, final String message, final Collection data) {
        return new Response(success, message, null, data, null);
    }

    public static Response of(final String success, final String message, final Collection data, final Integer count) {
        return new Response(success, message, null, data, count);
    }

    public static Response of(final String success, final String message, final String devMessage, final Collection data) {
        return new Response(success, message, devMessage, data, null);
    }

    public static Response of(final String success, final String message, final String devMessage, final Collection data, final Integer count) {
        return new Response(success, message, devMessage, data, count);
    }

    public static Response of(int httpCode, final String success, final String message, final String devMessage, final Collection data, final Integer count) {
        return new Response(httpCode, success, message, devMessage, data, count);
    }

    public static Response of(final String success, final String saveSuccessMessage, final Collection data, final int size, boolean status) {
        return new Response(success, saveSuccessMessage, null, data, size, status);

    }
    public static Response of(final String success, final String saveSuccessMessage, final Collection data, final int size, boolean status, Long totalRecords) {
        return new Response(success, saveSuccessMessage, null, data, size, status,totalRecords);

    }
    public static Response of(final String success, final String saveSuccessMessage, Object payload, boolean status) {
        return new Response(success, saveSuccessMessage, null, null, payload, null, status);

    }

    private Object payload;
    private final String success;
    private boolean status;
    private final String message;

    private final String devMessage;

    private final Collection data;

    private final Integer count;

    private final String timestamp;

    private int httpCode;
    
    private Long totalRecords;

    protected Response(final String success, final String message, final String devMessage, final Collection data, final Integer count) {
        this.success = success;
        this.message = message;
        this.devMessage = devMessage;
        this.data = data;
        this.count = count;
        this.timestamp =null;
    }

    protected Response(int httpCode, final String success, final String message, final String devMessage, final Collection data, final Integer count) {
        this.httpCode = httpCode;
        this.success = success;
        this.message = message;
        this.devMessage = devMessage;
        this.data = data;
        this.count = count;
        this.timestamp = null;
    }

    protected Response(final String success, final String message, final String devMessage, final Collection data, final Integer count, boolean status) {
        this.success = success;
        this.message = message;
        this.devMessage = devMessage;
        this.data = data;
        this.count = count;
        this.status = status;
        this.timestamp = null;
    }

    protected Response(final String success, final String message, final String devMessage, final Collection data, final Integer count, boolean status, Long totalRecords ) {
        this.success = success;
        this.message = message;
        this.devMessage = devMessage;
        this.data = data;
        this.count = count;
        this.status = status;
        this.totalRecords= totalRecords;
        this.timestamp = null;
    }
    
    protected Response(final String success, final String message, final String devMessage, final Collection data, Object payload, final Integer count, boolean status) {
        this.success = success;
        this.message = message;
        this.devMessage = devMessage;
        this.payload = payload;
        this.count = count;
        this.data = data;
        this.status = status;
        this.timestamp = null;
    }

    
    public Integer getCount() {
        return count;
    }

    public Collection getData() {
        return this.data;
    }

    public String getDevMessage() {
        return this.devMessage;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSuccess() {
        return this.success;
    }

    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @return the httpCode
     */
    public int getHttpCode() {
        return httpCode;
    }

    
  

	/**
	 * @return the totalRecords
	 */
	public Long getTotalRecords() {
		
		return totalRecords;
	}

	/**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }
    /**
     * @return the payLoad
     */
    public Object getPayload() {
        return payload;
    }

}
