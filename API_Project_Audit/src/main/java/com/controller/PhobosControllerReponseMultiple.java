package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.ReponseMultiple;
import com.dao.DaoFactory;
import com.dao.ReponseMultipleDao;


@RestController
public class PhobosControllerReponseMultiple {
	
	@RequestMapping(value = "/reponseMultipleByIdReponse", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<ReponseMultiple> appelGET_reponseMultipleByIdReponse(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel reponse multiple GET by ID reponse");

		DaoFactory fact = new DaoFactory();
		ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
		
		return reponseMultipleDao.getReponseMultipleByIdReponse(id);
	}
	
	@RequestMapping(value = "/addReponseMultiple", method = RequestMethod.POST)
	@ResponseBody
	public void appelPOST_addReponseMultiple(@RequestParam(required = true, value = "reponse") String reponse,
												@RequestParam(required = true, value = "idQuestion") String idQuestion,
												@RequestParam(required = true, value = "cochee") Boolean cochee) {

		System.out.println("Appel addReponseMultiple ");

		DaoFactory fact = new DaoFactory();
		ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
		reponseMultipleDao.addReponseMultiple(reponse, idQuestion,cochee);
	}
	
	@RequestMapping(value = "/updateReponseMultiple", method = RequestMethod.POST)
	@ResponseBody
	public void appelPOST_updateReponseMultiple(@RequestParam(required = true, value = "ReponseMultiple") String ReponseMultiple,
												@RequestParam(required = true, value = "id") int id) {
		System.out.println("Appel updateReponseMultiple ");

		DaoFactory fact = new DaoFactory();
		ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
		reponseMultipleDao.updateReponseMultiple(ReponseMultiple,id);
	}
	
	@RequestMapping(value = "/deleteReponseMultiple", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelPOST_deleteReponseMultiple(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel deleteReponseMultiple ");

		DaoFactory fact = new DaoFactory();
		ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
		reponseMultipleDao.deleteReponseMultiple(id);
	}
}
