package org.itis.app;

import org.itis.app.repository.UserRepository;
import org.itis.app.entity.User;
import org.itis.app.util.HibernateUtil;
import org.itis.app.entity.Order;
import org.itis.app.repository.OrderRepository;
import org.itis.app.repository.OrderRepositoryImpl;
import org.itis.app.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.itis.app.entity.User;
import org.itis.app.validator.UserValidator;
import java.util.List;

import javax.validation.Valid;

@Controller
public class UserController{
	@Autowired
	UserValidator userValidator;
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String showUserList(Model model) {
    	try{
    		UserRepository userRepository = new UserRepositoryImpl(HibernateUtil.getSessionFactory());
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
    		UserRepository userRepository = new UserRepositoryImpl(HibernateUtil.getSessionFactory());
    		List<Order> orderList = userRepository.getUserById(userId).getOrderList();
    		OrderRepository orderRepository = new OrderRepositoryImpl(HibernateUtil.getSessionFactory());
    		for (Order o: orderList)
    			orderRepository.deleteOrder(o.getId());
        	userRepository.deleteUser(userId);
        	return "redirect:/";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    @RequestMapping(value="user/update", method=RequestMethod.GET)
    public String updateUser(@RequestParam("id") int userId, Model model) {
    	try{
    		UserRepository userRepository = new UserRepositoryImpl(HibernateUtil.getSessionFactory());
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
    		UserRepository userRepository = new UserRepositoryImpl(HibernateUtil.getSessionFactory());
    		userRepository.updateUser(user);
    		return "redirect:/";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    @RequestMapping(value="user/add", method=RequestMethod.GET)
    public String addUser(Model model) {
    	try{
    		UserRepository userRepository = new UserRepositoryImpl(HibernateUtil.getSessionFactory());
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
    		UserRepository userRepository = new UserRepositoryImpl(HibernateUtil.getSessionFactory());
    		userRepository.saveUser(user);
    		return "redirect:/";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
}
