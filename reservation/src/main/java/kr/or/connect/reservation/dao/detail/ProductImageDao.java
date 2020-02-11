package kr.or.connect.reservation.dao.detail;

import static kr.or.connect.reservation.dao.detail.ProductImageDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.detail.ProductImage;

@Repository
public class ProductImageDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public ProductImageDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = namedParameterJdbcTemplate;
	}
	
	public List<ProductImage> selectAllProductImages(int productId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("productId", productId);
		
		return jdbc.query(SELECT_ALL_PRODUCT_IMAGES, params, BeanPropertyRowMapper.newInstance(ProductImage.class));
	}
}