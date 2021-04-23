package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Section;
import com.dao.DaoFactory;
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
	
	@RequestMapping(value = "/sectionByModele", method = RequestMethod.GET)
	@ResponseBody
	public void getSectionByIdModele(@RequestParam(required = false, value = "id") String id_Modele) {
		System.out.println("méthode GET, getSectionByIdModele");
		DaoFactory fact = new DaoFactory();
		SectionDao SectionDao = fact.getSectionDao();
		SectionDao.getSectionByIdModele(id_Modele);
		}
	
	@RequestMapping(value = "/section", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Section> appelGET_section(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");

		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		ArrayList<Section> allSection = sectionDao.getSections();
		return allSection;

	}

	@RequestMapping(value = "/section", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_section(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel Delete");

		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		sectionDao.deleteSection(id);
	}
}
