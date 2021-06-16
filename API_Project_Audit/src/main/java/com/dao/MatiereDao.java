package com.dao;

import java.util.List;

import com.beans.Matiere;

public interface MatiereDao {

	Matiere getMatiereById(String matiereID);

	int getMatiereIdByResponsableID(int idProf);
	
	public List<Matiere> getMatieres();

	void updateMatiereProfRef(String idMatiere, String idProf);

	List<Matiere> getMatiereByProfRefId(int idProf);

	void addMatiere(Matiere matiere);

	void deleteMatiere(String idMatiere);

}
