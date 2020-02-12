package kr.or.connect.reservation.service;

import java.util.List;
import java.util.Optional;

import kr.or.connect.reservation.dto.detail.ProductDetail;
import kr.or.connect.reservation.dto.mainpage.Product;

public interface ProductService {
	public static final Integer LIMIT = 4;
	public List<Product> getProducts(Integer start, Optional<Integer> categoryId);
	public ProductDetail getProductDetail(Integer displayInfoId);
	public Integer getProductsCount(Optional<Integer> categoryId);
}