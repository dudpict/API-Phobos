package com.beans;

public class Ensseigne {
	int idMatiere;
	int idProf; 
	
	
	
	public Ensseigne(int idMatiere, int idProf) {
		super();
		this.idMatiere = idMatiere;
		this.idProf = idProf;
	}
	public int getIdMatiere() {
		return idMatiere;
	}
	public void setIdMatiere(int idMatiere) {
		this.idMatiere = idMatiere;
	}
	public int getIdProf() {
		return idProf;
	}
	public void setIdProf(int idProf) {
		this.idProf = idProf;
	}
	
	
}
