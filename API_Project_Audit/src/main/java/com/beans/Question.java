package com.beans;

public class Question {
	private static int idCount = 0;
	String designation;
	String reponse;
	int id;
	
	public Question(String designation, String reponse, int id) {
		super();
		this.designation = designation;
		this.reponse = reponse;
		this.id = id;
	}
	
	public Question() {
		this.id=idCount;
		idCount++;
		}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
