package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.mainpage.CategoryDao;
import kr.or.connect.reservation.dto.mainpage.Category;
import kr.or.connect.reservation.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	private final CategoryDao categoryDao;
	
	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> getCategories() {
		return categoryDao.selectCategories();
	}
}