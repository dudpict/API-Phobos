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

public class ReponseDaoImpl implements ReponseDao {

	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(ReponseMultipleDaoImpl.class);

	ReponseDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Reponse> getReponseById(String id) {
		ArrayList<Reponse> reponses = new ArrayList<Reponse>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		Connection connexion2 = null;
		Statement statement2 = null;
		ResultSet resultat2 = null;
		PreparedStatement preparedStmt2 = null;

		try {			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Reponse where id=?;");
			preparedStmt.setString(1, id);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				int idReponse = resultat.getInt("id");
				int note = resultat.getInt("Note");
				boolean reponseCourte = resultat.getBoolean("ReponseLongue");
				String reponseLongue = resultat.getString("ReponseLongue");
				ArrayList<ReponseMultiple> reponseMultiples = null;
				
				String idQuestion = resultat.getString("ID_question");
				connexion2 = daoFactory.getConnection();
				statement2 = connexion2.createStatement();
				preparedStmt2 = connexion2.prepareStatement("SELECT * FROM question where id=?;");
				preparedStmt2.setString(1, idQuestion);
				resultat2 = preparedStmt2.executeQuery();
				
				while (resultat2.next()) {
					String idTypeQuestion = resultat2.getString("id_typeQuestion");
					if(idTypeQuestion.equals("4")) {
						DaoFactory fact = new DaoFactory();
						ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
						reponseMultiples = reponseMultipleDao.getReponseMultipleByIdReponse(Integer.toString(idReponse));
					}
				}
				
				Reponse reponse = new Reponse();				
				reponse.setId(idReponse);				
				reponse.setNote(note);				
				reponse.setReponseCourte(reponseCourte);				
				reponse.setReponseLongue(reponseLongue);				
				reponse.setReponseMultiple(reponseMultiples);
				reponses.add(reponse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(connexion!= null) {
					connexion.close();
					statement.close();
					resultat.close();
				}
				if(connexion2!= null) {
					
					connexion2.close();
					statement2.close();
					resultat2.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return reponses;
	}
	
	@Override
	public ArrayList<Reponse> getReponsesByQuestionId(String id) {
		ArrayList<Reponse> reponses = new ArrayList<Reponse>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		Connection connexion2 = null;
		Statement statement2 = null;
		ResultSet resultat2 = null;
		PreparedStatement preparedStmt2 = null;
		DaoFactory fact = new DaoFactory();

		try {			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Reponse where ID_question=?;");
			preparedStmt.setString(1, id);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				int idReponse = resultat.getInt("id");
				int note = resultat.getInt("Note");
				boolean reponseCourte = resultat.getBoolean("ReponseLongue");
				String reponseLongue = resultat.getString("ReponseLongue");
				ArrayList<ReponseMultiple> reponseMultiples = null;
								
				String idQuestion = resultat.getString("ID_question");
				connexion2 = daoFactory.getConnection();
				statement2 = connexion2.createStatement();
				preparedStmt2 = connexion2.prepareStatement("SELECT * FROM question where id=?;");
				preparedStmt2.setString(1, idQuestion);
				resultat2 = preparedStmt2.executeQuery();
				
				while (resultat2.next()) {
					String idTypeQuestion = resultat2.getString("id_typeQuestion");
					if(idTypeQuestion.equals("4")) {
						ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
						reponseMultiples = reponseMultipleDao.getReponseMultipleByIdReponse(Integer.toString(idReponse));
					}
				}
								
				Reponse reponse = new Reponse();				
				reponse.setId(idReponse);				
				reponse.setNote(note);				
				reponse.setReponseCourte(reponseCourte);				
				reponse.setReponseLongue(reponseLongue);				
				reponse.setReponseMultiple(reponseMultiples);
				reponses.add(reponse);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql probleme add rep", e);
		}finally {
			fact.close(connexion,statement,preparedStmt,resultat);
			fact.close(connexion2,statement2,preparedStmt2,resultat2);
		}
		
		return reponses;
	}
	
	@Override
	public void addReponse (String ReponseLongue, int Note, Boolean ReponseCourte, String idQuestion) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		DaoFactory fact = new DaoFactory();
		
		try {			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO Reponse (ReponseLongue, Note, ReponseCourte, ID_question) VALUES (?,?,?,?);");
			preparedStmt.setString(1, ReponseLongue);
			preparedStmt.setInt(2, Note);
			preparedStmt.setBoolean(3,ReponseCourte);
			preparedStmt.setString(4, idQuestion);
			preparedStmt.executeQuery();
			
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql probleme add rep", e);
		}finally {
			fact.close(connexion,statement,preparedStmt,null);			
		}
	}
	
	@Override
	public void updateReponse (int id,String ReponseLongue, int note, Boolean ReponseCourte, String idQuestion) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		DaoFactory fact = new DaoFactory();

		try {			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("UPDATE Reponse SET ReponseLongue=?,Note=?,ReponseCourte=?,ID_question=? WHERE id=?;");
			preparedStmt.setString(1, ReponseLongue);
			preparedStmt.setInt(2, note);
			preparedStmt.setBoolean(3, ReponseCourte);
			preparedStmt.setString(4, idQuestion);
			preparedStmt.setInt(5,id);
			preparedStmt.executeQuery();
			
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem", e);
		}finally {
			fact.close(connexion,statement,preparedStmt,null);			
		}
	}
	
	@Override
	public void deleteReponse (String id) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		DaoFactory fact = new DaoFactory();

		try {			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("DELETE FROM Reponse WHERE id=?;");
			preparedStmt.setString(1, id);
			preparedStmt.executeQuery();
			
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem", e);
		}finally {
			fact.close(connexion,statement,preparedStmt,null);			
		}
	}
}
