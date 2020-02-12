package kr.or.connect.reservation.dto.mainpage;

import lombok.Data;

@Data
public class Promotion {
	private int id;					// 프로모션 id
	private int productId;			// 상품 id
	private String productImageUrl;	// 상품 썸네일 이미지 URL
}