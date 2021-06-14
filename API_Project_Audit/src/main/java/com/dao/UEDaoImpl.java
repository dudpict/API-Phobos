package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Professeur;
import com.beans.UE;

public class UEDaoImpl implements UEDao{
	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(UEDaoImpl.class);
	
	public UEDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public ArrayList<UE> getUES(){
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		ArrayList<UE> ues = new ArrayList<>();
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM UE ;");
			resultat = preparedStatement.executeQuery();
			
			while (resultat.next()) {
				String idProf = resultat.getString("id_Professeur");

				UE ue = new UE();
				ue.setId(resultat.getInt("id"));
				ue.setDesignation(resultat.getString("designation"));
				
				ue.setDepartement(resultat.getString("departement"));
				ProfesseurDao professeurDao = daoFactory.getProfesseurDao();
				Professeur matiereInstance = professeurDao.getProfesseurById(idProf);
				ue.setResponsable(matiereInstance);

				ues.add(ue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		
		return ues;
	}
	
	@Override
	public void updateUeProfRef(String idUe, String idProf) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("UPDATE UE SET id_Professeur=? WHERE id=?");
			preparedStmt.setString(1, idProf);
			preparedStmt.setString(1, idUe);
			preparedStmt.executeQuery();
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem updateUeProfRef", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
	}
}
