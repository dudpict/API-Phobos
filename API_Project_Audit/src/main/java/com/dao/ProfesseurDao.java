package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.beans.Audit;
import com.beans.Professeur;
import com.beans.RoleUtilisateur;

public interface ProfesseurDao {
	
	ArrayList<Professeur> getProfesseurs();
	void deleteProfesseur(String id);
	ArrayList<Audit> getAudits(int matiere, boolean publies);
	void setHeureAudits(String heureDebut, int id);
	int getMatiereResponsable(int id);
	Professeur getProfesseurById(String id);
	ArrayList<Professeur> getprofesseurByStr(String search);
	void addProfesseurToJuryId(String appartientId, String idProfesseur);
	void removeProfesseurToJuryId(String idJury, String idProfesseur);
	public Professeur getProfesseurByPersonneID(String idPersonne);
	public List<RoleUtilisateur> getRoleProf ( String id);
	public List<Professeur> getProfSansRole();
	ArrayList<Professeur> professeurByAudit(String idAudit);
	void addProfesseur(Professeur professeur);
	public boolean isProf(String id);
	public int getIdProf(String idPers);
	Professeur getProfByPersonneId(String id);
}