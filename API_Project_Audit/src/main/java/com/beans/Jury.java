package com.beans;

public class Jury {
	
	private static int idCount = 0;
	int id;
	String designation;
	
	public Jury() {
		this.id=idCount;
		idCount++;
		}

	public Jury(int id, String designation) {
		super();
		this.id = id;
		this.designation = designation;
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
	
	
}
