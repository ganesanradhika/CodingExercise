package com.modis.rest.service;

import com.modis.exception.InvalidDateException;

/**
 * Service exposed to users for DOB validation
 * @author radyog
 *
 */
public interface DateValidationService {
	
	String IS_MINOR = "This person is a minor";
	String IS_NOT_MINOR = "This person is not a minor";

	boolean isMinor(String date) throws InvalidDateException;
	
}
