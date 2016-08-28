package com.modis.test;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.modis.exception.InvalidDateException;
import com.modis.rest.service.DateValidationService;
import com.modis.rest.service.ListNames;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
@EnableWebMvc
public class RequestControllerTest {

	@Autowired
    private WebApplicationContext ctx;
	
	@Mock
	List<String> nameLst = new ArrayList<>();
	
	@Autowired
	DateValidationService dvsMock;
	
	@Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }
	

	@Test
	public void test_dateIsEmpty_FALSE() throws Exception{
		DateValidationService mock = org.mockito.Mockito.mock(DateValidationService.class);
		String userDate = "";
		
		when(mock.isMinor(userDate));
		mockMvc.perform(get("/validateDate?dob="+userDate)).andExpect(model()
				.attribute("result", InvalidDateException.INVALID_DATE));;
	}
	
	private MockMvc mockMvc;
	
	@Test
	public void test_dateValidBorderCondition_18Yesterday_FALSE() throws Exception{
		
		DateValidationService mock = org.mockito.Mockito.mock(DateValidationService.class);
		LocalDate ld = LocalDate.of(2017,9,05);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-uuuu");
		String userDate = ld.format(formatter);
		when(mock.isMinor(userDate)).thenReturn(Boolean.FALSE);
		
		mockMvc.perform(get("/validateDate?dob="+userDate)).andExpect(model()
				.attribute("result", InvalidDateException.FUTURE_DATE));
		
		 
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void test_ListNames_TRUE() throws Exception{
		
		ListNames mock = org.mockito.Mockito.mock(ListNames.class);
		when(mock.getNames()).thenReturn(nameLst);
	
		MvcResult result = mockMvc.perform(get("/Names")).andReturn();
		Assert.assertTrue(((ArrayList)result.getRequest().getAttribute("nameList")).size() == 5);
		
	}
	
	
}


