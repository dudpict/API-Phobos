package com.beans;

/**
 * Modèle est une classe permettant de créer, modifier ou supprimer un modèle d'audit.
 * @author divib
 *
 */

public class Modele {
	private static int idCount = 0;
	private int id;
	private String designation;
	
	/**
	 * Constructeur de modèle
	 */
	public Modele() {
		this.id=idCount;
		idCount++;
	}

	/**
	 * Constructeur de modèle avec en paramètre sa désignation.
	 * @param designation
	 */
	public Modele(String designation) {
		super();
		this.designation = designation;
	}
	
	
	/**
	 * Constructeur de modèle avec en paramètre sa désignation et son id.
	 * @param designation
	 */
	public Modele(String designation, int id) {
		this.id = id;
		this.designation = designation;
	}

	/**
	 * Getter de l'id d'un modèle
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter sur l'id d'un modèle
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter de la désignation d'un modèle
	 * @return
	 */
	public String getDesignation() {
		return designation;
	}
	
	/**
	 * Setter sur la désignation d'un modèle
	 * @param designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
}
