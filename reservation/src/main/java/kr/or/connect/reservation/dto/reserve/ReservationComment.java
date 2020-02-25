package kr.or.connect.reservation.dto.reserve;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationComment {
	/*		reservation_user_comment 테이블 관련	*/
	@NotNull
	Integer productId;			// 상품 id
	
	@NotNull
	Integer reservationInfoId;	// 예약 정보 id
	
	@NotNull
	Integer score;				// 별점
	
	@NotNull
	@NotEmpty 
	@NotBlank 
	@Length(min = 5, max = 400)
	String comment;			// 평
	
	
	/*		reservation_user_comment_image 및 file_info 테이블 관련	*/
	MultipartFile commentImage;
	String saveFileName;
	Integer reservationUserCommentId;
	Integer fileId;
	Integer reservationUserCommentImageId;
}