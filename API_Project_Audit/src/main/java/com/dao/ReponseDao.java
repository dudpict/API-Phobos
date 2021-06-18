package com.dao;

import com.beans.Reponse;

public interface ReponseDao {

	Reponse getReponseById(String id);

	Reponse getReponsesByQuestionId(String id);

	void addReponse(String reponseLongue, int note, boolean reponseCourte, String idQuestion);

	void deleteReponse(String id);

	void updateReponse(int id, String reponseLongue, int note, boolean reponseCourte, String idQuestion);

	
	
}
