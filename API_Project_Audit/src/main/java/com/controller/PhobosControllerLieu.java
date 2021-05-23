package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Lieu;
import com.dao.DaoFactory;
import com.dao.LieuDao;

@RestController
public class PhobosControllerLieu {
	
	
	@GetMapping (value = "/lieux")
	@ResponseBody
	public List<Lieu> getLieux(@RequestParam(required= false, value = "lieuId") String lieuId){
		DaoFactory fact = new DaoFactory();
		LieuDao lieuDao = fact.getLieuDao();
		List<Lieu> lieux = new ArrayList<>(); 
		if (lieuId == null ) {
			 lieux = lieuDao.getLieux();
		}else {
			lieux . add( lieuDao.getLieuById(lieuId));
		}
		return lieux;
		
		
	}
}