package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Audit;
import com.beans.Personne;
import com.beans.Professeur;

public class ProfesseurDaoImpl implements ProfesseurDao {
	
	private DaoFactory daoFactory;
	
	ProfesseurDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
	
	
	@Override
	public ArrayList<Professeur> getProfesseurs() {
		 ArrayList<Professeur> professeurs = new ArrayList<Professeur>();
	        Connection connexion = null;
	        Statement statement = null;
	        ResultSet resultat = null;

	        try {
	            connexion = daoFactory.getConnection();
	            statement = connexion.createStatement();
	            resultat = statement.executeQuery("SELECT * FROM Professeur;");
	            connexion.close();
	            
	            while (resultat.next()) {
	            	String id = resultat.getString("id");
	                String bureau = resultat.getString("bureau");
	                int personneID = resultat.getInt("id_Personne");
	                
	              //Récupére l'instance de Prsonne via l'id
	        		PersonneDao personneDao = daoFactory.getPersonneDao();
	        		Personne personne  = personneDao.getPersonneById(personneID);
	     
	                Professeur professeur = new Professeur();
	                professeur.setId(Integer.valueOf(id));
	                professeur.setBureau(bureau);
	                professeur.setPersonne(personne);

	                professeurs.add(professeur);
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return professeurs;
	}
	
	
	@Override
	public ArrayList<Audit> getAudits(int matiere, boolean publies) {
		ArrayList<Audit> audits = new ArrayList<Audit>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		String egalOuDiff = publies ? "=" : "<>";

		try {
			connexion = daoFactory.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM Audit WHERE etat ? 'publie' and matiere = ? ;");
			preparedStatement.setString(1, egalOuDiff);
			preparedStatement.setInt(2, matiere);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Audit audit = new Audit();
				audit.setId(resultat.getInt("id"));
				audit.setDesignation(resultat.getString("designation"));
				audit.setEtat(resultat.getString("etat"));
				audit.getMatiere().setId(resultat.getInt("matiere"));
				audits.add(audit);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return audits;
	}

	@Override
	public void setHeureAudits(String heureDebut, int id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			PreparedStatement preparedStatement = connexion
					.prepareStatement("SET dateDebut = to_date('?', 'YYYY/MM/DD HH24:MI') FROM audit WHERE id = ? ;");
			preparedStatement.setString(1, heureDebut);
			preparedStatement.setInt(2, id);
			resultat = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int getMatiereResponsable(int idProf) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		int matiere = -1;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			PreparedStatement preparedStatement = connexion
					.prepareStatement("SELECT id FROM Matiere WHERE responsable = ? ;");
			preparedStatement.setInt(1, idProf);
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				matiere = resultat.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return matiere;
	}


}
