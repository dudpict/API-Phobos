package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Jury;

public class JuryDaoImpl implements JuryDao {
	private static final Logger logger = Logger.getLogger(JuryDaoImpl.class);
	private String [] sqlParamJury = {"id","designation"};
	private DaoFactory daoFactory;

	JuryDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public ArrayList<Jury> getJurys() {
    	ArrayList<Jury> jurys = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id,designation FROM Jury;");

            while (resultat.next()) {
            	String id = resultat.getString(sqlParamJury[0]);
                String designation = resultat.getString(sqlParamJury[1]);
                     
                Jury jury = new Jury();
                jury.setId(Integer.valueOf(id));
                jury.setDesignation(designation);

                jurys.add(jury);
            }
        }catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getJurys", e);

		}finally {
			daoFactory.close(connexion,statement,null,resultat);			
		}
        return jurys;
    }

	@Override
	public Jury getJuryById(String juryID) {
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        PreparedStatement preparedStmt = null;
        Jury jury = new Jury();
        ArrayList<Jury> jurys = new ArrayList<>();

        try {
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT id,designation FROM Jury WHERE id=?;");
			preparedStmt.setString(1,juryID );
			resultat=preparedStmt.executeQuery();
        	
			while (resultat.next()) {
            	String id = resultat.getString(sqlParamJury[0]);
                String designation = resultat.getString(sqlParamJury[1]);
               
                jury.setId(Integer.valueOf(id));
                jury.setDesignation(designation);
                jurys.add(jury);
            }
        } catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getEtudiantById", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
        return jury;
	}
	
	@Override
	public Jury  getJurybyidAudit(String idAudit) {
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        PreparedStatement preparedStmt = null;
                
        
        Jury jury = new Jury();

        try {
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Audit where id=?;");
			preparedStmt.setString(1, idAudit);
			resultat = preparedStmt.executeQuery();
			
            while (resultat.next()) {
            	String idJury = resultat.getString("id_Jury");
	            jury = daoFactory.getJuryDao().getJuryById(idJury);
            	
            }
        }catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getJurybyidAudit", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);
		}
        return jury;
	}
	
	@Override
	public Jury  getJuryByString(String designation ) {
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        PreparedStatement preparedStmt = null;
                
        
        Jury jury = new Jury();
        jury=null;
        try {
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Jury j WHERE j.designation = ?;");
			preparedStmt.setString(1, designation);
			resultat = preparedStmt.executeQuery();
			
            while (resultat.next()) {
            	String idJury = resultat.getString("id");
	            jury = daoFactory.getJuryDao().getJuryById(idJury);
            	
            }
        }catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getJuryByString", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);
		}
        return jury;
	}
	
	@Override
	public void  addJury(String designation) {
        Connection connexion = null;
        Statement statement = null;
        PreparedStatement preparedStmt = null;

        try {
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO Jury(designation) VALUES (?);");
			preparedStmt.setString(1, designation);
			preparedStmt.executeQuery();
			
        }catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addJury", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,null);
		}
      
	}

	
}
