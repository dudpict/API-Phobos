package com.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DaoFactory {
	private static final Logger logger = Logger.getLogger(DaoFactory.class);
	
	
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
		 String userLogin = getEncryptedLogin();
		 String userPassword= getEncryptedPass();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.log(Level.INFO, "connection bdd problem", e);
		}
		return DriverManager.getConnection(returnProperties("urlBddDistant"),userLogin,userPassword);
	}
	
	//urlBddDistant, urlBddLocal

	public String returnProperties(String prop) {
		Properties properties = new Properties();
		try (
			InputStream stream1 = new FileInputStream("src/main/resources/application.properties");		    
		  ) {
			properties.load(stream1);
		}
		catch (IOException e) {
			logger.log(Level.INFO, "returnProperties problem", e);
		}		
		return properties.getProperty(prop);
	}
	
	
	public String getEncryptedPass() {
		
		return  returnProperties("userPass");
	}
	
	public String getEncryptedLogin() {
		
		return  returnProperties("userLogin");
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
