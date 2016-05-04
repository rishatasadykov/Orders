package org.itis.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.itis.app.entity.User;
import org.itis.app.util.HibernateUtil;
import org.itis.app.entity.Order;
import org.itis.app.repository.OrderRepository;
import org.itis.app.repository.OrderRepositoryImpl;
import org.itis.app.repository.UserRepository;
import org.itis.app.repository.UserRepositoryImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try{
    		OrderRepository orderRepository = new OrderRepositoryImpl(HibernateUtil.getSessionFactory());
        	List<Order> orders = orderRepository.getAllOrders();
        	for (Order order: orders)
        		System.out.println(order.getUser().getAge());
        }catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
