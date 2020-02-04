package kr.or.connect.reservation.dao;

public class ProductDaoSqls {
	// J1 : 'product_image' LEFT JOIN 'file_info'
	public static final String JOIN_FOR_PRODUCT1 = "(SELECT product_id, file_name FROM product_image LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE product_image.type = 'th')";
	// J2 : 'product' LEFT JOIN 'J1'
	public static final String JOIN_FOR_PRODUCT2 = "(SELECT id AS productId, file_name AS productImageUrl, description AS productDescription, content AS productContent FROM product LEFT JOIN " + JOIN_FOR_PRODUCT1 + " AS J1 ON product.id = J1.product_id)";
	// J3 : 'product' LEFT JOIN 'J1' BY CATEGORY ID
	public static final String JOIN_FOR_PRODUCT3 = "(SELECT id AS productId, file_name AS productImageUrl, description AS productDescription, content AS productContent FROM product LEFT JOIN " + JOIN_FOR_PRODUCT1 + " AS J1 ON product.id = J1.product_id WHERE category_id = :categoryId)";
		
	// 위의 조인 테이블을 이용하여 상품 목록 불러오기를 수행하는 쿼리문
	public static final String SELECT_ALL_PRODUCTS = "SELECT productId, productImageUrl, productDescription, productContent, display_info.id AS displayInfoId, place_name AS placeName FROM " + JOIN_FOR_PRODUCT2 + " AS product LEFT JOIN display_info ON product.productId = display_info.product_id";
	public static final String SELECT_ALL_PRODUCTS_WITH_LIMIT = "SELECT productId, productImageUrl, productDescription, productContent, display_info.id AS displayInfoId, place_name AS placeName FROM " + JOIN_FOR_PRODUCT2 + " AS product LEFT JOIN display_info ON product.productId = display_info.product_id LIMIT :start, :limit";
	public static final String SELECT_PRODUCTS_BY_CATEGORY_WITH_LIMIT = "SELECT productId, productImageUrl, productDescription, productContent, display_info.id AS displayInfoId, place_name AS placeName FROM " + JOIN_FOR_PRODUCT3 + " AS product LEFT JOIN display_info ON product.productId = display_info.product_id LIMIT :start, :limit";
	
	public static final String SELECT_COUNT_ALL_PRODUCTS = "SELECT count(*) FROM (" + SELECT_ALL_PRODUCTS + ") AS ALL_PRODUCTS";
	public static final String SELECT_COUNT_PRODUCTS_BY_CATEGORY = ""; 
}
