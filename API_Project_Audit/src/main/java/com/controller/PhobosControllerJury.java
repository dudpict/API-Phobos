package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Jury;
import com.dao.DaoFactory;
import com.dao.JuryDao;

@RestController
public class PhobosControllerJury {
	
	
	@RequestMapping (value = "/jurys", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Jury> getJurys(@RequestParam(required = false , value = "juryId ") String juryId){
		DaoFactory fact = new DaoFactory();
		JuryDao juryDao = fact.getJuryDao();
		ArrayList<Jury> jurys = new ArrayList<Jury>();
		if (juryId == null ) {
			jurys = juryDao.getJurys();
		}else {
			jurys.add(juryDao.getJuryById(juryId));
		}
		
		return jurys;
		
		
	}

}