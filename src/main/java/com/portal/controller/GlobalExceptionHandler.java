package com.portal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public void handleIOException(HttpServletRequest request,HttpServletResponse response, Exception ex) throws IOException{
		ex.printStackTrace();
		System.out.println("Exception "+ex.getLocalizedMessage());
		if (ex.getLocalizedMessage().contains("Request method 'GET' not supported")) {
			response.sendRedirect("/login");
		}
		System.out.println(request.getRequestURI());
		//returning 404 error code
	}
	
	@ExceptionHandler(Exception.class)
	public void handleGlobalException(HttpServletRequest request,HttpServletResponse response, Exception ex) throws IOException{
		ex.printStackTrace();
		if (ex.getLocalizedMessage().contains("HttpRequestMethodNotSupportedException")) {
			response.sendRedirect("/login");
		}
		System.out.println(request.getRequestURI());
		//returning 404 error code
	}

}
