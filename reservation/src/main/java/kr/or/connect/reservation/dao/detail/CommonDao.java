package kr.or.connect.reservation.dao.detail;

import static kr.or.connect.reservation.dao.detail.CommonDaoSqls.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/*
 * 		detail 페이지 내에서 빈번하게 하는 데이터 작업을 모은 클래스이다. 
 */
@Repository
public class CommonDao {
private NamedParameterJdbcTemplate jdbc;
	
	public CommonDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = namedParameterJdbcTemplate;
	}
	
	public int selectProductIdWithDisplayInfoId(Integer displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("displayInfoId", displayInfoId);
		
		return jdbc.queryForObject(SELECT_PRODUCT_ID_WITH_DISPLAY_INFO_ID, params, Integer.class);
	}
}
