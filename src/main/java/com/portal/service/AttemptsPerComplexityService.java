package com.portal.service;

import java.util.List;

import com.portal.model.MaxAttempts;
import com.portal.model.MaxComplexity;


public interface AttemptsPerComplexityService {
	
	public List<MaxAttempts> getMaxAttemptsPerComplexity(Integer categoryId);
	public void updateQuestionsPerAttempts(List<MaxAttempts> questionsLst,Integer categoryId, String chkEntry, Integer maxAttempts);
	public List<MaxComplexity> getMaxComplexity();
}
