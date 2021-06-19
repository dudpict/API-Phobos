package com.dao;

import java.util.ArrayList;

import com.beans.ReponseMultiple;

public interface ReponseMultipleDao {

	ArrayList<ReponseMultiple> getReponseMultipleByIdReponse(String idReponse);

	void addReponseMultiple(String designation, String idReponse, boolean cochee);

	void deleteReponseMultiple(String id);

	void updateReponseMultiple(String reponseMultiple, String idReponse, boolean cochee);

	
}
