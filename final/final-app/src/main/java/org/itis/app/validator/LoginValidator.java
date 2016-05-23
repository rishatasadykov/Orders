package org.itis.app.validator;

import org.itis.app.entity.LoginUser;
import org.itis.app.entity.Order;
import org.itis.app.repository.LoginUserRepositoryImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

	public boolean supports(Class aClass) {
		return LoginUser.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		LoginUser loginUser = (LoginUser) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "field.required", "Enter login");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "Enter password");
		
		
		if (!errors.hasFieldErrors("login")) {
			LoginUserRepositoryImpl loginUserRepositoryImpl = new LoginUserRepositoryImpl();
		  	if (!loginUserRepositoryImpl.isExists(loginUser)) {
		        errors.rejectValue("login", "wrong_login", "Incorrect login/password!");
		  	}
		}
	}
}
