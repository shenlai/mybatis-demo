package com.sl.test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Options.FlushCachePolicy;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.sl.mapper.CacheMapper;
import com.sl.mapper.LazyLoadMapper;
import com.sl.mapper.ProductMapper;
import com.sl.mapper.UnitMapper;
import com.sl.po.Category;
import com.sl.po.Product;
import com.sl.po.ProductDetailInfo;
import com.sl.po.ProductInfo;
import com.sl.po.ProductVo;
import com.sl.po.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestCacheMapperClient {

	
	SqlSessionFactory factory = null;

	@Before
	public void init() throws IOException {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		factory = builder.build(inputStream);
	}
	

	// 一级缓存
	//@Test
	public void testSelectProductById() {

		SqlSession session = factory.openSession();
		CacheMapper mapper = session.getMapper(CacheMapper.class);

		Product product = mapper.selectProductById(1);

		System.out.println(product.getName());

		//执行commit 将清空一级缓存
		session.commit();
		
		Product product2 = mapper.selectProductById(1);
		
		System.out.println(product.getName());
		// 关闭会话
		session.close();
	}
	
	//二级缓存
	@Test
	public void testSelectProductById2() throws IOException {

		SqlSession session1 = factory.openSession();
		CacheMapper mapper1 = session1.getMapper(CacheMapper.class);

		Product product = mapper1.selectProductById(1);

		System.out.println(product.getName());
		
		/************同一session 共享一级缓存***************/
		//CacheMapper mapper2 = session1.getMapper(CacheMapper.class);

		//Product product2 = mapper2.selectProductById(1);

		//System.out.println(product2.getName());

		//执行commit 将清空一级缓存,无法情况二级缓存
		session1.commit();
		session1.close();
		
		//清空二级缓存 
		//Mapper接口注解@Options(flushCache=FlushCachePolicy.TRUE) 或者Mapper.xml配置属性 flushCache="true"
		SqlSession session4 = factory.openSession();
		CacheMapper mapper4 = session4.getMapper(CacheMapper.class);
		
		Product up = new Product();
		up.setId(1);
		up.setIsNew(true);
		up.setName("缓存测试2");

		int count = mapper4.updateProductById(up);
		session4.commit();
		session4.close();
		
		/**********不同session实例 共享二级缓存************/
		SqlSession session3 = factory.openSession();
		
		CacheMapper mapper3 = session3.getMapper(CacheMapper.class);

		Product product3 = mapper3.selectProductById(1);

		System.out.println(product3.getName());
		// 关闭会话
		session3.close();
	}
	
	
	
	

}
