package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> getCategories() throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		List<Category> categories = categoryService.getCategories();
		
		resultMap.put("categories", categories);
		
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
}