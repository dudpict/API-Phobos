package com.dao;

import java.util.List;

import com.beans.Matiere;

public interface MatiereDao {

	Matiere getMatiereById(String matiereID);

	int getMatiereIdByResponsableID(int idProf);
	
	public List<Matiere> getMatieres();

}
