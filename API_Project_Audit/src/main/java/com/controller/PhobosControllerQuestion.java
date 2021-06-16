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

import com.beans.Question;
import com.dao.DaoFactory;
import com.dao.QuestionDao;

@RestController
public class PhobosControllerQuestion {
	private static final Logger logger = Logger.getLogger(PhobosControllerQuestion.class);
	
	
	//APPEL GET
	
	// SELECT d'une question par son id de section
	@GetMapping(value = "/questionBySectionId")
	@ResponseBody
	public List<Question> getQuestionsBySectionId(@RequestParam(required = false, value = "id") String id) {
		logger.log(Level.INFO, "getQuestionsBySectionId");
		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();

		return questionDao.getQuestionsBySectionId(id);
	}

	@GetMapping(value = "/question")
	@ResponseBody
	public List<Question> appelGETquestion(@RequestParam(required = false, value = "id") String id) {
		logger.log(Level.INFO, "appelGET_question");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		return questionDao.getQuestions();
	}

	@GetMapping(value = "/questionById")
	@ResponseBody
	public Question appelGETquestionById(@RequestParam(required = true, value = "id") String id) {
		logger.log(Level.INFO, "appelGET_questionById");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		return questionDao.getQuestionById(id);
	}
	
	@GetMapping(value = "/questionByNom")
	@ResponseBody
	public Question appelGETquestionByNom(@RequestParam(required = true, value = "designation") String designation) {
		logger.log(Level.INFO, "appelGET_questionByNom");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		return questionDao.getQuestionByNom(designation);
	}
	
	
	@GetMapping(value = "/ getQuestion_By_All_Param")
	@ResponseBody
	public List<Question>  getQuestionByAllParam(@RequestBody Question question) {
		logger.log(Level.INFO, "getQuestion_By_All_Param");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		return questionDao.getQuestionByAllParam(question);
	}
	
	//APPEL POST 
	
	// INSERT d'une question

	@PostMapping(value = "/addQuestionBody")
	@ResponseBody
	public void appelPostquestionaddQuestionBody(@RequestBody Question question) {
		logger.log(Level.INFO, "appelPost_question_addQuestion Body");
		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		questionDao.addQuestion(question.getDesignation(), question.getIntitule(), question.getSection().getId(), question.getTypeQuestion().getId());
	}
	

	// UPDATE d'une question
	@PostMapping(value = "/updateQuestion")
	@ResponseBody
	public void appelPostquestionupdateQuestion(@RequestParam(required = false, value = "id") int id,
			@RequestParam(required = false, value = "Designation") String designation,
			@RequestParam(required = false, value = "reponse") String reponse,
			@RequestParam(required = false, value = "id_section") int idsection,
			@RequestParam(required = false, value = "id_typeQuestion") int idtypeQuestion,
			@RequestParam(required = false, value = "intitule") String intitule) {
		logger.log(Level.INFO, "appelPost_question_updateQuestion");
		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		questionDao.updateQuestion(id, designation, intitule, reponse, idsection, idtypeQuestion);
	}
	
	@GetMapping(value = "/questionByNomBody")
	@ResponseBody
	public Question appelGETquestionByNomBody(@RequestBody Question question) {
		logger.log(Level.INFO, "appelGET_questionByNomBody");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		return questionDao.getQuestionByNom(question.getDesignation());
	}
	
		
	//APPEL DELETE
	
	@DeleteMapping(value = "/question")
	@ResponseBody
	public void appelDELETEQuestion(@RequestParam(required = false, value = "id") String id) {
		logger.log(Level.INFO, "appelDELETE_question");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		questionDao.deleteQuestion(id);
	}
	
}



