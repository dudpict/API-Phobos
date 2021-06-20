package testcontroller;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.beans.Equipe;
import com.beans.Jury;
import com.beans.Lieu;
import com.beans.Modele;
import com.beans.Option;
import com.beans.Professeur;
import com.beans.RoleUtilisateur;
import com.beans.Section;
import com.beans.TypeQuestion;
import com.beans.UE;
import com.controller.*;


public class TestController {
		
	@Test
	public void testUE() {		
		UE ue = new UE(0,"Java EE","56");
		Professeur prof = new Professeur(11, null ,null);
		Option opt = new Option(4, 1, null);
		
		ue.setOption(opt);
		ue.setResponsable(prof);
		
		PhobosControllerUE contrUe = new PhobosControllerUE();
		contrUe.addUe(ue);
		
		List<UE> ueL = contrUe.getUES();
		Boolean ueOk = false;
		String idUe = null;
		
		for (UE ueD : ueL) {
			if(ueD.getDesignation().equals("Java EE") && ueD.getDepartement().equals("56") && ueD.getResponsable().getId()==11) {
				ueOk=true;
				idUe=Integer.toString(ueD.getId());
			}
		}
		
		Assert.assertEquals(true,ueOk);	
		Assert.assertNotNull(idUe);
	
		contrUe.deleteUe(idUe);
		List<UE> ueListeDel = contrUe.getUES();
		
		for (UE ueD : ueListeDel) {
			if(ueD.getId()==Integer.parseInt(idUe)) {
				ueOk = false;
			}			
		}
		Assert.assertEquals(true,ueOk);			
	}
	
	@Test
	public void testOption() {
		PhobosControllerOption contrOption = new PhobosControllerOption();
		List<Option> lOption = contrOption.allOption();
		
		Assert.assertTrue(lOption.size()>0);
		Assert.assertNotNull(lOption.get(0).getDesignation());
		Assert.assertNotEquals(lOption.get(0).getIdProfesseur(),0);

	}
	
	@Test
	public void testLieu() {
		PhobosControllerLieu contrLieu = new PhobosControllerLieu();
		List<Lieu> lLieu = contrLieu.getLieux(null);
		
		Assert.assertTrue(lLieu.size()>1);	
		
		lLieu = contrLieu.getLieux("1");		
		
		Assert.assertEquals(1,lLieu.get(0).getId());
		Assert.assertNotNull(lLieu.get(0).getVille());
		Assert.assertNotNull(lLieu.get(0).getEtablissement());
		Assert.assertNotNull(lLieu.get(0).getBatiment());
		Assert.assertNotNull(lLieu.get(0).getNomSalle());
		Assert.assertNotNull(lLieu.get(0).getEtage());
		Assert.assertNotNull(lLieu.get(0).getNumSalle());

	}
	
	@Test
	public void testTypeQuestion() {
		PhobosControllerTypeQuestion contrTypeQuestion = new PhobosControllerTypeQuestion();
		List<TypeQuestion> lTypeQuestion = contrTypeQuestion.appelGETtypequestion();
		
		Assert.assertTrue(lTypeQuestion.size()>1);	
		
		TypeQuestion typeQ = contrTypeQuestion.appelGETtypeQuestionById("1");
		
		Assert.assertEquals(1,typeQ.getId());
		Assert.assertNotNull(typeQ.getDesignation());
	}
	
	@Test
	public void testJury() {
		String name = "JurytestJunit";
		PhobosControllerJury contrJury = new PhobosControllerJury();
		Jury jr = new Jury(0,name);
		
		contrJury.addJury(jr);
		
		Jury jury = contrJury.getJuryByString(name);
		
		int idJ = jury.getId();
		
		Assert.assertEquals(name,jury.getDesignation());
		Assert.assertEquals(idJ,jury.getId());
		
		
		jury = contrJury.getJuryByStringBody(jr);
		
		Assert.assertEquals(name,jury.getDesignation());
		Assert.assertEquals(idJ,jury.getId());
		
		List<Jury> lJury = contrJury.getJurys(null);
		
		Assert.assertTrue(lJury.size()>1);
		
		lJury = contrJury.getJurys(Integer.toString(idJ));
		
		Assert.assertEquals(1,lJury.size());
		Assert.assertEquals(idJ,lJury.get(0).getId());
		Assert.assertEquals(name,lJury.get(0).getDesignation());	
	}
	
