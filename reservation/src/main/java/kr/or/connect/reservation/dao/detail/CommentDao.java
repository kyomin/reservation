package kr.or.connect.reservation.dao.detail;

import static kr.or.connect.reservation.dao.detail.CommentDaoSqls.SELECT_ALL_COMMENTS_BY_PRODUCT_ID_WITHOUT_COMMENT_IMAGE;
import static kr.or.connect.reservation.dao.detail.CommentDaoSqls.SELECT_ALL_COMMENT_IMAGES_BY_RESERVATION_USER_COMMENT_ID;
import static kr.or.connect.reservation.dao.detail.CommentDaoSqls.SELECT_AVERAGE_SCORE_OF_COMMENT_BY_PRODUCT_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.detail.Comment;
import kr.or.connect.reservation.dto.detail.CommentImage;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public CommentDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbc = namedParameterJdbcTemplate;
	}
	
	public List<Comment> selectAllCommentsByProductIdWithoutCommentImage(int displayInfoId, int productId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("displayInfoId", displayInfoId);
		params.put("productId", productId);
		
		return jdbc.query(SELECT_ALL_COMMENTS_BY_PRODUCT_ID_WITHOUT_COMMENT_IMAGE, params, BeanPropertyRowMapper.newInstance(Comment.class));
	}
	
	public List<CommentImage> selectAllCommentImagesByReservationUserCommentId(Integer reservationUserCommentId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("reservationUserCommentId", reservationUserCommentId);
		
		return jdbc.query(SELECT_ALL_COMMENT_IMAGES_BY_RESERVATION_USER_COMMENT_ID, params, BeanPropertyRowMapper.newInstance(CommentImage.class));
	}
	
	public double selectAverageScoreOfCommentByProductId(Integer productId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("productId", productId);
		
		try {
			return jdbc.queryForObject(SELECT_AVERAGE_SCORE_OF_COMMENT_BY_PRODUCT_ID, params, Double.class);
		} catch(NullPointerException e) {
			return 0;
		}
	}
}
