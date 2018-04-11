package com.portal.dao;

import java.util.List;

import com.portal.model.SecurityQuestions;
import com.portal.model.User;

public interface RegistrationDao {

	public List<SecurityQuestions> fetchSecurityQuestionLst();
	public void feedUserDetails(User user);
	public boolean getUserName(String uName);
}
