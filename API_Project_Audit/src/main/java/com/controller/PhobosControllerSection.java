package com.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Section;
import com.dao.DaoFactory;
import com.dao.SectionDao;

@RestController
public class PhobosControllerSection {
	private static final Logger logger = Logger.getLogger(PhobosControllerSection.class);


	// METHODE POST 
	
	// INSERT d'une Section 
	@PostMapping(value = "/addSection")
	@ResponseBody
	public void appelPostquestionaddSection(@RequestParam(required = false, value = "Designation") String designation,
												@RequestParam(required = false, value = "id_Modele") int idModele) {
		logger.log(Level.INFO, "appelPost_question_addSection");
		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		sectionDao.addSection(designation, idModele);
	}
	
	@PostMapping(value = "/addSectionBody")
	@ResponseBody
	public void appelPostquestionaddSection(@RequestBody Section section) {
		logger.log(Level.INFO, "appelPost_question_addSection");
		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		sectionDao.addSection(section.getDesignation(), section.getModele().getId());
	}
	
	// UPDATE d'une Section 
	@PostMapping(value = "/updateSection")
	@ResponseBody
	public void appelPostquestionupdateSection(@RequestParam(required = false, value = "id") int id,
												@RequestParam(required = false, value = "Designation") String designation,
												@RequestParam(required = false, value = "id_Modele") int idModele) {
		logger.log(Level.INFO, "appelPost_question_updateSection");
		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		sectionDao.updateSection(id, designation, idModele);
		}
	
	
	// METHODE GET
	
	@GetMapping(value = "/sectionByModele")
	@ResponseBody
	public List<Section> getSectionByIdModele(@RequestParam(required = false, value = "id") String idModele) {
		logger.log(Level.INFO, "getSectionByIdModele");
		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		return sectionDao.getSectionByIdModele(idModele);
		}
	
	@GetMapping(value = "/sectionById")
	@ResponseBody
	public Section getSectionById(@RequestParam(required = false, value = "id") int id) {
		logger.log(Level.INFO, "getSectionById");
		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		return sectionDao.getSectionById(id);
		}
	
	@GetMapping(value = "/section")
	@ResponseBody
	public List<Section> appelGETsection(@RequestParam(required = false, value = "id") String id) {
		logger.log(Level.INFO, "appelGET_section");

		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		return sectionDao.getSections();

	}
	
	@GetMapping(value = "/sectionByNom")
	@ResponseBody
	public List<Section> appelGETsectionByNom(@RequestParam(required = false, value = "designation") String designation) {
		logger.log(Level.INFO, "appelGET_section_By_Nom");

		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		return sectionDao.getSectionByNom(designation);

	}
	
	@GetMapping(value = "/getSection_By_All_Param")
	@ResponseBody
	public List<Section> getSectionByAllParam(@RequestBody Section section) {
		logger.log(Level.INFO, "getSection_By_All_Param");
		
		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		return sectionDao.getSectionByAllParam(section);
	}
	
	// METHODE DELETE

	@DeleteMapping(value = "/section")
	@ResponseBody
	public void appelDELETEsection(@RequestParam(required = false, value = "id") String id) {
		logger.log(Level.INFO, "appelDELETE_section");

		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		sectionDao.deleteSection(id);
	}
}
