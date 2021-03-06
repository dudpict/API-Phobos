package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Audit;
import com.beans.Personne;
import com.beans.Professeur;
import com.beans.RoleUtilisateur;

public class ProfesseurDaoImpl implements ProfesseurDao {
	private static final Logger logger = Logger.getLogger(ProfesseurDaoImpl.class);
	private DaoFactory daoFactory;
	private String [] sqlParamProfesseur = {"id","bureau","id_Personne","id_Role"};

	ProfesseurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Professeur> getProfesseurs() {
		ArrayList<Professeur> professeurs = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Professeur;");
			connexion.close();

			while (resultat.next()) {
				String id = resultat.getString(sqlParamProfesseur[0]);
				int personneID = resultat.getInt(sqlParamProfesseur[2]);
				String bureau = resultat.getString(sqlParamProfesseur[1]);
				
				Professeur professeur = new Professeur();
				
				PersonneDao personneDao = daoFactory.getPersonneDao();
				Personne personne = personneDao.getPersonneById(personneID);

				
				professeur.setId(Integer.valueOf(id));
				
				professeur.setPersonne(personne);
				
				professeur.setBureau(bureau);
				professeurs.add(professeur);

			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getProfesseurs", e);
		}finally {
			daoFactory.close(connexion,statement,null,resultat);	
		}
		return professeurs;
	}
	
	@Override
	public ArrayList<Professeur> getProfSansRole() {
		ArrayList<Professeur> professeurs = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Professeur p WHERE p.id NOT IN (SELECT id_Professeur FROM a_pour);");
			connexion.close();

			while (resultat.next()) {
				Professeur professeur = new Professeur();
				
				
				String id = resultat.getString(sqlParamProfesseur[0]);
				String bureau = resultat.getString(sqlParamProfesseur[1]);
				int personneID = resultat.getInt(sqlParamProfesseur[2]);

				// R??cup??re l'instance de Prsonne via l'id
				PersonneDao personneDao = daoFactory.getPersonneDao();
				Personne personne = personneDao.getPersonneById(personneID);
				professeur.setBureau(bureau);
				
				professeur.setId(Integer.valueOf(id));
				
				professeur.setPersonne(personne);
				professeurs.add(professeur);

			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getProfSansRole", e);
		}finally {
			daoFactory.close(connexion,statement,null,resultat);	
		}
		return professeurs;
	}

