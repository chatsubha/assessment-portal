/**
 * 
 */
package com.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.portal.dao.AttemptsPerComplexityDao;
import com.portal.dao.CategoryDao;
import com.portal.dao.OptionsDao;
import com.portal.dao.QuestionDao;
import com.portal.model.Category;
import com.portal.model.Question;
import com.portal.service.CategoryService;

/**
 * @author Rajkumar Kathiresan.
 *
 */
@Service("categoryService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CategoryServiceImpl implements CategoryService {

	/** category Dao */
	@Autowired
	CategoryDao categoryDao;

	/** question Dao */
	@Autowired
	QuestionDao questionDao;

	/** option Dao */
	@Autowired
	OptionsDao optionsDao;

	/** complexity DAO */
	@Autowired
	AttemptsPerComplexityDao complexityDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.portal.service.CategoryService#addCategory(com.portal.model
	 * .Category)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addCategory(Category category) {
		categoryDao.addCategory(category);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.service.CategoryService#getCategories()
	 */
	@Override
	public List<Category> getCategories() {
		return categoryDao.getCategories();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.service.CategoryService#getCategoryById(Integer)
	 */
	@Override
	public Category getCategoryById(Integer categoryId) {
		return categoryDao.getCategoryById(categoryId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.service.CategoryService#deleteCategoryByCategoryId(int)
	 */
	@Override
	public void deleteCategoryByCategoryId(int categoryId) {
		complexityDao.deleteComplexityLookupByCategoryId(categoryId);
		complexityDao.deleteAttemptByCategoryId(categoryId);
		List<Question> questionByCategoryId = questionDao.getQuestionByCategoryId(categoryId);
		for (Question question : questionByCategoryId) {
			optionsDao.deleteOptionsByQuestionId(question.getQuestionId());
		}
		questionDao.deleteQuestionsByCategoryId(categoryId);
		categoryDao.deleteCategoryByCategoryId(categoryId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.service.CategoryService#updateCategory(com.portal.
	 * model.Category)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateCategory(Category category) {
		categoryDao.updateCategory(category);

	}

}
