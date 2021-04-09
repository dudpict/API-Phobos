package com.beans;

import java.util.ArrayList;
import java.util.Date;

public class Audit {
	
	private static int idCount = 0;
	int id;
	String designation;
	String etat;
	Date dateDebut;
	Date dateFin;
	Date dateLimite;
	Date dateModif;
	ArrayList<Date> dateModifList;
	Matiere matiere;
	
	public Audit() {
		this.id=idCount;
		idCount++;
		}
	
	public Audit(int id, String designation, String etat, Date dateDebut, Date dateFin, Date dateLimite, Date dateModif,
			ArrayList<Date> dateModifList, Matiere matiere) {
		super();
		this.id = id;
		this.designation = designation;
		this.etat = etat;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dateLimite = dateLimite;
		this.dateModif = dateModif;
		this.dateModifList = dateModifList;
		this.matiere = matiere;
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

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Date getDateLimite() {
		return dateLimite;
	}

	public void setDateLimite(Date dateLimite) {
		this.dateLimite = dateLimite;
	}

	public Date getDateModif() {
		return dateModif;
	}

	public void setDateModif(Date dateModif) {
		this.dateModif = dateModif;
	}

	public ArrayList<Date> getDateModifList() {
		return dateModifList;
	}

	public void setDateModifList(ArrayList<Date> dateModifList) {
		this.dateModifList = dateModifList;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	
	

}