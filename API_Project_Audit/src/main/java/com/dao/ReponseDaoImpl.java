package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Question;
import com.beans.Reponse;
import com.beans.ReponseMultiple;
import com.beans.Section;
import com.beans.TypeQuestion;

public class ReponseDaoImpl implements ReponseDao {

	private DaoFactory daoFactory;

	ReponseDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Reponse> getReponse() {
		ArrayList<Reponse> reponses = new ArrayList<Reponse>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;


		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Reponse;");
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				int id = resultat.getInt("id");
				int note = resultat.getInt("Note");
				boolean reponseCourte = resultat.getString("ReponseLongue");
				String reponseLongue = resultat.getString("ReponseLongue");
				ArrayList<ReponseMultiple> reponseMultiple;
				
				Reponse reponse = new Reponse();				
				reponse.setId(id);				
				reponse.setNote(note);				
				reponse.setReponseCourte(reponseCourte);				
				reponse.setReponseLongue(reponseLongue);				
				reponse.setReponseMultiple(reponseMultiple);
				reponses.add(reponse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connexion.close();
			statement.close();
			resultat.close();
		}
		
		return reponses;
	}
	
	
	
	
}
