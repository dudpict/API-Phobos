package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.beans.Matiere;
import com.beans.Professeur;

public class MatiereDaoImpl implements MatiereDao {

	private DaoFactory daoFactory;

	public MatiereDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Matiere getMatiereById(String matiereID) {
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        Matiere matiere = new Matiere();

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Matiere WHERE id="+matiereID+";");

            while (resultat.next()) {
            	String id = resultat.getString("id");
                String designation = resultat.getString("designation");
                int effectif = resultat.getInt("effectif");
                String departement = resultat.getString("departement");
                String professeurID = resultat.getString("id_Professeur");
                
                ProfesseurDao profDaoImpl = daoFactory.getProfesseurDao();
                Professeur prof = profDaoImpl.getProfesseurById(professeurID);
               
                matiere.setId(Integer.valueOf(id));
                matiere.setDesignation(designation);
                matiere.setDepartement(departement);
                matiere.setEffectif(effectif);
                matiere.setResponsable(prof);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
			resultat.close();
			statement.close();
			connexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return matiere;
	}

	@Override
	public int getMatiereIdByResponsableID(int idProf) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		int matiere = -1;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT id FROM matiere WHERE responsable = ? ;");
			preparedStatement.setInt(1, idProf);
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				matiere = resultat.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultat.close();
			statement.close();
			preparedStatement.close();
			connexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return matiere;
	}
	
	@Override
	public List<Matiere> getMatieres() {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		List<Matiere> matieres = new ArrayList<Matiere>();
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM Matiere ;");
			resultat = preparedStatement.executeQuery();
			
			while (resultat.next()) {
				String idProf = resultat.getString("id_Professeur");

				Matiere matiere = new Matiere();
				matiere.setId(resultat.getInt("id"));
				matiere.setDesignation(resultat.getString("designation"));
				matiere.setEffectif(Integer.parseInt(resultat.getString("effectif")));
				matiere.setDepartement(resultat.getString("departement"));
				ProfesseurDao professeurDao = daoFactory.getProfesseurDao();
				Professeur matiereInstance = professeurDao.getProfesseurById(idProf);
				matiere.setResponsable(matiereInstance);

				matieres.add(matiere);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultat.close();
			statement.close();
			preparedStatement.close();
			connexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return matieres;
	}
}
