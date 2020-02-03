package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, Object> list(@RequestParam(name = "start", required = false, defaultValue = "0")int start,
			@RequestParam(name = "category_id", required = false, defaultValue = "-1")int categoryId) {
		Map<String, Object> map = new HashMap<>();
		List<Product> products;
		int totalCount = productService.getProductsTotalCount();
		
		if(categoryId != -1) {	// 전체 리스트가 아닐 경우
			products = productService.getProductsByCategory(start, categoryId);
			map.put("products", products);
			map.put("totalCount", -1);	// 클라이언트에서 이 값으로 판단한다.
		} else {	// 전체 리스트일 경우!
			products = productService.getProducts(start);
			map.put("products", products);
			map.put("totalCount", totalCount);
		}
	
		return map;
	}
}
