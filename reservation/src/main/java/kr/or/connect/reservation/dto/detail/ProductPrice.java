package kr.or.connect.reservation.dto.detail;

import lombok.Data;

@Data
public class ProductPrice {
	private String createDate;		// 생성일
	private double discountRate;	// 할인율
	private String modifyDate;		// 수정일
	private int price;				// 가격
	private String priceTypeName;	// 가격 타입명
	private int productId;			// 상품 id
	private int productPriceId;		// 상품 가격 id
}