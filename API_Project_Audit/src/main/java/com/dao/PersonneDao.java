package com.dao;

import java.util.ArrayList;

import com.beans.Personne;

public interface PersonneDao {
    ArrayList<Personne> getPersonnes(String role);
	Personne getPersonneById(int id);

	Personne getPersonneByMail(String mail);
	void addPersonne(String nom, String prenom, String email, String tel);
}
