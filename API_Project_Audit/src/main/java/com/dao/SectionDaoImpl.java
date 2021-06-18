package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Modele;
import com.beans.Section;

public class SectionDaoImpl implements SectionDao {

	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(SectionDaoImpl.class);
	private String [] sqlParamPersonne = {"id","designation","id_Modele"};

	SectionDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Section> getSections() {
		ArrayList<Section> sections = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM section;");
			connexion.close();

			while (resultat.next()) {
				int id = resultat.getInt(sqlParamPersonne[0]);
				String designation = resultat.getString(sqlParamPersonne[1]);
				String modeleID = resultat.getString(sqlParamPersonne[2]);

				// Récupére l'instance de Prsonne via l'id
				ModeleDao modeleDao = daoFactory.getModeleDao();
				Modele modele = modeleDao.getModeleById(modeleID);


				Section section = new Section();
				section.setId(id);
				section.setDesignation(designation);
				section.setModele(modele);
				sections.add(section);
			}
		}  catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getSections", e);
		}finally {
			daoFactory.close(connexion,statement,null,resultat);	
		}
		return sections;
	}

	@Override
	public void deleteSection(String id) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "DELETE FROM section WHERE id=?";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.execute();

		}  catch (SQLException e) {
			logger.log(Level.INFO, "sql problem deleteSection", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,null);	
		}
	}

	@Override
	public Section getSectionById(int sectionID) {

		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;
		Section section = new Section();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM section WHERE id=? ;");
			preparedStatement.setInt(1, sectionID);
			resultat = preparedStatement.executeQuery();
			
			while (resultat.next()) {
				int id = resultat.getInt(sqlParamPersonne[0]);
				String designation = resultat.getString(sqlParamPersonne[1]);
				String modeleID = resultat.getString(sqlParamPersonne[2]);

				// Récupére l'instance de Prsonne via l'id
				ModeleDao modeleDao = daoFactory.getModeleDao();
				Modele modele = modeleDao.getModeleById(modeleID);

				section.setId(id);
				section.setDesignation(designation);
				section.setModele(modele);
			}
		}  catch (SQLException e) {
			logger.log(Level.INFO, "sql problem deleteSection", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		return section;
	}

	@Override
	public void addSection(String designation, int idModele) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "INSERT INTO `section`(`designation`, `id_Modele`) VALUES (?,?);";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, designation);
			preparedStmt.setInt(2, idModele);
			preparedStmt.execute();
			connexion.close();

		}  catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addSection", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,null);	
		}
	}

	@Override
	public void updateSection(int id, String designation, int idModele) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "UPDATE `section` SET `designation`=?,`id_Modele`=? WHERE `id`=?;";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, designation);
			preparedStmt.setInt(2, idModele);
			preparedStmt.setInt(3, id);
			preparedStmt.execute();
			connexion.close();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem updateSection", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,null);	
		}
	}

	@Override
	public ArrayList<Section> getSectionByIdModele(String idModele) {
		ArrayList<Section> sections = new ArrayList<>();
		Connection connexion = null;
		ResultSet rs = null;
		PreparedStatement preparedStmt = null;
		
		try {
			connexion = daoFactory.getConnection();
			String requete = "SELECT * FROM section WHERE id_Modele=?;";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, idModele);
			rs = preparedStmt.executeQuery();
			connexion.close();
			while (rs.next()) {
				int id = rs.getInt(sqlParamPersonne[0]);
				String designation = rs.getString(sqlParamPersonne[1]);
				String modeleID = rs.getString(sqlParamPersonne[2]);

				// Récupére l'instance de Prsonne via l'id
				ModeleDao modeleDao = daoFactory.getModeleDao();
				Modele modele = modeleDao.getModeleById(modeleID);

				Section section = new Section();
				section.setId(id);
				section.setDesignation(designation);
				section.setModele(modele);
				sections.add(section);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem updateSection", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,rs);	
		}
		return sections;
	}
	
	@Override
	public ArrayList<Section> getSectionByNom(String designationPara) {
		ArrayList<Section> sections = new ArrayList<>();
		Connection connexion = null;
		ResultSet rs = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "SELECT * FROM section WHERE designation=?;";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, designationPara);
			rs = preparedStmt.executeQuery();
			connexion.close();
			while (rs.next()) {
				int id = rs.getInt(sqlParamPersonne[0]);
				String designation = rs.getString(sqlParamPersonne[1]);
				String modeleID = rs.getString(sqlParamPersonne[2]);

				// Récupére l'instance de Prsonne via l'id
				ModeleDao modeleDao = daoFactory.getModeleDao();
				Modele modele = modeleDao.getModeleById(modeleID);

				Section section = new Section();
				section.setId(id);
				section.setDesignation(designation);
				section.setModele(modele);
				sections.add(section);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getSectionByNom", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,rs);	
		}
		return sections;
	}
	
	@Override
	public ArrayList<Section> getSectionByAllParam(Section sectionParam) {
		ArrayList<Section> sections = new ArrayList<>();
		Connection connexion = null;
		ResultSet rs = null;
		PreparedStatement preparedStmt = null;
	
		try {
			connexion = daoFactory.getConnection();
			
			String requete = "SELECT * FROM section WHERE id LIKE '?%' AND designation LIKE '?%' AND id_Modele LIKE '?%'";
			
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setInt(1, sectionParam.getId());
			preparedStmt.setString(2, sectionParam.getDesignation());
			preparedStmt.setInt(3, sectionParam.getModele().getId());
			rs = preparedStmt.executeQuery();
			connexion.close();
			
			while (rs.next()) {
				int id = rs.getInt(sqlParamPersonne[0]);
				String designation = rs.getString(sqlParamPersonne[1]);
				String modeleID = rs.getString(sqlParamPersonne[2]);

				// Récupére l'instance de Prsonne via l'id
				ModeleDao modeleDao = daoFactory.getModeleDao();
				Modele modele = modeleDao.getModeleById(modeleID);

				Section section = new Section();
				section.setId(id);
				section.setDesignation(designation);
				section.setModele(modele);
				sections.add(section);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getSectionByNom", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,rs);	
		}
		return sections;
	}
}
