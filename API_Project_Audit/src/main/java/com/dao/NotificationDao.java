package com.dao;

import java.util.ArrayList;

import com.beans.Notification;

public interface NotificationDao {

	ArrayList<Notification> getNotificationByAudit(String idAudit);

	void addNotification(String typeNotif, String designation, String etat, String dateDeNotification, int idAudit);

	ArrayList<Notification> getNotificationByEquipe(String idEquipe);

	ArrayList<Notification> getNotificationByMatiere(String idmatiere);

	ArrayList<Notification> getNotificationByUe(String idUE);
	
}
