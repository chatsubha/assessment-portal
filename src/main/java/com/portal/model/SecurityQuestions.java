package com.portal.model;

public class SecurityQuestions {

	private Integer securityQuestNumber;
	private String securityQuestions;
	/**
	 * @return the securityQuestNumber
	 */
	public Integer getSecurityQuestNumber() {
		return securityQuestNumber;
	}
	/**
	 * @param securityQuestNumber the securityQuestNumber to set
	 */
	public void setSecurityQuestNumber(Integer securityQuestNumber) {
		this.securityQuestNumber = securityQuestNumber;
	}
	/**
	 * @return the securityQuestions
	 */
	public String getSecurityQuestions() {
		return securityQuestions;
	}
	/**
	 * @param securityQuestions the securityQuestions to set
	 */
	public void setSecurityQuestions(String securityQuestions) {
		this.securityQuestions = securityQuestions;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SecurityQuestions [securityQuestNumber=" + securityQuestNumber + ", securityQuestions="
				+ securityQuestions + "]";
	}
	
	
}
