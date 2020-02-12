package kr.or.connect.reservation.dto.detail;

import lombok.Data;

@Data
public class DisplayInfo {
	private int categoryId;				// 카테고리 id
	private String categoryName;		// 카테고리 이름
	private String createDate;			// 생성일
	private int displayInfoId;			// 전시 id
	private String email;				// 이메일
	private String homepage;			// 홈페이지
	private String modifyDate;			// 수정일
	private String openingHours;		// 전시 시간
	private String placeLot;			// 전시 번지명
	private String placeName;			// 전시장
	private String placeStreet;			// 전시 도로명
	private String productContent;		// 상품 내용
	private String productDescription;	// 상품 설명
	private String productEvent;		// 상품 이벤트
	private int productId;				// 상품 id
	private String telephone;			// 전화번호
}