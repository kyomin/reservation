package kr.or.connect.reservation.dao.reserve;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.connect.reservation.dto.reserve.Reservation;

@Mapper
public interface ReservationDao {
	void insertReservation(@Param("reservation")Reservation reservation);
}