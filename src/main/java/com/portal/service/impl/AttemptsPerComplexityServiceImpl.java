package com.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dao.AttemptsPerComplexityDao;
import com.portal.model.MaxAttempts;
import com.portal.model.MaxComplexity;
import com.portal.service.AttemptsPerComplexityService;

@Service("AttemptsPerComplexityService")
public class AttemptsPerComplexityServiceImpl implements AttemptsPerComplexityService {

	@Autowired
	AttemptsPerComplexityDao attemptsDao;
	
	@Override
	public List<MaxAttempts> getMaxAttemptsPerComplexity(Integer categoryId) {
		return attemptsDao.getMaxAttemptsPerComplexity(categoryId);
	}
	@Override
	public void updateQuestionsPerAttempts(List<MaxAttempts> questionsLst,Integer categoryId, String checkEntry, Integer maxAttempts) {
		attemptsDao.updateQuestionsPerAttempts(questionsLst,categoryId, checkEntry, maxAttempts);
	}
	@Override
	public List<MaxComplexity> getMaxComplexity() {
		return attemptsDao.getMaxAttempts();
	}

}
