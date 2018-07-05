package com.sl.po;

import java.math.BigDecimal;

public class ProductInfo {

	private int Id;
	private String ProductName;
	private String Description;
	private String CityCode;
	private String CityName;
	private boolean IsNew;
	
	
	public int getId() {
		return Id;
	}
	public boolean isIsNew() {
		return IsNew;
	}
	public void setIsNew(boolean isNew) {
		IsNew = isNew;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getCityCode() {
		return CityCode;
	}
	public void setCityCode(String cityCode) {
		CityCode = cityCode;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	
	@Override
    public String toString() {
        return "Product [id=" + Id + ", ProductName=" + ProductName + ", Description=" + Description
                + ", CityCode=" + CityCode + ", CityName=" + CityName + "]";
    }
}
