/**
 * 
 */
package com.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.portal.dao.OptionsDao;
import com.portal.model.Options;
import com.portal.service.OptionsService;

/**
 * @author Rajkumar Kathiresan.
 *
 */
@Service("optionsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OptionsServiceImpl implements OptionsService {

	/** options Dao */
	@Autowired
	OptionsDao optionsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.portal.service.OptionsService#addOptions(com.portal.model.
	 * Options)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addOptions(Options question) {
		optionsDao.addOptions(question);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.service.OptionsService#getOptions()
	 */
	@Override
	public List<Options> getOptions() {
		return optionsDao.getOptions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.portal.service.OptionsService#getOptionsByQuestionId(java.lang.
	 * Integer)
	 */
	@Override
	public List<Options> getOptionsByQuestionId(Integer questionId) {
		return optionsDao.getOptionsByQuestionId(questionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.portal.service.OptionsService#deleteOptionsByQuestionId(Integer)
	 */
	@Override
	public void deleteOptionsByQuestionId(Integer questionId) {
		optionsDao.deleteOptionsByQuestionId(questionId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.service.OptionsService#deleteOptionsById(Integer)
	 */
	@Override
	public void deleteOptionsById(Integer optionId) {
		optionsDao.deleteOptionsById(optionId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.portal.service.OptionsService#getOptionsById(java.lang.Integer)
	 */
	@Override
	public Options getOptionsById(Integer optionId) {
		return optionsDao.getOptionsById(optionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.portal.service.OptionsService#updateOptions(com.portal.model.
	 * Options)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateOptions(Options options) {
		optionsDao.updateOptions(options);

	}

}
