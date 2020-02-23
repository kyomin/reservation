package kr.or.connect.reservation.dto.reserve;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price {
	@NotNull
	private Integer count;						// 상품 티켓 갯수
	
	@NotNull
	private Integer productPriceId;				// 상품 id
	
	private Integer reservationInfoId;			// 예약 정보 id
	private Integer reservationInfoPriceId;		// 여러 타입의 가격에 대한 예약 정보 id
}