package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Reponse;
import com.beans.ReponseMultiple;

public class ReponseMultipleDaoImpl implements ReponseMultipleDao {

	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(ReponseMultipleDaoImpl.class);
	
	ReponseMultipleDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<ReponseMultiple> getReponseMultipleByIdReponse(String idReponse){
		DaoFactory fact = new DaoFactory();
		ArrayList<ReponseMultiple> reponsesMultiples = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM ReponseMultiple WHERE id_Reponse = ?;");
			preparedStmt.setString(1, idReponse);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				int id = resultat.getInt("ID");				
				ReponseMultiple reponseMultiple = new ReponseMultiple();
				reponseMultiple.setId(id);
				reponseMultiple.setReponse(resultat.getString("ReponseMultiple"));
				reponsesMultiples.add(reponseMultiple);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getReponseMultipleByIdReponse", e);

		}finally {
			fact.close(connexion,statement,preparedStmt,resultat);			
		}
		
		return reponsesMultiples;
	}	
	
	@Override
	public void addReponseMultiple(String reponse, String idQuestion, Boolean cochee ) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		int idReponse = 0;
				
		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		
		
		ArrayList<Reponse> reponses = reponseDao.getReponsesByQuestionId(idQuestion);
		if (reponses.isEmpty()) {
			reponseDao.addReponse(null,-1,false, idQuestion);
			reponses = reponseDao.getReponsesByQuestionId(idQuestion);
		}
		for(Reponse reponseL : reponses) {
			idReponse = reponseL.getId();
		}
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO ReponseMultiple(ReponseMultiple, id_Reponse,cochee) VALUES (?,?,?);");
			preparedStmt.setString(1, reponse);
			preparedStmt.setInt(2, idReponse);
			preparedStmt.setBoolean(3, cochee);
			preparedStmt.executeQuery();
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addReponseMultiple", e);
		}finally {
			fact.close(connexion,statement,preparedStmt,null);			
		}
	}
	
	@Override
	public void updateReponseMultiple(String reponseMultiple, int id ) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		DaoFactory fact = new DaoFactory();
				
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("UPDATE ReponseMultiple SET ReponseMultiple=? WHERE ID=?;");
			preparedStmt.setString(1, reponseMultiple);
			preparedStmt.setInt(2, id);
			preparedStmt.executeQuery();
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem", e);
		}finally {
			fact.close(connexion,statement,preparedStmt,null);			
		}
	}
	
	@Override
	public void deleteReponseMultiple(String id) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		DaoFactory fact = new DaoFactory();
				
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("DELETE FROM ReponseMultiple WHERE ID=?;");
			preparedStmt.setString(1, id);
			preparedStmt.executeQuery();
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem", e);
		}finally {
			fact.close(connexion,statement,preparedStmt,null);			
		}
	}	
}
