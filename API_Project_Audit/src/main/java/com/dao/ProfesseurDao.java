package com.dao;

import java.util.ArrayList;

import com.beans.Audit;
import com.beans.Professeur;

public interface ProfesseurDao {
	
	ArrayList<Professeur> getProfesseurs();
	void deleteProfesseur(String id);
	ArrayList<Audit> getAudits(int matiere, boolean publies);
	void setHeureAudits(String heureDebut, int id);
	int getMatiereResponsable(int id);
	Professeur getProfesseurById(String id);
	ArrayList<Professeur> getprofesseurByStr(String search);
	void addProfesseurToJuryId(String AppartientId, String id_Professeur);
	void removeProfesseurToJuryId(String Id_Jury, String id_Professeur);
	public Professeur getProfesseurByPersonneID(String idPersonne);
	public String getRoleProf ( String id);
	ArrayList<Professeur> professeurByAudit(String id_Audit);
	void addProfesseur(Professeur professeur);
	public boolean isProf(String id);

}