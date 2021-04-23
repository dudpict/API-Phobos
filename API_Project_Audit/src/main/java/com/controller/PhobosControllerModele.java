package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Modele;
import com.dao.DaoFactory;
import com.dao.ModeleDao;

@RestController
public class PhobosControllerModele {

	// INSERT d'un Modele 
	@RequestMapping(value = "/addModele", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_question_addModele(@RequestParam(required = false, value = "Designation") String Designation) {
		System.out.println("méthode POST, add Modele");
		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		modeleDao.addModele(Designation);
	}
	
	// UPDATE d'un Modele
	@RequestMapping(value = "/updateModele", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_question_updateModele(@RequestParam(required = false, value = "id") String id,
												@RequestParam(required = false, value = "Designation") String Designation) {
		System.out.println("méthode POST, update Modele");
		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		modeleDao.updateModele(Integer.parseInt(id), Designation);
	}
	
	@RequestMapping(value = "/modele", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Modele> appelGET_modele() {
		System.out.println("Appel GET sur modele");

		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		ArrayList<Modele> allModele = modeleDao.getModeles();
		return allModele;
	}

	@RequestMapping(value = "/modeleById", method = RequestMethod.GET)
	@ResponseBody
	public Modele appelGET_modeleById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		Modele modele = modeleDao.getModeleById(id);
		return modele;
	}
	
	@RequestMapping(value = "/modeleByNom", method = RequestMethod.GET)
	@ResponseBody
	public Modele appelGET_modeleByNom(@RequestParam(required = true, value = "Designation") String designation) {
		System.out.println("Appel GET by Designation");

		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		Modele modele = modeleDao.getModeleByNom(designation);
		return modele;
	}


	@RequestMapping(value = "/modele", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_modele(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel Delete");

		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		modeleDao.deleteModele(id);
	}
}
