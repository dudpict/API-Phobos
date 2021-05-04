package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Reponse;
import com.dao.DaoFactory;
import com.dao.ReponseDao;


@RestController
public class PhobosControllerReponse {
	
	@RequestMapping(value = "/reponseByIdReponse", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Reponse> appelGET_reponseByIdReponse(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel reponse  GET by ID reponse");

		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		ArrayList<Reponse> reponseMultiples = reponseDao.getReponseById(id);
		return reponseMultiples;
	}
	
	@RequestMapping(value = "/getReponsesByQuestionId", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Reponse> appelGET_ReponsesByQuestionId(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel reponse  getReponsesByQuestionId");

		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		ArrayList<Reponse> reponseMultiples = reponseDao.getReponsesByQuestionId(id);
		return reponseMultiples;
	}
	

}
