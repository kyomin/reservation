package kr.or.connect.reservation.dao.reserve;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.connect.reservation.dto.reserve.ReservationComment;

@Mapper
public interface ReservationCommentDao {
	void insertReservationComment(@Param("reservationComment")ReservationComment reservationComment);
	Integer insertReservationCommentFile(@Param("reservationComment")ReservationComment reservationComment);
	Integer insertReservationCommentImage(@Param("reservationComment")ReservationComment reservationComment);
}