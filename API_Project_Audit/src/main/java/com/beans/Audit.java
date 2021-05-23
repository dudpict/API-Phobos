package com.beans;

import java.util.List;

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
	List<String> dateModifList;
	Boolean publie;
	String semaineAudit;
	String modeDate;
	Equipe equipe; 
	int note;
	

	public Audit() {
		this.id = idCount;
		idCount++;
	}

	public Audit(int id, String designation, String etat, String dateDebut, String dateFin, String dateLimite) {

		super();
		this.id = id;
		this.designation = designation;
		this.etat = etat;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dateLimite = dateLimite;
		
	}
	
	public Audit(String dateModif, List<String> dateModifList, Matiere matiere, Boolean publie, Modele modele, Jury jury, Lieu lieu) {
		super();
		this.dateModif = dateModif;
		this.dateModifList = dateModifList;
		this.matiere = matiere;
		this.publie = publie;
		this.lieu = lieu;
		this.modele = modele;
		this.jury = jury;
	}

	public Audit(String designation, String etat, String dateDebut, String dateFin, String dateLimite, String dateModif,
			List<String> dateModifList ) {
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
	
	public void setModeDate(String modeDate) {
		this.modeDate=modeDate;
	}
	
	public String getModeDate() {
		return this.modeDate;
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

	public List<String> getDateModifList() {
		return dateModifList;
	}

	public void setDateModifList(List<String> dateModifList) {
		this.dateModifList =  dateModifList;
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
		return this.lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}
	
	public String getSemaineAudit() {
		return this.semaineAudit;
	}
	
	public void setSemaineAudit(String semaine) {
		this.semaineAudit=semaine;
	}
	
	public int getNote() {
		return this.note;
	}
	
	public void setNote(int note) {
		this.note=note;
	}
	
	public void setEquipe(Equipe equipe) {
		this.equipe=equipe;
	}
	
	public Equipe getEquipe() {
		return this.equipe;
	}
	
	
	

}
