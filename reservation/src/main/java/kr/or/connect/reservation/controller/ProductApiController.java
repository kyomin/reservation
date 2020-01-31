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
	public Map<String, Object> list(@RequestParam(name = "start", required = false, defaultValue = "0")int start) {
		System.out.println("/api/products로 GET 요청 왔는지 확인!");
		System.out.println("start : " + start);
		
		List<Product> products = productService.getProducts(start);
		int totalCount = productService.getProductsTotalCount();

		System.out.println("totalCount : " + totalCount);
		
		Map<String, Object> map = new HashMap<>();
		map.put("products", products);
		map.put("totalCount", totalCount);

		return map;
	}

}
