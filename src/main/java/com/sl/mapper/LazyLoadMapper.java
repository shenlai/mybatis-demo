package com.sl.mapper;

import java.util.List;

import com.sl.po.Category;
import com.sl.po.Product;

public interface LazyLoadMapper {
	
	
	Category lazyLoadTest(int id);

	//嵌套查询
	List<Product> selectProductsByCategoryId(int cId);
}
