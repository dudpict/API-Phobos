package com.dao;

import java.util.ArrayList;

import com.beans.Modele;

public interface ModeleDao {
	
	ArrayList<Modele> getModeles();
	void deleteModele(String id);
	Modele getModeleById(String modeleID);
	Modele getModeleByNom(String Designation);
	void addModele(String Designation);
	void updateModele(int id, String Designation);
}
