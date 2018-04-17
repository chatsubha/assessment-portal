/**
* 
 */
package com.portal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.FormatWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.portal.form.AdminFormBean;
import com.portal.form.ChooseAssessment;
import com.portal.model.AjaxResponseContents;
import com.portal.model.AnswerType;
import com.portal.model.Category;
import com.portal.model.Complexity;
import com.portal.model.Options;
import com.portal.model.Question;
import com.portal.model.UserActivityHistory;
import com.portal.service.AttemptsPerComplexityService;
import com.portal.service.CategoryService;
import com.portal.service.ChooseAssessmentService;
import com.portal.service.FileUploadService;
import com.portal.service.LookupTableService;
import com.portal.service.OptionsService;
import com.portal.service.QuestionService;
import com.portal.service.UserService;

/**
 * @author Rajkumar Kathiresan.
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	/** Category Service */
	@Autowired
	CategoryService categoryService;

	/** Question Service */
	@Autowired
	QuestionService questionService;

	/** Options Service */
	@Autowired
	OptionsService optionsService;

	/** Lookup Table Service */
	@Autowired
	LookupTableService lookUpTableService;

	@Autowired
	AttemptsPerComplexityService attemptsService;

	@Autowired
	private ChooseAssessmentService chooseAssessmentService;
	
	/** Excel file upload service */
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	UserService userservice;

	/**
	 * Used to load the Administrator page. (fetching the categories details onload)
	 * 
	 * @param model
	 *            the Model Map
	 * @return the view page
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showAdmin(Map<String, Object> model,HttpSession session) {
		AdminFormBean adminForm = new AdminFormBean();
		adminForm.setCategories(categoryService.getCategories());
		model.put("adminForm", adminForm);
		int maxId=questionService.fetchMaxId();
		session.setAttribute("maxQuestionId", maxId+1);
		return "admin";
	}
	/**
	 * Used to load the Administrator page. (fetching the categories details onload)
	 * 
	 * @param model
	 *            the Model Map
	 * @return the view page
	 */
	@RequestMapping(value = "/fetchMaxId",method = RequestMethod.POST)
	public String fetchMax(HttpSession session) {
		int maxId=questionService.fetchMaxId();
		session.setAttribute("maxQuestionId", maxId+1);
		return "maxid";
	}

	/**
	 * Load the Questions List by selected category Id.
	 * 
	 * @param categoryId
	 *            the category Id
	 * @return the ModelAndView Object with Question List
	 */
	@RequestMapping(value = "/getQuestionByCategory", method = RequestMethod.POST)
	public ModelAndView getQuestionByCategory(@RequestParam int categoryId) {
		ModelAndView modelAndView = new ModelAndView("questionPage");
		AdminFormBean adminForm = new AdminFormBean();
		adminForm.setQuestionList(questionService.getQuestionByCategoryId(categoryId));
		modelAndView.addObject("adminForm", adminForm);
		return modelAndView;
	}

	/**
	 * Load the Options list by question id.
	 * 
	 * @param questionId
	 *            the question id
	 * @return the ModelAndView Object with Options List.
	 */
	@RequestMapping(value = "/getOptionsByQuestionId", method = RequestMethod.POST)
	public ModelAndView getOptionsByQuestionId(@RequestParam int questionId) {
		ModelAndView modelAndView = new ModelAndView("optionsPage");
		AdminFormBean adminForm = new AdminFormBean();
		adminForm.setOptionsList(optionsService.getOptionsByQuestionId(questionId));
		modelAndView.addObject("adminForm", adminForm);
		return modelAndView;
	}

	/**
	 * Delete the Category details by Category Id.
	 * 
	 * @param categoryId
	 *            the category Id.
	 * @return redirect the action to '/admin'.
	 */
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.POST)
	public ModelAndView deleteCategory(@RequestParam String categoryId) {
		categoryService.deleteCategoryByCategoryId(Integer.parseInt(categoryId));
		return new ModelAndView("redirect:/admin");
	}

	/**
	 * Delete the question by question id.
	 * 
	 * @param questionId
	 *            the question id.
	 * @param categoryId
	 *            the category id.
	 * @return the ModelAndView Object with Category list post delete.
	 */
	@RequestMapping(value = "/deleteQuestion", method = RequestMethod.POST)
	public ModelAndView deleteQuestion(@RequestParam String questionId, @RequestParam String categoryId) {
		ModelAndView modelAndView = new ModelAndView("questionPage");
		questionService.deleteQuestionById(Integer.parseInt(questionId));
		AdminFormBean adminForm = new AdminFormBean();
		adminForm.setQuestionList(questionService.getQuestionByCategoryId(Integer.parseInt(categoryId)));
		modelAndView.addObject("adminForm", adminForm);
		return modelAndView;
	}

	/**
	 * Delete the options by option id.
	 * 
	 * @param optionId
	 *            the option id.
	 * @param questionId
	 *            the question id.
	 * @return the ModelAndView Object with Question list post delete.
	 */
	@RequestMapping(value = "/deleteOption", method = RequestMethod.POST)
	public ModelAndView deleteOption(@RequestParam String optionId, @RequestParam String questionId) {
		ModelAndView modelAndView = new ModelAndView("optionsPage");
		optionsService.deleteOptionsById(Integer.parseInt(optionId));
		AdminFormBean adminForm = new AdminFormBean();
		adminForm.setOptionsList(optionsService.getOptionsByQuestionId(Integer.parseInt(questionId)));
		modelAndView.addObject("adminForm", adminForm);
		return modelAndView;
	}

	/**
	 * Load the category details by category Id.
	 * 
	 * @param categoryId
	 *            the category Id.
	 * @return the category object by using JSON
	 */
	@RequestMapping(value = "/loadCategoryDetails", method = RequestMethod.POST)
	public @ResponseBody Category loadCategoryDetails(@RequestParam String categoryId) {
		Category category = new Category();
		if (null != categoryId && !categoryId.equalsIgnoreCase("")) {
			category = categoryService.getCategoryById(Integer.parseInt(categoryId));
		}
		return category;
	}

	/**
	 * Add the new category.
	 * 
	 * @param formBean
	 *            the AdminFormBean
	 * @return redirect to '/admin' action.
	 */
	@RequestMapping(value = "addCategory", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute AdminFormBean formBean) {
		Category category = formBean.getCategory();
		categoryService.addCategory(category);
		return new ModelAndView("redirect:/admin");
	}

	/**
	 * Edit the category details.
	 * 
	 * @param formBean
	 *            the AdminFormBean
	 * @return redirect to '/admin' action.
	 */
	@RequestMapping(value = "editCategory", method = RequestMethod.POST)
	public ModelAndView editCategory(@ModelAttribute AdminFormBean formBean) {
		Category category = formBean.getCategory();
		categoryService.updateCategory(category);
		return new ModelAndView("redirect:/admin");
	}

	/**
	 * Load the Question details by question Id.
	 * 
	 * @param questionId
	 *            the question Id.
	 * @return the question object by using JSON
	 */
	@RequestMapping(value = "/loadQuestionDetails", method = RequestMethod.POST)
	public @ResponseBody Question loadQuestionDetails(@RequestParam String questionId) {
		Question question = new Question();
		if (null != questionId && !questionId.equalsIgnoreCase("")) {
			question = questionService.getQuestionById(Integer.parseInt(questionId));
		}
		return question;
	}

	/**
	 * Add the new Question.
	 * 
	 * @param formBean
	 *            the AdminFormBean.
	 * @return the ModelAndView with Question List and questionPage view
	 */
	@RequestMapping(value = "addQuestion", method = RequestMethod.POST)
	public ModelAndView addQuestion(@ModelAttribute AdminFormBean formBean) {
		ModelAndView modelAndView = new ModelAndView("questionPage");
		questionService.addQuestion(formBean.getQuestion());
		formBean.setQuestionList(questionService.getQuestionByCategoryId(formBean.getQuestion().getCategoryId()));
		modelAndView.addObject("adminForm", formBean);
		return modelAndView;
	}

	/**
	 * Edit the new Question.
	 * 
	 * @param formBean
	 *            the AdminFormBean.
	 * @return the ModelAndView with Question List and questionPage view
	 */
	@RequestMapping(value = "editQuestion", method = RequestMethod.POST)
	public ModelAndView editQuestion(@ModelAttribute AdminFormBean formBean) {
		ModelAndView modelAndView = new ModelAndView("questionPage");
		questionService.updateQuestion(formBean.getQuestion());
		formBean.setQuestionList(questionService.getQuestionByCategoryId(formBean.getQuestion().getCategoryId()));
		modelAndView.addObject("adminForm", formBean);
		return modelAndView;
	}

	/**
	 * Load the Option details by option Id.
	 * 
	 * @param optionId
	 *            the option Id.
	 * @return the option object by using JSON
	 */
	@RequestMapping(value = "/loadOptionDetails", method = RequestMethod.POST)
	public @ResponseBody Options loadOptionDetails(@RequestParam String optionId) {
		Options options = new Options();
		if (null != optionId && !optionId.equalsIgnoreCase("")) {
			options = optionsService.getOptionsById(Integer.parseInt(optionId));
		}
		return options;
	}

	/**
	 * Add the new Option.
	 * 
	 * @param formBean
	 *            the AdminFormBean.
	 * @return the ModelAndView with Option List and optionsPage view
	 */
	@RequestMapping(value = "addOption", method = RequestMethod.POST)
	public ModelAndView addOption(@ModelAttribute AdminFormBean formBean) {
		ModelAndView modelAndView = new ModelAndView("optionsPage");
		optionsService.addOptions(formBean.getOption());
		formBean.setOptionsList(optionsService.getOptionsByQuestionId(formBean.getOption().getQuestionId()));
		modelAndView.addObject("adminForm", formBean);
		return modelAndView;
	}

	/**
	 * Edit the new Option.
	 * 
	 * @param formBean
	 *            the AdminFormBean.
	 * @return the ModelAndView with Option List and optionsPage view
	 */
	@RequestMapping(value = "editOption", method = RequestMethod.POST)
	public ModelAndView editOption(@ModelAttribute AdminFormBean formBean) {
		ModelAndView modelAndView = new ModelAndView("optionsPage");
		optionsService.updateOptions(formBean.getOption());
		formBean.setOptionsList(optionsService.getOptionsByQuestionId(formBean.getOption().getQuestionId()));
		modelAndView.addObject("adminForm", formBean);
		return modelAndView;
	}

	/**
	 * Load the Model Attribute as ansTypeList
	 * 
	 * @return the Map object
	 */
	@ModelAttribute("ansTypeList")
	public List<AnswerType> getAnsTypeList() {
		return lookUpTableService.getAnswerTypes();
	}

	/**
	 * Load the Model Attribute as ansTypeList
	 * 
	 * @return the Map object
	 */
	@ModelAttribute("complexityList")
	public List<Complexity> getComplexityList() {
		return lookUpTableService.getComplexity();
	}

	/**
	 * Get all the Category details.
	 */
	@RequestMapping(value = "/allCategory", method = RequestMethod.POST)
	public ModelAndView loadallCategory() {
		ModelAndView modelAndView = null;
		List<Category> categoryObjLst = chooseAssessmentService.fetchAssessmentCategory();
		ChooseAssessment assessment = new ChooseAssessment();
		assessment.setCategoryObjList(categoryObjLst);
		modelAndView = new ModelAndView("attemptsQuestionsPage", "assessment", assessment);

		modelAndView.addObject("categoryObjs", categoryObjLst);
		return modelAndView;
	}
	
	/**
	 * Get all users activity.
	 */
	@RequestMapping(value = "/allUsersActivity", method = RequestMethod.POST)
	public ModelAndView loadAllUsersActivity() {
		List<UserActivityHistory> historyList=userservice.getUserAssesmentHistory();
		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView("allUserActivityHistory");
		modelAndView.addObject("assessHistoryList",historyList);
		return modelAndView;
	}

	@RequestMapping(value = "/getAttemptsByCategory", method = RequestMethod.POST)
	public ModelAndView getAttemptsByCategory(@RequestParam String categoryId, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("questionsPerAttempts");
		AdminFormBean adminForm = new AdminFormBean();
		adminForm.setMaxAttemptsList(attemptsService.getMaxAttemptsPerComplexity(Integer.parseInt(categoryId)));
		adminForm.setComplexityHeaderLst(attemptsService.getMaxComplexity());

		request.setAttribute("categoryId", categoryId);
		if (!adminForm.getMaxAttemptsList().isEmpty()) {
			request.setAttribute("checkEntry", adminForm.getMaxAttemptsList().get(0).getChkAttemptEntry());
		}
		modelAndView.addObject("adminForm", adminForm);
		return modelAndView;
	}

	@RequestMapping(value = "/saveQuestionsPerAttempt", method = RequestMethod.POST)
	public @ResponseBody AjaxResponseContents saveQuestionsPerAttempt(@RequestParam String categoryId, @RequestParam Integer totalAttempts,
			@RequestParam String chkEntryId, @ModelAttribute AdminFormBean formBean) {
		AjaxResponseContents ajaxResponse = new AjaxResponseContents();
		try {
			StringBuilder responseBuf = new StringBuilder();
			for(int i=0; i<formBean.getMaxAttemptsList().size(); i++) {
				int totalPercentages=0;
				for(int j=0; j<formBean.getMaxAttemptsList().get(i).getMaxComplexity().size(); j++) {
					totalPercentages +=formBean.getMaxAttemptsList().get(i).getMaxComplexity().get(j).getPercentageValues();
				}
				if(totalPercentages !=100) {
					ajaxResponse.setErrorCode(400);
					responseBuf.append("The Total Number of Percentages in Attempt ("+(i+1) +") should be equals to Hundred \n");
				}
			}
			ajaxResponse.setResponseValue(responseBuf.toString());
			if(ajaxResponse.getErrorCode()!=400) {
			attemptsService.updateQuestionsPerAttempts(formBean.getMaxAttemptsList(), Integer.parseInt(categoryId),
					chkEntryId, totalAttempts);
			ajaxResponse.setErrorCode(200);
			ajaxResponse.setResponseValue("Data has been stored successfully.");
			}
		} catch (Exception ex) {
			ajaxResponse.setErrorCode(400);
			ajaxResponse.setResponseValue("Some thing went wrong. Please try after some time...!!!");
		}
		return ajaxResponse;
	}

	@RequestMapping(value = "/gotoAdmin", method = RequestMethod.POST)
	public ModelAndView gotoAdmin() {
		return new ModelAndView("redirect:/admin");
	}
	
	@RequestMapping(value="/uploadFile",method=RequestMethod.POST)
	public @ResponseBody String uploadfile(@RequestParam(name="name",required=false) String name,@RequestParam("excelFile") MultipartFile file)
	{
		if(!file.isEmpty() ) {
			/**Checking MIME type*/
			if(!file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
					&&!file.getContentType().equals("application/vnd.ms-excel")) {
				return "Invalid file type";
			}
			/**Checking file size*/
			double sizeOfFile=(file.getSize()/1024);
			System.out.println(file.getSize());
			System.out.println((sizeOfFile/1024)+"Mb");
			if((sizeOfFile/1024)>5) {
				return "Invalid file size";
			}
			
			try {
				String rowsAffected="";
				if(file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
					rowsAffected=fileUploadService.processExcelData(file.getInputStream());
				} else if(file.getContentType().equals("application/vnd.ms-excel")) {
					rowsAffected=fileUploadService.processExcelDataForEx2003(file.getInputStream());
				}
				
				if(rowsAffected.equals("0")) {
					return "File upload was not successful";
				}
				else if(!rowsAffected.equals("1")) {
					return rowsAffected;
				} 
				return "you have successfully uplad the file";
				
			}
			catch(Exception e)
			{
				return "you faild the upload the file "+name+e.getMessage();
			}
		}
		else{
			return "you faild to uplaod"+name+"because file is empty";
		}
	}

}
