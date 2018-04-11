package com.portal.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.portal.model.SecurityQuestions;

public class SecurityQuestionsRowMapper implements RowMapper<SecurityQuestions> {

	@Override
	public SecurityQuestions mapRow(ResultSet rs, int rowNum) throws SQLException {		
		SecurityQuestions securityQuestions = new SecurityQuestions();
		securityQuestions.setSecurityQuestNumber(rs.getInt("securityquestnumber"));
		securityQuestions.setSecurityQuestions(rs.getString("securityquestions"));
		return securityQuestions;
	}
	

}
