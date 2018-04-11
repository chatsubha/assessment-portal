package com.portal.form;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AssessementBean {
	
	private int categoryId;	
	private int categoryCutOff;
	private String categoryName;	
	private int totalQuestions;
	private int questionId;
	private String courseTime;	
	private int questionIndex;
	private int questionNumber;
	private String[] userAnswers;
	private String answerType;
	private int userId;	
	private int numberOfAttempts;	
	private int assessementPercentage;
	private int unselectedQuestions;
	private Map<String,List<String>> questOption;	
	private LinkedHashMap<Integer, String> qsMap;
	private LinkedHashMap<Integer, List<String>> asMap;	
	LinkedHashMap<Integer, String> questionList;	
	private Map<Integer,String> correctAnswerMap;
	private HashMap<Integer,String[]> userAnsMap;
	private Map<Integer, String> result;
	private LinkedHashMap<Integer, Integer> typeOfAnsMap;
	private Map<Integer, Integer> orderResult;
	Map<Integer, String> resultPageMap;
	private HashMap<Integer, String[]> userSelectedAnswersMap = new  HashMap<Integer, String[]>();
	private int pendingAttempts;
	private int maxAttempts;

	
	/**
	 * @return the resultPageMap
	 */
	public Map<Integer, String> getResultPageMap() {
		return resultPageMap;
	}
	/**
	 * @param resultPageMap the resultPageMap to set
	 */
	public void setResultPageMap(Map<Integer, String> resultPageMap) {
		this.resultPageMap = resultPageMap;
	}
	/**
	 * @return the orderResult
	 */
	public Map<Integer, Integer> getOrderResult() {
		return orderResult;
	}
	/**
	 * @param orderResult the orderResult to set
	 */
	public void setOrderResult(Map<Integer, Integer> orderResult) {
		this.orderResult = orderResult;
	}
	/**
	 * @return the questionIndex
	 */
	public int getQuestionIndex() {
		return questionIndex;
	}
	/**
	 * @param questionIndex the questionIndex to set
	 */
	public void setQuestionIndex(int questionIndex) {
		this.questionIndex = questionIndex;
	}
	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the categoryCutOff
	 */
	public int getCategoryCutOff() {
		return categoryCutOff;
	}
	/**
	 * @param categoryCutOff the categoryCutOff to set
	 */
	public void setCategoryCutOff(int categoryCutOff) {
		this.categoryCutOff = categoryCutOff;
	}
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the totalQuestions
	 */
	public int getTotalQuestions() {
		return totalQuestions;
	}
	/**
	 * @param totalQuestions the totalQuestions to set
	 */
	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}
	/**
	 * @return the questionId
	 */
	public int getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return the answerType
	 */
	public String getAnswerType() {
		return answerType;
	}
	/**
	 * @param answerType the answerType to set
	 */
	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the percentage
	 */
	public int getAssessementPercentage() {
		return assessementPercentage;
	}
	/**
	 * @param percentage the percentage to set
	 */
	public void setAssessementPercentage(int assessementPercentage) {
		this.assessementPercentage = assessementPercentage;
	}
	/**
	 * @return the questAns
	 */
	public Map<String, List<String>> getQuestOption() {
		return questOption;
	}
	/**
	 * @param questAns the questAns to set
	 */
	public void setQuestOption(Map<String, List<String>> questOption) {
		this.questOption = questOption;
	}
	/**
	 * @return the qsMap
	 */
	public LinkedHashMap<Integer, String> getQsMap() {
		return qsMap;
	}
	/**
	 * @param qsMap the qsMap to set
	 */
	public void setQsMap(LinkedHashMap<Integer, String> qsMap) {
		this.qsMap = qsMap;
	}
	/**
	 * @return the asMap
	 */
	public LinkedHashMap<Integer, List<String>> getAsMap() {
		return asMap;
	}
	/**
	 * @param asMap the asMap to set
	 */
	public void setAsMap(LinkedHashMap<Integer, List<String>> asMap) {
		this.asMap = asMap;
	}
	/**
	 * @return the questionNumber
	 */
	public int getQuestionNumber() {
		return questionNumber;
	}
	/**
	 * @param questionNumber the questionNumber to set
	 */
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	/**
	 * @return the userAnswers
	 */
	public String[] getUserAnswers() {
		return userAnswers;
	}
	/**
	 * @param userAnswers the userAnswers to set
	 */
	public void setUserAnswers(String[] userAnswers) {
		this.userAnswers = userAnswers;
	}
	/**
	 * @return the correctAnswerMap
	 */
	public Map<Integer, String> getCorrectAnswerMap() {
		return correctAnswerMap;
	}
	/**
	 * @param correctAnswerMap the correctAnswerMap to set
	 */
	public void setCorrectAnswerMap(Map<Integer, String> correctAnswerMap) {
		this.correctAnswerMap = correctAnswerMap;
	}
	/**
	 * @return the userAnsMap
	 */
	public HashMap<Integer, String[]> getUserAnsMap() {
		return userAnsMap;
	}
	/**
	 * @param userAnsMap the userAnsMap to set
	 */
	public void setUserAnsMap(HashMap<Integer, String[]> userAnsMap) {
		this.userAnsMap = userAnsMap;
	}
	/**
	 * @return the result
	 */
	public Map<Integer, String> getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(Map<Integer, String> result) {
		this.result = result;
	}
	/**
	 * @return the typeOfAnsMap
	 */
	public LinkedHashMap<Integer, Integer> getTypeOfAnsMap() {
		return typeOfAnsMap;
	}
	/**
	 * @param typeOfAnsMap the typeOfAnsMap to set
	 */
	public void setTypeOfAnsMap(LinkedHashMap<Integer, Integer> typeOfAnsMap) {
		this.typeOfAnsMap = typeOfAnsMap;
	}
	/**
	 * @return the questionList
	 */
	public LinkedHashMap<Integer, String> getQuestionList() {
		return questionList;
	}
	/**
	 * @param questionList the questionList to set
	 */
	public void setQuestionList(LinkedHashMap<Integer, String> questionList) {
		this.questionList = questionList;
	}	
	/**
	 * @return the unselectedQuestions
	 */
	public int getUnselectedQuestions() {
		return unselectedQuestions;
	}
	/**
	 * @param unselectedQuestions the unselectedQuestions to set
	 */
	public void setUnselectedQuestions(int unselectedQuestions) {
		this.unselectedQuestions = unselectedQuestions;
	}
	
	/**
	 * @return the userSelectedAnswersMap
	 */
	public HashMap<Integer, String[]> getUserSelectedAnswersMap() {
		return userSelectedAnswersMap;
	}
	/**
	 * @param userSelectedAnswersMap the userSelectedAnswersMap to set
	 */
	public void setUserSelectedAnswersMap(HashMap<Integer, String[]> userSelectedAnswersMap) {
		this.userSelectedAnswersMap = userSelectedAnswersMap;
	}
	/**
	 * @return the courseTime
	 */
	public String getCourseTime() {
		return courseTime;
	}
	/**
	 * @param courseTime the courseTime to set
	 */
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	/**
	 * @return the numberOfAttempts
	 */
	public int getNumberOfAttempts() {
		return numberOfAttempts;
	}
	/**
	 * @param numberOfAttempts the numberOfAttempts to set
	 */
	public void setNumberOfAttempts(int numberOfAttempts) {
		this.numberOfAttempts = numberOfAttempts;
	}
	public int getPendingAttempts() {
		return pendingAttempts;
	}
	public void setPendingAttempts(int pendingAttempts) {
		this.pendingAttempts = pendingAttempts;
	}
	public int getMaxAttempts() {
		return maxAttempts;
	}
	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}
	
}
