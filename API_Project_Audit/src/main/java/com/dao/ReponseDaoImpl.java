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
				if(!reponseCourte) {
					DaoFactory fact = new DaoFactory();
					ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
					reponseMultiples = reponseMultipleDao.getReponseMultipleByIdReponse(Integer.toString(idReponse));
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
			if(connexion!= null || statement!= null || resultat!= null) {
				try {
					connexion.close();
					statement.close();
					resultat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
				if(!reponseCourte) {
					DaoFactory fact = new DaoFactory();
					ReponseMultipleDao reponseMultipleDao = fact.getReponseMultipleDao();
					reponseMultiples = reponseMultipleDao.getReponseMultipleByIdReponse(Integer.toString(idReponse));
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
			if(connexion!= null || statement!= null || resultat!= null) {
				try {
					connexion.close();
					statement.close();
					resultat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
		
		return reponses;
	}
	
	
	
}
