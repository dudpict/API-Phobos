package com.dao;

import java.util.ArrayList;

import com.beans.Section;

public interface SectionDao {

	ArrayList<Section> getSections();
	void deleteSection(String id);
	Section getSectionById(int sectionID);
	void addSection(String id, String Designation, String id_Modele);
	void updateSection(String id, String Designation, String id_Modele);
}
