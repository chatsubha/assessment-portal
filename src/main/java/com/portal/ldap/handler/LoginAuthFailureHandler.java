package com.portal.ldap.handler;

import java.io.IOException;

import javax.naming.AuthenticationException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.portal.controller.AssessmentPortalApplication;

public class LoginAuthFailureHandler implements AuthenticationFailureHandler {
	
	static Logger logger= org.slf4j.LoggerFactory.getLogger(LoginAuthFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {
		
//		response.sendRedirect(arg0);
		
		if (exception!=null) {
			logger.error(exception.getLocalizedMessage(),exception);
			exception.printStackTrace();
			if(exception.getLocalizedMessage().contains("Bad credentials")) {
				
				response.sendRedirect("/login?error=t");
			}
			else {
				
				response.sendRedirect("/login?error=s");
			}
			
		}
		else {
			response.sendRedirect("/login?error=t");
		}
		
	}
	
 

}
