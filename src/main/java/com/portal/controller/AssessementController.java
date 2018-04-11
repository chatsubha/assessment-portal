package com.portal.controller;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.el.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.portal.assessment.exception.AssessmentException;
import com.portal.form.AssessementBean;
import com.portal.model.UserActivity;
import com.portal.service.AssessementService;

@Controller
@SessionAttributes({ "assessementObj" })
public class AssessementController
{
	Logger logger = LoggerFactory.getLogger(AssessementController.class);
	@Autowired
	private AssessementService assessementService;	

	/**
	 * @return 
	 */
	@ModelAttribute("assessementObj")
	public AssessementBean assessementObj()
	{
		return new AssessementBean();
	}
	

	/**
	 * Load the Questions based on the Previous and Next functionality.
	 * @param assessementObj
	 * @param action
	 * @return the ModelAndView Object with AssessementBean List.
	 */
	@RequestMapping("/assessementQs")	
	public ModelAndView assessementQuestions(@ModelAttribute("assessementObj") AssessementBean assessementObj, 
			@ModelAttribute("action") String action, HttpServletRequest request, HttpSession session,ModelMap model)
	{
				
		if(request.getHeader("X-Requested-With")!=null&&request.getHeader("X-Requested-With").equals("XMLHttpRequest"))
		{
		 	model.put("isAjax", "yes");
		}
		else
		{
			model.put("isAjax", "no");
		}
		if("Next".equals(action)) {
			
			int questionNumber = assessementObj.getQuestionNumber();

			HashMap<Integer, String[]> userSelectedAnswersMap = assessementObj.getUserSelectedAnswersMap();

			userSelectedAnswersMap.put(questionNumber, assessementObj.getUserAnswers());
			assessementObj.setUserAnsMap(userSelectedAnswersMap);

			assessementObj.setQuestionNumber(questionNumber + 1);
			assessementObj.setUserAnswers(assessementObj.getUserAnsMap().get(questionNumber + 1));

			assessementObj = getTypeOfOptions(assessementObj, assessementObj.getQuestionNumber());

			Map<String, List<String>> questAns = new HashMap<String, List<String>>();
			questAns.put(assessementObj.getQsMap().get(assessementObj.getQuestionNumber()),
					assessementObj.getAsMap().get(assessementObj.getQuestionNumber()));
			assessementObj.setQuestOption(questAns);
			return new ModelAndView("assessement", "assessementObj", assessementObj);
			
		}
		else if("Previous".equals(action)) {

			int questionNo = assessementObj.getQuestionNumber();

			HashMap<Integer, String[]> userSelectedAnswersMap = assessementObj.getUserSelectedAnswersMap();
			userSelectedAnswersMap.put(questionNo, assessementObj.getUserAnswers());
			assessementObj.setUserAnsMap(userSelectedAnswersMap);

			assessementObj.setQuestionNumber(questionNo - 1);
			assessementObj.setUserAnswers(assessementObj.getUserAnsMap().get(questionNo - 1));

			assessementObj = getTypeOfOptions(assessementObj, assessementObj.getQuestionNumber());

			Map<String, List<String>> questAns = new HashMap<String, List<String>>();
			questAns.put(assessementObj.getQsMap().get(assessementObj.getQuestionNumber()),
					assessementObj.getAsMap().get(assessementObj.getQuestionNumber()));
			assessementObj.setQuestOption(questAns);

			return new ModelAndView("assessement", "assessementObj", assessementObj);
		}	
		else if ("Finish".equals(action)) {

 			String countdownTime = request.getParameter("countdown");
			assessementObj.setCourseTime(countdownTime);
			System.out.println("countdownTime Parameter ::: " + countdownTime);

			int questionNumber = assessementObj.getQuestionNumber();
			HashMap<Integer, String[]> userSelectedAnswersMap = assessementObj.getUserSelectedAnswersMap();
			userSelectedAnswersMap.put(questionNumber, assessementObj.getUserAnswers());
			assessementObj.setUserAnsMap(userSelectedAnswersMap);
			return new ModelAndView("confirmation", "assessementObj", assessementObj);

		}
		else {			
			model.put("isFirstPage","y");
			assessementObj.setUserAnsMap(null);
			assessementObj.getUserSelectedAnswersMap().clear();
			Integer category_Id, catCutOff;
			Integer userId = (Integer)session.getAttribute("userId");		
			String categoryId = request.getParameter("categoryId");
			String categoryCutOff = request.getParameter("categoryCutOff");			
			
			if(categoryId == null ||categoryCutOff ==null) {
				category_Id = assessementObj.getCategoryId();
				catCutOff = assessementObj.getCategoryCutOff();
			}
			else {
				category_Id = Integer.parseInt(request.getParameter("categoryId"));
				catCutOff = Integer.parseInt(request.getParameter("categoryCutOff"));
				assessementObj.setCategoryId(category_Id);
				assessementObj.setCategoryCutOff(catCutOff);
			}			
			assessementObj.setUserId(userId);
			int userAttempts=assessementService.getAttempts(assessementObj);
			int maxAttempts=assessementService.getMaxAttempt(assessementObj.getCategoryId());
			if(maxAttempts>userAttempts) {
				assessementObj.setPendingAttempts(maxAttempts-userAttempts);
				assessementObj.setMaxAttempts(maxAttempts);
				assessementObj.setCategoryName(assessementService.fetchAssessmentCategory(assessementObj.getCategoryId()).getCategoryName());
			}
			else
			{
				logger.error("User attempts over "+userAttempts);
				model.put("errorMsg", "Sorry!!! You cannot launch this assessment, As you have reached maximum number of attempts.Please contact Admin for further updates.");
				return new ModelAndView("AssessmentError");
			}
			assessementObj = initializeQuestions(assessementObj);
		}
		
		if(assessementObj.getQuestionList().size() == 0 ) {
			System.out.println("Questions not found!!!");
			model.put("errorMsg","Some Error has occurred, Please try again later...");
			return new ModelAndView("AssessmentError");
		}else {
			return new ModelAndView("assessement","assessementObj",assessementObj);
		}
		
	}
	
