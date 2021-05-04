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


@RestController
public class PhobosControllerAudit {
	
	@Autowired
	AuditBLO auditBLO;
	
	
	@RequestMapping(value = "/audits", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin(origins = "*")
	public ArrayList<Audit> appelGET_audit(@RequestParam(required = false, value = "matiereId") String matiereId,@RequestParam(required = false, value = "publie") String publie) {
		System.out.println("Appel GET avec matiereID et publiesBoolean");
		if (matiereId==null || publie==null) {
			ArrayList<Audit> audits  = new ArrayList<Audit>();
			audits=auditBLO.getAllAudits();
			return audits;
		}else {
			ArrayList<Audit> audits  = new ArrayList<Audit>();
			audits=auditBLO.getAudits(matiereId, publie);
			return audits;
			
		}
		

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
