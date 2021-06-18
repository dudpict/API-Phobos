package com.beans;

public class ReponseMultiple {
	private int id;
	private String designation;	
	private boolean cochee;
	private Reponse reponse;
	
	/**
	 * Constructeur d'une réponse de type reponseMultiple
	 * @param reponse
	 */
	public ReponseMultiple() {
		super();
	}
	
	public ReponseMultiple(String reponse){
		this.designation=reponse;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public boolean getCochee() {
		return cochee;
	}

	public void setCochee(boolean cochee) {
		this.cochee = cochee;
	}
	
	public Reponse getReponse() {
		return reponse;
	}

	public void setReponse(Reponse reponse) {
		this.reponse = reponse;
	}

	
}
