package com.modis.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.modis.exception.InvalidDateException;
import com.modis.rest.service.DateValidationService;

/**
 * Covers all scenarios related to date validation comprehensively.
 * @author radyog
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
public class DateValidationServiceImplTest{

	@Autowired
	DateValidationService dvs;
	
	
	@Test
	public void test_dateValidBorderCondition_18Yesterday_FALSE() throws InvalidDateException{
		final LocalDate todayMinus18Years = LocalDate.now().minusYears(18).minusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-uuuu");
		Assert.assertFalse(dvs.isMinor(todayMinus18Years.format(formatter)));
	}
	
	@Test
	public void test_dateValidBorderCondition_18Today_TRUE() throws InvalidDateException{
		final LocalDate todayMinus18Years = LocalDate.now().minusYears(18);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-uuuu");
		Assert.assertTrue(dvs.isMinor(todayMinus18Years.format(formatter)));
	}
	
	@Test
	public void test_dateValidBorderCondition_18Tomorrow_TRUE() throws InvalidDateException{
		final LocalDate todayMinus18Years = LocalDate.now().minusYears(18).plusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-uuuu");
		Assert.assertTrue(dvs.isMinor(todayMinus18Years.format(formatter)));
	}
	
	@Test
	public void test_dateValidAndNotMinor_FALSE() throws InvalidDateException{
		Assert.assertFalse(dvs.isMinor("09-05-1985"));
	}
	
	@Test
	public void test_dateValidAndMinor_TRUE() throws InvalidDateException{
		Assert.assertTrue(dvs.isMinor("09-05-2005"));
	}
	
	@Test
	public void test_dateInvalid_FALSE() throws InvalidDateException{
		Assert.assertFalse(dvs.isMinor("02-30-1985"));
	}

	@Test
	public void test_dateIsGarbageVal_FALSE() throws InvalidDateException{

		try{
			Assert.assertFalse(dvs.isMinor("%%%&&&&"));
		}catch(InvalidDateException ide){
			
		}
	}
}
