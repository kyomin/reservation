package kr.or.connect.reservation.dto.mainpage;

import lombok.Data;

@Data
public class Product {
	private int displayInfoId;			// 전시 id
	private int productId;				// 상품 id
	private String productDescription;	// 상품 설명
	private String placeName;			// 전시 장소 명
	private String productContent;		// 상품 상세 설명
	private String productImageUrl;		// 상품 썸네일 이미지 URL
}