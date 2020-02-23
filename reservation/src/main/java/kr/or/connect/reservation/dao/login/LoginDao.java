package kr.or.connect.reservation.dao.login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginDao {
	int selectAllReservationsCountByReservationEmail(@Param("reservationEmail")String reservationEmail);
}
