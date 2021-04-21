package com.dao;

import java.util.ArrayList;

import com.beans.TypeQuestion;

public interface TypeQuestionDao {

	ArrayList<TypeQuestion> getTypeQuestions();
	void deleteTypeQuestion(String id);
	TypeQuestion getTypeQuestionById(int typeQuestionID);
}
