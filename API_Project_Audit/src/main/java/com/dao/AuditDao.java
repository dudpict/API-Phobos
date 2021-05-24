package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.beans.Audit;

public interface AuditDao {
	Audit getAuditById(String id);
	Audit updateAudit(Audit auditUpdated);
	Audit addAudit(Audit audit);
	void deleteAudit(String id);
	void setHeureAudits(Audit audit);
	Audit setAuditDate(Audit audit);
	public Audit setSemaineAudit(Audit audit);

	public  List<Audit> getFilteredAudits(String matiereId, String lieuId, String titre, String juryId,String etat,String id, String role,String ueId);
	void addEquipeToAudit(String idEquipe, String idAudit);
	void removeEquipeToAudit(String idAudit);
	void addJuryToAudit(String idJury, String idAudit);
	void removeJuryToAudit(String idAudit);
	ArrayList<Audit> auditByEtudiantId(String idEtudiant);
	ArrayList<Audit> auditByProfesseurId(String idProfesseur);
	ArrayList<Audit> isPersonneIsInAudit(String idPersonne);
}
