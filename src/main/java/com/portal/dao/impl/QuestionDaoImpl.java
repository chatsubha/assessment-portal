/**
 * 
 */
package com.portal.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.portal.dao.QuestionDao;
import com.portal.mapper.QustionRowMapper;
import com.portal.model.Question;
import com.portal.util.SQLConst;

/**
 * @author Rajkumar Kathiresan.
 *
 */
@Repository("questionDao")
public class QuestionDaoImpl implements QuestionDao {

	/** Spring JDBC Template */
	@Autowired
	JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.QuestionDao#addQuestion(com.portal.model.
	 * Question)
	 */
	@Override
	public void addQuestion(Question question) {

		jdbcTemplate.update(SQLConst.createQuestion,
				new Object[] { question.getQuestionDesc(), question.getAnswerType(), question.getCategoryId(),question.getComplexity() });

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.QuestionDao#addQuestion(com.portal.model.
	 * Question)
	 */
	@Override
	public void addBulkQuestion(List<Object[]> parameterList) {
		
		
		jdbcTemplate.batchUpdate(SQLConst.createQuestion,parameterList
				);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.QuestionDao#getQuestions()
	 */
	@Override
	public List<Question> getQuestions() {
		return jdbcTemplate.query(SQLConst.findAllQuestions, new QustionRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.QuestionDao#getQuestionByCategoryId(java.lang.
	 * Integer)
	 */
	@Override
	public List<Question> getQuestionByCategoryId(Integer categoryId) {
		return jdbcTemplate.query(SQLConst.findQuestionByCategoryId, new Object[] { categoryId },
				new QustionRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.QuestionDao#deleteQuestionsByCategoryId(int)
	 */
	@Override
	public void deleteQuestionsByCategoryId(Integer categoryId) {
		jdbcTemplate.update(SQLConst.deleteQuestionByCategoryId, new Object[] { categoryId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.QuestionDao#deleteQuestionById(java.lang.Integer)
	 */
	@Override
	public void deleteQuestionById(Integer questionId) {
		jdbcTemplate.update(SQLConst.deleteQuestionById, new Object[] { questionId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.QuestionDao#getQuestionById(java.lang.Integer)
	 */
	@Override
	public Question getQuestionById(Integer questionId) {
		return jdbcTemplate.queryForObject(SQLConst.findQuestionById, new Object[] { questionId },
				new QustionRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.QuestionDao#updateQuestion(com.portal.model.
	 * Question)
	 */
	@Override
	public void updateQuestion(Question question) {
		jdbcTemplate.update(SQLConst.updateQuestionById, new Object[] { question.getQuestionDesc(),
				question.getAnswerType(), question.getCategoryId(),question.getComplexity(), question.getQuestionId() });
	}

	@Override
	public int isSameQuestion(Object[] question) {
		try {
			return jdbcTemplate.queryForObject(SQLConst.getNumberOfQuestion, question , Integer.class);
		}
		catch (Exception e) {
			return 0;
		}
		
	}

}
