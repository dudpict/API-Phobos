package com.dao;

import java.sql.Connection;
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
                connexion.close();
            }
        } catch (SQLException e) {
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
                connexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jury;
	}
}
