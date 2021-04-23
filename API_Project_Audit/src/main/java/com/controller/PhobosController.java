package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Audit;
import com.beans.Etudiant;
import com.beans.Modele;
import com.beans.Personne;
import com.beans.Professeur;
import com.beans.Question;
import com.beans.Section;
import com.beans.TypeQuestion;
import com.dao.AuditDao;
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

	@RequestMapping(value = "/etudiantById", method = RequestMethod.GET)
	@ResponseBody
	public Etudiant appelGET_etudiantById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		Etudiant etudiant = etudiantDao.getEtudiantById(id);
		return etudiant;
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

	@RequestMapping(value = "/etudiant", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_etudiant(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel DELETE");

		DaoFactory fact = new DaoFactory();
		EtudiantDao etudiantDao = fact.getEtudiantDao();
		etudiantDao.deleteEtudiant(id);
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

	@RequestMapping(value = "/professeurById", method = RequestMethod.GET)
	@ResponseBody
	public Professeur appelGET_professeurById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		Professeur professeur = professeurDao.getProfesseurById(id);
		return professeur;
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

	@RequestMapping(value = "/professeur", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_professeur(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel DELETE");

		DaoFactory fact = new DaoFactory();
		ProfesseurDao professeurDao = fact.getProfesseurDao();
		professeurDao.deleteProfesseur(id);
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

	@RequestMapping(value = "/question", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_question(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel Delete");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		questionDao.deleteQuestion(id);
	}

	@RequestMapping(value = "/modeleById", method = RequestMethod.GET)
	@ResponseBody
	public Question appelGET_questionById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		DaoFactory fact = new DaoFactory();
		QuestionDao questionDao = fact.getQuestionDao();
		Question question = questionDao.getQuestionById(id);
		return question;
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

	@RequestMapping(value = "/section", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_section(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel Delete");

		DaoFactory fact = new DaoFactory();
		SectionDao sectionDao = fact.getSectionDao();
		sectionDao.deleteSection(id);
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
/*
	@RequestMapping(value = "/modeleById", method = RequestMethod.GET)
	@ResponseBody
	public Modele appelGET_modeleById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		Modele modele = modeleDao.getModeleById(id);
		return modele;
	}
*/
	@RequestMapping(value = "/modele", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_modele(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel Delete");

		DaoFactory fact = new DaoFactory();
		ModeleDao modeleDao = fact.getModeleDao();
		modeleDao.deleteModele(id);
	}

	// GESTION TYPEQUESTION
	@RequestMapping(value = "/typeQuestion", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<TypeQuestion> appelGET_typequestion(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel GET");

		DaoFactory fact = new DaoFactory();
		TypeQuestionDao typeQuestionDao = fact.getTypeQuestionDao();
		ArrayList<TypeQuestion> allTypeQuestion = typeQuestionDao.getTypeQuestions();
		return allTypeQuestion;
	}

	@RequestMapping(value = "/typeQuestionById", method = RequestMethod.GET)
	@ResponseBody
	public TypeQuestion appelGET_typeQuestionById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		DaoFactory fact = new DaoFactory();
		TypeQuestionDao typeQuestionDao = fact.getTypeQuestionDao();
		TypeQuestion typeQuestion = typeQuestionDao.getTypeQuestionById(id);
		return typeQuestion;
	}

	// GESTION AUDITS
	@RequestMapping(value = "/audits", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Audit> appelGET_audit(@RequestParam(required = true, value = "matiereId") int matiereId,
			@RequestParam(required = true, value = "publiesBoolean") boolean publiesBoolean) {
		System.out.println("Appel GET avec matiereID et publiesBoolean");

		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		ArrayList<Audit> allAudit = auditDao.getAudits(matiereId, publiesBoolean);
		return allAudit;

	}

	@RequestMapping(value = "/auditById", method = RequestMethod.GET)
	@ResponseBody
	public Audit appelGET_auditById(@RequestParam(required = true, value = "id") String id) {
		System.out.println("Appel GET by ID");

		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		Audit audit = auditDao.getAuditById(id);
		return audit;
	}

	@RequestMapping(value = "/addAudit", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_audit(@RequestParam(required = false, value = "promo") String promo,
			@RequestParam(required = false, value = "id") String id,
			@RequestParam(required = false, value = "classe") String classe,
			@RequestParam(required = false, value = "nom") String nom,
			@RequestParam(required = false, value = "prenom") String prenom,
			@RequestParam(required = false, value = "email") String email,
			@RequestParam(required = false, value = "tel") String tel) {

		Personne personneToAdd = new Personne(nom, prenom, email, tel);
		Etudiant etudiantToAdd = new Etudiant(promo, classe, personneToAdd);

		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		auditDao.addAudit(etudiantToAdd);
	}

	@RequestMapping(value = "/deleteAudit", method = RequestMethod.DELETE)
	@ResponseBody
	public void appelDELETE_audit(@RequestParam(required = false, value = "id") String id) {
		System.out.println("Appel DELETE");

		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		auditDao.deleteAudit(id);
	}
	
	@RequestMapping(value = "/updateAudit", method = RequestMethod.POST)
	@ResponseBody
	public void appelPost_audit_updateAudit(@RequestParam(required = false, value = "id") String id,
												@RequestParam(required = false, value = "Designation") String Designation) {
		System.out.println("méthode POST, update Modele");
		DaoFactory fact = new DaoFactory();
		AuditDao auditDao = fact.getAuditDao();
		auditDao.updateAudit(id);
		}

}
