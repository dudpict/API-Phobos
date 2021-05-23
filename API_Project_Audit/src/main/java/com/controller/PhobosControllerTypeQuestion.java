package com.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.TypeQuestion;
import com.dao.DaoFactory;
import com.dao.TypeQuestionDao;

@RestController
public class PhobosControllerTypeQuestion {
	private static final Logger logger = Logger.getLogger(PhobosControllerTypeQuestion.class);


	@GetMapping(value = "/typeQuestion")
	@ResponseBody
	public List<TypeQuestion> appelGETtypequestion(@RequestParam(required = false, value = "id") String id) {
		logger.log(Level.INFO, "appelGET_typequestion");

		DaoFactory fact = new DaoFactory();
		TypeQuestionDao typeQuestionDao = fact.getTypeQuestionDao();
		return typeQuestionDao.getTypeQuestions();
	}

	@GetMapping(value = "/typeQuestionById")
	@ResponseBody
	public TypeQuestion appelGETtypeQuestionById(@RequestParam(required = true, value = "id") String id) {
		logger.log(Level.INFO, "appelGET_typeQuestionById");

		DaoFactory fact = new DaoFactory();
		TypeQuestionDao typeQuestionDao = fact.getTypeQuestionDao();
		return typeQuestionDao.getTypeQuestionById(id);
	}

}
