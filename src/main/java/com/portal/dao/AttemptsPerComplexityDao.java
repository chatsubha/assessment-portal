package com.portal.dao;

import java.util.List;

import com.portal.model.MaxAttempts;
import com.portal.model.MaxComplexity;

public interface AttemptsPerComplexityDao {
	public List<MaxAttempts> getMaxAttemptsPerComplexity(Integer categoryId);

	public void updateQuestionsPerAttempts(List<MaxAttempts> questionsLst, Integer categoryId, String checkEntry,
			Integer maxAttempts);

	public List<MaxComplexity> getMaxAttempts();

	/**
	 * Delete the Complexity Lookup details by category Id.
	 * 
	 * @param categoryId
	 *            the category Id
	 */
	void deleteComplexityLookupByCategoryId(int categoryId);

	/**
	 * Delete the attempts details by category Id.
	 * 
	 * @param categoryId
	 *            the category id
	 */
	void deleteAttemptByCategoryId(int categoryId);
}
