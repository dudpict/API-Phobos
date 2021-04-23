package com.beans;

import java.util.ArrayList;

public class Audit {

	private static int idCount = 0;
	int id;
	String designation;
	String etat;
	String dateDebut;
	String dateFin;
	String dateLimite;
	String dateModif;
	Matiere matiere;
	Modele modele;
	Jury jury;
	Lieu lieu;
	ArrayList<String> dateModifList;
	Boolean publie;

	public Audit() {
		this.id = idCount;
		idCount++;
	}

	public Audit(int id, String designation, String etat, String dateDebut, String dateFin, String dateLimite,
			String dateModif, ArrayList<String> dateModifList, Matiere matiere, Boolean publie, Modele modele, Jury jury, Lieu lieu) {

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
		this.publie = publie;
		this.lieu = lieu;
		this.modele = modele;
		this.jury = jury;
	}

	public Audit(String designation, String etat, String dateDebut, String dateFin, String dateLimite, String dateModif,
			ArrayList<String> dateModifList ) {
		super();
		this.designation = designation;
		this.etat = etat;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dateLimite = dateLimite;
		this.dateModif = dateModif;
		this.dateModifList = dateModifList;
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

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getDateLimite() {
		return dateLimite;
	}

	public void setDateLimite(String dateLimite) {
		this.dateLimite = dateLimite;
	}

	public String getDateModif() {
		return dateModif;
	}

	public void setDateModif(String dateModif) {
		this.dateModif = dateModif;
	}

	public ArrayList<String> getDateModifList() {
		return dateModifList;
	}

	public void setDateModifList(ArrayList<String> dateModifList) {
		this.dateModifList = dateModifList;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Boolean getPublie() {
		return publie;
	}

	public void setPublie(Boolean publie) {
		this.publie = publie;
	}

	public Modele getModele() {
		return modele;
	}

	public void setModele(Modele modele) {
		this.modele = modele;
	}

	public Jury getJury() {
		return jury;
	}

	public void setJury(Jury jury) {
		this.jury = jury;
	}

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}
	
	
	

}
