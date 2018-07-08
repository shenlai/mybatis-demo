package com.sl.po;

import java.math.BigDecimal;

public class ProductDetailInfo {
	
	private int Id;
	private String Name;
	private String Description;
	private BigDecimal UnitPrice;
	private String ImageUrl;
	private Boolean IsNew;
	private String cityCode;
	private int categoryId;
	private String categoryName;
	private String categoryRemark;
	
	public int getId() {
		return Id;
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
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public BigDecimal getUnitPrice() {
		return UnitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		UnitPrice = unitPrice;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public Boolean getIsNew() {
		return IsNew;
	}
	public void setIsNew(Boolean isNew) {
		IsNew = isNew;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryRemark() {
		return categoryRemark;
	}
	public void setCategoryRemark(String categoryRemark) {
		this.categoryRemark = categoryRemark;
	}
	
	@Override
	public String toString() {
		return "ProductDetailInfo [Id=" + Id + ", Name=" + Name + ", Description=" + Description + ", UnitPrice="
				+ UnitPrice + ", ImageUrl=" + ImageUrl + ", IsNew=" + IsNew + ", cityCode=" + cityCode + ", categoryId="
				+ categoryId + ", categoryName=" + categoryName + ", categoryRemark=" + categoryRemark + "]";
	}
	
}
