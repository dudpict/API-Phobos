package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.beans.Matiere;
import com.beans.Professeur;

public class MatiereDaoImpl implements MatiereDao {

	private DaoFactory daoFactory;
	private static final Logger logger = Logger.getLogger(MatiereDaoImpl.class);
	private String [] sqlParamTable = {"id","designation","effectif","departement","id_Professeur","id_UE"};

	public MatiereDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Matiere getMatiereById(String matiereID) {
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        PreparedStatement preparedStmt = null;
        Matiere matiere = new Matiere();

        try {
            connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Matiere WHERE id=?");
			preparedStmt.setString(1, matiereID);
			resultat = preparedStmt.executeQuery();
            
            
            while (resultat.next()) {
            	int id = resultat.getInt(sqlParamTable[0]);
				String designation = resultat.getString(sqlParamTable[1]);
				String departement = resultat.getString(sqlParamTable[3]);
				int effectif = resultat.getInt(sqlParamTable[2]);
				Professeur responsable = daoFactory.getProfesseurDao().getProfesseurById(resultat.getString(sqlParamTable[4]));
				
				matiere = new Matiere(id, designation, departement, effectif, responsable);
            }
        } catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getMatiereById", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);	
		}
        return matiere;
	}

	@Override
	public int getMatiereIdByResponsableID(int idProf) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		int matiere = -1;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT id FROM Matiere WHERE id_Professeur = ? ;");
			preparedStatement.setInt(1, idProf);
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				matiere = resultat.getInt(sqlParamTable[0]);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getMatiereIdByResponsableID", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		
		return matiere;
	}
	
	@Override
	public List<Matiere> getMatieres() {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		List<Matiere> matieres = new ArrayList<>();
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM Matiere ;");
			resultat = preparedStatement.executeQuery();
			
			while (resultat.next()) {
				String idProf = resultat.getString(sqlParamTable[4]);

				Matiere matiere = new Matiere();
				matiere.setId(resultat.getInt(sqlParamTable[0]));
				matiere.setDesignation(resultat.getString(sqlParamTable[1]));
				matiere.setEffectif(Integer.parseInt(resultat.getString(sqlParamTable[2])));
				matiere.setDepartement(resultat.getString(sqlParamTable[3]));
				ProfesseurDao professeurDao = daoFactory.getProfesseurDao();
				Professeur matiereInstance = professeurDao.getProfesseurById(idProf);
				matiere.setResponsable(matiereInstance);

				matieres.add(matiere);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getMatieres", e);
		}finally {
			daoFactory.close(connexion,statement,preparedStatement,resultat);	
		}
		
		return matieres;
	}

	@Override
	public void updateMatiereProfRef(String idMatiere, String idProf) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("UPDATE Matiere SET id_Professeur=? WHERE id=?");
			preparedStmt.setString(1, idProf);
			preparedStmt.setString(2, idMatiere);
			preparedStmt.executeQuery();
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem updateMatiereProfRef", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
	}
	
	@Override
	public List<Matiere> getMatiereByProfRefId(int idProf) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		Matiere matiere = null;
		List<Matiere> matiereList = new ArrayList<>(); 
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Matiere WHERE id_Professeur=?");
			preparedStmt.setInt(1, idProf);
			resultat = preparedStmt.executeQuery();
			
			while(resultat.next()) {
				int id = resultat.getInt(sqlParamTable[0]);
				String designation = resultat.getString(sqlParamTable[1]);
				String departement = resultat.getString(sqlParamTable[3]);
				int effectif = resultat.getInt(sqlParamTable[2]);
				Professeur responsable = daoFactory.getProfesseurDao().getProfesseurById(resultat.getString(sqlParamTable[4]));
				
				matiere = new Matiere(id, designation, departement, effectif, responsable);
				matiereList.add(matiere);
			}
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem updateMatiereProfRef", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		
		return matiereList;
	}
	
	
	@Override
	public void addMatiere(Matiere matiere) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		try {
			
			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO Matiere(designation, effectif, departement, id_Professeur, id_UE) VALUES (?,?,?,?,?)");
			preparedStmt.setString(1, matiere.getDesignation());
			preparedStmt.setInt(2, matiere.getEffectif());
			preparedStmt.setString(3, matiere.getDepartement());
			preparedStmt.setInt(4, matiere.getResponsable().getId());
			if(matiere.getUe()!=null) {
				preparedStmt.setInt(5, matiere.getUe().getId());
			}else {
				preparedStmt.setString(5, null);
			}
			
			preparedStmt.executeQuery();
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addMatiere", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
	}
	
	@Override
	public void deleteMatiere(String idMatiere) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("DELETE FROM Matiere WHERE id=?");
			preparedStmt.setString(1, idMatiere);
			preparedStmt.executeQuery();
	
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addMatiere", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
	}
}
