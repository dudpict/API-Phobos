package com.beans;

public class Etudiant {
	
	private static int idCount = 0;
	int id;
	String promo;
	String classe;
	Personne personne;
	
	public Etudiant() {
		this.id=idCount;
		idCount++;
		}

	public Etudiant(String promo, String classe,Personne personne) {
		super();
		this.promo = promo;
		this.classe = classe;
		this.personne = personne;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	
	
	
	

}
