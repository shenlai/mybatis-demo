package com.sl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;

import com.sl.po.Category;
import com.sl.po.Product;

public interface CacheMapper {
	
	Product selectProductById(int id);
	
	@Options(flushCache=FlushCachePolicy.TRUE)
	int updateProductById(Product product);
	
}
