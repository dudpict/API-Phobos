package com.controller;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Personne;
import com.dao.DaoFactory;
import com.dao.PersonneDao;
@CrossOrigin(origins = "*")
@RestController
public class PhobosControllerPersonne {
	
	private static final Logger logger = Logger.getLogger(PhobosControllerPersonne.class);
	
	@GetMapping(value = "/personneByMail")
	@ResponseBody
	public Personne getPersonneByMail(@RequestParam(required = true, value = "mail") String mail) {
		logger.log(Level.INFO, "appel get getPersonneByMail");
		DaoFactory fact = new DaoFactory();
		PersonneDao personneDao = fact.getPersonneDao();
	
		return personneDao.getPersonneByMail(mail);
	}
	
	@PostMapping(value = "/addPersonne")
	@ResponseBody
	public void postAddPersonne(@RequestParam(required = true, value = "nom") String nom,
									@RequestParam(required = true, value = "prenom") String prenom,
									@RequestParam(required = true, value = "email") String email,
									@RequestParam(required = true, value = "tel") String tel) {
		logger.log(Level.INFO, "appel get postAddPersonne");
		DaoFactory fact = new DaoFactory();
		PersonneDao personneDao = fact.getPersonneDao();
	
		personneDao.addPersonne(nom, prenom, email, tel);;
	}
	
}
