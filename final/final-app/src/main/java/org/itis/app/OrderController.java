package org.itis.app;

import org.itis.app.repository.OrderRepository;
import org.itis.app.entity.Order;
import org.itis.app.entity.User;
import org.itis.app.util.HibernateUtil;
import org.itis.app.repository.OrderRepositoryImpl;
import org.itis.app.repository.UserRepository;
import org.itis.app.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.itis.app.validator.OrderValidator;

import java.util.List;

@Controller
public class OrderController{
	@Autowired
	OrderValidator orderValidator;
	@Autowired
	OrderRepository orderRepository;
    @RequestMapping(value="orders", method=RequestMethod.GET)
    public String showOrderList(Model model) {
    	try{
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
    		Order order = orderRepository.getOrderById(orderId);
    		UserRepository userRepository = new UserRepositoryImpl();
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
    		UserRepository userRepository = new UserRepositoryImpl();
    		order.setUser(userRepository.getUserById(Integer.parseInt(user)));
    		orderValidator.validate(order, result);
    		if (result.hasErrors()) {
    			model.addAttribute("order", order);
            	List<User> users = userRepository.getAllUsers();
        		model.addAttribute("userList", users);
    			return "updateOrder";
    		}
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
    		UserRepository userRepository = new UserRepositoryImpl();
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
    		UserRepository userRepository = new UserRepositoryImpl();
    		order.setUser(userRepository.getUserById(Integer.parseInt(user)));
    		orderValidator.validate(order, result);
    		if (result.hasErrors()) {
    			model.addAttribute("order", order);
            	List<User> users = userRepository.getAllUsers();
        		model.addAttribute("userList", users);
    			return "addOrder";
    		}
    		orderRepository.saveOrder(order);
    		return "redirect:/orders";
    	}catch(Exception e){
    		e.printStackTrace();
    	}
		return null;
    }
}