	/**
	 * Get the question.
	 * 
	 * @param questionId
	 *            the question Id. 
	 */
	@RequestMapping(value = "/getQuestionById", method = RequestMethod.POST)
	private @ResponseBody String getQuestionDetails(@ModelAttribute("assessementObj") AssessementBean assessementObj,@RequestParam("id") String id) {
		
		if(id!=null && !id.equals("")) {
			try {
			    logger.error("Correct answre map size"+assessementObj.getCorrectAnswerMap());
				return assessementService.getQuestionById(Integer.parseInt(id))+":"+assessementObj.getCorrectAnswerMap().get(Integer.parseInt(id));
			} catch ( NumberFormatException e) {
			
				return "";
			}
			
		} else {
			return "";
		}
		
	}
	/**
	 * Get the total questions.
	 * 
	 * @param questionId
	 *            the question Id. 
	 */
	@RequestMapping(value = "/getTotalQuestions", method = RequestMethod.POST)
	public @ResponseBody AssessementBean getTotalQuestions(@ModelAttribute("assessementObj") AssessementBean assessementObj) {		
		
		int questionNo = assessementObj.getQuestionNumber();
				
		HashMap<Integer, String[]> userSelectedAnswersMap = assessementObj.getUserSelectedAnswersMap();
		userSelectedAnswersMap.put(questionNo, assessementObj.getUserAnswers());			
		assessementObj.setUserAnsMap(userSelectedAnswersMap);		
		int unselectedQuestion = getUnselectedQuestions(assessementObj);
		
		assessementObj.setUnselectedQuestions(unselectedQuestion);
		return assessementObj;
	}
	
