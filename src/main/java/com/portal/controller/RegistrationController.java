package com.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.portal.form.RegistrationFormBean;
import com.portal.model.SecurityQuestions;
import com.portal.model.User;
import com.portal.service.RegistrationService;
import com.portal.util.EncryptDecrypt;

@Controller
@SessionAttributes({"registrationBean"})
public class RegistrationController {

@Autowired
private RegistrationService registrationService;

@RequestMapping(value = "/registrationForm")
public ModelAndView launchRegistration(@ModelAttribute("userForm") User user,ModelMap model,@RequestParam(name="user_name",required=false) String name,@RequestParam(name="password",required=false) String password,@RequestParam(name="error",required=false) String error)
{
	ModelAndView modelAndView = new ModelAndView();
	RegistrationFormBean registrationBean = new RegistrationFormBean();
	String viewName = "registrationPg";
//	List<SecurityQuestions> securityQuesList = registrationService.getSecurityQuestionLst();
//	registrationBean.setSecurityQuestions(securityQuesList);
//	modelAndView.addObject("registrationBean",registrationBean.getSecurityQuestions());
	modelAndView.setViewName(viewName);
	if(error!=null&&error.equals("s")) {
		model.put("user_name", name);
		model.put("password",password );
		model.put("errorMessage", "Not a registered user.Please register with your cognizant id");
	}
	return modelAndView;
}

@RequestMapping(value="/register", method = RequestMethod.POST)
public ModelAndView registerUser(@Valid @ModelAttribute("userForm") User user,BindingResult results, Model model)
{
	ModelAndView modelAndView = new ModelAndView();
	String viewName = "login";
	
	modelAndView.setViewName("registrationPg");
	if((user.getUser_name().length()>0) && (user.getUser_name()!=null) && ((user.getPassword().length()>0) && (user.getPassword()!=null)) &&
	   (user.getEmail().length()>0) && (user.getEmail()!=null) 
	   /*&& ((user.getUserSecurtiyQuest().length()>0) && (user.getUserSecurtiyQuest()!=null)) && 
	   ((user.getSecurityAns().length()>0) && (user.getSecurityAns()!=null))
	   */
	   )
	{
/*		userNameFlag = registrationService.validateUser(user);

	if(userNameFlag)
	{
		model.addAttribute("invalidName", "User Name already Exist.");
	}
	else
	{*/
		user.setUser_name(user.getUser_name());
		user.setPassword(EncryptDecrypt.encrypt(user.getPassword())); 
		user.setRole("user");
		user.setEmail(user.getEmail());
//		user.setUserSecurtiyQuest(user.getUserSecurtiyQuest());
//		user.setSecurityAns(user.getSecurityAns());
		modelAndView.addObject("userForm", user);
		registrationService.insertUserDetails(user);
		model.addAttribute("successMsg", "Registration Successful!");
		modelAndView.setViewName(viewName);
	}
	return modelAndView;
}


@RequestMapping(value = "/checkNameAvailability", method = RequestMethod.POST)
public @ResponseBody String checkAvailability(Model model,@RequestParam String username)
{	
	String result = "";
	
	if (registrationService.validateUser(username))
	{
		result = "User name is already taken";	
	}

	return result;
	
}

}
