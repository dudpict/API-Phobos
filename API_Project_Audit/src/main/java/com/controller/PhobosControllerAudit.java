package com.controller;

import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Audit;
import com.beans.AuditModif;
import com.blo.AuditBLO;
import com.dao.AuditDao;
import com.dao.AuditModifDao;
import com.dao.DaoFactory;
@CrossOrigin(origins = "*")
@RestController
public class PhobosControllerAudit {

	private static final Logger logger = Logger.getLogger(PhobosControllerAudit.class);
	
	@Autowired
	AuditBLO auditBLO;

	
	@GetMapping(value = "/audits")
	@ResponseBody	
	public ArrayList<Audit> triAudit(@RequestParam(required = false, value="matiereId") String matiereId, 
			@RequestParam(required = false, value="lieuId") String lieuId ,
			@RequestParam(required = false, value="titre") String titre,
			@RequestParam(required = false, value="juryId") String juryId,
			@RequestParam(required = false, value="etat") String etat,
			@RequestParam(required = true, value = "id") String id,
			@RequestParam(required = true, value = "role") String role) {
		logger.log(Level.INFO, "appel get triAudit");
		return auditBLO.getFilteredAudit(matiereId,lieuId,titre,juryId,etat,id,role);
		
	}
	
	@RequestMapping(value = "/getModifiAuditByIdAudit", method = RequestMethod.GET)
	@ResponseBody	
	public ArrayList<AuditModif> getModifiAudit(@RequestParam(required = true, value="id") String id) {
		System.out.println("Appel GET getModifiAudit");
		
		
		DaoFactory fact = new DaoFactory();
		AuditModifDao AuditModifDao = fact.getAuditModifDao();
		return AuditModifDao.getModifiAudit(id);		
	}
	
	@GetMapping(value = "/isPersonneIsInAudit")
	@ResponseBody
	public ArrayList<Audit> appelGET_isPersonneIsInAudit(@RequestParam(required = true, value = "idPersonne") String idPersonne) {
		System.out.println("appelGET_isPersonneIsInAudit");

		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		return auditDao.isPersonneIsInAudit(idPersonne);
	}
	
	@RequestMapping(value = "/auditsAll", method = RequestMethod.GET)
	@ResponseBody	
	public ArrayList<Audit> auditsAll() {		
		return auditBLO.getAllAudits();
		
	}

	@RequestMapping(value = "/auditById", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin(origins = "*")
	public Audit appelGET_auditById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		Audit audit = auditBLO.getAuditById(id);
		return audit;
	}
	
	@RequestMapping(value = "/auditByEtudiantId", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Audit> appelGET_auditByEtudiantId(@RequestParam(required = true, value = "id_Etudiant") String id_Etudiant) {
		System.out.println("appelGET_auditByEtudiantId");

		DaoFactory fact = new DaoFactory();
		AuditDao AuditDao = fact.getAuditDao();
		return AuditDao.auditByEtudiantId(id_Etudiant);
	}
	
	@RequestMapping(value = "/auditByProfesseurId", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Audit> appelGET_auditByProfesseurId(@RequestParam(required = true, value = "id_Professeur") String id_Professeur) {
		System.out.println("appelGET_auditByProfesseurId");

		DaoFactory fact = new DaoFactory();
		AuditDao AuditDao = fact.getAuditDao();
		return AuditDao.auditByProfesseurId(id_Professeur);
	}

	@RequestMapping(value = "/deleteAudit", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_audit(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel DELETE");

		auditBLO.deleteAudit(id);
	}

	@RequestMapping(value = "/dateLimiteAudits", method = RequestMethod.POST)
	@ResponseBody
	public Audit appelPost_audit_setDateLimiteAudits(@RequestBody Audit audit) {
		Audit auditret = auditBLO.setAuditDate(audit);
		return auditret;
	}

	@RequestMapping(value = "/semaineAudits", method = RequestMethod.POST)
	@ResponseBody
	public Audit appelPost_audit_setSemaineeAudits(@RequestBody Audit audit) {
		System.out.println("Appel SET dateLimite");

		auditBLO.setAuditWeek(audit);
		return audit;
	}

	@RequestMapping(value = "/addAudit", method = RequestMethod.POST)
	@ResponseBody
	public Audit appelPost_audit(@RequestBody Audit audit) {
		auditBLO.addAudit(audit);
		return audit;

	}

	@RequestMapping(value = "/updateAudit", method = RequestMethod.POST)
	@ResponseBody
	public Audit appelPost_audit_update(@RequestBody Audit audit) {
		auditBLO.updateAudit(audit);
		return audit;
	}
	
	@RequestMapping(value = "/addEquipeToAudit", method = RequestMethod.POST)
	@ResponseBody
	public void  appelPost_addEquipeToAudit(@RequestParam(required = true, value = "id_Equipe") String id_Equipe,
											@RequestParam(required = true, value = "id_Audit") String id_Audit) {
		System.out.println("appelPost_addEquipeToAudit");

		DaoFactory fact = new DaoFactory();
		AuditDao AuditDao = fact.getAuditDao();
		AuditDao.addEquipeToAudit(id_Equipe, id_Audit);
	}
	
	@RequestMapping(value = "/removeEquipeToAudit", method = RequestMethod.POST)
	@ResponseBody
	public void  appelPost_removeEquipeToAudit(@RequestParam(required = true, value = "id_Audit") String id_Audit) {
		System.out.println("appelPost_removeEquipeToAudit");

		DaoFactory fact = new DaoFactory();
		AuditDao AuditDao = fact.getAuditDao();
		AuditDao.removeEquipeToAudit(id_Audit);
	}
	
	@RequestMapping(value = "/addJuryToAudit", method = RequestMethod.POST)
	@ResponseBody
	public void  appelPost_addJuryToAudit(@RequestParam(required = true, value = "id_Jury") String id_Jury,
												@RequestParam(required = true, value = "id_Audit") String id_Audit) {
		System.out.println("appelPost_addJuryToAudit");

		DaoFactory fact = new DaoFactory();
		AuditDao AuditDao = fact.getAuditDao();
		AuditDao.addJuryToAudit(id_Jury, id_Audit);
	}
	
	@RequestMapping(value = "/removeJuryToAudit", method = RequestMethod.POST)
	@ResponseBody
	public void  appelPost_removeJuryToAudit(@RequestParam(required = true, value = "id_Audit") String id_Audit) {
		System.out.println("appelPost_removeJuryToAudit");

		DaoFactory fact = new DaoFactory();
		AuditDao AuditDao = fact.getAuditDao();
		AuditDao.removeJuryToAudit(id_Audit);
	}
	
}
