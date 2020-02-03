package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductDao productDao;
	
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	@Transactional
	public List<Product> getProducts(Integer start) {
		return productDao.selectAllProducts(start, ProductService.LIMIT);
	}

	@Override
	@Transactional
	public List<Product> getProductsByCategory(Integer start, Integer categoryId) {
		return productDao.selectProductsByCategory(start, ProductService.LIMIT, categoryId);
	}
	
	@Override
	public int getProductsTotalCount() {
		return productDao.selectAllProductsCount();
	}
}
