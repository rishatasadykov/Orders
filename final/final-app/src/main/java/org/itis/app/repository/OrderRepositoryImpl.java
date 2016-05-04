package org.itis.app.repository;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.itis.app.entity.Order;
import org.itis.app.entity.User;
public class OrderRepositoryImpl implements OrderRepository {
	private SessionFactory sessionFactory;
	List<Order> orders = new ArrayList<Order>();
	public List<Order> getAllOrders() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Order> orders = session.createCriteria(Order.class).list();
		session.close();
		return orders;
	}
	
	public OrderRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void saveOrder(Order order) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(order);
			session.getTransaction().commit();
		}catch(HibernateException he) {
			he.printStackTrace();
		}finally{
			session.close();
		}
	}

	public void updateOrder(Order order) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(order);
			session.getTransaction().commit();
		}catch(HibernateException he) {
			he.printStackTrace();
		}finally{
			session.close();
		}
	}

	public void deleteOrder(int id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.delete(getOrderById(id));
			session.getTransaction().commit();
		}catch(HibernateException he) {
			he.printStackTrace();
		}finally{
			session.close();
		}
	}
	@Override
	public Order getOrderById(int orderId) {
		for (Order order: getAllOrders())
			if (order.getId() == orderId)
				return order;
		return null;
	}
}
