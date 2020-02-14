package kr.or.connect.reservation.dao.detail;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.connect.reservation.dto.detail.DisplayInfo;

@Mapper
public interface DisplayInfoDao {
	public DisplayInfo selectDisplayInfo(@Param("displayInfoId")int displayInfoId, @Param("productId")int productId);	
	public int selectProductIdWithDisplayInfoId(@Param("displayInfoId")int displayInfoId);
}