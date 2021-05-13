package com.dao;

import java.util.ArrayList;

import com.beans.Audit;

public interface AuditDao {
	Audit getAuditById(String id);
	Audit updateAudit(Audit auditUpdated);
	Audit addAudit(Audit audit);
	void deleteAudit(String id);
	void setHeureAudits(Audit audit);
	Audit setAuditDate(Audit audit);
	public Audit setSemaineAudit(Audit audit);
	public  ArrayList<Audit> getFilteredAudits(String matiereId, String lieuId, String titre, String juryId,String etat,String id, String role);
}