	@Override
	public Professeur getProfesseurById(String id) {		
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		Professeur professeur = new Professeur();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Professeur WHERE id=?;");
			preparedStmt.setString(1, id);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				// R??cup??re l'instance de Prsonne via l'id
				PersonneDao personneDao = daoFactory.getPersonneDao();
				
				
				String id2 = resultat.getString(sqlParamProfesseur[0]);
				String bureau = resultat.getString(sqlParamProfesseur[1]);
				int personneID = resultat.getInt(sqlParamProfesseur[2]);

				Personne personne = personneDao.getPersonneById(personneID);

				professeur.setId(Integer.valueOf(id2));
				professeur.setBureau(bureau);
				professeur.setPersonne(personne);

			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getProfesseurById", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);	
		}
		if (!Integer.toString(professeur.getId()).equals(id)) {
			professeur=null;
		}
		return professeur;
	}

	@Override
	public Professeur getProfesseurByPersonneID(String idPersonne){
		Professeur professeur = null;
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Professeur WHERE id_Personne = ?;");
			preparedStmt.setString(1, idPersonne);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String bureau = resultat.getString("bureau");
				int personneID = resultat.getInt("id_Personne");

				PersonneDao personneDao = daoFactory.getPersonneDao();
				Personne personne = personneDao.getPersonneById(personneID);
				professeur = new Professeur();
				professeur.setId(id);
				professeur.setBureau(bureau);
				professeur.setPersonne(personne);
	           
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getProfesseurByPersonneID", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		
		return professeur;
	}
	
	
	@Override
	public void addProfesseur(Professeur professeur) {
			
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		DaoFactory fact = new DaoFactory();
		PersonneDao personneDao = fact.getPersonneDao();
		
		
		Personne personne1 = personneDao.getPersonneById(professeur.getPersonne().getId());
		
		if (personne1.getId()==0) {
			personneDao.addPersonne(professeur.getPersonne());
			personne1 = personneDao.getPersonneByMail(professeur.getPersonne().getEmail());
		}
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO Professeur(bureau, id_Personne) VALUES (?,?);");
			preparedStmt.setString(1, professeur.getBureau());
			preparedStmt.setInt(2, personne1.getId());
			resultat = preparedStmt.executeQuery();
			
			
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addProfesseur", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		
	}

	@Override
	public ArrayList<Professeur>  professeurByAudit(String idAudit){
		ArrayList<Professeur> professeurs = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Professeur WHERE id IN (SELECT id_professeur FROM appartient ap WHERE ap.id = (SELECT id_jury from Audit WHERE id = ?));");
			preparedStmt.setString(1, idAudit);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				PersonneDao personneDao = daoFactory.getPersonneDao();
				String id = resultat.getString(sqlParamProfesseur[0]);				
				int personneID = resultat.getInt(sqlParamProfesseur[2]);
				String bureau = resultat.getString(sqlParamProfesseur[1]);				
				Personne personne = personneDao.getPersonneById(personneID);

				Professeur professeur = new Professeur();
				professeur.setPersonne(personne);
				professeur.setId(Integer.valueOf(id));
				professeur.setBureau(bureau);
				
				professeurs.add(professeur);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem professeurByAudit", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		
		return professeurs;
	}	
	
	
	
	
	@Override
	public void deleteProfesseur(String id) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStmt = connexion.prepareStatement("DELETE FROM Professeur WHERE id=?");
			
			preparedStmt.setString(1, id);
			preparedStmt.execute();
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem deleteProfesseur", e);

		}finally {
			daoFactory.close(connexion,null,preparedStmt,null);			
		}

	}

	@Override
	public ArrayList<Audit> getAudits(int matiere, boolean publies) {
		ArrayList<Audit> audits = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		String egalOuDiff = publies ? "=" : "<>";

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("SELECT * FROM Audit WHERE etat ? 'publie' and matiere = ? ;");
			preparedStatement.setString(1, egalOuDiff);
			preparedStatement.setInt(2, matiere);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				Audit audit = new Audit();
				audit.setId(resultat.getInt("id"));
				audit.setDesignation(resultat.getString("designation"));
				audit.setEtat(resultat.getString("etat"));
				audit.getMatiere().setId(resultat.getInt("matiere"));
				audits.add(audit);
			}

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getAudits", e);

		}finally {
			daoFactory.close(connexion,null,preparedStatement,resultat);			
		}
		return audits;
	}

	@Override
	public void setHeureAudits(String heureDebut, int id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion
					.prepareStatement("SET dateDebut = to_date('?', 'YYYY/MM/DD HH24:MI') FROM audit WHERE id = ? ;");
			preparedStatement.setString(1, heureDebut);
			preparedStatement.setInt(2, id);
			resultat = preparedStatement.executeQuery();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getAudits", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);			
		}
	}

	@Override
	public int getMatiereResponsable(int idProf) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;
		int matiere = -1;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion
					.prepareStatement("SELECT id FROM Matiere WHERE responsable = ? ;");
			preparedStatement.setInt(1, idProf);
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				matiere = resultat.getInt("id");
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getMatiereResponsable", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);			
		}

		return matiere;
	}

	@Override
	public ArrayList<Professeur> getprofesseurByStr(String search) {
		DaoFactory fact = new DaoFactory();	
		
		ArrayList<Professeur> professeurs = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Professeur pr INNER JOIN Personne p ON p.id = pr.id_Personne WHERE LOWER(p.nom) LIKE ? OR  LOWER(p.prenom) LIKE ?;");
			preparedStmt.setString(1, "%"+search+"%");
			preparedStmt.setString(2, "%"+search+"%");
			resultat = preparedStmt.executeQuery();
			connexion.close();

			while (resultat.next()) {
				PersonneDao personneDao = daoFactory.getPersonneDao();
				String id = resultat.getString(sqlParamProfesseur[0]);
				int personneID = resultat.getInt(sqlParamProfesseur[2]);
				String bureau = resultat.getString(sqlParamProfesseur[1]);
				

				
				Personne personne = personneDao.getPersonneById(personneID);

				Professeur professeur = new Professeur();				
				professeur.setBureau(bureau);
				professeur.setPersonne(personne);
				professeur.setId(Integer.valueOf(id));
				professeurs.add(professeur);

			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getprofesseurByStr", e);
		}finally {
			fact.close(connexion,statement,preparedStmt,resultat);	
		}
		
		return professeurs;
	}
	
	@Override
	public void addProfesseurToJuryId(String idJury,String idProfesseur) {
		
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO appartient (id, id_Professeur) VALUES (?,?);");
			preparedStmt.setString(1, idJury);
			preparedStmt.setString(2, idProfesseur);
			preparedStmt.executeQuery();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addProfesseurToJuryId", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,null);	
		}
	}
	
	@Override
	public void removeProfesseurToJuryId(String idJury,String idProfesseur) {
		
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("DELETE FROM appartient WHERE id_Professeur = ? AND id = ?;");
			preparedStmt.setString(1, idProfesseur);
			preparedStmt.setString(2, idJury);
			preparedStmt.executeQuery();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem removeProfesseurToJuryId", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,null);	
		}

	}
	
	public List<RoleUtilisateur> getRoleProf (String id) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;
		ResultSet resultat = null;
		
		RoleUtilisateur resRole= null;
		List<RoleUtilisateur> listRole = new ArrayList<>();
		
		try {
			connexion=daoFactory.getConnection();
			preparedStmt=connexion.prepareStatement("SELECT * FROM RoleUtilisateur ro LEFT JOIN a_pour ap ON ro.id = ap.id WHERE ap.id IN (SELECT id FROM a_pour ap WHERE ap.id_Professeur =?) GROUP BY ro.id;") ;
			preparedStmt.setString(1, id);
			resultat=preparedStmt.executeQuery();
			
			while (resultat.next()) {
				resRole = new RoleUtilisateur(resultat.getInt("id"),resultat.getString("designation"));
				
				listRole.add(resRole);
			}
			
		}catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getRoleProf", e);
		}finally {
			daoFactory.close(connexion, null, preparedStmt, resultat );
		}
		
		return listRole;
	}
	
	public boolean isProf(String id) {
		boolean res = false; 
		Connection connexion = null;
		PreparedStatement preparedStmt = null;
		ResultSet resultat = null;
		
		try {
			connexion=daoFactory.getConnection();
			preparedStmt=connexion.prepareStatement("SELECT * FROM Professeur WHERE id_Personne = ? ") ;
			preparedStmt.setString(1, id);
			resultat=preparedStmt.executeQuery();
			while(resultat.next()) {
				res=true;				
			}			
		}catch (SQLException e) {
			logger.log(Level.INFO, "sql problem isProf", e);
		}finally {
			daoFactory.close(connexion, null, preparedStmt,null );
		}
		return res;
	}
	
	@Override
	public Professeur getProfByPersonneId(String id) {
		Professeur prof = null;
		Connection connexion = null;
		PreparedStatement preparedStmt = null;
		ResultSet resultat = null;
		
		try {
			connexion=daoFactory.getConnection();
			preparedStmt=connexion.prepareStatement("SELECT * FROM Professeur WHERE id_Personne = ? ") ;
			preparedStmt.setString(1, id);
			resultat=preparedStmt.executeQuery();
			while(resultat.next()) {
				String idProf = resultat.getString("id");
				prof = getProfesseurById(idProf);
			}			
		}catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getProfByPersonneId", e);
		}finally {
			daoFactory.close(connexion, null, preparedStmt,null );
		}
		return prof;
	}
	
	@Override
	public int getIdProf(String idPers) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;
		int id= 0;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT id FROM Professeur WHERE id_Personne=? ;");
			preparedStatement.setString(1, idPers);
			resultat = preparedStatement.executeQuery();			
		
			while(resultat.next()) {
				id = resultat.getInt("id");
			}
		}
		catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getIdProf", e);
		}finally {
			daoFactory.close(connexion, statement, preparedStatement,resultat );
		}
		return id;
	}
	
	
}
