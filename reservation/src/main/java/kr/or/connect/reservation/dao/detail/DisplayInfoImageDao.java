package kr.or.connect.reservation.dao.detail;

import static kr.or.connect.reservation.dao.detail.DisplayInfoImageDaoSqls.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.detail.DisplayInfoImage;

@Repository
public class DisplayInfoImageDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public DisplayInfoImageDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = namedParameterJdbcTemplate;
	}
	
	public DisplayInfoImage selectDisplayInfoImage(Integer displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("displayInfoId", displayInfoId);
		
		return jdbc.queryForObject(SELECT_DISPLAY_INFO_IMAGE, params, BeanPropertyRowMapper.newInstance(DisplayInfoImage.class));
	}
}