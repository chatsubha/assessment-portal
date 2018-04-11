package com.portal.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dao.AssessementDao;
import com.portal.form.AssessementBean;
import com.portal.model.Category;
import com.portal.model.UserActivity;
import com.portal.service.AssessementService;

@Service
public class AssessementServiceImpl implements AssessementService{

	
	@Autowired
	private AssessementDao assessementDao;	 
	
	/* (non-Javadoc)
	 * @see com.portal.service.AssessementService#initializeQuestions(com.portal.bean.AssessementBean)
	 */
	/*@Override
	public LinkedHashMap<Integer, String> initializeQuestions(AssessementBean assessementObj)
	{
		LinkedHashMap<Integer, String> questions=assessementDao.getQuestionDetails(assessementObj);
		return questions;
	}*/

	/* (non-Javadoc)
	 * @see com.portal.service.AssessementService#initializeQuestions(com.portal.bean.AssessementBean)
	 */
	@Override
	public LinkedHashMap<Integer, String> initializeQuestions(AssessementBean assessementObj, int attempt)
	{
		int questionCount = assessementDao.getQuestionCount(attempt , assessementObj.getCategoryId());
		Map<Integer, Integer> breakUp = assessementDao.getComplexityBreakUp(attempt, assessementObj.getCategoryId());
		
		breakUp.forEach((complexity, percentage) -> {
			int temp = (int)(((float)percentage/100)*questionCount);
			breakUp.put(complexity, temp);
		});
		LinkedHashMap<Integer, String> questions=assessementDao.getQuestionDetails(assessementObj, breakUp);
		System.out.println(questions);
		return questions;
	}

	/* (non-Javadoc)
	 * @see com.portal.service.AssessementService#getAnswer(com.portal.bean.AssessementBean)
	 */
	@Override
	public LinkedHashMap<Integer, List<String>> getAnswer(AssessementBean assessementBeanObj)
	{
		LinkedHashMap<Integer, List<String>> answer=assessementDao.getAnswerDetails(assessementBeanObj);
		return answer;
	}


	/* (non-Javadoc)
	 * @see com.portal.service.AssessementService#getCorrectOption(com.portal.bean.AssessementBean)
	 */
	@Override
	public LinkedHashMap<Integer, String> getCorrectOption(AssessementBean assessementBeanObj) {
		// TODO Auto-generated method stub		
		LinkedHashMap<Integer,String> option=assessementDao.getCorrectAnswerDetails(assessementBeanObj);
		return option;
	}


	/* (non-Javadoc)
	 * @see com.portal.service.AssessementService#getTypeOfAnswer(com.portal.bean.AssessementBean)
	 */
	@Override
	public LinkedHashMap<Integer, Integer> getTypeOfAnswer(AssessementBean assessementBeanObj) {
		LinkedHashMap<Integer, Integer> answerType = assessementDao.getAnswerType(assessementBeanObj);
		return answerType;
	}


	/* (non-Javadoc)
	 * @see com.portal.service.AssessementService#insertResult(com.portal.service.UserActivity)
	 */
	@Override
	public void insertResult(UserActivity userActivity) {
		// TODO Auto-generated method stub
		assessementDao.insertUserActivity(userActivity);
	}
	
	
	@Override
	public int getAttempts(AssessementBean assessementBeanObj) {
		return assessementDao.getNumberOfAttempts(assessementBeanObj);
	}

	/* (non-Javadoc)
	 * @see com.portal.service.AssessementService#getAssessmentTime(com.portal.form.AssessementBean)
	 */
	@Override
	public String getAssessmentTime(AssessementBean assessementObj) {
		// TODO Auto-generated method stub
		return assessementDao.getAssessmentTimeLimit(assessementObj);
		
	}

	@Override
	public int getMaxAttempt(int id) {
		return assessementDao.getMaxAttempt(id);
	}

	@Override
	public Category fetchAssessmentCategory(int id) {
		return assessementDao.fetchAssessmentCategory(id);
	}

	@Override
	public String getQuestionById(int id) {
		// TODO Auto-generated method stub
		return assessementDao.getQuestionById(id);
	}
	
	

}
