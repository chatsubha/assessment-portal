package com.portal.dao;

import java.util.List;

import com.portal.model.Category;

public interface ChooseAssessmentDao {
	
	public List<Category> getAssessmentCatogory();
	public Category fetchAssessmentCategory(int id);
	public List<Integer> getNotApplicableCategories(int userID);
	public List<Integer> getEmptyQuestionsCategories();

}
