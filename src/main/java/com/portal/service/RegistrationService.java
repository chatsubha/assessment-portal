package com.portal.service;

import java.util.List;

import com.portal.model.SecurityQuestions;
import com.portal.model.User;

public interface RegistrationService {

	public List<SecurityQuestions> getSecurityQuestionLst();
	public void insertUserDetails(User user);
	public boolean validateUser(String uName);
}
