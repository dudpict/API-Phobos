package com.dao;

import java.util.ArrayList;

import com.beans.Audit;

public interface AuditDao {
	ArrayList<Audit> getAudits(int matiere, boolean publies);
	Audit getAuditById(String id);
	void updateAudit(String id, Audit auditUpdated);
	void addAudit(Audit audit);
	void deleteAudit(String id);
	void setHeureAudits(String heureDebut, int id, String mode);
	void setDateLimiteAudits(String heureLimite, int id);
}
