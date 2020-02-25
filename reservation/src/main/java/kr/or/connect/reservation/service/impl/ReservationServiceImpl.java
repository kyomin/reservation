package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.detail.DisplayInfoDao;
import kr.or.connect.reservation.dao.reserve.ReservationCommentDao;
import kr.or.connect.reservation.dao.reserve.PriceDao;
import kr.or.connect.reservation.dao.reserve.ReservationDao;
import kr.or.connect.reservation.dto.reserve.ReservationComment;
import kr.or.connect.reservation.dto.reserve.Reservation;
import kr.or.connect.reservation.dto.reserve.ReservationInfo;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	private final ReservationDao reservationDao;
	private final PriceDao priceDao;
	private final ReservationCommentDao reservationCommentDao;
	
	private final DisplayInfoDao displayInfoDao;
	
	public ReservationServiceImpl(ReservationDao reservationDao, PriceDao priceDao, DisplayInfoDao displayInfoDao, ReservationCommentDao reservationCommentDao) {
		this.reservationDao = reservationDao;
		this.priceDao = priceDao;
		this.displayInfoDao = displayInfoDao;
		this.reservationCommentDao = reservationCommentDao;
	}
	
	@Override
	@Transactional
	public void createReservations(Reservation reservation) {
		reservationDao.insertReservation(reservation);
		priceDao.insertPrices(reservation.getId(), reservation.getPrices());
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReservationInfo> getReservations(String reservationEmail) {
		List<ReservationInfo> reservations = reservationDao.selectAllReservationsByReservationEmail(reservationEmail);
		// DB 테이블로부터 아직 매핑하지 못한 displayInfo와 totalPrice 구하기!
		reservations.forEach( (reservation) -> {
			reservation.setTotalPrice(reservationDao.selectTotalPriceByReservationInfoId(reservation.getReservationInfoId()));
			reservation.setDisplayInfo(displayInfoDao.selectDisplayInfo(reservation.getDisplayInfoId(), reservation.getProductId()));
		});
		
		return reservations;
	}

	@Override
	@Transactional
	public void updateCancelFlagByReservationInfoId(Integer reservationInfoId) {
		reservationDao.updateCancelFlagByReservationInfoId(reservationInfoId);
	}
	
	@Override
	@Transactional
	public void createReservationComment(ReservationComment reservationComment) {
		reservationCommentDao.insertReservationComment(reservationComment);
	}
}