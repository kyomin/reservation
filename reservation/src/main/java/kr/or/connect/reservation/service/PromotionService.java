package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.mainpage.Promotion;

public interface PromotionService {
	List<Promotion> getPromotions() throws Exception;
}