package com.dao;

import java.util.ArrayList;

import com.beans.ReponseMultiple;

public interface ReponseMultipleDao {

	ArrayList<ReponseMultiple> getReponseMultipleByIdReponse(String idReponse);

	void addReponseMultiple(String reponse, String idQuestion, Boolean cochee);

	void deleteReponseMultiple(String id);

	void updateReponseMultiple(String ReponseMultiple, int id);
	
}
