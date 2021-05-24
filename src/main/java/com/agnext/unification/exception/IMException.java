/*
 * @author Vishal B.
 * @version 1.0
 */
package com.agnext.unification.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class IMException.
 */
public class IMException extends Exception{
	
 
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4562086856377484853L;
	
	/** The error code. */
	@JsonProperty("error-code")
	String errorCode;
	
	/**
	 * Instantiates a new IM exception.
	 *
	 * @param errorCode the error code
	 * @param errorMessage the error message
	 */
	public IMException(String errorCode,String errorMessage){
		super(errorMessage);
		this.errorCode=errorCode;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@JsonProperty("error-message")
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
