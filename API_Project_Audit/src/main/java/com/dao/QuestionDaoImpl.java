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
import com.beans.Section;
import com.beans.TypeQuestion;

public class QuestionDaoImpl implements QuestionDao {
	private String [] sqlParamQuestionr = {"id","Designation","intitule","id_section","id_typeQuestion","RoleEnseignant","RoleEleve"};
	private static final Logger logger = Logger.getLogger(QuestionDaoImpl.class);
	
	private DaoFactory daoFactory;

	QuestionDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Question> getQuestions() {
		ArrayList<Question> questions = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM question;");
			connexion.close();
			while (resultat.next()) {
				int id = resultat.getInt(sqlParamQuestionr[0]);
				String designation = resultat.getString(sqlParamQuestionr[1]);
				String intitule = resultat.getString(sqlParamQuestionr[2]);
				int typeQuestionID = resultat.getInt(sqlParamQuestionr[4]);
				int sectionID = resultat.getInt(sqlParamQuestionr[3]);

				
				// Récupére l'instance de TypeQuestion via l'id
				TypeQuestionDao typeQuestionDao = daoFactory.getTypeQuestionDao();
				TypeQuestion typeQuestion = typeQuestionDao.getTypeQuestionById(Integer.toString(typeQuestionID));

				// Récupére l'instance de Section via l'id
				SectionDao sectionDao = daoFactory.getSectionDao();
				Section section = sectionDao.getSectionById(sectionID);

				// Remplir les attributs
				Question question = new Question();
				boolean roleEnseignant =  resultat.getBoolean(sqlParamQuestionr[5]);
				boolean roleEleve =  resultat.getBoolean(sqlParamQuestionr[6]);
				question.setRoleEnseignant(roleEnseignant);
				question.setRoleEleve(roleEleve);
				
				question.setId(id);
				question.setDesignation(designation);
				question.setSection(section);
				question.setTypeQuestion(typeQuestion);
				question.setIntitule(intitule);

				questions.add(question);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getQuestions", e);
		}finally {
			daoFactory.close(connexion,statement,null,resultat);			
		}
		
		return questions;
	}

