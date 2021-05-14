package com.controller;

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
	
	@RequestMapping(value = "/getJurybyidAudit", method = RequestMethod.GET)
	@ResponseBody
	public Jury appelGET_getJurybyidAudit(@RequestParam(required = true, value = "id_Audit") String id_Audit) {
		System.out.println("Appel GET getJurybyidAudit");

		DaoFactory fact = new DaoFactory();
		JuryDao JuryDao = fact.getJuryDao();
		Jury jury = JuryDao.getJurybyidAudit(id_Audit);
		return jury;

	}

}
