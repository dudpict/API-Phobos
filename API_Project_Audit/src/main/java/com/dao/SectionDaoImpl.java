package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Modele;
import com.beans.Section;

public class SectionDaoImpl implements SectionDao {
	
	private DaoFactory daoFactory;

	SectionDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

	@Override
	public ArrayList<Section> getSections() {
		ArrayList<Section> sections = new ArrayList<Section>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM section;");
            connexion.close();

            while (resultat.next()) {
            	int id = resultat.getInt("id");
                String designation = resultat.getString("designation");
                String modeleID = resultat.getString("id_Modele");
                
                
                //Récupére l'instance de Prsonne via l'id
        		ModeleDao modeleDao = daoFactory.getModeleDao();
        		Modele modele  = modeleDao.getModeleById(modeleID);
        		        		
        		//TODO IMPLEMENTER EQUIPE ET ROLE MODELE
                Section section = new Section();
                section.setId(id);
                section.setDesignation(designation);
                section.setModele(modele);

                sections.add(section);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;
	}

	@Override
	public void deleteSection(String id) {
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "DELETE FROM section WHERE id=?";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.execute();
			connexion.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Section getSectionById(int sectionID) {

		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        Section section=new Section();
        
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM section WHERE id="+sectionID+";");
            
            connexion.close();

            while (resultat.next()) {
            	int id = resultat.getInt("id");
                String designation = resultat.getString("designation");
                String modeleID = resultat.getString("id_Modele");
                
                
                //Récupére l'instance de Prsonne via l'id
        		ModeleDao modeleDao = daoFactory.getModeleDao();
        		Modele modele  = modeleDao.getModeleById(modeleID);
        		        		
        		//TODO IMPLEMENTER EQUIPE ET ROLE MODELE
                section.setId(id);
                section.setDesignation(designation);
                section.setModele(modele);
                
//                connexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return section;
	}
	
	
	@Override
	public void addSection(String Designation, int id_Modele) {
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "INSERT INTO `section`(`designation`, `id_Modele`) VALUES (?,?);";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, Designation);
			preparedStmt.setInt(2, id_Modele);
			preparedStmt.execute();
			connexion.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	@Override
	public void updateSection(int id,String Designation,int id_Modele) {
		Connection connexion = null;

		try {
			connexion = daoFactory.getConnection();
			String requete = "UPDATE `section` SET `designation`=?,`id_Modele`=? WHERE `id`=?;";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, Designation);
			preparedStmt.setInt(2, id_Modele);
			preparedStmt.setInt(3, id);
			preparedStmt.execute();
			connexion.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public ArrayList<Section> getSectionByIdModele(String id_Modele) {
		ArrayList<Section> sections = new ArrayList<Section>();
        Connection connexion = null;
        Statement statement = null;

        try {
        	connexion = daoFactory.getConnection();
			String requete = "SELECT * FROM section WHERE 'id_Modele'=?;";
			PreparedStatement preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id_Modele);
			ResultSet rs = preparedStmt.executeQuery();

            while (rs.next()) {
            	int id = rs.getInt("id");
                String designation = rs.getString("designation");
                String modeleID = rs.getString("id_Modele");
                
                
                //Récupére l'instance de Prsonne via l'id
        		ModeleDao modeleDao = daoFactory.getModeleDao();
        		Modele modele  = modeleDao.getModeleById(modeleID);
        		        		
        		//TODO IMPLEMENTER EQUIPE ET ROLE MODELE
                Section section = new Section();
                section.setId(id);
                section.setDesignation(designation);
                section.setModele(modele);

                sections.add(section);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;
	}

}
