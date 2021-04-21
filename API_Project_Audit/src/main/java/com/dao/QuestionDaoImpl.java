package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Question;
import com.beans.Section;
import com.beans.TypeQuestion;

public class QuestionDaoImpl implements QuestionDao {

	private DaoFactory daoFactory;

	QuestionDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Question> getQuestions() {
		ArrayList<Question> questions = new ArrayList<Question>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM question;");
			connexion.close();

			while (resultat.next()) {
				int id = resultat.getInt("id");
				String designation = resultat.getString("designation");
				int typeQuestionID = resultat.getInt("id_typeQuestion");
				int sectionID = resultat.getInt("id_section");
				
				//Récupére l'instance de TypeQuestion via l'id
        		TypeQuestionDao typeQuestionDao = daoFactory.getTypeQuestionDao();
        		TypeQuestion typeQuestion  = typeQuestionDao.getTypeQuestionById(typeQuestionID);
        		
        		//Récupére l'instance de Section via l'id
        		SectionDao sectionDao = daoFactory.getSectionDao();
        		Section section  = sectionDao.getSectionById(sectionID);

        		//Remplir les attributs
				Question question = new Question();
				question.setId(id);
				question.setDesignation(designation);
        		question.setSection(section);
        		question.setTypeQuestion(typeQuestion);

				questions.add(question);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questions;
	}

	@Override
	public void deleteQuestion(String id) {
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "DELETE FROM question WHERE id=?";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.execute();
			connexion.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//TODO vérifier les types des parametres de la fonction 
	@Override
	public void addQuestion(String id,String Designation, String id_section,String id_typeQuestion) {
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "INSERT INTO `question`(`id`, `Designation`, `reponse`, `id_section`, `id_typeQuestion`) VALUES (?,?, null,?,?)";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, Designation);
			preparedStmt.setString(3, id_section);
			preparedStmt.setString(4,id_typeQuestion);
			preparedStmt.execute();
			connexion.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//TODO vérifier les types des parametres de la fonction 
	@Override
	public void updateQuestion(String id,String Designation,String reponse, String id_section,String id_typeQuestion) {
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "UPDATE `question` SET `Designation`=?,`reponse`=?,`id_section`=?,`id_typeQuestion`=? WHERE `id`=?";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, Designation);
			preparedStmt.setString(2, reponse);
			preparedStmt.setString(3, id_section);
			preparedStmt.setString(4,id_typeQuestion);
			preparedStmt.setString(4,id);
			preparedStmt.execute();
			connexion.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
