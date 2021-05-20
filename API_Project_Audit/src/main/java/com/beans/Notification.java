package com.beans;

public class Notification {

	int id;
	String designation;
	String typeNotif;
	//DATE ou GROUPEETUDIANT
	String etat;
	//NONLU ou LU
	String dateDeNotification;
	int id_audit;

	public Notification() {
		super();
	}
	
	public Notification(int id, String designation, String typeNotif, String etat, String dateDeNotification,int id_audit) {
		super();
		this.id = id;
		this.designation = designation;
		this.typeNotif = typeNotif;
		this.etat = etat;
		this.dateDeNotification = dateDeNotification;
		this.id_audit = id_audit;
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

	public int getId_audit() {
		return id_audit;
	}

	public void setId_audit(int id_audit) {
		this.id_audit = id_audit;
	}

}