	/**
	 * Display questions summary in the Confirmation page
	 * 
	 * If Timer times out it goes to result page directly.
	 * 
	 */
	@RequestMapping(value = "/finishAssessement", method = RequestMethod.POST)
	public ModelAndView finishAssessement(@ModelAttribute("assessementObj") AssessementBean assessementObj,@ModelAttribute("action") String action, HttpServletRequest request,ModelMap model) {
		
		if(request.getHeader("X-Requested-With")!=null&&request.getHeader("X-Requested-With").equals("XMLHttpRequest"))
		{
		 	model.put("isAjax", "yes");
		}
		else
		{
			model.put("isAjax", "no");
		}
		if ("Go Back".equals(action) || "BTEQL".equals(action)) {
			
			int questionNo = assessementObj.getQuestionNumber();
			HashMap<Integer, String[]> userSelectedAnswersMap = assessementObj.getUserSelectedAnswersMap();
			userSelectedAnswersMap.put(questionNo, assessementObj.getUserAnswers());
			assessementObj.setUserAnsMap(userSelectedAnswersMap);
			assessementObj.setQuestionNumber(questionNo);
			assessementObj.setUserAnswers(assessementObj.getUserAnsMap().get(questionNo));
			assessementObj = getTypeOfOptions(assessementObj, assessementObj.getQuestionNumber());
			Map<String, List<String>> questAns = new HashMap<String, List<String>>();
			questAns.put(assessementObj.getQsMap().get(assessementObj.getQuestionNumber()),
					assessementObj.getAsMap().get(assessementObj.getQuestionNumber()));
			assessementObj.setQuestOption(questAns);
			System.out.println("question number..."+assessementObj.getQuestionNumber());
			return new ModelAndView("assessement", "assessementObj", assessementObj);
			
		} else {
			int questionNo = assessementObj.getQuestionNumber();
			HashMap<Integer, String[]> userSelectedAnswersMap = assessementObj.getUserSelectedAnswersMap();
			userSelectedAnswersMap.put(questionNo, assessementObj.getUserAnswers());
			assessementObj.setUserAnsMap(userSelectedAnswersMap);
			try {
				assessementObj = calculateResults(assessementService, assessementObj);
			}
			catch(AssessmentException e) {
				model.put("error", "Please attempt all the question");
				return new ModelAndView("confirmation", "assessementObj", assessementObj);
			}
			return new ModelAndView("result", "assessementObj", assessementObj);
		}
		
	}


	/**
	 * Get question Id based on sequence number
	 * @param orderResult
	 * @param userAnsMap
	 * @return 
	 */
	private Map<Integer, String> getCorrectAnswerWIthId(Map<Integer, Integer> orderResult, HashMap<Integer, String[]> userAnsMap) {

		Map<Integer, String> userSelectedOrderMap = new HashMap<Integer, String>();
		for (Map.Entry<Integer, Integer> orderMap : orderResult.entrySet()) {
			for (Map.Entry<Integer, String[]> userSelected : userAnsMap.entrySet()) {

				if (orderMap.getKey().intValue() == userSelected.getKey().intValue()) {
					String strVal = "";
					if (userSelected.getValue() != null) {
						if (userSelected.getValue().toString().length() != 0)
							strVal = convertArrayToString(userSelected.getValue(), ",");
					}

					userSelectedOrderMap.put(orderMap.getValue().intValue(), strVal);
				}

			}
		}
		return userSelectedOrderMap;
	}


