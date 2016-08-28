package com.modis.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;


/**
 * Implements {@link ListNames} to fetch 5 random names from a List (hard-coded in this example)
 * @author radyog
 *
 */
@Service
public class ListNamesImpl implements ListNames {

	List<String> nameLst = new ArrayList<>(); 
	
	@Override
	public List<String> getNames() {
		
		List<String> returnVal = new ArrayList<>();
		for(int i=0;i<5;i++){
			
			int index = (int)(Math.random()* nameLst.size());
			returnVal.add(nameLst.get(index));
		}
		return returnVal;
	}
	
	@PostConstruct
	public void initMethod(){
		nameLst.add("Tom");
		nameLst.add("Harry");
		nameLst.add("Natalie");
		nameLst.add("Bridger");
		nameLst.add("Shayana");
		nameLst.add("Betty");
		nameLst.add("Tiarra");
		nameLst.add("Tanya");
		nameLst.add("Jennifer");
		nameLst.add("Sam");
		nameLst.add("Bill");
		nameLst.add("Medardo");
		nameLst.add("Shiva");
		nameLst.add("Priyam");
	}

}
