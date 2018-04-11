package com.portal.dao;

import java.util.List;

import com.portal.model.User;
import com.portal.model.UserActivityHistory;

public interface UserDao {
	
	public String checkRole(String userId);
	public String getUserPswd(String userId);
	public int getUserId(String username);
	
	public String getUserQuest(String UserId);

	public String getSecrtyAns(String UserId);

	public String getUserEmail(String UserId);
	
	public void changePassword(String UserId,String pwd);
	
	public List<UserActivityHistory> getUserAssesmentHistory(String UserId);
	
	public List<UserActivityHistory> getUserAssesmentHistory();
}
