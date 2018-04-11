package com.portal.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.portal.dao.AttemptsPerComplexityDao;
import com.portal.model.MaxAttempts;
import com.portal.model.MaxComplexity;
import com.portal.util.SQLConst;

@Repository("AttemptsPerComplexityDao")
public class AttemptsPerComplexityDaoImpl implements AttemptsPerComplexityDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<MaxAttempts> getMaxAttemptsPerComplexity(Integer categoryId) {
		List<MaxAttempts> noOfAttemptsLst = new ArrayList<>();
		List<Map<String, Object>> attemptList = jdbcTemplate.queryForList(
				"select category.max_attempt max_attempt, attempt.no_question questionCnt, attempt.time Time from AP.tblcategory category left join AP.tblattempt attempt on  "
						+ "category.category_id = attempt.category_id where category.category_id=" + categoryId
						+ " order by attempt.no_attempt asc");
		Integer maxAttempts = 0;
		List<Integer> questionsLst = new ArrayList<>();
		List<Time> timeTakenLst = new ArrayList<>();
		if (!attemptList.isEmpty()) {
			for (Map<String, Object> qsList : attemptList) {
				maxAttempts = (Integer) qsList.get("max_attempt");
				Integer tmp = (Integer) qsList.get("questionCnt");
				Time timeTakenTmp = (Time) qsList.get("Time");
				if (tmp != null && timeTakenTmp != null) {
					questionsLst.add((Integer) qsList.get("questionCnt"));
					timeTakenLst.add((Time) qsList.get("Time"));
				}
			}
		}

		for (int i = 0; i < maxAttempts; i++) {

			MaxAttempts maxAttempt = new MaxAttempts();
			maxAttempt.setAttemptsId(i + 1);
			maxAttempt.setAttemptsDesc("Attempt " + (i + 1));
			if (!questionsLst.isEmpty() && i < questionsLst.size()) {
				maxAttempt.setChkAttemptEntry("Y");
				maxAttempt.setQuestionsId(questionsLst.get(i));
				maxAttempt.setTimePerAttempt(timeTakenLst.get(i));
			} else {
				maxAttempt.setChkAttemptEntry("N");
				maxAttempt.setQuestionsId(0);
			}
			maxAttempt.setMaxComplexity(getComplexityLst(categoryId, i + 1));
			maxAttempt.setQuestionDesc("Number Of Questions For Attempt " + (i + 1));
			noOfAttemptsLst.add(maxAttempt);
		}
		return noOfAttemptsLst;
	}

	/**
	 * This method is used to get complexities percentages per attempts.
	 */
	public List<MaxComplexity> getComplexityLst(int categoryId, int attemptId) {

		List<MaxComplexity> tmpLst = jdbcTemplate.query(
				"select percentage from  ap.tblcomplexitylookup lookup where lookup.category_id= ? and lookup.no_attempt= ? order by complexity, no_attempt asc ",
				new Object[] { categoryId, attemptId }, new RowMapper<MaxComplexity>() {
					@Override
					public MaxComplexity mapRow(ResultSet rs, int rownumber) throws SQLException {
						MaxComplexity maxComplexity = new MaxComplexity();
						maxComplexity.setPercentageValues(rs.getInt("percentage"));
						return maxComplexity;
					}
				});
		return tmpLst;
	}

	@Override
	public void updateQuestionsPerAttempts(List<MaxAttempts> questionsLst, Integer categoryId, String checkEntry,
			Integer maxAttempts) {
		// if("N".equalsIgnoreCase(checkEntry)) {
		if (questionsLst != null && !questionsLst.isEmpty()) {
			for (int i = 0; i < questionsLst.size(); i++) {

				List<Map<String, Object>> tempLst = jdbcTemplate
						.queryForList("select no_question from ap.tblattempt where category_id=" + categoryId
								+ " and no_attempt= " + (i + 1));
				if (!tempLst.isEmpty()) {
					jdbcTemplate.update(SQLConst.updateQuestionsPerCategory,
							new Object[] { questionsLst.get(i).getQuestionsId(),
									questionsLst.get(i).getTimePerAttempt(), i + 1, categoryId });
					for (int j = 0; j < questionsLst.get(i).getMaxComplexity().size(); j++) {
						jdbcTemplate.update(SQLConst.updateComplexityPerAttempts,
								new Object[] { questionsLst.get(i).getMaxComplexity().get(j).getPercentageValues(),
										i + 1, j + 1, categoryId });
					}
				} else {
					jdbcTemplate.update(SQLConst.insertQuestionsPerCategory, new Object[] { categoryId, i + 1,
							questionsLst.get(i).getQuestionsId(), questionsLst.get(i).getTimePerAttempt() });
					for (int j = 0; j < questionsLst.get(i).getMaxComplexity().size(); j++) {
						jdbcTemplate.update(SQLConst.insertComplexityPerAttempts, new Object[] { categoryId, j + 1,
								i + 1, questionsLst.get(i).getMaxComplexity().get(j).getPercentageValues() });
					}
				}

			}
		}
		/*
		 * } else { if(questionsLst!=null && !questionsLst.isEmpty()) { for(int i=0;
		 * i<questionsLst.size(); i++) {
		 * jdbcTemplate.update(SQLConst.updateQuestionsPerCategory, new Object[] {
		 * questionsLst.get(i).getQuestionsId(),
		 * questionsLst.get(i).getTimePerAttempt(), i+1,categoryId }); for(int j =0; j<
		 * questionsLst.get(i).getMaxComplexity().size(); j++) {
		 * jdbcTemplate.update(SQLConst.updateComplexityPerAttempts, new Object[] {
		 * questionsLst.get(i).getMaxComplexity().get(j).getPercentageValues(), i+1,
		 * j+1, categoryId}); } } } }
		 */
	}

	@Override
	public List<MaxComplexity> getMaxAttempts() {
		List<MaxComplexity> retLst = new ArrayList<>();
		List<Map<String, Object>> complexityLst = jdbcTemplate
				.queryForList("select complexity,description from AP.tblcomplexity");
		if (!complexityLst.isEmpty()) {
			for (Map<String, Object> qsList : complexityLst) {
				MaxComplexity tmp = new MaxComplexity();
				tmp.setComplexityId((Integer) qsList.get("complexity"));
				tmp.setComplexityName((String) qsList.get("description"));
				retLst.add(tmp);
			}
		}
		return retLst;
	}

	@Override
	public void deleteComplexityLookupByCategoryId(int categoryId) {
		jdbcTemplate.update(SQLConst.deleteComplexityByCategoryId, new Object[] { categoryId });
	}

	@Override
	public void deleteAttemptByCategoryId(int categoryId) {
		jdbcTemplate.update(SQLConst.deleteAttemptsByCategoryId, new Object[] { categoryId });
	}

}
