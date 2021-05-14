package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
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

	// METHODE POST 
	
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
	
	
	// METHODE GET
	
	@RequestMapping(value = "/sectionByModele", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Section> getSectionByIdModele(@RequestParam(required = false, value = "id") String id_Modele) {
		System.out.println("méthode GET, getSectionByIdModele");
		DaoFactory fact = new DaoFactory();
		SectionDao SectionDao = fact.getSectionDao();
		ArrayList<Section> allSection = SectionDao.getSectionByIdModele(id_Modele);
		return allSection;
		}
	
	@RequestMapping(value = "/sectionById", method = RequestMethod.GET)
	@ResponseBody
	public Section getSectionById(@RequestParam(required = false, value = "id") int id) {
		System.out.println("méthode GET, getSectionById");
		DaoFactory fact = new DaoFactory();
		SectionDao SectionDao = fact.getSectionDao();
		Section section = SectionDao.getSectionById(id);
		return section;
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
	
	@RequestMapping(value = "/sectionByNom", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Section> appelGET_section_By_Nom(@RequestParam(required = false, value = "designation") String designation) {
		System.out.println("Appel GET section by Nom ");

		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		ArrayList<Section> allSection = sectionDao.getSectionByNom(designation);
		return allSection;

	}
	
	@RequestMapping(value = "/getSection_By_All_Param", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Section> getSection_By_All_Param(@RequestBody Section section) {
		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		ArrayList<Section> allSection = sectionDao.getSection_By_All_Param(section);
		return allSection;
	}
	
	// METHODE DELETE

	@RequestMapping(value = "/section", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_section(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel Delete");

		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		sectionDao.deleteSection(id);
	}
}
