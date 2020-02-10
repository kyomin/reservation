package kr.or.connect.reservation.dao.detail;

public class DisplayInfoDaoSqls {
	public static final String SELECT_DISPLAY_INFO = "SELECT categoryId, categoryName, productContent, productDescription, productEvent, productId, display_info.create_date AS createDate, display_info.id AS displayInfoId, email, homepage, display_info.modify_date AS modifyDate, display_info.opening_hours AS openingHours, display_info.place_lot AS placeLot, display_info.place_name AS placeName, display_info.place_street AS placeStreet, tel As telephone FROM display_info LEFT JOIN (SELECT category.id AS categoryId, category.name AS categoryName, product.content AS productContent, product.description AS productDescription, product.event AS productEvent, product.id AS productId FROM product LEFT JOIN category ON product.category_id = category.id WHERE product.id = :productId) AS product_with_category ON display_info.product_id = product_with_category.productId WHERE display_info.id = :displayInfoId";
}
