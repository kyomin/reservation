package kr.or.connect.reservation.service.impl;

import java.util.List;
import java.util.Optional;

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
	@Transactional(readOnly = true)
	public List<Product> getProducts(Integer start, Optional<Integer> categoryId) {
		
		return categoryId.isPresent() ? productDao.selectProductsByCategory(start, ProductService.LIMIT, categoryId.get()) : 
					productDao.selectAllProducts(start, ProductService.LIMIT);
	}

	@Override
	public int getProductsTotalCount() {
		return productDao.selectAllProductsCount();
	}
}
