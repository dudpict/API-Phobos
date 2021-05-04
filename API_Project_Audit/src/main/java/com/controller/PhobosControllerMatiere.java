package com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Matiere;
import com.dao.DaoFactory;
import com.dao.MatiereDao;

@RestController
public class PhobosControllerMatiere {
	
	@RequestMapping(value = "/matiereIdByResponsableId", method = RequestMethod.GET)
	@ResponseBody
	public int appelGET_audit(@RequestParam(required = true, value = "idProf") int idProf) {
		System.out.println("Appel GET avec matiereID et publiesBoolean");

		DaoFactory fact = new DaoFactory();
		MatiereDao matiereDao = fact.getMatiereDao();
		int matiereId = matiereDao.getMatiereIdByResponsableID(idProf);
		return matiereId;

	}
	
	@RequestMapping(value = "/matieres", method = RequestMethod.GET)
	@ResponseBody
	public List<Matiere> appelGET_audit() {
		System.out.println("Appel GET avec matiereID et publiesBoolean");

		DaoFactory fact = new DaoFactory();
		MatiereDao matiereDao = fact.getMatiereDao();
		return matiereDao.getMatieres();

	}
	
	
	

}
