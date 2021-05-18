package com.dao;

import java.util.ArrayList;

import com.beans.Reponse;

public interface ReponseDao {

	ArrayList<Reponse> getReponseById(String id);

	ArrayList<Reponse> getReponsesByQuestionId(String id);

	void addReponse(String ReponseLongue, int Note, Boolean ReponseCourte, String idQuestion);

	void deleteReponse(String id);

	void updateReponse(int id, String ReponseLongue, int note, Boolean ReponseCourte, String idQuestion);

	
	
}
