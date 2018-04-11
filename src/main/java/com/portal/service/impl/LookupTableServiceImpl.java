/**
 * 
 */
package com.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.portal.dao.LookupTableDao;
import com.portal.model.AnswerType;
import com.portal.model.Complexity;
import com.portal.service.LookupTableService;

/**
 * @author Rajkumar Kathiresan.
 *
 */
@Service("lookupTableService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LookupTableServiceImpl implements LookupTableService {

	/** lookup Table Dao */
	@Autowired
	LookupTableDao lookupTableDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.service.LookupTableService#getAnswerTypes()
	 */
	@Override
	public List<AnswerType> getAnswerTypes() {
		return lookupTableDao.getAnswerTypes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.service.LookupTableService#getComplexity()
	 */
	@Override
	public List<Complexity> getComplexity() {
		return lookupTableDao.getComplexity();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.portal.service.LookupTableService#getLookupTableDesc(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getLookupTableDesc(String tableName, String columnName, String whereCondition) {
		return lookupTableDao.getLookupTableDesc(tableName, columnName, whereCondition);
	}

}
