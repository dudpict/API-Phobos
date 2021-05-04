package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Question;
import com.dao.DaoFactory;
import com.dao.QuestionDao;

@RestController
public class PhobosControllerQuestion {
	
	//APPEL GET
	
	// SELECT d'une question par son id de section
	@RequestMapping(value = "/questionBySection", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Question> getQuestionsBySectionId(@RequestParam(required = false, value = "id") String id) {
		System.out.println("méthode GET, getQuestionsBySectionId");
		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		ArrayList<Question> questions = questionDao.getQuestionsBySectionId(id);

		return questions;
	}

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Question> appelGET_question(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		ArrayList<Question> allQuestion = questionDao.getQuestions();
		return allQuestion;
	}

	@RequestMapping(value = "/questionById", method = RequestMethod.GET)
	@ResponseBody
	public Question appelGET_questionById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		Question question = questionDao.getQuestionById(id);
		return question;
	}
	
	@RequestMapping(value = "/questionByNom", method = RequestMethod.GET)
	@ResponseBody
	public Question appelGET_questionByNom(@RequestParam(required = true, value = "Designation") String Designation) {
		System.out.println("Appel GET by ID");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		Question question = questionDao.getQuestionByNom(Designation);
		return question;
	}
	
	@RequestMapping(value = "/ getQuestion_By_All_Param", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Question>  getQuestion_By_All_Param(@RequestBody Question question) {
		System.out.println("Appel GET question by all param");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		ArrayList<Question> allQuestion = questionDao.getQuestion_By_All_Param(question);
		return allQuestion;
	}
	
	//APPEL POST 
	
	// INSERT d'une question
	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_question_addQuestion(
			@RequestParam(required = false, value = "Designation") String Designation,
			@RequestParam(required = false, value = "id_section") int id_section,
			@RequestParam(required = false, value = "id_typeQuestion") int id_typeQuestion,
			@RequestParam(required = false, value = "intitule") String intitule) {
		System.out.println("méthode POST, add question");
		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		questionDao.addQuestion(Designation, intitule, id_section, id_typeQuestion);
	}

	// UPDATE d'une question
	@RequestMapping(value = "/updateQuestion", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_question_updateQuestion(@RequestParam(required = false, value = "id") int id,
			@RequestParam(required = false, value = "Designation") String Designation,
			@RequestParam(required = false, value = "reponse") String reponse,
			@RequestParam(required = false, value = "id_section") int id_section,
			@RequestParam(required = false, value = "id_typeQuestion") int id_typeQuestion,
			@RequestParam(required = false, value = "intitule") String intitule) {
		System.out.println("méthode POST, update question");
		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		questionDao.updateQuestion(id, Designation, intitule, reponse, id_section, id_typeQuestion);
	}
		
	//APPEL DELETE
	
	@RequestMapping(value = "/question", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_question(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel Delete");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		questionDao.deleteQuestion(id);
	}
	
}
