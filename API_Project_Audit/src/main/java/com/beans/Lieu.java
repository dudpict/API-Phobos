package com.beans;

public class Lieu {

	String ville;
	String etablissement;
	String batiment;
	String etage;
	String nomSalle;
	String numSalle;

	public Lieu(String ville, String etablissement, String batiment, String etage, String nomSalle, String numSalle) {
		super();
		this.ville = ville;
		this.etablissement = etablissement;
		this.batiment = batiment;
		this.etage = etage;
		this.nomSalle = nomSalle;
		this.numSalle = numSalle;
	}

	public Lieu() {
		super();
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	public String getBatiment() {
		return batiment;
	}

	public void setBatiment(String batiment) {
		this.batiment = batiment;
	}

	public String getEtage() {
		return etage;
	}

	public void setEtage(String etage) {
		this.etage = etage;
	}

	public String getNomSalle() {
		return nomSalle;
	}

	public void setNomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
	}

	public String getNumSalle() {
		return numSalle;
	}

	public void setNumSalle(String numSalle) {
		this.numSalle = numSalle;
	}

}
