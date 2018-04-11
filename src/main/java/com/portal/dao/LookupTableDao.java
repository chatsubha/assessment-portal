/**
 * 
 */
package com.portal.dao;

import java.util.List;

import com.portal.model.AnswerType;
import com.portal.model.Complexity;

/**
 * @author Rajkumar Kathiresan.
 *
 */
public interface LookupTableDao {

	/**
	 * Get all the AnswerTypes from table.
	 * 
	 * @return the List of AnswerType
	 */
	public List<AnswerType> getAnswerTypes();

	/**
	 * Get all the Complexity from table.
	 * 
	 * @return the List of Complexity
	 */
	public List<Complexity> getComplexity();

	/**
	 * Get the description value for the given key.
	 * 
	 * @param tableName
	 *            the table Name
	 * @param columnName
	 *            the column Name
	 * @param whereCondition
	 *            where Condition
	 * @return the Description
	 */
	public String getLookupTableDesc(String tableName, String columnName, String whereCondition);
	
	/**
	 * Get the description value for the given key.
	 * 
	 * @param tableName
	 *            the table Name
	 * @param columnName
	 *            the column Name
	 * @param whereCondition
	 *            where Condition
	 * @param whereCondition
	 *            Parameters
	 * @return the Description
	 */
	public String getLookupTableDesc(String tableName, String columnName, String whereCondition,Object[] params);

}
