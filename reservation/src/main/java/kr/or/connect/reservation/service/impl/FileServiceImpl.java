package kr.or.connect.reservation.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import kr.or.connect.reservation.dao.file.ImageFileDao;
import kr.or.connect.reservation.dto.file.ImageFile;
import kr.or.connect.reservation.enums.ImageUploadFolderPath;
import kr.or.connect.reservation.service.FileService;

@Service
@PropertySource("classpath:datasource.properties")
public class FileServiceImpl implements FileService {
	@Value("${image.local.file.path}")
	private String LOCAL_FILE_PATH;
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmmss");
	
	private final ImageFileDao imegeFileDao;
	
	public FileServiceImpl(ImageFileDao imegeFileDao) {
		this.imegeFileDao = imegeFileDao;
	}
	
	@Override
	public void uploadImageFileInLocal(byte[] uploadImageBytes, String saveFileName) throws IOException {
		FileCopyUtils.copy(uploadImageBytes, new File(LOCAL_FILE_PATH + saveFileName));
	}

	@Override
	public String generateSaveFileName(String originalFileName, ImageUploadFolderPath imageUploadFolderPath) {
		return imageUploadFolderPath.getFolderPath() + LocalDateTime.now().format(formatter) + originalFileName;
	}

	@Override
	public byte[] downloadFileByteData(String saveFileName) throws IOException {
		Path path = Paths.get(LOCAL_FILE_PATH + saveFileName);
		return Files.readAllBytes(path);
	}

	@Override
	public ImageFile getFileInfoById(Integer id) throws Exception {
		return imegeFileDao.selectSaveFileNameById(id);
	}	
}