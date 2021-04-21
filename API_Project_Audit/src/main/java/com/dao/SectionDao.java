package com.dao;

import java.util.ArrayList;

import com.beans.Section;

public interface SectionDao {

	ArrayList<Section> getSections();
	void deleteSection(String id);
	Section getSectionById(int sectionID);
}
