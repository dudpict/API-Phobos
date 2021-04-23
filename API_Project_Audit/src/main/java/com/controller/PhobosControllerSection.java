package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DaoFactory;
import com.dao.ModeleDao;
import com.dao.SectionDao;

@RestController
public class PhobosControllerSection {

	// INSERT d'une Section 
	@RequestMapping(value = "/addSection", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_question_addSection(@RequestParam(required = false, value = "Designation") String Designation,
												@RequestParam(required = false, value = "id_Modele") int id_Modele) {
		System.out.println("méthode POST, add Section");
		DaoFactory fact = new DaoFactory();
		SectionDao SectionDao = fact.getSectionDao();
		SectionDao.addSection(Designation, id_Modele);
	}
	
	// UPDATE d'une Section 
	@RequestMapping(value = "/updateSection", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_question_updateSection(@RequestParam(required = false, value = "id") int id,
												@RequestParam(required = false, value = "Designation") String Designation,
												@RequestParam(required = false, value = "id_Modele") int id_Modele) {
		System.out.println("méthode POST, update Section");
		DaoFactory fact = new DaoFactory();
		SectionDao SectionDao = fact.getSectionDao();
		SectionDao.updateSection(id, Designation, id_Modele);
		}
	
	// UPDATE d'un Modele
	@RequestMapping(value = "/getSectionByIdModele", method = RequestMethod.GET)
	@ResponseBody
	public void getSectionByIdModele(@RequestParam(required = false, value = "id") String id_Modele) {
		System.out.println("méthode GET, getSectionByIdModele");
		DaoFactory fact = new DaoFactory();
		SectionDao SectionDao = fact.getSectionDao();
		SectionDao.getSectionByIdModele(id_Modele);
		}
}
