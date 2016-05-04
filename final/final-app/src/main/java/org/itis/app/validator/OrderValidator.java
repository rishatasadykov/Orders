package org.itis.app.validator;

import java.util.List;
import org.itis.app.entity.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OrderValidator implements Validator {

	public boolean supports(Class aClass) {
		return Order.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Order order = (Order) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "product", "field.required", "Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "field.required", "Required field");
		
		if (!errors.hasFieldErrors("price")) {
			
		  	if (order.getPrice() <= 0) {

		        errors.rejectValue("price", "not_zero", "Can't be free!");
		  		
		  	}

		}
		
	}
	
}