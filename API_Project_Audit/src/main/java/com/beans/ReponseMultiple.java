package com.beans;

import java.util.ArrayList;

public class ReponseMultiple {
	private int id;
	private String reponse;
	
	
	/**
	 * Constructeur d'une réponse de type reponseMultiple
	 * @param reponse
	 */
	public ReponseMultiple(String reponse){
		//super();
		this.reponse=reponse;
	}
	
	public String getReponseMultiple() {
		return reponse;
	}

	public void setReponseMultiple(String reponse) {
		this.reponse = reponse;
	}	
}
