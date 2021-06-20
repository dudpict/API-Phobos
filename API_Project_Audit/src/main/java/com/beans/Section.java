package com.beans;

public class Section {
	
	int id;
	String designation;
	Modele modele;
	
	public Section() {
		super();
		}

	public Section(int id, String designation, Modele modele) {
		super();
		this.id = id;
		this.designation = designation;
		this.modele = modele;
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

	public Modele getModele() {
		return modele;
	}

	public void setModele(Modele modele) {
		this.modele = modele;
	}
	
}
