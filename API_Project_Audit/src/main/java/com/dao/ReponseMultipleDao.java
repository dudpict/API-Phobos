package com.dao;

import java.util.ArrayList;

import com.beans.ReponseMultiple;

public interface ReponseMultipleDao {

	ArrayList<ReponseMultiple> getReponseMultipleByIdReponse(String idReponse);

	void addReponseMultiple(ReponseMultiple reponsepultiple, String idReponse);

	void updateReponseMultiple(ReponseMultiple reponsepultiple);

	void deleteReponseMultiple(ReponseMultiple reponsepultiple);
	
}
