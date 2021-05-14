package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Jury;

public class JuryDaoImpl implements JuryDao {

	private DaoFactory daoFactory;

	JuryDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public ArrayList<Jury> getJurys() {
    	ArrayList<Jury> jurys = new ArrayList<Jury>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id,designation FROM Jury;");

            while (resultat.next()) {
            	String id = resultat.getString("id");
                String designation = resultat.getString("designation");
                     
                Jury jury = new Jury();
                jury.setId(Integer.valueOf(id));
                jury.setDesignation(designation);

                jurys.add(jury);
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
        return jurys;
    }

	@Override
	public Jury getJuryById(String juryID) {
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        Jury jury = new Jury();

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id,designation FROM Jury WHERE id="+juryID+";");

            while (resultat.next()) {
            	String id = resultat.getString("id");
                String designation = resultat.getString("designation");
                     
               
                jury.setId(Integer.valueOf(id));
                jury.setDesignation(designation);
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
        return jury;
	}
	
	@Override
	public Jury  getJurybyidAudit(String idAudit) {
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        PreparedStatement preparedStmt = null;
                
        Connection connexion2 = null;
        Statement statement2 = null;
        ResultSet resultat2 = null;
        PreparedStatement preparedStmt2 = null;
        
        Jury jury = new Jury();

        try {
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Audit where id=?;");
			preparedStmt.setString(1, idAudit);
			resultat = preparedStmt.executeQuery();
			
            while (resultat.next()) {
            	String id_Jury = resultat.getString("id_Jury");
            	connexion2 = daoFactory.getConnection();
    			statement2 = connexion2.createStatement();
    			preparedStmt2 = connexion2.prepareStatement("SELECT * FROM Jury where id=?;");
    			preparedStmt2.setString(1, id_Jury);
    			resultat2 = preparedStmt2.executeQuery();
    			
    			 while (resultat2.next()) {
    				 int id = resultat2.getInt("id");
    				 String designation = resultat2.getString("designation");
    				 jury.setId(id);
    				 jury.setDesignation(designation);
    			 }
            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
        	if (connexion != null) {
        		resultat.close();
    			statement.close();
    			connexion.close();
        	}
        	if (connexion2 != null) {
        		resultat2.close();
    			statement2.close();
    			connexion2.close();
        	}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jury;
	}
}
