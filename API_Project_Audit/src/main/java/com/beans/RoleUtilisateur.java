package com.beans;

public class RoleUtilisateur {
	int id;
	String designation;
	
	public RoleUtilisateur() {
		super();
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
