package kr.or.connect.reservation.dto.detail;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
	private String comment;							// 상품평
	private int commentId;							// 상품평 id
	private List<CommentImage> commentImages;		// 상품평 이미지들 => 추후 이 안에 이너 클래스로 정의해준다. 여기서만 사용한다면.
	private String createDate;						// 생성일
	private String modifyDate;						// 수정일
	private int productId;							// 상품 id
	private String reservationDate;					// 예약일
	private String reservationEmail;				// 예약자 이메일
	private int reservationInfoId;					// 예약자 id
	private String reservationName;					// 예약자 이름
	private String reservationTelephone;			// 예약자 전화번호
	private double score;							// 평점
}