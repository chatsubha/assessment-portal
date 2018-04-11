/**
 * 
 */
package com.portal.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.portal.dao.OptionsDao;
import com.portal.mapper.OptionRowMapper;
import com.portal.model.Options;
import com.portal.util.SQLConst;

/**
 * @author Rajkumar Kathiresan
 *
 */
@Repository("optionsDao")
public class OptionsDaoImpl implements OptionsDao {

	/** Spring JDBC Template */
	@Autowired
	JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.OptionsDao#addOptions(com.portal.model.Options)
	 */
	@Override
	public void addOptions(Options option) {
		jdbcTemplate.update(SQLConst.createOptions,
				new Object[] { option.getQuestionId(), option.getOptionsDesc(), option.getCorrectAnswer() });

	}
	
	@Override
	public void addBulkOptions(List<Object[]> parameterList) {
		jdbcTemplate.batchUpdate(SQLConst.createOptions,
				parameterList);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.OptionsDao#getOptions()
	 */
	@Override
	public List<Options> getOptions() {
		return jdbcTemplate.query(SQLConst.findAllOptions, new OptionRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.OptionsDao#getOptionsByQuestionId(int)
	 */
	@Override
	public List<Options> getOptionsByQuestionId(Integer questionId) {
		return jdbcTemplate.query(SQLConst.findOptionsByQuestionId, new Object[] { questionId }, new OptionRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.OptionsDao#deleteOptionsByQuestionId(int)
	 */
	@Override
	public void deleteOptionsByQuestionId(Integer questionId) {
		jdbcTemplate.update(SQLConst.deleteOptionsByQuestionId, new Object[] { questionId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.OptionsDao#deleteOptionsById(int)
	 */
	@Override
	public void deleteOptionsById(Integer optionId) {
		jdbcTemplate.update(SQLConst.deleteOptionsById, new Object[] { optionId });

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.dao.OptionsDao#getOptionsById(java.lang.Integer)
	 */
	@Override
	public Options getOptionsById(Integer optionId) {
		return jdbcTemplate.queryForObject(SQLConst.findOptionsById, new Object[] { optionId }, new OptionRowMapper());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.portal.dao.OptionsDao#updateOptions(com.portal.model.Options)
	 */
	@Override
	public void updateOptions(Options options) {
		jdbcTemplate.update(SQLConst.updateOptionsById, new Object[] { options.getQuestionId(),
				options.getOptionsDesc(), options.getCorrectAnswer(), options.getOptionsId() });

	}

}
