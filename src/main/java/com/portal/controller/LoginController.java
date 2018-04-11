package com.portal.controller;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.portal.model.User;
import com.portal.service.UserService;
import com.portal.service.impl.UserServiceImpl;
import com.portal.validator.UserLogin;

@Controller
@SessionAttributes("username")

public class LoginController {

	@Autowired
	UserService userservice;
    
	@Autowired
    User user;
	
	@Autowired
	private UserLogin userLoginValidator;

	@RequestMapping(value="/")
	public String showLoginPage(ModelMap model){
		return "login";
	}
	
	@RequestMapping(value="/login")
	public String loadLoginPage(HttpSession session,ModelMap model,@RequestParam(name="error",required=false) String error){
		
		System.out.println("Error"+ error);
		if(error!=null&&error.equals("s")) {
			model.put("errorMessage", "Please try after sometime");
		}
		else if(error!=null&&error.equals("t")) {
			model.put("errorMessage", "Invalid Credentials");
		}
        if(session.getAttribute("userId")!=null) {
        	return "redirectAssessment";
        }
		return "login";
	}
	
	
	@RequestMapping(value="/assessment", method = RequestMethod.POST)
	public ModelAndView showWelcomePage(ModelMap model, HttpSession session, @RequestParam String name, @RequestParam String password){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");

//		boolean isValidUser = false;
//		isValidUser = userservice.validateUser(name, password);
//
//		if (!isValidUser) {
//			model.put("errorMessage", "Invalid Credentials");
//			return modelAndView;
//		}

		String role = null;
        int uid=0; 
        if(session.getAttribute("userId")!=null) {
        	name = session.getAttribute("username").toString();
        	role = session.getAttribute("userRole").toString();
        	uid  = Integer.parseInt(session.getAttribute("userId").toString());
        	model.put("username", name);
    		model.put("password", password);
    		
    		if(role != null && role.equals("admin"))
    		{
    			modelAndView.setViewName("redirect:/admin");								
    		}
    		else
    		{
    			
    			modelAndView.setViewName("redirect:/chooseAssessment");
//    			modelAndView.setViewName("redirect:/assessmentHistory");
    		}
        	
        }
        else if(name!=null && !name.equals("")) {
        	
        	 
        		model.put("username", name);
        		model.put("password", password);
                
		role = userservice.checkRole(name);
		if (role==null) {
			modelAndView.setViewName("redirectReg");
			return modelAndView;
		}
		
		uid =userservice.getUid(name);
        
		session.setAttribute("username", name);
	    session.setAttribute("userId", uid);
	    session.setAttribute("userRole", role);

		if(role != null && role.equals("admin"))
		{
			modelAndView.setViewName("redirect:/admin");								
		}
		else
		{
			
			modelAndView.setViewName("redirect:/chooseAssessment");
//			modelAndView.setViewName("redirect:/assessmentHistory");
		}
	}
	else {
		modelAndView.setViewName("redirect:/login?error=t");
	}

		return modelAndView;
	}

	

	@RequestMapping(value = "/forgetPassword")
	public ModelAndView forgetPassword(@ModelAttribute("userForm") User user)
	{
		
		ModelAndView modelAndView = new ModelAndView();
		String viewName = "forgotpassword";
		System.out.println("user.getUser_Id()");
		modelAndView.addObject("userForm", user);
		modelAndView.setViewName(viewName);
		return modelAndView;
	}
	
	@RequestMapping(value = "/fetchUserQuest")
	public ModelAndView password(@ModelAttribute("userForm") User user,Model model)
	{
		ModelAndView modelAndView = new ModelAndView();
		
		String securityQuestions = userservice.fetchUserQuest(user.getUser_name());
		
		
		System.out.println("qstn"+securityQuestions);
		//String securityQuestions = "What is your hobby?" ;
		if(securityQuestions!=null){
		user.setUserSecurtiyQuest(securityQuestions);
		modelAndView.addObject("userQuestion", user.getUserSecurtiyQuest());
		}else{
			model.addAttribute("invalidQuest", "Unable to fetch the Question! User not available.");
		}
		modelAndView.setViewName("forgotpassword");
		modelAndView.addObject("userForm", user);
		return modelAndView;
	}

	@RequestMapping(value = "/fetchPswd")
	public ModelAndView fetchPassword(@ModelAttribute("userForm") User user, HttpSession session, BindingResult results,Model model)
	{
		
		ModelAndView modelAndView = new ModelAndView();
		String securityQuestions = userservice.fetchUserQuest(user.getUser_name());
		user.setUserSecurtiyQuest(securityQuestions);
		
		String secAns = userservice.fetchUserAns(user.getUser_name());
		System.out.println("security ans:"+secAns);
		
		model.addAttribute("secAns", secAns);
		
		userLoginValidator.validate(user, results);
		
		if (results.hasErrors()) {
			modelAndView.setViewName("forgotpassword");
			modelAndView.addObject("userForm", user);
			model.addAttribute("invalidAns", "Invalid answer!");
			user.setValidAndFlg(true);
			return modelAndView;
		} else {
			// send mail
			String email = userservice.fetchUserEmail(user.getUser_name());
			user.setEmail(email);
			model.addAttribute("invalidAns", "success");
			
			modelAndView.setViewName("changepassword");
					
			session.setAttribute("user_name", user.getUser_name());
			
			modelAndView.addObject("userForm", user);
	
			return modelAndView;
		}
	}
	
	
	@RequestMapping(value="/changepassword")
	public ModelAndView changePassword(@Valid @ModelAttribute("userForm") User user){
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("changepassword");
		modelAndView.addObject("userForm", user);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/resetpassword")
	public ModelAndView chgPassword(@ModelAttribute("userForm") User user, HttpSession session,Model model){
		
		ModelAndView modelAndView = new ModelAndView();
		
		String userId = (String) session.getAttribute("user_name");
		String pwd = user.getPassword();
		
		userservice.setPassword(userId, pwd);
				
		model.addAttribute("pwdchanged", "Password Changed Successfully!");
		
		modelAndView.setViewName("login");
		return modelAndView;
	}	

	/*@RequestMapping(value="/forgotpassword")
	public String forgotPasswordPage(ModelMap model){
		
		return ;
	}*/

	
	/*to end session*/
	@RequestMapping("/logout")
	public String nextHandlingMethod(SessionStatus status, HttpSession session, WebRequest request){
		status.setComplete();
		request.removeAttribute("assessementObj", WebRequest.SCOPE_SESSION);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}

}
