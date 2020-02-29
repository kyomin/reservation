package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.mainpage.Promotion;
import kr.or.connect.reservation.service.PromotionService;

@RestController
@RequestMapping(path="/api/promotions")
public class PromotionApiController {
	private final PromotionService promotionService;
	
	public PromotionApiController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}
	
	@GetMapping
	public Map<String, Object> getPromotions() throws Exception {
		List<Promotion> promotions = promotionService.getPromotions();
		Map<String, Object> map = new HashMap<>();
		
		map.put("promotions", promotions);
		
		return map;
	}
}