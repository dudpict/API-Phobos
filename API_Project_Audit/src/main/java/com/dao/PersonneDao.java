package com.dao;

import java.util.ArrayList;

import com.beans.Personne;

public interface PersonneDao {
	void ajouter( Personne personne );
    ArrayList<Personne> getPersonnes(String role);
	Personne getPersonneById(int id);
	//void modifier(String id, String field, String value);
}
