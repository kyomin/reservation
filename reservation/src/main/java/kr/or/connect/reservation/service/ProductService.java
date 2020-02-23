package kr.or.connect.reservation.service;

import java.util.List;
import java.util.Optional;

import kr.or.connect.reservation.dto.detail.ProductDetail;
import kr.or.connect.reservation.dto.mainpage.Product;

public interface ProductService {
	static final int LIMIT = 4;
	List<Product> getProducts(Integer start, Optional<Integer> categoryId);
	ProductDetail getProductDetail(Integer displayInfoId);
	int getProductsCount(Optional<Integer> categoryId);
}