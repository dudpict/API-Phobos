package com.dao;

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
	void addEquipeToAudit(String idEquipe, String idAudit);
	void removeEquipeToAudit(String idAudit);
	void addJuryToAudit(String idJury, String idAudit);
	void removeJuryToAudit(String idAudit);
	List<Audit> auditByEtudiantId(String idEtudiant);
	List<Audit> auditByProfesseurId(String idProfesseur);
	List<Audit> isPersonneIsInAudit(String idPersonne);
	List<Audit> getFilteredAudits(String titre, String etat, String id, String role, String[] tablParamid);
	String[] returnParam(String matiereId, String lieuId, String ueId, String juryId);
}
