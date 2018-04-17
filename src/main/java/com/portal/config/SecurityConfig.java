/**
 * 
 */
package com.portal.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Collections;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.context.ServletContextAware;

import com.portal.ldap.handler.LoginAuthFailureHandler;
import com.portal.ldap.handler.LoginAuthSuccessHandler;

/**
 * @author Subhasis
 *
 */
@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter implements ServletContextAware   {
	
	@Value("${ldap.urls}")
	private String ldapUrls;
	
	@Value("${ldap.base.dn}")
	private String ldapBaseDn;
	
	@Value("${ldap.username}")
	private String ldapSecurityPrincipal;
	
	@Value("${ldap.password}")
	private String ldapPrincipalPassword;
	
	@Value("${ldap.user.dn.pattern}")
	private String ldapUserDnPattern;
	
	@Value("${ldap.enabled}")
	private String ldapEnabled;
	
	ServletContext context=null;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        		.antMatchers("/login**").permitAll()
        		.antMatchers("/assessment/**").fullyAuthenticated()
            	.antMatchers("/").permitAll()
            	.antMatchers("/admin/**").permitAll()
            	.and()
            .csrf()
    		.disable()
            .formLogin()
            	.loginPage("/login")
            	.usernameParameter("name")
            	.passwordParameter("password")
            	.loginProcessingUrl("/login")
            	.successForwardUrl("/assessment")
            	.successHandler(new LoginAuthSuccessHandler())
            	.failureHandler(new LoginAuthFailureHandler())         	
            	.permitAll()
            	.and()
            .logout()
            	.invalidateHttpSession(true)
            	.deleteCookies("JSESSIONID")
            	.permitAll()
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()
                .defaultsDisabled()
                .cacheControl();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		    
		if(Boolean.parseBoolean(ldapEnabled)) {
			DefaultSpringSecurityContextSource contextSource=new DefaultSpringSecurityContextSource(ldapUrls + ldapBaseDn);
			contextSource.setUserDn(ldapSecurityPrincipal);
			
			String key = "This is a secret";
			File encryptedFile = new File(context.getRealPath("WEB-INF/encrypt/text.encrypted"));
			ldapPrincipalPassword=fileProcessor(Cipher.DECRYPT_MODE,key,encryptedFile,null);
			contextSource.setPassword(ldapPrincipalPassword);
			
			contextSource.setReferral("follow");
			contextSource.setBaseEnvironmentProperties(Collections.singletonMap("com.sun.jndi.ldap.connect.timeout", "30000"));
			contextSource.afterPropertiesSet();
			auth
				.ldapAuthentication()
				.contextSource(contextSource)
						.userDnPatterns(ldapUserDnPattern).userSearchBase("ou=Cognizant")
		                .userSearchFilter("(userprincipalname={0})");
		} else {
	        auth
	        .inMemoryAuthentication()
	            .withUser("user").password("pass").roles("USER")
	            .and()
	            .withUser("admin").password("admin").roles("ADMIN");
		}
	}
	
	static String fileProcessor(int cipherMode,String key,File inputFile,File outputFile){
		 try {
			   Key secretKey = new SecretKeySpec(key.getBytes(), "AES");
		       
		       System.out.println(cipherMode);
		       Cipher cipher = Cipher.getInstance("AES");
		       System.out.println(cipher);
		       cipher.init(cipherMode, secretKey);

		       FileInputStream inputStream = new FileInputStream(inputFile);
		       byte[] inputBytes = new byte[(int) inputFile.length()];
		       inputStream.read(inputBytes);

		       byte[] outputBytes = cipher.doFinal(inputBytes);
	           if (cipherMode==1) {
	        	   FileOutputStream outputStream = new FileOutputStream(outputFile);
	    	       outputStream.write(outputBytes);
	    	       inputStream.close();
	    	       outputStream.close();
	           } else {
	        	   inputStream.close();
	        	   return new String(outputBytes);
	           }
	           
		    } catch (NoSuchPaddingException | NoSuchAlgorithmException 
	                     | InvalidKeyException | BadPaddingException
		             | IllegalBlockSizeException | IOException e) {
			e.printStackTrace();
	            }
		 return "";
	     }


	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.context=servletContext;
	}
}
