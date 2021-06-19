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

import com.beans.Etudiant;
import com.dao.DaoFactory;
import com.dao.EtudiantDao;

@RestController
@CrossOrigin(origins= "*")
public class PhobosControllerEtudiant {
	private static final Logger log = Logger.getLogger(PhobosControllerEtudiant.class);

	@GetMapping(value = "/etudiant")
	@ResponseBody
	public List<Etudiant> appelGETetudiant() {
		log.log(Level.INFO, "appel get etudiant");
		
		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		return etudiantDao.getEtudiants();

	}

	@GetMapping(value = "/etudiantById")
	@ResponseBody
	public Etudiant appelGETetudiantById(@RequestParam(required = true, value = "id") String id) {
		log.log(Level.INFO, "Appel GET by ID");
		
		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		return etudiantDao.getEtudiantById(id);
	}
	
	@GetMapping(value = "/etudiantByStr")
	@ResponseBody
	public List<Etudiant> appelGETetudiantByStr(@RequestParam(required = true, value = "search") String search) {
		log.log(Level.INFO, "appelGET_etudiantByStr");

		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		return etudiantDao.etudiantByStr(search);
	}
	
	@GetMapping(value = "/etudiantByPersonneId")
	@ResponseBody
	public Etudiant appelGETEtudiantByPersonneID(@RequestParam(required = true, value = "idPersonne") String idPersonne) {
		log.log(Level.INFO, "appelGET_EtudiantByPersonneID");

		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		return etudiantDao.getEtudiantByPersonneID(idPersonne);
	}
	
	@GetMapping(value = "/etudiantByAudit")
	@ResponseBody
	public List<Etudiant> appelGETetudiantByAudit(@RequestParam(required = true, value = "id_Audit") String idAudit) {
		log.log(Level.INFO, "appelGETetudiantByAudit");

		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		return etudiantDao.etudiantByAudit(idAudit);
	}
	
	
	@PostMapping(value = "/addetudiant")
	@ResponseBody
	public void appelPostaddetudiant(@RequestBody Etudiant etudiant) {
		log.log(Level.INFO, "appelPost_addetudiant");

		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		etudiantDao.addEtudiant(etudiant);
	}
	
	
	@PostMapping(value = "/addEtudiantToEquipeId")
	@ResponseBody
	public void appelPostaddEtudiantToEquipeId(@RequestParam(required = true, value = "id_Equipe") String idEquipe,
									@RequestParam(required = true, value = "id") String id) {
		log.log(Level.INFO, "appelPost_addEtudiantToEquipeId");
		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		etudiantDao.addEtudiantToEquipeId(idEquipe, id);
	}
	
	@PostMapping(value = "/removeEtudiantToEquipeId")
	@ResponseBody
	public void appelPostremoveEtudiantToEquipeId(@RequestParam(required = true, value = "id") String id) {
		log.log(Level.INFO, "appelPost_removeEtudiantToEquipeId");
		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		etudiantDao.removeEtudiantToEquipeId(id);
	}

	
	@DeleteMapping(value = "/etudiant")
	@ResponseBody
	public void appelDELETEetudiant(@RequestParam(required = true, value = "id") String id) {
		log.log(Level.INFO, "appelDELETE_etudiant");

		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		etudiantDao.deleteEtudiant(id);
	}
	
	@GetMapping(value = "/isStudentByPersonneId")
	@ResponseBody
	public boolean isEtudiant(@RequestParam(required= true, value ="id") String id ) {
		
		log.log(Level.INFO, "isEtudiant");
		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		return etudiantDao.getRoleEtudiant(id);
	}
	
	@GetMapping(value="/etudiantId")
	@ResponseBody
	public int etudiantId(@RequestParam(required=true,value="personneId")String idPersonne ) {
		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		return etudiantDao.getIdEtudiant(idPersonne);
	}
}
