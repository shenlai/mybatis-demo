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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
		product.setName("小酒馆2222");
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
	// @Test
	public void testSelectByProductName() {
		// 使用like '%${value}%'
		String statement = "com.sl.mapper.ProductMapper.selectProductByName";
		List<Product> listProduct = session.selectList(statement, "琶洲");
		for (Product product : listProduct) {
			System.out.println(product);
		}

		System.out.println("***************************************************");

		// 使用 like #{value}
		String statement2 = "com.sl.mapper.ProductMapper.selectProductByName2";
		List<Product> listProduct2 = session.selectList(statement2, "%国际%");
		for (Product product : listProduct2) {
			System.out.println(product);
		}
		// 关闭会话
		session.close();
	}

	// pojo
	// @Test
	public void testselectProductByPoJo() {

		String statement = "com.sl.mapper.ProductMapper.selectProductByPoJo";
		Product pro = new Product();
		pro.setUnitPrice(new BigDecimal(30));
		pro.setIsNew(true);

		List<Product> listProduct = session.selectList(statement, pro);
		for (Product product : listProduct) {
			System.out.println(product);
		}

		// 关闭会话
		session.close();
	}

	// vo包装对象
	// @Test
	public void testselectProductByVo() {

		String statement = "com.sl.mapper.ProductMapper.selectProductByVo";
		ProductVo vo = new ProductVo();
		// vo.setCategory(category);
		Product po = new Product();
		po.setCityCode("A01");
		po.setIsNew(true);
		vo.setProduct(po);

		List<Product> listProduct = session.selectList(statement, vo);
		for (Product product : listProduct) {
			System.out.println(product);
		}

		// 关闭会话
		session.close();
	}

	// hashmap
	// @Test
	public void testselectProductByHashMap() {

		String statement = "com.sl.mapper.ProductMapper.selectProductByHashMap";

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cityCode", "A02");
		map.put("isNew", true);

		List<Product> listProduct = session.selectList(statement, map);
		for (Product product : listProduct) {
			System.out.println(product);
		}

		// 关闭会话
		session.close();
	}

	// 见xml配置

	// 配置输出结果集映射 resultType 和 resultMap
	// @Test
	public void testCountProduct() {
		String statement = "com.sl.mapper.ProductMapper.countProducts";

		int count = session.selectOne(statement);
		System.out.println(count);
		session.commit();// insert 需要commit
		session.close();
	}

	// resultMap
	// @Test
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
	public void testSelectAllProduct() {
		String statement = "com.sl.mapper.ProductMapper.selectAllProduct";
		List<Product> listProduct = session.selectList(statement);
		for (Product product : listProduct) {
			System.out.println(product);
		}
		// 关闭会话
		session.close();
	}

	// pojo->hashmap
	// @Test
	public void testSelectProductById() {
		String statement = "com.sl.mapper.ProductMapper.selectProductById2";
		HashMap<String, Object> map = session.selectOne(statement, 1);

		Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}
		// 关闭会话
		session.close();
	}

	// list<pojo> -> hashmap
	// @Test
	public void testSelectProducts2() {
		String statement = "com.sl.mapper.ProductMapper.selectAllProduct2";

		List<HashMap<String, Object>> list = session.selectList(statement);

		for (HashMap<String, Object> map : list) {
			Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Object> entry = it.next();
				System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			}
		}

		// 关闭会话
		session.close();
	}

	/********************************************************************************/
	/********************************************************************************/
	/********************************************************************************/
	// 动态sql
	// if
	//@Test
	public void testifTest() {
		String statement = "com.sl.mapper.ProductMapper.ifTest";
		ProductInfo info = new ProductInfo();
		info.setCityCode("A01");
		info.setProductName("%国际%");
		// info.setDescription("%xx%"); //select * from products where isnew = ? and
		// name like ?
		List<Product> listProduct = session.selectList(statement, info);
		for (Product pro : listProduct) {
			System.out.println(pro);
		}

		// 关闭会话
		session.close();
	}

	// choose, when, otherwise
	//@Test
	public void testchoosewhenotherwiseTest() {
		String statement = "com.sl.mapper.ProductMapper.choosewhenotherwiseTest";
		Product product = new Product();
		product.setIsNew(true);
		//product.setName("%国际%");
		//prouct.setDescription("%xx%");
		product.setUnitPrice(new BigDecimal(100));
		List<Product> listProduct = session.selectList(statement, product);
		for (Product pro : listProduct) {
			System.out.println(pro);
		}

		// 关闭会话
		session.close();
	}
	
	//where 标签改写if
	//@Test
	public void testwhereTest() {
		String statement = "com.sl.mapper.ProductMapper.whereTest";
		Product product = new Product();
		product.setCityCode("A01");
		product.setName("%国际%");
		//product.setDescription("%xx%");
		product.setUnitPrice(new BigDecimal(100));
		List<Product> listProduct = session.selectList(statement, product);
		for (Product pro : listProduct) {
			System.out.println(pro);
		}

		// 关闭会话
		session.close();
	}
	
	//if+set
	//@Test
		public void testsetTest() {
			String statement = "com.sl.mapper.ProductMapper.setTest";
			Product product = new Product();
			product.setId(1);
			product.setCityCode("A01");
			//product.setName("SetTest酒店名称");
			product.setDescription("SetTest酒店名称");
			int count = session.update(statement, product);
			session.commit();
			System.out.println(count);
			// 关闭会话
			session.close();
		}
	
	
		//trim 改写set
		@Test
		public void testtrimsetTest() {
			String statement = "com.sl.mapper.ProductMapper.trimsetTest";
			Product product = new Product();
			product.setId(1);
			product.setCityCode("A02");
			//product.setName("SetTest酒店名称");
			product.setDescription("trimTest酒店名称");
			int count = session.update(statement, product);
			session.commit();
			System.out.println(count);
			// 关闭会话
			session.close();
		}
	
	
	
	
	//trim 改写where
	//@Test
	public void testreimwhereTest() {
		String statement = "com.sl.mapper.ProductMapper.trimwhereTest";
		Product product = new Product();
		product.setCityCode("A01");
		product.setName("%国际%");
		//prouct.setDescription("%xx%");
		product.setUnitPrice(new BigDecimal(100));
		List<Product> listProduct = session.selectList(statement, product);
		for (Product pro : listProduct) {
			System.out.println(pro);
		}

		// 关闭会话
		session.close();
	}

}
