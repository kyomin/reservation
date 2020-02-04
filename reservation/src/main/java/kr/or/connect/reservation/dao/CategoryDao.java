package kr.or.connect.reservation.dao;

import java.util.List;


import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Category;
import static kr.or.connect.reservation.dao.CategoryDaoSqls.*;

@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	
	public CategoryDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = namedParameterJdbcTemplate;
	}
	
	public List<Category> selectCategories() {
		return jdbc.query(SELECT_ALL_CATEGORIES, rowMapper);
	}
}
