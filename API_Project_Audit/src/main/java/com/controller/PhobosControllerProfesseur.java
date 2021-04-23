package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Personne;
import com.beans.Professeur;
import com.dao.DaoFactory;
import com.dao.ProfesseurDao;

@RestController
public class PhobosControllerProfesseur {

	@RequestMapping(value = "/professeur", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Professeur> appelGET_professeur(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		ArrayList<Professeur> allProfesseur = professeurDao.getProfesseurs();
		return allProfesseur;
	}

	@RequestMapping(value = "/professeurById", method = RequestMethod.GET)
	@ResponseBody
	public Professeur appelGET_professeurById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		Professeur professeur = professeurDao.getProfesseurById(id);
		return professeur;
	}

	@RequestMapping(value = "/professeur", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_professeur(@RequestParam(required = false, value = "nom") String nom,
			@RequestParam(required = false, value = "prenom") String prenom,
			@RequestParam(required = false, value = "email") String email,
			@RequestParam(required = false, value = "tel") String tel) {

		Personne personneToAdd = new Personne(nom, prenom, email, tel);
		Professeur professeurToAdd = new Professeur();
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.addProfesseur(professeurDao);
	}

	@RequestMapping(value = "/professeur", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_professeur(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel DELETE");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.deleteProfesseur(id);
	}
}