	@Override
	public void deleteQuestion(String id) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;
		try {
			connexion = daoFactory.getConnection();
			String requete = "DELETE FROM question WHERE id=?";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.execute();
			connexion.close();

		} catch (SQLException e) {			
			logger.log(Level.INFO, "sql problem getQuestions", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,null);			
		}
	}

	@Override
	public void addQuestion(String designation, String intitule, int idSection, int idTypeQuestion, boolean eleves, boolean professeur) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			String requete ="INSERT INTO `question` (`Designation`, `intitule`, `id_section`, `id_typeQuestion`,RoleEleve,RoleEnseignant) VALUES (?,?,?,?,?,?);";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, designation);
			preparedStmt.setString(2, intitule);
			preparedStmt.setInt(3, idSection);
			preparedStmt.setInt(4, idTypeQuestion);
			preparedStmt.setBoolean(5, eleves);
			preparedStmt.setBoolean(6, professeur);
			preparedStmt.execute();

		} catch (SQLException e) {			
			logger.log(Level.INFO, "sql problem addQuestion", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,null);			
		}

		
	}

	@Override
	public void updateQuestion(int id, String designation, String intitule, String reponse, int idSection,
			int idTypeQuestion) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "UPDATE `question` SET `Designation`=?,'intitule'=?,`id_section`=?,`id_typeQuestion`=? WHERE `id`=?";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, designation);
			preparedStmt.setString(2, intitule);
			preparedStmt.setInt(3, idSection);
			preparedStmt.setInt(4, idTypeQuestion);
			preparedStmt.setInt(5, id);
			preparedStmt.execute();

		}  catch (SQLException e) {			
			logger.log(Level.INFO, "sql problem updateQuestion", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,null);			
		}
	}

	@Override
	public Question getQuestionById(String id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;
		Question question = new Question();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM question WHERE id=? ;");
			preparedStatement.setString(1, id);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				int id2 = resultat.getInt(sqlParamQuestionr[0]);
				String designation = resultat.getString(sqlParamQuestionr[1]);
				String intitule = resultat.getString(sqlParamQuestionr[2]);
				int typeQuestionID = resultat.getInt(sqlParamQuestionr[4]);
				int sectionID = resultat.getInt(sqlParamQuestionr[3]);

				// Récupére l'instance de TypeQuestion via l'id
				TypeQuestionDao typeQuestionDao = daoFactory.getTypeQuestionDao();
				TypeQuestion typeQuestion = typeQuestionDao.getTypeQuestionById(Integer.toString(typeQuestionID));

				// Récupére l'instance de Section via l'id
				SectionDao sectionDao = daoFactory.getSectionDao();
				Section section = sectionDao.getSectionById(sectionID);

				// Remplir les attributs
				boolean roleEnseignant =  resultat.getBoolean(sqlParamQuestionr[5]);
				boolean roleEleve =  resultat.getBoolean(sqlParamQuestionr[6]);
				question.setRoleEnseignant(roleEnseignant);
				question.setRoleEleve(roleEleve);
				question.setId(id2);
				question.setDesignation(designation);
				question.setIntitule(intitule);
				question.setSection(section);
				question.setTypeQuestion(typeQuestion);
			}
		}catch (SQLException e) {			
			logger.log(Level.INFO, "sql problem getQuestionById", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);			
		}
		return question;
	}
	
	@Override
	public Question getQuestionByNom(String nom) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		Question question = null;
		PreparedStatement preparedStmt = null;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM question WHERE Designation=?;");
			preparedStmt.setString(1, nom);
			resultat = preparedStmt.executeQuery();			
			connexion.close();

			while (resultat.next()) {
				int id2 = resultat.getInt(sqlParamQuestionr[0]);
				String designation = resultat.getString(sqlParamQuestionr[1]);
				String intitule = resultat.getString(sqlParamQuestionr[2]);
				int typeQuestionID = resultat.getInt(sqlParamQuestionr[4]);
				int sectionID = resultat.getInt(sqlParamQuestionr[3]);

				// Récupére l'instance de TypeQuestion via l'id
				TypeQuestionDao typeQuestionDao = daoFactory.getTypeQuestionDao();
				TypeQuestion typeQuestion = typeQuestionDao.getTypeQuestionById(Integer.toString(typeQuestionID));

				// Récupére l'instance de Section via l'id
				SectionDao sectionDao = daoFactory.getSectionDao();
				Section section = sectionDao.getSectionById(sectionID);

				// Remplir les attributs
				question = new Question();
				
				boolean roleEnseignant =  resultat.getBoolean(sqlParamQuestionr[5]);
				boolean roleEleve =  resultat.getBoolean(sqlParamQuestionr[6]);
				question.setRoleEnseignant(roleEnseignant);
				question.setRoleEleve(roleEleve);
				question.setId(id2);
				question.setDesignation(designation);
				question.setIntitule(intitule);
				question.setSection(section);
				question.setTypeQuestion(typeQuestion);
			}
		} catch (SQLException e) {			
			logger.log(Level.INFO, "sql problem getQuestionByNom", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		return question;
	}

	@Override
	public ArrayList<Question> getQuestionsBySectionId(String idSection) {
		ArrayList<Question> questions = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "SELECT * FROM question WHERE `id_section`=?";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, idSection);
			rs = preparedStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(sqlParamQuestionr[0]);
				String designation = rs.getString(sqlParamQuestionr[1]);
				String intitule = rs.getString(sqlParamQuestionr[2]);
				int typeQuestionID = rs.getInt(sqlParamQuestionr[4]);
				int sectionID = rs.getInt(sqlParamQuestionr[3]);

				// Récupére l'instance de TypeQuestion via l'id
				TypeQuestionDao typeQuestionDao = daoFactory.getTypeQuestionDao();
				TypeQuestion typeQuestion = typeQuestionDao.getTypeQuestionById(Integer.toString(typeQuestionID));

				// Récupére l'instance de Section via l'id
				SectionDao sectionDao = daoFactory.getSectionDao();
				Section section = sectionDao.getSectionById(sectionID);

				// Remplir les attributs
				Question question = new Question();
				question.setId(id);
				question.setDesignation(designation);
				question.setIntitule(intitule);
				question.setSection(section);
				question.setTypeQuestion(typeQuestion);
				boolean roleEnseignant =  rs.getBoolean(sqlParamQuestionr[5]);
				boolean roleEleve =  rs.getBoolean(sqlParamQuestionr[6]);
				question.setRoleEnseignant(roleEnseignant);
				question.setRoleEleve(roleEleve);
				questions.add(question);
			}
			connexion.close();
			preparedStmt.close();
			rs.close();

		}catch (SQLException e) {			
			logger.log(Level.INFO, "sql problem getQuestionsBySectionId", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,rs);			
		}

		return questions;
	}
	
	@Override
	public ArrayList<Question> getQuestionByAllParam(Question questionParam) {
		ArrayList<Question> questions = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStmt = null;
		ResultSet rs = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "SELECT * FROM `question` WHERE id LIKE '?%' AND Designation LIKE '?%' AND intitule LIKE '?%' AND id_section LIKE '?%' id_typeQuestion LIKE '?%' ";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setInt(1, questionParam.getId());
			preparedStmt.setString(2, questionParam.getDesignation());
			preparedStmt.setString(3, questionParam.getIntitule());
			preparedStmt.setInt(4, questionParam.getSection().getId());
			preparedStmt.setInt(5, questionParam.getTypeQuestion().getId());
			rs = preparedStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(sqlParamQuestionr[0]);
				String designation = rs.getString(sqlParamQuestionr[1]);
				String intitule = rs.getString(sqlParamQuestionr[2]);
				int typeQuestionID = rs.getInt(sqlParamQuestionr[4]);
				int sectionID = rs.getInt(sqlParamQuestionr[3]);

				// Récupére l'instance de TypeQuestion via l'id
				TypeQuestionDao typeQuestionDao = daoFactory.getTypeQuestionDao();
				TypeQuestion typeQuestion = typeQuestionDao.getTypeQuestionById(Integer.toString(typeQuestionID));

				// Récupére l'instance de Section via l'id
				SectionDao sectionDao = daoFactory.getSectionDao();
				Section section = sectionDao.getSectionById(sectionID);

				// Remplir les attributs
				Question question = new Question();
				question.setId(id);
				question.setDesignation(designation);
				question.setIntitule(intitule);
				question.setSection(section);
				boolean roleEnseignant =  rs.getBoolean(sqlParamQuestionr[5]);
				boolean roleEleve =  rs.getBoolean(sqlParamQuestionr[6]);
				question.setRoleEnseignant(roleEnseignant);
				question.setRoleEleve(roleEleve);
				question.setTypeQuestion(typeQuestion);

				questions.add(question);
			}
			connexion.close();
			preparedStmt.close();
			rs.close();

		} catch (SQLException e) {			
			logger.log(Level.INFO, "sql problem getQuestionByAllParam", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,rs);			
		}

		return questions;
	}

}
