package org.itis.app.repository;

import java.util.List;
import org.itis.app.entity.User;
import org.springframework.stereotype.Component;
public interface UserRepository {
	public List<User> getAllUsers();
	public void saveUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	public User getUserById(int id);
}
