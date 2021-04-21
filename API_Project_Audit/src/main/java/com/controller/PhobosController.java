package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Etudiant;
import com.beans.Modele;
import com.beans.Personne;
import com.beans.Professeur;
import com.beans.Question;
import com.beans.Section;
import com.beans.TypeQuestion;
import com.dao.DaoFactory;
import com.dao.EtudiantDao;
import com.dao.ModeleDao;
import com.dao.ProfesseurDao;
import com.dao.QuestionDao;
import com.dao.SectionDao;
import com.dao.TypeQuestionDao;

@RestController
public class PhobosController {

	// GESTION ETUDIANTS
	@RequestMapping(value = "/etudiant", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Etudiant> appelGET_etudiant(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");

		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		ArrayList<Etudiant> allEtudiant = etudiantDao.getEtudiants();
		return allEtudiant;

	}

	@RequestMapping(value = "/etudiant", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_etudiant(@RequestParam(required = false, value = "promo") String promo,
			@RequestParam(required = false, value = "id") String id,
			@RequestParam(required = false, value = "classe") String classe,
			@RequestParam(required = false, value = "nom") String nom,
			@RequestParam(required = false, value = "prenom") String prenom,
			@RequestParam(required = false, value = "email") String email,
			@RequestParam(required = false, value = "tel") String tel) {

		Personne personneToAdd = new Personne(nom, prenom, email, tel);
		Etudiant etudiantToAdd = new Etudiant(promo, classe, personneToAdd);

		DaoFactory fact = new DaoFactory();

		EtudiantDao etudiantDao = fact.getEtudiantDao();
		etudiantDao.addEtudiant(etudiantToAdd);
	}

	// GESTION PROFESSEURS
	@RequestMapping(value = "/professeur", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Professeur> appelGET_professeur(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		ArrayList<Professeur> allProfesseur = professeurDao.getProfesseurs();
		return allProfesseur;

	}

	@RequestMapping(value = "/professeur", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_professeur(@RequestParam(required = false, value = "nom") String nom,
			@RequestParam(required = false, value = "prenom") String prenom,
			@RequestParam(required = false, value = "email") String email,
			@RequestParam(required = false, value = "tel") String tel) {

		Personne personneToAdd = new Personne(nom, prenom, email, tel);
		Professeur professeurToAdd = new Professeur();

		DaoFactory fact = new DaoFactory();

		ProfesseurDao professeurDao = fact.getProfesseurDao();
		
		professeurDao.addProfesseur(professeurDao);
	}
	
	
	

	// GESTION QUESTION
	@RequestMapping(value = "/question", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Question> appelGET_question(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		ArrayList<Question> allQuestion = questionDao.getQuestions();
		return allQuestion;

	}

	// GESTION SECTION
	@RequestMapping(value = "/section", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Section> appelGET_section(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");

		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		ArrayList<Section> allSection = sectionDao.getSections();
		return allSection;

	}

	// GESTION MODELE
	@RequestMapping(value = "/modele", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Modele> appelGET_modele(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");

		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		ArrayList<Modele> allModele = modeleDao.getModeles();
		return allModele;

	}

		//GESTION TYPEQUESTION
		@RequestMapping(value = "/typeQuestion", method = RequestMethod.GET)
		@ResponseBody
		public ArrayList<TypeQuestion> appelGET_typequestion(@RequestParam(required = false, value = "id") String id) {
			System.out.println("Appel GET");
			
			DaoFactory fact = new DaoFactory();
			TypeQuestionDao typeQuestionDao = fact.getTypeQuestionDao();
			ArrayList<TypeQuestion> allTypeQuestion = typeQuestionDao.getTypeQuestions();
			return allTypeQuestion;

		}

}
