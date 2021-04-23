package com.beans;

import java.util.ArrayList;


/**
 * Modèle est une classe permettant de créer, modifier ou supprimer un modèle d'audit.
 * @author divib
 *
 */

public class Modele {
	private static int idCount = 0;
	private int id;
	private String designation;
	private ArrayList<Section> sections;
	
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
		this.sections = new ArrayList<Section>();
	}
	
	
	/**
	 * Constructeur de modèle avec en paramètre sa désignation et son id.
	 * @param designation
	 */
	public Modele(String designation, int id) {
		this.id = id;
		this.designation = designation;
		this.sections = new ArrayList<Section>();
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
	
	/**
	 * Getter sur la liste de section d'un modèle
	 * @return
	 */
	public ArrayList<Section> getSections() {
		return sections;
	}
	
	/**
	 * Setter sur la liste de section d'un modèle
	 * @param sections
	 */
	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}
	
	/**
	 * Méthode permettant l'ajout de la section passée en paramètre
	 * @param section
	 */
	public void addSection(Section section) {
		this.sections.add(section);
	}
	
	/**
	 * Méthode permettant la suppression de la section passée en paramètre
	 * @param section
	 */
	public void removeSection(Section section) {
		this.sections.remove(section);
	}
	
//	public ArrayList<Section> getSections() throws DaoException {
//		UserDao userDao = DaoFactory.getImpl();
//		this.sections = userDao.getSections(this);
//		return this.sections;
//	}
//	
//	public void setSections() throws DaoException {
//		UserDao userDao = DaoFactory.getImpl();
//		this.sections = userDao.getSections(this);
//	}
	
	/**
	 * Retourne le nombre de section contenu dans la liste de section
	 * @return
	 */
	public int getNombreSections() {
		if(this.sections.isEmpty())
			return 0;
		return this.sections.size();
	}
	
	/**
	 * Méthode permettant la permutation de deux éléments dans une liste de section
	 * @param t
	 * @param m
	 * @param n
	 */
	public void échangerÉléments(ArrayList<Section> t, int m, int n) {
	    Section temp = t.get(m);

	    t.set(m, t.get(n));
	    t.set(n, temp);
	  }

}
