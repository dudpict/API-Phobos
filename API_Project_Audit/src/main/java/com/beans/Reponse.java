package com.beans;

import java.util.ArrayList;

/**
 * Réponse est une classe permettant de matérialiser une réponse à une question suivant le type de cette question.
 * @author divib
 *
 */
public class Reponse {
	//private static int idCount = 0;
	private int id;
	private String reponseLongue;
	private int note;
	private boolean reponseCourte;
	private ArrayList<ReponseMultiple> reponseMultiple;

	/**
	 * Constructeur de réponse
	 */
	public Reponse(){
		/*this.id = idCount;
		idCount++;*/
	}
	
	/**
	 * Constructeur d'une réponse de type reponseLongue
	 * @param reponse
	 */
	public Reponse(String reponse){
		//super();
		this.reponseLongue=reponse;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReponseLongue() {
		return reponseLongue;
	}

	public void setReponseLongue(String reponseLongue) {
		this.reponseLongue = reponseLongue;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public boolean getReponseCourte() {
		return reponseCourte;
	}

	public void setReponseCourte(boolean reponseCourte) {
		this.reponseCourte = reponseCourte;
	}

	public ArrayList<ReponseMultiple> getReponseMultiple() {
		return reponseMultiple;
	}

	public void setReponseMultiple(ArrayList<ReponseMultiple> reponseMultiple) {
		this.reponseMultiple = reponseMultiple;
	}

	/**
	 * Constructeur d'une réponse de type note
	 * @param reponse
	 */
	public Reponse(int reponse){
		//super();
		this.note=reponse;
	}
	
	/**
	 * Constructeur d'une réponse de type reponseCourte
	 * @param reponse
	 */
	public Reponse(boolean reponse){
		//super();
		this.reponseCourte=reponse;
	}
	
	/**
	 * Constructeur d'une réponse de type reponseMultiple
	 * @param reponse
	 */
	public Reponse(ArrayList<ReponseMultiple> reponse){
		//super();
		this.reponseMultiple=reponse;
	}
	
	/**
	 * Ajouter une reponseMultiple au choix que représente reponseMultiple
	 * @param reponse
	 */
	public void addReponseMultiple(ReponseMultiple reponse) {
		this.reponseMultiple.add(reponse);
	}
	
	/***
	 * Supprimer une reponseMultiple au choix que représente reponseMultiple
	 * @param reponse
	 */
	public void removeReponseMultiple(ReponseMultiple reponse) {
		this.reponseMultiple.remove(reponse);
	}
	
}

