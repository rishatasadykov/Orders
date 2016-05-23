package org.itis.app;

import java.util.List;

import javax.validation.Valid;

import org.itis.app.entity.LoginUser;
import org.itis.app.entity.User;
import org.itis.app.repository.LoginUserRepository;
import org.itis.app.repository.UserRepositoryImpl;
import org.itis.app.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class LoginController {
	@Autowired
	LoginValidator loginValidator;
    @InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(loginValidator);
	}
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String autPage(Model model) {
        LoginUser loginUser = new LoginUser();
        model.addAttribute("user", loginUser);
        return "aut";
    }
    @RequestMapping(value="/", method=RequestMethod.POST)
    public String enter(@ModelAttribute("user") @Valid LoginUser user, BindingResult result, Model model) {
        if (result.hasErrors()){
        	model.addAttribute("user", user);
            return "aut";
        }
    	return "redirect:/users";
    }
}
