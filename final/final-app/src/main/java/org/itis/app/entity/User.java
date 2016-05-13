package org.itis.app.entity;
import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.Style;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name="USERS", catalog="itis_hib_db")
public class User {
	private int id;
	private String firstname;
	private String lastname;
	private int age;
	
	List<Order> orderList = new ArrayList<Order>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@Cascade({CascadeType.SAVE_UPDATE})
	public List<Order> getOrderList() {
		return orderList;
	}
	
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USER_ID", nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "FIRSTNAME", nullable = false, length=20)
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	@Column(name = "LASTNAME", nullable = false, length=20)
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Column(name = "AGE", nullable = false)
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
