package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Lieu;

public class LieuDaoImpl implements LieuDao {

	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(LieuDaoImpl.class);

	public LieuDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Lieu getLieuById(String lieuID) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		Lieu lieu = new Lieu();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Lieu WHERE id=?;");
			preparedStmt.setString(1,lieuID );
			resultat=preparedStmt.executeQuery();
			
			while (resultat.next()) {
				String ville = resultat.getString("Ville");
				String etablissement = resultat.getString("Etablissement");
				String bat = resultat.getString("Batiment");
				String etage = resultat.getString("Etage");
				String nomSalle = resultat.getString("NomSalle");
				String numSalle = resultat.getString("NumSalle");
				lieu.setId(Integer.parseInt(lieuID));
				lieu.setVille(ville);
				lieu.setEtablissement(etablissement);
				lieu.setEtage(etage);
				lieu.setBatiment(bat);
				lieu.setNomSalle(nomSalle);
				lieu.setNumSalle(numSalle);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getJurys", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		return lieu;
	}

	public ArrayList<Lieu> getLieux() {
		ArrayList<Lieu> lieux = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Lieu ;");
			while (resultat.next()) {
				String id = resultat.getString("id");
				String ville = resultat.getString("Ville");
				String etablissement = resultat.getString("Etablissement");
				String bat = resultat.getString("Batiment");
				String etage = resultat.getString("Etage");
				String nomSalle = resultat.getString("NomSalle");
				String numSalle = resultat.getString("NumSalle");
				Lieu lieu = new Lieu();
				lieu.setId(Integer.parseInt(id));
				lieu.setVille(ville);
				lieu.setEtablissement(etablissement);
				lieu.setEtage(etage);
				lieu.setBatiment(bat);
				lieu.setNomSalle(nomSalle);
				lieu.setNumSalle(numSalle);
				lieux.add(lieu);

			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getJurys", e);

		}finally {
			daoFactory.close(connexion,statement,null,resultat);			
		}
		return lieux;

	}

}
