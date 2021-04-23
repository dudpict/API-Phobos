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
				String intitule = resultat.getString("intitule");
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
        		question.setIntitule(intitule);
        		
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
	
	
	@Override
	public void addQuestion(String Designation, String intitule, int id_section,int id_typeQuestion) {
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "INSERT INTO `question`(`Designation`,'intitule', `reponse`, `id_section`, `id_typeQuestion`) VALUES (?,?, null,?,?)";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, Designation);
			preparedStmt.setString(2, intitule);
			preparedStmt.setInt(3, id_section);
			preparedStmt.setInt(4,id_typeQuestion);
			preparedStmt.execute();
			connexion.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
		
	@Override
	public void updateQuestion(int id,String Designation,String intitule,String reponse, int id_section,int id_typeQuestion) {
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "UPDATE `question` SET `Designation`=?,'intitule'=?,`reponse`=?,`id_section`=?,`id_typeQuestion`=? WHERE `id`=?";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, Designation);
			preparedStmt.setString(2, intitule);
			preparedStmt.setString(3, reponse);
			preparedStmt.setInt(4, id_section);
			preparedStmt.setInt(5,id_typeQuestion);
			preparedStmt.setInt(6,id);
			preparedStmt.execute();
			connexion.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Question getQuestionById(String id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		Question question = new Question();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM question WHERE id="+id+";");
			connexion.close();

			while (resultat.next()) {
				int id2 = resultat.getInt("id");
				String designation = resultat.getString("designation");
				String intitule = resultat.getString("intitule");
				int typeQuestionID = resultat.getInt("id_typeQuestion");
				int sectionID = resultat.getInt("id_section");
				
				//Récupére l'instance de TypeQuestion via l'id
        		TypeQuestionDao typeQuestionDao = daoFactory.getTypeQuestionDao();
        		TypeQuestion typeQuestion  = typeQuestionDao.getTypeQuestionById(typeQuestionID);
        		
        		//Récupére l'instance de Section via l'id
        		SectionDao sectionDao = daoFactory.getSectionDao();
        		Section section  = sectionDao.getSectionById(sectionID);

        		//Remplir les attributs
				
				question.setId(id2);
				question.setDesignation(designation);
				question.setIntitule(intitule);
        		question.setSection(section);
        		question.setTypeQuestion(typeQuestion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return question;
	}
	
	@Override
	public ArrayList<Question> getQuestionsBySectionId(String id_section) {
		ArrayList<Question> questions = new ArrayList<Question>();
		Connection connexion = null;
		
		try {
			connexion = daoFactory.getConnection();
			String requete = "SELECT * FROM question WHERE `id_section`=?";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id_section);
			ResultSet rs = preparedStmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String designation = rs.getString("designation");
				String intitule = rs.getString("intitule");
				int typeQuestionID = rs.getInt("id_typeQuestion");
				int sectionID = rs.getInt("id_section");
				
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
				question.setIntitule(intitule);
        		question.setSection(section);
        		question.setTypeQuestion(typeQuestion);

				questions.add(question);
			}
			connexion.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return questions;
	}
	
}
