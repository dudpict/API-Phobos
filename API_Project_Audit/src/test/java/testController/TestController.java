package testController;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.beans.Option;
import com.beans.Professeur;
import com.beans.UE;
import com.controller.*;


public class TestController {
		
	@Test
	public void testAudit() {
		
		
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

}
