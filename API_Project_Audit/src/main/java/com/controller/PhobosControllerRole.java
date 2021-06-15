package com.controller;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DaoFactory;
import com.dao.ProfesseurDao;
import com.dao.RoleDao;

@RestController
public class PhobosControllerRole {

	private static final Logger logger = Logger.getLogger(PhobosControllerRole.class);
	
	@GetMapping(value= "/roleProfesseur")
	@ResponseBody
	public String roleProf (@RequestParam(required= true, value="id") String id) {
		logger.log(Level.INFO, "roleProfesseur");
		
		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		return professeurDao.getRoleProf ( id);
		
	}
	
	@PostMapping(value = "/addRoleProfesseur")
	@ResponseBody
	public void appelPostaddRoleProfesseur(@RequestParam(required = true, value = "idProf") String idProf,
										@RequestParam(required = true, value = "idRole") String idRole,
										@RequestParam(required = false, value = "idRef") String idRef) {

		logger.log(Level.INFO, "appelPostaddRoleProfesseur");
		DaoFactory fact = new DaoFactory();
		RoleDao roledao = fact.getRoleDao();
		roledao.addRoleProfesseur(idProf, idRole, idRef);
	}
	
	@PostMapping(value = "/updateRoleProfesseur")
	@ResponseBody
	public void appelPostupdateRoleProfesseur(@RequestParam(required = true, value = "idProf") String idProf,
										@RequestParam(required = true, value = "idRole") String idRole,
										@RequestParam(required = true, value = "newIdRole") String newIdRole,
										@RequestParam(required = false, value = "idRef") String idRef) {

		logger.log(Level.INFO, "appelPostupdateRoleProfesseur");
		DaoFactory fact = new DaoFactory();
		RoleDao roledao = fact.getRoleDao();
		roledao.updateRoleProfesseur(idProf, idRole, newIdRole, idRef);
	}
	
	@DeleteMapping(value = "/deleteRoleProfesseur")
	@ResponseBody
	public void appelDELETEdeleteRoleProfesseur(@RequestParam(required = true, value = "idProf") String idProf,
										@RequestParam(required = true, value = "idRole") String idRole) {

		logger.log(Level.INFO, "deleteRoleProfesseur");
		DaoFactory fact = new DaoFactory();
		RoleDao roledao = fact.getRoleDao();
		
		if(!idRole.equals(Integer.toString(3)) && !idRole.equals(Integer.toString(2)) && !idRole.equals(Integer.toString(4))){
			roledao.deleteRoleProfesseur(idProf, idRole);
		}
	}

	
	
	
}
