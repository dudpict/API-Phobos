package com.dao;

import java.util.ArrayList;

import com.beans.Equipe;

public interface EquipeDao {
	ArrayList<Equipe> getEquipes();
	public Equipe getEquipeById(String id);
	Equipe getEquipeByEtudiantId(String idetudiant);

}
