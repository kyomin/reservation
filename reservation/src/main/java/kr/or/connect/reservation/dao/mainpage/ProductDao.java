package kr.or.connect.reservation.dao.mainpage;

import static kr.or.connect.reservation.dao.mainpage.ProductDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.mainpage.Product;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public ProductDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = namedParameterJdbcTemplate;
	}
	
	public List<Product> selectAllProducts(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("start", start);
		params.put("limit", limit);
		
		return jdbc.query(SELECT_ALL_PRODUCTS_WITH_LIMIT, params, BeanPropertyRowMapper.newInstance(Product.class));
	}
	
	public List<Product> selectProductsByCategory(Integer start, Integer limit, Integer categoryId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("start", start);
		params.put("limit", limit);
		params.put("categoryId", categoryId);
		
		return jdbc.query(SELECT_PRODUCTS_BY_CATEGORY_WITH_LIMIT, params, BeanPropertyRowMapper.newInstance(Product.class));
	}
	
	public int selectAllProductsCount() {
		try {
			return jdbc.queryForObject(SELECT_COUNT_ALL_PRODUCTS, Collections.emptyMap(), Integer.class);
		} catch(NullPointerException e) {
			return 0;
		}
	}
	
	public int selectProductsByCategoryCount(Integer categoryId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("categoryId", categoryId);
		
		try {
			return jdbc.queryForObject(SELECT_COUNT_PRODUCTS_BY_CATEGORY, params, Integer.class);
		} catch(NullPointerException e) {
			return 0;
		}
	}
}