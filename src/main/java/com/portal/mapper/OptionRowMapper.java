package com.portal.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.portal.model.Options;

public class OptionRowMapper implements RowMapper<Options> {

	@Override
	public Options mapRow(ResultSet rs, int rowNum) throws SQLException {
		Options options = new Options();
		options.setOptionsId(rs.getInt("OPTION_ID"));
		options.setQuestionId(rs.getInt("QUESTION_ID"));
		options.setOptionsDesc(rs.getString("OPTION_DESC"));
		options.setCorrectAnswer(rs.getString("CORRECT_ANSWER"));
		return options;
	}
}
