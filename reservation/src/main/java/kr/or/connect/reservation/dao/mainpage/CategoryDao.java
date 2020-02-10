package kr.or.connect.reservation.dao.mainpage;

import static kr.or.connect.reservation.dao.mainpage.CategoryDaoSqls.*;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.mainpage.Category;

@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public CategoryDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = namedParameterJdbcTemplate;
	}
	
	public List<Category> selectCategories() {
		return jdbc.query(SELECT_ALL_CATEGORIES, BeanPropertyRowMapper.newInstance(Category.class));
	}
}
