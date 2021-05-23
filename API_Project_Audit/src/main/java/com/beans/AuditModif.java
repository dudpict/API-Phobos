package com.beans;

public class AuditModif {
	int id;
	int idAudit;
	String oldDesignation;
	String newDesignation;
	String oldEtat;
	String newEtat;
	String oldDateDebut;
	String newDateDebut;
	String oldDateFin;
	String newDateFin;
	String oldDateLimite;
	String newDateLimite;
	String dateModif;
	int oldNote;
	int newNote;
	Modele oldModele;
	Modele newModele;
	Jury oldJury;
	Jury newJury;
	Matiere oldMatiere;
	Matiere newMatiere;
	Lieu oldLieu;
	Lieu newLieu;
	String oldChamp;
	String newChamp;
	String commentaire;
	
	public AuditModif() {
		super();
	}

	public AuditModif(int id, int idAudit, String oldDesignation, String newDesignation, String oldEtat, String newEtat) {
		super();
		this.id = id;
		this.idAudit = idAudit;
		this.oldDesignation = oldDesignation;
		this.newDesignation = newDesignation;
		this.oldEtat = oldEtat;
		this.newEtat = newEtat;
		
	}
	
	public AuditModif(String oldDateDebut, String newDateDebut, String oldDateFin, String newDateFin, String oldDateLimite,
			String newDateLimite) {
		super();
		this.oldDateDebut = oldDateDebut;
		this.newDateDebut = newDateDebut;
		this.oldDateFin = oldDateFin;
		this.newDateFin = newDateFin;
		this.oldDateLimite = oldDateLimite;
		this.newDateLimite = newDateLimite;
	}
	
	public AuditModif(String dateModif, int oldNote, int newNote, Modele oldModele, Modele newModele,
			Jury oldJury, Jury newJury) {
		super();
		this.dateModif = dateModif;
		this.oldNote = oldNote;
		this.newNote = newNote;
		this.oldModele = oldModele;
		this.newModele = newModele;
		this.oldJury = oldJury;
		this.newJury = newJury;
			
	}
	
	public AuditModif( Matiere oldMatiere, Matiere newMatiere, Lieu oldLieu, Lieu newLieu,	String oldChamp, String newChamp, String commentaire) {
		super();
		this.oldMatiere = oldMatiere;
		this.newMatiere = newMatiere;
		this.oldLieu = oldLieu;
		this.newLieu = newLieu;
		this.oldChamp = oldChamp;
		this.newChamp = newChamp;
		this.commentaire = commentaire;	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdAudit() {
		return idAudit;
	}

	public void setIdAudit(int idAudit) {
		this.idAudit = idAudit;
	}

	public String getOldDesignation() {
		return oldDesignation;
	}

	public void setOldDesignation(String oldDesignation) {
		this.oldDesignation = oldDesignation;
	}

	public String getNewDesignation() {
		return newDesignation;
	}

	public void setNewDesignation(String newDesignation) {
		this.newDesignation = newDesignation;
	}

	public String getOldEtat() {
		return oldEtat;
	}

	public void setOldEtat(String oldEtat) {
		this.oldEtat = oldEtat;
	}

	public String getNewEtat() {
		return newEtat;
	}

	public void setNewEtat(String newEtat) {
		this.newEtat = newEtat;
	}

	public String getOldDateDebut() {
		return oldDateDebut;
	}

	public void setOldDateDebut(String oldDateDebut) {
		this.oldDateDebut = oldDateDebut;
	}

	public String getNewDateDebut() {
		return newDateDebut;
	}

	public void setNewDateDebut(String newDateDebut) {
		this.newDateDebut = newDateDebut;
	}

	public String getOldDateFin() {
		return oldDateFin;
	}

	public void setOldDateFin(String oldDateFin) {
		this.oldDateFin = oldDateFin;
	}

	public String getNewDateFin() {
		return newDateFin;
	}

	public void setNewDateFin(String newDateFin) {
		this.newDateFin = newDateFin;
	}

	public String getOldDateLimite() {
		return oldDateLimite;
	}

	public void setOldDateLimite(String oldDateLimite) {
		this.oldDateLimite = oldDateLimite;
	}

	public String getNewDateLimite() {
		return newDateLimite;
	}

	public void setNewDateLimite(String newDateLimite) {
		this.newDateLimite = newDateLimite;
	}

	public String getDateModif() {
		return dateModif;
	}

	public void setDateModif(String dateModif) {
		this.dateModif = dateModif;
	}

	public int getOldNote() {
		return oldNote;
	}

	public void setOldNote(int oldNote) {
		this.oldNote = oldNote;
	}

	public int getNewNote() {
		return newNote;
	}

	public void setNewNote(int newNote) {
		this.newNote = newNote;
	}

	public Modele getOldModele() {
		return oldModele;
	}

	public void setOldModele(Modele oldModele) {
		this.oldModele = oldModele;
	}

	public Modele getNewModele() {
		return newModele;
	}

	public void setNewModele(Modele newModele) {
		this.newModele = newModele;
	}

	public Jury getOldJury() {
		return oldJury;
	}

	public void setOldJury(Jury oldJury) {
		this.oldJury = oldJury;
	}

	public Jury getNewJury() {
		return newJury;
	}

	public void setNewJury(Jury newJury) {
		this.newJury = newJury;
	}

	public Matiere getOldMatiere() {
		return oldMatiere;
	}

	public void setOldMatiere(Matiere oldMatiere) {
		this.oldMatiere = oldMatiere;
	}

	public Matiere getNewMatiere() {
		return newMatiere;
	}

	public void setNewMatiere(Matiere newMatiere) {
		this.newMatiere = newMatiere;
	}

	public Lieu getOldLieu() {
		return oldLieu;
	}

	public void setOldLieu(Lieu oldLieu) {
		this.oldLieu = oldLieu;
	}

	public Lieu getNewLieu() {
		return newLieu;
	}

	public void setNewLieu(Lieu newLieu) {
		this.newLieu = newLieu;
	}

	public String getOldChamp() {
		return oldChamp;
	}

	public void setOldChamp(String oldChamp) {
		this.oldChamp = oldChamp;
	}

	public String getNewChamp() {
		return newChamp;
	}

	public void setNewChamp(String newChamp) {
		this.newChamp = newChamp;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

}
