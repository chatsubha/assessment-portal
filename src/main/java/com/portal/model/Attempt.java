/**
 * 
 */
package com.portal.model;

/**
 * @author Rajkumar Kathiresan
 *
 */
public class Attempt {

	/** Attempt Id - primary Key of Attempt table. */
	private Integer attemptId;

	/** Category Id - foreign Key of Category table. */
	private Integer categoryId;

	/** Number of attempt for the single Category. */
	private Integer noOfAttempt;

	/** Number of question for the single attempt. */
	private Integer noOfQuestion;

	/**
	 * @return the attemptId
	 */
	public Integer getAttemptId() {
		return attemptId;
	}

	/**
	 * @param attemptId
	 *            the attemptId to set
	 */
	public void setAttemptId(Integer attemptId) {
		this.attemptId = attemptId;
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

	/**
	 * @return the noOfAttempt
	 */
	public Integer getNoOfAttempt() {
		return noOfAttempt;
	}

	/**
	 * @param noOfAttempt
	 *            the noOfAttempt to set
	 */
	public void setNoOfAttempt(Integer noOfAttempt) {
		this.noOfAttempt = noOfAttempt;
	}

	/**
	 * @return the noOfQuestion
	 */
	public Integer getNoOfQuestion() {
		return noOfQuestion;
	}

	/**
	 * @param noOfQuestion
	 *            the noOfQuestion to set
	 */
	public void setNoOfQuestion(Integer noOfQuestion) {
		this.noOfQuestion = noOfQuestion;
	}

}
