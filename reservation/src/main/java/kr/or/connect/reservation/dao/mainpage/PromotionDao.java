package kr.or.connect.reservation.dao.mainpage;

import static kr.or.connect.reservation.dao.mainpage.PromotionDaoSqls.*;

import java.util.List;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.mainpage.Promotion;

@Repository
public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public PromotionDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = namedParameterJdbcTemplate;
	}
	
	public List<Promotion> selectAllPromotions() {
		return jdbc.query(SELECT_ALL_PROMOTIONS, BeanPropertyRowMapper.newInstance(Promotion.class));
	}
}