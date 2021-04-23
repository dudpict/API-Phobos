package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Audit;
import com.beans.Etudiant;
import com.beans.Personne;
import com.dao.AuditDao;
import com.dao.DaoFactory;

@RestController
public class PhobosControllerAudit {
	@RequestMapping(value = "/audits", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Audit> appelGET_audit(@RequestParam(required = true, value = "matiereId") int matiereId,
			@RequestParam(required = true, value = "publiesBoolean") boolean publiesBoolean) {
		System.out.println("Appel GET avec matiereID et publiesBoolean");

		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		ArrayList<Audit> allAudit = auditDao.getAudits(matiereId, publiesBoolean);
		return allAudit;

	}

	@RequestMapping(value = "/auditById", method = RequestMethod.GET)
	@ResponseBody
	public Audit appelGET_auditById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		Audit audit = auditDao.getAuditById(id);
		return audit;
	}

	@RequestMapping(value = "/addAudit", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_audit(@RequestParam(required = false, value = "promo") String promo,
			@RequestParam(required = false, value = "id") String id,
			@RequestParam(required = false, value = "classe") String classe,
			@RequestParam(required = false, value = "nom") String nom,
			@RequestParam(required = false, value = "prenom") String prenom,
			@RequestParam(required = false, value = "email") String email,
			@RequestParam(required = false, value = "tel") String tel) {

		Personne personneToAdd = new Personne(nom, prenom, email, tel);
		Etudiant etudiantToAdd = new Etudiant(promo, classe, personneToAdd);

		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		auditDao.addAudit(etudiantToAdd);
	}

	@RequestMapping(value = "/deleteAudit", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_audit(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel DELETE");

		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		auditDao.deleteAudit(id);
	}
	
	@RequestMapping(value = "/updateAudit", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_audit_updateAudit(@RequestParam(required = false, value = "id") String id,
												@RequestParam(required = false, value = "Designation") String Designation) {
		System.out.println("méthode POST, update Modele");
		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		auditDao.updateAudit(id);
		}
}
