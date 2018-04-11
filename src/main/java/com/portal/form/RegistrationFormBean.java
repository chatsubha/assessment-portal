package com.portal.form;

import java.util.ArrayList;
import java.util.List;

import com.portal.model.SecurityQuestions;

public class RegistrationFormBean {
	
	private List<SecurityQuestions> securityQuestions = new ArrayList<SecurityQuestions>();
	private boolean nameAvailabilityFlag = false;

	
		
	/**
	 * @return the nameAvailabilityFlag
	 */
	public boolean isNameAvailabilityFlag() {
		return nameAvailabilityFlag;
	}

	/**
	 * @param nameAvailabilityFlag the nameAvailabilityFlag to set
	 */
	public void setNameAvailabilityFlag(boolean nameAvailabilityFlag) {
		this.nameAvailabilityFlag = nameAvailabilityFlag;
	}

	/**
	 * @return the securityQuestions
	 */
	public List<SecurityQuestions> getSecurityQuestions() {
		return securityQuestions;
	}

	/**
	 * @param securityQuestions the securityQuestions to set
	 */
	public void setSecurityQuestions(List<SecurityQuestions> securityQuestions) {
		this.securityQuestions = securityQuestions;
	}
	
	

}
