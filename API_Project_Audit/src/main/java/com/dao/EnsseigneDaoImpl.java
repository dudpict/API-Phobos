package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Ensseigne;

public class EnsseigneDaoImpl implements EnsseigneDao {
	private static final Logger logger = Logger.getLogger(EnsseigneDaoImpl.class);
	private DaoFactory daoFactory;

	EnsseigneDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
	@Override
	public Ensseigne getEnsseigneByIdAndIdProf(int idMat,int idProf) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		Ensseigne ensseigne = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM enseigne WHERE id=? AND id_Professeur=? ;");
			preparedStmt.setInt(1, idMat);
			preparedStmt.setInt(2, idProf);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				int idMatiere = resultat.getInt("id");
				int idProfesseur = resultat.getInt("id_Professeur");
				ensseigne = new Ensseigne(idMatiere,idProfesseur);
			}
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getEnsseigneByIdAndIdProf", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		
		return ensseigne;
	}
	
	@Override
	public List<Ensseigne> getEnsseigneByIdProf(int idProf) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		Ensseigne ensseigne = null;
		List<Ensseigne> ensseigneList = new ArrayList<>();
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM enseigne WHERE id_Professeur=? ;");
			preparedStmt.setInt(1, idProf);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				int idMatiere = resultat.getInt("id");
				int idProfesseur = resultat.getInt("id_Professeur");
				ensseigne = new Ensseigne(idMatiere,idProfesseur);
				ensseigneList.add(ensseigne);
			}
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getEnsseigneByIdAndIdProf", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		
		return ensseigneList;
	}
	
	@Override
	public void addEnsseigne(int idMat,int idProf) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
				
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO enseigne(id, id_Professeur) VALUES (?,?);");
			preparedStmt.setInt(1, idMat);
			preparedStmt.setInt(2, idProf);
			preparedStmt.executeQuery();
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addEnsseigne", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
	}
	
	@Override
	public void deleteEnsseigne(int idMat,int idProf) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
				
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("DELETE FROM enseigne WHERE id=? AND id_Professeur=?;");
			preparedStmt.setInt(1, idMat);
			preparedStmt.setInt(2, idProf);
			preparedStmt.executeQuery();
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addEnsseigne", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
	}
	
}
