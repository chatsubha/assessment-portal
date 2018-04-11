/**
 * 
 */
package com.portal.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.portal.dao.LookupTableDao;
import com.portal.mapper.AnswerTypeRowMapper;
import com.portal.mapper.ComplexityRowMapper;
import com.portal.model.AnswerType;
import com.portal.model.Complexity;
import com.portal.util.SQLConst;

/**
 * @author Rajkumar Kathiresan
 *
 */
@Repository("lookupTableDao")
public class LookupTableDaoImpl implements LookupTableDao {

	/** Spring JDBC Template */
	@Autowired
	JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.LookupTableDao#getAnswerTypes()
	 */
	@Override
	public List<AnswerType> getAnswerTypes() {
		return jdbcTemplate.query(SQLConst.findAllAnswerTypes, new AnswerTypeRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.LookupTableDao#getAnswerTypes()
	 */
	@Override
	public List<Complexity> getComplexity() {
		return jdbcTemplate.query(SQLConst.findAllComplexityTypes, new ComplexityRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.LookupTableDao#getLookupTableDesc(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String getLookupTableDesc(String tableName, String columnName, String whereCondition) {
		String sql = "SELECT " + columnName + "   FROM " + tableName + " WHERE " + whereCondition;
		return (String) jdbcTemplate.queryForObject(sql, String.class);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.LookupTableDao#getLookupTableDesc(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String getLookupTableDesc(String tableName, String columnName, String whereCondition,Object[] params) {
		String sql = "SELECT " + columnName + "   FROM " + tableName + " WHERE " + whereCondition;
		return (String) jdbcTemplate.queryForObject(sql,params, String.class);
	}

}
