package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

	public DaoFactory() {

	}

	public static DaoFactory getInstance() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		DaoFactory instance = new DaoFactory();
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mariadb://localhost:3306/DB?user=root&password=network");
	}

	// Récupération du Dao
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