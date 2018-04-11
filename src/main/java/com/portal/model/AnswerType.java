/**
 * 
 */
package com.portal.model;

/**
 * @author Rajkumar Kathiresan.
 *
 */
public class AnswerType {

	/** Answer Id - primary Key. */
	private Integer answerTypeId;
	/** Answer Type Description */
	private String answerTypeDesc;

	/**
	 * @return the answerTypeId
	 */
	public Integer getAnswerTypeId() {
		return answerTypeId;
	}

	/**
	 * @param answerTypeId
	 *            the answerTypeId to set
	 */
	public void setAnswerTypeId(Integer answerTypeId) {
		this.answerTypeId = answerTypeId;
	}

	/**
	 * @return the answerTypeDesc
	 */
	public String getAnswerTypeDesc() {
		return answerTypeDesc;
	}

	/**
	 * @param answerTypeDesc
	 *            the answerTypeDesc to set
	 */
	public void setAnswerTypeDesc(String answerTypeDesc) {
		this.answerTypeDesc = answerTypeDesc;
	}

}
