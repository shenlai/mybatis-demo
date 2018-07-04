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

import com.sl.po.Product;
import com.sl.po.ProductInfo;
import com.sl.po.ProductVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//使用productMapper.xml配置文件
public class TestClient {

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

	// select by id
	// @Test
	public void testSelectProduct() {
		String statement = "com.sl.mapper.ProductMapper.selectProduct";
		Product product = session.selectOne(statement, 1);
		System.out.println(product);

		session.close();
	}

	// insert
	// @Test
	public void testInsertProduct() {
		String statement = "com.sl.mapper.ProductMapper.insertProduct";
		Product product = new Product();
		product.setName("小酒馆");
		product.setDescription("xxxx");
		product.setIsNew(true);
		product.setUnitPrice(new BigDecimal(1000));
		int count = session.insert(statement, product);
		// System.out.println(product);
		session.commit();// insert 需要commit
		session.close();
	}

	// update
	// @Test
	public void testUpdateProduct() {
		String statement = "com.sl.mapper.ProductMapper.updateProduct";
		Product product = new Product();
		product.setId(24);
		product.setIsNew(true);
		product.setUnitPrice(new BigDecimal(2000));

		int count = session.update(statement, product);
		// System.out.println(product);
		session.commit();// insert 需要commit
		session.close();
	}

	// update
	// @Test
	public void testProduct() {
		String statement = "com.sl.mapper.ProductMapper.deleteProduct";

		int count = session.delete(statement, 24);
		// System.out.println(product);
		session.commit();// insert 需要commit
		session.close();
	}

	/*********************************************************************************************************************/
	// 配置输入参数映射
	//@Test
	public void testSelectByProductName() {
		//使用like '%${value}%'
		String statement = "com.sl.mapper.ProductMapper.selectProductByName";
		List<Product> listProduct = session.selectList(statement,"琶洲");
		for (Product product : listProduct) {
			System.out.println(product);
		}
		
		System.out.println("***************************************************");
		
		//使用 like #{value}
		String statement2 = "com.sl.mapper.ProductMapper.selectProductByName2";
		List<Product> listProduct2 = session.selectList(statement2,"%国际%");
		for (Product product : listProduct2) {
			System.out.println(product);
		}
		// 关闭会话
		session.close();
	}
	
	//pojo
	//@Test
	public void testselectProductByPoJo() {
		
		String statement = "com.sl.mapper.ProductMapper.selectProductByPoJo";
		Product pro = new Product();
		pro.setUnitPrice(new BigDecimal(30));
		pro.setIsNew(true);
		
		List<Product> listProduct = session.selectList(statement,pro);
		for (Product product : listProduct) {
			System.out.println(product);
		}
		
		// 关闭会话
		session.close();
	}
	
	//vo包装对象
	//@Test
	public void testselectProductByVo() {
		
		String statement = "com.sl.mapper.ProductMapper.selectProductByVo";
		ProductVo vo= new ProductVo();
		//vo.setCategory(category);
		Product po =new Product();
		po.setCityCode("A01");
		po.setIsNew(true);
		vo.setProduct(po);
		
		List<Product> listProduct = session.selectList(statement,vo);
		for (Product product : listProduct) {
			System.out.println(product);
		}
		
		// 关闭会话
		session.close();
	}
	
	//hashmap
	@Test
	public void testselectProductByHashMap() {
		
		String statement = "com.sl.mapper.ProductMapper.selectProductByHashMap";
		
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("cityCode", "A02");
		map.put("isNew", true);
		
		List<Product> listProduct = session.selectList(statement,map);
		for (Product product : listProduct) {
			System.out.println(product);
		}
		
		// 关闭会话
		session.close();
	}
	
	
	
	
	
	// 见xml配置

	// 配置输出结果集映射 resultType 和 resultMap
	//@Test
	public void testCountProduct() {
		String statement = "com.sl.mapper.ProductMapper.countProducts";

		int count = session.selectOne(statement);
		System.out.println(count);
		session.commit();// insert 需要commit
		session.close();
	}
	
	//resultMap
	//@Test
	public void testselectProductInfo() {
		String statement = "com.sl.mapper.ProductMapper.selectProductInfo";

		List<ProductInfo> list = session.selectList(statement);
		for (ProductInfo info : list) {
			System.out.println(info);
		}
		session.close();
	}
	

	/***********************************************************************************************************************/
	// 查询所有user表所有数据
	// @Test
	public void testSelectAllUser() {
		String statement = "com.sl.mapper.ProductMapper.selectAllProduct";
		List<Product> listProduct = session.selectList(statement);
		for (Product product : listProduct) {
			System.out.println(product);
		}
		// 关闭会话
		session.close();
	}

}
