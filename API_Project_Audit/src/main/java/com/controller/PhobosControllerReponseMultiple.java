package com.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.ReponseMultiple;
import com.dao.DaoFactory;
import com.dao.ReponseMultipleDao;


@RestController
public class PhobosControllerReponseMultiple {
	private static final Logger logger = Logger.getLogger(PhobosControllerReponseMultiple.class);

	
	@GetMapping(value = "/reponseMultipleByIdReponse")
	@ResponseBody
	public List<ReponseMultiple> appelGETreponseMultipleByIdReponse(@RequestParam(required = true, value = "id") String id) {
		logger.log(Level.INFO, "appelGET_reponseMultipleByIdReponse");

		DaoFactory fact = new DaoFactory();
		ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
		
		return reponseMultipleDao.getReponseMultipleByIdReponse(id);
	}
	
	
	@PostMapping(value = "/addReponseMultipleBody")
	@ResponseBody
	public void appelPOSTaddReponsesMultipleBody(@RequestBody ReponseMultiple reponseMultiple) {
			
		logger.log(Level.INFO, "appelPOST_ReponsesMultipleByReponseIdBody");

		DaoFactory fact = new DaoFactory();
		ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
		reponseMultipleDao.addReponseMultiple(reponseMultiple.getDesignation(), Integer.toString(reponseMultiple.getId()), reponseMultiple.getCochee());
	}
	
	@PostMapping(value = "/updateReponseMultiple")
	@ResponseBody
	public void appelPOSTupdateReponseMultiple(@RequestBody ReponseMultiple reponseMultiple) {
		logger.log(Level.INFO, "appelPOST_updateReponseMultiple");

		DaoFactory fact = new DaoFactory();
		ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
		reponseMultipleDao.updateReponseMultiple(reponseMultiple.getDesignation(),Integer.toString(reponseMultiple.getReponse().getId()), reponseMultiple.getCochee());
	}
	
	@DeleteMapping(value = "/deleteReponseMultiple")
	@ResponseBody
	public void appelPOSTdeleteReponseMultiple(@RequestParam(required = true, value = "id") String id) {
		logger.log(Level.INFO, "appelPOST_deleteReponseMultiple");

		DaoFactory fact = new DaoFactory();
		ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
		reponseMultipleDao.deleteReponseMultiple(id);
	}
}
