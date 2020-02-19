package kr.or.connect.reservation.dao.detail;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.connect.reservation.dto.detail.DisplayInfoImage;

@Mapper
public interface DisplayInfoImageDao {
	DisplayInfoImage selectDisplayInfoImage(@Param("displayInfoId")int displayInfoId);
}