package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value = "/reponseById", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/addReponse", method = RequestMethod.POST)
	@ResponseBody
	public void appelPOST_ReponsesByQuestionId(@RequestParam(required = true, value = "ReponseLongue") String ReponseLongue,
												@RequestParam(required = true, value = "Note") int Note,
												@RequestParam(required = true, value = "ReponseCourte") Boolean ReponseCourte,
												@RequestParam(required = true, value = "idQuestion") String idQuestion) {
			
		System.out.println("Appel POST  addReponse");

		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		reponseDao.addReponse(ReponseLongue, Note, ReponseCourte, idQuestion);;
	}
	
	@RequestMapping(value = "/updateReponse", method = RequestMethod.POST)
	@ResponseBody
	public void appelPOST_updateReponse(@RequestParam(required = true, value = "id") int id, 
										@RequestParam(required = false, value = "ReponseLongue") String ReponseLongue, 
										@RequestParam(required = false, value = "note") int note, 
										@RequestParam(required = false, value = "ReponseCourte") Boolean ReponseCourte, 
										@RequestParam(required = false, value = "idQuestion") String idQuestion) {
		System.out.println("Appel POST  updateReponse");

		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		reponseDao.updateReponse(id, ReponseLongue, note, ReponseCourte, idQuestion);;
	}
	
	@RequestMapping(value = "/deleteReponse", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelPOST_deleteReponse(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel POST  deleteReponse");

		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		reponseDao.deleteReponse(id);
	}

}
