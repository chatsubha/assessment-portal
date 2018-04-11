package com.portal.ldap.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public class LoginAuthSuccessHandler implements AuthenticationSuccessHandler {
	Logger logger = LoggerFactory.getLogger(LoginAuthSuccessHandler.class);
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		logger.error(authentication.getDetails().toString());
		logger.error(authentication.getPrincipal().toString());
		String[] cn = authentication.getPrincipal().toString().split("cn=");
		if(cn.length>1) {
			request.getSession().setAttribute("CN", cn[1].split(",")[0]);
		}	
//		response.sendRedirect("/assessment");
		RequestDispatcher dispatch=request.getRequestDispatcher("/assessment");
		dispatch.forward(request, response);
	}

}
