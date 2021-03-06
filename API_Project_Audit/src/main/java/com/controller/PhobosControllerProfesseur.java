package com.controller;

import java.util.List;

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
import com.beans.RoleUtilisateur;
import com.dao.DaoFactory;
import com.dao.ProfesseurDao;

@RestController
@CrossOrigin(origins = "*")
public class PhobosControllerProfesseur {
	private static final Logger logger = Logger.getLogger(PhobosControllerProfesseur.class);
	
	@GetMapping(value = "/professeur")
	@ResponseBody
	public List<Professeur> appelGETprofesseur() {
		logger.log(Level.INFO, "appel get appelGET_professeur");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.getProfesseurs();
	}
	
	@GetMapping(value = "/professeurByAudit")
	@ResponseBody
	public  List<Professeur> appelGETprofesseurByAudit(@RequestParam(required = true, value = "id_Audit") String idAudit) {
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
	public  List<Professeur> appelGETprofesseurByStr(@RequestParam(required = true, value = "search") String search) {
		logger.log(Level.INFO, "appelGET_professeurByStr");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.getprofesseurByStr(search);
	}

	
	@GetMapping(value="/isProfesseurByPersonneId")
	@ResponseBody
	public boolean isProf(@RequestParam(required= true, value= "id") String id) {
		logger.log(Level.INFO, "isProfesseurByPersonneId");
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.isProf(id);
	}
	
	@GetMapping(value="/professeurByPersonneId")
	@ResponseBody
	public Professeur appelGetProfesseurByPersonneId(@RequestParam(required= true, value= "idPersonne") String idPersonne) {
		logger.log(Level.INFO, "isProfesseurByPersonneId");
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.getProfByPersonneId(idPersonne);
	}
	
	@GetMapping(value="/professeurId")
	@ResponseBody
	public int profId(@RequestParam(required=true, value="id") String idPers ) {
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.getIdProf(idPers);
	}
	
	@GetMapping(value= "/roleProfesseur")
	@ResponseBody
	public List<RoleUtilisateur> roleProf (@RequestParam(required= true, value="id") String id) {
		logger.log(Level.INFO, "roleProfesseur");
		
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.getRoleProf(id);
		
	}
	
	@GetMapping(value= "/professeurSansRole")
	@ResponseBody
	public List<Professeur> profSansRole () {
		logger.log(Level.INFO, "appel get profSansRole");
		
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.getProfSansRole();
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
	public void appelPostaddProfesseurToJuryId(@RequestParam(required = true, value = "idJury") String idJury,
										@RequestParam(required = true, value = "idProfesseur") String idProfesseur) {

		logger.log(Level.INFO, "appelPost_addProfesseurToJuryId");
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.addProfesseurToJuryId(idJury, idProfesseur);
	}

	@DeleteMapping(value = "/professeur")
	@ResponseBody
	public void appelDELETEprofesseur(@RequestParam(required = true, value = "id") String id) {
		logger.log(Level.INFO, "appelDELETE_professeur");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.deleteProfesseur(id);
	}
	
	@DeleteMapping(value = "/removeProfesseurToJuryId")
	@ResponseBody
	public void appelDELETEremoveProfesseurToJuryId(@RequestParam(required = true, value = "Id_Jury") String idJury,
													@RequestParam(required = true, value = "id_Professeur") String idProfesseur) {
		logger.log(Level.INFO, "appelDELETE_removeProfesseurToJuryId");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.removeProfesseurToJuryId(idJury, idProfesseur);
	}
	
	
}
