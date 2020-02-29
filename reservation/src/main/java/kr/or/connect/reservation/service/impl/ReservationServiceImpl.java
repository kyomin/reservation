package kr.or.connect.reservation.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dao.detail.DisplayInfoDao;
import kr.or.connect.reservation.dao.reserve.PriceDao;
import kr.or.connect.reservation.dao.reserve.ReservationCommentDao;
import kr.or.connect.reservation.dao.reserve.ReservationDao;
import kr.or.connect.reservation.dto.reserve.Reservation;
import kr.or.connect.reservation.dto.reserve.ReservationComment;
import kr.or.connect.reservation.dto.reserve.ReservationInfo;
import kr.or.connect.reservation.enums.ImageUploadFolderPath;
import kr.or.connect.reservation.service.FileService;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	// use DAO
	private final ReservationDao reservationDao;
	private final PriceDao priceDao;
	private final ReservationCommentDao reservationCommentDao;	
	private final DisplayInfoDao displayInfoDao;
	
	// use Service
	private final FileService fileService;
	
	public ReservationServiceImpl(ReservationDao reservationDao, PriceDao priceDao, DisplayInfoDao displayInfoDao, ReservationCommentDao reservationCommentDao, FileService fileService) {
		this.reservationDao = reservationDao;
		this.priceDao = priceDao;
		this.displayInfoDao = displayInfoDao;
		this.reservationCommentDao = reservationCommentDao;
		
		this.fileService = fileService;
	}
	
	@Override
	@Transactional
	public void createReservations(Reservation reservation) throws Exception {
		reservationDao.insertReservation(reservation);
		priceDao.insertPrices(reservation.getId(), reservation.getPrices());
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReservationInfo> getMyReservationInfoByEmail(String reservationEmail) throws Exception {
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
	public void updateCancelFlagByReservationInfoId(Integer reservationInfoId) throws Exception {
		reservationDao.updateCancelFlagByReservationInfoId(reservationInfoId);
	}
	
	@Override
	@Transactional(rollbackFor = {SQLException.class, IOException.class})
	public void createReservationComment(ReservationComment reservationComment) throws SQLException, IOException {
		reservationCommentDao.insertReservationComment(reservationComment);
		
		if (reservationComment.getCommentImage() != null) {
			//setting
			MultipartFile imageFile = reservationComment.getCommentImage();
			String saveFileName = fileService.generateSaveFileName(imageFile.getOriginalFilename(), ImageUploadFolderPath.COMMENT);
			
			//upload
			fileService.uploadImageFileInLocal(imageFile.getBytes(), saveFileName);
			
			//database Insert
			reservationComment.setSaveFileName(saveFileName);
			reservationCommentDao.insertReservationCommentFile(reservationComment);
			reservationCommentDao.insertReservationCommentImage(reservationComment);
		}
	}
}