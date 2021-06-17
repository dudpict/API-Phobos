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

import com.beans.TypeQuestion;

public class TypeQuestionDaoImpl implements TypeQuestionDao {

	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(TypeQuestionDaoImpl.class);
	private String [] sqlParamTypeQuestion = {"id","Designation"};
	
	TypeQuestionDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<TypeQuestion> getTypeQuestions() {
		List<TypeQuestion> typeQuestions = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM typeQuestion;");
			connexion.close();
			while (resultat.next()) {
				int id = resultat.getInt(sqlParamTypeQuestion[0]);
				String designation = resultat.getString(sqlParamTypeQuestion[1]);

				TypeQuestion typeQuestion = new TypeQuestion();
				typeQuestion.setId(id);
				typeQuestion.setDesignation(designation);
				typeQuestions.add(typeQuestion);

			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getTypeQuestions", e);
		}finally {
			daoFactory.close(connexion,statement,null,resultat);	
		}
		return typeQuestions;
	}

	@Override
	public void deleteTypeQuestion(String id) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "DELETE FROM typeQuestion WHERE id=?";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.execute();
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem deleteTypeQuestion", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,null);	
		}
	}

	@Override
	public TypeQuestion getTypeQuestionById(String id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;
		TypeQuestion typeQuestion = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM typeQuestion WHERE id=? ;");
			preparedStatement.setString(1, id);
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				int id2 = resultat.getInt(sqlParamTypeQuestion[0]);
				String designation = resultat.getString(sqlParamTypeQuestion[1]);
				
				typeQuestion = new TypeQuestion (id2,designation);

			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getTypeQuestionById", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		return typeQuestion;
	}

}
