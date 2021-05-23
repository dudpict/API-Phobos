package com.beans;

public class Notification {

	int id;
	String designation;
	String typeNotif;
	//DATE ou GROUPEETUDIANT
	String etat;
	//NONLU ou LU
	String dateDeNotification;
	int idaudit;
	int idpersonne;
	
	public Notification() {			
		super();
	}

	public Notification(int id, String designation, String typeNotif, String etat, String dateDeNotification,
			int idaudit, int idpersonne) {
		super();
		this.id = id;
		this.designation = designation;
		this.typeNotif = typeNotif;
		this.etat = etat;
		this.dateDeNotification = dateDeNotification;
		this.idaudit = idaudit;
		this.idpersonne = idpersonne;
		
	}
	
	public Notification(String designation, String typeNotif, String etat, String dateDeNotification) {
		super();
		this.designation = designation;
		this.typeNotif = typeNotif;
		this.etat = etat;
		this.dateDeNotification = dateDeNotification;
		
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

	public String getTypeNotif() {
		return typeNotif;
	}

	public void setTypeNotif(String typeNotif) {
		this.typeNotif = typeNotif;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getDateDeNotification() {
		return dateDeNotification;
	}

	public void setDateDeNotification(String dateDeNotification) {
		this.dateDeNotification = dateDeNotification;
	}

	public int getIdaudit() {
		return idaudit;
	}

	public void setIdaudit(int idaudit) {
		this.idaudit = idaudit;
	}
	
	public int getIdpersonne() {
		return idpersonne;
	}

	public void setIdpersonne(int idpersonne) {
		this.idpersonne = idpersonne;
	}

}
