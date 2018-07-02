package com.sl.test;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.sl.po.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


//使用productMapper.xml配置文件
public class TestClient {

	//定义会话SqlSession
	SqlSession session =null;
	
	@Before
	public void init() throws IOException {
		//定义mabatis全局配置文件
		String resource = "SqlMapConfig.xml";
		
		//加载mybatis全局配置文件
		//InputStream inputStream = TestClient.class.getClassLoader().getResourceAsStream(resource);
		
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(inputStream);
		//根据sqlSessionFactory产生会话sqlsession
		session = factory.openSession();	
	}

	
	//查询所有user表所有数据
	@Test
	public void testSelectAllUser() {
		String statement = "com.sl.mapper.ProductMapper.selectAllProduct";
		List<Product> listProduct =session.selectList(statement);
		for(Product product:listProduct)
		{
			System.out.println(product);
		}
		//关闭会话
		session.close();	
	}
	
	
}
