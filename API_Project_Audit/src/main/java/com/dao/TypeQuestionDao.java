package com.dao;

import java.util.List;

import com.beans.TypeQuestion;

public interface TypeQuestionDao {

	List<TypeQuestion> getTypeQuestions();
	void deleteTypeQuestion(String id);
	TypeQuestion getTypeQuestionById(int typeQuestionID);
	TypeQuestion getTypeQuestionById(String id);
}
