package com.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Option;
import com.dao.DaoFactory;
import com.dao.OptionDao;

@RestController
public class PhobosControllerOption {
	private static final Logger logger = Logger.getLogger(PhobosControllerOption.class);

	
	@GetMapping (value = "/allOption")
	@ResponseBody
	public List<Option> getLieux(){
		logger.log(Level.INFO, "appelGET_allOption");
		
		DaoFactory fact = new DaoFactory();
		OptionDao optionDao = fact.getOptionDao();		
		return optionDao.getAllOption();	
	}
}