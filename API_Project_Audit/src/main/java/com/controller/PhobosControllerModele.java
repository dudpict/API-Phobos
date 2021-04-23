package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
