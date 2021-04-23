package com.beans;

public class Question {
	private static int idCount = 0;
	String designation;
	String reponse;
	int id;
	TypeQuestion typeQuestion;
	Section section;
	
	public Question(String designation, String reponse, int id) {
		super();
		this.designation = designation;
		this.reponse = reponse;
		this.id = id;
	}
	
	public Question(String designation, String reponse, TypeQuestion typeQuestion, Section section) {
		super();
		this.designation = designation;
		this.reponse = reponse;
		this.typeQuestion = typeQuestion;
		this.section = section;
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

	public TypeQuestion getTypeQuestion() {
		return typeQuestion;
	}

	public void setTypeQuestion(TypeQuestion typeQuestion) {
		this.typeQuestion = typeQuestion;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}
	
	
	
	
}
