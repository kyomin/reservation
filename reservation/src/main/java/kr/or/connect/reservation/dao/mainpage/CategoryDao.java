package kr.or.connect.reservation.dao.mainpage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.connect.reservation.dto.mainpage.Category;

@Mapper
public interface CategoryDao {
	List<Category> selectCategories();
}