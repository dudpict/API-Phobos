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
	
    PersonneDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public ArrayList<Personne> getPersonnes(String role) {
        List<Personne> personnes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Personne WHERE role LIKE '"+role+"%';");

            while (resultat.next()) {
            	String id = resultat.getString("id");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String email = resultat.getString("email");
                String tel = resultat.getString("tel");
     
                Personne personne = new Personne();
                personne.setId(Integer.valueOf(id));
                personne.setNom(nom);
                personne.setPrenom(prenom);
                personne.setEmail(email);
                personne.setTelephone(tel);

                personnes.add(personne);
            }
        } catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getPersonneByMail", e);
		}finally {
			daoFactory.close(connexion,statement,null,resultat);	
		}
        return (ArrayList<Personne>) personnes;
    }
    
    @Override
    public Personne getPersonneById(int id) {
    	Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        Personne personne=new Personne();
        
        
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Personne WHERE id="+id+";");
            
            connexion.close();

            while (resultat.next()) {
            	String id2=resultat.getString("id");
            	String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String email = resultat.getString("email");
                String tel = resultat.getString("tel");
     
                personne.setId(Integer.valueOf(id2));
                personne.setNom(nom);
                personne.setPrenom(prenom);
                personne.setEmail(email);
                personne.setTelephone(tel);
                
            }
        }catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getPersonneById", e);
		}finally {
			daoFactory.close(connexion,statement,null,resultat);	
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
            	String id = resultat.getString("id");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String email = resultat.getString("email");
                String tel = resultat.getString("tel");	     
            
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
