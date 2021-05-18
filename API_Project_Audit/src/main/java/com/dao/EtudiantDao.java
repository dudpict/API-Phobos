package com.dao;

import java.util.ArrayList;

import com.beans.Etudiant;

public interface EtudiantDao {
    ArrayList<Etudiant> getEtudiants();
    void addEtudiant(Etudiant etudiant);
    void deleteEtudiant(String id);
    void updateEtudiant(String id, Etudiant etudiantUpdated);
	Etudiant getEtudiantById(String id);
	ArrayList<Etudiant> etudiantByStr(String search);
	void addEtudiantToEquipeId(String id_Equipe, String id);
	void removeEtudiantToEquipeId(String id);
	ArrayList<Etudiant> etudiantByAudit(String id_Audit);

}
