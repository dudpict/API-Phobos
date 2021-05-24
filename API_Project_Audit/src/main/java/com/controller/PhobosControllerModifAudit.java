package com.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.AuditModif;
import com.dao.AuditModifDao;
import com.dao.DaoFactory;

@RestController
@CrossOrigin(origins = "*")
public class PhobosControllerModifAudit {
	
	private static final Logger logger = Logger.getLogger(PhobosControllerModifAudit.class);

	
	@GetMapping(value = "/getModifiAuditByIdAudit")
	@ResponseBody	
	public List<AuditModif> getModifiAudit(@RequestParam(required = true, value="id") String id) {
		logger.log(Level.INFO, "Appel GET getModifiAudit");
		
		DaoFactory fact = new DaoFactory();
		AuditModifDao auditModifDao = fact.getAuditModifDao();
		return auditModifDao.getModifiAudit(id);		
	}
}
