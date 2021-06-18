package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Question;
import com.beans.Reponse;
import com.beans.ReponseMultiple;

public class ReponseDaoImpl implements ReponseDao {

	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(ReponseDaoImpl.class);
	private String [] sqlParamReponse = {"id","ReponseLongue","Note","ReponseCourte","ID_question"};

	ReponseDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Reponse getReponseById(String id) {
		Reponse reponses = new Reponse();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		try {			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Reponse where id=?;");
			preparedStmt.setString(1, id);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				int idReponse = resultat.getInt(sqlParamReponse[0]);
				int note = resultat.getInt(sqlParamReponse[2]);
				boolean reponseCourte = resultat.getBoolean(sqlParamReponse[3]);
				String reponseLongue = resultat.getString(sqlParamReponse[1]);
				ArrayList<ReponseMultiple> reponseMultiples = null;
								
				String idQuestion = resultat.getString(sqlParamReponse[4]);
				Question question = daoFactory.getQuestionDao().getQuestionById(idQuestion);
				String idTypeQuestion = Integer.toString(question.getTypeQuestion().getId());
				if(idTypeQuestion.equals("4")) {
					ReponseMultipleDao reponseMultipleDao = daoFactory.getReponseMultipleDao();
					reponseMultiples = reponseMultipleDao.getReponseMultipleByIdReponse(Integer.toString(idReponse));
				}
				
				Reponse reponse = new Reponse();				
				reponse.setId(idReponse);				
				reponse.setNote(note);				
				reponse.setReponseCourte(reponseCourte);				
				reponse.setReponseLongue(reponseLongue);				
				reponse.setReponseMultiple(reponseMultiples);
				reponse.setIdQuestion(Integer.parseInt(idQuestion));
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getReponseById", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);	
		}
		
		return reponses;
	}
	
	@Override
	public Reponse getReponsesByQuestionId(String id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		Reponse reponse = new Reponse();
		DaoFactory fact = new DaoFactory();
		
		try {			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Reponse where ID_question=?;");
			preparedStmt.setString(1, id);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				int idReponse = resultat.getInt(sqlParamReponse[0]);
				
				int note = resultat.getInt(sqlParamReponse[2]);
				boolean reponseCourte = resultat.getBoolean(sqlParamReponse[3]);
				String reponseLongue = resultat.getString(sqlParamReponse[1]);
				ArrayList<ReponseMultiple> reponseMultiples = null;
								
				String idQuestion = resultat.getString(sqlParamReponse[4]);
				
				Question question = daoFactory.getQuestionDao().getQuestionById(idQuestion);
				String idTypeQuestion = Integer.toString(question.getTypeQuestion().getId());
				if(idTypeQuestion.equals("4")) {
					ReponseMultipleDao reponseMultipleDao = daoFactory.getReponseMultipleDao();
					reponseMultiples = reponseMultipleDao.getReponseMultipleByIdReponse(Integer.toString(idReponse));
				}
								
								
				reponse.setId(idReponse);				
				reponse.setNote(note);				
				reponse.setReponseCourte(reponseCourte);				
				reponse.setReponseLongue(reponseLongue);				
				reponse.setReponseMultiple(reponseMultiples);
				reponse.setIdQuestion(Integer.parseInt(idQuestion));
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql probleme getReponsesByQuestionId", e);
		}finally {
			fact.close(connexion,statement,preparedStmt,resultat);
			
		}
		
		return reponse;
	}
	
	@Override
	public void addReponse (String reponseLongue, int note, Boolean reponseCourte, String idQuestion) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		DaoFactory fact = new DaoFactory();
		
		try {			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO Reponse (ReponseLongue, Note, ReponseCourte, ID_question) VALUES (?,?,?,?);");
			preparedStmt.setString(1, reponseLongue);
			preparedStmt.setInt(2, note);
			preparedStmt.setBoolean(3,reponseCourte);
			preparedStmt.setString(4, idQuestion);
			preparedStmt.executeQuery();
			
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql probleme add rep", e);
		}finally {
			fact.close(connexion,statement,preparedStmt,null);			
		}
	}
	
	@Override
	public void updateReponse (int id, String reponseLongue, int note, Boolean reponseCourte, String idQuestion) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		DaoFactory fact = new DaoFactory();

		try {			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("UPDATE Reponse SET ReponseLongue=?,Note=?,ReponseCourte=?,ID_question=? WHERE id=?;");
			preparedStmt.setString(1, reponseLongue);
			preparedStmt.setInt(2, note);
			preparedStmt.setBoolean(3, reponseCourte);
			preparedStmt.setString(4, idQuestion);
			preparedStmt.setInt(5,id);
			preparedStmt.executeQuery();
			
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem updateReponse", e);
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
			logger.log(Level.INFO, "sql problem deleteReponse", e);
		}finally {
			fact.close(connexion,statement,preparedStmt,null);			
		}
	}
}
