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

public class TestLazyLoadMapperClient {

	// 定义会话SqlSession
	SqlSession session = null;

	@Before
	public void init() throws IOException {
		// 定义mabatis全局配置文件
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(inputStream);
		// 根据sqlSessionFactory产生会话sqlsession
		session = factory.openSession();
	}

	// 延迟加载
	@Test
	public void testoneToManyTestCollectionSelect() {

		LazyLoadMapper mapper = session.getMapper(LazyLoadMapper.class);

		Category category = mapper.lazyLoadTest(1);

		//System.out.println(category);
		System.out.println(category.getName());

		if (category.getProductList().size() > 0) {
			for (Product pro : category.getProductList()) {
				System.out.println(pro);
			}
		}
		// 关闭会话
		session.close();
	}

}
