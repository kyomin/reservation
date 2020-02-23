package kr.or.connect.reservation.dto.login;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
	@NotEmpty 
	@NotBlank 
	@Email
	private String reservationEmail;		// 로그인 계정 정보를 위한 예약자 이메일
}
