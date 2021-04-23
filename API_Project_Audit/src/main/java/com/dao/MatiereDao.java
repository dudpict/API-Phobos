package com.dao;

import com.beans.Matiere;

public interface MatiereDao {

	Matiere getMatiereById(String matiereID);

	int getMatiereIdByResponsableID(int idProf);

}
