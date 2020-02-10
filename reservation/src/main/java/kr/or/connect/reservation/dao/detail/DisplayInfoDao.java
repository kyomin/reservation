package kr.or.connect.reservation.dao.detail;

import static kr.or.connect.reservation.dao.detail.DisplayInfoDaoSqls.SELECT_DISPLAY_INFO;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.detail.DisplayInfo;

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public DisplayInfoDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = namedParameterJdbcTemplate;
	}
	
	public DisplayInfo selectDisplayInfo(Integer displayInfoId, Integer productId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("displayInfoId", displayInfoId);
		params.put("productId", productId);
		
		return jdbc.queryForObject(SELECT_DISPLAY_INFO, params, BeanPropertyRowMapper.newInstance(DisplayInfo.class));
	}
}
