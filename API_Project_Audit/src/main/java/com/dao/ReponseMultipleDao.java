package com.dao;

import java.util.ArrayList;

import com.beans.ReponseMultiple;

public interface ReponseMultipleDao {

	ArrayList<ReponseMultiple> getReponseMultipleByIdReponse(String idReponse);
	
}
