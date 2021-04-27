package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Audit;
import com.blo.AuditBLO;
import com.dao.AuditDao;
import com.dao.DaoFactory;

@RestController
public class PhobosControllerAudit {
	
	@Autowired
	AuditBLO auditBLO;
	
	
	@RequestMapping(value = "/audits", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Audit> appelGET_audit() {
		System.out.println("Appel GET avec matiereID et publiesBoolean");

		ArrayList<Audit> audits  = new ArrayList<Audit>();
		audits=auditBLO.getAllAudits();
		return audits;

	}

	@RequestMapping(value = "/auditById", method = RequestMethod.GET)
	@ResponseBody
	public Audit appelGET_auditById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		Audit audit = auditBLO.getAuditById(id);
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

	@RequestMapping(value = "/dateHeureAudits", method = RequestMethod.PATCH)
	@ResponseBody
	public Audit appelPost_audit_setDateLimiteAudits(@RequestBody Audit audit) {
		Audit auditret = auditBLO.setAuditDate(audit);
		return auditret;
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
