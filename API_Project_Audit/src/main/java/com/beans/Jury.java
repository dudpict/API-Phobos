package com.beans;

public class Jury {
	
	int id;
	String designation;
	
	public Jury() {
		super();
		}

	public Jury(int id, String designation) {
		super();
		this.id = id;
		this.designation = designation;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
}
