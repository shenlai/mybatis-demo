package com.sl.po;

import java.util.List;

public class Category {
	private int Id;
	private String Name;
	private String Remark;
	
	private List<Product> productList;
	
	
	public int getId() {
		return Id;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}

	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	
	@Override
	public String toString() {
		return "Category [Id=" + Id + ", Name=" + Name + ", Remark=" + Remark + ", productList=" + productList + "]";
	}
}
