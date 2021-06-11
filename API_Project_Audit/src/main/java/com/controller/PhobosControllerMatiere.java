package com.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Matiere;
import com.dao.DaoFactory;
import com.dao.MatiereDao;

@RestController
public class PhobosControllerMatiere {
	
	private static final Logger logger = Logger.getLogger(PhobosControllerMatiere.class);
	
	@GetMapping(value = "/matiereIdByResponsableId")
	@ResponseBody
	public int getMatiereByIdResp(@RequestParam(required = true, value = "idProf") int idProf) {
		logger.log(Level.INFO, "Appel GET avec matiereID et publiesBoolean");
		
		
		DaoFactory fact = new DaoFactory();
		MatiereDao matiereDao = fact.getMatiereDao();
		return matiereDao.getMatiereIdByResponsableID(idProf);

	}
	
	@GetMapping(value = "/matieres")
	@ResponseBody
	public List<Matiere> appelGETMatieres() {
		logger.log(Level.INFO, "Appel GET de toutes les matieres");
		
		DaoFactory fact = new DaoFactory();
		MatiereDao matiereDao = fact.getMatiereDao();
		return matiereDao.getMatieres();

	}
	
	
	

}
