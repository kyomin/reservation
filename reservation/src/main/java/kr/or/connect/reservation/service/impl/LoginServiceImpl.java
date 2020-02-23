package kr.or.connect.reservation.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.login.LoginDao;
import kr.or.connect.reservation.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	private final LoginDao loginDao;
	
	public LoginServiceImpl(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	@Override
	@Transactional(readOnly = true)
	public int getLoginInfoCount(String reservationEmail) {
		return loginDao.selectAllReservationsCountByReservationEmail(reservationEmail);
	}
}