package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Modele;

public class ModeleDaoImpl implements ModeleDao {

	private DaoFactory daoFactory;

	ModeleDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Modele> getModeles() {
		ArrayList<Modele> modeles = new ArrayList<Modele>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Modele;");
			connexion.close();
			statement.close();

			while (resultat.next()) {
				int id = resultat.getInt("id");
				String designation = resultat.getString("designation");

				// TODO IMPLEMENTER EQUIPE ET ROLE MODELE
				Modele modele = new Modele();
				modele.setId(id);
				modele.setDesignation(designation);

				modeles.add(modele);
				resultat.close();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modeles;
	}

	@Override
	public Modele getModeleById(String modeleID) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		Modele modele = new Modele();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Modele WHERE id=" + modeleID + ";");

			connexion.close();
			statement.close();
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String designation = resultat.getString("designation");

				// TODO IMPLEMENTER EQUIPE ET ROLE MODELE
				modele.setId(id);
				modele.setDesignation(designation);
				resultat.close();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modele;
	}

	@Override
	public void deleteModele(String id) {
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "DELETE FROM Modele WHERE id=?";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.execute();
			connexion.close();
			preparedStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addModele(String Designation) {
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "INSERT INTO `Modele`(`designation`) VALUES (?)";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, Designation);
			preparedStmt.execute();
			connexion.close();
			preparedStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateModele(int id, String Designation) {
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "UPDATE `Modele` SET `designation`=? WHERE `id`=?";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, Designation);
			preparedStmt.setInt(2, id);
			preparedStmt.execute();
			connexion.close();
			preparedStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Modele getModeleByNom(String Designation) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		Modele modele = new Modele();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Modele WHERE Designation='" + Designation + "';");

			connexion.close();
			statement.close();

			while (resultat.next()) {
				int id = resultat.getInt("id");
				String designation = resultat.getString("designation");

				// TODO IMPLEMENTER EQUIPE ET ROLE MODELE
				modele.setId(id);
				modele.setDesignation(designation);
				resultat.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return modele;
	}

}
