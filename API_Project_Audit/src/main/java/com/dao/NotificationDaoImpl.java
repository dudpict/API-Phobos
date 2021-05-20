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
				notification.setId_audit(idAuditRs);
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
			logger.log(Level.INFO, "sql problem getAuditById", e);
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
			logger.log(Level.INFO, "sql problem getAuditById", e);
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
			preparedStatement = connexion.prepareStatement("SELECT * FROM Audit WHERE 	id_Matiere = ? ;");
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
	public void addNotification(String typeNotif, String designation, String etat, String dateDeNotification, int idAudit) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("INSERT INTO Notification(type_notif, designation, etat, date_creation, id_Audit) VALUES (?,?,?,?,?);");
			preparedStatement.setString(1, typeNotif);
			preparedStatement.setString(2, designation);
			preparedStatement.setString(3, etat);
			preparedStatement.setString(4, dateDeNotification);
			preparedStatement.setInt(5, idAudit);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getAuditById", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,null);	
		}
	}
	
	
}
	


