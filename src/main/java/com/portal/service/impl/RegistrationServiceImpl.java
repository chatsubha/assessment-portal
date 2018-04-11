package com.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dao.RegistrationDao;
import com.portal.model.SecurityQuestions;
import com.portal.model.User;
import com.portal.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {
	
	@Autowired
	private RegistrationDao registrationDao;
	
	@Override
	public List<SecurityQuestions> getSecurityQuestionLst() {		
		List<SecurityQuestions> securityQuestLst = registrationDao.fetchSecurityQuestionLst();		
		return securityQuestLst;
	}

	@Override
	public void insertUserDetails(User user) {
		registrationDao.feedUserDetails(user);		
	}

	@Override
	public boolean validateUser(String uName) {
		
		if(registrationDao.getUserName(uName))
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
}
