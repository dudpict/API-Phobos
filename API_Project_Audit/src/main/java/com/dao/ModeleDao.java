package com.dao;

import java.util.ArrayList;

import com.beans.Modele;

public interface ModeleDao {
	
	ArrayList<Modele> getModeles();
	void deleteModele(String id);
	Modele getModeleById(String modeleID);
	Modele getModeleByNom(String designation);
	void addModele(Modele modele);
	void updateModele(int id, String designation);
}
