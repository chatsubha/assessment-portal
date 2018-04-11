/**
 * 
 */
package com.portal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rajkumar Kathiresan.
 *
 */
public class Question {

	/** Question Id - primary Key. */
	private Integer questionId;
	/** Question Description */
	private String questionDesc;
	/** Answer Type - foreign Key of Answer Type Table */
	private Integer answerType;
	/** Category Id - foreign Key of Category table. */
	private Integer categoryId;
	/** Complexity Type - foreign Key of complexity table. */
	private Integer complexity;

	/**
	 * @return the questionId
	 */
	public Integer getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the questionDesc
	 */
	public String getQuestionDesc() {
		return questionDesc;
	}

	/**
	 * @param questionDesc
	 *            the questionDesc to set
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	/**
	 * @return the answerType
	 */
	public Integer getAnswerType() {
		return answerType;
	}

	/**
	 * @param answerType
	 *            the answerType to set
	 */
	public void setAnswerType(Integer answerType) {
		this.answerType = answerType;
	}

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/** Category questionOptions - questions options. */
	private String[] questionOptions;

	/**
	 * @return the questionOptions
	 */
	public String[] getQuestionOptions() {
		return questionOptions;
	}

	/**
	 * @param questionOptions
	 *            the questionOptions to set
	 */
	public void setQuestionOptions(String[] questionOptions) {
		this.questionOptions = questionOptions;
	}

	/**
	 * @return the complexity
	 */
	public Integer getComplexity() {
		return complexity;
	}

	/**
	 * @param complexity
	 *            the complexity to set
	 */
	public void setComplexity(Integer complexity) {
		this.complexity = complexity;
	}

	/**
	 * Default constructor.
	 */
	public Question() {
	}

	/**
	 * parameter constructor.
	 */
	public Question(Integer categoryId, Integer questionId, String questionDesc, Integer answerType) {

		this.categoryId = categoryId;
		this.questionId = questionId;
		this.questionDesc = questionDesc;
		this.answerType = answerType;

	}

	public static List<Question> questions = new ArrayList<Question>();

	static {
		questions.add(new Question(100, 001, "What is Java?", 1));
		questions.add(new Question(101, 002, "What is Spring MVC", 2));
		questions.add(new Question(102, 003, "What is Hibernate ", 1));
		questions.add(new Question(103, 004, "Is below statement is correct related to JSP", 1));
	}
}
