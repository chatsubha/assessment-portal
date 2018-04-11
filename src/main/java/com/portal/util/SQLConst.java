package com.portal.util;

/**
 * Constant values for SQL Scripts.
 * 
 * @author Rajkumar Kathiresan.
 *
 */
public class SQLConst {

	/** Categories related Queries */
	public static final String findAllCategories = "SELECT * FROM AP.TBLCATEGORY";

	public static final String findCategoryById = "SELECT * FROM AP.TBLCATEGORY WHERE CATEGORY_ID = ?";

	public static final String createCategory = "INSERT INTO AP.TBLCATEGORY (CATEGORY_NAME,DESCRIPTION,CUT_OFF,MAX_ATTEMPT) VALUES(?,?,?,?)";

	public static final String deleteCategoryById = "DELETE FROM AP.TBLCATEGORY WHERE CATEGORY_ID = ?";

	public static final String updateCategoryById = "UPDATE AP.TBLCATEGORY SET CATEGORY_NAME = ?, DESCRIPTION = ?, CUT_OFF = ?, MAX_ATTEMPT = ? WHERE CATEGORY_ID = ?";

	/** Questions related Queries */

	public static final String findAllQuestions = "SELECT * FROM AP.TBLQUESTION";

	public static final String findQuestionById = "SELECT * FROM AP.TBLQUESTION WHERE QUESTION_ID = ?";

	public static final String findQuestionByCategoryId = "SELECT * FROM AP.TBLQUESTION WHERE CATEGORY_ID = ?";

	public static final String createQuestion = "INSERT INTO AP.TBLQUESTION (QUESTION_DESC,ANSWER_TYPE_ID,CATEGORY_ID,COMPLEXITY) VALUES(?,?,?,?)";

	public static final String deleteQuestionById = "DELETE FROM AP.TBLQUESTION WHERE QUESTION_ID = ?";

	public static final String deleteQuestionByCategoryId = "DELETE FROM AP.TBLQUESTION WHERE CATEGORY_ID = ?";

	public static final String updateQuestionById = "UPDATE AP.TBLQUESTION SET QUESTION_DESC = ?, ANSWER_TYPE_ID = ?,CATEGORY_ID = ?, COMPLEXITY = ? WHERE QUESTION_ID = ?";

	/** Options related Queries */

	public static final String findAllOptions = "SELECT * FROM AP.TBLOPTIONS";

	public static final String findOptionsById = "SELECT * FROM AP.TBLOPTIONS WHERE OPTION_ID = ?";

	public static final String findOptionsByQuestionId = "SELECT * FROM AP.TBLOPTIONS WHERE QUESTION_ID = ?";

	public static final String createOptions = "INSERT INTO AP.TBLOPTIONS (QUESTION_ID,OPTION_DESC,CORRECT_ANSWER) VALUES(?,?,?)";

	public static final String deleteOptionsById = "DELETE FROM AP.TBLOPTIONS WHERE OPTION_ID = ?";

	public static final String deleteOptionsByQuestionId = "DELETE FROM AP.TBLOPTIONS WHERE QUESTION_ID = ?";

	public static final String updateOptionsById = "UPDATE AP.TBLOPTIONS SET QUESTION_ID = ?, OPTION_DESC = ?,CORRECT_ANSWER = ? WHERE OPTION_ID = ?";
	
	public static final String getNotApplicableAssessements = "select count(activity.category_id) AttemptsTaken from AP.tbluseractivity activity where activity.user_id= ? and activity.category_id = ? ";
	
	public static final String getMaximumAttemptsPerCategory = " select max(no_attempt) MaximumAttempts, category_id from AP.tblattempt group by category_id ";
	
	public static final String getMaximumAttemptsOfCategory = " select max(no_attempt) MaximumAttempts from AP.tblattempt where category_id=? group by category_id ";
	
	public static final String updateQuestionsPerCategory = "UPDATE AP.tblattempt SET NO_QUESTION= ?, TIME= ? WHERE NO_ATTEMPT = ? AND CATEGORY_ID= ? ";
	
	public static final String insertQuestionsPerCategory = "INSERT INTO AP.tblattempt (CATEGORY_ID, NO_ATTEMPT, NO_QUESTION , time) VALUES (?, ? , ?, ?)";
	
	public static final String updateComplexityPerAttempts = "update ap.tblcomplexitylookup set percentage = ? where no_attempt= ? and complexity = ? and category_id=? ";
	
	public static final String insertComplexityPerAttempts = "INSERT INTO ap.tblcomplexitylookup(CATEGORY_ID, COMPLEXITY, NO_ATTEMPT, PERCENTAGE) VALUES (?,?,?,?)";
	
	/* Complexity max */
	public static final String complexityMax = "select count(*) as complexity from ap.tblcomplexity";
	public static final String deleteComplexityByCategoryId = "DELETE FROM AP.TBLCOMPLEXITYLOOKUP WHERE CATEGORY_ID = ?";
	public static final String deleteAttemptsByCategoryId = "DELETE FROM AP.TBLATTEMPT WHERE CATEGORY_ID = ?";
	
	/** Look up table related queries */
	public static final String findAllAnswerTypes = "SELECT * FROM AP.TBLANSWERTYPELOOKUP";
	public static final String findAllComplexityTypes = "SELECT * FROM AP.TBLCOMPLEXITY";
	

	/*Security Question - Registration*/
	public static final String securityQuestions = "Select securityquestnumber, securityquestions from ap.tblsecurityquestion";
	
	public static final String createUser = "Insert into AP.tblusers (user_name, password, user_role, email, securityQuestNumber, securityAnswer) VALUES (?,?,?,?,?,?)";
	
	public static final String compareUserName = "Select user_name from ap.tblusers where user_name = ?";
	
	
	/*Change Password*/

	public static final String updatePassword = "UPDATE AP.TBLUSERS SET PASSWORD = ? WHERE USER_NAME = ?";
	
	public static final String getUserAssessmentHistory="SELECT user.user_id,userdata.user_name username,user.category_id,user.score,user.result,cat.category_name, user.create_ts last_attempt_date ,\r\n" + 
			"(select count(a.category_id) from ap.tbluseractivity a where user.user_id=a.user_id and user.category_id=a.category_id) num_of_attempts  \r\n" + 
			"FROM ap.tbluseractivity user inner join ap.tblcategory cat\r\n" + 
			" on user.category_id=cat.category_id inner join  ap.tblusers userdata on user.user_id=userdata.user_id where user.user_id=? group by user.user_id,user.category_id, user.create_ts having \r\n" + 
			" user.create_ts=(select max(create_ts) from ap.tbluseractivity where user_id=user.user_id and category_id=user.category_id); ";
	
	public static final String getAllUserAssessmentHistory="SELECT user.user_id,userdata.user_name username,user.category_id,user.score,user.result,cat.category_name, user.create_ts last_attempt_date ,\r\n" + 
			"(select count(a.category_id) from ap.tbluseractivity a where user.user_id=a.user_id and user.category_id=a.category_id) num_of_attempts  \r\n" + 
			"FROM ap.tbluseractivity user inner join ap.tblcategory cat\r\n" + 
			" on user.category_id=cat.category_id inner join  ap.tblusers userdata on user.user_id=userdata.user_id  group by user.user_id,user.category_id, user.create_ts having \r\n" + 
			" user.create_ts=(select max(create_ts) from ap.tbluseractivity where user_id=user.user_id and category_id=user.category_id); ";
	
	public static final String getNumberOfQuestion = "select question_id from ap.TBLQUESTION where question_desc=?";

}
