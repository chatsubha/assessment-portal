package com.portal.form;

import java.util.List;

import com.portal.model.Category;

public class ChooseAssessment {

	private String chosenAssessment;
	private int chosenAssessments;
	private int categoryId;
	private int categoryCutOff;
	private List<Category> categoryObjList;
	/** Category Object */
	private Category category = new Category();
	
	
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * @return the category_Id
	 */
	public int getCategoryId() {
		return categoryId;
	}

	/**
	 * @param category_Id the category_Id to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	/**
	 * @return the categoryObjList
	 */
	public List<Category> getCategoryObjList() {
		return categoryObjList;
	}

	/**
	 * @param categoryObjList the categoryObjList to set
	 */
	public void setCategoryObjList(List<Category> categoryObjList) {
		this.categoryObjList = categoryObjList;
	}

	/**
	 * @return the chosenAssessment
	 */
	public String getChosenAssessment() {
		return chosenAssessment;
	}

	/**
	 * @param chosenAssessment
	 *            the chosenAssessment to set
	 */
	public void setChosenAssessment(String chosenAssessment) {
		this.chosenAssessment = chosenAssessment;
	}

	/**
	 * @return the chosenAssessments
	 */
	public int getChosenAssessments() {
		return chosenAssessments;
	}

	/**
	 * @param chosenAssessments
	 *            the chosenAssessments to set
	 */
	public void setChosenAssessments(int chosenAssessments) {
		this.chosenAssessments = chosenAssessments;
	}
	/**
	 * @return the categoryCutOff
	 */
	public int getCategoryCutOff() {
		return categoryCutOff;
	}

	/**
	 * @param categoryCutOff the categoryCutOff to set
	 */
	public void setCategoryCutOff(int categoryCutOff) {
		this.categoryCutOff = categoryCutOff;
	}
}
