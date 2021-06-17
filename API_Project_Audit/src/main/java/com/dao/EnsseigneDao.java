package com.dao;

import java.util.List;

import com.beans.Ensseigne;

public interface EnsseigneDao {

	Ensseigne getEnsseigneByIdAndIdProf(int idMat,int idProf);

	void addEnsseigne(int idMat, int idProf);

	void deleteEnsseigne(int idMat, int idProf);

	List<Ensseigne> getEnsseigneByIdProf(int idProf);



}
