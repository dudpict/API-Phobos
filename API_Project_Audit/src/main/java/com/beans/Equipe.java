package com.beans;

public class Equipe {
	

	int id;
	String designation;
	
	public Equipe() {
		super();
		}

	public Equipe(int id, String designation) {
		super();		
		this.designation = designation;
		this.id = id;
	}

	

	public void setId(int id) {
		this.id = id;
	}	

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public int getId() {
		return id;
	}
	
	
}
