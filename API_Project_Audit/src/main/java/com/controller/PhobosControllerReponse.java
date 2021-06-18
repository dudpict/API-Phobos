package com.controller;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Reponse;
import com.beans.Section;
import com.dao.DaoFactory;
import com.dao.ReponseDao;
import com.dao.SectionDao;


@RestController
public class PhobosControllerReponse {
	private static final Logger logger = Logger.getLogger(PhobosControllerReponse.class);

	
	@GetMapping(value = "/reponseById")
	@ResponseBody
	public Reponse appelGETreponseByIdReponse(@RequestParam(required = true, value = "id") String id) {
		logger.log(Level.INFO, "appelGET_reponseByIdReponse");

		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		return reponseDao.getReponseById(id);
	}
	
	@GetMapping(value = "/getReponsesByQuestionId")
	@ResponseBody
	public Reponse appelGETReponsesByQuestionId(@RequestParam(required = true, value = "id") String id) {
		logger.log(Level.INFO, "appelGET_ReponsesByQuestionId");

		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		return reponseDao.getReponsesByQuestionId(id);
	}
	
	/*@PostMapping(value = "/addReponse")
	@ResponseBody
	public void appelPOSTaddReponse(@RequestParam(required = true, value = "ReponseLongue") String reponseLongue,
												@RequestParam(required = true, value = "Note") int note,
												@RequestParam(required = true, value = "ReponseCourte") Boolean reponseCourte,
												@RequestParam(required = true, value = "idQuestion") String idQuestion) {
			
		logger.log(Level.INFO, "appelPOSTaddReponse");
		
		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		reponseDao.addReponse(reponseLongue, note, reponseCourte, idQuestion);
	}*/
	
	@PostMapping(value = "/addReponse")
	@ResponseBody
	public void appelPostReponseaddReponse(@RequestBody Reponse reponse) {
		logger.log(Level.INFO, "appelPost_reponse_addReponse");
		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		reponseDao.addReponse(reponse.getReponseLongue(), reponse.getNote(), reponse.getReponseCourte(),reponse.getIdQuestion());
	}
	
	@PostMapping(value = "/addReponseBody")
	@ResponseBody
	public void appelPOSTReponsesByQuestionIdBody(@RequestBody Reponse reponse) {
			
		logger.log(Level.INFO, "appelPOST_ReponsesByQuestionIdBody");

		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		reponseDao.addReponse(reponse.getReponseLongue(), reponse.getNote(), reponse.getReponseCourte(), Integer.toString(reponse.getIdQuestion()));
	}
	
	@PostMapping(value = "/updateReponse")
	@ResponseBody
	public void appelPOSTupdateReponse(@RequestParam(required = true, value = "id") int id, 
										@RequestParam(required = false, value = "ReponseLongue") String reponseLongue, 
										@RequestParam(required = false, value = "note") int note, 
										@RequestParam(required = false, value = "ReponseCourte") Boolean reponseCourte, 
										@RequestParam(required = false, value = "idQuestion") int idQuestion) {
		logger.log(Level.INFO, "appelPOST_updateReponse");

		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		reponseDao.updateReponse(id, reponseLongue, note, reponseCourte, idQuestion);
	}
	
	@DeleteMapping(value = "/deleteReponse")
	@ResponseBody
	public void appelPOSTdeleteReponse(@RequestParam(required = true, value = "id") String id) {
		logger.log(Level.INFO, "appelPOST_deleteReponse");

		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		reponseDao.deleteReponse(id);
	}

}
