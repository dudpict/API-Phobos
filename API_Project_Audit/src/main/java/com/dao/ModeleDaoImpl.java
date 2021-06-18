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

public class ModeleDaoImpl implements ModeleDao {

	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(ModeleDaoImpl.class);
	private String [] sqlParamModele = {"id","designation"};

	ModeleDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Modele> getModeles() {
		ArrayList<Modele> modeles = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Modele;");
			connexion.close();
			
			while (resultat.next()) {
				int id = resultat.getInt(sqlParamModele[0]);
				String designation = resultat.getString(sqlParamModele[1]);

				Modele modele = new Modele();
				modele.setId(id);
				modele.setDesignation(designation);
				modeles.add(modele);
							}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getModeles", e);
		}finally {
			daoFactory.close(connexion,statement,null,resultat);	
		}
		
		return modeles;
	}

	@Override
	public Modele getModeleById(String modeleID) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		Modele modele = new Modele();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Modele WHERE id=?;");
			preparedStmt.setString(1,modeleID );
			resultat=preparedStmt.executeQuery();
			while (resultat.next()) {
				int id = resultat.getInt(sqlParamModele[0]);
				String designation = resultat.getString(sqlParamModele[1]);

				modele.setId(id);
				modele.setDesignation(designation);
							}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getModeleById", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);	
		}
		return modele;
	}

	@Override
	public void deleteModele(String id) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "DELETE FROM Modele WHERE id=?";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.execute();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem deleteModele", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,null);	
		}
	}

	@Override
	public void addModele(Modele modele) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "INSERT INTO `Modele`(`designation`) VALUES (?)";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, modele.getDesignation());
			preparedStmt.execute();
			connexion.close();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addModele", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,null);	
		}
	}

	@Override
	public void updateModele(int id, String design) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "UPDATE `Modele` SET `designation`=? WHERE `id`=?";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, design);
			preparedStmt.setInt(2, id);
			preparedStmt.execute();
			connexion.close();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem updateModele", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,null);	
		}
	}

	@Override
	public Modele getModeleByNom(String designation) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		Modele modele = new Modele();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Modele WHERE Designation=?");
			preparedStmt.setString(1, designation);
			resultat = preparedStmt.executeQuery();
			
			
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String design = resultat.getString("designation");

				modele.setId(id);
				modele.setDesignation(design);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem updateModele", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);	
		}
		
		return modele;
	}

}
