package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Reponse;
import com.beans.ReponseMultiple;

public class ReponseMultipleDaoImpl implements ReponseMultipleDao {

	private DaoFactory daoFactory;

	ReponseMultipleDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<ReponseMultiple> getReponseMultipleByIdReponse(String idReponse){
		
		ArrayList<ReponseMultiple> reponsesMultiples = new ArrayList<ReponseMultiple>();
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
                String ReponseMultiple = resultat.getString("ReponseMultiple");
				
				ReponseMultiple reponseMultiple = new ReponseMultiple();
				reponseMultiple.setId(id);
				reponseMultiple.setReponse(ReponseMultiple);
				reponsesMultiples.add(reponseMultiple);
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
		
		return reponsesMultiples;
	}	
	
	@Override
	public void addReponseMultiple(ReponseMultiple reponsepultiple, String idQuestion) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;

		Reponse reponse = new Reponse();		
		DaoFactory fact = new DaoFactory();
		ReponseDao reponseDao = fact.getReponseDao();
		reponseDao.addReponse(reponse, idQuestion);
		
		ArrayList<Reponse> reponses = reponseDao.getReponsesByQuestionId(idQuestion);
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Reponse WHERE ID_question = ?;");
			preparedStmt.setString(1, idQuestion);
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
