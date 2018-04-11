/**
 * 
 */
package com.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Rajkumar Kathiresan.
 *
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
   static Logger logger=LoggerFactory.getLogger(SessionInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// ignore login page
		if (request.getServletPath().equalsIgnoreCase("/") ||request.getServletPath().equalsIgnoreCase("/registrationForm") || request.getServletPath().equalsIgnoreCase("/register") ||request.getServletPath().equalsIgnoreCase("/checkNameAvailability") || request.getServletPath().equalsIgnoreCase("/login")
				|| request.getServletPath().equalsIgnoreCase("/assessment") || request.getServletPath().equalsIgnoreCase("/forgetpassword")) {
			System.out.println("ignore interceptor");
			return true;
		}
		HttpSession session = request.getSession();
		

		String userRole = (String) session.getAttribute("userRole");
		
		if (userRole == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			session.invalidate();
			return false;
		} else if ("/admin".equalsIgnoreCase(request.getServletPath()) && "user".equalsIgnoreCase(userRole)) {
			response.sendRedirect(request.getContextPath() + "/login");
			session.invalidate();
			return false;
		}
		return true;
	}

}
