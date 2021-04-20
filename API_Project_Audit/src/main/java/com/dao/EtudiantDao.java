package com.dao;

import java.util.ArrayList;

import com.beans.Etudiant;

public interface EtudiantDao {
    ArrayList<Etudiant> getEtudiants();
    void addEtudiant(Etudiant etudiant);
    void deleteEtudiant(String id);
    void updateEtudiant(String id, Etudiant etudiantUpdated);

}
