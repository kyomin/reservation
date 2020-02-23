package kr.or.connect.reservation.dto.reserve;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {
	private Integer id;
	
	@NotNull
	private Integer displayInfoId;
	
	private List<Price> prices;							// 해당 상품의 여러 가격 타입에 대한 사용자 주문 정보 리스트
	
	@NotNull
	private Integer productId;
	
	@NotNull
	@NotEmpty 
	@NotBlank 
	@Email
	private String reservationEmail;					// 예약자 이메일
	
	@NotNull
	@NotEmpty 
	@NotBlank 
	@Length(min = 2, max = 17)
	private String reservationName;						// 예약자 성명
	
	@NotNull
	@NotBlank
	@Pattern(regexp="01[016789]-[0-9]{3,4}-[0-9]{4}")
	private String reservationTelephone;				// 예약자 핸드폰 번호
	
	@NotNull
	@NotBlank
	private String reservationYearMonthDay;
}