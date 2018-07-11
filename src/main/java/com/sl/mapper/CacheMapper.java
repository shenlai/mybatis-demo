package com.sl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;

import com.sl.po.Category;
import com.sl.po.Product;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;

public interface CacheMapper {
	
	@Options(useCache=false)
	Product selectProductById(int id);
	
	@Options(flushCache=FlushCachePolicy.TRUE)  //清空 二级缓存
	int updateProductById(Product product);
	
}
