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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beans.Audit;
import com.beans.Equipe;
import com.beans.Jury;
import com.beans.Lieu;
import com.beans.Matiere;
import com.beans.Modele;
@Component
public class AuditDaoImpl implements AuditDao {
	@Autowired
	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(AuditDaoImpl.class);
	private String sqlSelectAll = "SELECT * FROM Audit;";
	private String [] sqlParamTable = {"id_Modele","id_Jury","id_Lieu","id_Equipe","id_Matiere"};
	private String [] sqlParamAudit = {"id","designation","etat","dateDebut","dateFin","dateLimite","dateMode"};
	
	AuditDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public List<Audit> getAudits(){
		ArrayList<Audit> audits = new ArrayList<>();
		Connection connexion = null;
		ResultSet resultat = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement(sqlSelectAll);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				String modeleID = resultat.getString(sqlParamTable[0]);
				String juryID = resultat.getString(sqlParamTable[1]);
				String matiereID = resultat.getString(sqlParamTable[4]);
				String lieuID = resultat.getString(sqlParamTable[2]);
				String equipeID = resultat.getString(sqlParamTable[3]);

				Audit audit = new Audit();
				audit.setId(resultat.getInt(sqlParamAudit[0]));
				audit.setDesignation(resultat.getString(sqlParamAudit[1]));
				audit.setEtat(resultat.getString(sqlParamAudit[2]));
				audit.setDateDebut(resultat.getString(sqlParamAudit[3]));
				audit.setDateFin(resultat.getString(sqlParamAudit[4]));
				audit.setDateLimite(resultat.getString(sqlParamAudit[5]));
				audit.setModeDate(resultat.getString(sqlParamAudit[6]));
				
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
				
				EquipeDao equipeDao = daoFactory.getEquipeDao();
				Equipe equipe = equipeDao.getEquipeById(equipeID);
				audit.setEquipe(equipe);
				audits.add(audit);
			}

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getAudits", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		return audits;
	}
	
	@Override
	public ArrayList<Audit> isPersonneIsInAudit(String idPersonne){
		String idAudit ="-1";
		
		ArrayList<Audit> audits = new ArrayList<>();
		AuditDao auditDao =daoFactory.getAuditDao(); 
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT id FROM Audit where id_Equipe IN (SELECT id_Equipe FROM Etudiant WHERE id_Personne =? ) OR id_Jury IN (SELECT a.id FROM appartient a, Professeur p WHERE a.id_Professeur=p.id AND p.id_Personne=?);");
			preparedStmt.setString(1, idPersonne);
			preparedStmt.setString(2, idPersonne);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				idAudit = resultat.getString("id");
				audits.add(auditDao.getAuditById(idAudit));
	           
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem isPersonneIsInAudit", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		if(idAudit.equals("-1")) {
			audits = null;
		}		
		return audits;
	}
	
	@Override
	public ArrayList<Audit> auditByEtudiantId(String idEtudiant){
		ArrayList<Audit> audits = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		ResultSet resultat = null;
		
		try {
			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Audit a INNER JOIN Etudiant e ON a.id_Equipe = e.id_Equipe WHERE e.id = ?;");
			preparedStmt.setString(1, idEtudiant);
			resultat= preparedStmt.executeQuery();

			while (resultat.next()) {
				String modeleID = resultat.getString(sqlParamTable[0]);
				String juryID = resultat.getString(sqlParamTable[1]);
				String matiereID = resultat.getString(sqlParamTable[4]);
				String lieuID = resultat.getString(sqlParamTable[2]);
				String equipeID = resultat.getString(sqlParamTable[3]);

				Audit audit = new Audit();
				audit.setId(resultat.getInt(sqlParamAudit[0]));
				audit.setDesignation(resultat.getString(sqlParamAudit[1]));
				audit.setEtat(resultat.getString(sqlParamAudit[2]));
				audit.setDateDebut(resultat.getString(sqlParamAudit[3]));
				audit.setDateFin(resultat.getString(sqlParamAudit[4]));
				audit.setDateLimite(resultat.getString(sqlParamAudit[5]));
				audit.setModeDate(resultat.getString(sqlParamAudit[6]));

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
				
				EquipeDao equipeDao = daoFactory.getEquipeDao();
				Equipe equipe = equipeDao.getEquipeById(equipeID);
				audit.setEquipe(equipe);
				audits.add(audit);
			}

		}catch (SQLException e) {
			logger.log(Level.INFO, "sql problem auditByProfesseurId", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);	
		}
		return audits;
	}

	@Override
	public ArrayList<Audit> auditByProfesseurId(String idProfesseur){
		ArrayList<Audit> audits = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		ResultSet resultat = null;
		
		try {
			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Audit a INNER JOIN appartient ap ON a.id_Jury = ap.id WHERE ap.id IN (SELECT id FROM appartient ap WHERE ap.id_Professeur =?) GROUP BY a.id;");
			preparedStmt.setString(1, idProfesseur);
			resultat= preparedStmt.executeQuery();

			while (resultat.next()) {
				String modeleID = resultat.getString(sqlParamTable[0]);
				String juryID = resultat.getString(sqlParamTable[1]);
				String matiereID = resultat.getString(sqlParamTable[4]);
				String lieuID = resultat.getString(sqlParamTable[2]);
				String equipeID = resultat.getString(sqlParamTable[3]);
				
				Audit audit = new Audit();
				audit.setId(resultat.getInt(sqlParamAudit[0]));
				audit.setDesignation(resultat.getString(sqlParamAudit[1]));
				audit.setEtat(resultat.getString(sqlParamAudit[2]));
				audit.setDateDebut(resultat.getString(sqlParamAudit[3]));
				audit.setDateFin(resultat.getString(sqlParamAudit[4]));
				audit.setDateLimite(resultat.getString(sqlParamAudit[5]));
				audit.setModeDate(resultat.getString(sqlParamAudit[6]));
				// Implementer un object Matiere, Jury, Modele et Lieu
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
				
				EquipeDao equipeDao = daoFactory.getEquipeDao();
				Equipe equipe = equipeDao.getEquipeById(equipeID);
				audit.setEquipe(equipe);
				audits.add(audit);
			}

		}catch (SQLException e) {
			logger.log(Level.INFO, "sql problem auditByProfesseurId", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);	
		}
		return audits;
	}
	
	@Override
	public Audit getAuditById(String id) {
		Connection connexion = null;
		ResultSet resultat = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		Audit audit = new Audit();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM Audit WHERE id = ? ;");
			preparedStatement.setString(1, id);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				String modeleID = resultat.getString(sqlParamTable[0]);
				String juryID = resultat.getString(sqlParamTable[1]);
				String matiereID = resultat.getString(sqlParamTable[4]);
				String lieuID = resultat.getString(sqlParamTable[2]);
				
				audit.setId(resultat.getInt(sqlParamAudit[0]));
				audit.setDesignation(resultat.getString(sqlParamAudit[1]));
				audit.setEtat(resultat.getString(sqlParamAudit[2]));
				audit.setDateDebut(resultat.getString(sqlParamAudit[3]));
				audit.setDateFin(resultat.getString(sqlParamAudit[4]));
				audit.setDateLimite(resultat.getString(sqlParamAudit[5]));
				audit.setModeDate(resultat.getString(sqlParamAudit[6]));
				// Implementer un object Matiere, Jury, Modele et Lieu
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
			logger.log(Level.INFO, "sql problem getAuditById", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		return audit;
	}


	@Override
	public Audit addAudit(Audit audit) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("INSERT INTO Audit (designation, etat,dateDebut,dateFin,dateLimite,dateModif,note,id_Modele,id_Jury,id_Matiere,id_Lieu,dateMode,semaineAudit,id_Equipe ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
			preparedStatement.setString(1, audit.getDesignation());
			preparedStatement.setString(2, audit.getEtat());
			preparedStatement.setString(3, null);
			preparedStatement.setString(4, null);
			preparedStatement.setString(5, audit.getDateLimite());
			preparedStatement.setString(6, audit.getDateModif());
			preparedStatement.setString(7, null);
			preparedStatement.setInt(8, audit.getModele().getId());
			preparedStatement.setString(9, null);
			preparedStatement.setInt(10, audit.getMatiere().getId());
			preparedStatement.setInt(11, audit.getLieu().getId());
			preparedStatement.setString(12, audit.getModeDate());
			preparedStatement.setString(13, audit.getSemaineAudit());
			preparedStatement.setInt(14, audit.getEquipe().getId());
						
			preparedStatement.executeQuery();

		}  catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addAudit", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,null);	
		}
		return audit;

	}

	@Override
	public void deleteAudit(String id) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;
		Statement statement = null;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			String requete = "DELETE * FROM audit WHERE id=?";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.execute();
		}  catch (SQLException e) {
			logger.log(Level.INFO, "sql problem deleteAudit", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,null);	
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
			preparedStatement = connexion.prepareStatement("SET dateDebut = to_date('?', 'YYYY/MM/DD HH24:MI') FROM audit WHERE id = ? ;");
			preparedStatement.setString(1, audit.getDateDebut());
			preparedStatement.setInt(2, audit.getId());
			resultat = preparedStatement.executeQuery();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem setHeureAudits", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
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
			logger.log(Level.INFO, "sql problem updateAudit", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
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
					.prepareStatement("UPDATE Audit SET dateLimite = STR_TO_DATE(?, '%Y-%m-%d'), dateMode= ? WHERE id = ? ;");
			preparedStatement.setString(1, audit.getDateLimite());
			preparedStatement.setString(2, audit.getModeDate());
			preparedStatement.setInt(3, audit.getId());
			resultat = preparedStatement.executeQuery();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem setAuditDate", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
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
			preparedStatement = connexion.prepareStatement("UPDATE Audit SET semaineAudit = ? , dateMode='WEEK' WHERE id = ? ;");
			preparedStatement.setString(1, audit.getSemaineAudit());
			preparedStatement.setInt(2, audit.getId());
			resultat = preparedStatement.executeQuery();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem setSemaineAudit", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		return audit;
	}
	
	
	@Override
	public String[] returnParam(String matiereId,String lieuId,String ueId,String juryId) {
		String [] tablParamid = {"","","",""};
		tablParamid[0]=matiereId;
		tablParamid[1]=lieuId;
		tablParamid[2]=ueId;
		tablParamid[3]=juryId;		
		return tablParamid;
	}
	
	@Override
	public ArrayList<Audit> getFilteredAudits(String titre,String etat,String id, String role,String [] tablParamid){
		ArrayList<Audit> audits = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;

		String matiereId=tablParamid[0];
		String lieuId=tablParamid[1];
		String ueId=tablParamid[2];
		String juryId=tablParamid[3];
		
		try {
			connexion = daoFactory.getConnection();
			
			switch(role) {
			case  "OPTION_RESP" :
				preparedStatement = connexion
				.prepareStatement("SELECT * FROM Audit WHERE id_Matiere IN (SELECT id FROM Matiere WHERE Matiere.id_UE IN (SELECT id FROM UE WHERE UE.id_Option IN (SELECT id FROM options WHERE options.id_Professeur = ? ) AND UE.id LIKE ? )) AND `id_Jury` LIKE ? AND `id_Lieu` LIKE ? AND REPLACE(etat,' ','') LIKE ? AND REPLACE(designation,' ','') LIKE ? AND `id_Matiere` LIKE ? ;");
				preparedStatement.setInt(1, Integer.parseInt(id));
				preparedStatement.setString(2, comparPreparStatemStr(ueId));
				preparedStatement.setString(3, comparPreparStatemStr(juryId));
				preparedStatement.setString(4, comparPreparStatemStr(lieuId));
				preparedStatement.setString(5, comparPreparStatemStr(etat));
				preparedStatement.setString(6, comparPreparStatemStr(titre));
				preparedStatement.setString(7, comparPreparStatemStr(matiereId));
				
				break;	
			case "UE_RESP" : 
				preparedStatement = connexion
				.prepareStatement("SELECT * FROM Audit WHERE `id_Matiere` IN (SELECT id FROM Matiere WHERE id_UE IN (SELECT id FROM UE WHERE id_Professeur = ?)  ) AND `id_Jury` LIKE ? AND `id_Lieu` LIKE ? AND REPLACE(etat,' ','') LIKE ? AND REPLACE(designation,' ','') LIKE ? AND id_Matiere LIKE ?;");
				
				preparedStatement.setInt(1, Integer.parseInt(id));
				preparedStatement.setString(2, comparPreparStatemStr(juryId));
				preparedStatement.setString(3, comparPreparStatemStr(lieuId));
				preparedStatement.setString(4, comparPreparStatemStr(etat));
				preparedStatement.setString(5, comparPreparStatemStr(titre));
				preparedStatement.setString(6, comparPreparStatemStr(matiereId));
				
				break;
			case "MATIERE_ENSEIGNANT" :
				preparedStatement = connexion
				.prepareStatement("SELECT * FROM `Audit` WHERE id_Matiere IN ( SELECT id FROM enseigne WHERE id_Professeur = ? ) AND `id_Jury` LIKE ? AND `id_Lieu` LIKE ? AND REPLACE(etat,' ','') LIKE ? AND REPLACE(designation,' ','') LIKE ? AND id_Matiere IN (SELECT id FROM Matiere WHERE id_UE LIKE ? )  AND id_Matiere LIKE ?;");
				
				preparedStatement.setInt(1, Integer.parseInt(id));
				preparedStatement.setString(2, comparPreparStatemStr(ueId));
				preparedStatement.setString(3, comparPreparStatemStr(juryId));
				preparedStatement.setString(4, comparPreparStatemStr(lieuId));
				preparedStatement.setString(5, comparPreparStatemStr(etat));
				preparedStatement.setString(6, comparPreparStatemStr(titre));
				preparedStatement.setString(7, comparPreparStatemStr(matiereId));
				
				break;
			case "MATIERE_ELEVE" : 
				preparedStatement = connexion
				.prepareStatement("SELECT * FROM `Audit` WHERE id_Equipe IN ( SELECT id_Equipe FROM Etudiant WHERE id = ? ) AND `id_Jury` LIKE ? AND `id_Lieu` LIKE ? AND REPLACE(etat,' ','') LIKE ? AND REPLACE(designation,' ','') LIKE ? AND id_Matiere IN (SELECT id FROM Matiere WHERE id_UE LIKE ? ) AND id_Matiere LIKE ? ;");
				
				preparedStatement.setInt(1, Integer.parseInt(id));
				preparedStatement.setString(2, comparPreparStatemStr(juryId));
				preparedStatement.setString(3, comparPreparStatemStr(lieuId));
				preparedStatement.setString(4, comparPreparStatemStr(etat));
				preparedStatement.setString(5, comparPreparStatemStr(titre));
				preparedStatement.setString(6, comparPreparStatemStr(ueId));
				preparedStatement.setString(7, comparPreparStatemStr(matiereId));
				
				break;
				
			case "MATIERE_RESP" : 
				preparedStatement = connexion
				.prepareStatement("SELECT * FROM `Audit` WHERE id_Matiere IN ( SELECT id FROM enseigne WHERE id_Professeur = ? ) AND `id_Jury` LIKE ? AND `id_Lieu` LIKE ? AND REPLACE(etat,' ','') LIKE ? AND REPLACE(designation,' ','') LIKE ? AND id_Matiere IN (SELECT id FROM Matiere WHERE id_UE LIKE ? )  AND id_Matiere LIKE ? OR id_Matiere IN ( SELECT id From Matiere WHERE id_Professeur = ?);");
				
				preparedStatement.setInt(1, Integer.parseInt(id));
				preparedStatement.setString(2, comparPreparStatemStr(ueId));
				preparedStatement.setString(3, comparPreparStatemStr(juryId));
				preparedStatement.setString(4, comparPreparStatemStr(lieuId));
				preparedStatement.setString(5, comparPreparStatemStr(etat));
				preparedStatement.setString(6, comparPreparStatemStr(titre));
				preparedStatement.setString(7, comparPreparStatemStr(matiereId));
				preparedStatement.setInt(8, Integer.parseInt(id));
				
				break;
				
			default :
				preparedStatement = connexion
				.prepareStatement("SELECT * FROM Audit");
				
				break;			
			}			
			logger.log(Level.INFO, preparedStatement);
			
			
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				String modeleID = resultat.getString("id_Modele");
				String juryID = resultat.getString("id_Jury");
				String matiereID = resultat.getString("id_Matiere");
				String lieuID = resultat.getString("id_Lieu");
				String equipeID = resultat.getString(("id_Equipe"));

				Audit auditTemp = new Audit();
				auditTemp.setId(resultat.getInt("id"));
				auditTemp.setDesignation(resultat.getString("designation"));
				auditTemp.setEtat(resultat.getString("etat"));
				auditTemp.setDateDebut(resultat.getString("dateDebut"));
				auditTemp.setDateFin(resultat.getString("dateFin"));
				auditTemp.setDateLimite(resultat.getString("dateLimite"));
				auditTemp.setModeDate(resultat.getString("dateMode"));
				// Récupére l'instance de Prsonne via l'id
				MatiereDao matiereDao = daoFactory.getMatiereDao();
				Matiere matiereInstance = matiereDao.getMatiereById(matiereID);
				auditTemp.setMatiere(matiereInstance);

				JuryDao juryDao = daoFactory.getJuryDao();
				Jury jury = juryDao.getJuryById(juryID);
				auditTemp.setJury(jury);

				ModeleDao modeleDao = daoFactory.getModeleDao();
				Modele modele = modeleDao.getModeleById(modeleID);
				auditTemp.setModele(modele);

				LieuDao lieuDao = daoFactory.getLieuDao();
				Lieu lieu = lieuDao.getLieuById(lieuID);
				auditTemp.setLieu(lieu);
				
				EquipeDao equipeDao = daoFactory.getEquipeDao();
				Equipe equipe = equipeDao.getEquipeById(equipeID);
				auditTemp.setEquipe(equipe);
				

				audits.add(auditTemp);
			}

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem filter Audit", e);
		}finally {
			daoFactory.close(connexion,null,preparedStatement,resultat);	
		}
		return audits;
	}
	
	
	
	public String comparPreparStatemStr(String compare) {
		if (compare!=null && !compare.equals("\"\"")) {
			compare= "%"+compare+"%";

		}else {
			compare="%";
		}
		return compare;
	}
	@Override
	public void addEquipeToAudit(String idEquipe,String idAudit) {
		
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("UPDATE Audit a SET a.id_Equipe = ? WHERE a.id = ?;");
			preparedStmt.setString(1, idEquipe);
			preparedStmt.setString(2, idAudit);
			preparedStmt.executeQuery();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem add equipe Audit", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,null);	
		}

	}
	
	@Override
	public void removeEquipeToAudit(String idAudit) {
		
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("UPDATE Audit a SET a.id_Equipe = null WHERE a.id = ?");
			preparedStmt.setString(1, idAudit);
			preparedStmt.executeQuery();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem remove equipe Audit", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,null);	
		}

	}

	@Override
	public void addJuryToAudit(String idJury, String idAudit) {
		
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("UPDATE Audit a SET a.id_Jury = ? WHERE a.id = ?");
			preparedStmt.setString(1, idJury);
			preparedStmt.setString(2, idAudit);
			preparedStmt.executeQuery();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,null);	
		}

	}
	
	@Override
	public void removeJuryToAudit(String idAudit) {
		
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("UPDATE Audit a SET a.id_Jury = null WHERE a.id =?");
			preparedStmt.setString(1, idAudit);
			preparedStmt.executeQuery();

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,null);	
		}

	}
}
	


