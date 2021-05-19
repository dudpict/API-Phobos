package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Lieu;
import com.dao.DaoFactory;
import com.dao.LieuDao;

@RestController
public class PhobosControllerLieu {
	
	
	@RequestMapping (value = "/lieux",method= RequestMethod.GET)
	@ResponseBody
	public ArrayList<Lieu> getLieux(@RequestParam(required= false, value = "lieuId") String lieuId){
		DaoFactory fact = new DaoFactory();
		LieuDao lieuDao = fact.getLieuDao();
		ArrayList<Lieu> lieux = new ArrayList<Lieu>(); 
		if (lieuId == null ) {
			 lieux = lieuDao.getLieux();
		}else {
			lieux . add( lieuDao.getLieuById(lieuId));
		}
		return lieux;
		
		
	}
}