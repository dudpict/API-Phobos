package com.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Audit;
import com.blo.AuditBLO;
@CrossOrigin(origins = "*")
@RestController
public class PhobosControllerAudit {

	@Autowired
	AuditBLO auditBLO;

	
	@RequestMapping(value = "/audits", method = RequestMethod.GET)
	@ResponseBody
	
	public ArrayList<Audit> triAudit(@RequestParam(required = false, value="matiereId") String matiereId, 
			@RequestParam(required = false, value="lieuId") String lieuId ,
			@RequestParam(required = false, value="titre") String titre,
			@RequestParam(required = false, value="juryId") String juryId,
			@RequestParam(required = false, value="etat") String etat,
			@RequestParam(required = true, value = "id") String id,
			@RequestParam(required = true, value = "role") String role) {
		
		return auditBLO.getFilteredAudit(matiereId,lieuId,titre,juryId,etat,id,role);
		
	}

	@RequestMapping(value = "/auditById", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin(origins = "*")
	public Audit appelGET_auditById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		Audit audit = auditBLO.getAuditById(id);
		return audit;
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

}
