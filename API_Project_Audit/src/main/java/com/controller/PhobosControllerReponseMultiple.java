package com.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping(value = "/addReponseMultiple")
	@ResponseBody
	public void appelPOSTaddReponseMultiple(@RequestParam(required = true, value = "reponse") String reponse,
												@RequestParam(required = true, value = "idQuestion") String idQuestion,
												@RequestParam(required = true, value = "cochee") Boolean cochee) {

		logger.log(Level.INFO, "appelPOST_addReponseMultiple");

		DaoFactory fact = new DaoFactory();
		ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
		reponseMultipleDao.addReponseMultiple(reponse, idQuestion,cochee);
	}
	
	@PostMapping(value = "/updateReponseMultiple")
	@ResponseBody
	public void appelPOSTupdateReponseMultiple(@RequestParam(required = true, value = "ReponseMultiple") String reponseMultiple,
												@RequestParam(required = true, value = "id") int id) {
		logger.log(Level.INFO, "appelPOST_updateReponseMultiple");

		DaoFactory fact = new DaoFactory();
		ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
		reponseMultipleDao.updateReponseMultiple(reponseMultiple,id);
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
