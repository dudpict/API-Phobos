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
		ArrayList<ReponseMultiple> reponseMultiples = reponseMultipleDao.getReponseMultipleByIdReponse(id);
		return reponseMultiples;
	}
	
	@RequestMapping(value = "/addReponseMultiple", method = RequestMethod.POST)
	@ResponseBody
	public void appelPOST_addReponseMultiple(@RequestBody ReponseMultiple reponseMultiple,
											@RequestBody String idReponse) {
		System.out.println("Appel addReponseMultiple ");

		DaoFactory fact = new DaoFactory();
		ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
		reponseMultipleDao.addReponseMultiple(reponseMultiple, idReponse);;
	}
	

}
