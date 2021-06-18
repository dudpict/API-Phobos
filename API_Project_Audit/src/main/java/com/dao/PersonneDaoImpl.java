package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Personne;

public class PersonneDaoImpl implements PersonneDao {

	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(PersonneDaoImpl.class);
	private String [] sqlParamPersonne = {"id","nom","prenom","email","tel"};
	
    PersonneDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public ArrayList<Personne> getPersonnes(String role) {
        List<Personne> personnes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        PreparedStatement preparedStmt= null;

        try {
        	
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Personne WHERE role LIKE ?;");
			preparedStmt.setString(1,role );
			resultat=preparedStmt.executeQuery();

            while (resultat.next()) {
            	String id = resultat.getString(sqlParamPersonne[0]);
                String nom = resultat.getString(sqlParamPersonne[1]);
                String prenom = resultat.getString(sqlParamPersonne[2]);
                String email = resultat.getString(sqlParamPersonne[3]);
                String tel = resultat.getString(sqlParamPersonne[4]);
     
                Personne personne = new Personne();
                personne.setId(Integer.valueOf(id));
                personne.setNom(nom);
                personne.setPrenom(prenom);
                personne.setEmail(email);
                personne.setTelephone(tel);

                personnes.add(personne);
            }
        } catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getPersonnes", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);	
		}
        return (ArrayList<Personne>) personnes;
    }
    
    @Override
    public Personne getPersonneById(int id) {
    	Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        PreparedStatement preparedStmt= null;
        Personne personne=new Personne();
        
        
        try {
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Personne WHERE id=?;");
			preparedStmt.setInt(1,id );
			resultat=preparedStmt.executeQuery();
        	
            while (resultat.next()) {
            	String id2 = resultat.getString(sqlParamPersonne[0]);
                String nom = resultat.getString(sqlParamPersonne[1]);
                String prenom = resultat.getString(sqlParamPersonne[2]);
                String email = resultat.getString(sqlParamPersonne[3]);
                String tel = resultat.getString(sqlParamPersonne[4]);
     
                personne.setId(Integer.valueOf(id2));
                personne.setNom(nom);
                personne.setPrenom(prenom);
                personne.setEmail(email);
                personne.setTelephone(tel);
                
            }
        }catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getPersonneById", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);	
		}
        return personne;
    }

    @Override
    public Personne getPersonneByMail(String mail) {
        Personne personne = null;
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        PreparedStatement preparedStatement= null;
        
		try {
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM Personne WHERE email=? ;");
			preparedStatement.setString(1, mail);
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				personne= new Personne();
				String id = resultat.getString(sqlParamPersonne[0]);
                String nom = resultat.getString(sqlParamPersonne[1]);
                String prenom = resultat.getString(sqlParamPersonne[2]);
                String email = resultat.getString(sqlParamPersonne[3]);
                String tel = resultat.getString(sqlParamPersonne[4]);    
            
                personne.setId(Integer.valueOf(id));
                personne.setNom(nom);
                personne.setPrenom(prenom);
                personne.setEmail(email);
                personne.setTelephone(tel);	                
           }
            
        } catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getPersonneByMail", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
        return personne;
    }
    
    @Override
    public Personne addPersonne(Personne personne) {
        Connection connexion = null;
        Statement statement = null;
        PreparedStatement preparedStatement= null;
        
		try {
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("INSERT INTO Personne(nom, prenom, email, tel) VALUES (?,?,?,?) ;");
			preparedStatement.setString(1, personne.getNom());
			preparedStatement.setString(2, personne.getPrenom());
			preparedStatement.setString(3, personne.getEmail());
			preparedStatement.setString(4, personne.getTelephone());
			preparedStatement.executeQuery();
			
            
        } catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addPersonne", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,null);	
		}
		return personne;
    }
    
    
}
