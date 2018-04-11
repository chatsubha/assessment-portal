/**
 * 
 */
package com.portal.service;

import java.util.List;

import com.portal.model.Category;

/**
 * @author Rajkumar Kathiresan.
 *
 */
public interface CategoryService {

	/**
	 * Add the Category to the table.
	 * 
	 * @param category
	 *            the category
	 */
	public void addCategory(Category category);

	/**
	 * Get all the categories from table.
	 * 
	 * @return the List of Categories
	 */
	public List<Category> getCategories();

	/**
	 * Get Category by category Id from the table.
	 * 
	 * @param categoryId
	 *            the category Id.
	 * @return the Category object.
	 */
	public Category getCategoryById(Integer categoryId);

	/**
	 * Delete the Category details by ID
	 * 
	 * @param categoryId
	 *            the category Id
	 */
	public void deleteCategoryByCategoryId(int categoryId);

	/**
	 * Update Category
	 * 
	 * @param category
	 *            the category Object
	 */
	public void updateCategory(Category category);
}
