package kr.or.connect.reservation.dao.reserve;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.connect.reservation.dto.reserve.Reservation;
import kr.or.connect.reservation.dto.reserve.ReservationInfo;

@Mapper
public interface ReservationDao {
	void insertReservation(@Param("reservation")Reservation reservation);
	List<ReservationInfo> selectAllReservationsByReservationEmail(@Param("reservationEmail")String reservationEmail);
	int selectTotalPriceByReservationInfoId(@Param("reservationInfoId")int reservationInfoId);
	void updateCancelFlagByReservationInfoId(@Param("reservationInfoId")int reservationInfoId);
}