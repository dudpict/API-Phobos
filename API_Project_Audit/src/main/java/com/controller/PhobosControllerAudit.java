package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Audit;
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

	@RequestMapping(value = "/deleteAudit", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_audit(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel DELETE");

		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		auditDao.deleteAudit(id);
	}

	@RequestMapping(value = "/dateHeureAudits", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_audit_setDateLimiteAudits(
			@RequestParam(required = false, value = "heureDebut") String heureDebut,
			@RequestParam(required = false, value = "id") int id,
			@RequestParam(required = false, value = "mode") String mode) {
		System.out.println("Appel SET by ID");

		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		auditDao.setHeureAudits(heureDebut, id, mode);
	}

	@RequestMapping(value = "/dateLimiteAudits", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_audit_setDateLimiteAudits(
			@RequestParam(required = false, value = "heureLimite") String heureLimite,
			@RequestParam(required = false, value = "id") int id) {
		System.out.println("Appel SET dateLimite");

		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		auditDao.setDateLimiteAudits(heureLimite, id);
	}

	@RequestMapping(value = "/addAudit", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_audit() {
		// TODO A dev pour Alexis et Gab - demander a QUENTIN
	}

	@RequestMapping(value = "/updateAudit", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_audit_setHeureAudit() {
		// TODO A dev pour Alexis et Gab - demander a QUENTIN
	}

}
