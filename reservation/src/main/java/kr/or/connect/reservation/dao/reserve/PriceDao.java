package kr.or.connect.reservation.dao.reserve;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.connect.reservation.dto.reserve.Price;

@Mapper
public interface PriceDao {
	void insertPrices(@Param("reservationInfoId")Integer reservationInfoId, @Param("prices")List<Price> prices);
}
