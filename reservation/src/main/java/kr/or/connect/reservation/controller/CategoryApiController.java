package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.mainpage.Category;
import kr.or.connect.reservation.service.CategoryService;

@RestController
@RequestMapping(path="/api/categories")
public class CategoryApiController {
	private final CategoryService categoryService;
	
	public CategoryApiController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public Map<String, Object> getCategories() {
		Map<String, Object> map = new HashMap<>();
		List<Category> categories = categoryService.getCategories();
		
		map.put("categories", categories);
		
		return map;
	}
}