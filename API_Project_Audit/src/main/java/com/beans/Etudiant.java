package com.beans;

public class Etudiant {
	
	private static int idCount = 0;
	int id;
	String promo;
	String classe;
	
	public Etudiant() {
		this.id=idCount;
		idCount++;
		}

	public Etudiant(int id, String promo, String classe) {
		super();
		this.id = id;
		this.promo = promo;
		this.classe = classe;
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
	
	

}
