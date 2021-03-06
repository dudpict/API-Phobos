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

import com.beans.Modele;
import com.dao.DaoFactory;
import com.dao.ModeleDao;

@RestController
public class PhobosControllerModele {
	private static final Logger logger = Logger.getLogger(PhobosControllerModele.class);

	@GetMapping(value = "/modele")
	@ResponseBody
	public List<Modele> appelGETmodele() {
		logger.log(Level.INFO, "appelGET_modele");

		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		return modeleDao.getModeles();
	}

	@GetMapping(value = "/modeleById")
	@ResponseBody
	public Modele appelGETmodeleById(@RequestParam(required = true, value = "id") String id) {
		logger.log(Level.INFO, "appelGET_modeleById");

		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		return modeleDao.getModeleById(id);
	}
	
	
	@PostMapping(value = "/modeleByNomBody")
	@ResponseBody
	public Modele appelGETmodeleByNom(@RequestBody Modele modele) {
		logger.log(Level.INFO, "appelGET_modeleByNom");

		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		return  modeleDao.getModeleByNom(modele.getDesignation());
	}

	
	// INSERT d'un Modele 
	@PostMapping(value = "/addModele")
	@ResponseBody
	public void appelPostquestionaddModele(@RequestBody Modele modele) {
		logger.log(Level.INFO, "appelPostquestionaddModele");
		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		modeleDao.addModele(modele);
	}
	
	// UPDATE d'un Modele
	@PostMapping(value = "/updateModele")
	@ResponseBody
	public void appelPostupdateModele(@RequestParam(required = true, value = "id") String id,
												@RequestParam(required = true, value = "Designation") String designation) {
		logger.log(Level.INFO, "appelPost_question_updateModele");
		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		modeleDao.updateModele(Integer.parseInt(id), designation);
	}
	
	// UPDATE d'un Modele
	@PostMapping(value = "/updateModeleBody")
	@ResponseBody
	public void appelPostupdateModeleBody(@RequestBody Modele modele) {
		logger.log(Level.INFO, "appelPost_question_updateModele");
		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		modeleDao.updateModele(modele.getId(), modele.getDesignation());
	}
	
	

	@DeleteMapping(value = "/deleteModele")
	@ResponseBody
	public void appelDELETEmodele(@RequestParam(required = true, value = "id") String id) {
		logger.log(Level.INFO, "appelDELETE_modele");

		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		modeleDao.deleteModele(id);
	}
}
