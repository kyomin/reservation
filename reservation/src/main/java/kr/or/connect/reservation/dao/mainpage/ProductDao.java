package kr.or.connect.reservation.dao.mainpage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.connect.reservation.dto.mainpage.Product;

@Mapper
public interface ProductDao {
	public List<Product> selectAllProducts(@Param("start")int start, @Param("limit")int limit);
	public List<Product> selectProductsByCategory(@Param("start")int start, @Param("limit")int limit, @Param("categoryId")int categoryId);
	public int selectAllProductsCount();
	public int selectProductsByCategoryCount(@Param("categoryId")int categoryId);	
}