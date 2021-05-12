package com.beans;

public class ReponseMultiple {
	private int id;
	private String reponse;	
	
	/**
	 * Constructeur d'une réponse de type reponseMultiple
	 * @param reponse
	 */
	public ReponseMultiple() {
		super();
	}
	
	public ReponseMultiple(String reponse){
		this.reponse=reponse;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	
}
