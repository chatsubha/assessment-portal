/**
 * 
 */
package com.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.portal.dao.QuestionDao;
import com.portal.model.Question;
import com.portal.service.OptionsService;
import com.portal.service.QuestionService;

/**
 * @author 683173
 *
 */
@Service("questionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class QuestionServiceImpl implements QuestionService {

	/** Question DAO */
	@Autowired
	QuestionDao questionDao;

	/** Options Service */
	@Autowired
	OptionsService optionsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.portal.service.QuestionService#addQuestion(com.portal.model
	 * .Question)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addQuestion(Question question) {
		questionDao.addQuestion(question);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.service.QuestionService#getQuestions()
	 */
	@Override
	public List<Question> getQuestions() {
		return questionDao.getQuestions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.service.QuestionService#getQuestionByCategoryId(
	 * categoryId)
	 */
	@Override
	public List<Question> getQuestionByCategoryId(Integer categoryId) {
		return questionDao.getQuestionByCategoryId(categoryId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.service.QuestionService#deleteQuestionsByCategoryId(
	 * categoryId)
	 */
	@Override
	public void deleteQuestionsByCategoryId(Integer categoryId) {
		List<Question> questionByCategoryId = questionDao.getQuestionByCategoryId(categoryId);
		for (Question question : questionByCategoryId) {
			optionsService.deleteOptionsByQuestionId(question.getQuestionId());
		}
		questionDao.deleteQuestionsByCategoryId(categoryId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.portal.service.QuestionService#deleteQuestionById(java.lang.
	 * Integer)
	 */
	@Override
	public void deleteQuestionById(Integer questionId) {
		optionsService.deleteOptionsByQuestionId(questionId);
		questionDao.deleteQuestionById(questionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.service.QuestionService#getQuestionById(java.lang.
	 * Integer)
	 */
	@Override
	public Question getQuestionById(Integer questionId) {
		return questionDao.getQuestionById(questionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.portal.service.QuestionService#updateQuestion(com.portal.
	 * model.Question)
	 */
	@Override
	public void updateQuestion(Question question) {
		questionDao.updateQuestion(question);

	}

}
