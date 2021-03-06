package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DaoFactory {
	private static final Logger logger = Logger.getLogger(DaoFactory.class);
	
	private static final String DRIVER = "jdbc:mariadb://";
	private static final String BDD_LOCAL = "localhost";
	private static final String BDD_DISTANT = "172.24";
	private static final String BDD = "/projetGL";
	private static final String LOGIN_BDD = "essai";
	private static final String BDD_PASS = "network";
	
	
	public DaoFactory() {
		super();
	}
	
	

	public static DaoFactory getInstance() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.log(Level.INFO, "problem driver jdbc", e);
		}
		
		return  new DaoFactory();
	}
	
	public Connection getConnection() throws SQLException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.log(Level.INFO, "connection bdd problem", e);
		}
		return DriverManager.getConnection(returnUrlBdd("distant"),getEncryptedLogin(),getEncryptedPass());
	}
	
	
	public String returnUrlBdd (String url) {
		if(url.equals("distant")) {
			return DRIVER+BDD_DISTANT+".1.9"+BDD;
		}else if (url.equals("local")) {
			return DRIVER+BDD_LOCAL+BDD;
		}else {
			return "";
		}
	}
	
	
	public String getEncryptedPass() {
		
		return  BDD_PASS;
	}
	
	public String getEncryptedLogin() {
		
		return  LOGIN_BDD;
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
	
	public NotificationDao getNotificationDao() {
		return new NotificationDaoImpl(this);
	}
	
	public AuditModifDao getAuditModifDao() {
		return new AuditModifDaoImpl(this);
	}
	public UEDao getUEDao() {
		return new UEDaoImpl(this);
	}
	
	public OptionDao getOptionDao() {
		return new OptionDaoImpl(this);
	}
	
	public RoleDao getRoleDao() {
		return new RoleDaoImpl(this);
	}
	
	public EnsseigneDao getEnsseigneDao() {
		return new EnsseigneDaoImpl(this);
	}
	
	public void close (Connection connexion, Statement statement, PreparedStatement preparedStmt,ResultSet resultat) {
		try {
			if(connexion!=null) {			
				connexion.close();
			}
			if(statement!=null) {
				statement.close();
			}
			if(preparedStmt!=null) {
				preparedStmt.close();
			}
			if(resultat!=null) {
				resultat.close();
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "problem sql", e);
		}
		
		
	}
}