	/**
	 * Preparing question page If the first question entry occur  
	 * @param question
	 * @return
	 */
	private AssessementBean initializeQuestions(AssessementBean assessementObj) {		

		int startquestionNumber = 1;
		int numberOfAttempts = assessementService.getAttempts(assessementObj) + 1;
		System.out.println("Number of Attempts trying ::: " + numberOfAttempts);
		// TODO Auto-generated method stub
		LinkedHashMap<Integer, String> questionList = assessementService.initializeQuestions(assessementObj,
				numberOfAttempts);

		assessementObj.setQuestionList(questionList);

		if (questionList.size() != 0) {

			LinkedHashMap<Integer, List<String>> answerList = assessementService.getAnswer(assessementObj);
			LinkedHashMap<Integer, Integer> ansTypeList = assessementService.getTypeOfAnswer(assessementObj);
			LinkedHashMap<String, List<String>> questAns = new LinkedHashMap<String, List<String>>();

			assessementObj = orderQuestion(assessementObj, questionList, answerList, ansTypeList);
			assessementObj.setNumberOfAttempts(numberOfAttempts);
			assessementObj.setQuestionNumber(startquestionNumber);
			assessementObj = getTypeOfOptions(assessementObj, startquestionNumber);
			questAns.put(assessementObj.getQsMap().get(startquestionNumber),
					assessementObj.getAsMap().get(startquestionNumber));
			assessementObj.setQuestOption(questAns);

			String courseTime = assessementService.getAssessmentTime(assessementObj);

			assessementObj.setCourseTime(courseTime);
		}

		return assessementObj;
	}
	
	/**
	 * Questions list taken from database with random orders 
	 * Populating the question and options maps in UI with sequence number order.   
	 * @param assessementObj
	 * @param questionList
	 * @param answerList
	 * @param ansTypeList 
	 */
	private AssessementBean orderQuestion(AssessementBean assessementObj, LinkedHashMap<Integer, String> questionList,
			LinkedHashMap<Integer, List<String>> answerList, LinkedHashMap<Integer, Integer> ansTypeList) {

		int qsCount = 1;
		LinkedHashMap<Integer, String> orderedQuestionList = new LinkedHashMap<Integer, String>();
		HashMap<Integer, Integer> orderedResult = new HashMap<Integer, Integer>();

		for (Map.Entry<Integer, String> orderedQs : questionList.entrySet()) {
			orderedQuestionList.put(qsCount, orderedQs.getValue());
			orderedResult.put(qsCount, (Integer) orderedQs.getKey());
			assessementObj.setOrderResult(orderedResult);
			qsCount++;
		}

		int asCount = 1;
		LinkedHashMap<Integer, List<String>> orderedAnswerList = new LinkedHashMap<Integer, List<String>>();
		for (Map.Entry<Integer, List<String>> orderedAs : answerList.entrySet()) {
			orderedAnswerList.put(asCount, orderedAs.getValue());
			asCount++;
		}

		assessementObj.setAsMap(orderedAnswerList);
		assessementObj.setQsMap(orderedQuestionList);
		assessementObj.setTypeOfAnsMap(ansTypeList);

		return assessementObj;
	}

	
	/**
	 * @param assessementObj
	 * This method is used to get type of options
	 * Returning single value options or multi value options as per questionId
	 */
	private AssessementBean getTypeOfOptions(AssessementBean assessementObj, Integer qsNumber) {
		// TODO Auto-generated method stub
		
		for (Map.Entry<Integer, Integer> ansType : assessementObj.getOrderResult().entrySet()) {
			if(ansType.getKey()==qsNumber) {
				if (assessementObj.getTypeOfAnsMap().get(ansType.getValue()) == 1)
				{
					assessementObj.setAnswerType("SingleOption");
				}
				if (assessementObj.getTypeOfAnsMap().get(ansType.getValue()) == 2)
				{
					assessementObj.setAnswerType("MultiOption");
				}
			}
		}
		return assessementObj;
	}
	

