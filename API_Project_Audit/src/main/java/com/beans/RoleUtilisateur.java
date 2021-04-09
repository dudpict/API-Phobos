package com.beans;

public class RoleUtilisateur {
	private static int idCount = 0;
	int id;
	String designation;
	
	public RoleUtilisateur() {
		this.id=idCount;
		idCount++;
		}

	public RoleUtilisateur(int id, String designation) {
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
