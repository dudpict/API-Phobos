package com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.UE;
import com.dao.DaoFactory;
import com.dao.UEDao;

@RestController
@CrossOrigin(origins = "*")
public class PhobosControllerUE {
	
	@GetMapping(value = "/UES")
	@ResponseBody
	public List<UE> getUES(){
		DaoFactory fact  = new DaoFactory();
		UEDao ueDao = fact.getUEDao();
		return  ueDao.getUES();
	}
}
