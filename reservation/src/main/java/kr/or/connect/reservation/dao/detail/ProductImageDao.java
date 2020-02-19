package kr.or.connect.reservation.dao.detail;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.connect.reservation.dto.detail.ProductImage;

@Mapper
public interface ProductImageDao {
	List<ProductImage> selectAllProductImages(@Param("productId")int productId);
}