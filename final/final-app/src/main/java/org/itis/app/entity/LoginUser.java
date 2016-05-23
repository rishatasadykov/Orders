package org.itis.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOGINUSER", catalog="itis_hib_db")
public class LoginUser {
	
	private String login;
	private String password;
	public LoginUser(){
		
	}
	public LoginUser(String login, String password){
		this.login = login;
		this.password = password;
	}
	@Id
	@Column(name = "LOGIN", nullable = false, length=20)
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	@Column(name = "PASSWORD", nullable = false, length=100)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
