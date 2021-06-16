package com.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Réponse est une classe permettant de matérialiser une réponse à une question suivant le type de cette question.
 * @author divib
 *
 */
public class Reponse {
	private int id;
	private String reponseLongue;
	private int note;
	private boolean reponseCourte;
	private int idQuestion;
	private ArrayList<ReponseMultiple> reponseMultiple;

	/**
	 * Constructeur de réponse
	 */
	public Reponse(){
		super();
	}
	
	/**
	 * Constructeur d'une réponse de type reponseLongue
	 * @param reponse
	 */
	
	
	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	public Reponse(String reponse){
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

	public List<ReponseMultiple> getReponseMultiple() {
		return reponseMultiple;
	}

	public void setReponseMultiple(List<ReponseMultiple> reponseMultiple) {
		this.reponseMultiple = (ArrayList<ReponseMultiple>) reponseMultiple;
	}

	/**
	 * Constructeur d'une réponse de type note
	 * @param reponse
	 */
	public Reponse(int reponse){
		this.note=reponse;
	}
	
	/**
	 * Constructeur d'une réponse de type reponseCourte
	 * @param reponse
	 */
	public Reponse(boolean reponse){
		this.reponseCourte=reponse;
	}
	
	/**
	 * Constructeur d'une réponse de type reponseMultiple
	 * @param reponse
	 */
	public Reponse(List<ReponseMultiple> reponse){
		this.reponseMultiple=(ArrayList<ReponseMultiple>) reponse;
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

