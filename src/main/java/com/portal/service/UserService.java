package com.portal.service;

import java.util.List;

import com.portal.model.User;
import com.portal.model.UserActivityHistory;

public interface UserService {
	boolean validateUser(String userid, String password) ;
	public String checkRole(String userId);
	int getUid(String username);
	public String fetchUserQuest(String userId);
	public String fetchUserEmail(String userId);
	
	public void setPassword(String userId, String pwd);

	
	public String fetchUserAns(String userId);
	public List<UserActivityHistory> getUserAssesmentHistory(String userId);
	public List<UserActivityHistory> getUserAssesmentHistory();
}
