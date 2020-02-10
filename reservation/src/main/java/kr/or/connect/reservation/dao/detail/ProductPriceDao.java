package kr.or.connect.reservation.dao.detail;

import static kr.or.connect.reservation.dao.detail.ProductPriceDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.detail.ProductPrice;

@Repository
public class ProductPriceDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public ProductPriceDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = namedParameterJdbcTemplate;
	}
	
	public List<ProductPrice> selectAllProductPrices(int productId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("productId", productId);
		
		return jdbc.query(SELECT_ALL_PRODUCT_PRICES, params, BeanPropertyRowMapper.newInstance(ProductPrice.class));
	}
}
