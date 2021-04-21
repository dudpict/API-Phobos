package com.dao;

import java.util.ArrayList;

import com.beans.Question;

public interface QuestionDao {

	ArrayList<Question> getQuestions();
	void deleteQuestion(String id);
	void addQuestion(String id, String Designation, String id_section, String id_typeQuestion);
	void updateQuestion(String id, String Designation, String reponse, String id_section, String id_typeQuestion);
}
