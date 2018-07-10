package com.sl.mapper;

import java.util.List;

import com.sl.po.Category;
import com.sl.po.Product;
import com.sl.po.ProductDetailInfo;
import com.sl.po.User;

public interface UnitMapper {

	
	ProductDetailInfo oneToOneTest(int productId);
	
	Product oneToOneTestMap(int productId);
	
	Category oneToManyTest(int cId);
	
	User manyToManyTest(int id);	
	
	
	Product oneToOneTestAssociationSelect(int id);
	
	Category selectCategoryInfo(int id);
	
	
	Category oneToManyTestCollectionSelect(int id);
	
	List<Product> selectProductByCategoryId(int cId);
}
