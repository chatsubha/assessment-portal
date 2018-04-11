package com.portal.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.portal.dao.RegistrationDao;
import com.portal.mapper.SecurityQuestionsRowMapper;
import com.portal.model.SecurityQuestions;
import com.portal.model.User;
import com.portal.util.SQLConst;

@Repository
public class RegistrationDaoImpl implements RegistrationDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<SecurityQuestions> fetchSecurityQuestionLst() {		
		List<SecurityQuestions> dbSecurityQuestionsLst = jdbcTemplate.query(SQLConst.securityQuestions, new SecurityQuestionsRowMapper());						
		return dbSecurityQuestionsLst;
	}

	@Override
	public void feedUserDetails(User user) {
		try {
			jdbcTemplate.update(SQLConst.createUser, new Object[] {user.getUser_name(), user.getPassword(), user.getRole(), 
																   user.getEmail(), user.getUserSecurtiyQuest(), user.getSecurityAns()});
		}catch(Exception e){
			System.out.println(e);
			throw new RuntimeException();
		}
						
	}
	

	@Override
	public boolean getUserName(String uName) {
		List<String> userName = jdbcTemplate.queryForList(SQLConst.compareUserName, new Object[] {uName}, String.class);
		if (userName.isEmpty() || userName ==null ) {
	        return false;
	    } else {
	        return true;
	    }		
	}

}
