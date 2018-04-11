/**
 * 
 */
package com.portal.form;

import java.util.ArrayList;
import java.util.List;

import com.portal.model.Category;
import com.portal.model.MaxAttempts;
import com.portal.model.MaxComplexity;
import com.portal.model.Options;
import com.portal.model.Question;

/**
 * @author Rajkumar Kathiresan.
 *
 */
public class AdminFormBean {

	/** List of categories */
	private List<Category> categories;

	/** List of question for the selected category */
	private List<Question> questionList;

	/** List of options for the selected question */
	private List<Options> optionsList;
	
	private List<MaxComplexity> complexityHeaderLst;
	
	public List<MaxComplexity> getComplexityHeaderLst() {
		return complexityHeaderLst;
	}

	public void setComplexityHeaderLst(List<MaxComplexity> complexityHeaderLst) {
		this.complexityHeaderLst = complexityHeaderLst;
	}

	private List<MaxAttempts> maxAttemptsList;
	
	private List<MaxComplexity> maxComplexityList;
	
	public List<MaxComplexity> getMaxComplexityList() {
		return maxComplexityList;
	}

	public void setMaxComplexityList(List<MaxComplexity> maxComplexityList) {
		this.maxComplexityList = maxComplexityList;
	}

	public List<MaxAttempts> getMaxAttemptsList() {
		return maxAttemptsList;
	}

	public void setMaxAttemptsList(List<MaxAttempts> maxAttemptsList) {
		this.maxAttemptsList = maxAttemptsList;
	}

	/** Option Object */
	private Options option;

	/** Question Object */
	private Question question = new Question();

	/** Category Object */
	private Category category = new Category();
	
	private List<String> cntMaxAttemptsLst ;
	
	public List<String> getCntMaxAttemptsLst() {
		return cntMaxAttemptsLst;
	}

	public void setCntMaxAttemptsLst(List<String> cntMaxAttemptsLst) {
		this.cntMaxAttemptsLst = cntMaxAttemptsLst;
	}

	Integer totalAttempts;
	
	public Integer getTotalAttempts() {
		return totalAttempts;
	}

	public void setTotalAttempts(Integer totalAttempts) {
		this.totalAttempts = totalAttempts;
	}

	/**
	 * @return the categories
	 */
	public List<Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories
	 *            the categories to set
	 */
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	/**
	 * @return the questionList
	 */
	public List<Question> getQuestionList() {
		return questionList;
	}

	/**
	 * @param questionList
	 *            the questionList to set
	 */
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	/**
	 * @return the optionsList
	 */
	public List<Options> getOptionsList() {
		return optionsList;
	}

	/**
	 * @param optionsList
	 *            the optionsList to set
	 */
	public void setOptionsList(List<Options> optionsList) {
		this.optionsList = optionsList;
	}

	/**
	 * @return the option
	 */
	public Options getOption() {
		return option;
	}

	/**
	 * @param option
	 *            the option to set
	 */
	public void setOption(Options option) {
		this.option = option;
	}

	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

}
