package com.portal.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dao.ChooseAssessmentDao;
import com.portal.model.Category;


@Service
public class ChooseAssessmentServiceImpl implements ChooseAssessmentService {
	
	@Autowired
	private ChooseAssessmentDao chooseAssessmentDao;
	
	@Override
	public List<Category> fetchAssessmentCategory() {
		
		List<Category> assessmentCategList = chooseAssessmentDao.getAssessmentCatogory();
		
		return assessmentCategList;
	}

	@Override
	public Category getSelectedCategoryObj(int assessmentId) {
		
		Category selectedCategory = chooseAssessmentDao.fetchAssessmentCategory(assessmentId);
		return selectedCategory;		
	}

	@Override
	public List<Integer> getNotApplicableCategories(int userID) {
		List<Integer> invalidCategoryId = chooseAssessmentDao.getNotApplicableCategories(userID);
		return invalidCategoryId;	
	}

	@Override
	public List<Integer> getEmptyQuestionsCategories() {
		List<Integer> emptyQuestionCategoryLst = chooseAssessmentDao.getEmptyQuestionsCategories();
		return emptyQuestionCategoryLst;	
	}
	

}
