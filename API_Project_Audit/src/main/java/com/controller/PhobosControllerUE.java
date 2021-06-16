package com.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.UE;
import com.dao.DaoFactory;
import com.dao.UEDao;



@RestController
@CrossOrigin(origins = "*")
public class PhobosControllerUE {
	private static final Logger logger = Logger.getLogger(PhobosControllerUE.class);

	
	
	@GetMapping(value = "/UES")
	@ResponseBody
	public List<UE> getUES(){
		DaoFactory fact  = new DaoFactory();
		UEDao ueDao = fact.getUEDao();
		return  ueDao.getUES();
	}
	
	@PostMapping(value = "/addUe")
	@ResponseBody
	public void addUe(@RequestBody UE ue) {
		logger.log(Level.INFO, "addUe");

		DaoFactory fact  = new DaoFactory();
		UEDao ueDao = fact.getUEDao();
		ueDao.addUe(ue);
	}
	
	
	@DeleteMapping(value = "/deleteUe")
	@ResponseBody
	public void deleteUe(@RequestParam(required = true, value = "idUe") String idUe) {
		logger.log(Level.INFO, "deleteUe");

		DaoFactory fact  = new DaoFactory();
		UEDao ueDao = fact.getUEDao();
		ueDao.deleteUe(idUe);
	}
}
