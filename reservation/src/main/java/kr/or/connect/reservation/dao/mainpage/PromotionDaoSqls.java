package kr.or.connect.reservation.dao.mainpage;

public class PromotionDaoSqls {
	// 'product_image' LEFT JOIN 'file_info'
	public static final String JOIN_FOR_PROMOTION = "(SELECT product_id AS productId, file_name AS productImageUrl FROM product_image LEFT JOIN file_info ON product_image.file_id = file_info.id WHERE product_image.type = 'th')";

	public static final String SELECT_ALL_PROMOTIONS = "SELECT id, productId, productImageUrl FROM promotion LEFT JOIN " + JOIN_FOR_PROMOTION + " AS J1 ON promotion.product_id = J1.productId";
}
