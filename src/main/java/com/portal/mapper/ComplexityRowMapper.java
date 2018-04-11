/**
 * 
 */
package com.portal.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.portal.model.Complexity;

/**
 * @author 683173
 *
 */
public class ComplexityRowMapper implements RowMapper<Complexity> {

	@Override
	public Complexity mapRow(ResultSet rs, int rowNum) throws SQLException {
		Complexity complexity = new Complexity();
//		complexity.setComplexity(rs.getInt("complexity"));
		complexity.setComplexityId(rs.getInt("complexity"));
		complexity.setComplexityName(rs.getString("description"));
		return complexity;
	}

}
