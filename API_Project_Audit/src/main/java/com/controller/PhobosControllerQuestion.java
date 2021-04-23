package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DaoFactory;
import com.dao.QuestionDao;

@RestController
public class PhobosControllerQuestion {

	// INSERT d'une question 
	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_question_addQuestion(@RequestParam(required = false, value = "Designation") String Designation,
												@RequestParam(required = false, value = "id_section") int id_section,
												@RequestParam(required = false, value = "id_typeQuestion") int id_typeQuestion) {
		System.out.println("méthode POST, add question");
		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		questionDao.addQuestion(Designation, id_section, id_typeQuestion);
	}
	
	// UPDATE d'une question 
	@RequestMapping(value = "/updateQuestion", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_question_updateQuestion(@RequestParam(required = false, value = "id") int id,
												@RequestParam(required = false, value = "Designation") String Designation,
												@RequestParam(required = false, value = "reponse") String reponse,
												@RequestParam(required = false, value = "id_section") int id_section,
												@RequestParam(required = false, value = "id_typeQuestion") int id_typeQuestion) {
		System.out.println("méthode POST, update question");
		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		questionDao.updateQuestion(id, Designation, reponse, id_section, id_typeQuestion);
	}

}
