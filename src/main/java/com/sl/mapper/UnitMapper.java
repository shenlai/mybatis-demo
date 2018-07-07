package com.sl.mapper;

import java.util.List;

import com.sl.po.Product;
import com.sl.po.ProductDetailInfo;

public interface UnitMapper {

	List<ProductDetailInfo> oneToOneTest();
	
	List<Product> oneToOneTestMap();
	
	
}
