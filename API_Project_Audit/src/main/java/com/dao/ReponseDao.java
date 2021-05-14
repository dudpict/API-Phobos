package com.dao;

import java.util.ArrayList;

import com.beans.Reponse;

public interface ReponseDao {

	ArrayList<Reponse> getReponseById(String id);

	ArrayList<Reponse> getReponsesByQuestionId(String id);

	

	void updateReponse(Reponse reponse, String idQuestion);

	void deleteReponse(Reponse reponse);

	void addReponse(String ReponseLongue, int Note, Boolean ReponseCourte, String idQuestion);

	
	
}
