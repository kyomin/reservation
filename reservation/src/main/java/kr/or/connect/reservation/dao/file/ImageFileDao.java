package kr.or.connect.reservation.dao.file;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.connect.reservation.dto.file.ImageFile;

@Mapper
public interface ImageFileDao {
	ImageFile selectSaveFileNameById(@Param("id") Integer id);
}