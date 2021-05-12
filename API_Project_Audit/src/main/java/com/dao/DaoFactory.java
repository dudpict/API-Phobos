package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;
@Configuration
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
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("");
		return DriverManager.getConnection("jdbc:mariadb://localhost/projetGL","essai","network");

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
	
	public ProfesseurDao getProfesseurDao() {
		return new ProfesseurDaoImpl(this);
	}

	public JuryDao getJuryDao() {
		return new JuryDaoImpl(this);
	}
	
	public ModeleDao getModeleDao() {
		return new ModeleDaoImpl(this);
	}
	
	public QuestionDao getQuestionDao() {
		return new QuestionDaoImpl(this);
	}
	
	public SectionDao getSectionDao() {
		return new SectionDaoImpl(this);
	}
	
	public TypeQuestionDao getTypeQuestionDao() {
		return new TypeQuestionDaoImpl(this);
	}

	public MatiereDao getMatiereDao() {
		return new MatiereDaoImpl(this);
	}

	public LieuDao getLieuDao() {
		return new LieuDaoImpl(this);
	}
	
	public ReponseMultipleDao getReponseMultipleDao() {
		return new ReponseMultipleDaoImpl(this);
	}

	public ReponseDao getReponseDao() {
		return new ReponseDaoImpl(this);
	}
}
