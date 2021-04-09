package com.beans;

public class UE {
	private static int idCount = 0;
	int id;
	String designation;
	String departement;
	
	public UE() {
		this.id=idCount;
		idCount++;
		}

	public UE(int id, String designation, String departement) {
		super();
		this.id = id;
		this.designation = designation;
		this.departement = departement;
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

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}
	
	

}
