package com.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.portal.dao.UserDao;
import com.portal.dao.impl.UserDaoImpl;
import com.portal.model.User;
import com.portal.service.UserService;
import com.portal.util.EncryptDecrypt;
import com.portal.model.UserActivityHistory;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	

	@Override
	public String fetchUserQuest(String userId){
		String question=userDao.getUserQuest(userId);
		return question;
	}
	
	@Override
	public String fetchUserAns(String userId){
		String question=userDao.getSecrtyAns(userId);
		return question;
	}
	
	@Override
	public String fetchUserEmail(String userId)
	{
		String email=userDao.getUserEmail(userId);
		return email;
	}

	User user;
	
	@Override
	public boolean validateUser(String userid, String password) {
		
		boolean isValid=false;
		String dbpassword = getUserDao().getUserPswd(userid);
		String encryptPass= EncryptDecrypt.encrypt(password);
		
		if(dbpassword!=null && encryptPass !=null){
		if(dbpassword.equals(encryptPass)){
			isValid=true;
		 }
		}
		return isValid;
    }
	
	@Override
	public String checkRole(String userId) {
		// TODO Auto-generated method stub
		String role=userDao.checkRole(userId);
		return role;
	}
	
	@Override
	public int getUid(String username) {
		int uid = userDao.getUserId(username);
		return uid;
	}
	
	public void setPassword(String userId, String pwd) {
		
		 String encryptPass= EncryptDecrypt.encrypt(pwd);
		 userDao.changePassword(userId,encryptPass);
	}
	
	@Override
	public List<UserActivityHistory> getUserAssesmentHistory(String userId)
	{
		List<UserActivityHistory> list =userDao.getUserAssesmentHistory(userId);
		return list;
	}

	@Override
	public List<UserActivityHistory> getUserAssesmentHistory() {
		// TODO Auto-generated method stub
		List<UserActivityHistory> list =userDao.getUserAssesmentHistory();
		return list;
	}
	
}
