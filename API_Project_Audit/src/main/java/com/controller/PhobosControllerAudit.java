package com.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Audit;
import com.blo.AuditBLO;
import com.dao.AuditDao;
import com.dao.DaoFactory;
@CrossOrigin(origins = "*")
@RestController
public class PhobosControllerAudit {

	private static final Logger logger = Logger.getLogger(PhobosControllerAudit.class);
	
	@Autowired
	AuditBLO auditBLO;

	
	@GetMapping(value = "/audits")
	@ResponseBody	
	public List<Audit> triAudit(@RequestParam(required = false, value="matiereId") String matiereId, 
			@RequestParam(required = false, value="lieuId") String lieuId ,
			@RequestParam(required = false, value="titre") String titre,
			@RequestParam(required = false, value="juryId") String juryId,
			@RequestParam(required = false, value="etat") String etat,
			@RequestParam(required = true, value = "id") String id,
			@RequestParam(required = true, value = "role") String role,
			@RequestParam(required= false , value ="ueId")String ueId) {
		logger.log(Level.INFO, "appel get triAudit");
		
		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		
		String [] tablParamid=auditDao.returnParam(matiereId, lieuId, ueId, juryId);
		return auditDao.getFilteredAudits(titre, etat, id, role, tablParamid);
		
	}
	
	
	@GetMapping(value = "/isPersonneIsInAudit")
	@ResponseBody
	public List<Audit> appelGETIsPersonneIsInAudit(@RequestParam(required = true, value = "idPersonne") String idPersonne) {
		logger.log(Level.INFO, "appelGET_isPersonneIsInAudit");
		
		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		return auditDao.isPersonneIsInAudit(idPersonne);
	}
	
	@GetMapping(value = "/auditsAll")
	@ResponseBody	
	public List<Audit> auditsAll() {
		logger.log(Level.INFO, "Appel GET auditsAll");
		
		return auditBLO.getAllAudits();
		
	}

	@GetMapping(value = "/auditById")
	@ResponseBody
	@CrossOrigin(origins = "*")
	public Audit appelGETauditById(@RequestParam(required = true, value = "id") String id) {
		logger.log(Level.INFO, "appelGETauditById");
		
		return auditBLO.getAuditById(id);
	}
	
	@GetMapping(value = "/auditByEtudiantId")
	@ResponseBody
	public List<Audit> appelGETauditByEtudiantId(@RequestParam(required = true, value = "id_Etudiant") String idEtudiant) {
		logger.log(Level.INFO, "appelGET_auditByEtudiantId");
		
		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		return auditDao.auditByEtudiantId(idEtudiant);
	}
	
	@GetMapping(value = "/auditByProfesseurId")
	@ResponseBody
	public List<Audit> appelGETauditByProfesseurId(@RequestParam(required = true, value = "id_Professeur") String idProfesseur) {

		logger.log(Level.INFO, "Appel GET auditByProfesseurId");
		
		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		return auditDao.auditByProfesseurId(idProfesseur);
	}

	

	@PostMapping(value = "/dateLimiteAudits")
	@ResponseBody
	public Audit appelPostauditsetDateLimiteAudits(@RequestBody Audit audit) {
		logger.log(Level.INFO, "Appel GET appelPostauditsetDateLimiteAudits");
		
		return auditBLO.setAuditDate(audit);
	}

	@PostMapping(value = "/semaineAudits")
	@ResponseBody
	public Audit appelPostauditsetSemaineeAudits(@RequestBody Audit audit) {
		logger.log(Level.INFO, "appelPostauditsetSemaineeAudits");
		
		auditBLO.setAuditWeek(audit);
		return audit;
	}

	@PostMapping(value = "/addAudit")
	@ResponseBody
	public Audit appelPostaudit(@RequestBody Audit audit) {
		logger.log(Level.INFO, "appelPostaudit");
		
		auditBLO.addAudit(audit);
		return audit;

	}

	@PostMapping(value = "/updateAudit")
	@ResponseBody
	public Audit appelPostAuditUpdate(@RequestBody Audit audit) {
		logger.log(Level.INFO, "appelPost_audit_update");
		
		auditBLO.updateAudit(audit);
		return audit;
	}
	
	@PostMapping(value = "/addEquipeToAudit")
	@ResponseBody
	public void  appelPostaddEquipeToAudit(@RequestParam(required = true, value = "id_Equipe") String idEquipe,
											@RequestParam(required = true, value = "id_Audit") String idAudit) {
		logger.log(Level.INFO, "appelPost_addEquipeToAudit");
		
		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		auditDao.addEquipeToAudit(idEquipe, idAudit);
	}
	
	@PostMapping(value = "/removeEquipeToAudit")
	@ResponseBody
	public void  appelPostremoveEquipeToAudit(@RequestParam(required = true, value = "id_Audit") String idAudit) {
		logger.log(Level.INFO, "appelPost_removeEquipeToAudit");
		
		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		auditDao.removeEquipeToAudit(idAudit);
	}
	
	@PostMapping(value = "/addJuryToAudit")
	@ResponseBody
	public void  appelPostaddJuryToAudit(@RequestParam(required = true, value = "id_Jury") String idJury,
												@RequestParam(required = true, value = "id_Audit") String idAudit) {
		logger.log(Level.INFO, "appelPost_addJuryToAudit");
		
		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		auditDao.addJuryToAudit(idJury, idAudit);
	}
	
	@PostMapping(value = "/removeJuryToAudit")
	@ResponseBody
	public void  appelPostremoveJuryToAudit(@RequestParam(required = true, value = "id_Audit") String idAudit) {
		logger.log(Level.INFO, "appelPostremoveJuryToAudit");
		
		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		auditDao.removeJuryToAudit(idAudit);
	}
	
	
	@DeleteMapping(value = "/deleteAudit")
	@ResponseBody
	public void appelDELETEaudit(@RequestParam(required = true, value = "id") String id) {
		logger.log(Level.INFO, "Appel deleteAudit");
		
		auditBLO.deleteAudit(id);
	}
}
