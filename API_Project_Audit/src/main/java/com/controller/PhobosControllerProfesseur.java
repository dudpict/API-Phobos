package com.controller;

import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Professeur;
import com.dao.DaoFactory;
import com.dao.ProfesseurDao;

@RestController
@CrossOrigin(origins = "*")
public class PhobosControllerProfesseur {
	private static final Logger logger = Logger.getLogger(PhobosControllerProfesseur.class);
	
	@GetMapping(value = "/professeur")
	@ResponseBody
	public ArrayList<Professeur> appelGETprofesseur() {
		logger.log(Level.INFO, "appel get appelGET_professeur");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.getProfesseurs();
	}
	
	@GetMapping(value = "/professeurByAudit")
	@ResponseBody
	public  ArrayList<Professeur> appelGETprofesseurByAudit(@RequestParam(required = true, value = "id_Audit") String idAudit) {
		logger.log(Level.INFO, "appel get appelGET_professeurByAudit");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.professeurByAudit(idAudit);
	}

	@GetMapping(value = "/professeurById")
	@ResponseBody
	public Professeur appelGETprofesseurById(@RequestParam(required = true, value = "id") String id) {
		logger.log(Level.INFO, "appelGET_professeurById");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.getProfesseurById(id);
	}
	
	@GetMapping(value = "/professeurByStr")
	@ResponseBody
	public  ArrayList<Professeur> appelGETprofesseurByStr(@RequestParam(required = true, value = "search") String search) {
		logger.log(Level.INFO, "appelGET_professeurByStr");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.getprofesseurByStr(search);
	}

	@PostMapping(value = "/professeur")
	@ResponseBody
	public void appelPostprofesseur(@RequestBody Professeur professeur) {
		logger.log(Level.INFO, "appelPost_professeur");
		
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.addProfesseur(professeur);
	}
	
	@PostMapping(value = "/addProfesseurToJuryId")
	@ResponseBody
	public void appelPostaddProfesseurToJuryId(@RequestParam(required = true, value = "Id_Jury") String idJury,
										@RequestParam(required = true, value = "id_Professeur") String idProfesseur) {

		logger.log(Level.INFO, "appelPost_addProfesseurToJuryId");
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.addProfesseurToJuryId(idJury, idProfesseur);
	}

	@DeleteMapping(value = "/professeur")
	@ResponseBody
	public void appelDELETEprofesseur(@RequestParam(required = false, value = "id") String id) {
		logger.log(Level.INFO, "appelDELETE_professeur");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.deleteProfesseur(id);
	}
	
	@DeleteMapping(value = "/removeProfesseurToJuryId")
	@ResponseBody
	public void appelDELETEremoveProfesseurToJuryId(@RequestParam(required = true, value = "Id_Jury") String Id_Jury,
													@RequestParam(required = true, value = "id_Professeur") String idProfesseur) {
		logger.log(Level.INFO, "appelDELETE_removeProfesseurToJuryId");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.removeProfesseurToJuryId(Id_Jury, idProfesseur);
	}
	
	@GetMapping(value= "/roleProfesseur")
	@ResponseBody
	public String roleProf (@RequestParam(required= true) String id) {
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.getRoleProf ( id);
		
	}
}
