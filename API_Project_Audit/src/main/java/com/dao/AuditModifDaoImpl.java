package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.AuditModif;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AuditModifDaoImpl implements AuditModifDao {
	@Autowired
	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(AuditModifDaoImpl.class);
	
	
	AuditModifDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	
	@Override
	public ArrayList<AuditModif> getModifiAudit(String id){
		
		ArrayList<AuditModif> auditModifs = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		ModeleDao modeleDao = daoFactory.getModeleDao();
		JuryDao juryDao = daoFactory.getJuryDao();
		MatiereDao matiereDao = daoFactory.getMatiereDao();
		LieuDao lieuDao = daoFactory.getLieuDao();

		
        try {
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM AuditModif WHERE id=?;");
			preparedStmt.setString(1, id);
			resultat = preparedStmt.executeQuery();        	
            
            while (resultat.next()) {
            	
            	AuditModif auditModif = new AuditModif();
            	
            	auditModif.setIdAudit(resultat.getInt("id_modif"));
            	auditModif.setId(resultat.getInt("id"));
            	auditModif.setOldDesignation(resultat.getString("oldDesignation"));
            	auditModif.setNewDesignation(resultat.getString("newDesignation"));
            	auditModif.setOldEtat(resultat.getString("oldEtat"));
            	auditModif.setNewEtat(resultat.getString("newEtat"));
            	auditModif.setOldDateDebut(resultat.getString("oldDateDebut"));
            	auditModif.setNewDateDebut(resultat.getString("newDateDebut"));
            	auditModif.setOldDateFin(resultat.getString("oldDateFin"));
            	auditModif.setNewDateFin(resultat.getString("newDateFin"));
            	auditModif.setOldDateLimite(resultat.getString("oldDateLimite"));
            	auditModif.setNewDateLimite(resultat.getString("newDateLimite"));
            	auditModif.setDateModif(resultat.getString("dateModif"));
            	auditModif.setOldNote(resultat.getInt("oldNote"));
            	auditModif.setNewNote(resultat.getInt("newNote"));
            	auditModif.setOldModele(modeleDao.getModeleById(resultat.getString("oldId_Modele")));
            	auditModif.setNewModele(modeleDao.getModeleById(resultat.getString("newId_Modele")));
            	auditModif.setOldJury(juryDao.getJuryById(resultat.getString("oldId_Jury")));
            	auditModif.setNewJury(juryDao.getJuryById(resultat.getString("newId_Jury")));
            	auditModif.setOldMatiere(matiereDao.getMatiereById(resultat.getString("oldId_Matiere")));
            	auditModif.setNewMatiere(matiereDao.getMatiereById(resultat.getString("newId_Matiere")));
            	auditModif.setOldLieu(lieuDao.getLieuById(resultat.getString("oldId_Lieu")));
            	auditModif.setNewLieu(lieuDao.getLieuById(resultat.getString("newId_Lieu")));
            	auditModif.setOldChamp(resultat.getString("oldChamp"));
            	auditModif.setNewChamp(resultat.getString("newChamp"));
            	auditModif.setCommentaire(resultat.getString("commentaire"));
            	
            	
            	auditModifs.add(auditModif);            	
            }
            

        } catch (SQLException e) {
			logger.log(Level.INFO, "sql problem", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		return auditModifs;
	}
}
	


