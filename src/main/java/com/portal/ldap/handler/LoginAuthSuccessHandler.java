package com.portal.ldap.handler;

import java.io.IOException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
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
	
	public static String fetchUserMailId(String userName,String Password) throws NamingException {
		
		Properties inititalProperties=new Properties();
		inititalProperties.put(Context.SECURITY_CREDENTIALS,Password);
		inititalProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		inititalProperties.put(Context.PROVIDER_URL,"ldap://CTS.com/dc=CTS,dc=com");
		inititalProperties.put(Context.SECURITY_PRINCIPAL, userName);
		inititalProperties.put("com.sun.jndi.ldap.connect.timeout", "300000");
		DirContext context=new InitialDirContext(inititalProperties);
		System.out.print("done");
		String seatchFilter="(userPrincipalName="+userName+")";
		String[] attributes={"msRTCSIP-PrimaryUserAddress"};
		SearchControls controls=new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		controls.setReturningAttributes(attributes);
		NamingEnumeration users=context.search("ou=Cognizant", seatchFilter,controls);
		SearchResult searchResult=null;
		String mailId=null;
		
		while(users.hasMore()) {
			searchResult=(SearchResult)users.next();
			Attributes attribute=searchResult.getAttributes();
			System.out.println(attribute.toString());
			mailId=attribute.get("msRTCSIP-PrimaryUserAddress").get(0).toString();
			System.out.println(mailId.split(":")[1]);
			
		}
		
		context.close();
		return mailId.split(":")[1];
	}
	
}


