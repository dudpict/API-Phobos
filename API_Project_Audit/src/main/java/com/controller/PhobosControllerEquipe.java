package com.controller;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Equipe;
import com.dao.DaoFactory;
import com.dao.EquipeDao;

@RestController
public class PhobosControllerEquipe {
	private static final Logger log = Logger.getLogger(PhobosControllerEquipe.class);

	@GetMapping(value = "/equipeByStr")
	@ResponseBody
	public Equipe getEquipeByString(@RequestParam(required = false, value = "designation") String designation) {
		log.log(Level.INFO, "appel getEquipeByString");
		
		DaoFactory fact = new DaoFactory();
		EquipeDao equipeDao = fact.getEquipeDao();
		return equipeDao.getEquipeByString(designation);
	}
	
	@PostMapping(value = "/addEquipe")
	@ResponseBody
	public void addEquipe(@RequestBody Equipe equipe) {
		log.log(Level.INFO, "appel post addEquipe");
		
		DaoFactory fact = new DaoFactory();
		EquipeDao equipeDao = fact.getEquipeDao();
		equipeDao.addEquipe(equipe.getDesignation());
	}
	
}
