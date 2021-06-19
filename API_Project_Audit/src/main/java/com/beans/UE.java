package com.beans;

public class UE {
	int id;
	String designation;
	String departement;
	Professeur responsable;
	Option option;	

	public UE() {
		super();
		}

	public UE(int id, String designation, String departement) {
		super();
		this.id = id;
		this.designation = designation;
		this.departement = departement;
	}

	

	

	public String getDesignation() {
		return designation;
	}
	
	public int getId() {
		return id;
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
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Professeur getResponsable() {
		return this.responsable;
	}
	
	public void setResponsable(Professeur prof ) {
		this.responsable = prof;
	}
	
	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

}
