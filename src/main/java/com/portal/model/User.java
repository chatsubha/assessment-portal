package com.portal.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class User {

	private int user_id;
	
	@Pattern(regexp = "[a-zA-Z]+", message = "Please enter alphabetic characters")
	private String user_name;
	

	@NotNull
	@Size(min = 6, max = 10, message = "Password must be in between 6 and 10 characters")
	private String password;
	
	@NotNull
	@Size(min = 6, max = 10, message = "Password must be in between 6 and 10 characters")
	private String confirmPassword;
    
	@NotNull
	@Size(min = 6, max = 10, message = "Password must be in between 6 and 10 characters")
	private String confirmpassword;
	
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}


	private String role;
   
	
	@NotNull
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Please enter a valid E-mail id")
	private String email;
	
	@NotEmpty(message = "Please select a security question")
	private String userSecurtiyQuest;

	@NotEmpty(message = "Please enter your security answer")
	private String securityAns;
	
	private boolean validAndFlg = false;
	
	
	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}
	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public boolean isValidAndFlg() {
		return validAndFlg;
	}
	public void setValidAndFlg(boolean validAndFlg) {
		this.validAndFlg = validAndFlg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserSecurtiyQuest() {
		return userSecurtiyQuest;
	}
	public void setUserSecurtiyQuest(String userSecurtiyQuest) {
		this.userSecurtiyQuest = userSecurtiyQuest;
	}
	public String getSecurityAns() {
		return securityAns;
	}
	public void setSecurityAns(String securityAns) {
		this.securityAns = securityAns;
	}
	

	//private List<String> securityQuestions;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
