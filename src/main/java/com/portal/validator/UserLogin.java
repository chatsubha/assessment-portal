package com.portal.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.portal.dao.UserDao;
import com.portal.model.User;

@Component
public class UserLogin implements Validator
{

	@Autowired
	private UserDao loginDao;

	@Override
	public boolean supports(Class<?> arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors)
	{
		User user = (User) target;
		String securityAns = loginDao.getSecrtyAns(user.getUser_name());
		System.out.println(securityAns);

		if (!user.getSecurityAns().equalsIgnoreCase(securityAns)) {
			System.out.println("error");
			//errors.rejectValue("securityAns", "securityAns.error");
			errors.reject(securityAns);

		}
	}
}
