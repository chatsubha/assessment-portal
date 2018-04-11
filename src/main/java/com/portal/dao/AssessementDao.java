package com.portal.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.portal.form.AssessementBean;
import com.portal.model.Category;
import com.portal.model.UserActivity;

public interface AssessementDao {	
	/**
	 * @param assessementObj
	 * @return
	 */
	LinkedHashMap<Integer, String> getQuestionDetails(AssessementBean assessementObj, Map<Integer, Integer> breakUp);
	/**
	 * @param assessementObj
	 * @return
	 */
	LinkedHashMap<Integer, List<String>> getAnswerDetails(AssessementBean assessementObj);
	/**
	 * @param assessementObj
	 * @return
	 */
	LinkedHashMap<Integer, String> getCorrectAnswerDetails(AssessementBean assessementObj);
	/**
	 * @param assessementObj
	 * @return
	 */
	LinkedHashMap<Integer, Integer> getAnswerType(AssessementBean assessementObj);	
	
	/**
	 * @param userId
	 * @return 
	 * @return
	 */
	
	void insertUserActivity(UserActivity userActivity);
	
	/**
	 * @param attempt
	 * @param category
	 * @return
	 */
	Integer getQuestionCount(int attempt, int category);
	
	/**
	 * @param attempt
	 * @param category
	 * @return
	 */
	Map<Integer, Integer> getComplexityBreakUp(int attempt, int category);
	/**
	 * @param assessementBeanObj
	 * @param userID
	 * @return
	 */
	int getNumberOfAttempts(AssessementBean assessementBeanObj);
	/**
	 * @param assessementObj
	 * @return
	 */
	String getAssessmentTimeLimit(AssessementBean assessementObj);
	/**
	 * @param int
	 * @return
	 */
	int getMaxAttempt(int id);
	/**
	 * @param int
	 * @return
	 */
	public Category fetchAssessmentCategory(int id);
	
	public String getQuestionById(int id);
}
