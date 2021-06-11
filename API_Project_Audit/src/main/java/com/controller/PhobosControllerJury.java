package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Jury;
import com.dao.DaoFactory;
import com.dao.JuryDao;

@RestController
public class PhobosControllerJury {
	
	
	@GetMapping (value = "/jurys")
	@ResponseBody
	public List<Jury> getJurys(@RequestParam(required = false , value = "juryId ") String juryId){
		DaoFactory fact = new DaoFactory();
		JuryDao juryDao = fact.getJuryDao();
		ArrayList<Jury> jurys = new ArrayList<>();
		if (juryId == null ) {
			jurys = juryDao.getJurys();
		}else {
			jurys.add(juryDao.getJuryById(juryId));
		}
		
		return jurys;
		
	}
	
	@GetMapping (value = "/juryByStr")
	@ResponseBody
	public Jury getJuryByString(@RequestParam(required = true , value = "designation") String designation){
		DaoFactory fact = new DaoFactory();
		JuryDao juryDao = fact.getJuryDao();
		
		return juryDao.getJuryByString(designation);
		
	}
	
	@PostMapping (value = "/juryByStrBody")
	@ResponseBody
	public Jury getJuryByStringBody(@RequestBody Jury jury){
		DaoFactory fact = new DaoFactory();
		JuryDao juryDao = fact.getJuryDao();
		
		return juryDao.getJuryByString(jury.getDesignation());
		
	}
	
	@PostMapping (value = "/addJury")
	@ResponseBody
	public void addJury(@RequestBody Jury jury){
		DaoFactory fact = new DaoFactory();
		JuryDao juryDao = fact.getJuryDao();
		juryDao.addJury(jury.getDesignation());
					
	}

}
