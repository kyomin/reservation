package kr.or.connect.reservation.dto.detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImage {
	private String contentType;			// 파일 확장자
	private String createDate;			// 생성일
	private boolean deleteFlag;			// 삭제 여부
	private int fileInfoId;				// 파일 id
	private String fileName;			// 파일 이름
	private String modifyDate;			// 수정일
	private int productId;				// 상품 id
	private int productImageId;			// 상품 이미지 id
	private String saveFileName;		// 파일 저장 위치 이름
	private String type;				// 이미지 타입
}