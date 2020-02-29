package kr.or.connect.reservation.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import kr.or.connect.reservation.dto.reserve.Reservation;
import kr.or.connect.reservation.dto.reserve.ReservationComment;
import kr.or.connect.reservation.dto.reserve.ReservationInfo;

public interface ReservationService {
	void createReservations(Reservation reservation) throws Exception;
	List<ReservationInfo> getMyReservationInfoByEmail(String reservationEmail) throws Exception;
	void updateCancelFlagByReservationInfoId(Integer reservationInfoId) throws Exception;
	void createReservationComment(ReservationComment commentWrite) throws SQLException, IOException;
}