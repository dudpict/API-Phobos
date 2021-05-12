package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Reponse;
import com.beans.ReponseMultiple;

public class ReponseDaoImpl implements ReponseDao {

	private DaoFactory daoFactory;

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
			if(connexion!= null || statement!= null || resultat!= null || connexion2!= null || statement2!= null || resultat2!= null) {
				try {
					connexion.close();
					statement.close();
					resultat.close();
					connexion2.close();
					statement2.close();
					resultat2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
		
		return reponses;
	}
	
	@Override
	public void addReponse (Reponse reponse, String idQuestion) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		
		try {			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO Reponse (ReponseLongue, Note, ReponseCourte, ID_question) VALUES (?,?,?,?);");
			preparedStmt.setString(1, reponse.getReponseLongue());
			preparedStmt.setInt(2, reponse.getNote());
			preparedStmt.setBoolean(3, reponse.getReponseCourte());
			preparedStmt.setString(4, idQuestion);
			preparedStmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(connexion!= null || statement!= null ) {
				try {
					connexion.close();
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
	}
}
