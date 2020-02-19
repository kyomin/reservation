package kr.or.connect.reservation.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.reserve.PriceDao;
import kr.or.connect.reservation.dao.reserve.ReservationDao;
import kr.or.connect.reservation.dto.reserve.Reservation;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	private final ReservationDao reservationDao;
	private final PriceDao priceDao;
	
	public ReservationServiceImpl(ReservationDao reservationDao, PriceDao priceDao) {
		this.reservationDao = reservationDao;
		this.priceDao = priceDao;
	}
	
	@Override
	@Transactional
	public void createReservation(Reservation reservation) {
		reservationDao.insertReservation(reservation);
		priceDao.insertPrices(reservation.getId(), reservation.getPrices());
	}
}