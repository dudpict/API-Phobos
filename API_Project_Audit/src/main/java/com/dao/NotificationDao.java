package com.dao;

import java.util.ArrayList;

import com.beans.Notification;

public interface NotificationDao {

	ArrayList<Notification> getNotificationByAudit(String idAudit);
	
	ArrayList<Notification> getNotificationByEquipe(String idEquipe);

	ArrayList<Notification> getNotificationByMatiere(String idmatiere);

	ArrayList<Notification> getNotificationByUe(String idUE);

	void addNotification(Notification notification);

	Notification getNotificationById(String idNotif);

	ArrayList<Notification> getNotificationByPersonneId(String idPersonne);

	void deleteNotificationById(String id);

	void deleteNotificationByIdAudit(String idAudit);
	
}
