package kr.or.connect.reservation.dto.detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayInfoImage {
	private String contentType;			// 파일 확장자
	private String createDate;			// 생성일
	private boolean deleteFlag;			// 삭제 여부
	private int displayInfoId;			// 전시 id
	private int displayInfoImageId;		// 전시 이미지 id
	private int fileId;					// 파일 id
	private String fileName;			// 파일 이름
	private String modifyDate;			// 수정일
	private String saveFileName;		// 파일 저장 위치 이름
}