package com.dao;

import java.util.ArrayList;

import com.beans.Modele;

public interface ModeleDao {
	
	ArrayList<Modele> getModeles();
	void deleteModele(String id);
	Modele getModeleById(String modeleID);
	void addModele(String id, String Designation);
	void updateModele(String id, String Designation);
}
