package com.portal.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.portal.model.Question;

public class QustionRowMapper implements RowMapper<Question> {

	@Override
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
		Question question = new Question();
		question.setQuestionId(rs.getInt("QUESTION_ID"));
		question.setQuestionDesc(rs.getString("QUESTION_DESC"));
		question.setAnswerType(rs.getInt("ANSWER_TYPE_ID"));
		question.setCategoryId(rs.getInt("CATEGORY_ID"));
		question.setComplexity(rs.getInt("COMPLEXITY"));
		return question;
	}

}
