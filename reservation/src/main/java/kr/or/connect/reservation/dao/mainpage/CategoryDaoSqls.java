package kr.or.connect.reservation.dao.mainpage;

public class CategoryDaoSqls {
	public static final String JOIN_FOR_CATEGORY = "(SELECT category_id, count(*) AS count FROM product LEFT JOIN display_info ON product.id = display_info.product_id GROUP BY category_id)";
	
	public static final String SELECT_ALL_CATEGORIES = "SELECT id, name, count FROM category LEFT JOIN " + JOIN_FOR_CATEGORY + " AS J1 ON category.id = J1.category_id";
}
