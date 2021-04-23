package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.beans.Lieu;

public class LieuDaoImpl implements LieuDao {

	private DaoFactory daoFactory;

	public LieuDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Lieu getLieuById(String lieuID) {
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        Lieu lieu = new Lieu();
        
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Lieu WHERE id="+lieuID+";");
            
            connexion.close();

            while (resultat.next()) {
            	String ville=resultat.getString("Ville");
            	String etablissement = resultat.getString("Etablissement");
                String bat = resultat.getString("Batiment");
                String etage = resultat.getString("Etage");
                String nomSalle = resultat.getString("NomSalle");
                String numSalle = resultat.getString("NumSalle");
     
                lieu.setVille(ville);
                lieu.setEtablissement(etablissement);
                lieu.setEtage(etage);
                lieu.setBatiment(bat);
                lieu.setNomSalle(nomSalle);
                lieu.setNumSalle(numSalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lieu;
	}

}
