package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Etudiant;
import com.beans.Personne;
import com.beans.Professeur;
import com.dao.DaoFactory;
import com.dao.EtudiantDao;
import com.dao.ProfesseurDao;

@RestController
public class PhobosController {
	
	//GESTION ETUDIANTS
	@RequestMapping(value = "/etudiant", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Etudiant> appelGET_etudiant(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");
		
		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		ArrayList<Etudiant> allEtudiant = etudiantDao.getEtudiants();
		return allEtudiant;

	}

	@RequestMapping(value = "/etudiant", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_etudiant(@RequestParam(required = false, value = "promo") String promo,
			@RequestParam(required = false, value = "id") String id,
			@RequestParam(required = false, value = "classe") String classe,
			@RequestParam(required = false, value = "nom") String nom,
			@RequestParam(required = false, value = "prenom") String prenom,
			@RequestParam(required = false, value = "email") String email,
			@RequestParam(required = false, value = "tel") String tel) {
		
		Personne personneToAdd = new Personne(nom, prenom, email, tel);
		Etudiant etudiantToAdd = new Etudiant(promo, classe, personneToAdd);

		DaoFactory fact = new DaoFactory();

		EtudiantDao etudiantDao = fact.getEtudiantDao();
		etudiantDao.addEtudiant(etudiantToAdd);
	}
	
	
	//GESTION PROFESSEURS
	@RequestMapping(value = "/professeur", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Professeur> appelGET_professeur(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");
		
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		ArrayList<Professeur> allProfesseur = professeurDao.getProfesseurs();
		return allProfesseur;

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
		//TODO DEV la partie ajout professeur
//		professeurDao.addProfesseur(professeurDao);
	}

	
	
}