	/**
	 * This method for results calculations
	 * Matching the question id of user selected values with actual answers
	 * @param assessementService
	 * @param assessementObj 
	 * @throws AssessmentException 
	 * 
	 */
	private AssessementBean calculateResults(AssessementService assessementService, AssessementBean assessementObj) throws AssessmentException {
		// TODO Auto-generated method stub 
		
		double totalCorrect = 0.0;
		int assessementPercentage = 0;
		Map<Integer, String> userSelectedOrderMap = null;

		LinkedHashMap<Integer, String> correctAnsMap = assessementService.getCorrectOption(assessementObj);
		assessementObj.setCorrectAnswerMap(correctAnsMap);

		userSelectedOrderMap = getCorrectAnswerWIthId(assessementObj.getOrderResult(), assessementObj.getUserAnsMap());

		int countQs = 0, totalQsCount = 0, selectedQsCount = 0;
		Map<Integer, String> summaryMap = new HashMap<Integer, String>();
		for (Map.Entry<Integer, String> correctResult : assessementObj.getCorrectAnswerMap().entrySet()) {
			for (Map.Entry<Integer, String> userSelected : userSelectedOrderMap.entrySet()) {
				if (correctResult.getKey() == userSelected.getKey()) {
					countQs++;
					if (userSelected.getValue() != null) {
						if (correctResult.getValue().equals(userSelected.getValue())) {
							totalCorrect++;
							summaryMap.put(countQs, "Correct");
						} else if (userSelected.getValue().toString().length() == 0) {
							throw new AssessmentException("Please choose the answer");
//							summaryMap.put(countQs, "Unanswered");
						} else
							summaryMap.put(countQs, "Wrong:"+userSelected.getKey());
					} else
						summaryMap.put(countQs, "Unanswered");
				}
			}
		}

		totalQsCount = assessementObj.getTotalQuestions();
		selectedQsCount = summaryMap.size();

		// Summary Page Unanswered question display, If Timer exceeds time.
		if (totalQsCount != selectedQsCount) {
			while (selectedQsCount < totalQsCount) {
				selectedQsCount++;
				summaryMap.put(selectedQsCount, "Unanswered");
			}
		}
		assessementObj.setResultPageMap(summaryMap);

		assessementPercentage = (int) ((totalCorrect / assessementObj.getTotalQuestions()) * 100);

		assessementObj.setAssessementPercentage(Math.round(assessementPercentage));
		System.out.println(assessementObj.getAssessementPercentage());
		String result = "Pass";
		if (assessementPercentage < assessementObj.getCategoryCutOff())
			result = "Fail";

		UserActivity userActivity = new UserActivity();
		userActivity.setResult(result);
		userActivity.setScore(assessementPercentage);
		userActivity.setUser_id(assessementObj.getUserId());
		userActivity.setCategory_id(assessementObj.getCategoryId());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		userActivity.setAttentedDate(dateFormat.format(date));
		assessementService.insertResult(userActivity);

		return assessementObj;
	}

	/**
	 * @param userAnsMap
	 * @return
	 */
	private int getUnselectedQuestions(@ModelAttribute("assessementObj") AssessementBean assessementObj) {
		
		int answeredQs =0;
		int totalUnansweredQ = 0;
		
		HashMap<Integer, String[]> userAnsMap = assessementObj.getUserAnsMap();		
		
		
		for (Entry<Integer, String[]> userSelectedMap :userAnsMap.entrySet())
		{		
			String ans[];						
			if(userSelectedMap.getValue() !=null) {
 				ans =userSelectedMap.getValue(); 				
 				if(ans!=null && ans.length > 0) {
 					answeredQs=answeredQs+1;
 				} 				
			}
										
		}
		
		totalUnansweredQ=assessementObj.getTotalQuestions()-answeredQs;
		return totalUnansweredQ;
	}
	
	private String convertArrayToString(String[] arr, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<arr.length;i++) {
			sb.append(arr[i]).append(delimiter);
		}
		if(sb.toString().length()!=0) {
			return sb.substring(0, sb.length() - 1);
		}
		return "";

	}


	@RequestMapping("/Exit")
	public String exitResultPage(@ModelAttribute AssessementBean assessementObj, SessionStatus status, WebRequest request, HttpSession session) {
		  status.setComplete();
		  assessementObj.setUserAnsMap(null);
		  session.setAttribute("assessementObj", null);		  		  
		  request.removeAttribute("assessementObj", WebRequest.SCOPE_SESSION);		  

		  return "redirect:/chooseAssessment";
	}
	
	
	
}
