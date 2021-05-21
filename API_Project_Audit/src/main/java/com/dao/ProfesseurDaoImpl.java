package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Audit;
import com.beans.Etudiant;
import com.beans.Personne;
import com.beans.Professeur;

public class ProfesseurDaoImpl implements ProfesseurDao {
	private static final Logger logger = Logger.getLogger(ReponseMultipleDaoImpl.class);
	private DaoFactory daoFactory;

	ProfesseurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Professeur> getProfesseurs() {
		ArrayList<Professeur> professeurs = new ArrayList<Professeur>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Professeur;");
			connexion.close();

			while (resultat.next()) {
				String id = resultat.getString("id");
				String bureau = resultat.getString("bureau");
				int personneID = resultat.getInt("id_Personne");

				// Récupére l'instance de Prsonne via l'id
				PersonneDao personneDao = daoFactory.getPersonneDao();
				Personne personne = personneDao.getPersonneById(personneID);

				Professeur professeur = new Professeur();
				professeur.setId(Integer.valueOf(id));
				professeur.setBureau(bureau);
				professeur.setPersonne(personne);
				professeurs.add(professeur);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.close();
			resultat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return professeurs;
	}

	@Override
	public Professeur getProfesseurById(String id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		Professeur professeur = new Professeur();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Professeur WHERE id=" + id + ";");
			connexion.close();

			while (resultat.next()) {
				String id2 = resultat.getString("id");
				String bureau = resultat.getString("bureau");
				int personneID = resultat.getInt("id_Personne");

				// Récupére l'instance de Prsonne via l'id
				PersonneDao personneDao = daoFactory.getPersonneDao();
				Personne personne = personneDao.getPersonneById(personneID);

				professeur.setId(Integer.valueOf(id2));
				professeur.setBureau(bureau);
				professeur.setPersonne(personne);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.close();
			resultat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return professeur;
	}

	@Override
	public void addProfesseur(ProfesseurDao professeurDao) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Professeur>  professeurByAudit(String id_Audit){
		ArrayList<Professeur> professeurs = new ArrayList<Professeur>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Professeur WHERE id IN (SELECT id_professeur FROM appartient ap WHERE ap.id = (SELECT id_jury from Audit WHERE id = ?));");
			preparedStmt.setString(1, id_Audit);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				String id = resultat.getString("id");
				String bureau = resultat.getString("bureau");
				int personneID = resultat.getInt("id_Personne");

				// Récupére l'instance de Prsonne via l'id
				PersonneDao personneDao = daoFactory.getPersonneDao();
				Personne personne = personneDao.getPersonneById(personneID);

				Professeur professeur = new Professeur();
				professeur.setId(Integer.valueOf(id));
				professeur.setBureau(bureau);
				professeur.setPersonne(personne);
				professeurs.add(professeur);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem", e);

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
			String requete = "DELETE * FROM Professeur WHERE id=?";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connexion.close();
			preparedStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Audit> getAudits(int matiere, boolean publies) {
		ArrayList<Audit> audits = new ArrayList<Audit>();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connexion.close();
			preparedStatement.close();
			resultat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return audits;
	}

	@Override
	public void setHeureAudits(String heureDebut, int id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			PreparedStatement preparedStatement = connexion
					.prepareStatement("SET dateDebut = to_date('?', 'YYYY/MM/DD HH24:MI') FROM audit WHERE id = ? ;");
			preparedStatement.setString(1, heureDebut);
			preparedStatement.setInt(2, id);
			resultat = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connexion.close();
			statement.close();
			resultat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getMatiereResponsable(int idProf) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		int matiere = -1;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			PreparedStatement preparedStatement = connexion
					.prepareStatement("SELECT id FROM Matiere WHERE responsable = ? ;");
			preparedStatement.setInt(1, idProf);
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				matiere = resultat.getInt("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connexion.close();
			statement.close();
			resultat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return matiere;
	}

	@Override
	public ArrayList<Professeur> getprofesseurByStr(String search) {
		DaoFactory fact = new DaoFactory();	
		
		ArrayList<Professeur> professeurs = new ArrayList<Professeur>();
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
				String id = resultat.getString("id");
				String bureau = resultat.getString("bureau");
				int personneID = resultat.getInt("id_Personne");

				// Récupére l'instance de Prsonne via l'id
				PersonneDao personneDao = daoFactory.getPersonneDao();
				Personne personne = personneDao.getPersonneById(personneID);

				Professeur professeur = new Professeur();
				professeur.setId(Integer.valueOf(id));
				professeur.setBureau(bureau);
				professeur.setPersonne(personne);
				professeurs.add(professeur);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			fact.close(connexion,statement,preparedStmt,resultat);	
		}
		
		return professeurs;
	}
	
	@Override
	public void addProfesseurToJuryId(String Id_Jury,String id_Professeur) {
		
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO appartient (id, id_Professeur) VALUES (?,?);");
			preparedStmt.setString(1, Id_Jury);
			preparedStmt.setString(2, id_Professeur);
			preparedStmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,null);	
		}
	}
	
	@Override
	public void removeProfesseurToJuryId(String Id_Jury,String id_Professeur) {
		
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("DELETE FROM appartient WHERE id_Professeur = ? AND id = ?;");
			preparedStmt.setString(1, Id_Jury);
			preparedStmt.setString(2, id_Professeur);
			preparedStmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,null);	
		}

	}
	
	public String getRoleProf ( String id) {
		String res= "";
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStmt=connexion.prepareStatement("SELECT designation FROM RoleUtilisateur ro, Professeur pr  WHERE ro.id = pr.id_Role AND pr.id= ?  ");
			preparedStmt.setInt(1, Integer.parseInt(id));
			resultat = preparedStmt.executeQuery();
			while (resultat.next()) {
				res = resultat.getString("designation");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			daoFactory.close(connexion, statement, preparedStmt,null );
		}
		return res;
	}
	
	
	
	
}
