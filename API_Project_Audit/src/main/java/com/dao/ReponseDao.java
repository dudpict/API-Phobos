package com.dao;

import java.util.ArrayList;

import com.beans.Reponse;

public interface ReponseDao {

	Reponse getReponseById(String id);

	ArrayList<Reponse> getReponsesByQuestionId(String id);

	void addReponse(String reponseLongue, int note, Boolean reponseCourte, String idQuestion);

	void deleteReponse(String id);

	void updateReponse(int id, String reponseLongue, int note, Boolean reponseCourte, String idQuestion);

	
	
}
