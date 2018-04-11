/**
 * 
 */
package com.portal.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.portal.model.AnswerType;

/**
 * @author Rajkumar Kathiresan
 *
 */
public class AnswerTypeRowMapper implements RowMapper<AnswerType> {
	
	@Override
	public AnswerType mapRow(ResultSet rs, int rowNum) throws SQLException {
		AnswerType answerType = new AnswerType();
		answerType.setAnswerTypeId(rs.getInt("answer_type_id"));
		answerType.setAnswerTypeDesc(rs.getString("answer_type"));
		return answerType;
	}


}
