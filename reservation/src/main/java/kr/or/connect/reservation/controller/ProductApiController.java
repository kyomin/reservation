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

import kr.or.connect.reservation.dto.detail.ProductDetail;
import kr.or.connect.reservation.dto.mainpage.Product;
import kr.or.connect.reservation.service.ProductService;

@RestController
public class ProductApiController {
	private final ProductService productService;
	
	public ProductApiController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/api/products")
	public Map<String, Object> getProducts(@RequestParam(name = "start", required=false, defaultValue="0")Integer start, @RequestParam(name = "category_id", required=false)Integer categoryId) {
		Map<String, Object> resultMap = new HashMap<>();
		Optional<Integer> judgeCategoryIdNullable = Optional.ofNullable(categoryId);
		
		int totalCount = Optional.ofNullable(productService.getProductsCount(judgeCategoryIdNullable)).orElseGet(() -> 0);
		List<Product> products = productService.getProducts(start, judgeCategoryIdNullable);
		
		resultMap.put("products", products);
		resultMap.put("totalCount", totalCount);
		
		return resultMap;
	}
	
	@GetMapping("/api/products/{displayInfoId}")
	public ResponseEntity<ProductDetail> getProductDetail(@PathVariable("displayInfoId") int displayInfoId) {
		return new ResponseEntity<>(productService.getProductDetail(displayInfoId), HttpStatus.OK);
	}
}