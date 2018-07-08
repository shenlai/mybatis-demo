package com.sl.po;

import java.sql.Date;
import java.util.List;

public class User {
	
	private int id;
	private String sex;
	private Date birthday;
	private String address;
	private String userName;
	
	//映射当前用户订单列表
	private List<Order> orders;
	
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", sex=" + sex + ", birthday=" + birthday + ", address=" + address + ", userName="
				+ userName + ", orders=" + orders + "]";
	}
	
	
	
}
