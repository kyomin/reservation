package kr.or.connect.reservation.service;

import java.io.IOException;

import kr.or.connect.reservation.dto.file.ImageFile;
import kr.or.connect.reservation.enums.ImageUploadFolderPath;

public interface FileService {
	void uploadImageFileInLocal(byte[] uploadImageBytes, String saveFileName) throws IOException;
	String generateSaveFileName(String originalFileName, ImageUploadFolderPath imageUploadFolderPath);
	byte[] downloadFileByteData(String saveFileName) throws IOException;
	ImageFile getFileInfoById(Integer id) throws Exception;
}