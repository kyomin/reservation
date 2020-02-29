package kr.or.connect.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.mainpage.Product;
import kr.or.connect.reservation.service.ProductService;

@RestController
public class ProductApiController {
	private final ProductService productService;
	
	public ProductApiController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/api/products")
	public ResponseEntity<?> getProducts(@RequestParam(name = "start", required=false, defaultValue="0")Integer start, @RequestParam(name = "category_id", required=false)Integer categoryId) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		Optional<Integer> judgeCategoryIdNullable = Optional.ofNullable(categoryId);
		
		int totalCount = productService.getProductsCount(judgeCategoryIdNullable);
		List<Product> products = productService.getProducts(start, judgeCategoryIdNullable);
		
		resultMap.put("products", products);
		resultMap.put("totalCount", totalCount);
		
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
	
	@GetMapping("/api/products/{displayInfoId}")
	public ResponseEntity<?> getProductDetail(@PathVariable("displayInfoId") int displayInfoId) throws Exception {
		return new ResponseEntity<>(productService.getProductDetail(displayInfoId), HttpStatus.OK);
	}
}