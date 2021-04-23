package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Etudiant;
import com.beans.Personne;
import com.dao.DaoFactory;
import com.dao.EtudiantDao;

@RestController
public class PhobosControllerEtudiant {

	@RequestMapping(value = "/etudiant", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Etudiant> appelGET_etudiant(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");

		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		ArrayList<Etudiant> allEtudiant = etudiantDao.getEtudiants();
		return allEtudiant;

	}

	@RequestMapping(value = "/etudiantById", method = RequestMethod.GET)
	@ResponseBody
	public Etudiant appelGET_etudiantById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		Etudiant etudiant = etudiantDao.getEtudiantById(id);
		return etudiant;
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

	@RequestMapping(value = "/etudiant", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_etudiant(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel DELETE");

		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		etudiantDao.deleteEtudiant(id);
	}
}
