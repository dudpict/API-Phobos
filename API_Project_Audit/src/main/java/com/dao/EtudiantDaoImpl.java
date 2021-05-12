package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Etudiant;
import com.beans.Personne;
import com.beans.ReponseMultiple;

public class EtudiantDaoImpl implements EtudiantDao {

	private static final Logger logger = Logger.getLogger(ReponseMultipleDaoImpl.class);
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
            resultat = statement.executeQuery("SELECT * FROM Etudiant;");
            connexion.close();

            while (resultat.next()) {
            	String id = resultat.getString("id");
                String classe = resultat.getString("classe");
                String promo = resultat.getString("promo");
                int personneID = resultat.getInt("id_Personne");
//                int equipeID = resultat.getInt("id_Equipe");
//                int roleUserID = resultat.getInt("id_roleUtilisateur");
                
                //Récupére l'instance de Prsonne via l'id
        		PersonneDao personneDao = daoFactory.getPersonneDao();
        		Personne personne  = personneDao.getPersonneById(personneID);
        		        		
        		//TODO IMPLEMENTER EQUIPE ET ROLE UTILISATEUR
                Etudiant etudiant = new Etudiant();
                etudiant.setId(Integer.valueOf(id));
                etudiant.setPromo(promo);
                etudiant.setClasse(classe);
                etudiant.setPersonne(personne);
                
//                connexion.close();

                etudiants.add(etudiant);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiants;
    }
    
    @Override
	public Etudiant getEtudiantById(String id) {
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        Etudiant etudiant = new Etudiant();

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Etudiant WHERE id="+id+";");
            connexion.close();
            System.out.println("apelle etudiant id "+id);
            while (resultat.next()) {
            	String id2 = resultat.getString("id");
                String classe = resultat.getString("classe");
                String promo = resultat.getString("promo");
                int personneID = resultat.getInt("id_Personne");
                int equipeID = resultat.getInt("id_Equipe");
                int roleUserID = resultat.getInt("id_roleUtilisateur");
                
                //Récupére l'instance de Prsonne via l'id
        		PersonneDao personneDao = daoFactory.getPersonneDao();
        		Personne personne  = personneDao.getPersonneById(personneID);
        		        		
        		//TODO IMPLEMENTER EQUIPE ET ROLE UTILISATEUR
                etudiant.setId(Integer.valueOf(id2));
                etudiant.setPromo(promo);
                etudiant.setClasse(classe);
                etudiant.setPersonne(personne);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
	}

	@Override
	public void addEtudiant(Etudiant etudiant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEtudiant(String id) {
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "DELETE * FROM Etudiant WHERE id=?";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.execute();
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateEtudiant(String id, Etudiant etudiantUpdated) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public ArrayList<Etudiant> etudiantByStr(String search){
		DaoFactory fact = new DaoFactory();		
		
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Etudiant e INNER JOIN Personne p ON p.id = e.id_Personne WHERE LOWER(p.nom) LIKE \"%?%\" OR  LOWER(p.prenom) LIKE \"%?%\";");
			preparedStmt.setString(1, search);
			preparedStmt.setString(2, search);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				
				String id = resultat.getString("id");
                String classe = resultat.getString("classe");
                String promo = resultat.getString("promo");
                int personneID = resultat.getInt("id_Personne");

        		PersonneDao personneDao = daoFactory.getPersonneDao();
        		Personne personne  = personneDao.getPersonneById(personneID);
				
				Etudiant etudiant = new Etudiant();
                etudiant.setId(Integer.valueOf(id));
                etudiant.setPromo(promo);
                etudiant.setClasse(classe);
                etudiant.setPersonne(personne);
	            etudiants.add(etudiant);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem", e);

		}finally {
			fact.close(connexion,statement,preparedStmt,resultat);			
		}
		
		return etudiants;
	}	
	

}
