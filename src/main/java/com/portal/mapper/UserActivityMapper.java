package com.portal.mapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.jdbc.core.RowMapper;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers.CalendarDeserializer;
import com.portal.model.UserActivity;
import com.portal.model.UserActivityHistory;

public class UserActivityMapper implements RowMapper<UserActivityHistory>,Serializable {

	@Override
	public UserActivityHistory mapRow(ResultSet rs, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		UserActivityHistory userActivity=new UserActivityHistory();
		userActivity.setResult(rs.getString("result"));
		userActivity.setScore(rs.getInt("score"));
		userActivity.setUser_id(rs.getInt("user_id"));
		userActivity.setCategory_name(rs.getString("category_name"));
		userActivity.setNum_of_attempts(rs.getInt("num_of_attempts"));
		SimpleDateFormat st=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		userActivity.setLast_attempt_date(st.format(rs.getTimestamp("last_attempt_date")));
		userActivity.setUser_name(rs.getString("username"));
		return userActivity;
	}

}
