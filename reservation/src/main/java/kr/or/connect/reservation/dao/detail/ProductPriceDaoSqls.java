package kr.or.connect.reservation.dao.detail;

public class ProductPriceDaoSqls {
	public static final String SELECT_ALL_PRODUCT_PRICES = "SELECT create_date AS createDate, discount_rate AS discountRate, modify_date AS modifyDate, price, price_type_name AS priceTypeName, product_id AS productId, id AS productPriceId FROM product_price WHERE product_id = :productId";
}
