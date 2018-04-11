/**
 * 
 */
package com.portal.dao;

import java.util.List;

import com.portal.model.Question;

/**
 * @author Rajkumar Kathiresan.
 *
 */
public interface QuestionDao {

	/**
	 * Add the Question to the table.
	 * 
	 * @param question
	 *            the Question
	 */
	public void addQuestion(Question question);
	
	/**
	 * Add the Bulk Question to the table.
	 * 
	 * @param parameterList
	 *            the Question
	 */
	public void addBulkQuestion(List<Object[]> parameterList);

	/**
	 * Get all the Questions from table.
	 * 
	 * @return the List of Questions
	 */
	public List<Question> getQuestions();

	/**
	 * Get Questions List by category Id from the table.
	 * 
	 * @param categoryId
	 *            the category Id.
	 * @return the Question object.
	 */
	public List<Question> getQuestionByCategoryId(Integer categoryId);

	/**
	 * Delete the questions by Category Id.
	 * 
	 * @param categoryId
	 *            the Category Id.
	 */
	public void deleteQuestionsByCategoryId(Integer categoryId);

	/**
	 * Delete the Question details by ID
	 * 
	 * @param questionId
	 *            the question ID
	 */
	public void deleteQuestionById(Integer questionId);

	/**
	 * Load the question object by question ID
	 * 
	 * @param questionId
	 *            the Question ID.
	 * @return the Question Object
	 */
	public Question getQuestionById(Integer questionId);

	/**
	 * Update the existing Question details
	 * 
	 * @param question
	 *            the Question Object
	 */
	public void updateQuestion(Question question);
	
	/**
	 * check existing Question details
	 * 
	 * @param question
	 *            the Question Object
	 */
	public int isSameQuestion(Object[] question);
}
