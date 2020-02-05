package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping(path="/api/products")
public class ProductApiController {
	private final ProductService productService;
	
	public ProductApiController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public Map<String, Object> list(@RequestParam(name = "start", required=false, defaultValue="0")Integer start, @RequestParam(name = "category_id", required=false)Integer categoryId) {
		Map<String, Object> map = new HashMap<>();
		List<Product> products;
		Integer totalCount;
		Optional<Integer> isCategoryIdNull = Optional.ofNullable(categoryId);		
		
		products = productService.getProducts(start, isCategoryIdNull);
		totalCount = isCategoryIdNull.isPresent() ? null : productService.getProductsTotalCount();
		
		map.put("products", products);
		map.put("totalCount", totalCount);
		
		return map;
	}
}
