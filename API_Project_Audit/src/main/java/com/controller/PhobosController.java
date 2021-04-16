package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Personne;
import com.dao.DaoFactory;
import com.dao.PersonneDao;

@RestController
public class PhobosController {
	@RequestMapping(value = "/personne", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Personne> appelGET(@RequestParam(required = false, value = "role") String role, 
										@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");
		
		//TODO AJOUTER LE LIEN BDD SERVER
		
		DaoFactory fact = new DaoFactory("jdbc:mariadb://172.24.1.9/phpmyadmin/projetGl","root","network");
		
		PersonneDao personneDao = fact.getPersonneDao();
		ArrayList<Personne> allPersonnes = personneDao.getPersonnes(role);
		return allPersonnes;

	}

	@RequestMapping(value = "/personne", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost(@RequestParam(required = false, value = "codeCommune") String codeCommune,
			@RequestParam(required = false, value = "nom") String nom,
			@RequestParam(required = false, value = "prenom") String prenom,
			@RequestParam(required = false, value = "email") String email,
			@RequestParam(required = false, value = "tel") String tel) {
		
		Personne personneToAdd = new Personne(nom, prenom, email, tel);
		
		DaoFactory fact = new DaoFactory("url","identifiant","mdp");
		PersonneDao personneDao = fact.getPersonneDao();
		personneDao.ajouter(personneToAdd);
	}

}
