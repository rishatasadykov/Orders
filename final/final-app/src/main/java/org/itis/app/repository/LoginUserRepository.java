package org.itis.app.repository;

import java.util.List;

import org.itis.app.entity.LoginUser;

public interface LoginUserRepository {
	public List<LoginUser> getAllUsers();
	public void saveUser(LoginUser user);
	public boolean isExists(LoginUser loginUser);
}
