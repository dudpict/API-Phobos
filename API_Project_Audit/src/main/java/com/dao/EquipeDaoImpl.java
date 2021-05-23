package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Equipe;

public class EquipeDaoImpl implements EquipeDao {
	private static final Logger logger = Logger.getLogger(EquipeDaoImpl.class);
	private DaoFactory daoFactory;

	EquipeDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public ArrayList<Equipe> getEquipes() {
    	ArrayList<Equipe> equipes = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id,designation FROM Equipe;");

            while (resultat.next()) {
            	String id = resultat.getString("id");
                String designation = resultat.getString("designation");
     
                Equipe equipe = new Equipe();
                equipe.setId(Integer.valueOf(id));
                equipe.setDesignation(designation);
                equipes.add(equipe);
            }
        } catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getEquipes", e);

		}finally {
			daoFactory.close(connexion,statement,null,resultat);			
		}
        return equipes;
    }
    
    @Override
    public Equipe getEquipeById(String id) {
    	Connection connexion = null;
        PreparedStatement statement = null;
        ResultSet resultat = null;
        Equipe equipe = new Equipe();
        
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.prepareStatement("SELECT id,designation FROM Equipe WHERE id = ? ;");
            statement.setString(1, id);
            resultat = statement.executeQuery();
            while (resultat.next()) {
            	equipe.setId(Integer.parseInt(id));
            	equipe.setDesignation(resultat.getString("designation"));
            }
            

        } catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getEquipeById", e);

		}finally {
			daoFactory.close(connexion,statement,null,resultat);			
		}
        return equipe;
    	
    }
    
    @Override
    public Equipe getEquipeByString(String designation) {
    	Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
        Equipe equipe = new Equipe();
        equipe=null;
        try {
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Equipe e WHERE e.designation = ?;");
			preparedStmt.setString(1, designation);
			resultat = preparedStmt.executeQuery();        	
            
            while (resultat.next()) {
            	EquipeDao equipeDao = daoFactory.getEquipeDao();
            	equipe = equipeDao.getEquipeById(resultat.getString("id"));
            	
            }
            

        } catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getEquipeByString", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
        return equipe;
    	
    }
    
    
    @Override
    public Equipe getEquipeByEtudiantId(String idEtudiant) {
    	Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
        Equipe equipe = new Equipe();
        
        try {
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Etudiant WHERE id = ? ;");
			preparedStmt.setString(1, idEtudiant);
			resultat = preparedStmt.executeQuery();        	
            
            while (resultat.next()) {
            	EquipeDao equipeDao = daoFactory.getEquipeDao();
            	equipe = equipeDao.getEquipeById(resultat.getString("id_Equipe"));
            	
            }
            

        } catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getEquipeByEtudiantId", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
        return equipe;
    	
    }
    
    @Override
    public void addEquipe(String designation) {
    	Connection connexion = null;
		Statement statement = null;		
		PreparedStatement preparedStmt = null;
       
        
        try {
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO Equipe(designation) VALUES (?);");
			preparedStmt.setString(1, designation);
			preparedStmt.executeQuery();        	
        } catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addEquipe", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,null);			
		}
    	
    }

}
