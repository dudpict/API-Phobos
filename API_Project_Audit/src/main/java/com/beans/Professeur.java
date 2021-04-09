package com.beans;

public class Professeur {
	private static int idCount = 0;
	int id;
	String bureau;
	
	public Professeur(int id, String bureau) {
		super();
		this.id = id;
		this.bureau = bureau;
	}
	
	public Professeur() {
		this.id=idCount;
		idCount++;
		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBureau() {
		return bureau;
	}

	public void setBureau(String bureau) {
		this.bureau = bureau;
	}
	
	

}
