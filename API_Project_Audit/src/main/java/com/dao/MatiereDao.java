package com.dao;

import com.beans.Matiere;

public interface MatiereDao {

	Matiere getMatiereById(String matiereID);

	int getMatiereResponsable(int idProf);

}
