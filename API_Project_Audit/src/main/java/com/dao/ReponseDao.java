package com.dao;

import java.util.ArrayList;

import com.beans.Reponse;

public interface ReponseDao {

	ArrayList<Reponse> getReponseById(String id);

	ArrayList<Reponse> getReponsesByQuestionId(String id);

	void addReponse(Reponse reponse, String idQuestion);
	
}
