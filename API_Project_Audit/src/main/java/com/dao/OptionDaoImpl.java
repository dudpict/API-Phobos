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

public class OptionDaoImpl implements OptionDao {

	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(OptionDaoImpl.class);

	public OptionDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void updateOptionProfRef(String idOption, String idProf) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("UPDATE options SET id_Professeur=? WHERE id=?");
			preparedStmt.setString(1, idProf);
			preparedStmt.setString(2, idOption);
			preparedStmt.executeQuery();
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem updateOptionProfRef", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
	}
	
	
	@Override
	public Option getOptionById(String idOption) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		Option option = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM options SET WHERE id=?");
			preparedStmt.setString(1, idOption);
			resultat = preparedStmt.executeQuery();
			while (resultat.next()) {
				int id = resultat.getInt("id");
				int idProfesseur = resultat.getInt("idProfesseur");
				String designation = resultat.getString("designation");
				option = new Option(id,idProfesseur,designation);				
			}
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getOptionById", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		return option;
	}
	
	
	@Override
	public List<Option> getOptionByIdProfRef(int idProfRef) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		Option option = null;
		List<Option> optionList = new ArrayList<>();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM options SET WHERE id_Professeur=?");
			preparedStmt.setInt(1, idProfRef);
			resultat = preparedStmt.executeQuery();
			while (resultat.next()) {
				int id = resultat.getInt("id");
				int idProfesseur = resultat.getInt("idProfesseur");
				String designation = resultat.getString("designation");
				option = new Option(id,idProfesseur,designation);	
				optionList.add(option);
			}
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getOptionByIdProfRef", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		return optionList;
	}

}
