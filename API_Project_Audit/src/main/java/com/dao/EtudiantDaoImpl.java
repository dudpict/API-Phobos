package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.beans.Equipe;
import com.beans.Etudiant;
import com.beans.Personne;

public class EtudiantDaoImpl implements EtudiantDao {

	private static final Logger logger = Logger.getLogger(EtudiantDaoImpl.class);
	private DaoFactory daoFactory;
	private String [] sqlParamEtudiant = {"id","promo","classe","id_Personne","id_Equipe ","id_roleUtilisateur"};

	EtudiantDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public ArrayList<Etudiant> getEtudiants() {
    	ArrayList<Etudiant> etudiants = new ArrayList<>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Etudiant;");
            connexion.close();

            while (resultat.next()) {
            	String id = resultat.getString(sqlParamEtudiant[0]);
                String classe = resultat.getString(sqlParamEtudiant[2]);
                String promo = resultat.getString(sqlParamEtudiant[1]);
                int personneID = resultat.getInt(sqlParamEtudiant[3]);

                
                //Récupére l'instance de Prsonne via l'id
        		PersonneDao personneDao = daoFactory.getPersonneDao();
        		Personne personne  = personneDao.getPersonneById(personneID);
        		        		
                Etudiant etudiant = new Etudiant();
                etudiant.setId(Integer.valueOf(id));
                etudiant.setPromo(promo);
                etudiant.setClasse(classe);
                etudiant.setPersonne(personne);

                etudiants.add(etudiant);
                
            }
        }catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getEtudiantById", e);

		}finally {
			daoFactory.close(connexion,statement,null,resultat);			
		}
        return etudiants;
    }
    
    @Override
	public Etudiant getEtudiantById(String id) {
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        PreparedStatement preparedStmt = null;
        Etudiant etudiant = new Etudiant();

        try { 
        	connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Etudiant WHERE id=?;");
			preparedStmt.setString(1,id );
			resultat=preparedStmt.executeQuery();
            while (resultat.next()) {
            	String id2 = resultat.getString(sqlParamEtudiant[0]);
                String classe = resultat.getString(sqlParamEtudiant[2]);
                String promo = resultat.getString(sqlParamEtudiant[1]);
                int personneID = resultat.getInt(sqlParamEtudiant[3]);
                
                //Récupére l'instance de Prsonne via l'id
        		PersonneDao personneDao = daoFactory.getPersonneDao();
        		Personne personne  = personneDao.getPersonneById(personneID);
        		        		
                etudiant.setId(Integer.valueOf(id2));
                etudiant.setPromo(promo);
                etudiant.setClasse(classe);
                etudiant.setPersonne(personne);
                
            }
        } catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getEtudiantById", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
        return etudiant;
	}

	@Override
	public void deleteEtudiant(String id) {
		Connection connexion = null;
		PreparedStatement preparedStmt = null;
		try {
			connexion = daoFactory.getConnection();
			String requete = "DELETE * FROM Etudiant WHERE id=?";
			preparedStmt = connexion.prepareStatement(requete);
			preparedStmt.setString(1, id);
			preparedStmt.execute();
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem deleteEtudiant", e);
		}finally {
			daoFactory.close(connexion,null,preparedStmt,null);			
		}
		
	}

	@Override
	public void addEtudiant(Etudiant etudiant) {
			
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		
		DaoFactory fact = new DaoFactory();
		PersonneDao personneDao = fact.getPersonneDao();
		
		Personne personne1 = personneDao.getPersonneById(etudiant.getPersonne().getId());
		
		if (personne1.getId()==0) {
			personneDao.addPersonne(etudiant.getPersonne());
			personne1 = personneDao.getPersonneByMail(etudiant.getPersonne().getEmail());
		}
		
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("INSERT INTO Etudiant(promo, classe, id_Personne) VALUES (?,?,?);");
			preparedStmt.setString(1, etudiant.getPromo());
			preparedStmt.setString(2, etudiant.getClasse());
			preparedStmt.setInt(3, personne1.getId());
			resultat = preparedStmt.executeQuery();
			
			
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addEtudiant", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		
	}
	
	@Override
	public void addEtudiantToEquipeId(String idEquipe, String id) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
	
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("UPDATE Etudiant e SET e.id_Equipe = ? WHERE e.id = ?;");
			preparedStmt.setString(1,idEquipe );
			preparedStmt.setString(2,id);
			preparedStmt.executeQuery();
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem addEtudiantToEquipeId", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,null);			
		}
	}
	
	@Override
	public void removeEtudiantToEquipeId(String id) {
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
	
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("UPDATE Etudiant e SET e.id_Equipe = null WHERE e.id = ?;");
			preparedStmt.setString(1,id);
			preparedStmt.executeQuery();
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem removeEtudiantToEquipeId", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,null);			
		}
	}
		
	@Override
	public ArrayList<Etudiant> etudiantByStr(String search){
		ArrayList<Etudiant> etudiants = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Etudiant e INNER JOIN Personne p ON p.id = e.id_Personne WHERE LOWER(p.nom) LIKE ? OR  LOWER(p.prenom) LIKE ?;");
			preparedStmt.setString(1, "%"+search+"%");
			preparedStmt.setString(2, "%"+search+"%");
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				
				String id = resultat.getString(sqlParamEtudiant[0]);
                String classe = resultat.getString(sqlParamEtudiant[2]);
                String promo = resultat.getString(sqlParamEtudiant[1]);
                int personneID = resultat.getInt(sqlParamEtudiant[3]);

        		PersonneDao personneDao = daoFactory.getPersonneDao();
        		Personne personne  = personneDao.getPersonneById(personneID);
				
				Etudiant etudiant = new Etudiant();
                etudiant.setId(Integer.valueOf(id));
                etudiant.setPromo(promo);
                etudiant.setClasse(classe);
                etudiant.setPersonne(personne);
	            etudiants.add(etudiant);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem etudiantByStr", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		
		return etudiants;
	}	
	
	@Override
	public ArrayList<Etudiant> etudiantByAudit(String idAudit){
		ArrayList<Etudiant> etudiants = new ArrayList<>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Etudiant WHERE id_Equipe IN (SELECT id FROM Equipe eq WHERE eq.id = (SELECT id_Equipe from Audit WHERE id = ?));");
			preparedStmt.setString(1, idAudit);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				
				String id = resultat.getString(sqlParamEtudiant[0]);
                String classe = resultat.getString(sqlParamEtudiant[2]);
                String promo = resultat.getString(sqlParamEtudiant[1]);
                int personneID = resultat.getInt(sqlParamEtudiant[3]);

        		PersonneDao personneDao = daoFactory.getPersonneDao();
        		Personne personne  = personneDao.getPersonneById(personneID);
				
				Etudiant etudiant = new Etudiant();
                etudiant.setId(Integer.valueOf(id));
                etudiant.setPromo(promo);
                etudiant.setClasse(classe);
                etudiant.setPersonne(personne);
	            etudiants.add(etudiant);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem etudiantByAudit", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		
		return etudiants;
	}
	
	@Override
	public Etudiant getEtudiantByPersonneID(String idPersonne){
		Etudiant etudiant = null;
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT * FROM Etudiant WHERE id_Personne = ?;");
			preparedStmt.setString(1, idPersonne);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				int id = resultat.getInt(sqlParamEtudiant[0]);
                String classe = resultat.getString(sqlParamEtudiant[2]);
                String promo = resultat.getString(sqlParamEtudiant[1]);
                int personneID = resultat.getInt(sqlParamEtudiant[3]);

        		PersonneDao personneDao = daoFactory.getPersonneDao();
        		Personne personne  = personneDao.getPersonneById(personneID);
        		etudiant = new Etudiant();
                etudiant.setId(id);
                etudiant.setPromo(promo);
                etudiant.setClasse(classe);
                etudiant.setPersonne(personne);
	           
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		
		return etudiant;
	}
	
	
	@Override
	public Equipe isPersonneIsInTeam(String idPersonne){
		String idTeam ="-1";
		
		EquipeDao equipeDao = daoFactory.getEquipeDao();
		Equipe equipe = new Equipe();
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT id_Equipe FROM Etudiant WHERE id_Personne = ?;");
			preparedStmt.setString(1, idPersonne);
			resultat = preparedStmt.executeQuery();
			
			while (resultat.next()) {
				idTeam = resultat.getString("id_Equipe");
				equipe = equipeDao.getEquipeById(idTeam);
			}
		} catch (SQLException e) {
			logger.log(Level.INFO, "sql problem", e);

		}finally {
			daoFactory.close(connexion,statement,preparedStmt,resultat);			
		}
		if(idTeam==null) {
			equipe=null;
		}
				
		return equipe;
		
	}
	public boolean getRoleEtudiant(String id) {
		
		boolean res= false;
		Connection connexion = null;
		Statement statement = null;
		PreparedStatement preparedStmt = null;
		ResultSet resultat = null;
		
		try {
			connexion = daoFactory.getConnection();
			preparedStmt=connexion.prepareStatement("SELECT * from Etudiant WHERE id_Personne = ? ");
			preparedStmt.setInt(1, Integer.parseInt(id));
			resultat = preparedStmt.executeQuery();
			while (resultat.next()) {
				res = true;
			}
			
		}catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getRoleEtudiant", e);
		}finally {
			daoFactory.close(connexion, statement, preparedStmt,null );
		}
		return res;
		
	}
	
	public int getIdEtudiant(String idPers) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		PreparedStatement preparedStmt = null;
		int id= 0;
		try {
			
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			preparedStmt = connexion.prepareStatement("SELECT id FROM Etudiant WHERE id_Personne =?;");
			preparedStmt.setString(1,idPers );
			resultat=preparedStmt.executeQuery();

			while(resultat.next()) {
				id = resultat.getInt("id");
			}
		}
		catch (SQLException e) {
			logger.log(Level.INFO, "sql problem getIdEtudiant", e);
		}finally {
			daoFactory.close(connexion, statement, preparedStmt,resultat );
		}
		return id;
	}
	
}
