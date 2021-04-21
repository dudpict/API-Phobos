package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.TypeQuestion;

public class TypeQuestionDaoImpl implements TypeQuestionDao {
	
	private DaoFactory daoFactory;

	TypeQuestionDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

	@Override
	public ArrayList<TypeQuestion> getTypeQuestions() {
		ArrayList<TypeQuestion> typeQuestions = new ArrayList<TypeQuestion>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM typeQuestion;");
            connexion.close();

            while (resultat.next()) {
            	int id = resultat.getInt("id");
                String designation = resultat.getString("designation");
        		        		
        		//TODO IMPLEMENTER EQUIPE ET ROLE MODELE
                TypeQuestion typeQuestion = new TypeQuestion();
                typeQuestion.setId(id);
                typeQuestion.setDesignation(designation);

                typeQuestions.add(typeQuestion);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeQuestions;
	}
	
	@Override
	public TypeQuestion getTypeQuestionById(int typeQuestionID) {
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        TypeQuestion typeQuestion=new TypeQuestion();
        
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM typeQuestion WHERE id="+typeQuestionID+";");
            
            connexion.close();

            while (resultat.next()) {
            	int id = resultat.getInt("id");
                String designation = resultat.getString("designation");
     
                typeQuestion.setId(id);
                typeQuestion.setDesignation(designation);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeQuestion;
	}

	@Override
	public void deleteTypeQuestion(String id) {
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "DELETE FROM typeQuestion WHERE id=?";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.execute();
			connexion.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

}
