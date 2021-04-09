package com.beans;

public class Section {
	
	private static int idCount = 0;
	int id;
	String designation;
	
	public Section() {
		this.id=idCount;
		idCount++;
		}

	public Section(int id, String designation) {
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
