package com.sl.mapper;
import java.util.List;
import com.sl.po.Product;
import com.sl.po.ProductVo;

public interface ProductMapper {
	
	List<Product> selectAllProduct();
	
	List<Product> selectProductsByVo(ProductVo vo);
	
}
