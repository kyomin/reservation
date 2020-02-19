package kr.or.connect.reservation.dao.mainpage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.connect.reservation.dto.mainpage.Product;

@Mapper
public interface ProductDao {
	List<Product> selectAllProducts(@Param("start")int start, @Param("limit")int limit);
	List<Product> selectProductsByCategoryId(@Param("start")int start, @Param("limit")int limit, @Param("categoryId")int categoryId);
	int selectAllProductsCount();
	int selectProductsCountByCategoryId(@Param("categoryId")int categoryId);	
}