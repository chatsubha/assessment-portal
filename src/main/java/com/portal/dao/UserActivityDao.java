package com.portal.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.portal.model.UserActivity;

public interface UserActivityDao {
	public String getScore(String userId);
	
	public List<UserActivity> getAllUserActivityRowMapper(String userId);
	
}
