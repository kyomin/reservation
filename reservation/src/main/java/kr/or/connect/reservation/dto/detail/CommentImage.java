package kr.or.connect.reservation.dto.detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentImage {
	private String contentType;				// 파일 확장자
	private String createDate;				// 생성일
	private boolean deleteFlag;				// 삭제 여부
	private int fileId;						// 파일 아이디
	private String fileName;				// 파일 이름
	private int imageId;					// 이미지 id
	private String modifyDate;				// 수정일
	private String saveFileName;			// 파일 저장 위치 이름
	private int reservationInfoId;			// 예약 id
	private int reservationUserCommentId;	// 예약자 상품평 id
}