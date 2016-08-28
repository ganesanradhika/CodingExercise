package com.modis.rest.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Service;

import com.modis.exception.InvalidDateException;

/**
 * Implements {@link DateValidationService} Checks the following things on the date passed by users -
 * 
 * Assumption - The date passed is date of birth (DoB).
 * 
 * 1. Date should be in format MM-DD-YYYY.
 * 2. Date should not be in future.
 * 3. Checks whether the DoB is of minor or not.
 * 
 * @author radyog
 *
 */
@Service
public class DateValidationServiceImpl implements DateValidationService {

	public boolean isMinor(String date) throws InvalidDateException {
		
		if(date == null || date.isEmpty()){
			throw new InvalidDateException(InvalidDateException.INVALID_DATE);
		}
		
		LocalDate userDate = LocalDate.now();
		// Is Date in expected format ?
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-uuuu");
		try{
			userDate = LocalDate.parse(date, formatter);
		}catch(DateTimeParseException dtpe){
			throw new InvalidDateException(InvalidDateException.INVALID_FORMAT);
		}
		
		// Check if the date is not in future
		LocalDate today = LocalDate.now();
		if(userDate.isAfter(today)){
			throw new InvalidDateException(InvalidDateException.FUTURE_DATE);
		}
		// check if the DOB is < 18
		// Subtract 18 years from the user date and if that result falls before user date, 
		// the person is below 18
		boolean isMinor = true;
		LocalDate minorDate = today.minusYears(18);
		if(userDate.isBefore(minorDate)){
			isMinor = false;
		}
		
		return isMinor;

	}

}
