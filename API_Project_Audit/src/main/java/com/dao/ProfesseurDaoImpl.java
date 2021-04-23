package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Audit;
import com.beans.Personne;
import com.beans.Professeur;

public class ProfesseurDaoImpl implements ProfesseurDao {

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

}
