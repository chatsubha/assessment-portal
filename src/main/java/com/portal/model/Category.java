/**
 * 
 */
package com.portal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rajkumar Kathiresan.
 *
 */
public class Category {

	/** Category Id - primary Key. */
	private Integer categoryId;
	/** Category Name - to display. */
	private String categoryName;
	/** Category Description - about the topic. */
	private String categoryDesc;
	/** cut Off mark to pass */
	private Integer cutOff;
	
	private Integer maximumAttempts;
	
	private Integer noOfAttempts;
	
	public Integer getMaximumAttempts() {
		return maximumAttempts;
	}

	public void setMaximumAttempts(Integer maximumAttempts) {
		this.maximumAttempts = maximumAttempts;
	}

	public Integer getNoOfAttempts() {
		return noOfAttempts;
	}

	public void setNoOfAttempts(Integer noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the categoryDesc
	 */
	public String getCategoryDesc() {
		return categoryDesc;
	}

	/**
	 * @param categoryDesc
	 *            the categoryDesc to set
	 */
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	/**
	 * @return the cutOff
	 */
	public Integer getCutOff() {
		return cutOff;
	}

	/**
	 * @param cutOff
	 *            the cutOff to set
	 */
	public void setCutOff(Integer cutOff) {
		this.cutOff = cutOff;
	}

	/**
	 * Default constructor.
	 */
	public Category() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * parameter constructor.
	 */
	public Category(Integer categoryId, String categoryName, String categoryDesc, Integer cutOff) {

		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		this.cutOff = cutOff;

	}

	public static List<Category> categories = new ArrayList<Category>();

	static {
		categories.add(new Category(100, "Core Java",
				"Object, Class, Constructor, Static block, instance block, OOP'S Related Question", 7));
		categories.add(new Category(101, "Framework", "Spring MVC, JSF, Struts related questions ", 8));
		categories.add(new Category(102, "Database", "SQL scripts and Hibernate related questions ", 6));
		categories.add(new Category(103, "UI", "JSP, Javascript, JQury, CSS related questions ", 7));
	}

}
