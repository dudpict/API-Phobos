package com.dao;

import java.util.ArrayList;

import com.beans.Etudiant;
import com.beans.Personne;

public interface EtudiantDao {
    ArrayList<Etudiant> getEtudiants();
    void addEtudiant(Etudiant etudiant);
    void deleteEtudiant(String id);
    void updateEtudiant(String id, Etudiant etudiantUpdated);
	Etudiant getEtudiantById(String id);
	ArrayList<Etudiant> etudiantByStr(String search);
	void addEtudiantToEquipeId(String idEquipe, String id);
	void removeEtudiantToEquipeId(String id);
	ArrayList<Etudiant> etudiantByAudit(String idAudit);
	void addEtudiant(String promo, String classe, int idPersonne, int idEquipe, int idroleUtilisateur,
			Personne personne);

}
