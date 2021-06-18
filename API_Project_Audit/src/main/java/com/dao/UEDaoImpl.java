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

import com.beans.Option;
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
			logger.log(Level.INFO, "sql problem getUES", e);
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
			preparedStmt.setString(2, idUe);
			preparedStmt.executeQuery();
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem updateUeProfRef", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
	}
	
	@Override
	public void addUe(UE ue) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO UE(Designation, Departement, id_Professeur, id_Option) VALUES (?,?,?,?)");
			preparedStmt.setString(1, ue.getDesignation());
			preparedStmt.setString(2, ue.getDepartement());
			preparedStmt.setInt(3, ue.getResponsable().getId());
			if(ue.getOption()!=null) {
				preparedStmt.setInt(4, ue.getOption().getId());
			}else {
				preparedStmt.setString(4, null);
			}
			preparedStmt.executeQuery();
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addUe", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
	}
	
	@Override
	public void deleteUe(String idUe) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("DELETE FROM UE WHERE id=?");
			preparedStmt.setString(1, idUe);
			preparedStmt.executeQuery();
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem deleteUe", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
	}
	
	@Override
	public UE getUeById(String idUe) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		UE ue = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM UE WHERE id=?");
			preparedStmt.setString(1, idUe);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String designation = resultat.getString("Designation");
				String departement = resultat.getString("Departement");
				Professeur responsable = daoFactory.getProfesseurDao().getProfesseurById(resultat.getString("id_Professeur")) ;
				Option option = daoFactory.getOptionDao().getOptionById(resultat.getString("id_Option"));
				
				ue = new UE(id,designation,departement);
				ue.setOption(option);
				ue.setResponsable(responsable);
			}
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getUeById", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		
		return ue;
	}
	
	
	@Override
	public List<UE> getUeByIdProfRef(int idProfRef) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		List<UE> ueList = new ArrayList<>();
		UE ue = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM UE WHERE id_Professeur=?");
			preparedStmt.setInt(1, idProfRef);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				int id = resultat.getInt("id");
				String designation = resultat.getString("Designation");
				String departement = resultat.getString("Departement");
				Professeur responsable = daoFactory.getProfesseurDao().getProfesseurById(resultat.getString("id_Professeur")) ;
				Option option = daoFactory.getOptionDao().getOptionById(resultat.getString("id_Option"));
				
				ue = new UE(id,designation,departement);
				ue.setOption(option);
				ue.setResponsable(responsable);
				
				ueList.add(ue);
			}
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getUeByIdProfRef", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		
		return ueList;
	}
}
