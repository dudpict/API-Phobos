package com.dao;

import java.util.ArrayList;

import com.beans.Personne;

public interface PersonneDao {
    ArrayList<Personne> getPersonnes(String role);
	Personne getPersonneById(int id);

	Personne getPersonneByMail(String mail);
	public Personne addPersonne(Personne personne);
}
