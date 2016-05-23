package org.itis.app;

import org.itis.app.repository.UserRepository;
import org.itis.app.entity.User;
import org.itis.app.util.HibernateUtil;
import org.itis.app.entity.Order;
import org.itis.app.repository.OrderRepository;
import org.itis.app.repository.OrderRepositoryImpl;
import org.itis.app.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.itis.app.validator.UserValidator;
import java.util.List;

import javax.validation.Valid;

@Controller
public class UserController{
	@Autowired
	UserValidator userValidator;
	@Autowired
	UserRepository userRepository;
    @RequestMapping(value="/users", method=RequestMethod.GET)
    public String showUserList(Model model) {
    	try{
        	List<User> users = userRepository.getAllUsers();
        	model.addAttribute("users", users);
        	return "userList";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    @InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}
    @RequestMapping(value="user/delete", method=RequestMethod.GET)
    public String deleteUser(@RequestParam("id") int userId, Model model) {
    	try{
        	userRepository.deleteUser(userId);
        	return "redirect:/users";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    @RequestMapping(value="user/update", method=RequestMethod.GET)
    public String updateUser(@RequestParam("id") int userId, Model model) {
    	try{
    		User user = userRepository.getUserById(userId);
    		model.addAttribute("user", user);
    		return "updateUser";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    @RequestMapping(value="user/update", method=RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
    	try{
    		if (result.hasErrors()) {
    			model.addAttribute("user", user);
    			return "updateUser";
    		}
    		userRepository.updateUser(user);
    		return "redirect:/users";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    @RequestMapping(value="user/add", method=RequestMethod.GET)
    public String addUser(Model model) {
    	try{
    		User user = new User();
    		model.addAttribute("user", user);
    		return "addUser";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    @RequestMapping(value="user/add", method=RequestMethod.POST)
    public String saveNewUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
    	try{
    		if (result.hasErrors()) {
    			model.addAttribute("user", user);
    			return "addUser";
    		}
    		userRepository.saveUser(user);
    		return "redirect:/users";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
}
