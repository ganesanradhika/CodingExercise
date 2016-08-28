package com.modis.exception;

/**
 * User Exception to denote the invalid scenarios based on business logic.
 * 
 * @author radyog
 *
 */
public class InvalidDateException extends RuntimeException {

	private static final long serialVersionUID = 6227622816021881175L;
	
	public static final String INVALID_FORMAT = "Invalid Date Or Incorrect date format (Expected - MM-DD-YYYY)";
	
	public static final String FUTURE_DATE = "Invalid Date. Cannot be a future date.";
	
	public static final String INVALID_DATE = "Invalid Date.";
	
	public InvalidDateException(String message){
		this.message = message;
	}
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
