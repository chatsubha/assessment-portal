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
public class Options {

	/** Options Id - foreign Key. */
	private Integer optionsId;
	/** Question Id - foreign Key. */
	private Integer questionId;
	/** Options Description */
	private String optionsDesc;
	/** Correct Answer */
	private String correctAnswer;

	/**
	 * @return the optionsId
	 */
	public Integer getOptionsId() {
		return optionsId;
	}

	/**
	 * @param optionsId
	 *            the optionsId to set
	 */
	public void setOptionsId(Integer optionsId) {
		this.optionsId = optionsId;
	}

	/**
	 * @return the questionId
	 */
	public Integer getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the optionsDesc
	 */
	public String getOptionsDesc() {
		return optionsDesc;
	}

	/**
	 * @param optionsDesc
	 *            the optionsDesc to set
	 */
	public void setOptionsDesc(String optionsDesc) {
		this.optionsDesc = optionsDesc;
	}

	/**
	 * @return the correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @param correctAnswer
	 *            the correctAnswer to set
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	/**
	 * parameter constructor.
	 */
	public Options() {

	}

	/**
	 * parameter constructor.
	 */
	public Options(Integer questionId, Integer optionsId, String optionsDesc, String correctAnswer) {

		this.questionId = questionId;
		this.optionsId = optionsId;
		this.optionsDesc = optionsDesc;
		this.correctAnswer = correctAnswer;

	}

	public static List<Options> options = new ArrayList<Options>();

	static {
		options.add(new Options(001, 1, "java 1", "Y"));
		options.add(new Options(001, 2, "java 2", "Y"));
		options.add(new Options(001, 3, "java 3", "Y"));
		options.add(new Options(001, 4, "java 4", "Y"));

		options.add(new Options(002, 5, "Spring MVC 1", "Y"));
		options.add(new Options(002, 6, "Spring MVC 2", "Y"));
		options.add(new Options(002, 7, "Spring MVC 3", "Y"));
		options.add(new Options(002, 8, "Spring MVC 4", "Y"));

		options.add(new Options(003, 9, "Hibernate 1", "Y"));
		options.add(new Options(003, 10, "Hibernate 2", "Y"));
		options.add(new Options(003, 11, "Hibernate 3", "Y"));
		options.add(new Options(003, 12, "Hibernate 4", "Y"));

		options.add(new Options(004, 13, "JSP 1", "Y"));
		options.add(new Options(004, 14, "JSP 2", "Y"));
		options.add(new Options(004, 15, "JSP 3", "Y"));
		options.add(new Options(004, 16, "JSP 4", "Y"));
	}

	/**
	 * @return the options
	 */
	public static List<Options> getOptions() {
		return options;
	}

	/**
	 * @param options
	 *            the options to set
	 */
	public static void setOptions(List<Options> options) {
		Options.options = options;
	}

}
