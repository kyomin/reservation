package kr.or.connect.reservation.dao;

public class ProductDaoSqls {
	/* 
	 * 전체리스트 불러오기 작업을 위한 쿼리
	 */
	public static final String JOIN_FOR_PRODUCT1 = "SELECT product_id, file_name FROM product_image LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE product_image.type = 'th'";
	
	public static final String JOIN_FOR_PRODUCT2 = "SELECT id AS productId, productImageUrl, description AS productDescription, content AS productContent FROM product LEFT JOIN SELECT product_id, file_name AS productImageUrl FROM product_image LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE product_image.type = 'th') AS J1 ON product.id = J1.product_id";
	
	public static final String SELECT_ALL_PRODUCTS_WITH_LIMIT = "SELECT productId, productImageUrl, productDescription, productContent, display_info.id AS displayInfoId, place_name AS placeName FROM (SELECT id AS productId, productImageUrl, description AS productDescription, content AS productContent FROM product LEFT JOIN (SELECT product_id, file_name AS productImageUrl FROM product_image LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE product_image.type = 'th') AS J1 ON product.id = J1.product_id) AS product LEFT JOIN display_info ON product.productId = display_info.product_id LIMIT :start, :limit";
	
	public static final String SELECT_COUNT_ALL = "SELECT count(*) FROM product";
}
