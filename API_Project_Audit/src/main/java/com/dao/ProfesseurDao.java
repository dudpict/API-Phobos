package com.dao;

import java.util.ArrayList;

import com.beans.Audit;
import com.beans.Professeur;

public interface ProfesseurDao {
	
	ArrayList<Professeur> getProfesseurs();
	void addProfesseur(ProfesseurDao professeurDao);
	void deleteProfesseur(String id);
	ArrayList<Audit> getAudits(int matiere, boolean publies);
	void setHeureAudits(String heureDebut, int id);
	int getMatiereResponsable(int id);
	Professeur getProfesseurById(String id);
	
}
