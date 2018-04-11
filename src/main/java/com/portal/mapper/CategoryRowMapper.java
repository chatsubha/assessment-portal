package com.portal.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.portal.model.Category;

public class CategoryRowMapper implements RowMapper<Category> {
	
	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category category = new Category();
		category.setCategoryId(rs.getInt("category_Id"));
		category.setCategoryName(rs.getString("category_Name"));
		category.setCategoryDesc(rs.getString("description"));
		category.setCutOff(rs.getInt("cut_off"));
		category.setMaximumAttempts(rs.getInt("max_attempt"));
		return category;
	}



}
