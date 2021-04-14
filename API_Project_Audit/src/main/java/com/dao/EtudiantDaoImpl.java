package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Etudiant;

public class EtudiantDaoImpl implements EtudiantDao {

	
	private DaoFactory daoFactory;

	EtudiantDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public ArrayList<Etudiant> getEtudiants() {
    	ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id,nom,prenom,email,tel FROM Etudiant;");

            while (resultat.next()) {
            	String id = resultat.getString("id");
                String classe = resultat.getString("nom");
                String promo = resultat.getString("promo");
     
                Etudiant etudiant = new Etudiant();
                etudiant.setId(Integer.valueOf(id));
                etudiant.setPromo(promo);
                etudiant.setClasse(classe);

                etudiants.add(etudiant);
                connexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiants;
    }
}
