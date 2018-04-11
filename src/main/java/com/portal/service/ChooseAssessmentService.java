package com.portal.service;

import java.util.List;

import com.portal.model.Category;

public interface ChooseAssessmentService {

	public List<Category> fetchAssessmentCategory();
	public Category getSelectedCategoryObj(int assessmentId);
	public List<Integer> getNotApplicableCategories(int userID);
	public List<Integer> getEmptyQuestionsCategories();
}
