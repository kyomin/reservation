package kr.or.connect.reservation.dao.detail;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.connect.reservation.dto.detail.ProductPrice;

@Mapper
public interface ProductPriceDao {
	public List<ProductPrice> selectAllProductPrices(@Param("productId")int productId);
}