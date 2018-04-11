package com.portal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.portal.dao.AssessementDao;
import com.portal.form.AssessementBean;
import com.portal.mapper.AssessmentRowMapper;
import com.portal.mapper.CategoryRowMapper;
import com.portal.mapper.MaximumAttemptsRowMapper;
import com.portal.model.Category;
import com.portal.model.Question;
import com.portal.model.UserActivity;
import com.portal.util.SQLConst;

@Repository
public class AssessementDaoImpl implements AssessementDao
{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * @param jdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}

	/* (non-Javadoc)
	 * This method is used to get Question details from database
	 * Getting questions random orders using RAND()
	 * Getting question_id and question_desc parameters
	 */
	/*@Override
	public LinkedHashMap<Integer, String> getQuestionDetails(AssessementBean assessementBeanObj)
	{
		int questionNumber = 0;		
		Integer questionId = null;
		String questionDesc = null;
		
		LinkedHashMap<Integer, String> questionDetails = new LinkedHashMap<Integer, String>();		
		try {
			//String noOfQuestQry = "select question_desc from ap.tblquestion where category_id = '"+assessementBeanObj.getCategoryId()+"'";
			String questQuery = "SELECT question_id, question_desc FROM ap.tblquestion where category_id = '"+assessementBeanObj.getCategoryId()+"' ORDER BY RAND () LIMIT 10";
			List<Map<String,Object>> questionList = jdbcTemplate.queryForList(questQuery);

			if (!questionList.isEmpty()) {
				for (Map<String, Object> qsList : questionList){			
					questionNumber = questionList.size();
					questionId = (Integer) qsList.get("question_Id");
					questionDesc = (String)qsList.get("question_desc");
					System.out.println("QUestions:: ID "+questionId +"   :: Value : "+questionDesc);
					questionDetails.put(questionId, questionDesc);
				} 
			}	
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		
		assessementBeanObj.setTotalQuestions(questionNumber);	
		
		return questionDetails;
	}*/
	
	
	
	
	@Override
	public int getNumberOfAttempts(AssessementBean assessementBeanObj) {

		int numberOfAttempts = 0;		
		List<Category> categoryListValues = jdbcTemplate.query(SQLConst.getNotApplicableAssessements, new Object[] { assessementBeanObj.getUserId(), assessementBeanObj.getCategoryId() } ,new AssessmentRowMapper());
	
		numberOfAttempts = categoryListValues.get(0).getNoOfAttempts(); 
		
		System.out.println("numberOfAttempts  ::: "+numberOfAttempts);
		
		return numberOfAttempts;
	}
	
	/* (non-Javadoc)
	 * This method is used to get Question details from database
	 * Getting questions random orders using RAND()
	 * Getting question_id and question_desc parameters
	 */	
	@Override
	public LinkedHashMap<Integer, String> getQuestionDetails(AssessementBean assessementBeanObj, Map<Integer, Integer> breakUp)
	{
		int questionNumber = 0;		
		Integer questionId = null;
		String questionDesc = null;
		
		LinkedHashMap<Integer, String> questionDetails = new LinkedHashMap<Integer, String>();		
		try {
			//String noOfQuestQry = "select question_desc from ap.tblquestion where category_id = '"+assessementBeanObj.getCategoryId()+"'";
			StringBuilder sb = new StringBuilder();
			int i = 1;
			for(Entry<Integer, Integer> questionCount : breakUp.entrySet()) {
				sb.append("(SELECT question_id, question_desc FROM ap.tblquestion WHERE category_id = '"+assessementBeanObj.getCategoryId() 
					+"' AND complexity='"+ questionCount.getKey() +"' ORDER BY RAND () LIMIT "+ questionCount.getValue() +")");
				if(i < breakUp.size())
					sb.append(" UNION ");
				i++;
			}
			/*String questQuery = "SELECT question_id, question_desc FROM ap.tblquestion where category_id = '"+assessementBeanObj.getCategoryId()
								+"' ORDER BY RAND () LIMIT 10";*/
			List<Map<String,Object>> questionList = jdbcTemplate.queryForList(sb.toString());

			if (!questionList.isEmpty()) {
				for (Map<String, Object> qsList : questionList){			
					questionNumber = questionList.size();
					questionId = (Integer) qsList.get("question_Id");
					questionDesc = (String)qsList.get("question_desc");
					System.out.println("QUestions:: ID "+questionId +"   :: Value : "+questionDesc);
					questionDetails.put(questionId, questionDesc);
				} 
			}	
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		
		assessementBeanObj.setTotalQuestions(questionNumber);	
		
		return questionDetails;
	}
	
	/* (non-Javadoc)
	 * This method is used to get Question count from database
	 */
	@Override
	public Integer getQuestionCount(int attempt, int category) {
		Integer questionNumber = null;

		try {
			String questQuery = "select no_question from ap.tblattempt where no_attempt='" + attempt + "' and category_id='"+ category +"'";
			List<Map<String, Object>> questionCount = jdbcTemplate.queryForList(questQuery);

			if (!questionCount.isEmpty()) {
				for (Map<String, Object> count : questionCount) {
					questionNumber = (Integer) count.get("no_question");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return questionNumber;
	}
	
	/* (non-Javadoc)
	 * This method is used to get Complexity Break Up from database
	 */
	@Override
	public Map<Integer, Integer> getComplexityBreakUp(int attempt, int category) {
		Integer complexity = null;
		Integer percentage = null;
		Map<Integer, Integer> breakup = new HashMap<Integer, Integer>();

		try {
			String questQuery = "select complexity, percentage from ap.tblcomplexitylookup where category_id='"+ category +"' and no_attempt='" + attempt + "'";
			List<Map<String, Object>> queryList = jdbcTemplate.queryForList(questQuery);
			if (!queryList.isEmpty()) {
				for (Map<String, Object> result : queryList) {
					complexity = (Integer) result.get("complexity");
					percentage = (Integer) result.get("percentage");
					breakup.put(complexity, percentage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return breakup;
	}
	
	/* 
	 * This method is used to get Options details from database
	 * Getting option_desc parameter and stored in list 
	 */
	@Override
	public LinkedHashMap<Integer, List<String>> getAnswerDetails(AssessementBean assessementBeanObj)
	{
		LinkedHashMap<Integer, List<String>> ansAndId = new LinkedHashMap<Integer, List<String>>();		
		Integer qsId =0;
		
		for (Map.Entry<Integer, String> qsList : assessementBeanObj.getQuestionList().entrySet()) {			
			qsId =(Integer) qsList.getKey();			
			List<String> answerLst = new ArrayList<String>();
			String answserQuery = "SELECT option_desc FROM ap.tbloptions where question_id='" +qsId + "'";
			List<String> results = jdbcTemplate.queryForList(answserQuery, String.class);
			for (String result : results) {
				answerLst.add(result);				
			}
			ansAndId.put(qsId, answerLst);
		}
		return ansAndId;
	}
	
	/* 
	 * This method is used to get questions correctAnswer details from database
	 * Mapping correct answers for question 
	 */
	@Override
	public LinkedHashMap<Integer, String> getCorrectAnswerDetails(AssessementBean assessementBeanObj) {
		// TODO Auto-generated method stub
		String ans=null;
		Integer qsId =0;
		StringBuffer sb =new StringBuffer();
		LinkedHashMap<Integer, String> correctAnswer=new LinkedHashMap<Integer, String>();		
		try
		{			
			for (Map.Entry<Integer, String> qsList : assessementBeanObj.getQuestionList().entrySet()) {
				qsId =(Integer) qsList.getKey();
				String optionsQuery="select option_desc from ap.tbloptions  where question_id='" + qsId +"' and correct_answer='Yes'";		
				List<String> answer=jdbcTemplate.queryForList(optionsQuery,String.class);
				for(String answerObj: answer) {					
					sb.append(answerObj.toString()+",");
				}				
				ans = sb.toString();
				ans = ans.substring(0, ans.length() - 1);	
				correctAnswer.put(qsId, ans);		
				sb.setLength(0);
			}
		}
		catch(Exception e)
		{					
			e.printStackTrace();
		}
		return correctAnswer;
	
	}

	/* 
	 * This method is used to get type of answers details from database
	 * Getting single value options or multi value options for question   
	 */
	@Override
	public LinkedHashMap<Integer, Integer> getAnswerType(AssessementBean assessementBeanObj) {
		
		LinkedHashMap<Integer, Integer> answerType = new LinkedHashMap<Integer, Integer>();		
		int answerTypeValue = 0;		
		Integer qsId =0;
		
		for (Map.Entry<Integer, String> qsList : assessementBeanObj.getQuestionList().entrySet()) {			
			qsId =(Integer) qsList.getKey();			
			String answerTypeQuery = "SELECT answer_type_id FROM ap.tblquestion WHERE question_id ='" + qsId +"'";
			List<Integer> ansTypeList = jdbcTemplate.queryForList(answerTypeQuery, Integer.class);
			if (!ansTypeList.isEmpty())
			{
				answerTypeValue = ansTypeList.get(0);
			}			
			answerType.put(qsId, answerTypeValue);
		}		
	
		return answerType;
	}	
	
	 /* Inserting user activity details in the ap.tbluseractivity table
	 * @see com.portal.dao.AssessementDao#insertUserActivity(com.portal.model.UserActivity)
	 */
	public void insertUserActivity(UserActivity userActivity)
	 {
		try {
		 String insertSql = "INSERT INTO ap.tbluseractivity values(?,?,?,?,?)";
		 int status = jdbcTemplate.update(insertSql,new Object[]{userActivity.getUser_id(),userActivity.getCategory_id(),userActivity.getScore(),userActivity.getResult(), userActivity.getAttentedDate()});
		 if(status==1) {
			 System.out.println("User Result updated "+userActivity.getUser_id());
		 }
		}
		catch(Exception e) {
		  e.printStackTrace();	
		}
	 }

	/* (non-Javadoc)
	 * @see com.portal.dao.AssessementDao#getAssessmentTimeLimit(com.portal.form.AssessementBean)
	 */
	@Override
	public String getAssessmentTimeLimit(AssessementBean assessementObj) {

		String timeLimit="";
		String assessementTime = "select time from ap.tblattempt where category_id ='" + assessementObj.getCategoryId() +"' and no_attempt ='"+assessementObj.getNumberOfAttempts()+"'";		
		List<String> timeList = jdbcTemplate.queryForList(assessementTime, String.class);
		if (!timeList.isEmpty())
		{
			timeLimit = timeList.get(0);
		}
		System.out.println("Time Limit :: "+timeLimit);
		return timeLimit;
	}

	@Override
	public int getMaxAttempt(int id) {
		
		int maxAttempts=jdbcTemplate.queryForObject(SQLConst.getMaximumAttemptsOfCategory, new Object[]{id},Integer.class);
		return maxAttempts;
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
	public String getQuestionById(int id) {
		// TODO Auto-generated method stub
		
		String selectedAssessmentQry = "SELECT QUESTION_DESC FROM AP.TBLQUESTION WHERE QUESTION_Id =?";
		//Category categoryObject = (Category) jdbcTemplate.query(selectedAssessmentQry, new CategoryRowMapper());
		String question = jdbcTemplate.queryForObject(selectedAssessmentQry,new Object[]{id}, String.class);
		System.out.println(question);
		
		return question;
	}
}
