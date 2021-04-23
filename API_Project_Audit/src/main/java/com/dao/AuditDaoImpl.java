package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Audit;
import com.beans.Etudiant;
import com.beans.Jury;
import com.beans.Lieu;
import com.beans.Matiere;
import com.beans.Modele;

public class AuditDaoImpl implements AuditDao {

	private DaoFactory daoFactory;

	AuditDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Audit> getAudits(int matiere, boolean publies) {
		ArrayList<Audit> audits = new ArrayList<Audit>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		String egalOuDiff = publies ? "=" : "<>";

		try {
			connexion = daoFactory.getConnection();
			PreparedStatement preparedStatement = connexion
					.prepareStatement("SELECT * FROM audit WHERE etat ? 'publie' and matiere = ? ;");
			preparedStatement.setString(1, egalOuDiff);
			preparedStatement.setInt(2, matiere);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				int modeleID = resultat.getInt("id_Modele");
				String juryID = resultat.getString("id_Jury");
				String matiereID = resultat.getString("id_Matiere");
				String lieuID = resultat.getString("id_Lieu");

				Audit audit = new Audit();
				audit.setId(resultat.getInt("id"));
				audit.setDesignation(resultat.getString("designation"));
				audit.setEtat(resultat.getString("etat"));
				audit.setDateDebut(resultat.getString("dateDebut"));
				audit.setDateFin(resultat.getString("dateFin"));
				audit.setDateLimite(resultat.getString("dateLimite"));
				// TODO Implementer un object Matiere, Jury, Modele et Lieu
				// Récupére l'instance de Prsonne via l'id
				MatiereDao matiereDao = daoFactory.getMatiereDao();
				Matiere matiereInstance = matiereDao.getMatiereById(matiereID);
				audit.setMatiere(matiereInstance);

				JuryDao juryDao = daoFactory.getJuryDao();
				Jury jury = juryDao.getJuryById(juryID);
				audit.setJury(jury);

				ModeleDao modeleDao = daoFactory.getModeleDao();
				Modele modele = modeleDao.getModeleById(modeleID);
				audit.setModele(modele);

				LieuDao lieuDao = daoFactory.getLieuDao();
				Lieu lieu = lieuDao.getLieuById(lieuID);
				audit.setLieu(lieu);

				audits.add(audit);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return audits;
	}

	@Override
	public Audit getAuditById(String id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		Audit audit = new Audit();

		try {
			connexion = daoFactory.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM audit WHERE id = ? ;");
			preparedStatement.setString(1, id);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				int modeleID = resultat.getInt("id_Modele");
				String juryID = resultat.getString("id_Jury");
				String matiereID = resultat.getString("id_Matiere");
				String lieuID = resultat.getString("id_Lieu");

				audit.setId(resultat.getInt("id"));
				audit.setDesignation(resultat.getString("designation"));
				audit.setEtat(resultat.getString("etat"));
				audit.setDateDebut(resultat.getString("dateDebut"));
				audit.setDateFin(resultat.getString("dateFin"));
				audit.setDateLimite(resultat.getString("dateLimite"));
				// TODO Implementer un object Matiere, Jury, Modele et Lieu
				// Récupére l'instance de Prsonne via l'id
				MatiereDao matiereDao = daoFactory.getMatiereDao();
				Matiere matiereInstance = matiereDao.getMatiereById(matiereID);
				audit.setMatiere(matiereInstance);

				JuryDao juryDao = daoFactory.getJuryDao();
				Jury jury = juryDao.getJuryById(juryID);
				audit.setJury(jury);

				ModeleDao modeleDao = daoFactory.getModeleDao();
				Modele modele = modeleDao.getModeleById(modeleID);
				audit.setModele(modele);

				LieuDao lieuDao = daoFactory.getLieuDao();
				Lieu lieu = lieuDao.getLieuById(lieuID);
				audit.setLieu(lieu);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return audit;
	}

	@Override
	public void updateAudit(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addAudit(Etudiant etudiantToAdd) {
		// TODO A dev

	}

	@Override
	public void deleteAudit(String id) {
		// TODO A dev

	}

//

	public void setHeureAudits(String heureDebut, int id, String mode) {
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
	}

	public void setDateLimiteAudits(String heureLimite, int id) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			PreparedStatement preparedStatement = connexion
					.prepareStatement("SET dateLimite = to_date('?', 'YYYY/MM/DD HH24:MI') FROM audit WHERE id = ? ;");
			preparedStatement.setString(1, heureLimite);
			preparedStatement.setInt(2, id);
			resultat = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
