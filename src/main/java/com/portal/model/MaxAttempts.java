package com.portal.model;

import java.sql.Time;
import java.util.List;

public class MaxAttempts {

	private Integer attemptsId;
	
	private String attemptsDesc;
	
	private Integer questionsId;
	
	private String questionDesc;
	
	private String chkAttemptEntry;
	
	private Time timePerAttempt;
	
	public Time getTimePerAttempt() {
		return timePerAttempt;
	}

	public void setTimePerAttempt(Time timePerAttempt) {
		this.timePerAttempt = timePerAttempt;
	}

	List<MaxComplexity> maxComplexity;
	
	public List<MaxComplexity> getMaxComplexity() {
		return maxComplexity;
	}

	public void setMaxComplexity(List<MaxComplexity> maxComplexity) {
		this.maxComplexity = maxComplexity;
	}

	public String getChkAttemptEntry() {
		return chkAttemptEntry;
	}

	public void setChkAttemptEntry(String chkAttemptEntry) {
		this.chkAttemptEntry = chkAttemptEntry;
	}

	public Integer getAttemptsId() {
		return attemptsId;
	}

	public void setAttemptsId(Integer attemptsId) {
		this.attemptsId = attemptsId;
	}

	public String getAttemptsDesc() {
		return attemptsDesc;
	}

	public void setAttemptsDesc(String attemptsDesc) {
		this.attemptsDesc = attemptsDesc;
	}

	public Integer getQuestionsId() {
		return questionsId;
	}

	public void setQuestionsId(Integer questionsId) {
		this.questionsId = questionsId;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	
}
