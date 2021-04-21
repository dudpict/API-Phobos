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
                int modeleID = resultat.getInt("id_Modele");
                
                
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
                int modeleID = resultat.getInt("id_Modele");
                
                
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

}
