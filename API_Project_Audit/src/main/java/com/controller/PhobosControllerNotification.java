package com.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Notification;
import com.dao.DaoFactory;
import com.dao.NotificationDao;
@CrossOrigin(origins = "*")
@RestController
public class PhobosControllerNotification {
	
	private static final Logger logger = Logger.getLogger(PhobosControllerNotification.class);
	
	@GetMapping(value = "/notificationByAudit")
	@ResponseBody
	public List<Notification> getnotificationByAudit(@RequestParam(required = true, value = "idAudit") String idAudit) {
		logger.log(Level.INFO, "appel get getnotificationByAudit");
		DaoFactory fact = new DaoFactory();
		NotificationDao notificationDao = fact.getNotificationDao();
		
		return notificationDao.getNotificationByAudit(idAudit);
	}
	
	@GetMapping(value = "/getNotificationByPersonneId")
	@ResponseBody
	public List<Notification> getNotificationByPersonneId(@RequestParam(required = true, value = "idPersonne") String idPersonne) {
		logger.log(Level.INFO, "appel get getNotificationByPersonneId");
		DaoFactory fact = new DaoFactory();
		NotificationDao notificationDao = fact.getNotificationDao();
		
		return notificationDao.getNotificationByPersonneId(idPersonne);
	}
	
	@GetMapping(value = "/notificationByEquipe")
	@ResponseBody
	public List<Notification> getNotificationByEquipe(@RequestParam(required = true, value = "idEquipe") String idEquipe) {
		logger.log(Level.INFO, "appel get getNotificationByEquipe");
		DaoFactory fact = new DaoFactory();
		NotificationDao notificationDao = fact.getNotificationDao();
		
		return notificationDao.getNotificationByEquipe(idEquipe);
	}
	
	@GetMapping(value = "/NotificationByMatiere")
	@ResponseBody
	public List<Notification> getNotificationByMatiere(@RequestParam(required = true, value = "idmatiere") String idmatiere) {
		logger.log(Level.INFO, "appel get getNotificationByMatiere");
		DaoFactory fact = new DaoFactory();
		NotificationDao notificationDao = fact.getNotificationDao();
		
		return notificationDao.getNotificationByMatiere(idmatiere);
	}
	
	@GetMapping(value = "/NotificationByUe")
	@ResponseBody
	public List<Notification> getNotificationByUe(@RequestParam(required = true, value = "idUE") String idUE) {
		logger.log(Level.INFO, "appel get getNotificationByEquipe");
		DaoFactory fact = new DaoFactory();
		NotificationDao notificationDao = fact.getNotificationDao();
		
		return notificationDao.getNotificationByUe(idUE);
	}
	
	@PostMapping(value = "/addNotification")
	@ResponseBody
	public void addNotification(@RequestBody Notification notification) {
		logger.log(Level.INFO, "appel get addNotification");
		DaoFactory fact = new DaoFactory();
		NotificationDao notificationDao = fact.getNotificationDao();
		
		notificationDao.addNotification(notification);
	}
	
}
