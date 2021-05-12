package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beans.Audit;
import com.beans.Jury;
import com.beans.Lieu;
import com.beans.Matiere;
import com.beans.Modele;
@Component
public class AuditDaoImpl implements AuditDao {
	@Autowired
	private DaoFactory daoFactory;

	AuditDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	public ArrayList<Audit> getAudits(){
		ArrayList<Audit> audits = new ArrayList<Audit>();
		Connection connexion = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnection();
			PreparedStatement preparedStatement = connexion
					.prepareStatement("SELECT * FROM Audit;");
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				String modeleID = resultat.getString("id_Modele");
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
	public ArrayList<Audit> getAudits(int matiere, String publies) {
		ArrayList<Audit> audits = new ArrayList<Audit>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			if(publies.equals("true")) {
				preparedStatement = connexion.prepareStatement("SELECT * FROM Audit WHERE etat = 'publie' and id_Matiere = ? ;");
			}else {
				preparedStatement = connexion.prepareStatement("SELECT * FROM Audit WHERE etat <> 'publie' and id_Matiere = ? ;");
			}
			
			preparedStatement.setInt(1, matiere);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				String modeleID = resultat.getString("id_Modele");
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
			e.printStackTrace();
		}
		return audits;
	}

	@Override
	public Audit getAuditById(String id) {
		Connection connexion = null;
		ResultSet resultat = null;
		Audit audit = new Audit();

		try {
			connexion = daoFactory.getConnection();
			PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM Audit WHERE id = ? ;");
			preparedStatement.setString(1, id);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				String modeleID = resultat.getString("id_Modele");
				String juryID = resultat.getString("id_Jury");
				String matiereID = resultat.getString("id_Matiere");
				String lieuID = resultat.getString("id_Lieu");

				audit.setId(resultat.getInt("id"));
				audit.setDesignation(resultat.getString("designation"));
				audit.setEtat(resultat.getString("etat"));
				audit.setDateDebut(resultat.getString("dateDebut"));
				audit.setDateFin(resultat.getString("dateFin"));
				audit.setDateLimite(resultat.getString("dateLimite"));
				audit.setModeDate(resultat.getString("modeDate"));
				audit.setSemaineAudit(resultat.getString("semaineAudit"));
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
	public Audit addAudit(Audit audit) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion
					.prepareStatement("INSERT INTO Audit (designation, etat,dateDebut,dateFin,dateLimite,dateModif,note,id_Modele,id_Jury,id_Matiere,id_Lieu) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
			preparedStatement.setString(1, audit.getDesignation());
			preparedStatement.setString(2, audit.getEtat());
			preparedStatement.setString(3, audit.getDateDebut());
			preparedStatement.setString(4, audit.getDateFin());
			preparedStatement.setString(5, audit.getDateLimite());
			preparedStatement.setString(6, audit.getDateModif());
			preparedStatement.setInt(7, audit.getNote());
			preparedStatement.setInt(8, audit.getModele().getId());
			preparedStatement.setInt(9, audit.getJury().getId());
			preparedStatement.setInt(10, audit.getMatiere().getId());
			preparedStatement.setInt(11, audit.getLieu().getId());
			
			
			
			resultat = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultat.close();
			preparedStatement.close();
			statement.close();
			connexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return audit;

	}

	@Override
	public void deleteAudit(String id) {
		Connection connexion = null;
		try {
			connexion = daoFactory.getConnection();
			String requete = "DELETE * FROM audit WHERE id=?";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.execute();
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//
	@Override
	public void setHeureAudits(Audit audit) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion
					.prepareStatement("SET dateDebut = to_date('?', 'YYYY/MM/DD HH24:MI') FROM audit WHERE id = ? ;");
			preparedStatement.setString(1, audit.getDateDebut());
			preparedStatement.setInt(2, audit.getId());
			resultat = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultat.close();
			preparedStatement.close();
			statement.close();
			connexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public Audit updateAudit(Audit audit) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion
					.prepareStatement("UPDATE Audit  SET designation=?, etat=? ,dateDebut=?,dateFin=?,dateLimite=?,dateModif=?,note=?,id_Modele=?,id_Jury=?,id_Matiere=?,id_Lieu= ? WHERE id=?;");
			preparedStatement.setString(1, audit.getDesignation());
			preparedStatement.setString(2, audit.getEtat());
			preparedStatement.setString(3, audit.getDateDebut());
			preparedStatement.setString(4, audit.getDateFin());
			preparedStatement.setString(5, audit.getDateLimite());
			preparedStatement.setString(6, audit.getDateModif());
			preparedStatement.setInt(7, audit.getNote());
			preparedStatement.setInt(8, audit.getModele().getId());
			preparedStatement.setInt(9, audit.getJury().getId());
			preparedStatement.setInt(10, audit.getMatiere().getId());
			preparedStatement.setInt(11, audit.getLieu().getId());
			preparedStatement.setInt(12, audit.getId());
			
			
			
			resultat = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultat.close();
			preparedStatement.close();
			statement.close();
			connexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return audit;
		
	}
	@Override
	
	public Audit setAuditDate(Audit audit) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion
					.prepareStatement("UPDATE Audit SET dateLimite = STR_TO_DATE(?, '%Y-%m-%d'), dateMode='Date Limite' WHERE id = ? ;");
			preparedStatement.setString(1, audit.getDateLimite());
			preparedStatement.setInt(2, audit.getId());
			resultat = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultat.close();
			preparedStatement.close();
			statement.close();
			connexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return audit;
		
	}
	@Override
	public Audit setSemaineAudit(Audit audit) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion
					.prepareStatement("UPDATE Audit SET semaineAudit = ? , dateMode='WEEK' WHERE id = ? ;");
			preparedStatement.setString(1, audit.getSemaineAudit());
			preparedStatement.setInt(2, audit.getId());
			resultat = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			resultat.close();
			preparedStatement.close();
			statement.close();
			connexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return audit;
	}

}
