package com.dao;

import com.beans.RoleUtilisateur;

public interface RoleDao {

	void addRoleProfesseur(String idProf, String idRole, String idRef);

	void deleteRoleProfesseur(String idProf, String idRole, String idRef);

	void updateRoleProfesseur(String idProf, String idRole, String newIdRole, String idRef);

	void verifyRole(String idProf, String idRole, String idRef);

	RoleUtilisateur getRoleByIdAndIdProf(String idProf, String idRole);
	

	
}
