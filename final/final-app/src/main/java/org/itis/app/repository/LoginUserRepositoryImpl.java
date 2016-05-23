package org.itis.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.itis.app.entity.LoginUser;
import org.itis.app.entity.User;
import org.itis.app.util.HibernateUtil;
import org.springframework.stereotype.Component;
@Component
public class LoginUserRepositoryImpl implements LoginUserRepository{
	List<LoginUser> users = new ArrayList<LoginUser>();
	static String salt;
	private SessionFactory sessionFactory;
	public LoginUserRepositoryImpl() {
		this.sessionFactory = HibernateUtil.getSessionFactory();
		salt = "d@4d2-0";
	}
	public static String md5Apache(String st) {
	    String md5Hex = DigestUtils.md5Hex(st+salt);
	    return md5Hex;
	}
	@Override
	public List<LoginUser> getAllUsers() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<LoginUser> allUsers = session.createCriteria(LoginUser.class).list();
		session.close();
		return allUsers;
	}
	@Override
	public void saveUser(LoginUser user) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String password = user.getPassword();
			user.setPassword(md5Apache(password));
			session.save(user);
			session.getTransaction().commit();
		}catch(HibernateException he) {
			he.printStackTrace();
		}finally{
			session.close();
		}
	}
	@Override
	public boolean isExists(LoginUser loginUser) {
		List<LoginUser> allUsers = getAllUsers();
		String password = loginUser.getPassword();
		for (LoginUser lu: allUsers){
			if (lu.getLogin().equals(loginUser.getLogin())&&
					lu.getPassword().equals(md5Apache(password))){
				return true;
			}
		}
		return false;
	}
}
