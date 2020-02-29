package kr.or.connect.reservation.dto.file;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageFile {
	String contentType;
	Boolean deleteFlag;
	Integer fileId;
	String fileName;
	String saveFileName;
	String createDate;
	String modifyDate;
}