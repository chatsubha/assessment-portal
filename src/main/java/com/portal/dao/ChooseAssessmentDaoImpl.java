package com.portal.dao;

import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.portal.mapper.AssessmentRowMapper;
import com.portal.mapper.CategoryRowMapper;
import com.portal.mapper.MaximumAttemptsRowMapper;
import com.portal.model.Category;
import com.portal.util.SQLConst;

@Repository
public class ChooseAssessmentDaoImpl implements ChooseAssessmentDao  {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
/*	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}*/

	@Override
	public List<Category> getAssessmentCatogory() {
		String assessmentCategoryQry = "SELECT * FROM AP.TBLCATEGORY";
		List<Category> categoryList = jdbcTemplate.query(assessmentCategoryQry, new CategoryRowMapper());		
		System.out.println("Size : "+categoryList.size());
		/*ArrayList<String> categories = new ArrayList<String>();
		categories.add("Healthcare - Providers");
		categories.add("Agile");
		categories.add("Scrum Master");
		categories.add("Healthcare - Claims");
		categories.add("Healthcare - Eligibility");*/
		return categoryList;
	}

@Override
public Category fetchAssessmentCategory(int id) {
	String selectedAssessmentQry = "SELECT * FROM AP.TBLCATEGORY WHERE category_Id ='" + id + "'";
	//Category categoryObject = (Category) jdbcTemplate.query(selectedAssessmentQry, new CategoryRowMapper());
	Category categoryObject = (Category) jdbcTemplate.queryForObject(selectedAssessmentQry, new CategoryRowMapper());
	System.out.println(categoryObject);
	return categoryObject;
}

@Override
public List<Integer> getNotApplicableCategories(int userID) {

	List<Integer> invalidAssessments = new ArrayList<>();
	List<Category> noOfAttemptsLst = jdbcTemplate.query(SQLConst.getMaximumAttemptsPerCategory, new MaximumAttemptsRowMapper());
	for(int j=0; j < noOfAttemptsLst.size(); j++) {
		List<Category> categoryListValues = jdbcTemplate.query(SQLConst.getNotApplicableAssessements, new Object[] { userID, noOfAttemptsLst.get(j).getCategoryId() } ,new AssessmentRowMapper());
			if(categoryListValues.get(0).getNoOfAttempts() >= noOfAttemptsLst.get(j).getMaximumAttempts()) {
				invalidAssessments.add(noOfAttemptsLst.get(j).getCategoryId());
			}
	}
	
	
	return invalidAssessments;
}

@Override
public List<Integer> getEmptyQuestionsCategories() {
	List<Integer> emptyCategoryLst = new ArrayList<>();
	
	List<Map<String,Object>> executionLst = jdbcTemplate.queryForList("select category.category_id categories from ap.tblcategory category left join ap.tblquestion questions on questions.category_id = category.category_id where questions.category_id is null");
	if (!executionLst.isEmpty()) { 
		for (Map<String, Object> qsList : executionLst){
			emptyCategoryLst.add((Integer) qsList.get("categories"));
		}
	}
	
	return emptyCategoryLst;
}

	
}
