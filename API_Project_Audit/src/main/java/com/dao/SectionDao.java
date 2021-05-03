package com.dao;

import java.util.ArrayList;

import com.beans.Section;

public interface SectionDao {

	ArrayList<Section> getSections();
	void deleteSection(String id);
	Section getSectionById(int sectionID);
	void addSection(String Designation, int id_Modele);
	void updateSection(int id, String Designation, int id_Modele);
	ArrayList<Section> getSectionByIdModele(String id_Modele);
	ArrayList<Section> getSectionByNom(String designationPara);
}
