package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.reserve.ReservationComment;
import kr.or.connect.reservation.dto.reserve.Reservation;
import kr.or.connect.reservation.dto.reserve.ReservationInfo;

public interface ReservationService {
	void createReservations(Reservation reservation);
	List<ReservationInfo> getReservations(String reservationEmail);
	void updateCancelFlagByReservationInfoId(Integer reservationInfoId);
	void createReservationComment(ReservationComment commentWrite);
}