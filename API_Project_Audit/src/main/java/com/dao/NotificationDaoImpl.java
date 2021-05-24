package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.beans.Notification;

@Component
public class NotificationDaoImpl implements NotificationDao {
	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(NotificationDaoImpl.class);
	
	
	NotificationDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public Notification getNotificationById(String idNotif) {
		Connection connexion = null;
		ResultSet resultat = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		Notification notification = new Notification();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM Notification WHERE id = ? ;");
			preparedStatement.setString(1, idNotif);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				
				int id = resultat.getInt("id");
				String typeNotif = resultat.getString("type_notif");
				String designation = resultat.getString("designation");
				String etat = resultat.getString("etat");
				String dateDeNotification = resultat.getString("date_creation");
				int idAuditRs = resultat.getInt("id_Audit");
							
				notification.setId(id);
				notification.setTypeNotif(typeNotif);
				notification.setDesignation(designation);
				notification.setEtat(etat);
				notification.setDateDeNotification(dateDeNotification);
				notification.setIdaudit(idAuditRs);
			}

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getNotificationById", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		return notification;
	}
		
	@Override
	public ArrayList<Notification> getNotificationByPersonneId(String idPersonne) {
		int id = -1;
		Connection connexion = null;
		ResultSet resultat = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<Notification> notificationsList = new ArrayList<>();
		NotificationDao notificationDao = daoFactory.getNotificationDao();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM Notification WHERE id_Personne = ? ;");
			preparedStatement.setString(1, idPersonne);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {				
				id = resultat.getInt("id");				
				
				Notification notification = notificationDao.getNotificationById(Integer.toString(id));
				notificationsList.add(notification);
			}

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getNotificationByAudit", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		if(id==-1) {
			notificationsList=null;
		}
		return notificationsList;
	}
	
	
	@Override
	public ArrayList<Notification> getNotificationByAudit(String idAudit) {
		Connection connexion = null;
		ResultSet resultat = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Notification> notificationsList = new ArrayList<>();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM Notification WHERE id_Audit = ? ;");
			preparedStatement.setString(1, idAudit);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				
				int id = resultat.getInt("id");
				String typeNotif = resultat.getString("type_notif");
				String designation = resultat.getString("designation");
				String etat = resultat.getString("etat");
				String dateDeNotification = resultat.getString("date_creation");
				int idAuditRs = resultat.getInt("id_Audit");
				
				
				Notification notification = new Notification();
				notification.setId(id);
				notification.setTypeNotif(typeNotif);
				notification.setDesignation(designation);
				notification.setEtat(etat);
				notification.setDateDeNotification(dateDeNotification);
				notification.setIdaudit(idAuditRs);
				notificationsList.add(notification);
			}

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getNotificationByAudit", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		return notificationsList;
	}
	
	@Override
	public ArrayList<Notification> getNotificationByEquipe(String idEquipe) {
		Connection connexion = null;
		ResultSet resultat = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Notification> notificationsList = new ArrayList<>();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM Audit WHERE id_Equipe = ? ;");
			preparedStatement.setString(1, idEquipe);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				String id = resultat.getString("id");
				notificationsList.addAll(getNotificationByAudit(id));
			}

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getNotificationByEquipe", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		return notificationsList;
	}
	
	@Override
	public ArrayList<Notification> getNotificationByUe(String idUE) {
		Connection connexion = null;
		ResultSet resultat = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Notification> notificationsList = new ArrayList<>();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM Audit WHERE id_Matiere IN (SELECT id FROM Matiere WHERE id_UE=?) ;");
			preparedStatement.setString(1, idUE);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {				
				String id = resultat.getString("id");
				notificationsList.addAll(getNotificationByAudit(id));
			}

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getNotificationByUe", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		return notificationsList;
	}
	
	@Override
	public ArrayList<Notification> getNotificationByMatiere(String idmatiere) {
		Connection connexion = null;
		ResultSet resultat = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ArrayList<Notification> notificationsList = new ArrayList<>();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM Audit WHERE id_Matiere = ?;");
			preparedStatement.setString(1, idmatiere);
			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				
				String id = resultat.getString("id");
				notificationsList.addAll(getNotificationByAudit(id));
			}

		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getAuditById", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		return notificationsList;
	}
	
	
	
	@Override
	public void addNotification(Notification notification) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		
		try {		
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("INSERT INTO Notification(type_notif, designation, etat, date_creation, id_Audit,id_Personne) VALUES (?,?,?,?,?,?);");
			preparedStatement.setString(1, notification.getTypeNotif());
			preparedStatement.setString(2,notification.getDesignation());
			preparedStatement.setString(3, notification.getEtat());
			preparedStatement.setString(4, notification.getDateDeNotification());
			if(notification.getIdaudit()== 0) {
				preparedStatement.setNull(5, java.sql.Types.INTEGER);
			}else {
				preparedStatement.setInt(5, notification.getIdaudit());
			}
			if(notification.getIdpersonne()== 0) {
				preparedStatement.setNull(6, java.sql.Types.INTEGER);
			}else {
				preparedStatement.setInt(6, notification.getIdpersonne());
			}		
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getAuditById", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,null);	
		}
	}
	
	
}
	


