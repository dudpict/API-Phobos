package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.Personne;

public class PersonneDaoImpl implements PersonneDao {

	private DaoFactory daoFactory;

    PersonneDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    @Override
    public ArrayList<Personne> getPersonnes(String role) {
        List<Personne> personnes = new ArrayList<Personne>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id,nom,prenom,email,tel FROM personne WHERE role LIKE '"+role+"%';");

            while (resultat.next()) {
            	String id = resultat.getString("id");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String email = resultat.getString("email");
                String tel = resultat.getString("tel");
     
                Personne personne = new Personne();
                personne.setId(Integer.valueOf(id));
                personne.setNom(nom);
                personne.setPrenom(prenom);
                personne.setEmail(email);
                personne.setTelephone(tel);

                personnes.add(personne);
                connexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<Personne>) personnes;
    }
    
    @Override
    public Personne getPersonneById(int id) {
    	Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        Personne personne=new Personne();
        
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id,nom,prenom,email,tel FROM personne WHERE id="+id+";");

            while (resultat.next()) {
            	String id2=resultat.getString("id");
            	String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String email = resultat.getString("email");
                String tel = resultat.getString("tel");
     
                personne.setId(Integer.valueOf(id2));
                personne.setNom(nom);
                personne.setPrenom(prenom);
                personne.setEmail(email);
                personne.setTelephone(tel);
                
                connexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personne;
    }

	@Override
	public void ajouter(Personne personne) {
		// TODO Auto-generated method stub
		
	}
    
}
