package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.TypeQuestion;
import com.dao.DaoFactory;
import com.dao.TypeQuestionDao;

@RestController
public class PhobosControllerTypeQuestion {

	@RequestMapping(value = "/typeQuestion", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<TypeQuestion> appelGET_typequestion(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");

		DaoFactory fact = new DaoFactory();
		TypeQuestionDao typeQuestionDao = fact.getTypeQuestionDao();
		ArrayList<TypeQuestion> allTypeQuestion = typeQuestionDao.getTypeQuestions();
		return allTypeQuestion;
	}

	@RequestMapping(value = "/typeQuestionById", method = RequestMethod.GET)
	@ResponseBody
	public TypeQuestion appelGET_typeQuestionById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		DaoFactory fact = new DaoFactory();
		TypeQuestionDao typeQuestionDao = fact.getTypeQuestionDao();
		TypeQuestion typeQuestion = typeQuestionDao.getTypeQuestionById(id);
		return typeQuestion;
	}

}
