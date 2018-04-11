package com.portal.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.portal.dao.UserDao;
import com.portal.mapper.UserActivityMapper;
import com.portal.model.User;
import com.portal.model.UserActivityHistory;
import com.portal.util.LoginConstant;
import com.portal.util.SQLConst;

@Repository
public class UserDaoImpl implements UserDao  {

	@Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
    @Override
    public String getUserPswd(String userId)
	{
     //String sql = "SELECT password FROM tblusers WHERE user_name ='" + userId + "'";
    	try {
	    String password = jdbcTemplate.queryForObject(LoginConstant.USER_PASSWORD_SQL, new Object[] {userId}, String.class); 
	    
	    if (password.isEmpty()) {
	        return null;
	    } else {
	    	System.out.println("value: "+ password );
	        return password;
	    }
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public String checkRole(String userId) {
	try {
		String role=jdbcTemplate.queryForObject(LoginConstant.USER_ROLE_SQL, new Object[] {userId}, String.class); 
		System.out.println(role);
		return role;
	} catch (Exception e) {
		System.out.println(e.getMessage());
		return null;
	}
	
	}
	
	@Override
	public int getUserId(String username) {
		
		try {
			int uid=jdbcTemplate.queryForObject(LoginConstant.USER_ID_SQL, new Object[] {username}, Integer.class); 
			System.out.println(uid);
			return uid;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	

	@Override
	public String getUserQuest(String UserId)
	{
		
		try {
			String USER_QUESTION_SQL = "SELECT securityQuestNumber FROM AP.tblusers WHERE user_name = ?";

			String qid = jdbcTemplate.queryForObject(USER_QUESTION_SQL, new Object[] {UserId}, String.class); 
					
			String sql1 = "SELECT securityQuestions FROM AP.tblsecurityquestion WHERE securityQuestNumber ='" + qid + "'";
			
			List<String> question1 = jdbcTemplate.queryForList(sql1, String.class); 
			
			System.out.println("security qstn:" + question1.get(0) );
			
			    if (question1.isEmpty()) {
			        return null;
			    } else {
			    	System.out.println("value: "+question1.get(0));
			        return question1.get(0);
			    }
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}
	}


	@Override
	public String getSecrtyAns(String UserId)
	{
		String sql = "SELECT securityAnswer FROM AP.tblusers WHERE user_name ='" + UserId + "'";
		 List<String> securityAns = jdbcTemplate.queryForList(sql, String.class); 
		    if (securityAns.isEmpty()) {
		        return null;
		    } else {
		    	System.out.println("value: "+securityAns.get(0));
		        return securityAns.get(0);
		    }
	}


	@Override
	public String getUserEmail(String userId)
	{
	String sql = "SELECT email FROM AP.tblusers WHERE user_name ='" + userId + "'";
	 List<String> email = jdbcTemplate.queryForList(sql, String.class); 
	    if (email.isEmpty()) {
	        return null;
	    } else {
	    	System.out.println("value: "+email.get(0));
	        return email.get(0);
	    }
	}

	
	@Override
	public void changePassword(String UserId,String pwd) {
		
		jdbcTemplate.update(SQLConst.updatePassword , new Object[] { pwd, UserId });
		System.out.println("Password changed");
	}
	
	@Override
	public List<UserActivityHistory> getUserAssesmentHistory(String userId)
	{
		List<UserActivityHistory> assessmentHistoryList =  jdbcTemplate.query(SQLConst.getUserAssessmentHistory, new Object[] {userId}, new UserActivityMapper()) ;
		for(UserActivityHistory useract:assessmentHistoryList)
		{
			System.out.println("Cat name"+useract.getCategory_name());
		}
		return assessmentHistoryList;
	}

	@Override
	public List<UserActivityHistory> getUserAssesmentHistory() {
		// TODO Auto-generated method stub
		List<UserActivityHistory> assessmentHistoryList =  jdbcTemplate.query(SQLConst.getAllUserAssessmentHistory, new UserActivityMapper()) ;
		for(UserActivityHistory useract:assessmentHistoryList)
		{
			System.out.println("Cat name"+useract.getCategory_name());
		}
		return assessmentHistoryList;
	}
}
