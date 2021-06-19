package com.dao;

import java.util.ArrayList;

import com.beans.Question;

public interface QuestionDao {

	ArrayList<Question> getQuestions();
	void deleteQuestion(String id);
	void updateQuestion(int id, String designation,String intitule, String reponse, int idSection, int idTypeQuestion);
	Question getQuestionById(String id);
	ArrayList<Question> getQuestionsBySectionId(String idSection);
	ArrayList<Question> getQuestionByAllParam(Question questionParam);
	Question getQuestionByNom(String nom);
	void addQuestion(String designation, String intitule, int idSection, int idTypeQuestion, boolean eleves,
			boolean professeur);
}
