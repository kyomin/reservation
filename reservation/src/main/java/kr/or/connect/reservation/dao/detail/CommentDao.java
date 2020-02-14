package kr.or.connect.reservation.dao.detail;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.connect.reservation.dto.detail.Comment;
import kr.or.connect.reservation.dto.detail.CommentImage;

@Mapper
public interface CommentDao {
	public List<Comment> selectAllCommentsByProductIdWithoutCommentImage(@Param("productId")int productId);
	public List<CommentImage> selectAllCommentImagesByReservationUserCommentId(@Param("reservationUserCommentId")int reservationUserCommentId);
	public double selectAverageScoreOfCommentByProductId(@Param("productId")int productId);
}