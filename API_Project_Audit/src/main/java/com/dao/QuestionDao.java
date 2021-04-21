package com.dao;

import java.util.ArrayList;

import com.beans.Question;

public interface QuestionDao {

	ArrayList<Question> getQuestions();
	void deleteQuestion(String id);
}
