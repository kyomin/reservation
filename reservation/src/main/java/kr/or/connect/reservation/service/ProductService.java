package kr.or.connect.reservation.service;

import java.util.List;
import java.util.Optional;

import kr.or.connect.reservation.dto.Product;

public interface ProductService {
	public static final Integer LIMIT = 4;
	public List<Product> getProducts(Integer start, Optional<Integer> categoryId);
	public int getProductsTotalCount();
}
