package com.portal.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.portal.form.AssessementBean;
import com.portal.model.Category;
import com.portal.model.UserActivity;

/**
 * @author 683177
 *
 */
public interface AssessementService {
	/**
	 * @param assessementBeanObj
	 * @return
	 */
	public LinkedHashMap<Integer, String> initializeQuestions(AssessementBean assessementObj, int attempt);
	/**
	 * @param assessementBeanObj
	 * @return
	 */
	public LinkedHashMap<Integer,List<String>> getAnswer(AssessementBean assessementBeanObj);
	/**
	 * @param assessementBeanObj
	 * @param questionList 
	 * @return
	 */
	public LinkedHashMap<Integer, String> getCorrectOption(AssessementBean assessementBeanObj);
	/**
	 * @param assessementBeanObj
	 * @return
	 */
	public LinkedHashMap<Integer, Integer> getTypeOfAnswer(AssessementBean assessementBeanObj);	
	
	/**
	 * @param userActivity
	 */
	void insertResult(UserActivity userActivity);
	/**
	 * @param assessementBeanObj
	 * @param userId
	 * @return
	 */
	int getAttempts(AssessementBean assessementBeanObj);
	/**
	 * @param assessementObj
	 * @return
	 */
	public String getAssessmentTime(AssessementBean assessementObj);
	/**
	 * @param int
	 * @return
	 */
	public int getMaxAttempt(int id);
	/**
	 * @param int
	 * @return
	 */
	public Category fetchAssessmentCategory(int id);
	/**
	 * @param int
	 * @return
	 */
	public String getQuestionById(int id);
	
	
	
}
