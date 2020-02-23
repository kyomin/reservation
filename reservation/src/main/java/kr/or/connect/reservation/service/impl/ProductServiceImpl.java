package kr.or.connect.reservation.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.detail.CommentDao;
import kr.or.connect.reservation.dao.detail.DisplayInfoDao;
import kr.or.connect.reservation.dao.detail.DisplayInfoImageDao;
import kr.or.connect.reservation.dao.detail.ProductImageDao;
import kr.or.connect.reservation.dao.detail.ProductPriceDao;
import kr.or.connect.reservation.dao.mainpage.ProductDao;
import kr.or.connect.reservation.dto.detail.Comment;
import kr.or.connect.reservation.dto.detail.ProductDetail;
import kr.or.connect.reservation.dto.mainpage.Product;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	// for mainpage.jsp
	private final ProductDao productDao;
	
	// for detail.jsp
	private final CommentDao commentDao;
	private final DisplayInfoDao displayInfoDao;
	private final DisplayInfoImageDao displayInfoImageDao;
	private final ProductImageDao productImageDao;
	private final ProductPriceDao productPriceDao;
	
	public ProductServiceImpl(ProductDao productDao, CommentDao commentDao, DisplayInfoDao displayInfoDao, DisplayInfoImageDao displayInfoImageDao, ProductImageDao productImageDao, ProductPriceDao productPriceDao) {
		this.productDao = productDao;
		this.commentDao = commentDao;
		this.displayInfoDao = displayInfoDao;
		this.displayInfoImageDao = displayInfoImageDao;
		this.productImageDao = productImageDao;
		this.productPriceDao = productPriceDao;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> getProducts(Integer start, Optional<Integer> categoryId) {
		return categoryId.map( id -> 
			productDao.selectProductsByCategoryId(start, ProductService.LIMIT, id)
		).orElse( 
			productDao.selectAllProducts(start, ProductService.LIMIT)
		);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ProductDetail getProductDetail(Integer displayInfoId) {
		ProductDetail productDetail = new ProductDetail();
		
		//	displayInfoId로부터 해당하는 productId 추출!
		int productId = displayInfoDao.selectProductIdWithDisplayInfoId(displayInfoId);
		
		//	한줄평 이미지가 있는 것들만 필터링해 이미지 삽입!
		List<Comment> comments = commentDao.selectAllCommentsByProductIdWithoutCommentImage(productId);
		comments.forEach( (comment) -> {
			comment.setCommentImages(commentDao.selectAllCommentImagesByReservationUserCommentId(comment.getCommentId()));
		});
		
		productDetail.setComments(comments);
		productDetail.setDisplayInfo(displayInfoDao.selectDisplayInfo(displayInfoId, productId));
		productDetail.setAverageScore(commentDao.selectAverageScoreOfCommentByProductId(productId));
		productDetail.setDisplayInfoImage(displayInfoImageDao.selectDisplayInfoImage(displayInfoId));
		productDetail.setProductImages(productImageDao.selectAllProductImages(productId));
		productDetail.setProductPrices(productPriceDao.selectAllProductPrices(productId));
		
		return productDetail;
	}
	
	@Override
	@Transactional(readOnly = true)
	public int getProductsCount(Optional<Integer> categoryId) {
		return categoryId.map( id -> productDao.selectProductsCountByCategoryId(id)
		).orElse( 
			productDao.selectAllProductsCount()
		);
	}
}