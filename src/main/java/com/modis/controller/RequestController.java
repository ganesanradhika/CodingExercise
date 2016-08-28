package com.modis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modis.exception.InvalidDateException;
import com.modis.rest.service.DateValidationService;
import com.modis.rest.service.ListNames;

/**
 * Controller class in Spring MVC
 * @author radyog
 *
 */

@Controller
public class RequestController {

	List<String> nameList ;
	
	@Autowired
	DateValidationService dvs;
	
	@Autowired
	ListNames listNames;
	
	public DateValidationService getDvs() {
		return dvs;
	}

	public void setDvs(DateValidationService dvs) {
		this.dvs = dvs;
	}

	@RequestMapping(value = "/validateDate", method = RequestMethod.GET)
	public String validateDoB(@RequestParam(value = "dob", required = false) String dob, 
			ModelMap model) {
		
		String result = DateValidationService.IS_NOT_MINOR;
		try{
			boolean minor = dvs.isMinor(dob);
			if(minor){
				result = DateValidationService.IS_MINOR;
			}
		}catch(InvalidDateException ide){
			result = ide.getMessage();
		}
		
		model.addAttribute("result", result);
		return "validatedob";

	}

	
	@RequestMapping(value = "/Names", method = RequestMethod.GET)
	public String getListNames(ModelMap model) {
		
		model.addAttribute("nameList", listNames.getNames());
		return "listNames";

	}
	
	@RequestMapping(value = "/NamesJson", method = RequestMethod.GET,headers="Accept=application/json")
	public @ResponseBody List<String> getListNamesJson(ModelMap model) {
		return listNames.getNames();
	}
}
