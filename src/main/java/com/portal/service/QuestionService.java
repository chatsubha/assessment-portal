/**
 * 
 */
package com.portal.service;

import java.util.List;

import com.portal.model.Question;

/**
 * @author Rajkumar Kathiresan.
 *
 */
public interface QuestionService {
	/**
	 * Add the Question to the table.
	 * 
	 * @param question
	 *            the Question
	 */
	public void addQuestion(Question question);

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
	 * Delete the Question by Question Id
	 * 
	 * @param questionId
	 *            the Question Id
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
}
