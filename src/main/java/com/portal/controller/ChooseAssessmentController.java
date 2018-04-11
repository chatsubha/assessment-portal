package com.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.portal.form.ChooseAssessment;
import com.portal.model.Category;
import com.portal.model.UserActivityHistory;
import com.portal.service.ChooseAssessmentService;
import com.portal.service.UserService;

@Controller
/* @SessionAttributes({"assessmentLst"}) */
public class ChooseAssessmentController {

	@Autowired
	private ChooseAssessmentService chooseAssessmentService;
	@Autowired
	UserService userservice;
	
	private List<Category> categoryObjs;

	/**
	 * @return the categoryObjs
	 */
	public List<Category> getCategoryObjs() {
		return categoryObjs;
	}

	/**
	 * @param categoryObjs
	 *            the categoryObjs to set
	 */
	public void setCategoryObjs(List<Category> categoryObjs) {
		this.categoryObjs = categoryObjs;
	}


	@RequestMapping("/chooseAssessment")
	public ModelAndView chooseAssessment(HttpSession session,ModelMap model) {
		List<Category> categoryObjLst = chooseAssessmentService.fetchAssessmentCategory();
		List<Integer> inValidCategoryLst= chooseAssessmentService.getNotApplicableCategories((Integer)session.getAttribute("userId"));
		List<Integer>  emptyQuestCategoryLst = chooseAssessmentService.getEmptyQuestionsCategories();
		System.out.println("invalid Categories="+inValidCategoryLst);
		System.out.println("Controller size : "+categoryObjLst.size());
		ChooseAssessment assessment = new ChooseAssessment();
		assessment.setCategoryObjList(categoryObjLst);
		ModelAndView modelAndView = new ModelAndView("chooseAssessment", "assessment", assessment);
		modelAndView.addObject("categoryObjs",categoryObjLst);
		modelAndView.addObject("inValidCategoryIds", inValidCategoryLst);
		modelAndView.addObject("emptyQuestCategories", emptyQuestCategoryLst);
		if(session.getAttribute("userId")!=null)
		{
			List<UserActivityHistory> historyList=userservice.getUserAssesmentHistory(String.valueOf(session.getAttribute("userId")));
			model.put("assessHistoryList",historyList);
		}
		else
		{
			modelAndView.setViewName("login");
		}
		return modelAndView;
		/*
		 * List<String> assessmentTopics = new ArrayList<String>(); for(Category c:
		 * categoryObjLst) { assessmentTopics.add(c.getCategory_Name()); }
		 * modelAndView.addObject("chooseTopics", assessmentTopics);
		 * modelAndView.setViewName(viewName); return modelAndView;
		 */
	}
/*
	@RequestMapping("/launch")
	public ModelAndView launchAssessment(@ModelAttribute ("assessment") ChooseAssessment assessment) {		
		System.out.println(assessment.getChosenAssessments());
		//System.out.println(assessment.getCategoryObjList());		
		Category selectedCategoryObj = chooseAssessmentService.getSelectedCategoryObj(assessment.getChosenAssessments());
		System.out.println(selectedCategoryObj);
		ModelAndView modelAndView = new ModelAndView();				
		String viewName = "launchAssessment";
		modelAndView.setViewName(viewName);
		return modelAndView;
	}*/

}
