package org.itis.app.validator;

import org.itis.app.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

	public boolean supports(Class aClass) {
		return User.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "field.required", "Required field");
		if (!errors.hasFieldErrors("age")) {
			
			
		  	if (user.getAge() <= 0) {

		        errors.rejectValue("age", "not_zero", "Zero or negative age!");
		  		
		  	}

		}
		
	}
	
}
