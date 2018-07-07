package com.sl.test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.sl.mapper.ProductMapper;
import com.sl.mapper.UnitMapper;
import com.sl.po.Product;
import com.sl.po.ProductDetailInfo;
import com.sl.po.ProductInfo;
import com.sl.po.ProductVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestUnitMapperClient {

	// 定义会话SqlSession
	SqlSession session = null;

	@Before
	public void init() throws IOException {
		// 定义mabatis全局配置文件
		String resource = "SqlMapConfig.xml";

		// 加载mybatis全局配置文件
		// InputStream inputStream =
		// TestClient.class.getClassLoader().getResourceAsStream(resource);

		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(inputStream);
		// 根据sqlSessionFactory产生会话sqlsession
		session = factory.openSession();
	}

	//一对一  使用resultType映射结果集
	//@Test
	public void testSelectProduct() {

		// 获取mapper接口的代理对象
		UnitMapper unitMapper = session.getMapper(UnitMapper.class);

		List<ProductDetailInfo> listInfo = unitMapper.oneToOneTest();

		System.out.println(listInfo.size());

		// 关闭会话
		session.close();
	}
	
	   //一对一  使用resultMap映射结果集
		@Test
		public void testSelectProduct2() {

			// 获取mapper接口的代理对象
			UnitMapper unitMapper = session.getMapper(UnitMapper.class);

			List<Product> listInfo = unitMapper.oneToOneTestMap();

			System.out.println(listInfo.size());
			
			for (Product product : listInfo) {
				System.out.println(product);
				System.out.println(product.getCategory().getName());
				
			}
			// 关闭会话
			session.close();
		}

}
