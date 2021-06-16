package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Matiere;
import com.beans.Option;
import com.beans.RoleUtilisateur;
import com.beans.UE;

public class RoleDaoImpl implements RoleDao{
	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(RoleDaoImpl.class);
	
	public RoleDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void  addRoleProfesseur(String idProf, String idRole, String idRef){
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		verifyRole(idProf, idRole, idRef);
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO  a_pour ( id , id_Professeur) VALUES (?,?);");
			preparedStmt.setString(1, idRole);
			preparedStmt.setString(2, idProf);
			if(getRoleByIdAndIdProf(idProf,idRole)==null) {
				preparedStmt.executeQuery();
			}
			
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addRoleProfesseur", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
	}	
	
	@Override
	public void  deleteRoleProfesseur(String idProf, String idRole){
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("DELETE FROM a_pour WHERE id= ? AND id_Professeur=? ;");
			preparedStmt.setString(1, idRole);
			preparedStmt.setString(2, idProf);
			preparedStmt.executeQuery();			
			
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem deleteRoleProfesseur", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
	}	
	
	@Override
	public void  updateRoleProfesseur(String idProf, String idRole, String newIdRole, String idRef){
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		verifyRole(idProf, idRole, idRef);
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("UPDATE a_pour SET id_Professeur=? WHERE id=? AND id_Professeur=?");
			preparedStmt.setString(1, newIdRole);
			preparedStmt.setString(2, idRole);
			preparedStmt.setString(1, idProf);
			preparedStmt.executeQuery();
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem updateRoleProfesseur", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
	}	
	
	@Override
	public RoleUtilisateur  getRoleByIdAndIdProf(String idProf, String idRole){
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		RoleUtilisateur roleUt = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM a_pour WHERE id=? AND id_Professeur=?");
			preparedStmt.setString(1, idRole);
			preparedStmt.setString(2, idProf);
			resultat = preparedStmt.executeQuery();
			while (resultat.next()) {
				roleUt = new RoleUtilisateur(resultat.getInt("id"), resultat.getString("id_Professeur")); 
			}	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getRoleProfesseur", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		return roleUt;
	}	
	
	@Override
	public void  verifyRole(String idProf, String idRole, String idRef){
		
		if(idRole.equals(Integer.toString(2))) {
			OptionDao optDao = daoFactory.getOptionDao();
			
			Option option = optDao.getOptionById(idRef);
			int idProfRef = option.getIdProfesseur();
			
			if(optDao.getOptionByIdProfRef(idProfRef).size()<2) {
				deleteRoleProfesseur(Integer.toString(idProfRef), idRole);
			}
			
			optDao.updateOptionProfRef(idRef, idProf);		
			
		}else if (idRole.equals(Integer.toString(3))) {
			UEDao ueDao= daoFactory.getUEDao();
			
			UE ue = ueDao.getUeById(idRef);
			int idProfRef = ue.getResponsable().getId();
			
			if(ueDao.getUeByIdProfRef(idProfRef).size()<2) {
				deleteRoleProfesseur(Integer.toString(idProfRef), idRole);
			}			
			
			ueDao.updateUeProfRef(idRef, idProf);
			
		}else if (idRole.equals(Integer.toString(4))) {
			MatiereDao matDao = daoFactory.getMatiereDao();
			
			Matiere matiere = matDao.getMatiereById(idRef);
			int idProfRef = matiere.getResponsable().getId();
			
			if(matDao.getMatiereByProfRefId(idProfRef).size()<2) {
				deleteRoleProfesseur(Integer.toString(idProfRef), idRole);
			}
			
			matDao.updateMatiereProfRef(idRef, idProf);
			
		}
	}
	
}
