package com.dao;

import java.util.ArrayList;

import com.beans.ReponseMultiple;

public interface ReponseMultipleDao {

	ArrayList<ReponseMultiple> getReponseMultipleByIdReponse(String idReponse);

	void updateReponseMultiple(ReponseMultiple reponsepultiple);

	void deleteReponseMultiple(ReponseMultiple reponsepultiple);

	void addReponseMultiple(String reponse, String idQuestion);
	
}
