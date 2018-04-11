package com.portal.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.portal.dao.CategoryDao;
import com.portal.dao.QuestionDao;
import com.portal.mapper.CategoryRowMapper;
import com.portal.model.Category;
import com.portal.util.SQLConst;

/**
 * @author Rajkumar Kathiresan.
 *
 */
@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {

	/** Spring JDBC Template */
	@Autowired
	JdbcTemplate jdbcTemplate;

	/** question DAO */
	@Autowired
	QuestionDao questionDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.CategoryDao#addCategory(com.portal.model.
	 * Category)
	 */
	@Override
	public void addCategory(Category category) {

		jdbcTemplate.update(SQLConst.createCategory,
				new Object[] { category.getCategoryName().trim(), category.getCategoryDesc().trim(), category.getCutOff(),category.getMaximumAttempts() });

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.CategoryDao#getCategories()
	 */
	@Override
	public List<Category> getCategories() {
		return jdbcTemplate.query(SQLConst.findAllCategories, new CategoryRowMapper());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.CategoryDao#getCategoryById(int)
	 */
	@Override
	public Category getCategoryById(Integer categoryId) {

		return jdbcTemplate.queryForObject(SQLConst.findCategoryById, new Object[] { categoryId },
				new CategoryRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.CategoryDao#deleteCategoryByCategoryId(int)
	 */
	@Override
	public void deleteCategoryByCategoryId(Integer categoryId) {

		jdbcTemplate.update(SQLConst.deleteCategoryById, new Object[] { categoryId });

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.CategoryDao#updateCategory(com.portal.model.
	 * Category)
	 */
	@Override
	public void updateCategory(Category category) {

		jdbcTemplate.update(SQLConst.updateCategoryById, new Object[] { category.getCategoryName().trim(),
				category.getCategoryDesc().trim(), category.getCutOff(), category.getMaximumAttempts(), category.getCategoryId() });
	}

}
