package org.itis.app.repository;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.itis.app.entity.User;
import org.itis.app.util.HibernateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class UserRepositoryImpl implements UserRepository {
	List<User> users = new ArrayList<User>();
	private SessionFactory sessionFactory;
	public UserRepositoryImpl() {
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}

	public void saveUser(User user) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}catch(HibernateException he) {
			he.printStackTrace();
		}finally{
			session.close();
		}
	}

	public void updateUser(User user) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		}catch(HibernateException he) {
			he.printStackTrace();
		}finally{
			session.close();
		}
	}

	public void deleteUser(int id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.delete(getUserById(id));
			session.getTransaction().commit();
		}catch(HibernateException he) {
			he.printStackTrace();
		}finally{
			session.close();
		}
	}

	public List<User> getAllUsers() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<User> allUsers = session.createCriteria(User.class).list();
		session.close();
		return allUsers;
	}

	@Override
	public User getUserById(int id) {
		for (User user: getAllUsers())
			if (user.getId() == id)
				return user;
		return null;
	}

}
