package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	
	private String url;
    private String username;
    private String password;

    public DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DaoFactory getInstance() {

        DaoFactory instance = new DaoFactory(
                "jdbc:mariadb://172.24.1.9/phpmyadmin/projetGl", "root", "network");
        return instance;
    }

    public Connection getConnection() throws SQLException {
    	System.out.println("url : "+url );
    	try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return DriverManager.getConnection(this.url, this.username, this.password);
    }

     //Récupération du Dao
    public PersonneDao getPersonneDao() {
        return new PersonneDaoImpl(this);
    }
    
    public EquipeDao getEquipeDao() {
        return new EquipeDaoImpl(this);
    }
    
    public AuditDao getAuditDao() {
        return new AuditDaoImpl(this);
    }
    
    public EtudiantDao getEtudiantDao() {
        return new EtudiantDaoImpl(this);
    }
    
    public JuryDao getJuryDao() {
        return new JuryDaoImpl(this);
    }

}
