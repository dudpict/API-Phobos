package com.beans;

public class Matiere {
	int id;
	String designation;
	String departement;
	int effectif;
	Professeur responsable;
	UE ue;
	
	

	public Matiere() {
		super();
		}

	public Matiere(int id, String designation, String departement, int effectif, Professeur responsable) {
		super();
		this.id = id;
		this.designation = designation;
		this.departement = departement;
		this.effectif = effectif;
		this.responsable = responsable;
	}
	
	public UE getUe() {
		return ue;
	}

	public void setUe(UE ue) {
		this.ue = ue;
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

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public int getEffectif() {
		return effectif;
	}

	public void setEffectif(int effectif) {
		this.effectif = effectif;
	}

	public Professeur getResponsable() {
		return responsable;
	}

	public void setResponsable(Professeur responsable) {
		this.responsable = responsable;
	}
	
	
	
	

}