	@Test
	public void ttestSection() {
		PhobosControllerSection contrSection = new PhobosControllerSection();
		String name1 = "Section 1 junit";
		String name2 = "Section 2 junit"; 
		String name21 = "Section2junit";
		contrSection.appelPostaddSection(name1, 1);
		
		Section sec = new Section();	
		Modele mdo = new Modele();
		mdo.setId(1);
		
		sec.setId(0);
		sec.setDesignation(name2);
		sec.setModele(mdo);
		
		contrSection.appelPostaddSectionBody(sec);
		
		List<Section> lSection = contrSection.getSectionByAllParam(sec);
		int id1 = 0;
		if (lSection.size()>0) {
			id1++;
		}
		 id1 = 0;
		lSection = contrSection.appelGETsection(null);
		Assert.assertTrue(lSection.size()>1);
		
		int nbrSec =0;
		for(Section sect : lSection) {
			if(sect.getDesignation().equals(name1)){
				nbrSec++;
				id1 = sect.getId();
			}else if(sect.getDesignation().equals(name2)) {
				nbrSec++;
				sec=sect;				
			}
		}
		
		Assert.assertEquals(2,nbrSec);
		Assert.assertEquals(name2,sec.getDesignation());
		
		lSection = contrSection.getSectionByIdModele("1");
		
		Assert.assertTrue(lSection.size()>2);
		
		contrSection.appelPostquestionupdateSection(sec.getId(),name21,1);
		
		sec = contrSection.getSectionById(sec.getId());
		
		Assert.assertEquals(name21,sec.getDesignation());
		
		lSection = contrSection.appelGETsectionByNom(name21);
		
		Assert.assertTrue(lSection.size()>0);
		
		contrSection.appelDELETEsection(Integer.toString(sec.getId()));
		
		contrSection.appelDELETEsection(Integer.toString(id1));
	}
	
	@Test
	public void testRole() {
		PhobosControllerRole contrRole = new PhobosControllerRole();
		contrRole.appelPostaddRoleProfesseur("1","1",null);
		
		PhobosControllerProfesseur contrProf = new PhobosControllerProfesseur();
		List<RoleUtilisateur> lRole = contrProf.roleProf("1");
		
		Boolean roleOk = false;
		for (RoleUtilisateur role : lRole) {
			if(role.getId()==1 || role.getDesignation().equals("1")) {
				roleOk=true;
			}
		}
		
		Assert.assertTrue(roleOk);
		
		contrRole.appelDELETEdeleteRoleProfesseur("1", "1", null);
		
		lRole = contrProf.roleProf("1");
		
		
		for (RoleUtilisateur role : lRole) {
			if(role.getId()==1 || role.getDesignation().equals("1")) {
				roleOk=false;
			}
		}
		Assert.assertTrue(roleOk);
	}
	
	@Test
	public void testEquipe() {
		PhobosControllerEquipe contrEqu = new PhobosControllerEquipe();
		String name = "test Junit";
		Equipe equ = new Equipe();
		equ.setDesignation(name);
		equ.setId(0);
		contrEqu.addEquipe(equ);
		
		Equipe equ1=contrEqu.getEquipeByString(name);
		
		Assert.assertEquals(name,equ1.getDesignation());
		
		contrEqu.getEquipeByStringBody(equ);
		
		Assert.assertEquals(equ.getDesignation(),equ1.getDesignation());
		
		Equipe lEquipe = contrEqu.getEquipeByEtudiantId("33");
		
		Assert.assertNotNull(lEquipe.getDesignation());
	}

}
