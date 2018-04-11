/**
 * 
 */
package com.portal.service;

import java.util.List;

import com.portal.model.Options;

/**
 * @author Rajkumar Kathiresan.
 *
 */
public interface OptionsService {

	/**
	 * Add the Options to the table.
	 * 
	 * @param Options
	 *            the Options
	 */
	public void addOptions(Options question);

	/**
	 * Get all the Options from table.
	 * 
	 * @return the List of Options
	 */
	public List<Options> getOptions();

	/**
	 * Get List of Options by question Id from the table.
	 * 
	 * @param questionId
	 *            the question Id.
	 * @return the Options object.
	 */
	public List<Options> getOptionsByQuestionId(Integer questionId);

	/**
	 * Delete the Options by questions Id.
	 * 
	 * @param questionId
	 *            the question Id.
	 */
	public void deleteOptionsByQuestionId(Integer questionId);

	/**
	 * Delete Options by OptionId
	 * 
	 * @param optionId
	 *            the option Id
	 */
	public void deleteOptionsById(Integer optionId);

	/**
	 * Load the option object by ID
	 * 
	 * @param optionId
	 *            the Option ID
	 * @return the options Object
	 */
	public Options getOptionsById(Integer optionId);

	/**
	 * Update the existing options details
	 * 
	 * @param options
	 *            the Options
	 */
	public void updateOptions(Options options);
}
