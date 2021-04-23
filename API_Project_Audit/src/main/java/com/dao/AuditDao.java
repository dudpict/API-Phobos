package com.dao;

import java.util.ArrayList;

import com.beans.Audit;
import com.beans.Etudiant;

public interface AuditDao {
	ArrayList<Audit> getAudits();
	Audit getAuditById(String id);
	void updateAudit(String id);
	void addAudit(Etudiant etudiantToAdd);
	void deleteAudit(String id);
}
