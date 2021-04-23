package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.beans.Audit;
import com.beans.Etudiant;

public class AuditDaoImpl implements AuditDao {

	private DaoFactory daoFactory;

    AuditDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
	@Override
	public ArrayList<Audit> getAudits() {
		ArrayList<Audit> audits = new ArrayList<Audit>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT * FROM Audit;");

            while (resultat.next()) {
            	String id = resultat.getString("id");
                String designation = resultat.getString("designation");
                String etat = resultat.getString("etat");
                String dateDebut = resultat.getString("dateDebut");
                String dateFin = resultat.getString("dateFin");
                String dateLimite = resultat.getString("dateLimite");
                String dateModif = resultat.getString("dateModif");
     
                Audit audit = new Audit();
                audit.setId(Integer.valueOf(id));
                audit.setDesignation(designation);
                audit.setEtat(etat);
//                audit.setDateDebut(dateDebut);
//                audit.setDateFin(dateFin);
//                audit.setDateLimite(dateLimite);
//                audit.setDateModif(dateModif);

                audits.add(audit);
                connexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return audits;
	}

	@Override
	public Audit getAuditById(String id) {
		Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        Audit audit=new Audit();
        
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id,nom,prenom,email,tel FROM personne WHERE id="+id+";");

            while (resultat.next()) {
            	String id2 = resultat.getString("id");
                String designation = resultat.getString("designation");
                String etat = resultat.getString("etat");
                String dateDebut = resultat.getString("dateDebut");
                String dateFin = resultat.getString("dateFin");
                String dateLimite = resultat.getString("dateLimite");
                String dateModif = resultat.getString("dateModif");
     
                audit.setId(Integer.valueOf(id));
                audit.setDesignation(designation);
                audit.setEtat(etat);
//                audit.setDateDebut(dateDebut);
//                audit.setDateFin(dateFin);
//                audit.setDateLimite(dateLimite);
//                audit.setDateModif(dateModif);
//                
                connexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return audit;
	}

	@Override
	public void updateAudit(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAudit(Etudiant etudiantToAdd) {
		// TODO A dev
		
	}

	@Override
	public void deleteAudit(String id) {
		// TODO A dev
		
	}

}
