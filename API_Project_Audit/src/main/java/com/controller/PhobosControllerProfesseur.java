package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Professeur;
import com.dao.DaoFactory;
import com.dao.ProfesseurDao;

@RestController
public class PhobosControllerProfesseur {
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/professeur", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Professeur> appelGET_professeur() {
		System.out.println("Appel GET");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		ArrayList<Professeur> allProfesseur = professeurDao.getProfesseurs();
		return allProfesseur;
	}
	
	@RequestMapping(value = "/professeurByAudit", method = RequestMethod.GET)
	@ResponseBody
	public  ArrayList<Professeur> appelGET_professeurByAudit(@RequestParam(required = true, value = "id_Audit") String id_Audit) {
		System.out.println("appelGET_professeurByAudit");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		ArrayList<Professeur> professeurs = professeurDao.professeurByAudit(id_Audit);
		return professeurs;
	}
	
	@GetMapping(value = "/professeurByPersonneID")
	@ResponseBody
	public Professeur appelPost_getProfesseurByPersonneID(@RequestParam(required = true, value = "idPersonne") String idPersonne) {
		System.out.println("Appel getProfesseurByPersonneID");
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.getProfesseurByPersonneID(idPersonne);
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
	
	@RequestMapping(value = "/professeurByStr", method = RequestMethod.GET)
	@ResponseBody
	public  ArrayList<Professeur> appelGET_professeurByStr(@RequestParam(required = true, value = "search") String search) {
		System.out.println("appelGET_professeurByStr");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		ArrayList<Professeur> professeur = professeurDao.getprofesseurByStr(search);
		return professeur;
	}

	@PostMapping(value = "/addProfesseur")
	@ResponseBody
	public void appelPost_addProfesseur(@RequestBody Professeur professeur) {

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.addProfesseur(professeur);
	}
	
	@RequestMapping(value = "/addProfesseurToJuryId", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_addProfesseurToJuryId(@RequestParam(required = true, value = "Id_Jury") String Id_Jury,
										@RequestParam(required = true, value = "id_Professeur") String id_Professeur) {

		System.out.println("addProfesseurToJuryId");
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.addProfesseurToJuryId(Id_Jury, id_Professeur);
	}

	@RequestMapping(value = "/professeur", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_professeur(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel DELETE");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.deleteProfesseur(id);
	}
	
	@RequestMapping(value = "/removeProfesseurToJuryId", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_removeProfesseurToJuryId(@RequestParam(required = true, value = "Id_Jury") String Id_Jury,
													@RequestParam(required = true, value = "id_Professeur") String id_Professeur) {
		System.out.println("Appel DELETE removeProfesseurToJuryId");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.removeProfesseurToJuryId(Id_Jury, id_Professeur);;
	}
}
