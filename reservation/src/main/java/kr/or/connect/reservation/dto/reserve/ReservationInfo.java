package kr.or.connect.reservation.dto.reserve;

import kr.or.connect.reservation.dto.detail.DisplayInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationInfo {
	private boolean cancelYn;				// 예약 취소 여부
	private String createDate;				// 예약 생성 일시
	private DisplayInfo displayInfo;	
	private int displayInfoId;				// 전시상품 id
	private String modifyDate;				// 예약 수정일시
	private int productId;					// 상품 id
	private String reservationDate;			// 예약일
	private String reservationEmail;		// 예약자 이메일
	private int reservationInfoId;			// 예약 id
	private String reservationName;			// 예약자명
	private String reservationTelephone;	// 예약자 전화번호
	private int totalPrice;					// 예약한 상품 총 가격
}