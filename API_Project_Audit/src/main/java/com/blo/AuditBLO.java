package com.blo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beans.Audit;
import com.dao.AuditDaoImpl;
@Service
public class AuditBLO {
	@Autowired 
	
	AuditDaoImpl auditDao;
	
	public Audit getAuditById(String id) {
		return auditDao.getAuditById(id);
	}
	
	public ArrayList<Audit> getAllAudits(){
		return auditDao.getAudits();
	}
	
	public Audit setAuditDate(Audit audit) {
		return auditDao.setAuditDate(audit);
	}
	
	public void deleteAudit(String id) {
		auditDao.deleteAudit(id);
	}
	
	public void setAuditWeek(Audit audit) {
		auditDao.setSemaineAudit(audit);
	}
	
	public Audit addAudit(Audit audit) {
		return auditDao.addAudit(audit);
	}
	
	public Audit updateAudit(Audit audit) {
		return auditDao.updateAudit(audit);
		
	}
	
	
	
}
