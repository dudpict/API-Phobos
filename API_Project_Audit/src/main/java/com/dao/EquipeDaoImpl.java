package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Equipe;

public class EquipeDaoImpl implements EquipeDao {
	
	private DaoFactory daoFactory;

	EquipeDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public ArrayList<Equipe> getEquipes() {
    	ArrayList<Equipe> equipes = new ArrayList<Equipe>();
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
        return equipes;
    }

}
