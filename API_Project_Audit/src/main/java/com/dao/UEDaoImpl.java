package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.beans.Professeur;
import com.beans.UE;

public class UEDaoImpl implements UEDao{
	private DaoFactory daoFactory;
	
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
}
