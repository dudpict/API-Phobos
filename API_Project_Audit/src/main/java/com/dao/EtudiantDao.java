package com.dao;

import java.util.ArrayList;

import com.beans.Equipe;
import com.beans.Etudiant;

public interface EtudiantDao {
    ArrayList<Etudiant> getEtudiants();
    void deleteEtudiant(String id);
	Etudiant getEtudiantById(String id);
	ArrayList<Etudiant> etudiantByStr(String search);
	void addEtudiantToEquipeId(String idEquipe, String id);
	void removeEtudiantToEquipeId(String id);
	ArrayList<Etudiant> etudiantByAudit(String idAudit);
	void addEtudiant(Etudiant etudiant);
	Etudiant getEtudiantByPersonneID(String idPersonne);
	Equipe isPersonneIsInTeam(String idPersonne);

}
