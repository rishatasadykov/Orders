package org.itis.app;

import org.itis.app.repository.OrderRepository;
import org.itis.app.entity.Order;
import org.itis.app.entity.User;
import org.itis.app.util.HibernateUtil;
import org.itis.app.entity.Order;
import org.itis.app.repository.OrderRepositoryImpl;
import org.itis.app.repository.UserRepository;
import org.itis.app.repository.UserRepositoryImpl;
import org.itis.app.repository.OrderRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.CustomType;
import org.hibernate.usertype.UserType;
import org.itis.app.entity.Order;
import org.itis.app.validator.OrderValidator;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

@Controller
public class OrderController{
	@Autowired
	OrderValidator orderValidator;
    @RequestMapping(value="orders", method=RequestMethod.GET)
    public String showOrderList(Model model) {
    	try{
    		OrderRepository orderRepository = new OrderRepositoryImpl(HibernateUtil.getSessionFactory());
        	List<Order> orders = orderRepository.getAllOrders();
        	model.addAttribute("orders", orders);
        	return "orderList";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    @InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(orderValidator);
		binder.setDisallowedFields(new String[] {"user"});
	}
    @RequestMapping(value="order/delete", method=RequestMethod.GET)
    public String deleteOrder(@RequestParam String id, Model model) {
    	try{
    		OrderRepository orderRepository = new OrderRepositoryImpl(HibernateUtil.getSessionFactory());
        	orderRepository.deleteOrder(Integer.parseInt(id));
        	return "redirect:/orders";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    @RequestMapping(value="order/update", method=RequestMethod.GET)
    public String updateOrder(@RequestParam("id") int orderId, Model model) {
    	try{
    		OrderRepository orderRepository = new OrderRepositoryImpl(HibernateUtil.getSessionFactory());
    		Order order = orderRepository.getOrderById(orderId);
    		UserRepository userRepository = new UserRepositoryImpl(HibernateUtil.getSessionFactory());
        	List<User> users = userRepository.getAllUsers();
    		model.addAttribute("userList", users);
    		model.addAttribute("order", order);
    		return "updateOrder";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    @RequestMapping(value="order/update", method=RequestMethod.POST)
    public String saveOrder(@RequestParam String user, @ModelAttribute("order") Order order, BindingResult result, Model model) {
    	try{
    		UserRepository userRepository = new UserRepositoryImpl(HibernateUtil.getSessionFactory());
    		order.setUser(userRepository.getUserById(Integer.parseInt(user)));
    		orderValidator.validate(order, result);
    		if (result.hasErrors()) {
    			model.addAttribute("order", order);
            	List<User> users = userRepository.getAllUsers();
        		model.addAttribute("userList", users);
    			return "updateOrder";
    		}
    		OrderRepository orderRepository = new OrderRepositoryImpl(HibernateUtil.getSessionFactory());
    		orderRepository.updateOrder(order);
    		return "redirect:/orders";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    @RequestMapping(value="order/add", method=RequestMethod.GET)
    public String addOrder(Model model) {
    	try{
    		Order order = new Order();
    		model.addAttribute("order", order);
    		UserRepository userRepository = new UserRepositoryImpl(HibernateUtil.getSessionFactory());
        	List<User> users = userRepository.getAllUsers();
    		model.addAttribute("userList", users);
    		return "addOrder";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
    @RequestMapping(value="order/add", method=RequestMethod.POST)
    public String saveNewOrder(@RequestParam String user, @ModelAttribute("order") Order order, BindingResult result, ModelMap model) {
    	try{
    		UserRepository userRepository = new UserRepositoryImpl(HibernateUtil.getSessionFactory());
    		order.setUser(userRepository.getUserById(Integer.parseInt(user)));
    		orderValidator.validate(order, result);
    		if (result.hasErrors()) {
    			model.addAttribute("order", order);
            	List<User> users = userRepository.getAllUsers();
        		model.addAttribute("userList", users);
    			return "addOrder";
    		}
    		OrderRepository orderRepository = new OrderRepositoryImpl(HibernateUtil.getSessionFactory());
    		orderRepository.saveOrder(order);
    		return "redirect:/orders";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
}