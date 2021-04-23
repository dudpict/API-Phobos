package com.dao;

import java.util.ArrayList;

import com.beans.Question;

public interface QuestionDao {

	ArrayList<Question> getQuestions();
	void deleteQuestion(String id);
	void addQuestion(String Designation, int id_section, int id_typeQuestion);
	void updateQuestion(int id, String Designation, String reponse, int id_section, int id_typeQuestion);
	Question getQuestionById(String id);
}
