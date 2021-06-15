package com.beans;

public class Option {
	int id; 
	int idProfesseur;
	String designation;
	
	
	public Option(int id, int idProfesseur, String designation) {
		super();
		this.id = id;
		this.idProfesseur = idProfesseur;
		this.designation = designation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdProfesseur() {
		return idProfesseur;
	}
	public void setIdProfesseur(int idProfesseur) {
		this.idProfesseur = idProfesseur;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
	
}
