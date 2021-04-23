package com.dao;

import java.util.ArrayList;

import com.beans.Modele;

public interface ModeleDao {
	
	ArrayList<Modele> getModeles();
	void deleteModele(String id);
	Modele getModeleById(int modeleID);
	void addModele(String Designation);
	void updateModele(int id, String Designation);
}
