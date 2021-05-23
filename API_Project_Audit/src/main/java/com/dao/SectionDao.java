package com.dao;

import java.util.ArrayList;

import com.beans.Section;

public interface SectionDao {

	ArrayList<Section> getSections();
	void deleteSection(String id);
	Section getSectionById(int sectionID);
	void addSection(String designation, int idModele);
	void updateSection(int id, String designation, int idModele);
	ArrayList<Section> getSectionByIdModele(String idModele);
	ArrayList<Section> getSectionByNom(String designationPara);
	ArrayList<Section> getSectionByAllParam(Section sectionParam);
}
